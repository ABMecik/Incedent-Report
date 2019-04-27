package com.IncidentReport.web.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.RoleService;
import com.IncidentReport.web.Services.UserService;



@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		System.out.println("us : "+username);
		
		User user = new User(name,username,email,username,password);
		
		UserService us = new UserService();
		
		Boolean s = us.insert(user);
		
		RoleService rs = new RoleService();
		boolean r = rs.checkRole("User");
		if(!r) {
			rs = new RoleService();
			Role newRole = new Role();
			newRole.setName("User");
			rs.createRole(newRole);
		}
		
		
		if(s) {
			us = new UserService();
			user = us.Login(username, password);
			
			
			
			rs = new RoleService();
			Role role = rs.findByName("User");
			
			rs = new RoleService();
			rs.setUserRole(user.getId(), role.getId());
			
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			RequestDispatcher reqDispatcher = getServletConfig().getServletContext()
					.getRequestDispatcher("/index.jsp");
			reqDispatcher.forward(request, response);
		}else {
			
			request.setAttribute("warning", "Username or Email alredy valid");
			RequestDispatcher reqDispatcher = getServletConfig().getServletContext()
					.getRequestDispatcher("/index.jsp");
			reqDispatcher.forward(request, response);
		}
		
		
		
	}

}

