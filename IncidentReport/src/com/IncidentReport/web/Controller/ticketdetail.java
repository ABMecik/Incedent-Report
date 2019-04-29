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

import com.IncidentReport.web.Model.Message;
import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.MessageService;
import com.IncidentReport.web.Services.TicketService;
import com.IncidentReport.web.Services.UserService;

/**
 * Servlet implementation class ticketdetail
 */
@WebServlet({ "/ticketdetail", "/ticket-detail", "/TicketDetail", "/Ticket-Detail" })
public class ticketdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ticketdetail() {
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
		String role = (String) session.getAttribute("role");
		
		if(user==null) {
			request.setAttribute("warning", "You are not authorized to do so.");
			displayPage(request, response, "/index.jsp");
		}
		else {
			
			if(role.equals("Principal Inspector")) {
				int ticketID = Integer.parseInt(request.getParameter("ticketID"));
				TicketService ts = new TicketService();
				Ticket ticket = ts.findById(ticketID);
				
				MessageService ms = new MessageService();
				List<Message> messages = ms.findRelatedMessages(ticket, user.getId());

				request.setAttribute("messages", ticket.getMessages());
				request.setAttribute("ticket", ticket);
				displayPage(request, response, "/ticket-detail.jsp");
				
			}
			else if(role.equals("User")) {
				int ticketID = Integer.parseInt(request.getParameter("ticketID"));
				TicketService ts = new TicketService();
				Ticket ticket = ts.findById(ticketID);
				
				MessageService ms = new MessageService();

				request.setAttribute("ticket", ticket);
				displayPage(request, response, "/ticket-detail.jsp");
			}else {
				int ticketID = Integer.parseInt(request.getParameter("ticketID"));
				TicketService ts = new TicketService();
				Ticket ticket = ts.findById(ticketID);
				
				MessageService ms = new MessageService();
				List<Message> messages = ms.findRelatedMessages(ticket, user.getId());

				request.setAttribute("messages", messages);
				request.setAttribute("ticket", ticket);
				displayPage(request, response, "/ticket-detail.jsp");
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
