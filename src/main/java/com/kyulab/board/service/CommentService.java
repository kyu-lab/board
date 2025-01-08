package com.kyulab.board.service;

import com.kyulab.board.domain.Comment;
import com.kyulab.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostService postService;

	public Flux<Comment> getComments(int postId) {
		return commentRepository.findByPostId(postId);
	}

	public Mono<Void> saveComment(Comment comment) {
		return postService.existsByPostId(comment.getPostId())
				.flatMap(exists -> {
					if (exists) {
						return commentRepository.save(comment).then();
					} else {
						return Mono.error(new Exception("댓글 저장 실패"));
					}
				});
	}
}
