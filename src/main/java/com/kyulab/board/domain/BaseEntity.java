package com.kyulab.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {

	@Id
	@GeneratedValue(generator = "snowflake-id-gen")
	@GenericGenerator(
		name = "snowflake-id-gen",
		strategy = "com.kyulab.board.util.SnowflakeIdGen"
	)
	private long id;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
