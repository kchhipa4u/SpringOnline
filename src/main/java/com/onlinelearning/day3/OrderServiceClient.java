package com.onlinelearning.day3;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceClient {

	// Field based DI, recommend, reflection
	//@Autowired
	private OrderService orderService;  // instance variable

	// constructor based DI, Constructor
	@Autowired  // become optional
	public OrderServiceClient(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void showPendingOrderDetails() {
		System.out.println(orderService.getOrderDetails("100"));
	}
}
