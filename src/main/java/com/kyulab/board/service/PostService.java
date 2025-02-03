package com.kyulab.board.service;

import com.kyulab.board.domain.Board;
import com.kyulab.board.domain.Post;
import com.kyulab.board.dto.request.post.PostRequest;
import com.kyulab.board.dto.response.post.PostListResponse;
import com.kyulab.board.dto.response.post.PostResponse;
import com.kyulab.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true)
	public PostResponse findPostAndCommentById(long id) {
		return postRepository.findPostAndCommentById(id);
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
