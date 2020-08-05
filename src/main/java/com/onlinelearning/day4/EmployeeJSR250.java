package com.onlinelearning.day4;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeJSR250 {

	private static final String DEFAULT_NAME = "Binu";

	private String name;

	private int age = Integer.MIN_VALUE;

	public void setName(String name) {
		System.out.println("Setter called before init()");
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	@PostConstruct
	public void init() {

		System.out.println("Initializing bean");

		if (name == null) {
			System.out.println("Using default name");
			name = DEFAULT_NAME;
		}

		if (age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("You must set the age property of any beans of type " + EmployeeJSR250.class);
		}

	}
	
	@PreDestroy
	public void destroy() {
        System.out.println("@PreDestroy called....");
	}

	public String toString() {
		return "\tName: " + name + "\n\tAge: " + age;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context3.xml");
		EmployeeJSR250 emp1 = context.getBean("empOne", EmployeeJSR250.class);
		System.out.println(emp1);
		
		EmployeeJSR250 emp2 = context.getBean("empTwo", EmployeeJSR250.class);
		System.out.println(emp2);
		
		/*
		 * EmployeeWithInterface emp3 = context.getBean("empThree",
		 * EmployeeWithInterface.class); System.out.println(emp3);
		 */
		
		((AbstractApplicationContext) context).close();
		
	}

}
