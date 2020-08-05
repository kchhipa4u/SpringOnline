package com.teachalesson.day1;

import org.springframework.beans.factory.annotation.Autowired;

public class DisplayServiceClient {

	@Autowired
	private DisplayService displayService;
	
	public void displayMessage() {
		displayService.display("This is my First Spring-boot Project");
	}
}
