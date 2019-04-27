<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">
    <script src="resources/js/navbar.js"></script>

</head>
<body>

    <nav class="main-navigation">
        <div class="navbar-header animated fadeInUp">
            <a class="navbar-brand" href="#">No More Incidents</a>
        </div>
        <ul class="nav-list">
            <li class="nav-list-item">
                <a href="index" class="nav-link">Dashboard</a>
            </li>
            <li class="nav-list-item">
                <a href="create-ticket" class="nav-link">Create Ticket</a>
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
    
    <form action="CreateTicket" method="POST" enctype='multipart/form-data'>

    <div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="create-ticket" checked><label for="tab-1" class="tab">Ticket</label>
        <div class="login-form">
            
            <div class="create-ticket-htm">
				<div class="group">
					<label class="label">Title</label>
					<input id="title" type="text" class="input" name="title">
				</div>
                <div class="group">
					<label class="label">Description</label>
					<input id="decription" type="text" class="input" name="decription">
				</div>
				<div class="group">
					<label class="label">Location</label>
					<input id="location" type="text" class="input" name="location">
				</div>
				
                <div class="group">
				    <div class="container">
                      <div class="row">
                        <div class="col-xs-6">                         
                          <label id="priority" for="checkbox">Priority</label>
                          <div class="range">
                            <input type="range" name="priority" min="1" max="10" value="5" onchange="range.value=value">
                            <output id="range">5</output>
                          </div>
                        </div>
                      </div>
                </div>
				</div>
                
                
                
                <div class="group">
                    <div class="checkbox">
                        <input id="anonim" name="anonim" type="checkbox"/><label id="unknown" for="checkbox">Stay Anonim</label>
                    </div>
                </div>
                
                <div class="group">
                    <div class="file-upload">
                      <div class="image-upload-wrap">
                        <input class="file-upload-input" type='file' id="photo" name="photo" onchange="readURL(this);" accept="image/*" />
                        <div class="drag-text">
                          <h3>Drag and drop a file or select add Image</h3>
                        </div>
                      </div>
                      <div class="file-upload-content">
                        <img class="file-upload-image" src="#" alt="your image" />
                        <div class="image-title-wrap">
                          <button type="button" onclick="removeUpload()" class="remove-image">Remove <span class="image-title">Uploaded Image</span></button>
                        </div>
                      </div>
                    </div>
                </div>
                
				<div class="group">
					<input type="submit" class="button" value="Create-Ticket">
				</div>
				<div class="hr"></div>
			</div>

		</div>
	</div>
</div>

</form>

    
</body>
</html>