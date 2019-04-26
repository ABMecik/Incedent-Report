package com.IncidentReport.web.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="ticket")
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	@Column(nullable=false, unique=true)
	private String title;
	private String Desc;
	private String Location;
	
	@ManyToOne
	@JoinColumn(name="ticketpriority")
	private TicketPriority priority;
	
	@ManyToOne
	@JoinColumn(name="ticketstatus")
	private TicketStatus status;
	
	private int rating;
	
	@Column(nullable=false)
	private Date created_at;
	private Date timeout;
	
	@ManyToOne
	@JoinColumn(name="department")
	private Department dept;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User staff;
	
	
	@OneToMany(mappedBy="relatedticket")
	private List<Message> messages = new ArrayList<Message>();
	
	private boolean isAnonim;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User created_by;

	public Ticket() {
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

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
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

	public Date getTimeout() {
		return timeout;
	}

	public void setTimeout(Date timeout) {
		this.timeout = timeout;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public boolean isAnonim() {
		return isAnonim;
	}

	public void setAnonim(boolean isAnonim) {
		this.isAnonim = isAnonim;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}
	
	
	

}
