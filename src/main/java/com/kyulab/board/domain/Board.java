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
@Table("board")
@Builder
@ToString
public class Board {

	@Id
	private int boardId;

	private long creatorId;

	private String boardName;

	@CreatedDate
	private LocalDateTime createDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
