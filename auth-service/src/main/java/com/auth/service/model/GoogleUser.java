package com.auth.service.model;

import java.util.Map;

public class GoogleUser extends OAuth2UserInfo{

	public GoogleUser(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public String getFirstName() {
		return null;
	}

	@Override
	public String getLastName() {
		return null;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getProfileUrl() {
		return null;
	}

	@Override
	public AuthProvider getProvider() {
		return AuthProvider.GOOGLE;
	}
	
	
	

}
