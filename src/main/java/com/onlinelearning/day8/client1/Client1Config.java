package com.onlinelearning.day8.client1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.onlinelearning.day8.ProductProviderService;

@Configuration
@Profile("client1")
public class Client1Config {

	@Bean
	public ProductProviderService productProviderService() {
		return new ProductProviderServiceImpl();
	}
}
