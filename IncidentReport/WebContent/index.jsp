<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.IncidentReport.web.Model.Role"%>
<%@page import="com.IncidentReport.web.Model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script class="jsbin"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/user_dashboard.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<%if (session.getAttribute("user") == null){%>
	
	
	<c:if test="${info != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror info">
						<strong>Info -</strong> ${info}
					</div>
				</div>
			</div>
		</div>
	</c:if>



	<c:if test="${warning != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror warning">
						<strong>Sorry -</strong> ${warning}
					</div>
				</div>
			</div>
		</div>
	</c:if>


	<c:if test="${error != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror danger">
						<strong>Error -</strong> ${error}
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${success != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror success">
						<strong>Success -</strong> ${success}
					</div>
				</div>
			</div>
		</div>
	</c:if>
	

	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-3" type="radio" name="tab" class="register"><label
				for="tab-3" class="tab">Register</label> <input id="tab-1"
				type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="for-pwd"><label for="tab-2"
				class="tab">Forgot Password</label>
			<div class="login-form">

				<div class="register-htm">

					<form action="Register" method="POST">

						<div class="group">
							<label class="label">Username</label> <input type="text"
								class="input" name="username">
						</div>

						<div class="group">
							<label class="label">Password</label> <input type="password"
								class="input" data-type="password" name="password">
						</div>

						<div class="group">
							<label class="label">Name</label> <input type="text"
								class="input" name="name">
						</div>

						<div class="group">
							<label class="label">Surname</label> <input type="text"
								class="input" name="surname">
						</div>

						<div class="group">
							<label class="label">Email</label> <input type="text"
								class="input" name="email">
						</div>

						<div class="group">
							<input type="submit" class="button" value="Register">
						</div>

					</form>



					<div class="hr"></div>

				</div>

				<div class="sign-in-htm">

					<form action="Login" method="POST">
						<div class="group">
							<label for="user" class="label">Username</label> <input id="user"
								type="text" class="input" name="username">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input id="pass"
								type="password" class="input" data-type="password"
								name="password">
						</div>
						<div class="group">
							<input type="submit" class="button" value="Login">
						</div>
					</form>

					<div class="hr"></div>
				</div>

				<div class="for-pwd-htm">
					<div class="group">
						<label for="user" class="label">Username or Email</label> <input
							id="user" type="text" class="input">
					</div>
					<div class="group">
						<input type="submit" class="button" value="Reset Password">
					</div>
					<div class="hr"></div>
				</div>

			</div>
		</div>
	</div>
	<%}else{ %>

	<nav class="main-navigation">
		<div class="navbar-header animated fadeInUp">
			<a class="navbar-brand" href="index">No More Incidents</a>
		</div>
		<ul class="nav-list">

			<%
    			if (session.getAttribute("role").equals("Front Desk") || session.getAttribute("role").equals("Manager")|| session.getAttribute("role").equals("Staff")) {
			%>
				<li class="nav-list-item"><a href="controlboard"
					class="nav-link">Controlboard</a>
				</li>

			<%} %>
			
			<%
    			if (session.getAttribute("role").equals("Admin")) {
			%>
				<li class="nav-list-item"><a href="users"
					class="nav-link">Users</a>
				</li>
				<li class="nav-list-item"><a href="dpanel"
					class="nav-link">Add</a>
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


	
	<c:if test="${info != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror info">
						<strong>Info -</strong> ${info}
					</div>
				</div>
			</div>
		</div>
	</c:if>



	<c:if test="${warning != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror warning">
						<strong>Sorry -</strong> ${warning}
					</div>
				</div>
			</div>
		</div>
	</c:if>


	<c:if test="${error != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror danger">
						<strong>Error -</strong> ${error}
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${success != null}">
		<div class="note" id="note">
			<div class="row">
				<div class="error-notice">
					<div class="oaerror success">
						<strong>Success -</strong> ${success}
					</div>
				</div>
			</div>
		</div>
	</c:if>
	


	<div class="login-wrap">
		<div class="login-html">
			<div class="container">
				<div class="row">
					<p></p>
					<h1>Your Tickets</h1>
					<div class="col-md-10 col-md-offset-1">
						<div class="panel panel-default panel-table">
							<div class="panel-heading">
								<div class="row">
									<div class="col col-xs-6">
										<h3></h3>
									</div>
								</div>
							</div>
							<table class="table">
								  <thead class="thead-light">
								    <tr >
								      <th scope="col" >ID</th>
								      <th scope="col">Title</th>
								      <th scope="col">Date Created</th>
								      <th scope="col">Status</th>
								      <th scope="col">Priority</th>
								      <th scope="col">Detail</th>
								      <th scope="col"><em class="fa fa-cog"></em></th>
						              <th scope="col"></th>
								    </tr>
						           </thead>
						        <tbody>
		        				 <tr >
								  <c:forEach var="ticket" items="${tickets}">
									<tr >
										<td class="hidden-xs" align="center">${ticket.getId()}</td>
										<td align="center">${ticket.getTitle()}</td>
										<td align="center">${ticket.getCreated_at()}</td>
										<td align="center">
											<div class="progress">
												<c:choose>
													<c:when test="${ticket.getStatus().getName()=='Waiting'}">
														<div data-percentage="25%" style="width: 25%;" class="progress-bar progress-bar-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
													</c:when>
													<c:when
														test="${ticket.getStatus().getName()=='Processing'}">
														<div data-percentage="50%" style="width: 50%;" class="progress-bar progress-bar-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
													</c:when>
													<c:when
														test="${ticket.getStatus().getName()=='Approved'}">
														<div data-percentage="75%" style="width: 75%;" class="progress-bar progress-bar-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
													</c:when>
													<c:when
														test="${ticket.getStatus().getName()=='Declined'}">
														<div data-percentage="0%" style="width: 0%;" class="progress-bar progress-bar-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
													</c:when>
													<c:when
														test="${ticket.getStatus().getName()=='Finished'}">
														<div data-percentage="100%" style="width: 100%;" class="progress-bar progress-bar-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
													</c:when>
													<c:when
														test="${ticket.getStatus().getName()=='closed'}">
														<div data-percentage="100%" style="width: 100%;" class="progress-bar progress-bar-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
													</c:when>
												</c:choose>
						                    </div>
										</td>
										<td align="center">H</td>
										<td align="center">
											<form action="TicketDetail" method="GET">
												<input type="hidden" , name="ticketID" value="${ticket.getId()}">
												<button type="submit" class="btn text-color" value="TicketDetail">
													<i class="fa fa-cog"></i>
												</button>
											</form>
										</td>
										<td align="center">
											<form action="TicketDelete" method="POST">
												<input type="hidden" , name="ticketID"
													value="${ticket.getId()}">
												<button type="submit" class="btn text-color"
													value="TicketDelete">
													<i class="fa fa-trash"></i>
												</button>
												
											</form>
										</td>
									</tr>
								</c:forEach>
					 		</tr>
						  </tbody>
					</div>
				</div>

			</div>
		</div>
	</div>



	<%} %>
</body>
</html>



<!---
fa-question -beklemede
fa-times -reddet
fa-check -kabul et
fa-exchange -islemde
fa-flag-checkered -bitti


<button type="submit" class="btn text-color" value="Status">
    <i class="fa fa-question"></i>
</button>


<button type="submit" class="btn text-color" value="Status">
    <i class="fa fa-times"></i>
</button>


<button type="submit" class="btn text-color" value="Status">
    <i class="fa fa-check"></i>
</button>


<button type="submit" class="btn text-color" value="Status">
    <i class="fa fa-exchange"></i>
</button>


<button type="submit" class="btn text-color" value="Status">
    <i class="fa fa-flag-checkered"></i>
</button>



--->