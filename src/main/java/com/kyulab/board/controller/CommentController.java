package com.kyulab.board.controller;

import com.kyulab.board.config.GatewayConfig;
import com.kyulab.board.domain.Comment;
import com.kyulab.board.dto.request.CommentCreateRequest;
import com.kyulab.board.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comment")
@Tag(name = "댓글 API")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final WebClient webClient = GatewayConfig.getLocalWebClient();

	@GetMapping("/{postId}")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Comment> getComments(@PathVariable int postId) {
		return commentService.getComments(postId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Void> savePost(ServerHttpRequest httpRequest, @RequestBody CommentCreateRequest commentCreateRequest) {
		return webClient.post()
				.uri("/user/auth/{userId}", commentCreateRequest.getCommentAuthorId())
				.header(HttpHeaders.AUTHORIZATION, httpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Boolean.class)
				.flatMap(userExists -> {
					if (userExists != null && userExists) {
						Comment newComment = Comment.builder()
								.postId(commentCreateRequest.getPostId())
								.commentAuthorId(commentCreateRequest.getCommentAuthorId())
								.commentConent(commentCreateRequest.getCommentConent())
								.build();
						return commentService.saveComment(newComment).doOnTerminate(() ->
								System.out.println("답글 저장 완료"));
					} else {
						return Mono.error(new Exception("User with ID " + commentCreateRequest.getCommentAuthorId() + " not found."));
					}
				});
	}

}
