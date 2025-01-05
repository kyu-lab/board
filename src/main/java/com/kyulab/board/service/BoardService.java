package com.kyulab.board.service;

import com.kyulab.board.dto.Board;
import com.kyulab.board.repository.BoardRepository;
import com.kyulab.grpc.user.UserExistsRequest;
import com.kyulab.grpc.user.UserExistsResponse;
import com.kyulab.grpc.user.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@GrpcService
@Service
@RequiredArgsConstructor
public class BoardService extends UserServiceGrpc.UserServiceImplBase {

	private final BoardRepository boardRepository;

	@GrpcClient("user-service")
	private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

	public Optional<Board> readBoard(int boardId) {
		return boardRepository.findById(boardId);
	}

	public void saveBoard(Board board) {
		boardRepository.save(board);
	}

	public Mono<Boolean> userExists(int userId) {
		return Mono.fromCallable(() -> {
			UserExistsRequest request = UserExistsRequest.newBuilder()
					.setUserId(userId)
					.build();

			UserExistsResponse response = userServiceBlockingStub.userExists(request);
			return response.getExists();
		});
	}

}
