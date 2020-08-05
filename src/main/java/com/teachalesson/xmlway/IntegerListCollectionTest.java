package com.teachalesson.xmlway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IntegerListCollectionTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("resources/ciWithIntegerListCollection/ciWithIntegerListCollectionApplicationConext.xml");
		IntegerListCollection inteLiColl = (IntegerListCollection)context.getBean("inteLiColl");
		inteLiColl.displayInfo();
		
		
	}
}