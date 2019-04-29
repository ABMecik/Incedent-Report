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

import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.TicketPriority;
import com.IncidentReport.web.Model.TicketStatus;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.PriorityService;
import com.IncidentReport.web.Services.StatusService;
import com.IncidentReport.web.Services.TicketService;

/**
 * Servlet implementation class TicketReport
 */
@WebServlet("/TicketReport")
public class TicketReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketReport() {
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
			displayPage(request, response, "/index.jsp");
		}
		else {
			
			int ticketID = Integer.parseInt(request.getParameter("ticketID"));
			
			TicketService ts = new TicketService();
			Ticket ticket = ts.findById(ticketID);
			
			ts = new TicketService();
			
			String sSt = "Reported";
			int pSt = 11;
			
			PriorityService ps = new PriorityService();
			boolean pps = ps.checkIsValid(pSt);
			if(!pps) {
				ps = new PriorityService();
				ps.createNewPriority(pSt);
			}
			ps = new PriorityService();
			TicketPriority priority = ps.findPriority(pSt);
			
			
			StatusService ss = new StatusService();
			boolean ssi = ss.checkIsValid(sSt);
			if(!ssi) {
				ss = new StatusService();
				ss.createNewStatus(sSt,"");
			}
			ss = new StatusService();
			TicketStatus status = ss.findStatus(sSt);
			
			ts = new TicketService();
			
			ts.setStatus(ticketID, status);
			ts = new TicketService();
			ts.setPriority(ticketID, priority);
			
			
			request.setAttribute("info", "Ticket Reported.");
			
			openIndex(request, response, "/index.jsp", user);
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
