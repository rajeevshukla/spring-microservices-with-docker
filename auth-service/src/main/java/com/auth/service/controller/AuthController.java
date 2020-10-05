package com.auth.service.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import com.auth.service.config.JwtTokenUtil;
import com.auth.service.dto.UserDetailsDTO;
import com.auth.service.event.OnRegistrationCompleteEvent;
import com.auth.service.exception.AuthServerException;
import com.auth.service.model.AuthProvider;
import com.auth.service.model.Login;
import com.auth.service.model.LoginResponse;
import com.auth.service.model.RegisterUser;
import com.auth.service.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.nimbusds.jose.jwk.JWKSet;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class AuthController {
	
	
	@Autowired
	AuthenticationManager authManager;
	
	
	
	@Autowired
	JwtTokenUtil jwtTokenUtils;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	TokenService tokenService;
	
	
	@Autowired
	ClientDetailsService clientDetailsService;
	
	@Autowired 
	ApplicationEventPublisher eventPubisher;
	

	@Value("${ng.app.base-url}")
	private String ngAppBaseURL;
	
	@PostMapping("/register")
	public void registerUser(@Valid @RequestBody RegisterUser user, HttpServletRequest request) {
		String appURL = request.getContextPath();
		 
		 System.out.println(appURL);
		 
		 String referer = request.getHeader("referer");
		 System.out.println(referer);
		 
		UserDetailsDTO userDetailsDTO = userService.saveUser(user, AuthProvider.LOCAL);
		eventPubisher.publishEvent(new OnRegistrationCompleteEvent(userDetailsDTO, Locale.getDefault(),appURL));
	}
	
	@PostMapping("/login") 
	public ResponseEntity<?> login(@RequestBody Login login) {
	    log.info("Login attempted:"+ login);  
		 authenticate(login.getUsername(), login.getPassword());

		UserDetails userDetails = userService.loadUserByUsername(login.getUsername());
		final String token = jwtTokenUtils.generateToken(userDetails);
		return ResponseEntity.ok(new LoginResponse(token));
	}
	
	@GetMapping("/loginSuccess")
	public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal UserDetails userDetails){
		System.out.println(userDetails);
		final String token = jwtTokenUtils.generateToken(userDetails);
		return ResponseEntity.ok(new LoginResponse(token));
	}
	/*
	 * @Autowired private JWKSet jwkSet;
	 * 
	 * @GetMapping("/.well-known/jwks.json") public Map<String, Object> keys() {
	 * System.out.println("Request recieved to fetch jwks.json file"); return
	 * this.jwkSet.toJSONObject();
	 * 
	 * }
	 */
	/*
	@GetMapping("/resetPassword") 
	public ResponseEntity<?> resetPassword(HttpServletRequest request, @RequestParam("emailId") String emailId) {
		UserDetailsDTO userDetails = userService.loadUserByUsername(emailId);
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenEmail(userDetails, token);
		
		Map<String, Object> contentModel = new HashMap<String, Object>();
		contentModel.put("name", userDetails.getFirstName());
		String url= ngAppBaseURL +"/validatePasswdToken?token="+ token;
		contentModel.put("resetUrl", url);
		Email email = new Email(userDetails.getUsername(), "no-reply@lspace.com", "Password Reset", contentModel, EmailTemplateEnum.RESET_PASSWORD);
		emailService.send(email);
		return ResponseEntity.ok(null);
	 }
	
	@GetMapping("/validateResetPasswordToken") 
	public ResponseEntity<?> validateResetPasswordToken(HttpServletRequest request, @RequestParam("token") String token) {
		log.info("Password  token validation request ::: "+token);
		UserDetailsDTO userDetails = tokenService.validatePasswordVerificationToken(token);
		
		System.out.println("User Name obtained"+userDetails.getUsername());
		PasswordResetResponse resetResponse = new PasswordResetResponse();
		resetResponse.setUsername(userDetails.getUsername());
		resetResponse.setToken(token);
		return ResponseEntity.ok(resetResponse);
	 }
	
	@PostMapping("/changePassword") 
	public ResponseEntity<?> changeUserPassword(HttpServletRequest request, @RequestBody ChangePassword changePassword) {
		
		UserDetailsDTO userDetails = tokenService.validatePasswordVerificationToken(changePassword.getToken());
		if(userDetails == null) {
			throw new AuthServerException("Token was either invalid or changed. Please try resetting again.");
		} 
		
		userService.changePassword(changePassword);
			
		return ResponseEntity.ok(null);
	 }
	
	
	
	
	
	@GetMapping("/confirmRegistration") 
	public ResponseEntity<?> confirmRegistration(HttpServletRequest request, @RequestParam("token") String token) {
		
		UserDetailsDTO userDetails = tokenService.validateRegisterationToken(token);
		
		if(userDetails == null) {
			 throw new AuthServerException("Invalid token passed");
		}
		userDetails.setEnabled(true);
		userService.udpateUser(userDetails);
		return ResponseEntity.ok(null);
	 }*/
	
	private Authentication authenticate(String username, String password){
		return authManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
	}
	 

}
