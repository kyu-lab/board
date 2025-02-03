package com.kyulab.board.dto.response.board;

import com.kyulab.board.dto.response.post.PostListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {
	private long userId;

	private String name;

	List<PostListResponse> postList;
}
