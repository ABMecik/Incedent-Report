package com.IncidentReport.web.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="ticketpriority")
@Table(name="ticketpriority")
@NamedQueries({
	@NamedQuery(name = "FindByImportance", query = "SELECT tp FROM ticketpriority AS tp WHERE tp.importance = :importance")
	})
public class TicketPriority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
	private int id;

	@Column(unique = true)
	private int importance;
	
	
	@OneToMany(mappedBy="priority")
	private List<Ticket> tickets = new ArrayList<Ticket>();


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public int getImportance() {
		return importance;
	}


	public void setImportance(int importance) {
		this.importance = importance;
	}

	public TicketPriority() {
		super();
	}


	public List<Ticket> getTickets() {
		return tickets;
	}


	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
}
