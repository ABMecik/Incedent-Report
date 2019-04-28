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
import com.IncidentReport.web.Model.Message;
import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.TicketPriority;
import com.IncidentReport.web.Model.TicketStatus;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.MessageService;
import com.IncidentReport.web.Services.PriorityService;
import com.IncidentReport.web.Services.StatusService;
import com.IncidentReport.web.Services.TicketService;
import com.IncidentReport.web.Services.UserService;

/**
 * Servlet implementation class SearchTicket
 */
@WebServlet({ "/SearchTicket", "/Search-Ticket", "/searchticket", "/search-ticket" })
public class SearchTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTicket() {
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
			if(role.equals("User")) {
				request.setAttribute("warning", "No have permission");
				displayPage(request, response, "/index.jsp");
			}else {
				String searchType = request.getParameter("searchType");
				String sTitle = request.getParameter("lTitle");
				String sStatus = request.getParameter("lStatus");
				
				if(searchType.equals("fs")) {
					
					List<Ticket> ftickets;
					
					
					UserService us = new UserService();
					List<User> managers = us.findRoleList("Manager");
					
					TicketService ts = new TicketService();
					if(sTitle != null && sStatus != null) {
						ftickets = ts.findByStatusAndLikeTitle(sTitle,sStatus);
					}
					else if(sTitle == null && sStatus != null) {
						ftickets = ts.findByStatus(sStatus);
					}
					else if(sTitle != null && sStatus == null) {
						ftickets = ts.findLikeTitle(sTitle);
					}else {
						ftickets = ts.AllTickets();
					}
					
					request.setAttribute("managers", managers);
					request.setAttribute("ftickets", ftickets);
					displayPage(request, response, "/controlboard.jsp");

				}
				
				else if(searchType.equals("ms")) {
					
					UserService us = new UserService();
					List<User> staffs = us.deptReleated(user.getDept().getName(), "staff");
					
					TicketService ts = new TicketService();
					List<Ticket> mtickets;
					
					if(sTitle != null && sStatus != null) {
						List<Ticket> allTickets = ts.findByStatusAndLikeTitle(sTitle,sStatus);
						ts = new TicketService();
						mtickets = ts.deptReleatedSelection(user.getDept().getName(), allTickets);
					}
					else if(sTitle == null && sStatus != null) {
						List<Ticket> allTickets = ts.findByStatus(sStatus);
						ts = new TicketService();
						mtickets = ts.deptReleatedSelection(user.getDept().getName(), allTickets);
					}
					else if(sTitle != null && sStatus == null) {
						List<Ticket> allTickets = ts.findLikeTitle(sTitle);
						ts = new TicketService();
						mtickets = ts.deptReleatedSelection(user.getDept().getName(), allTickets);
					}else {
						mtickets = ts.managerReleated(user.getId());
					}
					
					
					request.setAttribute("staffs", staffs);
					request.setAttribute("mtickets", mtickets);
					displayPage(request, response, "/controlboard.jsp");

				}
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