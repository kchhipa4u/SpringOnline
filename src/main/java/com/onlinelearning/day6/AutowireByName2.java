package com.onlinelearning.day6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

interface Address {
	public void displayAddress();
}

class HomeAddress implements Address {

	@Override
	public void displayAddress() {
		System.out.println("This is my Home Address");
	}

}

class Employee {

	@Autowired
	@Qualifier("myoofice")
	private Address myAddr;

	public void getEmpAddress() {
		myAddr.displayAddress();
	}

}

@Configuration
class MyConfig {

	@Bean
	public Employee employeeBean() {
		return new Employee();
	}

	@Bean
	public Address homeAddressBean() {
		return new HomeAddress();
	}

	@Bean (name = "myoofice")
	public Address officeAddressBean() {
		return new OfficeAddress();
	}

}

class OfficeAddress implements Address {

	@Override
	public void displayAddress() {
		System.out.println("This is my Office Address");
	}

}

public class AutowireByName2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		Employee bean = context.getBean(Employee.class);

		bean.getEmpAddress();
	}

}
