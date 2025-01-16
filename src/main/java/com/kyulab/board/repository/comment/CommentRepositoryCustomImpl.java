package com.kyulab.board.repository.comment;

import com.kyulab.board.domain.Comment;
import com.kyulab.board.domain.QComment;
import com.kyulab.board.domain.QPost;
import com.kyulab.board.dto.response.comment.CommentResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryCustomImpl extends QuerydslRepositorySupport
		implements CommentRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public CommentRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		super(Comment.class);
		this.queryFactory = queryFactory;
	}

	@Override
	public List<CommentResponse> findAllCommentsByPostId(long postId) {
		QComment comment = QComment.comment;
		return queryFactory
				.select(Projections.constructor(
						CommentResponse.class,
						comment.userId,
						comment.userName,
						comment.content,
						comment.modifiedDate
				))
				.from(comment)
				.where(comment.post.id.eq(postId))
				.fetch();
	}
}
