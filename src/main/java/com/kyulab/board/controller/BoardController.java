package com.kyulab.board.controller;

import com.kyulab.board.config.GatewayConfig;
import com.kyulab.board.domain.Board;
import com.kyulab.board.dto.request.BoardCreateRequest;
import com.kyulab.board.service.BoardKafkaService;
import com.kyulab.board.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/board")
@Tag(name = "게시판 API")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final BoardKafkaService boardKafkaService;
	private final WebClient webClient = GatewayConfig.getLocalWebClient();

	@GetMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Board> readBoard(@PathVariable int boardId) {
		return boardService.getBoard(boardId);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Board> readBoard() {
		return boardService.getBoards();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Void> saveBoard(ServerHttpRequest httpRequest, @RequestBody BoardCreateRequest boardRquest) {
		return webClient.post()
				.uri("/user/auth/{userId}", boardRquest.getRequestUserId())
				.header(HttpHeaders.AUTHORIZATION, httpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Boolean.class)
				.flatMap(userExists -> { // flatMap을 사용하여 Mono 처리
 					if (userExists != null && userExists) {
						Board newBoard = Board.builder()
										.creatorId(boardRquest.getRequestUserId())
										.boardName(boardRquest.getBoardName())
										.createDate(LocalDateTime.now())
										.build();
						return boardService.saveBoard(newBoard).doOnTerminate(() ->
								System.out.println("저장 완료"));
					} else {
						return Mono.error(new Exception("User with ID " + boardRquest.getRequestUserId() + " not found.")); // 에러 Mono 반환
					}
				});
	}

	// gRPC를 이용한 연동 (현재는 사용안함)
	/*
	@PostMapping
	public Mono<Void> saveBoard(@RequestBody Board board) {
		return boardService.userExists(board.getUserId())
			.flatMap(exists -> {
				if (Boolean.TRUE.equals(exists)) {
					boardService.saveBoard(board);
					boardKafkaService.sendMsg("user-group", "게시글 생성 완료");
					return Mono.empty();
				} else {
					return Mono.error(new Exception("존재하지 않는 회원 : " + board.getUserName()));
				}
			});
	}
	*/

}
