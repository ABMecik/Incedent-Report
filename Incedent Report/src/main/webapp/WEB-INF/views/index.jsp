<!DOCTYPE html >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>YK | Welcome</title>

<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/login.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>


	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navigation">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand text-uppercase" href="/login">YK <span
					class="label label-success text-capitalize">Beta</span></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navigation">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="register"><button type="button"
								class="btn btn-success navbar-btn btn-circle">Sing up</button></a></li>
					<li><a href="/login"><button type="button"
								class="btn btn-success navbar-btn btn-circle">Sign in</button></a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="inpage">
		<c:choose>
			<c:when test="${mode=='MODE_REGISTER' }">
				<!-- 
				<div class="container text-center">
					<h3>New Registration</h3>
					<hr>
					<form class="form-horizontal" method="POST" action="register">
						<c:if test="${not empty error }">
							<div class="alert alert-danger">
								<c:out value="${error }"></c:out>
							</div>
						</c:if>
						<input type="hidden" name="id" value="${user.id }" />
						<div class="form-group">
							<label class="control-label col-md-3">Username</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="username"
									value="${user.username }" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="email"
									value="${user.email }" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">First Name</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="firstname"
									value="${user.firstname }" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">Last Name</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="lastname"
									value="${user.lastname }" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">Age </label>
							<div class="col-md-3">
								<input type="text" class="form-control" name="age"
									value="${user.age }" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">Password</label>
							<div class="col-md-7">
								<input type="password" class="form-control" name="password"
									value="${user.password }" />
							</div>
						</div>
						<div class="form-group ">
							<input type="submit" class="btn btn-primary" value="Register" />
						</div>
					</form>
				</div>
				 -->
				<div class="container text-center">
					<b id="login-name">REGISTER</b>
				</div>
				<div class="row">
					<div class="col-md-6 col-md-offset-3" id="login">
						<form class="form-horizontal" method="POST" action="register">
							<c:if test="${not empty error }">
								<div class="alert alert-danger">
									<c:out value="${error }"></c:out>
								</div>
							</c:if>
							<div class="form-group">
								<label class="user"> Email </label>
								<div class="input-group">
									<span class="input-group-addon" id="iconn"> <i
										class="glyphicon glyphicon-envelope"></i></span> <input type="text"
										class="form-control" id="text1" name="email" id="email"
										placeholder=" Email" value="${user.email }">
								</div>
							</div>
							<div class="form-group">
								<label class="user"> Username </label>
								<div class="input-group">
									<span class="input-group-addon" id="iconn"> <i
										class="glyphicon glyphicon-user"></i></span> <input type="text"
										class="form-control" id="text1" name="username" id="username"
										placeholder=" Username" value="${user.username }">
								</div>
							</div>
							<div class="form-group">
								<label class="user"> Password </label>
								<div class="input-group">
									<span class="input-group-addon" id="iconn1"> <i
										class="glyphicon glyphicon-lock"></i></span> <input type="password"
										class="form-control" id="text2" name="password" id="password"
										placeholder=" Password" value="${user.password }">
								</div>
							</div>
							<div class="form-group">
								<label class="user"> Repeat Password </label>
								<div class="input-group">
									<span class="input-group-addon" id="iconn1"> <i
										class="glyphicon glyphicon-lock"></i></span> <input type="password"
										class="form-control" id="text2" name="passwordagain"
										id="passwordagain" placeholder=" Password Again ">
								</div>
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-success" value="Register"
									style="border-radius: 0px;"> <input type="reset"
									class="btn btn-danger" value="reset"
									style="border-radius: 0px;">
							</div>
						</form>
					</div>
				</div>
	</div>
	</c:when>
	<c:when
		test="${mode=='MODE_LOGIN' or mode=='' or empty mode or mode!='MODE_REGISTER' }">
		<!--
				<div class="container text-center">
					<h3>User Login</h3>
					<hr>
					<form class="form-horizontal" method="POST" action="/login">
						<c:if test="${not empty param.error}">
							<div class="alert alert-danger">
								<h2>Invalid Email or Password</h2>
							</div>
						</c:if>
						<div class="form-group">
							<label class="control-label col-md-3">Username</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="username"
									value="${user.username }" id="username" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">Password</label>
							<div class="col-md-7">
								<input type="password" class="form-control" name="password"
									value="${user.password }" id="password" />
							</div>
						</div>
						<div class="form-group ">
							<input type="submit" class="btn btn-primary" value="Login" />
						</div>
					</form>
				</div>
				-->


		<div class="container">
			<div class="container text-center">
				<b id="login-name">LOGIN</b>
			</div>
			<div class="row">
				<div class="col-md-6 col-md-offset-3" id="register">
					<form class="form-horizontal" method="POST" action="/login">
						<c:if test="${not empty param.error}">
							<div class="alert alert-danger">
								<h2>Invalid Email or Password</h2>
							</div>
						</c:if>
						<div class="form-group">
							<label class="user"> UserName </label>
							<div class="input-group">
								<span class="input-group-addon" id="iconn"> <i
									class="glyphicon glyphicon-user"></i></span> <input type="text"
									class="form-control" id="text1" name="username" id="username"
									placeholder="username" value="${user.username }">
							</div>
						</div>
						<div class="form-group">
							<label class="user"> Password </label>
							<div class="input-group">
								<span class="input-group-addon" id="iconn1"> <i
									class="glyphicon glyphicon-lock"></i></span> <input type="password"
									class="form-control" id="text2" name="password" id="password"
									placeholder=" Enter Password">
							</div>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-success" value="Login"
								style="border-radius: 0px;"> <input type="reset"
								class="btn btn-danger" value="reset" style="border-radius: 0px;">
						</div>
						<br /> <br /> <br /> <a href="#"
							style="color: white; font-size: 15px; float: right; margin-right: 10px;">
							Forget Password </a> <a href="register"
							style="color: white; font-size: 15px; float: right; margin-right: 10px;">
							Register </a>
					</form>
				</div>
			</div>
		</div>
	</c:when>
	</c:choose>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>