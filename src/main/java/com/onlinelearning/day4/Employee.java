package com.onlinelearning.day4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Employee {

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

	/*
	 * The only constraint on your initialization method 
        is that it cannot accept any arguments.
        
        You can define any return type, although it is ignored by Spring, and 
you can even use a static method, but the method must accept no arguments.

	 */
	public void init() {

		System.out.println("Initializing bean");

		if (name == null) {
			System.out.println("Using default name");
			name = DEFAULT_NAME;
		}

		if (age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("You must set the age property of any beans of type " + Employee.class);
		}

	}
	
	public void destroy() {
		System.out.println("destroy called");
	}

	public String toString() {
		return "\tName: " + name + "\n\tAge: " + age;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		Employee emp1 = context.getBean("empOne", Employee.class);
		System.out.println(emp1);
		
		Employee emp2 = context.getBean("empTwo", Employee.class);
		System.out.println(emp2);
		
		/*
		 * EmployeeWithInterface emp3 = context.getBean("empThree",
		 * EmployeeWithInterface.class); System.out.println(emp3);
		 */
		
		((AbstractApplicationContext) context).close();
		
	}

}
