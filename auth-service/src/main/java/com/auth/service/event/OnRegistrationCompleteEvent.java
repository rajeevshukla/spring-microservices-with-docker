package com.auth.service.event;

import java.util.Locale;

import com.auth.service.dto.UserDetailsDTO;
import org.springframework.context.ApplicationEvent;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OnRegistrationCompleteEvent extends ApplicationEvent {
		
	private static final long serialVersionUID = -7550599963228174479L;
		
	private UserDetailsDTO userDetailsDTO;
	private Locale locale;
	private String appUrl;
	public OnRegistrationCompleteEvent(UserDetailsDTO user, Locale locale, String appUrl) {
		super(user);
		this.userDetailsDTO = user;
		this.locale=locale;
		this.appUrl = appUrl;
	}


}
