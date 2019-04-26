package com.website.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.pack.model.Role;
import com.website.pack.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findAllByUsername(String username);

	List<Role> findByRole(Role role);

	public default User findById(int id) {
		return findById(id).orElse(null);
	}
	
	public default User findOne(int id) {
		return findById(id).orElse(null);
	}

	User findOne(Long id);

	User findByEmail(String email);

	List<User> findByUsernameLike(String string);

	User findByEmailAndPassword(String email, String password);

	User findByUsernameAndPassword(String username, String password);

	User findByIdAndPassword(int id, String password);

	void deleteById(Long id);


}
