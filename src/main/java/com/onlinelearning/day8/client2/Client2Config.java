package com.onlinelearning.day8.client2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.onlinelearning.day8.ProductProviderService;

/*
 * Naming a Profile as "default" , has special meanings, i.e. if we don't activate any profile during startup, 
 * the "default" profile will be loaded along with the beans which don't have any profile associations. If we activate other profile, 
 * "default" will not be loaded.
 * 
 * @Profile("default")
 * 
 * We can activate profiles either by using system property (using a spring specific key spring.profiles.active and comma separated profile names) 
 * or by springContext#getEnvironment().setActiveProfiles("profile1", "profile2"...);

   In a servlet based application we have to add this context-param in web.xml
		<context-param>
		    <param-name>spring.profiles.active</param-name>
		    <param-value>profile1</param-value>
		</context-param>
 */

@Configuration
@Profile("client2")
public class Client2Config {

	@Bean
	public ProductProviderService productProviderService() {
		return new ProductProviderServiceImpl();
	}
}
