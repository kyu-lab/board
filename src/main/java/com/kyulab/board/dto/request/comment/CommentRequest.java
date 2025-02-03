package com.kyulab.board.dto.request.comment;

import lombok.Getter;
import lombok.ToString;

/**
 * 댓글 작성 요청시
 */
@Getter
@ToString
public class CommentRequest {

	private long postId;

	private long userId;

	private String userName;

	private String content;

}
