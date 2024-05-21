package com.employee.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfig {

	@Value("${addressservice.base.url}")
	private String addressUrl;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	public WebClient webCliet() {
		return WebClient.builder().baseUrl(addressUrl).build();
	}

}
