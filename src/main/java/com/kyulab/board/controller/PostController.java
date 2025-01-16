package com.kyulab.board.controller;

import com.kyulab.board.domain.Board;
import com.kyulab.board.dto.request.post.PostRequest;
import com.kyulab.board.dto.response.post.PostResponse;
import com.kyulab.board.service.GatewayService;
import com.kyulab.board.domain.Post;
import com.kyulab.board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/post")
@Tag(name = "게시글 API")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final GatewayService.ApiSerivce apiSerivce;

	@GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "게시글을 가져온다.")
	public ResponseEntity<PostResponse> getPost(@PathVariable long postId) {
		return ResponseEntity.ok(postService.getPostResponse(postId));
	}

	@GetMapping
	@Operation(summary = "게시글 목록을 가져온다.")
	public ResponseEntity<List<Post>> getPosts() {
		return ResponseEntity.ok(postService.getPosts());
	}

	@PostMapping
	@Operation(summary = "회원인증 후 게시글을 생성한다.")
	public ResponseEntity<String> savePost(HttpServletRequest httpRequest, @RequestBody PostRequest postRequest) {
		String uri = "/v1/user/auth/" + postRequest.getUserId();
		String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		if (Boolean.TRUE.equals(Boolean.parseBoolean(apiSerivce.postStringDataWithToken(uri, token)))) {
			if (postService.savePost(postRequest)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
