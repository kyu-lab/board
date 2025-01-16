package com.kyulab.board.repository.comment;

import com.kyulab.board.dto.response.comment.CommentResponse;

import java.util.List;

/**
 * Comment와 관련된 query dsl 구현대상 정의
 */
public interface CommentRepositoryCustom {

	List<CommentResponse> findAllCommentsByPostId(long postId);

}
