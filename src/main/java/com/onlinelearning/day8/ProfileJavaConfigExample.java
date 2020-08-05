package com.onlinelearning.day8;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.onlinelearning.day8.client1.Client1Config;
import com.onlinelearning.day8.client2.Client2Config;

public class ProfileJavaConfigExample {

	public static void main(String... args) {

		GenericApplicationContext context = new AnnotationConfigApplicationContext(Client1Config.class,
				Client2Config.class);

		ProductProviderService providerService = context.getBean("productProviderService",
				ProductProviderService.class);
		
		List<Product> provideProducts = providerService.provideProducts();
		
		for (Product product : provideProducts) {
			System.out.println("Product: " + product.getName());
		}
		
		context.close();

	}
}
