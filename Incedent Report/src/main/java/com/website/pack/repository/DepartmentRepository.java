package com.website.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.pack.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
