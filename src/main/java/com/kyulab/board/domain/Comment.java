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
@Table("comment")
@Builder
@ToString
public class Comment {

	@Id
	private int commentId;

	private int postId;

	private long commentAuthorId;

	private String commentAuthorName;

	private String commentConent;

	@CreatedDate
	private LocalDateTime createDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
