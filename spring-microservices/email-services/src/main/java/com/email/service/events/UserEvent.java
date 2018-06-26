package com.email.service.events;

import org.springframework.context.ApplicationEvent;

import com.email.service.utils.EventType;

public class UserEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserEvent(Object source, EventType eventType) {
		super(source);
	}

	
}
