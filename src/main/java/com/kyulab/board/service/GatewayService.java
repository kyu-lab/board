package com.kyulab.board.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class GatewayService {

	private final RestTemplate restTemplate;

	@Bean
	public ApiSerivce getApiSevice() {
		return new ApiSerivce(restTemplate);
	}

	@RequiredArgsConstructor
	public static class ApiSerivce {

		private static final String TOKEN_PREFIX = "bearer ";
		private final RestTemplate REST_TEMPLATE;

		public String getStringData(String uri) {
			String url = "http://localhost:8000" + uri;
			ResponseEntity<String> response = REST_TEMPLATE.getForEntity(url, String.class);
			return response.getBody();
		}

		public String postStringDataWithToken(String uri, String token) {
			String url = "http://localhost:8000" + uri;
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token);
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			ResponseEntity<String> response = REST_TEMPLATE.exchange(url, HttpMethod.POST, httpEntity, String.class);
			return response.getBody();
		}
	}

}
