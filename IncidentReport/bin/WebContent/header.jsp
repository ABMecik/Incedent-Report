<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="Homepage"><i class="fa fa-star-half"></i>myScore</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="true"
			aria-label="Toggle navigation">
			Menu <i class="fa fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav item">

					<form class="btn-group searchbar "action="Search" method="POST" name="search"
						onsubmit="return searchValidation()">
							<input class="search_input text-color" type="text" name="seav"
								placeholder="Search..."> <a class="search_icon">
								<button type="submit" class="btn select-1 text-color">
									<i class="fa fa-search text-color"></i>
								</button>
							</a>
					</form>

				</li>
				<li class="nav-item dropdown">
					<div class="btn-group">
						<a href="AllCategories"><button type="button"
								class="btn select-1 text-color " role="button"
								aria-haspopup="true" aria-expanded="false">
								TV Series <i class="fa fa-chevron-circle-down"></i>
							</button></a>
						<div class="dropdown-menu collapse text-color" role="navigation">
							<a class="dropdown-item select-2 text-color"
								href="AllSeries#topRatedTab">Top Rated TV Series</a> <a
								class="dropdown-item text-color" href="AllCategories">All
								Categories</a><a class="dropdown-item select-3 text-color"
								href="AllSeries#actionTab">Action</a> <a
								class="dropdown-item select-4 text-color"
								href="AllSeries#dramaTab">Drama</a> <a
								class="dropdown-item select-5 text-color"
								href="AllSeries#comedyTab">Comedy</a> <a
								class="dropdown-item select-6 text-color"
								href="AllSeries#crimeTab">Crime</a>
						</div>
					</div>
				</li>
			</ul>
			<ul class="navbar-nav flex-row d-md-flex ">
				<%
					response.setHeader("Cache-Control", "no-cach,no-store,must-revalidate");
					if (session.getAttribute("user") == null) {
				%>
				<li class="nav-item">
					<button type="button" class="btn  text-color" data-toggle="modal"
						data-target="#login-register-modal">
						<i class="fa fa-sign-in"></i>
					</button>
				</li>
				<%
					} else {
				%>
				<li class="nav-item"><a href="Profile"><button
							type="button" class="btn text-color">
							<i class="fa fa-user"></i>
						</button></a></li>
				<li class="nav-item">
					<form class="form-inline" action="Logout">
						<button type="submit" class="btn text-color" value="Logout">
							<i class="fa fa-sign-out"></i>
						</button>
					</form>
				</li>
				<%
					}
				%>


			</ul>
		</div>
	</div>
</nav>
<div class="modal" id="login-register-modal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content modal-color">

			<!-- Modal Header -->
			<div class="modal-body">

				<button type="button" class="close" data-dismiss="modal">
					<i class="fa fa-times-circle modal-color"> </i>
				</button>
				<br /> <br />

				<ul
					class="nav nav-tabs tab-color nav-justified justify-content-center ">
					<li class="nav-item tab-1"><a class="nav-link active"
						data-toggle="tab" href="#loginTab">Login</a></li>
					<li class="nav-item tab-2"><a class="nav-link"
						data-toggle="tab" href="#registerTab">Register</a></li>
				</ul>

				<br />
				<div class="tab-content">
					<div class="tab-pane container active" id="loginTab">
					
						<div class="form-group">
							<form name="loginForm" id="login-form" method="POST"
								action="Login" onsubmit="return loginValidation()">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text text-color input-border">
											<i class="fa fa-at "></i>
										</span>
									</div>
									<input type="text" class="form-control text-color input-border"
										name="username" id="username" placeholder="Username" value="">
								</div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text text-color input-border">
											<i class="fa fa-key "></i>
										</span>
									</div>
									<input type="password"
										class="form-control text-color input-border" name="password"
										id="password" placeholder="Password" value="">
								</div>

								<button id="submitLoginForm" type="submit"
									class="btn btn-color btn-block">
									<span><i class="fa fa-sign-in"></i></span> Login
								</button>
							</form>
							
							
						</div>
					</div>
					<div class="tab-pane container fade" id="registerTab">
						<form name="regForm" id="register-form" action="Register"
							onsubmit="return regValidation()" method="POST">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text text-color input-border">
										<i class="fa fa-user-circle "></i>
									</span>
								</div>
								<input type="text" class="form-control text-color input-border"
									name="name" id="name" placeholder="Name">
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text text-color input-border">
										<i class="fa fa-at "></i>
									</span>
								</div>
								<input type="text" class="form-control text-color input-border"
									name="username" id="username" placeholder="Username" value="">
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text text-color input-border">
										<i class="fa fa-envelope "></i>
									</span>
								</div>
								<input type="text" class="form-control text-color input-border"
									id="email" name="email" placeholder="Email" value="">
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text text-color input-border">
										<i class="fa fa-key "></i>
									</span>
								</div>
								<input type="password"
									class="form-control text-color input-border" name="password"
									id="password" placeholder="Password" value="">
							</div>
							<div class="input-group mb-3 text-color">
								<div class="input-group-prepend">
									<span class="input-group-text text-color input-border">
										<i class="fa fa-check "></i>
									</span>
								</div>
								<input type="password"
									class="form-control text-color input-border"
									name="confirmPassword" id="confirmPassword"
									placeholder="Confirm Password">
							</div>

							<button id="submitRegisterForm" type="submit"
								class="btn btn-color btn-block">
								<span><i class="fa fa-sign-in"></i></span> Create your Account
							</button>
							<br />
						</form>
					</div>


				</div>

			</div>
		</div>
	</div>
</div>


<script src="resources/js/scripts.js"></script>

