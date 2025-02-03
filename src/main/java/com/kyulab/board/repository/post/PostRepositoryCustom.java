package com.kyulab.board.repository.post;

import com.kyulab.board.dto.response.post.PostResponse;

public interface PostRepositoryCustom {

	PostResponse findPostAndCommentById(long id);

}
