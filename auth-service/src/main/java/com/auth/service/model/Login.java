package com.auth.service.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Login {
	private String username;
	private String password;
}
