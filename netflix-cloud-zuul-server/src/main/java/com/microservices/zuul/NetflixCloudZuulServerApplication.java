package com.microservices.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class NetflixCloudZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloudZuulServerApplication.class, args);
		
	}
	
	/*
	@Bean
	public Tracing tracing() { 
		return Tracing.newBuilder().localServiceName("zuulServerProducer").build();
	}
	
	
	@Bean 
	public SpringRabbitTracing springRabbitTracing() { 
		return SpringRabbitTracing.newBuilder(tracing()).remoteServiceName("my-mq-service").build();
	}
	
	*/

}
