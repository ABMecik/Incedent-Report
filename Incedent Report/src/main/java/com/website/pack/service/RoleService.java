package com.website.pack.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.website.pack.model.Role;
import com.website.pack.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public void addRole(Role role) {
		Role rr = new Role();
		rr = findByName(role.getName());
		if(rr == null) {
			roleRepository.save(role);
		}
	}

	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}

	public Optional<Role> findById(int id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

}
