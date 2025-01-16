package com.kyulab.board.dto.request.post;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostRequest {

	private long boardId;

	private long userId;

	private String userName;

	private String subject;

	private String content;

}
