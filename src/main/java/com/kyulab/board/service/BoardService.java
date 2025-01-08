package com.kyulab.board.service;

import com.kyulab.board.domain.Board;
import com.kyulab.board.repository.BoardRepository;
import com.kyulab.grpc.user.UserExistsRequest;
import com.kyulab.grpc.user.UserExistsResponse;
import com.kyulab.grpc.user.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@GrpcService
@Service
@RequiredArgsConstructor
public class BoardService extends UserServiceGrpc.UserServiceImplBase {

	private final BoardRepository boardRepository;

	@GrpcClient("user-service")
	private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

	public Mono<Board> getBoard(int boardId) {
		return boardRepository.findById(boardId);
	}

	public Flux<Board> getBoards() {
		return boardRepository.findAll();
	}

	public Mono<Boolean> existsByBoardId(int boardId) {
		return boardRepository.existsById(boardId);
	}

	public Mono<Void> saveBoard(Board board) {
		return boardRepository.save(board).then();
	}

	/*
	public Mono<Boolean> userExists(int userId) {
		return Mono.fromCallable(() -> {
			UserExistsRequest request = UserExistsRequest.newBuilder()
					.setUserId(userId)
					.build();

			UserExistsResponse response = userServiceBlockingStub.userExists(request);
			return response.getExists();
		});
	}
	*/

}
