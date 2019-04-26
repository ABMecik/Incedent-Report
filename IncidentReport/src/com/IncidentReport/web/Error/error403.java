package com.IncidentReport.web.Error;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class error403
 */
@WebServlet("/error403")
public class error403 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public error403() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		request.setAttribute("errordisc", "Forbidden: You don't have permission to access this directory.");
		request.setAttribute("errorcode0", "4");
		request.setAttribute("errorcode1", "0");
		request.setAttribute("errorcode2", "3");
		RequestDispatcher reqDispatcher = getServletConfig().getServletContext()
				.getRequestDispatcher("/error.jsp");
		reqDispatcher.forward(request, response);
	}
}
