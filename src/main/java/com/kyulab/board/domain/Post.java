package com.kyulab.board.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Table("post")
@Builder
@ToString
public class Post {

	@Id
	private int postId;

	private int boardId;

	private long authorId;

	private String authorName;

	private String postSubject;

	private String postContent;

	@CreatedDate
	private LocalDateTime createDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
