package com.onlinelearning.day8.client2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.onlinelearning.day8.ProductProviderService;

@Configuration
@Profile("client2")
public class Client2Config {

	@Bean
	public ProductProviderService productProviderService() {
		return new ProductProviderServiceImpl();
	}
}
