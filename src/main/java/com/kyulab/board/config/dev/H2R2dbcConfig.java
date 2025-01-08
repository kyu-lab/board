package com.kyulab.board.config.dev;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@Profile("local")
@RequiredArgsConstructor
@EnableR2dbcRepositories
@EnableR2dbcAuditing
public class H2R2dbcConfig extends AbstractR2dbcConfiguration {

	private Server h2ServerWeb;

	@EventListener(ContextRefreshedEvent.class)
	public void start() throws java.sql.SQLException {
		this.h2ServerWeb = org.h2.tools.Server.createWebServer("-webPort", "8092", "-tcpAllowOthers").start();
	}

	@EventListener(ContextClosedEvent.class)
	public void stop() {
		this.h2ServerWeb.stop();
	}

	@Override
	public ConnectionFactory connectionFactory() {
		return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
				.file("D:/kyu-lab/h2/boarddb")
				.username("sa")
				.build());
	}

}
