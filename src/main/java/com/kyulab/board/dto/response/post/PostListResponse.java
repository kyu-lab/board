package com.kyulab.board.dto.response.post;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class PostListResponse {
	private long id;

	private long userId;

	private String userName;

	private String subject;

	private LocalDateTime lastModifiedDate;
}
