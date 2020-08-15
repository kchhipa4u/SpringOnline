package com.onlinelearning.day11.REST;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRestController1 {
	
	@PostMapping("/head")
	public String readHead(@RequestHeader(required = false) String dept,

			@RequestHeader("Content-Type") String type, @RequestBody String mydata) {

		return "Hello Head :" + dept + " ," + type + ",Body:" + mydata;

	}

}