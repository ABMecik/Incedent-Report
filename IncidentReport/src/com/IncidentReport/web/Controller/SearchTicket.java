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
			request.setAttribute("warning", "You are not authorized to do so.");
			displayPage(request, response, "/index.jsp");
		}
		else {
			String role = (String) session.getAttribute("role");
			if(role.equals("User")) {
				request.setAttribute("warning", "You are not authorized to do so.");
				openIndex(request, response,"/index.jsp",user);
			}else {
				String searchType = request.getParameter("searchType");
				String sTitle = request.getParameter("lTitle");
				int sStatus = Integer.parseInt(request.getParameter("lStatus"));
				
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
				
				if(searchType.equals("pps")) {
					
					List<Ticket> atickets;
					
					
					UserService us = new UserService();
					List<User> managers = us.findRoleList("Manager");
					
					TicketService ts = new TicketService();
					
					if(sTitle != null && sStatus != -1) {
						atickets = ts.findByStatusAndLikeTitle(sTitle,sStatus);
					}
					else if(sTitle == null && sStatus != -1) {
						atickets = ts.findByStatus(sStatus);
					}
					else if(sTitle != null && sStatus == -1) {
						atickets = ts.findLikeTitle(sTitle);
					}else {
						atickets = ts.AllTickets();
					}
					
					request.setAttribute("managers", managers);
					request.setAttribute("atickets", atickets);
					displayPage(request, response, "/controlboard.jsp");

				}
				
				if(searchType.equals("fs")) {
					
					List<Ticket> ftickets;
					
					
					UserService us = new UserService();
					List<User> managers = us.findRoleList("Manager");
					
					TicketService ts = new TicketService();
					
					if(sTitle != null && sStatus != -1) {
						ftickets = ts.findByStatusAndLikeTitle(sTitle,sStatus);
					}
					else if(sTitle == null && sStatus != -1) {
						ftickets = ts.findByStatus(sStatus);
					}
					else if(sTitle != null && sStatus == -1) {
						ftickets = ts.findLikeTitle(sTitle);
					}else {
						ftickets = ts.AllTickets();
					}
					
					
					ts = new TicketService();
					List<Ticket> fftickets = ts.removeReportedsFromList(ftickets);
					
					request.setAttribute("managers", managers);
					request.setAttribute("ftickets", fftickets);
					displayPage(request, response, "/controlboard.jsp");

				}
				
				else if(searchType.equals("ms")) {
					
					UserService us = new UserService();
					List<User> staffs = us.deptReleated(user.getDept().getName(), "staff");
					
					TicketService ts = new TicketService();
					List<Ticket> mtickets;
					
					if(sTitle != null && sStatus != -1) {
						List<Ticket> allTickets = ts.findByStatusAndLikeTitle(sTitle,sStatus);
						ts = new TicketService();
						mtickets = ts.deptReleatedSelection(user.getDept().getName(), allTickets);
					}
					else if(sTitle == null && sStatus != -1) {
						List<Ticket> allTickets = ts.findByStatus(sStatus);
						ts = new TicketService();
						mtickets = ts.deptReleatedSelection(user.getDept().getName(), allTickets);
					}
					else if(sTitle != null && sStatus == -1) {
						List<Ticket> allTickets = ts.findLikeTitle(sTitle);
						ts = new TicketService();
						mtickets = ts.deptReleatedSelection(user.getDept().getName(), allTickets);
					}else {
						mtickets = ts.managerReleated(user.getId());
					}
					
					ts = new TicketService();
					List<Ticket> fftickets = ts.removeReportedsFromList(mtickets);
					
					request.setAttribute("staffs", staffs);
					request.setAttribute("mtickets", fftickets);
					displayPage(request, response, "/controlboard.jsp");

				}
				
				else if(searchType.equals("ss")) {
					

					
					TicketService ts = new TicketService();
					List<Ticket> stickets;
					
					if(sTitle != null && sStatus != -1) {
						List<Ticket> allTickets = ts.findByStatusAndLikeTitle(sTitle,sStatus);
						ts = new TicketService();
						stickets = ts.staffReleatedSelection(user.getId(), allTickets);
					}
					else if(sTitle == null && sStatus != -1) {
						List<Ticket> allTickets = ts.findByStatus(sStatus);
						ts = new TicketService();
						stickets = ts.staffReleatedSelection(user.getId(), allTickets);
					}
					else if(sTitle != null && sStatus == -1) {
						List<Ticket> allTickets = ts.findLikeTitle(sTitle);
						ts = new TicketService();
						stickets = ts.staffReleatedSelection(user.getId(), allTickets);
					}else {
						stickets = ts.staffsTickets(user.getId());;
					}
					
					ts = new TicketService();
					List<Ticket> fftickets = ts.removeReportedsFromList(stickets);
;
					request.setAttribute("stickets", fftickets);
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
