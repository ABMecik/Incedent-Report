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
 * Servlet implementation class UpdateTicket
 */
@WebServlet({ "/UpdateTicket", "/Update-Ticket", "/updateticket", "/update-ticket" })
public class UpdateTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTicket() {
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
				String updateType = request.getParameter("UpdateType");
				int ticketID = Integer.parseInt(request.getParameter("ticketID"));
				
				TicketService ts = new TicketService();
				Ticket ticket = ts.findById(ticketID);
				
				if(updateType.equals("uf")) {

					String sSt = request.getParameter("set-status");
					int pSt = Integer.parseInt(request.getParameter("set-priority"));
					String comment = request.getParameter("notes");
					int managerID = Integer.parseInt(request.getParameter("set-manager"));
					
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
					
					UserService us = new UserService();
					User manager = us.findById(managerID);
					
					Department department = manager.getDept();
					
					ts = new TicketService();
					
					ts.updateTicketSPMD(ticketID,status,priority,manager,department,user);
					ts = new TicketService();
					
					
					MessageService ms = new MessageService();
					Message newMS = new Message();
					ms.insert(user,manager,ticket,comment);
					
					callPage(request, response, role, user);
				}
				
				else if(updateType.equals("mf")) {
					
					String comment = request.getParameter("notes");
					int receiverID = Integer.parseInt(request.getParameter("set-staff"));
					String close = request.getParameter("close-ticket");
					
					if(close != null) {
						StatusService ss = new StatusService();
						boolean ssi = ss.checkIsValid("closed");
						if(!ssi) {
							ss = new StatusService();
							ss.createNewStatus("closed","");
						}
						ss = new StatusService();
						TicketStatus status = ss.findStatus("closed");
						
						ts = new TicketService();
						ts.setStatus(ticketID, status);
					}
					UserService us = new UserService();
					
					User reciver = us.findById(receiverID);
					
					MessageService ms = new MessageService();
					Message newMS = new Message();
					ms.insert(user,reciver,ticket,comment);
					
					if(reciver.getRole().getName().equals("Staff")) {
						ts = new TicketService();
						ts.assignStaff(reciver, ticketID);
					}
					
					callPage(request, response, role, user);
				}
			}
			
			
		}
		
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void callPage(HttpServletRequest request, HttpServletResponse response, String role,User user) throws ServletException, IOException {
		
		if(role.equals("Front Desk")) {
			TicketService ts = new TicketService();
			UserService us = new UserService();
			List<Ticket> ftickets = ts.AllTickets();
			List<User> managers = us.findRoleList("Manager");
			request.setAttribute("managers", managers);
			request.setAttribute("ftickets", ftickets);
			displayPage(request, response, "/controlboard.jsp");
			
		}else if (role.equals("Manager")) {
			TicketService ts = new TicketService();
			UserService us = new UserService();
			List<Ticket> mtickets = ts.managerReleated(user.getId());
			List<User> staffs = us.deptReleated(user.getDept().getName(), "staff");
			
			request.setAttribute("staffs", staffs);
			request.setAttribute("mtickets", mtickets);
			displayPage(request, response, "/controlboard.jsp");
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
