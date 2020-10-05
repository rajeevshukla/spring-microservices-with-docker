package com.auth.service.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class RegisterUser {
	
	@NotBlank(message = "Password can't be blank.")
	private String password;
	
	@NotBlank(message = "EmailId can't be blank.")
	@Email(message = "EmailId must have to a valid emailId.")
	private String emailId; //emailId is the userName;

	@NotBlank(message = "firstName can't be blank.")
	private String firstName;
	
	@NotBlank(message = "lastName can't be blank.")
	private String lastName;
	
	@JsonIgnore(value = true)
	private String profileImgUrl;
	
	private boolean pnp; // Privacy Policy
	
	

}
