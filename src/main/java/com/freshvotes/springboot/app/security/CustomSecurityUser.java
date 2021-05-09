package com.freshvotes.springboot.app.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.freshvotes.springboot.app.entity.Authority;
import com.freshvotes.springboot.app.entity.User;

public class CustomSecurityUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User customUser;
	
	public CustomSecurityUser(User user){
		this.customUser = user;
	}

	@Override
	public Set<Authority> getAuthorities() {
		return customUser.getAuthorities();
	}

	@Override
	public String getPassword() {
		return customUser.getPassword();
	}

	@Override
	public String getUsername() {
		return customUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
