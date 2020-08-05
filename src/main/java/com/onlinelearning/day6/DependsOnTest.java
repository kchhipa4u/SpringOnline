package com.onlinelearning.day6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

class FirstBean{
	
}

class SecondBean{
	
}

public class DependsOnTest {

	@Configuration
	public static class Config {
		
		@Bean(name = "first")
		@DependsOn("second")
		public FirstBean bean1() {
			return new FirstBean();
		}
		
		@Bean(name = "second")
		public SecondBean bean2() {
			return new SecondBean();
		}
		
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
	}
}
