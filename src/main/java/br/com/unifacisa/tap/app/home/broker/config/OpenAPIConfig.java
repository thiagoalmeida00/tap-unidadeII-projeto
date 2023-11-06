package br.com.unifacisa.tap.app.home.broker.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPIServer() {
		Server server = new Server();
		server.setUrl("http://localhost:8080");
		server.setDescription("Sistema Home Broker");
		
		return new OpenAPI().servers(List.of(server));
	}
	
}
