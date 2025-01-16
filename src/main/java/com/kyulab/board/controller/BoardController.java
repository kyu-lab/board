package com.kyulab.board.controller;

import com.kyulab.board.service.GatewayService;
import com.kyulab.board.domain.Board;
import com.kyulab.board.dto.request.board.BoardRequest;
import com.kyulab.board.dto.response.board.BoardResponse;
import com.kyulab.board.service.BoardKafkaService;
import com.kyulab.board.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/board")
@Tag(name = "게시판 생성 API")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final BoardKafkaService boardKafkaService;
	private final GatewayService.ApiSerivce apiSerivce;

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardResponse> getBoard(@PathVariable long id) {
		return ResponseEntity.ok(new BoardResponse());
	}

	@GetMapping
	public ResponseEntity<List<Board>> getBoards() {
		return ResponseEntity.ok(boardService.getBoards());
	}

	@PostMapping
	public ResponseEntity<String> saveBoard(HttpServletRequest httpRequest, @RequestBody BoardRequest boardRquest) {
		String uri = "/v1/user/auth/" + boardRquest.getUserId();
		String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		if (Boolean.TRUE.equals(Boolean.parseBoolean(apiSerivce.postStringDataWithToken(uri, token)))) {
			Board newBoard = Board.builder()
									.userId(boardRquest.getUserId())
									.name(boardRquest.getBoardName())
									.build();
			boardService.saveBoard(newBoard);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
