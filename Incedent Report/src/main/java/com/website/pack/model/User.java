package com.website.pack.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id", updatable = false, nullable = false)
	private int user_id;
	@Column(nullable=false, unique=true)
	private String username;
	@Column(nullable=false, unique=true)
	private String email;
	private String password;
	private String name;
	private String surname;
	private int rating;
	@Column(nullable=false)
	private Date created_at;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
				joinColumns = { @JoinColumn(name="user_id") },
				inverseJoinColumns = { @JoinColumn(name="role_id") })
	private List<Role> roles = new ArrayList<Role>();
	
	@ManyToOne
	@JoinColumn(name="title")
	private Title title;
	
	@ManyToOne
	@JoinColumn(name="department")
	private Department department;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int systemuser_id) {
		this.user_id = systemuser_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public User orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
