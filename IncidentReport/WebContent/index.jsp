<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<head>
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="resources/css/login.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%if (session.getAttribute("user") == null){%>

<div class="login-wrap">
	<div class="login-html">
        <input id="tab-3" type="radio" name="tab" class="register"><label for="tab-3" class="tab">Register</label>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="for-pwd"><label for="tab-2" class="tab">Forgot Password</label>
		<div class="login-form">
		
			<div class="register-htm">

            	<form action="Register" method="POST">

					<div class="group">
						<label class="label">Username</label>
						<input type="text" class="input" name="username">
					</div>
					
					<div class="group">
						<label class="label">Password</label>
						<input type="password" class="input" data-type="password" name="password">
					</div>
					
	                <div class="group">
						<label class="label">Name</label>
						<input type="text" class="input" name="name">
					</div>
					
	                <div class="group">
						<label class="label">Surname</label>
						<input type="text" class="input" name="surname">
					</div>
					
	                <div class="group">
						<label class="label">Email</label>
						<input type="text" class="input" name="email">
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
					<label for="user" class="label">Username</label>
					<input id="user" type="text" class="input" name="username">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password" class="input" data-type="password" name="password">
				</div>
				<div class="group">
					<input type="submit" class="button" value="Login">
				</div>
			</form>
			
				<div class="hr"></div>
			</div>
              
             <div class="for-pwd-htm">
				<div class="group">
					<label for="user" class="label">Username or Email</label>
					<input id="user" type="text" class="input">
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
<h1>Welcome ${user.getUsername()}</h1>
<%} %>
</body>
</html>


