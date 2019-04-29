package com.IncidentReport.web.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.TicketStatus;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.StatusService;
import com.IncidentReport.web.Services.TicketService;
import com.IncidentReport.web.Services.UserService;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Cookie userName = new Cookie("userNameCookie", request.getParameter("username"));
		   response.addCookie( userName );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		UserService us = new UserService();
		

		User user = us.Login(username, password);
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("role", user.getRole().getName());
			
			String role = user.getRole().getName();
			
			if(role.equals("User") || role.equals("Admin")) {
				openIndex(request, response, "/index.jsp", user);
			}else {
				
				StatusService ssTS = new StatusService();
				List<TicketStatus> statuses = ssTS.allStatuses();
				List<TicketStatus> sstatuses = null;
				
				if(!role.equals("Principal Inspector")) {
					ssTS = new StatusService();
					sstatuses = ssTS.removeReportedFromList(statuses);
				}else {
					sstatuses=statuses;
				}
				request.setAttribute("statuses", sstatuses);
				
				if(role.equals("Front Desk")) {
					TicketService ts = new TicketService();
					us = new UserService();
					List<Ticket> ftickets = ts.AllTickets();
					List<User> managers = us.findRoleList("Manager");

					
					ts = new TicketService();
					List<Ticket> fftickets = ts.removeReportedsFromList(ftickets);
					
					request.setAttribute("managers", managers);
					request.setAttribute("ftickets", fftickets);
					displayPage(request, response, "/controlboard.jsp");
				}
				
				if(role.equals("Manager")) {
					
					TicketService ts = new TicketService();
					us = new UserService();
					List<Ticket> mtickets = ts.managerReleated(user.getId());
					List<User> staffs = us.deptReleated(user.getDept().getName(), "Staff");
					
					ts = new TicketService();
					List<Ticket> fftickets = ts.removeReportedsFromList(mtickets);
					
					request.setAttribute("staffs", staffs);
					request.setAttribute("mtickets", fftickets);
					displayPage(request, response, "/controlboard.jsp");
				}
				
				if(role.equals("Staff")) {
					

					TicketService ts = new TicketService();
					List<Ticket> stickets = ts.staffsTickets(user.getId());
					
					ts = new TicketService();
					List<Ticket> fftickets = ts.removeReportedsFromList(stickets);

					request.setAttribute("stickets", fftickets);
					displayPage(request, response, "/controlboard.jsp");
				}
				
				if(role.equals("Principal Inspector")) {

					TicketService ts = new TicketService();
					List<Ticket> atickets = ts.AllTickets();
					us = new UserService();
					List<User> managers = us.findRoleList("Manager");

					request.setAttribute("managers", managers);
					request.setAttribute("atickets", atickets);
					displayPage(request, response, "/controlboard.jsp");
				}
			}

		}else {
			
			
			request.setAttribute("warning", "Invalid username or password.");
			displayPage(request, response, "/index.jsp");
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
