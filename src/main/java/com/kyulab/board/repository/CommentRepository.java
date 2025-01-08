package com.kyulab.board.repository;

import com.kyulab.board.domain.Comment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentRepository extends R2dbcRepository<Comment, Integer> {

	Flux<Comment> findByPostId(int postId);
	Mono<Boolean> existsByPostId(int postId);

}
