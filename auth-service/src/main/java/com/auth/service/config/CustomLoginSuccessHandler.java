package com.auth.service.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{


	@Autowired
	JwtTokenUtil jwtTokenUtils;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		if(response.isCommitted()) {
			log.error("Response is already commited");
			return;
		}
		UserDetails userDetails  = (UserDetails)authentication.getPrincipal();
		String token = jwtTokenUtils.generateToken(userDetails);

		String url =UriComponentsBuilder.fromUriString("http://localhost:4200/oauth2/redirect").queryParam("access_token", token).build().toString(); 
		getRedirectStrategy().sendRedirect(request, response, url);

	}

}
