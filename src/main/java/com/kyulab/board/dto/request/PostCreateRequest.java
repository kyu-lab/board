package com.kyulab.board.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostCreateRequest {

	private int boardId;

	private long authorId;

	private String authorName;

	private String postSubject;

	private String postContent;

}
