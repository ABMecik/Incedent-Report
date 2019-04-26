package com.website.pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.website.pack.model.User;
import com.website.pack.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findAllByUsername(username);
		CustomUserDetails userDetails = null;
		if(user!=null) {
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
		}else {
			throw new UsernameNotFoundException("Username : " + username + " Not Exist");
		}
		return null;
	}

}
