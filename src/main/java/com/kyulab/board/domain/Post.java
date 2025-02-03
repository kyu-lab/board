package com.kyulab.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Post extends BaseEntity {

	@Column(nullable = false)
	private long userId;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String subject;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

}
