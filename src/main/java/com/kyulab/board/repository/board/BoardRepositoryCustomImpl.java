package com.kyulab.board.repository.board;

import com.kyulab.board.domain.Board;
import com.kyulab.board.domain.QPost;
import com.kyulab.board.dto.response.post.PostListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport
		implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public BoardRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		super(Board.class);
		this.queryFactory = queryFactory;
	}

	public List<PostListResponse> findPostByBoardId(long boardId) {
		QPost post = QPost.post;
		return queryFactory
				.select(Projections.constructor(
						PostListResponse.class,
						post.id,
						post.userId,
						post.userName,
						post.subject,
						post.modifiedDate
				))
				.from(post)
				.where(post.board.id.eq(boardId))
				.fetch();
	}
}
