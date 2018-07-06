package com.microservices.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.model.UserDetails;
import com.microservices.user.service.UserRegistrationService;

@RestController
public class UserRegistrationController {

	@Autowired
	UserRegistrationService registrationService;
	
	@PostMapping("registerUser.htm")
	public String registerUser(@RequestBody UserDetails userDetails) { 
			
		 registrationService.saveUserDetail(userDetails);
		
		return "sucess";
	}
	
	
}
