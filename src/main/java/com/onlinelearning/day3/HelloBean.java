package com.onlinelearning.day3;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HelloBean {
	static {
		System.out.println("Bean Loading.....");
	}

	public HelloBean() {
		System.out.println("Bean Created....");
	}

	public String sayHello() {
		return "Hello User";
	}
	
	public static void main(String[] args) {
		Resource resource = new 
				ClassPathResource("applicationContext.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);
				HelloBean bean = (HelloBean)factory.getBean("helloBean1");
				System.out.println("Using BeanFactory: " + bean.sayHello());
				
	// using ApplicationContext
				ApplicationContext context = new 
						ClassPathXmlApplicationContext("applicationContext.xml");
						HelloBean bean1 = (HelloBean)context.getBean("helloBean");
						System.out.println("Using ApplicationContext: " + bean.sayHello());
						
			System.out.println(context.getBean("helloBean"));
			System.out.println(context.getBean("helloBean"));
			System.out.println(context.getBean("helloBean2"));
			System.out.println(context.getBean("helloBean2"));
	}
}
