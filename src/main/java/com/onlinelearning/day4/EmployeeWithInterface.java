package com.onlinelearning.day4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeWithInterface implements InitializingBean, DisposableBean {

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
	 *init() called before afterPropertiesSet() internally by IOC Container
	 *We can remore this part but need to remove init-method="init" from all the beans in this case

	 */
	
	public void init() {
		System.out.println("Testing init()");
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {

		System.out.println("Initializing bean");

		if (name == null) {
			System.out.println("Using default name");
			name = DEFAULT_NAME;
		}

		if (age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("You must set the age property of any beans of type " + EmployeeWithInterface.class);
		}

	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("Called before destruction");
		
	}

	public String toString() {
		return "\tName: " + name + "\n\tAge: " + age;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context2.xml");
		EmployeeWithInterface emp1 = context.getBean("empOne", EmployeeWithInterface.class);
		System.out.println(emp1);
		
		EmployeeWithInterface emp2 = context.getBean("empTwo", EmployeeWithInterface.class);
		System.out.println(emp2);
		
		/*
		 * EmployeeWithInterface emp3 = context.getBean("empThree",
		 * EmployeeWithInterface.class); System.out.println(emp3);
		 */
		
		((AbstractApplicationContext) context).close();
		
	}

}
