package com.onlinelearning.day3;

public class OrderServiceImpl1 implements OrderService {

	@Override
	public String getOrderDetails(String orderId) {
		return "Order details for impl 1, for order id= " + orderId;
	}

}
