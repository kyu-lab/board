package com.kyulab.board.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI apiDocDec() {
		return new OpenAPI()
				.info(new Info()
					.title("springdoc-openapi")
					.version("0.0.1")
					.description("여기는 board임"));
	}
	
}
