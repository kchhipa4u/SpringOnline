package com.onlinelearning.day3;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorBasedDIUsingXML {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderServiceClient bean = context.getBean(OrderServiceClient.class);
		
		// Beans can be lookup as a map from container where map key is a bean id:
		Map<String, Employee> billingService = context.getBeansOfType(Employee.class);
		System.out.println(billingService);

		bean.showPendingOrderDetails();
		
		Employee empBean = (Employee) context.getBean("employee");
		System.out.println(empBean.getEmployeeInfo());
		
		
		Employee bean2 = (Employee) context.getBean("employee2");
		System.out.println(bean2.getEmployeeInfo());
	}
}
