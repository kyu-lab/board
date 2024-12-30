package com.kyulab.board.controller;

import com.kyulab.board.dto.Board;
import com.kyulab.board.service.BoardService;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/board")
@Table(name = "게시판 API")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	// 테스트중
	private final WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:8000").build();

	@GetMapping("/{boardId}")
	public ResponseEntity<?> readBoard(@PathVariable int boardId) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(boardService.readBoard(boardId));
	}

	// Rest 방식을 이용한 연동
	@PostMapping
	public Mono<Void> saveBoard(@RequestBody Board board) {
		return webClient.post()
				.uri("/api/user/exists/{userId}", board.getUserId())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Boolean.class)
				.flatMap(userExists -> { // flatMap을 사용하여 Mono 처리
					if (userExists != null && userExists) {
						boardService.saveBoard(board);
						return Mono.empty(); // Void를 반환하는 Mono 생성
					} else {
						return Mono.error(new Exception("User with ID " + board.getUserId() + " not found.")); // 에러 Mono 반환
					}
				});
	}

	@GetMapping("/test")
	public String getUser(@RequestParam String name) {
		return boardService.getUserResponse(name);
	}

}
