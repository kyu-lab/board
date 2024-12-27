package com.kyulab.board.dto;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Table(name = "board")
@DynamicUpdate
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardId;

	@Column(name = "writerId", nullable = false)
	private int writerId;

	@Column(name = "writer", nullable = false, length = 25)
	private String writer;

	@Column(name = "subject", nullable = false, length = 50)
	private String subject;

	@Column(name = "content", nullable = false, length = 200)
	private String content;

}
