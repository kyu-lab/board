package com.kyulab.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table
@ToString
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity {

	@Column(nullable = false)
	private long userId;

	@Column(nullable = false)
	private String name;

	@Builder
	public Board(long userId, String name) {
		this.userId = userId;
		this.name = name;
	}

}
