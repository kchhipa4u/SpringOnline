package com.onlinelearning.day8.client2;

import java.util.ArrayList;
import java.util.List;

import com.onlinelearning.day8.Product;
import com.onlinelearning.day8.ProductProviderService;

public class ProductProviderServiceImpl implements ProductProviderService {

	@Override
	public List<Product> provideProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("P4"));
		products.add(new Product("P5"));
		products.add(new Product("P6"));
		return products;
	}
}
