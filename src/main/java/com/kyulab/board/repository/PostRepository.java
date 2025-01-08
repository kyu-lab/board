package com.kyulab.board.repository;

import com.kyulab.board.domain.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends R2dbcRepository<Post, Integer> {

}
