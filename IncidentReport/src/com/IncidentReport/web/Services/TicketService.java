package com.IncidentReport.web.Services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.Department;
import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.TicketPriority;
import com.IncidentReport.web.Model.TicketStatus;
import com.IncidentReport.web.Model.User;

public class TicketService {
	
	private EntityManager em;



	public TicketService() {

		em = EMF.createEntityManager();
	}



	public boolean createNewTicket(Ticket ticket) {
		try {

			em.getTransaction().begin();
			em.persist(ticket);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch(Exception e){
			em.close();
			return false;
		}
	}



	public Ticket findTicket(String title, Date created_at) {
		Ticket t = new Ticket();
		try {
			em.getTransaction().begin();
			t = em.createNamedQuery("FindTicketByNameAndDate", Ticket.class)
					.setParameter("title", title)
					.setParameter("created_at", created_at)
					.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return t;
			
		}
		catch(Exception e){
			System.out.println(e.toString());
			System.out.println("error");
			em.close();
			return null;
		}
	}



	public void setTicketFKeys(int ticketID, int userID, int statusID, int priorityID) {
		try {
			em.getTransaction().begin();
			
			TicketPriority tp = em.find(TicketPriority.class, priorityID);
			TicketStatus ts = em.find(TicketStatus.class, statusID);
			User u  = em.find(User.class, userID);
			Ticket t  = em.find(Ticket.class, ticketID);
			
			t.setCreated_by(u);
			t.setStatus(ts);
			t.setPriority(tp);
			
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e){
			System.out.println(e);
			em.close();
		}
		
	}



	public List<Ticket> findUserTickets(int id) {
		try {
			em.getTransaction().begin();
			
			User user = em.find(User.class, id);
			List<Ticket> myt = user.getTickets();
			
			em.getTransaction().commit();
			em.close();
			return myt;
		}
		catch(Exception e){
			System.out.println("its here");
			System.out.println(e);
			em.close();
			return null;
		}
		
	}



	public List<Ticket> AllTickets() {
		try {
			em.getTransaction().begin();
			List<Ticket> t = em.createNamedQuery("allTickets", Ticket.class).getResultList();
			em.getTransaction().commit();
			em.close();
			
			return t;
		}
		catch(Exception e){
			em.close();
			return null;
		}
	}



	public Ticket findById(int ticketID) {
		try {
			em.getTransaction().begin();

			Ticket myt = em.find(Ticket.class, ticketID);
			
			em.getTransaction().commit();
			em.close();
			return myt;
		}
		catch(Exception e){
			System.out.println("its here");
			System.out.println(e);
			em.close();
			return null;
		}
	}



	public void updateTicketSPMD(int ticketID, TicketStatus status, TicketPriority priority, User manager,
			Department department, User frontdesk) {
		
		try {
			em.getTransaction().begin();

			Ticket ticket = em.find(Ticket.class, ticketID);
			
			ticket.setStatus(status);
			ticket.setPriority(priority);
			ticket.setDepartment(department);
			ticket.setManager(manager);
			ticket.setFrontdesk(frontdesk);
			
			
			em.getTransaction().commit();
			em.close();

		}
		catch(Exception e){
			System.out.println(e);
			em.close();
		}
		
	}





}
