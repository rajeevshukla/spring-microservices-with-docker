package com.email.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SendUserEmailRestController {


	private static final Logger log = LoggerFactory.getLogger(SendUserEmailRestController.class);
	
	@PostMapping("sendUserRegEmail.htm")
	public boolean sendUserEmail(@RequestBody String userJson) {
		boolean status = false;
		log.info("Inside send user Reg Email===========");
		log.info("Request Recieved to send email:(Rest Contoller or Fiegn client)" + userJson);
		return status;
	}
	
	
}
