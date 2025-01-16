package com.kyulab.board.dto.response.post;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
public class PostResponse {
	private String userName;

	private String subject;

	private String content;

	private LocalDateTime modifiedDate;
}
