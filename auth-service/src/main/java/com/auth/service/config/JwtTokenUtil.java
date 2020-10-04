package com.auth.service.config;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.auth.service.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JwtTokenUtil implements Serializable{


	private static final long serialVersionUID = -1287104385143423869L;
	public static final long TOKEN_VALIDITY=5;
	
	
	@Autowired 
	KeyPair keyPair;
	
	
	
	public String getUsernameFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		String userName = claims.get("user_name", String.class);
		return userName;
	}
	

	public Date getExpirationDateFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		Date expDate = claims.get("exp", Date.class);
		return expDate;
	}
	
	public boolean isTokenExpired(String token) {
		final  Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	
	
	private Claims getAllClaimsFromToken(String token) {
//		RsaVerifier verifier = new RsaVerifier((RSAPublicKey)keyPair.getPublic());
//		String claims=  JwtHelper.decodeAndVerify(token, verifier).getClaims();
		Claims claims = Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token).getBody();
		return claims;
	}
	
	public String generateToken(UserDetails userDetails) {
		UserDetailsDTO userDetailsDto =(UserDetailsDTO) userDetails;
		Map<String, Object> claims = new HashMap<>();
		claims.put("user_name", userDetails.getUsername());
		claims.put("firstName", userDetailsDto.getFirstName());
		claims.put("scope", Arrays.asList("user_info"));
	  	claims.put("authorities", userDetails.getAuthorities().stream().map(m->m.getAuthority()).collect(Collectors.toList()));
	  	claims.put("exp", new Date(System.currentTimeMillis()+ TOKEN_VALIDITY*1000).getTime());
//	  	claims.put("active", userDetailsDto.isEnabled());
	  	//claims.put("jti", new RandomValueStringGenerator().generate());
	  	claims.put("client_id","clientapp");
	  	
	  	ObjectMapper objectMapper = new ObjectMapper();
	  	
	  	
	    final RsaSigner signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
	    Map<String, String> customHeaders = Collections.singletonMap("kid", "lspace-key-id");

	    
	    try {
	    	System.out.println(objectMapper.writeValueAsString(claims));
			String token = JwtHelper.encode(objectMapper.writeValueAsString(claims), signer, customHeaders).getEncoded();
			System.out.println("Token Generated"+token);
			return token;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return null;
//		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	/*
	 * private String doGenerateToken(Map<String, Object> claims, String subject) {
	 * System.out.println("Private key");
	 * 
	 * 
	 * return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new
	 * Date(System.currentTimeMillis())) .setExpiration(new
	 * Date(System.currentTimeMillis()+ TOKEN_VALIDITY*1000))
	 * .setId(UUID.randomUUID().toString())
	 * 
	 * .signWith(SignatureAlgorithm.HS256, "Test").compact(); }
	 */
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
}
