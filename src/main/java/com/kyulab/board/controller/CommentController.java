package com.kyulab.board.controller;

import com.kyulab.board.dto.response.comment.CommentResponse;
import com.kyulab.board.service.GatewayService;
import com.kyulab.board.domain.Comment;
import com.kyulab.board.dto.request.comment.CommentRequest;
import com.kyulab.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comment")
@Tag(name = "댓글 API")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final GatewayService.ApiSerivce apiSerivce;

	@GetMapping("/{postId}")
	@Operation(summary = "댓글 목록을 가져온다.")
	public ResponseEntity<List<CommentResponse>> findAllCommentsByPostId(@PathVariable long postId) {
		return ResponseEntity.ok(commentService.findAllCommentsByPostId(postId));
	}

	@PostMapping
	@Operation(summary = "회원인증 후 댓글을 저장한다.")
	public ResponseEntity<String> saveComment(HttpServletRequest httpRequest, @RequestBody CommentRequest commentRequest) {
		String uri = "/v1/user/auth/" + commentRequest.getUserId();
		String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		if (Boolean.TRUE.equals(Boolean.parseBoolean(apiSerivce.postStringDataWithToken(uri, token)))) {
			if (commentService.saveComment(commentRequest)) {
				return ResponseEntity.ok().build();
			} else{
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
