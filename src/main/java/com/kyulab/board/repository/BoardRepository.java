package com.kyulab.board.repository;

import com.kyulab.board.dto.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
