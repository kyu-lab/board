package com.kyulab.board.service;

import com.kyulab.board.dto.Board;
import com.kyulab.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public Optional<Board> readBoard(int boardId) {
		return boardRepository.findById(boardId);
	}

	public void saveBoard(Board board) {
		boardRepository.save(board);
	}

}
