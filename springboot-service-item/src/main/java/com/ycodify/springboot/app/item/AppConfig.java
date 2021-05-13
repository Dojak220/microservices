package com.ycodify.springboot.app.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean("clientRest")
	public RestTemplate setRestTemplate() {
		return new RestTemplate();
	}
}
