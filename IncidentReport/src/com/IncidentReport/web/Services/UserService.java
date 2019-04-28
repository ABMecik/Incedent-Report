package com.IncidentReport.web.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.Department;
import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Security.Sha512;


public class UserService {


	private EntityManager em;
	
	private RoleService rls = new RoleService();



	public UserService() {

		em = EMF.createEntityManager();
	}


	private Sha512 sha512 = new Sha512();


	public Boolean insert(User user) {
		Date date = new Date();
		user.setCreated_at(date);
		
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
			System.out.println(e);
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
			System.out.println(e);
			em.close();
			return null;
		}
	}



	public List<User> findRoleList(String role) {
		List<User> u = new ArrayList<User>();
		try {
			List<User> users = this.allUsers();
			
			for(User user : users) {
				
				if(user.getRole().getName().equals(role)) {
					u.add(user);
				}
			}
			
			return u;
		}
		catch(Exception e){
			em.close();
			return u;
		}
	}



	public List<User> allUsers() {
		List<User> u = new ArrayList<User>();
		try {
			em.getTransaction().begin();
			u = em.createNamedQuery("allUsers", User.class).getResultList();
			em.getTransaction().commit();
			em.close();
			
			return u;
		}
		catch(Exception e){
			em.close();
			return u;
		}
	}



	public List<User> deptReleated(String department, String role) {
		List<User> users = this.allUsers();
		List<User> sUsers = new ArrayList<User>();
		
		if(department == null) {
			department = "";
		}
		if(role == null) {
			role = "";
		}
		
		for(User user : users) {
			if(user.getDept() != null && user.getRole() != null) {
				if(user.getDept().getName().equals(department) && user.getRole().getName().equals(role)) {
					sUsers.add(user);
				}
			}
		}
		
		
		return sUsers;
	}
	
	
	public List<User> findByName(String name, String surname) {
		List<User> u = new ArrayList<User>();
		try {
			em.getTransaction().begin();
			
			u = em.createNamedQuery("FindByLikeName", User.class)
					.setParameter("name", "%"+name+"%")
					.setParameter("surname", "%"+surname+"%")
					.getResultList();
			
			em.getTransaction().commit();
			em.close();
			
			return u;
		}
		catch(Exception e){
			em.close();
			return u;
		}
	}



	public boolean UpdateUserRD(int userID, int departmentID, int roleID) {
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, userID);
			Department d = em.find(Department.class, departmentID);
			Role r = em.find(Role.class, roleID);
			
			u.setRole(r);
			u.setDept(d);
			
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch(Exception e){
			em.close();
			return false;
		}
		
	}



	public List<User> findByNameAndRole(String suNS, int suR) {
		List<User> u = this.findByName(suNS, suNS);
		List<User> us = new ArrayList<User>();
		
		for(User user : u) {
			if(user.getRole().getId() == suR) {
				us.add(user);
			}
		}
		
		return us;
	}



	public List<User> findByDepartment(int suD) {
		List<User> u = this.allUsers();
		List<User> us = new ArrayList<User>();
		
		for(User user : u) {
			if(user.getDept() != null) {
				if(user.getDept().getId() == suD) {
					us.add(user);
				}
			}
		}
		
		return us;
	}



	public List<User> findByDepartmentAndRole(int suR, int suD) {
		List<User> u = this.findByRole(suR);
		List<User> us = new ArrayList<User>();
		
		for(User user : u) {
			if(user.getDept() != null) {
				if(user.getDept().getId() == suD) {
					us.add(user);
				}
			}
		}
		
		return us;
	}



	public List<User> findByNameAndDepartment(String suNS, int suD) {
		List<User> u = this.findByName(suNS, suNS);
		List<User> us = new ArrayList<User>();
		
		for(User user : u) {
			if(user.getDept() != null) {
				if(user.getDept().getId() == suD) {
					us.add(user);
				}
			}
		}
		
		return us;
	}



	public List<User> findByNameDepartmentAndRole(String suNS, int suD, int suR) {
		List<User> u = this.findByNameAndRole(suNS, suR);
		List<User> us = new ArrayList<User>();
		
		for(User user : u) {
			if(user.getDept() != null) {
				if(user.getDept().getId() == suD) {
					us.add(user);
				}
			}
		}
		
		return us;
	}



	public List<User> findByRole(int suR) {
		List<User> u = this.allUsers();
		List<User> us = new ArrayList<User>();
		
		for(User user : u) {
			if(user.getDept() != null) {
				if(user.getRole().getId() == suR) {
					us.add(user);
				}
			}
		}
		
		return us;
	}


	

}