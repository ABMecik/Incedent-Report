package com.IncidentReport.web.Services;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.Role;

public class RoleService {
	
	private EntityManager em;



	public RoleService() {

		em = EMF.createEntityManager();
	}



	public Role checkRole(String role) {
		em.getTransaction().begin();
		Role r = em.createNamedQuery("FindByName", Role.class)
				.setParameter("name", role)
				.getSingleResult();
		em.getTransaction().commit();
		em.close();
		
		if(r == null) {
			
			createRole(r);
			
			em.getTransaction().begin();
			r = em.createNamedQuery("FindByName", Role.class)
					.setParameter("name", role)
					.getSingleResult();
			em.getTransaction().commit();
			em.close();

		}

		return r;

		

	}



	private void createRole(Role r) {
		
		try {

			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e){
			em.close();
		}

	}

}
