package com.kyulab.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {

	// 로컬 게이트웨이
	private static final WebClient localWebClient = WebClient.builder()
			.baseUrl("http://localhost:8000").build();

	public static WebClient getLocalWebClient() {
		return localWebClient;
	}

}
