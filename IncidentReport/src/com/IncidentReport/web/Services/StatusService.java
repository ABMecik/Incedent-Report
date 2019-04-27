package com.IncidentReport.web.Services;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.TicketPriority;
import com.IncidentReport.web.Model.TicketStatus;

public class StatusService {

	
	private EntityManager em;



	public StatusService() {

		em = EMF.createEntityManager();
	}



	public boolean checkIsValid(String status) {
		TicketStatus ts;
		try {
			em.getTransaction().begin();
			ts = em.createNamedQuery("FindStatusByName", TicketStatus.class)
					.setParameter("name", status)
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



	public void createNewStatus(String name, String desc) {
		TicketStatus ts = new TicketStatus();
		ts.setComment(desc);
		ts.setName(name);
		try {

			em.getTransaction().begin();
			em.persist(ts);
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e){
			em.close();
		}
		
	}



	public TicketStatus findStatus(String status) {
		TicketStatus ts;
		try {
			em.getTransaction().begin();
			ts = em.createNamedQuery("FindStatusByName", TicketStatus.class)
					.setParameter("name", status)
					.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return ts;
			
		}
		catch(Exception e){
			em.close();
			return null;
		}
	}
}
