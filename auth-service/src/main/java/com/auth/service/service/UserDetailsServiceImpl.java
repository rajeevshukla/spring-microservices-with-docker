package com.auth.service.service;

import java.util.HashSet;

import com.auth.service.dao.UserRepository;
import com.auth.service.dto.RoleDetailsDTO;
import com.auth.service.dto.UserDetailsDTO;
import com.auth.service.exception.UserAlreadyExistsException;
import com.auth.service.model.AuthProvider;
import com.auth.service.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;




	@Override
	public UserDetailsDTO loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsDTO userDetails = userRepository.findByUserName(username);

		if (userDetails == null) {
			throw new UsernameNotFoundException("User:" + username + " does not exist");
		}
		return userDetails;
	}

	public UserDetailsDTO saveUser(RegisterUser user, AuthProvider provider) {
		System.out.println("inside user service");
		UserDetails userDetails = userRepository.findByUserName(user.getEmailId());
		System.out.println(userDetails);
		if (userDetails != null) {
			throw new UserAlreadyExistsException("EmailId: " + user.getEmailId() + " already registered.");
		}
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername(user.getEmailId());
		userDetailsDTO.setFirstName(user.getFirstName());
		userDetailsDTO.setLastName(user.getLastName());
		if(AuthProvider.LOCAL.equals(provider)) { 
			userDetailsDTO.setPassword(passwordEncoder.encode(user.getPassword()));
			userDetailsDTO.setEnabled(false);
		} else {
			userDetailsDTO.setEnabled(true);
		}
		userDetailsDTO.setProvider(provider);
		userDetailsDTO.setProfileImgURL(user.getProfileImgUrl());
		RoleDetailsDTO roleDetailsDTO = new RoleDetailsDTO();
		roleDetailsDTO.setRoleId("ROLE_USER");
		HashSet<RoleDetailsDTO> authorities = new HashSet<>();
		authorities.add(roleDetailsDTO);
		userDetailsDTO.setAuthorities(authorities);
		userRepository.save(userDetailsDTO);
		log.info("User Saved Successfully");
		return userDetailsDTO;
	}

	/*public void createPasswordResetTokenEmail(UserDetailsDTO userDetails, String token ) {

		PasswordResetTokenDTO passwordResetTokenDTO = new PasswordResetTokenDTO();
		passwordResetTokenDTO.setToken(token);
		passwordResetTokenDTO.setUser(userDetails);
		passwordResetTokenDTO.setExpiryDate(passwordResetTokenDTO.calculateExpiry(PasswordResetTokenDTO.EXPIRATION));
		passwordResetTokenRepo.save(passwordResetTokenDTO);
	}

	public void changePassword(ChangePassword changePassword) {

		UserDetailsDTO userDetailsDTO = loadUserByUsername(changePassword.getUsername());

		if(changePassword.getConfirmPassword().equals(changePassword.getPassword())) {
			userDetailsDTO.setPassword(passwordEncoder.encode(changePassword.getPassword()));
			userRepository.save(userDetailsDTO);
		} else {
			throw new RuntimeException("Password and confirm Password do not match.");
		}
	}

	public void udpateUser(UserDetailsDTO user) {
		userRepository.save(user);
	}
*/
}
