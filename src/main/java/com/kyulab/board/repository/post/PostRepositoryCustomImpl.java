package com.kyulab.board.repository.post;

import com.kyulab.board.domain.Post;
import com.kyulab.board.domain.QComment;
import com.kyulab.board.domain.QPost;
import com.kyulab.board.dto.response.comment.CommentResponse;
import com.kyulab.board.dto.response.post.PostResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryCustomImpl extends QuerydslRepositorySupport
		implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public PostRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		super(Post.class);
		this.queryFactory = queryFactory;
	}

	@Override
	public PostResponse findPostAndCommentById(long postId) {
		QComment comment = QComment.comment;
		QPost post = QPost.post;

		// Post 데이터 가져오기
		PostResponse postResponse = queryFactory
				.select(Projections.constructor(
						PostResponse.class,
						post.userId,
						post.userName,
						post.subject,
						post.content,
						post.modifiedDate
				))
				.from(post)
				.where(post.id.eq(postId))
				.fetchOne();

		if (postResponse == null) {
			throw new IllegalArgumentException("Post not found for id: " + postId);
		}

		// Comment 데이터 가져오기
		List<CommentResponse> comments = queryFactory
				.select(Projections.constructor(
						CommentResponse.class,
						comment.id,
						comment.userId,
						comment.userName,
						comment.content,
						comment.modifiedDate
				))
				.from(comment)
				.where(comment.post.id.eq(postId)) // 경로 명확히
				.fetch();

		return PostResponse.builder()
				.userId(postResponse.getUserId())
				.userName(postResponse.getUserName())
				.subject(postResponse.getSubject())
				.content(postResponse.getContent())
				.modifiedDate(postResponse.getModifiedDate())
				.commentList(comments)
				.build();
	}
}
