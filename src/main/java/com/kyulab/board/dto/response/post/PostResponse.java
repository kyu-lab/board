package com.kyulab.board.dto.response.post;

import com.kyulab.board.dto.response.comment.CommentResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class PostResponse {
	private long userId;
	private String userName;
	private String subject;
	private String content;
	private LocalDateTime modifiedDate;
	private List<CommentResponse> commentList;

	@Builder
	public PostResponse(long userId, String userName, String subject, String content, LocalDateTime modifiedDate, List<CommentResponse> commentList) {
		this.userId = userId;
		this.userName = userName;
		this.subject = subject;
		this.content = content;
		this.modifiedDate = modifiedDate;
		this.commentList = commentList;
	}

	public PostResponse(long userId, String userName, String subject, String content, LocalDateTime modifiedDate) {
		this.userId = userId;
		this.userName = userName;
		this.subject = subject;
		this.content = content;
		this.modifiedDate = modifiedDate;
	}
}
