package com.website.pack.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.catalina.authenticator.jaspic.SimpleAuthConfigProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.website.pack.model.User;

public class CustomUserDetails implements UserDetails{

	
	private User user;
	
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toList());
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
