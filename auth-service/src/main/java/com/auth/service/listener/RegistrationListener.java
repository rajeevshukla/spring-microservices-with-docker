package com.auth.service.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.auth.service.dto.UserDetailsDTO;
import com.auth.service.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	// Send an email To User with confirmation email

	@Autowired
	MessageSource messageSource;
	/*@Autowired
	EmailService emailService;*/
	
	/*@Value("${ng.app.base-url}")
	private String baseURL;*/
	
/*
	@Autowired
	TokenService tokenService;
*/

	private final String REGISTRATION_EMAIL_SUBJECT = "Registration Confirmation";

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {

		UserDetailsDTO userDetailsDTO = event.getUserDetailsDTO();
		String token = UUID.randomUUID().toString();

		/*VerificationTokenDTO verificationTokenDTO = new VerificationTokenDTO();

		verificationTokenDTO.setToken(token);
		verificationTokenDTO.setUser(userDetailsDTO);
		verificationTokenDTO.setExpiryDate(verificationTokenDTO.calculateExpiry(VerificationTokenDTO.EXPIRATION));
		
		tokenService.saveRegistrationVerificationToken(verificationTokenDTO);
		*/
//		String confirmationUrl = baseURL + "/confirmRegistration?token=" + token;

		Map<String, Object> content = new HashMap<String, Object>();
		content.put("name", userDetailsDTO.getFirstName());
//		content.put("confirmationUrl", confirmationUrl);

	/*	Email email = new Email(userDetailsDTO.getUsername(), "no-reply@lspace.com", REGISTRATION_EMAIL_SUBJECT,
				content, EmailTemplateEnum.REGISTRATION_SUCCESS);
		emailService.send(email);
		log.info("Registration confirmation email sent for userId" + userDetailsDTO.getUsername());
*/
	}

}
