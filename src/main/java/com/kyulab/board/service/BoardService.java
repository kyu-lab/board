package com.kyulab.board.service;

import com.kyulab.HelloReply;
import com.kyulab.HelloRequest;
import com.kyulab.MyServiceGrpc;
import com.kyulab.board.dto.Board;
import com.kyulab.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@GrpcService
@Service
@RequiredArgsConstructor
public class BoardService extends MyServiceGrpc.MyServiceImplBase {

	private final BoardRepository boardRepository;
	@GrpcClient("user-service")
	private MyServiceGrpc.MyServiceBlockingStub stub;

	public Optional<Board> readBoard(int boardId) {
		return boardRepository.findById(boardId);
	}

	public void saveBoard(Board board) {
		boardRepository.save(board);
	}

	public String getUserResponse(String name) {
		HelloRequest request = HelloRequest.newBuilder().setName(name).build();
		HelloReply response = stub.sayHello(request);
		return response.getMessage();
	}
}
