package com.cloudtern.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker //Enabling the Hystrix for fault tolerance
public class MovieCatalogServiceApplication {

	@Bean
	@LoadBalanced //It is doing client side service discovery as well as load balancing
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
/*	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
