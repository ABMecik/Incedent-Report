package com.website.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.pack.model.Title;

public interface RoleRepository extends JpaRepository<Title, Integer>{

}
