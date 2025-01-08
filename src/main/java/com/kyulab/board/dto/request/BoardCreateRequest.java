package com.kyulab.board.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardCreateRequest {

	private long requestUserId;

	private String boardOwner;

	private String boardName;

}
