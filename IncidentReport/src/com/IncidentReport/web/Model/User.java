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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="user")
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="Login", query="SELECT u FROM user AS u WHERE u.username = :username AND u.password = :password"),
	@NamedQuery(name="Info", query="SELECT u FROM user AS u WHERE u.id = :id")
})
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	
	private String name;
	private String surname;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false, unique=true)
	private String username;
	
	private String password;
	
	@Column(nullable=false)
	private Date created_at;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
				joinColumns = { @JoinColumn(name="user_id") },
				inverseJoinColumns = { @JoinColumn(name="role_id") })
	private List<Role> uroles = new ArrayList<Role>();
	
	
	@ManyToOne
	@JoinColumn(name="department")
	private Department dept;
	
	
	private String title;
	
	@OneToMany(mappedBy="staff")
	private List<Ticket> todo = new ArrayList<Ticket>();

	@OneToMany(mappedBy="created_by")
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	@OneToMany(mappedBy="touser")
	private List<Message> recivedmsgs = new ArrayList<Message>();
	
	
	@OneToMany(mappedBy="fromuser")
	private List<Message> forwardmsgs = new ArrayList<Message>();
	
	public User() {
		super();
	}

	
	
	




	public List<Ticket> getTodo() {
		return todo;
	}








	public void setTodo(List<Ticket> todo) {
		this.todo = todo;
	}








	public List<Ticket> getTickets() {
		return tickets;
	}








	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}








	public List<Message> getRecivedmsgs() {
		return recivedmsgs;
	}








	public void setRecivedmsgs(List<Message> recivedmsgs) {
		this.recivedmsgs = recivedmsgs;
	}








	public List<Message> getForwardmsgs() {
		return forwardmsgs;
	}








	public void setForwardmsgs(List<Message> forwardmsgs) {
		this.forwardmsgs = forwardmsgs;
	}








	public User(String name, String email, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}








	public User(int id, String name, String surname, String email, String username, String password, Date created_at,
			List<Role> uroles, Department dept, String title) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.created_at = created_at;
		this.uroles = uroles;
		this.dept = dept;
		this.title = title;
	}








	public User(String name, String surname, String email, String username, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
	}








	public String getSurname() {
		return surname;
	}








	public void setSurname(String surname) {
		this.surname = surname;
	}








	public Department getDept() {
		return dept;
	}








	public void setDept(Department dept) {
		this.dept = dept;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public List<Role> getUroles() {
		return uroles;
	}


	public void setUroles(List<Role> uroles) {
		this.uroles = uroles;
	}




	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
