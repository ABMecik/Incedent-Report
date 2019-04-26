package com.IncidentReport.web.Error;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class error404
 */
@WebServlet("/error404")
public class error404 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public error404() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setAttribute("errordisc", "The page you are looking for was not found.");
		request.setAttribute("errorcode0", "4");
		request.setAttribute("errorcode1", "0");
		request.setAttribute("errorcode2", "4");
		RequestDispatcher reqDispatcher = getServletConfig().getServletContext()
				.getRequestDispatcher("/error.jsp");
		reqDispatcher.forward(request, response);
	}
}
