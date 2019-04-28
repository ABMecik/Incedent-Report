<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.IncidentReport.web.Model.Role"%>
<%@page import="com.IncidentReport.web.Model.User"%>
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
	<link rel="stylesheet" type="text/css" href="resources/css/user.css">
    <script src="staff_open_image.js"></script>
</head>
<body>

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
    
    
    <div class="login-wrap" style="margin-left: 1px">
	<div class="login-html">
        <div class="container">
            <div class="row">
            <p></p>
            <h1>Manage System Users</h1>
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
                                <th class="hidden-xs">ID</th>
                                <th>Name</th>
                                <th>Department</th>
                                <th>Role</th>
                            </tr> 
                          </thead>
                          <tbody>
                            <form action="SetUser" method="POST">
                              <tr>
                                <td class="hidden-xs" align="center">1</td>
                                <td align="center">Artun Burak Mecik</td>                                    
                                  <td align="center">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <select class="form-control" name="set-user-department">
                                                        <option value="" selected disabled hidden>Choose</option>
                                                        <option value="IT">IT</option>
                                                        <option value="HR">HR</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                  </td>
                                <td align="center">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <select class="form-control" name="set-user-role">
                                                        <option value="" selected disabled hidden>Choose</option>
                                                        <option value="SE">Software Developer</option>
                                                        <option value="R">Recruiter</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                  </td>
                                  <td align="center">
                                    <div class="group" >
                                        <input type="submit" class="button" id="bttn-set-user" value="Set User">
                                    </div>  
                                </td>
                              </tr>
                            </form>                    
                          </tbody>
                        </table>
                      </div>
        </div></div></div>
         
		</div>
	</div>
</div>
  
</body>
</html>
