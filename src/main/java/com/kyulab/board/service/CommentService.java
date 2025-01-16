package com.kyulab.board.service;

import com.kyulab.board.domain.Comment;
import com.kyulab.board.domain.Post;
import com.kyulab.board.dto.request.comment.CommentRequest;
import com.kyulab.board.dto.response.comment.CommentResponse;
import com.kyulab.board.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostService postService;

	public List<CommentResponse> findAllCommentsByPostId(long postId) {
		return commentRepository.findAllCommentsByPostId(postId);
	}

	public boolean saveComment(CommentRequest commentRequest) {
		Post targetPost = postService.findById(commentRequest.getPostId());
		if (Objects.isNull(targetPost)) {
			return false;
		}
		Comment newComment = Comment.builder()
				.userId(commentRequest.getUserId())
				.userName(commentRequest.getUserName())
				.content(commentRequest.getConent())
				.post(targetPost)
				.build();
		commentRepository.save(newComment);
		return true;
	}

}
