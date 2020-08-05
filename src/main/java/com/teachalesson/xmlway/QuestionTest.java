package com.teachalesson.xmlway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuestionTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("ciWithListCollectionApplicationContext.xml");
		Question q = (Question)context.getBean("q");
		q.displayInfo();
		
		Question2 q2 = (Question2)context.getBean("q2");
		q2.displayInfo();

	}

}