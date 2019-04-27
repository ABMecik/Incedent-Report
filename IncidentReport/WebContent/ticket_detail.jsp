<!DOCTYPE html>
<html>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<head>
	<title>Welcome</title>
	<link rel="stylesheet" type="text/css" href="resources/css/ticket_detail.css">
    <script src="resources/js/photo.js"></script>
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

    
    
    <div class="login-wrap">
	<div class="login-html">
        <div class="container">
            <div class="row">
            <p></p>
            <h1>Ticket Details</h1>
                <div class="col-md-10 col-md-offset-1">
                    <div class="panel panel-default panel-table">
                      <div class="panel-heading">
                        <div class="row">
                        </div>
                      </div>
                      <div class="panel-body">
                        <table class="table table-striped table-bordered table-list" cellspacing="20">
                          <thead>
                            <tr>
                                <th class="hidden-xs">ID:</th>
                                <th>142</th>
                            </tr> 
                          </thead>
                          <tbody>
                              <tr>
                                <td class="hidden-xs" align="center">Title:</td>
                                <td align="center">Irresponsible Manager</td>
                              </tr>
                              <tr>
                                <td class="hidden-xs" align="center">Date:</td>
                                <td align="center">04/27/2019</td>
                              </tr>
                              <tr>
                                <td class="hidden-xs" align="center">Description:</td>
                                <td align="center">The software of elevators are shit! Pathetic.</td>
                              </tr>
                              <tr>
                                <td class="hidden-xs" align="center">Location:</td>
                                <td align="center">MEF University</td>
                              </tr>
                              <tr>
                                <td class="hidden-xs" align="center">Priority:</td>
                                <td align="center">7</td>
                              </tr>
                              <tr>
                                <td class="hidden-xs" align="center">Photo:</td>
                                <td align="center">
                                    
                                    <!-- Trigger the Modal -->
<img id="myImg" src="anger.jpg" alt="Snow" style="width:100%;max-width:300px">

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- The Close Button -->
  <span class="close">&times;</span>

  <!-- Modal Content (The Image) -->
  <img class="modal-content" id="img01">

  <!-- Modal Caption (Image Text) -->
  <div id="caption"></div>
</div>
                                  </td>
                              </tr>
                                                      
                          </tbody>
                        </table>
                      </div>
        </div></div></div>
         
		</div>
	</div>
</div>
  
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