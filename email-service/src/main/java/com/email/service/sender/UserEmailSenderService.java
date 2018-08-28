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
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class UserEmailSenderService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired 
	SpringTemplateEngine templateEngine;

	public boolean sendRegistrationEmail(String userJson) {

		boolean isSent = false;
		try {
			isSent = false;
			JSONObject jsonObject = new JSONObject(userJson);
			System.out.println("Sending registration Email");

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

			// in case you want to add any attachment to email
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8);
			//helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

			Context context = new Context();
			//context.setVariables(); // to set dynamic variables here. 
			String html = templateEngine.process("reg-email-template", context);
            helper.setText(html, true);
			helper.setTo(jsonObject.getString("email"));

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
