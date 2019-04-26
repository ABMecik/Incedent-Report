package com.website.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.pack.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findAllByUsername(String username);

}
