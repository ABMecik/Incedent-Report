package com.IncidentReport.web.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Security.Sha512;


public class UserService {


	private EntityManager em;



	public UserService() {

		em = EMF.createEntityManager();
	}


	private Sha512 sha512 = new Sha512();


	public Boolean insert(User user) {
		Date date = new Date();
		user.setCreated_at(date);
		
		em.getTransaction().begin();
		Role role = em.createNamedQuery("FindByName", Role.class)
				.setParameter("name", "User")
				.getSingleResult();
		em.getTransaction().commit();
		em.close();
		
		if(role == null) {
			role = new Role();
			role.setName("User");
			
			try {

				em.getTransaction().begin();
				em.persist(role);
				em.getTransaction().commit();
				em.close();

				return true;
			}
			catch(Exception e){
				em.close();
				return false;
			}
			
		}
		
		em.getTransaction().begin();
		role = em.createNamedQuery("FindByName", Role.class)
				.setParameter("name", "User")
				.getSingleResult();
		em.getTransaction().commit();
		em.close();
		
		
		user.getUroles().add(role);

		String password = user.getPassword();
		password = sha512.encrypt(password);
		user.setPassword(password);

		try {

			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();

			return true;
		}
		catch(Exception e){
			em.close();
			return false;
		}

	}

	public User Login(String username,String password) {

		password = sha512.encrypt(password);

		try {
			em.getTransaction().begin();
			User u = em.createNamedQuery("Login", User.class)
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();

			em.getTransaction().commit();
			em.close();
			return u;
		}
		catch(Exception e){
			em.close();
			return null;
		}

	}

	public User info(int id) {
		try {
			em.getTransaction().begin();
			User u = em.createNamedQuery("Info", User.class)
					.setParameter("id", id)
					.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return u;
		}
		catch(Exception e){
			em.close();
			return null;
		}
	}
	public boolean UpdateInfo(String username, String email, int id) {
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, id);
			u.setEmail(email);
			u.setUsername(username);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch(Exception e){
			em.close();
			return false;
		}
	}

	public boolean UpdatePassword(String new_password, int id) {

		new_password = sha512.encrypt(new_password);

		try {
			em.getTransaction().begin();
			User u = em.find(User.class, id);
			u.setPassword(new_password);
			em.getTransaction().commit();
			em.close();
			return true;

		}
		catch(Exception e){
			em.close();
			return false;
		}

	}

	public String findNameById(int id) {
		try {
			em.getTransaction().begin();
			User k = em.createNamedQuery("Info", User.class)
					.setParameter("id", id)
					.getSingleResult();

			em.getTransaction().commit();
			em.close();
			
			return k.getUsername();
		}
		catch(Exception e){
			System.out.println("miss");
			em.close();
			return null;
		}
	}

	public User findById(int id) {
		try {
			em.getTransaction().begin();
			User k = em.find(User.class, id);

			em.getTransaction().commit();
			em.close();
			
			return k;
		}
		catch(Exception e){
			System.out.println("miss");
			em.close();
			return null;
		}
	}

}