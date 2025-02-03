package com.kyulab.board.controller;

import com.kyulab.board.dto.request.post.PostRequest;
import com.kyulab.board.dto.response.post.PostResponse;
import com.kyulab.board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@Tag(name = "게시글 API")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping(value = "/{postId}")
	@Operation(summary = "게시글을 가져온다.")
	public ResponseEntity<PostResponse> getPost(@PathVariable long postId) {
		return ResponseEntity.ok(postService.findPostAndCommentById(postId));
	}

	@PostMapping
	@Operation(summary = "게시글을 생성한다.")
	public ResponseEntity<String> savePost(@RequestBody PostRequest postRequest) {
		if (postService.savePost(postRequest)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
