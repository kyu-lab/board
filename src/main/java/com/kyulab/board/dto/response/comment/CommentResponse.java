package com.kyulab.board.dto.response.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class CommentResponse {
	private long id;

	private long userId;

	private String userName;

	private String content;

	private LocalDateTime modifiedDate;
}
