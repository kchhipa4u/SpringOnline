package com.teachalesson.xmlway.constructordependentinjection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("resources/ciWithDpObjApplicationContext.xml");
		Employee employee = (Employee)context.getBean("emp1");
		employee.show();
		
		

	}

}