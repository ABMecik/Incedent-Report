package com.IncidentReport.web.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="message")
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	@Column(nullable=false, unique=true)
	private String title;
	private String Desc;
	
	
	@ManyToOne
	@JoinColumn(name="user")
	@Column(nullable=false)
	private User fromuser;
	
	@ManyToOne
	@JoinColumn(name="user")
	@Column(nullable=false)
	private User touser;
	
	@ManyToOne
	@JoinColumn(name="ticket")
	private Ticket relatedticket;

	public Message() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public User getFromuser() {
		return fromuser;
	}

	public void setFromuser(User fromuser) {
		this.fromuser = fromuser;
	}

	public User getTouser() {
		return touser;
	}

	public void setTouser(User touser) {
		this.touser = touser;
	}

	public Ticket getRelatedticket() {
		return relatedticket;
	}

	public void setRelatedticket(Ticket relatedticket) {
		this.relatedticket = relatedticket;
	}
	
	
	
	
	
}
