package com.email.service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendUserEmailRestController {

	
	@PostMapping("sendUserRegEmail.htm")
	public boolean sendUserEmail(@RequestBody String userJson) {
		boolean status = false;
		System.out.println("Request Recieved to send email:(Rest Contoller or Fiegn client)" + userJson);
		return status;
	}
	
	
}
