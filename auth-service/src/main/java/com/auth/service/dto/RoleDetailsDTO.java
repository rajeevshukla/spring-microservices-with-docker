package com.auth.service.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "ROLE_DETAILS")
public class RoleDetailsDTO implements GrantedAuthority {

	private static final long serialVersionUID = 2952415589385293583L;
	
	@Id
	@Column(name = "ROLE_ID",nullable = false,length = 20)	
	private String roleId;
	@Column(name = "ROLE_DESC", length = 50)
    private String roleDesc;
	
	@Transient
    @Override
    public String getAuthority() {
    	return roleId;
    }
    
}
