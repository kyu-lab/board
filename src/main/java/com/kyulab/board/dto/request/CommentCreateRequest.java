package com.kyulab.board.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentCreateRequest {

	private int postId;

	private long commentAuthorId;

	private String commentAuthorName;

	private String commentConent;

}
