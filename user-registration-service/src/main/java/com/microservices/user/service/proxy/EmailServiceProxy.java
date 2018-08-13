package com.microservices.user.service.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * Use this code in case you are using only feign client not ribbon.
 * 
 * @FeignClient(name="email-service",url="localhost:8089")
//@RibbonClient()
public interface EmailServiceProxy {

	@PostMapping("sendUserRegEmail.htm")
	public boolean sendUserEmail(@RequestBody String userJson);
	
	
}
 Use below code when you are using feign client with ribbon for load balancing. 
*/

@FeignClient(name="netflix-cloud-zuul-server") //adding api gateway server for routing request through api getway to email service 
@RibbonClient(name="email-service")
public interface EmailServiceProxy {

	//@PostMapping("sendUserRegEmail.htm")
	@PostMapping("email-service/sendUserRegEmail.htm") //prepending service name (email-service) when talking though api gateway
	public boolean sendUserEmail(@RequestBody String userJson);
	
	
}
