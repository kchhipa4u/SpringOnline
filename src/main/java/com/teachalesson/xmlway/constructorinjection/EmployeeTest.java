package com.teachalesson.xmlway.constructorinjection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
		Employee employee = (Employee)context.getBean("e1");
		employee.show();
		
		employee = (Employee)context.getBean("e2");
		employee.show();
		
		employee = (Employee)context.getBean("e3");
		employee.show();
		
		employee = (Employee)context.getBean("e4");
		employee.show();
		
		employee = (Employee)context.getBean("e5");
		employee.show();
		
		employee = (Employee)context.getBean("e6");
		employee.show();

	}

}