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
	<link rel="stylesheet" type="text/css" href="resources/css/dpanel.css">
    <script src="staff_open_image.js"></script>
</head>
<body>

    <nav class="main-navigation">
        <div class="navbar-header animated fadeInUp">
            <a class="navbar-brand" href="#">No More Incidents</a>
        </div>
        <ul class="nav-list">
            <li class="nav-list-item">
                <a href="#" class="nav-link">Dashboard</a>
            </li>
            <li class="nav-list-item">
                <a href="#" class="nav-link">Create Ticket</a>
            </li>
            <li class="nav-list-item">
                <a href="#" class="nav-link">My Profile</a>
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
            <h1>Create Roles</h1>
                <div class="col-md-10 col-md-offset-1">
                    <div class="panel panel-default panel-table">
                      <div class="panel-heading">
                        <div class="row">
                        </div>
                      </div>
                      <div class="panel-body" >
                        <table class="table table-striped table-bordered table-list" cellspacing="50">
                          <thead>
                            <tr>
                                <th>Department</th>
                                <th>Role</th>
                                <th>Create</th>
                            </tr> 
                          </thead>
                          <tbody>
                            <form action="CreateIMRD" method="POST">
                              <tr>
                                  <td align="center">
                                    <div class="form-group">
                                        <input class="form-control" type="text" name="deptname" placeholder="Enter Department"/>
                                    </div>
                                  </td>
                                <td align="center">
                                    <div class="form-group">
                                        <input class="form-control" type="text" name="rolename" placeholder="Enter Role"/>
                                    </div>
                                  </td>
                                  <td align="center">
                                    <div class="form-group" >
                                        <input type="submit" class="button" id="bttn-set-role" value="Create">
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
