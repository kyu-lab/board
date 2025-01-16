package com.kyulab.board.dto.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostListResponse {
	private String userName;

	private String subject;

	private String content;

	private LocalDateTime lastModifiedDate;
}
