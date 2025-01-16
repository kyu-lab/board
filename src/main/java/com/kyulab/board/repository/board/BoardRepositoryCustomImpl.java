package com.kyulab.board.repository.board;

import com.kyulab.board.domain.Board;
import com.kyulab.board.domain.QBoard;
import com.kyulab.board.domain.QPost;
import com.kyulab.board.dto.response.board.BoardResponse;
import com.kyulab.board.dto.response.post.PostListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport
		implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public BoardRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		super(Board.class);
		this.queryFactory = queryFactory;
	}

	public BoardResponse getBoardDetail(long boardId) {
		QBoard board = QBoard.board;
		QPost post = QPost.post;

		return queryFactory
				.select(Projections.constructor(
						BoardResponse.class,
						board.id,
						board.name,
						Projections.list(
							Projections.constructor(
								PostListResponse.class,
								post.userName,
								post.subject,
								post.content,
								post.modifiedDate
							)
						)
				))
				.from(board)
				.leftJoin(post).on(post.board.id.eq(board.id))
				.where(board.id.eq(boardId))
				.fetchOne();
	}
}
