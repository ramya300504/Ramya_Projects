package com.hexaware.cozyhaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CozyHavenSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CozyHavenSpringBootApplication.class, args);
	}

	@Bean
	public RestTemplate  getRestTemplate() {
		
		return new RestTemplate();
		
	}
}
