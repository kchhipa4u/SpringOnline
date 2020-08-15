package com.onlinelearning.day11.REST;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin") // Optional
public class AdminRestController {
	@GetMapping("/show")
	public String helloMsgGet() {
		return "Hello From GET";
	}

	@PostMapping("/show")
	public String helloMsgPost() {
		return "Hello From POST";
	}

	@PutMapping("/show")
	public String helloMsgPut() {
		return "Hello From PUT";
	}

	@DeleteMapping("/show")
	public String helloMsgDelete() {
		return "Hello From DELETE";
	}

	@PatchMapping("/show")
	public String helloMsgPatch() {
		return "Hello From PATCH";
	}
}