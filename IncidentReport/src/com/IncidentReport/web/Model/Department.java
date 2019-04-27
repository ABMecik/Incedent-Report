package com.IncidentReport.web.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="department")
@Table(name="department")
public class Department {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	@Column(nullable=false, unique=true)
	private String name;
	
	
	@OneToMany(mappedBy="dept")
	private List<User> users = new ArrayList<User>();
	
	@OneToMany(mappedBy="department")
	private List<Ticket> todo = new ArrayList<Ticket>();


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public Department(int id, String name, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}


	public Department() {
		super();
	}


	public List<Ticket> getTodo() {
		return todo;
	}


	public void setTodo(List<Ticket> todo) {
		this.todo = todo;
	}
	
	
	
	
}
