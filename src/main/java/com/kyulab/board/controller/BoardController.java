package com.kyulab.board.controller;

import com.kyulab.board.dto.Board;
import com.kyulab.board.service.BoardService;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@Table(name = "게시판 API")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/{boardId}")
	public ResponseEntity<?> readBoard(@PathVariable int boardId) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(boardService.readBoard(boardId));
	}

	@PostMapping
	public ResponseEntity<?> saveBoard(@RequestBody Board board) {
		boardService.saveBoard(board);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("생성");
	}


}
