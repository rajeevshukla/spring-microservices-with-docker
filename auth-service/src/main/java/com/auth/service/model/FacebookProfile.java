package com.auth.service.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FacebookProfile{

	private String id;
	private String name;
	private String email;
	private String first_name;
	private String last_name;
	private String profileURL;
	@JsonProperty("picture")
	public void getPictureUrl(Map<String, Object> map) {
		  
	};
}
