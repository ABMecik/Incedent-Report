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
<link rel="stylesheet" type="text/css" href="resources/css/dashboard.css">
<script src="resources/js/staff_open_image.js"></script>
</head>
<body>


	<nav class="main-navigation">
		<div class="navbar-header animated fadeInUp">
			<a class="navbar-brand" href="index">No More Incidents</a>
		</div>
		<ul class="nav-list">

			<%
				if (session.getAttribute("role").equals("Front Desk") || session.getAttribute("role").equals("Manager")) {
			%>
			<li class="nav-list-item"><a href="controlboard"
				class="nav-link">Controlboard</a></li>

			<%
				}
			%>

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
								<div class="row"></div>
							</div>
							
							<form action="SearchTicket" method="POST" class="form-inline">
								<div class="form-group mb-2">
									<select class="form-control" name="lStatus">
										<option value="-1"> ANY </option>
										<c:forEach var="status" items="${statuses}">
											<option value="${status.getId()}">${status.getName()}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group mb-2">
										<input type="text" class="form-control" name="lTitle" placeholder="Search by title" /> 
										<input type="hidden" name="searchType" value="fs">
										<input type="submit" value="SearchTicket" class="btn btn-primary" />
								</div>

							</form>
							
							
							<table class="table">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col"></th>
		      <th scope="col">ID</th>
		      <th scope="col">Title</th>
		      <th scope="col">Date Created</th>
		      <th scope="col">Detail</th>
		      <th scope="col">Status</th>
		      <th scope="col">Priority</th>
		      <th scope="col">Notes</th>
		      <th scope="col">Send To</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		         <tr >
                <c:forEach var="ticket" items="${ftickets}">
											<tr>
												<td align="center">
													<div class="sonar-wrapper">

														<!-- Color Control System -->
														<c:choose>
															<c:when test="${ticket.getStatus().getName()=='Waiting'}">
																<div class="sonar-emitter"
																	style="background-color: white">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Processing'}">
																<div class="sonar-emitter"
																	style="background-color: lightgreen">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Approved'}">
																<div class="sonar-emitter"
																	style="background-color: yellow">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Declined'}">
																<div class="sonar-emitter" style="background-color: red">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Finished'}">
																<div class="sonar-emitter"
																	style="background-color: blue">
															</c:when>
														</c:choose>

														<!-- Color Control System -->
														<div class="sonar-wave"></div>
													</div>
													</div>
												</td>
												<td class="hidden-xs" align="center">${ticket.getId()}</td>
												<td align="center">${ticket.getTitle()}</td>
												<td align="center">${ticket.getCreated_at()}</td>
												<td align="center">
													<form action="TicketDetail" method="GET">
														<input type="hidden" , name="ticketID"
															value="${ticket.getId()}">
														<button type="submit" class="btn text-color"
															value="TicketDetail">
															<i class="fa fa-cog"></i>
														</button>
													</form>
												</td>
												<form action="UpdateTicket" method="POST">
													<input type="hidden" name="UpdateType" value="uf">
													<input type="hidden" name="ticketID"
														value="${ticket.getId()}">
													<td align="center">
														<div class="container">
															<div class="row">
																<div class="form-group mb-2">
																	<select class="form-control" name="set-status" style="width: 100px">
																		<option selected="${ticket.getStatus().getName()}"
																			value="${ticket.getStatus().getName()}">${ticket.getStatus().getName()}</option>
																		<option value="Waiting">Waiting</option>
																		<option value="Processing">Processing</option>
																		<option value="Approved">Approved</option>
																		<option value="Declined">Declined</option>
																		<option value="Finished">Finished</option>
																	</select>
																</div>
															</div>
														</div>
													</td>
													<td align="center">
														<div class="container">
															<div class="row">
																<div class="form-group mb-2">
																	<select class="form-control" name="set-priority" style="width: 50px">
																		<option
																			selected="${ticket.getPriority().getImportance()}"
																			value="${ticket.getPriority().getImportance()}">${ticket.getPriority().getImportance()}</option>
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
															<textarea class="form-control" rows="2" id="comment"
																name="notes" style="width: 200px"></textarea>
														</div>
													</td>
													<td align="center">
														<div class="container">
															<div class="row">
																<div class="form-group mb-2"">
																	<select class="form-control" name="set-manager" style="width: 100px">
																		<option value="" selected disabled hidden>Choose</option>
																		<c:forEach var="manager" items="${managers}">
																			<option value="${manager.getId()}">${manager.getName()}
																				${manager.getSurname()} -
																				${manager.getDept().getName()}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
													</td>
													<td align="center">
														<div class="group">
															<input class="btn btn-primary" type="submit" value="UpdateTicket">
														</div>
													</td>
												</form>
											</tr>
										</c:forEach>
            </tr>
		  </tbody>
		</table>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<%
		} else if (session.getAttribute("role").equals("Manager")) {
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
								<div class="row"></div>
							</div>



							<form action="SearchTicket" method="POST" class="form-inline">
								<div class="form-group mb-2">
									<select class="form-control" name="lStatus">
										<option value="-1"> ANY </option>
										<c:forEach var="status" items="${statuses}">
											<option value="${status.getId()}">${status.getName()}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group mb-2">
										<input type="text" class="form-control" name="lTitle" placeholder="Search by title" /> 
										<input type="hidden" name="searchType" value="ms">
										<input type="submit" value="SearchTicket" class="btn btn-primary" />
								</div>

							</form>

							<table class="table">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col"></th>
		      <th scope="col">ID</th>
		      <th scope="col">Title</th>
		      <th scope="col">Date Created</th>
		      <th scope="col">Detail</th>
		      <th scope="col">Notes</th>
		      <th scope="col">Close</th>
		      <th scope="col">Send To</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
			<c:forEach var="ticket" items="${mtickets}">
				<tr>
					<td align="center">
						<div class="sonar-wrapper">

							<!-- Color Control System -->
							<c:choose>
								<c:when test="${ticket.getStatus().getName()=='Waiting'}">
									<div class="sonar-emitter"
										style="background-color: white">
								</c:when>
								<c:when
									test="${ticket.getStatus().getName()=='Processing'}">
									<div class="sonar-emitter"
										style="background-color: lightgreen">
								</c:when>
								<c:when
									test="${ticket.getStatus().getName()=='Approved'}">
									<div class="sonar-emitter"
										style="background-color: yellow">
								</c:when>
								<c:when
									test="${ticket.getStatus().getName()=='Declined'}">
									<div class="sonar-emitter" style="background-color: red">
								</c:when>
								<c:when
									test="${ticket.getStatus().getName()=='Finished'}">
									<div class="sonar-emitter"
										style="background-color: blue">
								</c:when>
							</c:choose>

							<!-- Color Control System -->


							<div class="sonar-wave"></div>
						</div>
						</div>
					</td>
					<td class="hidden-xs" align="center">${ticket.getId()}</td>
					<td align="center">${ticket.getTitle()}</td>
					<td align="center">${ticket.getCreated_at()}</td>
					<td align="center">
						<form action="TicketDetail" method="GET">
							<input type="hidden" , name="ticketID"
								value="${ticket.getId()}">
							<button type="submit" class="btn text-color"
								value="TicketDetail">
								<i class="fa fa-cog"></i>
							</button>
						</form>
					</td>

					<form action="UpdateTicket" method="POST">
						<input type="hidden" name="UpdateType" value="mf">
						<input type="hidden" name="ticketID"
							value="${ticket.getId()}">
						<td align="center">
							<div class="form-group" id="add-note" name="notes">
								<textarea class="form-control" rows="2" id="comment"
									name="notes" style="width: 200px"></textarea>
							</div>
						</td>
						<td align="center">
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									name="close-ticket">
							</div>
						</td>
						<td align="center">
							<div class="container">
								<div class="row">
									<div class="form-group mb-2">
										<select class="form-control" name="set-staff" style="width: 100px">
											<option value="" selected disabled hidden>Choose</option>
											<option value="${ticket.getFrontdesk().getId()}">Front
												Desk</option>
											<c:forEach var="staff" items="${staffs}">
												<option value="${staff.getId()}">${staff.getName()}
													${staff.getSurname()}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</td>
						
						<td align="center">
							<div class="group">
								<input class="btn btn-primary" type="submit" value="UpdateTicket">
							</div>
						</td>
					</form>
				</tr>
			</c:forEach>
		</tbody>
		</table>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>



	<%
		}else if (session.getAttribute("role").equals("Staff")) {
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
                        
							<form action="SearchTicket" method="POST" class="form-inline">
								<div class="form-group mb-2">
									<select class="form-control" name="lStatus">
										<option value="-1"> ANY </option>
										<c:forEach var="status" items="${statuses}">
											<option value="${status.getId()}">${status.getName()}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group mb-2">
										<input type="text" class="form-control" name="lTitle" placeholder="Search by title" /> 
										<input type="hidden" name="searchType" value="ss">
										<input type="submit" value="SearchTicket" class="btn btn-primary" />
								</div>

							</form>
                        
                        
                      </div>
                      <div class="panel-body" >
                           		<table class="table">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col"></th>
		      <th scope="col">ID</th>
		      <th scope="col">Title</th>
		      <th scope="col">Date Created</th>
		      <th scope="col">Detail</th>
		      <th scope="col">Notes</th>
		      <th scope="col">Photo</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>

										<c:forEach var="ticket" items="${stickets}">
                              <tr>
                                <td align="center">
                                    <div class="sonar-wrapper">
                                        
                                        
                                        <!-- Color Control System -->
														<c:choose>
															<c:when test="${ticket.getStatus().getName()=='Waiting'}">
																<div class="sonar-emitter"
																	style="background-color: white">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Processing'}">
																<div class="sonar-emitter"
																	style="background-color: lightgreen">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Approved'}">
																<div class="sonar-emitter"
																	style="background-color: yellow">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Declined'}">
																<div class="sonar-emitter" style="background-color: red">
															</c:when>
															<c:when
																test="${ticket.getStatus().getName()=='Finished'}">
																<div class="sonar-emitter"
																	style="background-color: blue">
															</c:when>
														</c:choose>

														<!-- Color Control System -->
                                        
                                        <div class="sonar-wave"></div>
                                      </div>
                                    </div>  
                                </td>
                                <td class="hidden-xs" align="center">${ticket.getId()}</td>
								<td align="center">${ticket.getTitle()}</td>
								<td align="center">${ticket.getCreated_at()}</td>
                                <td align="center">
                                
                                    <form action="TicketDetail" method="GET">
										<input type="hidden" , name="ticketID"
											value="${ticket.getId()}">
										<button type="submit" class="btn text-color"
											value="TicketDetail">
											<i class="fa fa-cog"></i>
										</button>
									</form>
													
                                  <form action="UpdateTicket" method="POST"  enctype='multipart/form-data'>
                                  <input type="hidden" name="ticketID" value="${ticket.getId()}">
                                  <input type="hidden" name="UpdateType" value="tsf">
                                  <td align="center">
									<div class="form-group" id="add-note" name="notes">
										<textarea class="form-control" rows="2" id="comment"
											name="notes" style="width: 200px"></textarea>
									</div>
								</td>
                                  <td align="center">
                                    <div class="container">
                                        <div class="row">
                                            <div>
                                        <label class=newbtn>
                                            <input id="pic" class='pis' onchange="readURL(this);" name="staff-image" type="file" >
                                        </label>
                                        </div>
                                        </div>
                                    </div>
                                  </td>
                                <td align="center">
							<div class="group">
								<input class="btn btn-primary" type="submit" value="UpdateTicket">
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
