package com.IncidentReport.web.Model;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "ticket")
@Table(name = "ticket")
@NamedQueries({
	@NamedQuery(name = "FindTicketByNameAndDate", query = "SELECT t FROM ticket AS t WHERE t.title = :title AND t.created_at = :created_at")
	})
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	private String title;
	private String comment;
	private String location;
	private int rating;
	private boolean isAnonim;
	@Column(nullable = false)
	private Date created_at;
	private Date timeout;
	private String photo_path;

	@ManyToOne
	@JoinColumn(name = "staff")
	private User staff;
	
	@ManyToOne
	@JoinColumn(name = "department")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User created_by;
	 
	@OneToMany(mappedBy = "relatedticket")
	private List<Message> messages = new ArrayList<Message>();
	
	@ManyToOne
	@JoinColumn(name = "priority")
	private TicketPriority priority;

	@ManyToOne
	@JoinColumn(name = "status")
	private TicketStatus status;
	
	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public boolean getIsAnonim() {
		return isAnonim;
	}

	public void setIsAnonim(boolean isAnonim) {
		this.isAnonim = isAnonim;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
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

	public Ticket(String title, String comment, String location, boolean isAnonim, String photo_path) {
		super();
		this.title = title;
		this.comment = comment;
		this.location = location;
		this.isAnonim = isAnonim;
		this.photo_path = photo_path;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public void setAnonim(boolean isAnonim) {
		this.isAnonim = isAnonim;
	}
	
	
	


}
