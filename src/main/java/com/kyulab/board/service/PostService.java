package com.kyulab.board.service;

import com.kyulab.board.domain.Post;
import com.kyulab.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final BoardService boardService;

	public Mono<Post> getPost(int postID) {
		return postRepository.findById(postID);
	}

	public Flux<Post> getPosts() {
		return postRepository.findAll();
	}

	public Mono<Boolean> existsByPostId(int postId) {
		return postRepository.existsById(postId);
	}

	public Mono<Void> savePost(Post post) {
		return boardService.existsByBoardId(post.getBoardId())
				.flatMap(exists -> {
					if (exists) {
						return postRepository.save(post).then();
					} else {
						return Mono.error(new Exception("게시글 저장 실패"));
					}
				});
	}

}
