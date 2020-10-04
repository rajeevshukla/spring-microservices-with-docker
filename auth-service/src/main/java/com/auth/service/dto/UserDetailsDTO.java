package com.auth.service.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.auth.service.model.AuthProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "USER_DETAILS")
public class UserDetailsDTO implements UserDetails, OAuth2User {

	private static final long serialVersionUID = -8865315030153705770L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false)
	private Long userId;
	
	@Column(name = "USER_NAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "IS_ACCOUNT_NON_EXPIRED", columnDefinition = "smallint default 1")
	private boolean isAccountNonExpired=true;

	@Column(name = "IS_ACCOUNT_NON_LOCKED", columnDefinition = "smallint default 1")
	private boolean isAccountNonLocked=true;

	@Column(name = "IS_CREDENTIAL_NON_EXPIRED", columnDefinition = "smallint default 1")
	private boolean isCredentialsNonExpired=true;

	@Column(name = "IS_ENABLED", columnDefinition = "smallint default 1")
	private boolean isEnabled=true;
	
	@Column(name = "PROVIDER", nullable = false, columnDefinition = "varchar(20) default 'local'")
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;
	
	@Column(name = "PROFILE_IMG_URL") 
	private String profileImgURL;
	
	@Transient
	private Map<String, Object> attributes;
	
	@ManyToMany(targetEntity = RoleDetailsDTO.class, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE_MAPPING", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Collection<? extends GrantedAuthority> authorities = new HashSet<>();

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {

		return isEnabled;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	@Transient
	public String getName() {
		return (String)attributes.get("id");
	}
}
