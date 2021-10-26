package com.eventtickets.logictier;

import com.eventtickets.logictier.network.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LogicTierApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogicTierApplication.class, args);
	}

	@Bean
	public RestTemplate provideRestTemplate() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}



}
