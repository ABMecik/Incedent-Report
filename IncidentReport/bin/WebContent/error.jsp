<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String errrorDescription = (String) request.getAttribute("errordisc");
	String errorCode0 = (String) request.getAttribute("errorcode0");
	String errorCode1 = (String) request.getAttribute("errorcode1");
	String errorCode2 = (String) request.getAttribute("errorcode2");
%>


<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="resources/js/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/styles.css">
<link rel="stylesheet"
	href="resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"
	href="resources/magicscroll/magicscroll.css" />
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/scripts.js"></script>

</head>
<body>
	<div class="page-wrap d-flex flex-row align-items-center">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">

					<h1 class="display-1">
						<%out.print(errorCode0); %><i class="fa  fa-spin fa-cog fa-3x"></i><% out.print(errorCode2);%>
					</h1>
					<div class="mb-4 lead red-color"><%out.print(errrorDescription);%></div>
					<a href="Homepage"
						class="btn btn-link text-color">Back to
						Home</a>

				</div>
			</div>
		</div>
	</div>

</body>
</html>