package com.IncidentReport.web.Services;

import java.util.ArrayList;
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
		ticket.setEvidance_path("resources/image/img/processing.png");
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



	public List<Ticket> deptReleated(int department) {
		try {
			em.getTransaction().begin();
			
			List<Ticket> t = em.createNamedQuery("deptTickets", Ticket.class).setParameter("department", department).getResultList();
			
			em.getTransaction().commit();
			em.close();
			
			return t;
		}
		catch(Exception e){
			System.out.println(e.toString());
			em.close();
			return null;
		}
	}



	public void setStatus(int ticketID, TicketStatus status) {
		try {
			em.getTransaction().begin();
			
			Ticket t = em.find(Ticket.class, ticketID);
			t.setStatus(status);
			
			em.getTransaction().commit();
			em.close();

		}
		catch(Exception e){
			em.close();
		}
		
	}



	public void assignStaff(User staff, int ticketID) {
		try {
			em.getTransaction().begin();
			
			Ticket t = em.find(Ticket.class, ticketID);
			t.setStaff(staff);
			
			em.getTransaction().commit();
			em.close();

		}
		catch(Exception e){
			em.close();
		}
		
	}



	public List<Ticket> managerReleated(int managerID) {
		List<Ticket> tickets = this.AllTickets();
		List<Ticket> sTickets = new ArrayList<Ticket>();
		
		for(Ticket ticket : tickets) {
			if(ticket.getManager() != null) {
				if(ticket.getManager().getId() == managerID) {
					sTickets.add(ticket);
				}
			}
		}
		
		return sTickets;
	}



	public List<Ticket> findByStatusAndLikeTitle(String sTitle, int sStatus) {
		List<Ticket> allTickets = this.findLikeTitle(sTitle);
		List<Ticket> sTickets = new ArrayList<Ticket>();
		
		for(Ticket ticket : allTickets) {
			if(ticket.getStatus().getId() == sStatus) {
				sTickets.add(ticket);
			}
		}
		
		return sTickets;
	}



	public List<Ticket> findByStatus(int sStatus) {
		List<Ticket> allTickets = this.AllTickets();
		List<Ticket> sTickets = new ArrayList<Ticket>();
		
		for(Ticket ticket : allTickets) {
			if(ticket.getStatus().getId() == sStatus) {
				sTickets.add(ticket);
			}
		}
		
		return sTickets;
		
	}



	public List<Ticket> findLikeTitle(String sTitle) {
		try {
			em.getTransaction().begin();
			
			List<Ticket> t = em.createNamedQuery("findLikeTitle", Ticket.class)
					.setParameter("title", "%"+sTitle+"%")
					.getResultList();
			
			em.getTransaction().commit();
			em.close();
			
			return t;
		}
		catch(Exception e){
			em.close();
			return null;
		}
	}

	public List<Ticket> staffsTickets(int staffID) {
		List<Ticket> stickets = new ArrayList<Ticket>();
		List<Ticket> allTickets = this.AllTickets();
		
		for(Ticket ticket : allTickets) {
			if(ticket.getStaff() != null) {
				if(ticket.getStaff().getId() == staffID) {
					stickets.add(ticket);
				}
			}
		}
		
		
		return stickets;
	}



	public void updateStaffInput(int ticketID, String dbPath) {
		try {
			em.getTransaction().begin();
			
			Ticket t = em.find(Ticket.class, ticketID);
			t.setEvidance_path(dbPath);
	
			em.getTransaction().commit();
			em.close();

		}
		catch(Exception e){
			em.close();
		}
		
	}


	public List<Ticket> deptReleatedSelection(String dept, List<Ticket> allTickets) {
		List<Ticket> mtickets = new ArrayList<Ticket>();
		
		for(Ticket ticket : allTickets) {
			if(ticket.getDepartment() != null) {
				if(ticket.getDepartment().getName().equals(dept)) {
					mtickets.add(ticket);
				}
			}
		}
		
		
		return mtickets;
	}


	public List<Ticket> staffReleatedSelection(int staffID, List<Ticket> allTickets) {
		List<Ticket> stickets = new ArrayList<Ticket>();
		
		for(Ticket ticket : allTickets) {
			if(ticket.getStaff() != null) {
				if(ticket.getStaff().getId() == staffID) {
					stickets.add(ticket);
				}
			}
		}
		
		
		return stickets;
	}






}
