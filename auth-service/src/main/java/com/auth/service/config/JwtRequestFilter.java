package com.auth.service.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	private static final String AUTHORIZATION_HEADER_NAME="Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenHeader = request.getHeader(AUTHORIZATION_HEADER_NAME); 
		String username = null;
		String authToken= null;
 
	   System.out.println(request.getRequestURI());
	   System.out.println(tokenHeader);
		
		if(tokenHeader !=null && tokenHeader.startsWith("Bearer ")) {
			authToken = tokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				log.error("Unable to obtain username from the token:"+authToken,e);
			} catch (ExpiredJwtException e) {
				log.error("Auth Token is expried and not valid anymore",e);
			}
		} else {
			log.warn("Couldn't find bearer token in the header !");
		}
		
		log.info("Username:"+username);
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			log.info("Security context was not present. Authorizing the user");
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetails(request));
				log.info("Authenticated the user {}", username);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response);
	}


}
