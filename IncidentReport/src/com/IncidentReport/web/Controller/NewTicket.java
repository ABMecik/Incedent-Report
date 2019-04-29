package com.IncidentReport.web.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.TicketPriority;
import com.IncidentReport.web.Model.TicketStatus;
import com.IncidentReport.web.Model.User;
import com.IncidentReport.web.Services.PriorityService;
import com.IncidentReport.web.Services.StatusService;
import com.IncidentReport.web.Services.TicketService;
import com.IncidentReport.web.Uploader.UploadImage;

/**
 * Servlet implementation class NewTicket
 */
@WebServlet({ "/NewTicket", "/newticket", "/New-Ticket", "/new-ticket" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class NewTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTicket() {
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
		
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		
		
		String title = request.getParameter("title");
		String decription = request.getParameter("decription");
		String location = request.getParameter("location");
		int priority = Integer.parseInt(request.getParameter("priority"));
		String anonim = request.getParameter("anonim");
		Part part = request.getPart("photo");
		
		
		
		
		Date date = new Date();
		
		
		boolean isAnonim = false;
		if(anonim != null) {
			if(anonim.equals("on")) {
				isAnonim = true;
			}
		}
		
		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {

			String dbPath = null;
			if(part != null) {
				String appPath = request.getServletContext().getRealPath("");
				UploadImage UI = new UploadImage();
				dbPath = UI.UploadNewImage(part, date, appPath);
			}
			else {
				dbPath = "/resources/image/img/processing.png";
			}
			
			TicketService ts = new TicketService();
			Ticket ticket = new Ticket(title,decription,location,isAnonim,dbPath);
			ticket.setCreated_at(date);
			boolean s = ts.createNewTicket(ticket);
			
			if(s) {
				ts = new TicketService();
				
				
				ticket = ts.findTicket(title,date);
				
				PriorityService ps = new PriorityService();
				boolean pps = ps.checkIsValid(priority);
				if(!pps) {
					ps = new PriorityService();
					ps.createNewPriority(priority);
				}
				ps = new PriorityService();
				TicketPriority ttp = ps.findPriority(priority);
				
				StatusService ss = new StatusService();
				boolean ssi = ss.checkIsValid("Waiting");
				if(!ssi) {
					ss = new StatusService();
					ss.createNewStatus("Waiting","Waiting for fronthand's response");
				}
				ss = new StatusService();
				TicketStatus tts = ss.findStatus("Waiting");
				
				
				ts = new TicketService();
				
				System.out.println("ticket : " + ticket.getId());
				System.out.println("user : " + user.getId());
				System.out.println("Status: " + tts.getId());
				System.out.println("Priority : " + ttp.getId());

				ts.setTicketFKeys(ticket.getId(), user.getId(), tts.getId(), ttp.getId());

				int timeX = 14-priority;
				ts = new TicketService();
				ts.setTimeOut(ticket.getId(), timeX);

				forward(request, response, "/index.jsp", user);
			}else {
				
				openIndex(request, response, "/index.jsp", user);
			}
		}else {
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

	
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String rPage, User user) throws ServletException, IOException {
		request.setAttribute("success", "Ticket Created.");
		openIndex(request, response, rPage, user);
		
	}

}

