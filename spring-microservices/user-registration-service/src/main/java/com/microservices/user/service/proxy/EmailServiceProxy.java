package com.microservices.user.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.user.model.UserDetails;

@FeignClient(name="email-service",url="localhost:8089")
public interface EmailServiceProxy {

	@PostMapping("sendUserRegEmail.htm")
	public boolean sendUserEmail(@RequestBody UserDetails userDetails);
	
	
}
