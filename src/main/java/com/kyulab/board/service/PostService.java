package com.kyulab.board.service;

import com.kyulab.board.domain.Board;
import com.kyulab.board.domain.Post;
import com.kyulab.board.dto.request.post.PostRequest;
import com.kyulab.board.dto.response.post.PostResponse;
import com.kyulab.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final BoardService boardService;

	public Post findById(long id) {
		return postRepository.findById(id).orElse(null);
	}

	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	public boolean existsById(long id) {
		return postRepository.existsById(id);
	}

	public PostResponse getPostResponse(long id) {
		Post post = findById(id);
		if (Objects.isNull(post)) {
			return PostResponse.builder().build();
		}
		return PostResponse.builder()
				.userName(post.getUserName())
				.subject(post.getSubject())
				.content(post.getContent())
				.modifiedDate(post.getModifiedDate())
				.build();
	}


	public boolean savePost(PostRequest postRequest) {
		Optional<Board> targetBoard = boardService.findById(postRequest.getBoardId());
		if (targetBoard.isEmpty()) {
			return false;
		}
		Post newPost = Post.builder()
				.userId(postRequest.getUserId())
				.userName(postRequest.getUserName())
				.subject(postRequest.getSubject())
				.content(postRequest.getContent())
				.board(targetBoard.get())
				.build();
		postRepository.save(newPost);
		return true;
	}

}
