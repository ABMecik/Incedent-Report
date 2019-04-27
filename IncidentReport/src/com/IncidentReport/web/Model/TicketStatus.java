package com.IncidentReport.web.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="ticketstatus")
@Table(name="ticketstatus")
public class TicketStatus {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
	private int id;

	private String name;
	private String comment;
	
	
	@OneToMany(mappedBy="status")
	private List<Ticket> todo = new ArrayList<Ticket>();


	public TicketStatus() {
		super();
	}
	
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Ticket> getTodo() {
		return todo;
	}

	public void setTodo(List<Ticket> todo) {
		this.todo = todo;
	}

	
	
	
}
