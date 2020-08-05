package com.onlinelearning.day8.client1;

import java.util.ArrayList;
import java.util.List;

import com.onlinelearning.day8.Product;
import com.onlinelearning.day8.ProductProviderService;

public class ProductProviderServiceImpl  implements ProductProviderService{

	@Override
	public List<Product> provideProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("P1"));
		products.add(new Product("P2"));
		products.add(new Product("P3"));
		return products;
	}
	
}
