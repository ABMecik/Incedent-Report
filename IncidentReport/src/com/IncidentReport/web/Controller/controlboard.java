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

import com.IncidentReport.web.Model.Role;
import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.TicketService;
import com.IncidentReport.web.Services.UserService;

/**
 * Servlet implementation class controlboard
 */
@WebServlet({ "/controlboard", "/control-board", "/ControlBoard", "/Control-Board" })
public class controlboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controlboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		
		if(user==null) {
			request.setAttribute("warning", "No have permission");
			displayPage(request, response, "/index.jsp");
		}
		else {
			String role = (String) session.getAttribute("role");
			if(role.equals("User")) {
				request.setAttribute("warning", "No have permission");
				openIndex(request, response,"/index.jsp",user);
			}else {
				
				if(role.equals("Front Desk")) {
					TicketService ts = new TicketService();
					UserService us = new UserService();
					List<Ticket> ftickets = ts.AllTickets();
					List<User> managers = us.findRoleList("Manager");

					request.setAttribute("managers", managers);
					request.setAttribute("ftickets", ftickets);
					displayPage(request, response, "/controlboard.jsp");
				}
				
				if(role.equals("Manager")) {
					
					TicketService ts = new TicketService();
					UserService us = new UserService();
					List<Ticket> mtickets = ts.managerReleated(user.getId());
					List<User> staffs = us.deptReleated(user.getDept().getName(), "Staff");
					
					request.setAttribute("staffs", staffs);
					request.setAttribute("mtickets", mtickets);
					displayPage(request, response, "/controlboard.jsp");
				}
				
				if(role.equals("Staff")) {
					

					TicketService ts = new TicketService();
					List<Ticket> stickets = ts.staffsTickets(user.getId());

					request.setAttribute("stickets", stickets);
					displayPage(request, response, "/controlboard.jsp");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
