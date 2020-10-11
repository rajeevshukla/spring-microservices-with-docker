package com.auth.service.api;

import com.auth.service.model.FacebookProfile;


public class Facebook extends ApiBinding {

	
private static final String FACEBOOK_GRAPH_BASE_URL="https://graph.facebook.com/v7.0";
	
	public Facebook(String authToken) {
		super(authToken);
	}

	public FacebookProfile getProfileDetails() {
		System.out.println("Fetching facebook profile details");
		return restTemplate.getForObject(FACEBOOK_GRAPH_BASE_URL+"/me?fields=id,email,last_name,first_name,name", FacebookProfile.class);
	}

}
