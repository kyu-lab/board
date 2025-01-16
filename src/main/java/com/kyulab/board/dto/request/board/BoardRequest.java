package com.kyulab.board.dto.request.board;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardRequest {

	private long userId;

	private String userName;

	private String boardName;

}
