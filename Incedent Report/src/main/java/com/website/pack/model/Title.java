package com.website.pack.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
public class Title {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="title_id", updatable = false, nullable = false)
	private int title_id;
	@Column(nullable=false, unique=true)
	private String name;
	
	@OneToMany(mappedBy="title", cascade=CascadeType.ALL)
	private List<User> users = new ArrayList<User>();

	public int gettitle_id() {
		return title_id;
	}

	public void settitle_id(int title_id) {
		this.title_id = title_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	
}
