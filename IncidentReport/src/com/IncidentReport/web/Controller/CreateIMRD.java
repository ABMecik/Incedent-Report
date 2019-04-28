package com.IncidentReport.web.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.IncidentReport.web.Model.Department;
import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.DepartmentService;
import com.IncidentReport.web.Services.RoleService;
import com.IncidentReport.web.Services.TicketService;
import com.IncidentReport.web.Services.UserService;

/**
 * Servlet implementation class CreateIMRD
 */
@WebServlet("/CreateIMRD")
public class CreateIMRD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateIMRD() {
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
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		
		
		if(user==null) {
			request.setAttribute("warning", "No have permission");
			displayPage(request, response, "/index.jsp");
		}
		else {
			String role = (String) session.getAttribute("role");
			if(!role.equals("Admin")) {
				request.setAttribute("warning", "No have permission");
				openIndex(request, response,"/index.jsp",user);
			}else {
				
				
				String roleN = request.getParameter("rolename");
				String deptN = request.getParameter("deptname");
				DepartmentService ds = new DepartmentService();
				RoleService rs = new RoleService();
				
				if(roleN != null) {
					rs = new RoleService();
					if(!rs.checkRole(roleN)) {
						Role newR = new Role();
						newR.setName(roleN);
						rs = new RoleService();
						rs.createRole(newR);
					}
				}
				
				if(deptN != null) {
					ds = new DepartmentService();
					if(!ds.checkDept(deptN)) {
						Department newD = new Department();
						newD.setName(deptN);
						ds = new DepartmentService();
						ds.createDept(newD);
					}
				}
				

				UserService us = new UserService();
				ds = new DepartmentService();
				rs = new RoleService();

				List<Role> roles = rs.allRoles();
				List<Department> departments = ds.allDepartments();
				
				List<User> users = us.allUsers();

				request.setAttribute("users", users);
				request.setAttribute("departments", departments);
				request.setAttribute("roles", roles);

				displayPage(request, response, "/users.jsp");
				
			}
		}
		
		
		
		
		
	}
	
	
	
	private void openIndex(HttpServletRequest request, HttpServletResponse response, String rPage, User user) throws ServletException, IOException {
		
		List<Ticket> tickets = new ArrayList<Ticket>();
		TicketService ts = new TicketService();
		
		tickets = ts.findUserTickets(user.getId());

		request.setAttribute("tickets", tickets);
		
		displayPage(request, response, rPage);
	}
	
	private void displayPage(HttpServletRequest request, HttpServletResponse response, String rPage)
			throws ServletException, IOException {
		RequestDispatcher reqDispatcher = getServletConfig().getServletContext()
				.getRequestDispatcher(rPage);
		reqDispatcher.forward(request, response);
	}

}
