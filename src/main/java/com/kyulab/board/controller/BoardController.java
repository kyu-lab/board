package com.kyulab.board.controller;

import com.kyulab.board.dto.response.post.PostListResponse;
import com.kyulab.board.domain.Board;
import com.kyulab.board.dto.request.board.BoardRequest;
import com.kyulab.board.service.BoardKafkaService;
import com.kyulab.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@Tag(name = "게시판 API")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final BoardKafkaService boardKafkaService;

	@GetMapping("/{id}")
	@Operation(summary = "선택한 게시판의 게시글 목록을 가져온다.")
	public ResponseEntity<List<PostListResponse>> findPostById(@PathVariable long id) {
		return ResponseEntity.ok(boardService.findPostByBoardId(id));
	}

	@GetMapping
	public ResponseEntity<List<Board>> getBoards() {
		return ResponseEntity.ok(boardService.getBoards());
	}

	@PostMapping
	public ResponseEntity<String> saveBoard(@RequestBody BoardRequest boardRquest) {
		Board newBoard = Board.builder()
						.userId(boardRquest.getUserId())
						.name(boardRquest.getBoardName())
						.build();
		boardService.saveBoard(newBoard);
		return ResponseEntity.ok().build();
	}

}
