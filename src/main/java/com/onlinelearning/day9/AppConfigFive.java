package com.onlinelearning.day9;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.onlinelearning.day4.Employee;

@Configuration
@ImportResource(value="classpath:app-context.xml")
public class AppConfigFive {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigFive.class);
		Employee bean = context.getBean("empOne", Employee.class);
		System.out.println(bean);
	}
}