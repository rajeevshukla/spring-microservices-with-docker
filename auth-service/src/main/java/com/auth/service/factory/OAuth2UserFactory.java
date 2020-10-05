package com.auth.service.factory;

import java.util.HashMap;
import java.util.Map;

import com.auth.service.api.Facebook;
import com.auth.service.model.AuthProvider;
import com.auth.service.model.FacebookProfile;
import com.auth.service.model.FacebookUser;
import com.auth.service.model.OAuth2UserInfo;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

public class OAuth2UserFactory {

	public static OAuth2UserInfo getOAuth2User(OAuth2UserRequest request) {
		if(AuthProvider.FACEBOOK.name().equalsIgnoreCase(request.getClientRegistration().getRegistrationId())) {
			Facebook facebook = new Facebook(request.getAccessToken().getTokenValue());
			FacebookProfile profile = facebook.getProfileDetails();
            System.out.println(profile);
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("id", profile.getId());
			attributes.put("name", profile.getName());
			attributes.put("first_name", profile.getFirst_name());
			attributes.put("last_name", profile.getLast_name());
			attributes.put("email", profile.getEmail());
			
			FacebookUser facebookUser = new FacebookUser(attributes);
			return facebookUser;
			
		}  else {
			throw new RuntimeException("Login with "+ request.getClientRegistration().getRegistrationId() +" not supported");
		}
	}
	
	
}
