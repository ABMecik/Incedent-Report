package com.IncidentReport.web.Services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.Department;
import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.User;

public class DepartmentService {
	
	private EntityManager em;



	public DepartmentService() {

		em = EMF.createEntityManager();
	}



	public List<Department> allDepartments() {
		List<Department> d = new ArrayList<Department>();
		try {
			em.getTransaction().begin();
			d = em.createNamedQuery("allDepartments", Department.class).getResultList();
			em.getTransaction().commit();
			em.close();
			
			return d;
		}
		catch(Exception e){
			em.close();
			return d;
		}
	}



	public boolean checkDept(String name) {
		Department d = new Department();
		try {
			em.getTransaction().begin();
			d = em.createNamedQuery("FindByDepartmentName", Department.class)
					.setParameter("name", name)
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



	public void createDept(Department newD) {
		try {

			em.getTransaction().begin();
			em.persist(newD);
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e){
			em.close();
		}
		
	}




}
