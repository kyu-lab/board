package com.kyulab.board.repository;

import com.kyulab.board.domain.Board;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends R2dbcRepository<Board, Integer> {

}
