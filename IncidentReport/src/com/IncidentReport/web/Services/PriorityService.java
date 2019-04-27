package com.IncidentReport.web.Services;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.TicketPriority;
import com.IncidentReport.web.Listener.EMF;

public class PriorityService {
	
	private EntityManager em;



	public PriorityService() {

		em = EMF.createEntityManager();
	}



	public boolean checkIsValid(int priority) {
		TicketPriority tp;
		try {
			em.getTransaction().begin();
			tp = em.createNamedQuery("FindByImportance", TicketPriority.class)
					.setParameter("importance", priority)
					.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return true;
			
		}
		catch(Exception e){
			em.close();
			return false;
		}
	}



	public void createNewPriority(int priority) {
		TicketPriority tp = new TicketPriority();
		tp.setImportance(priority);
		try {

			em.getTransaction().begin();
			em.persist(tp);
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e){
			em.close();
		}
		
	}



	public TicketPriority findPriority(int priority) {
		TicketPriority tp;
		try {
			em.getTransaction().begin();
			tp = em.createNamedQuery("FindByImportance", TicketPriority.class)
					.setParameter("importance", priority)
					.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return tp;
			
		}
		catch(Exception e){
			em.close();
			return null;
		}
	}

}
