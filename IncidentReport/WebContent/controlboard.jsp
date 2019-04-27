<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.IncidentReport.web.Model.Role"%>
<%@page import="com.IncidentReport.web.Model.User"%>
<%@page import="com.IncidentReport.web.Model.Ticket"%>
<%@page import="com.IncidentReport.web.Model.Department"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<head>
	<title>Welcome</title>
	<link rel="stylesheet" type="text/css" href="resources/css/dashboard.css">
</head>
<body>

   
	<nav class="main-navigation">
		<div class="navbar-header animated fadeInUp">
			<a class="navbar-brand" href="index">No More Incidents</a>
		</div>
		<ul class="nav-list">

			<%
    			if (session.getAttribute("role").equals("Front Desk")) {
			%>
				<li class="nav-list-item"><a href="controlboard"
					class="nav-link">Controlboard</a>
				</li>

			<%} %>

			<li class="nav-list-item"><a href="create-ticket"
				class="nav-link">Create Ticket</a></li>
			<li class="nav-list-item"><a href="index" class="nav-link">Dashboard</a>
			</li>
			<li class="nav-list-item">
				<form class="form-inline" action="Logout">
					<button type="submit" class="btn text-color" value="Logout">
						<i class="fa fa-sign-out"></i>
					</button>
				</form>
			</li>
		</ul>
	</nav>
	
<%
    			if (session.getAttribute("role").equals("Front Desk")) {
			%>

    <div class="login-wrap" style="margin-left: 1px">
		<div class="login-html">
	        <div class="container">
	            <div class="row">
	            <p></p>
	            <h1>Manage Tickets</h1>
	                <div class="col-md-10 col-md-offset-1">
	                    <div class="panel panel-default panel-table">
	                      <div class="panel-heading">
	                        <div class="row">
	                        </div>
	                      </div>
	                      <div class="panel-body" >
	                        <table class="table table-striped table-bordered table-list" cellspacing="40">
	                          <thead>
	                            <tr>
	                                <th></th>
	                                <th class="hidden-xs">ID</th>
	                                <th>Title</th>
	                                <th>Date Created</th>
	                                <th>Detail</th>
	                                <th>Status</th>
	                                <th>Priority</th>
	                                <th>Notes</th>
	                                <th>Send To</th>
	                            </tr> 
	                          </thead>
	                          <tbody>
	                          
	                          <c:forEach var="ticket" items="${tickets}">
		                              <tr>
		                                <td align="center">
		                                    <div class="sonar-wrapper">
		                                    <c:choose>
												<c:when test="${ticket.getStatus().getName()=='Waiting'}">
		                                        	<div class="sonar-emitter" style="background-color: yellow">
												</c:when>
											</c:choose>
		                                        <div class="sonar-wave"></div>
		                                      </div>
		                                    </div>  
		                                </td>
		                                <td class="hidden-xs" align="center">1</td>
		                                <td align="center">${ticket.getTitle()}</td>
		                                <td align="center">${ticket.getCreated_at()}</td>
		                                <td align="center">
		                                    <form action="TicketDetail" method="GET">
														<input type="hidden" , name="ticketID" value="${ticket.getId()}">
														<button type="submit" class="btn text-color" value="TicketDetail">
															<i class="fa fa-cog"></i>
														</button>
													</form>
		                                </td>
		                            <form action="UpdateTicket" method="POST">
		                            <input type="hidden" name="ticketID" value="${ticket.getId()}">
		                                <td align="center">
		                                    <div class="container">
		                                    <div class="row">
		                                        <div class="col-sm-4">
		                                        <select class="form-control" name="set-status">
		                                            <option value="" selected disabled hidden>Choose</option>
		                                            <option value="volvo">Waiting</option>
		                                            <option value="saab">Processing</option>
		                                            <option value="mercedes">Approved</option>
		                                            <option value="audi">Declined</option>
		                                            <option value="audi">Finished</option>
		                                        </select>
		                                        </div>
		                                    </div>
		                                    </div>
		                                </td> 
		                                <td align="center">
		                                  <div class="container">
		                                    <div class="row">
		                                        <div class="col-sm-4">
		                                        <select class="form-control" name="set-priority" >
		                                            <option value="" selected disabled hidden>Choose</option>
		                                            <option value="1">1</option>
		                                            <option value="2">2</option>
		                                            <option value="3">3</option>
		                                            <option value="4">4</option>
		                                            <option value="5">5</option>
		                                            <option value="6">6</option>
		                                            <option value="7">7</option>
		                                            <option value="8">8</option>
		                                            <option value="9">9</option>
		                                            <option value="10">10</option>
		                                        </select>
		                                        </div>
		                                    </div>
		                                    </div>
		                                  </td>
		                                  <td align="center">
		                                    <div class="form-group" id="add-note" name="notes">
		                                      <textarea class="form-control" rows="5" id="comment"></textarea>
		                                    </div>
		                                  </td>
		                                  <td align="center">
		                                        <div class="container">
		                                            <div class="row">
		                                                <div class="col-sm-4">
		                                                    <select class="form-control" name="set-manager">
		                                                        <option value="" selected disabled hidden>Choose</option>
		                                                        <c:forEach var="manager" items="${managers}">
			                                                        <option value="${manager.getId()}">${manager.getName()} ${manager.getSurname()} - ${manager.getDept().getName()}</option>
		                                                        </c:forEach>
		                                                    </select>
		                                                </div>
		                                            </div>
		                                        </div>
		                                  </td>
		                                <td align="center">
		                                    <div class="group" >
		                                        <input type="submit" class="button" id="bttn-create" value="UpdateTicket">
		                                    </div>  
		                                </td>
		                                </form> 
		                              </tr>
	                            </c:forEach>
	                                               
	                          </tbody>
	                        </table>
	                      </div>
	        </div></div></div>
	         
			</div>
		</div>
	</div>
<%} %>
</body>
</html>
