package com.email.service.sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserEmailSenderService {

	@Autowired
	JavaMailSender javaMailSender;
	
	public boolean sendRegistrationEmail(String userJson) {
		
		boolean isSent = false;
		try {
			isSent = false;
			JSONObject jsonObject = new JSONObject(userJson);
			System.out.println("Sending registration Email");
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(jsonObject.getString("email"));
			helper.setText("<html> <body> <p>Hello "+jsonObject.getString("userName")+"</p> <p> This is test email "
					+ "from Microservice using spring boot using docker.</p>"
					+ "<p>Thanks, <br/> Docker Deamon</p>", true);
	
			helper.setSubject("Thank you for registring on Docker - Microservice Portal");
			javaMailSender.send(helper.getMimeMessage());
			isSent =true;
		} catch (MailException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MessagingException e ) { 
			e.printStackTrace();
		}
		return isSent;
	}
	
	
}
