package com.IncidentReport.web.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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


/**
 * Servlet implementation class CreateTicket
 */
@WebServlet({ "/CreateTicket", "/Create-Ticket", "/create-ticket", "/createticket" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class CreateTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String rPage = "/index.jsp";
		if(session.getAttribute("user")==null) {
			displayPage(request, response, "/index.jsp");
		}
		else {

			displayPage(request, response, "/create-ticket.jsp");
		}
		
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
		
		
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		
		String fileName = "" + sd.format(date).toString();
		
		String appPath = request.getServletContext().getRealPath("");
		
		String imgPath = "uploads" + File.separator + "images" + File.separator + "evidance" + File.separator;
		
		String savePath = (appPath +imgPath + fileName.trim() + part.getSubmittedFileName().trim());
		
		String dbPath = imgPath + fileName.trim() + part.getSubmittedFileName().trim();
		
		File file = new File(savePath);
		file.getParentFile().mkdirs();
	
		
		boolean isAnonim = false;
		if(anonim != null) {
			if(anonim.equals("on")) {
				isAnonim = true;
			}
		}
		
		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			
			TicketService ts = new TicketService();
			Ticket ticket = new Ticket(title,decription,location,isAnonim,dbPath);
			ticket.setCreated_at(date);
			boolean s = ts.createNewTicket(ticket);
			
			if(s) {
				
				part.write(savePath);
				System.out.println(savePath);
				

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
		request.setAttribute("success", "Series added");
		openIndex(request, response, rPage, user);
		
	}


}
