package com.email.service.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello.html")
	public String hello() { 
		return "Hello";
	}
}
