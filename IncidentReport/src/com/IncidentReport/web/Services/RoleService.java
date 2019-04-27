package com.IncidentReport.web.Services;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.User;

public class RoleService {
	
	private EntityManager em;



	public RoleService() {

		em = EMF.createEntityManager();
	}



	public boolean checkRole(String role) {
		Role r = new Role();
		try {
			em.getTransaction().begin();
			r = em.createNamedQuery("FindByName", Role.class)
					.setParameter("name", role)
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



	public void createRole(Role role) {
		
		try {

			em.getTransaction().begin();
			em.persist(role);
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e){
			em.close();
		}

	}



	public boolean setUserRole(int userID, int roleID) {
		try {
			em.getTransaction().begin();
			
			User user = em.find(User.class, userID);
			Role role = em.find(Role.class, roleID);

			user.setRole(role);
			role.getUsers().add(user);
			
			em.flush();
			em.getTransaction().commit();
			em.close();

			return true;
		}
		catch(Exception e){
			System.out.println(e);
			em.close();
			return false;
		}
		
	}



	public Role findByName(String role) {
		Role r = new Role();
		try {
			em.getTransaction().begin();
			r = em.createNamedQuery("FindByName", Role.class)
					.setParameter("name", role)
					.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return r;
			
		}
		catch(Exception e){
			em.close();
			return null;
		}
	}

}
