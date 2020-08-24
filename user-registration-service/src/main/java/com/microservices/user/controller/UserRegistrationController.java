package com.microservices.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.model.UserDetails;
import com.microservices.user.service.UserRegistrationService;
import com.microservices.user.service.proxy.EmailServiceProxy;

@RestController
@RefreshScope
public class UserRegistrationController {

	
	
	private static final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);

	
	@Autowired
	UserRegistrationService registrationService;

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	EmailServiceProxy emailServiceProxy;
	
	
	@Value("${user.property.value:This is default value from the controller}")
	String userPropertyValue;
	
	
	@PostMapping("registerUser.htm")
	public String registerUser(@RequestBody UserDetails userDetails) { 
			
		 log.info("Request received to save User:"+userDetails);
		 registrationService.saveUserDetail(userDetails);
		 		
		 rabbitTemplate.convertAndSend("user-registrations","user.created", userDetails);
		 
		 return "success";
	}
	
	@GetMapping("registerUser.htm")
	public String registerUser() {  
		return "Its working... Please use";
	}
	
	@GetMapping("/")
	public String home() {  
		return "Welcome to home page.<br/> User registerUser.htm with post for posting data";
	}
	
	@GetMapping("executeFeignClient.htm")
	public String executeFeignClient() {
		log.info("Executing Feign client");
		emailServiceProxy.sendUserEmail("executingFeignClient");
		return "success";
	}
	
	
	
	@GetMapping("/getProperty.htm") 
	public String fetchProperty() {
		return userPropertyValue;
	}
  	
	
}
