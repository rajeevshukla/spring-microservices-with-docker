package com.microservices.user.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.model.UserDetails;
import com.microservices.user.service.UserRegistrationService;
import com.microservices.user.service.proxy.EmailServiceProxy;

@RestController
public class UserRegistrationController {

	@Autowired
	UserRegistrationService registrationService;

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	EmailServiceProxy emailServiceProxy;
	
	@PostMapping("registerUser.htm")
	public String registerUser(@RequestBody UserDetails userDetails) { 
			
		 registrationService.saveUserDetail(userDetails);
		
		 rabbitTemplate.convertAndSend("user-registrations","user.created", userDetails);
		 
		// calling rest service directly using ribbon. 
		boolean status = emailServiceProxy.sendUserEmail(userDetails);
		
		return "sucess="+status;
	}
	
	@GetMapping("registerUser.htm")
	public String registerUser() {  
		return "Its working... Please use";
	}
	
	@GetMapping("/")
	public String home() {  
		return "Welcome to home page.<br/> User registerUser.htm with post for posting data";
	}
	
}
