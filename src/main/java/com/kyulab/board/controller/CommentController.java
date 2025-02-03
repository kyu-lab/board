package com.kyulab.board.controller;

import com.kyulab.board.dto.response.comment.CommentResponse;
import com.kyulab.board.dto.request.comment.CommentRequest;
import com.kyulab.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@Tag(name = "댓글 API")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/{postId}")
	@Operation(summary = "댓글 목록을 가져온다.")
	public ResponseEntity<List<CommentResponse>> findAllCommentsByPostId(@PathVariable long postId) {
		return ResponseEntity.ok(commentService.findAllCommentsByPostId(postId));
	}

	@PostMapping
	@Operation(summary = "회원인증 후 댓글을 저장한다.")
	public ResponseEntity<String> saveComment(@RequestBody CommentRequest commentRequest) {
		if (commentService.saveComment(commentRequest)) {
			return ResponseEntity.ok().build();
		} else{
			return ResponseEntity.badRequest().build();
		}
	}

}
