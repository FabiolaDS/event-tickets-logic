package com.eventtickets.logictier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LogicTierApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogicTierApplication.class, args);
	}

	@Bean
	public RestTemplate provideRestTemplate() {
		return new RestTemplate();
	}
}
