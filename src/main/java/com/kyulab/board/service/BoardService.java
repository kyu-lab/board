package com.kyulab.board.service;

import com.kyulab.board.domain.Board;
import com.kyulab.board.dto.response.board.BoardResponse;
import com.kyulab.board.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public List<Board> getBoards() {
		return boardRepository.findAll();
	}

	public boolean existsById(long id) {
		return boardRepository.existsById(id);
	}

	public Optional<Board> findById(long id) {
		if (existsById(id)) {
			return boardRepository.findById(id);
		}
		return Optional.empty();
	}

	public void saveBoard(Board board) {
		boardRepository.save(board);
	}

}
