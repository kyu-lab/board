package com.kyulab.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardKafkaService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMsg(String topic, String msg) {
		kafkaTemplate.send(topic, msg);
	}

}
