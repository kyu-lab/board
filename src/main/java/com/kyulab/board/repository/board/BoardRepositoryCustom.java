package com.kyulab.board.repository.board;

import com.kyulab.board.dto.response.post.PostListResponse;

import java.util.List;

/**
 * Board와 관련된 query dsl 구현대상 정의
 */
public interface BoardRepositoryCustom {

	List<PostListResponse> findPostByBoardId(long boardId);

}
