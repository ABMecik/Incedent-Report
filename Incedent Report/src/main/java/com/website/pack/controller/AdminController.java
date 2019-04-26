package com.website.pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.pack.model.User;
import com.website.pack.repository.UserRepository;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PreAuthorize("hasAnyRole('Admin')")
	@PostMapping("/admin/addUser")
	public String addUser(@RequestBody User user) {
		
		String pwd = user.getPassword();
		user.setPassword(passwordEncoder.encode(pwd));
		userRepository.save(user);
		return "user added";
	}

}
