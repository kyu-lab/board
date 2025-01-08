package com.kyulab.board.controller;

import com.kyulab.board.config.GatewayConfig;
import com.kyulab.board.domain.Board;
import com.kyulab.board.domain.Post;
import com.kyulab.board.dto.request.BoardCreateRequest;
import com.kyulab.board.dto.request.PostCreateRequest;
import com.kyulab.board.service.BoardKafkaService;
import com.kyulab.board.service.PostService;
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

import java.time.LocalDateTime;

@RestController
@RequestMapping("/post")
@Tag(name = "게시글 API")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final WebClient webClient = GatewayConfig.getLocalWebClient();

	@GetMapping("/{postId}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Post> readPost(@PathVariable int postId) {
		return postService.getPost(postId);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Post> readBoard() {
		return postService.getPosts();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Void> savePost(ServerHttpRequest httpRequest, @RequestBody PostCreateRequest postCreateRequest) {
		return webClient.post()
				.uri("/user/auth/{userId}", postCreateRequest.getAuthorId())
				.header(HttpHeaders.AUTHORIZATION, httpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Boolean.class)
				.flatMap(userExists -> {
					if (userExists != null && userExists) {
						Post newPost = Post.builder()
									.boardId(postCreateRequest.getBoardId())
									.authorId(postCreateRequest.getAuthorId())
									.authorName(postCreateRequest.getAuthorName())
									.postSubject(postCreateRequest.getPostSubject())
									.postContent(postCreateRequest.getPostContent())
									.createDate(LocalDateTime.now())
									.build();
						return postService.savePost(newPost).doOnTerminate(() ->
								System.out.println("저장 완료"));
					} else {
						return Mono.error(new Exception("User with ID " + postCreateRequest.getAuthorId() + " not found."));
					}
				});
	}

}
