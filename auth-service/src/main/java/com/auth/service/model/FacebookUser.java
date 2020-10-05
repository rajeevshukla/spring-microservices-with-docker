package com.auth.service.model;

import java.util.Map;

public class FacebookUser extends OAuth2UserInfo{

	public FacebookUser(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}

	@Override
	public String getFirstName() {
		return (String)attributes.get("first_name");
	}

	@Override
	public String getLastName() {
		return (String)attributes.get("last_name");
	}

	@Override
	public String getId() {
		return (String)attributes.get("id");
	}

	@Override
	public String getProfileUrl() {
		return (String)attributes.get("profile_url");
	}
	
	@Override
	public AuthProvider getProvider() {
		return AuthProvider.FACEBOOK;
	}

}
