package com.auth.service.service;

import com.auth.service.dao.UserRepository;
import com.auth.service.dto.UserDetailsDTO;
import com.auth.service.factory.OAuth2UserFactory;
import com.auth.service.model.OAuth2UserInfo;
import com.auth.service.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OAuth2UserServiceImpl  extends DefaultOAuth2UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	UserDetailsServiceImpl userService;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("Fetching profile details from facebook");
	 	OAuth2UserInfo oauth2UserInfo = OAuth2UserFactory.getOAuth2User(userRequest);
	    return 	processUser(oauth2UserInfo);
	}
	
	private UserDetailsDTO processUser(OAuth2UserInfo userInfo) {
		UserDetailsDTO userDetailsDTO=  userRepo.findByUserName(userInfo.getEmail());
		if(userDetailsDTO != null) {
			// checking if user has logged in with different provider
			userDetailsDTO.setFirstName(userInfo.getFirstName());
			userDetailsDTO.setLastName(userInfo.getLastName());
			userRepo.save(userDetailsDTO);
		}  else {
			RegisterUser registerUser = new RegisterUser();
			registerUser.setEmailId(userInfo.getEmail());
			registerUser.setFirstName(userInfo.getFirstName());
			registerUser.setLastName(userInfo.getLastName());
			registerUser.setProfileImgUrl(userInfo.getProfileUrl());
			log.info("Registering User into database");
			System.out.println(userService);
			userService.saveUser(registerUser, userInfo.getProvider());
			userDetailsDTO =  userRepo.findByUserName(userInfo.getEmail());
		}
		return userDetailsDTO;
	}
	
}
