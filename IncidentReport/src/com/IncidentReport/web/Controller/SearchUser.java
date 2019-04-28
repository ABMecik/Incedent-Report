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
 * Servlet implementation class SearchUser
 */
@WebServlet({ "/SearchUser", "/Search-User", "/searchuser", "/search-user" })
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUser() {
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
				String suNS = request.getParameter("suNS");
				int suR = Integer.parseInt(request.getParameter("suRole"));
				int suD = Integer.parseInt(request.getParameter("suDepartment"));
				
				UserService us = new UserService();
					
				List<User> users;
				
				TicketService ts = new TicketService();
				
				if(suNS != null && suR == -1 && suD == -1) {
					users = us.findByName(suNS, suNS);
					System.out.println("1");
				}else if(suNS != null && suR != -1 && suD == -1){
					users = us.findByNameAndRole(suNS, suR);
					System.out.println("2");
				}else if(suNS == null && suR != -1 && suD == -1){
					users = us.findByRole(suR);
					System.out.println("3");
				}else if(suNS == null && suR == -1 && suD != -1){
					users = us.findByDepartment(suD);
					System.out.println("4");
				}else if(suNS == null && suR != -1 && suD != -1){
					users = us.findByDepartmentAndRole(suR,suD);
					System.out.println("5");
				}else if(suNS != null && suR == -1 && suD != -1){
					users = us.findByNameAndDepartment(suNS,suD);
					System.out.println("6");
				}else if(suNS != null && suR != -1 && suD != -1){
					users = us.findByNameDepartmentAndRole(suNS,suD,suR);
					System.out.println("7");
				}else {
					users = us.allUsers();
				}
				
				
				
				DepartmentService ds = new DepartmentService();
				RoleService rs = new RoleService();

				List<Role> roles = rs.allRoles();
				List<Department> departments = ds.allDepartments();


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
