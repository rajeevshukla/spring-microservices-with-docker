package com.microservices.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.user.dao.UserRepository;
import com.microservices.user.exception.UserRegistrationException;
import com.microservices.user.model.UserDetails;

@Service
public class UserRegistrationService {

	
	@Autowired
	UserRepository repository;
	
	public boolean saveUserDetail(UserDetails userDetails) throws UserRegistrationException {
		
		repository.save(userDetails);
		return true;
	}
	
}
