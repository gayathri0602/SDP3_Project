<!DOCTYPE html>
<html lang="en">
  <head>
  	<title>Admin Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alstyle.css">
 
 
	</head>
	
	
	<body>
<!-- 	<nav class="navbar navbar-inverse navbar-fixed-top"> -->
<!--   <div class="container-fluid"> -->
<!--     <div class="navbar-header"> -->
<!--       <a class="navbar-brand" href="#">WebSiteName</a> -->
<!--     </div> -->
<!--     <ul class="nav navbar-nav"> -->
<!--       <li class="active"><a href="#">Home</a></li> -->
<!--       <li><a href="#">Page 1</a></li> -->
<!--       <li><a href="#">Page 2</a></li> -->
<!--       <li><a href="#">Page 3</a></li> -->
<!--     </ul> -->
<!--   </div> -->
<!-- </nav> -->

<section class="ftco-section">
	
		<div class="container">
			<div class="row justify-content-center">
				
			</div>
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap py-5">
		      	<div class="img d-flex align-items-center justify-content-center" style="background-image: url(${pageContext.request.contextPath}/images/logo.png);"></div>
		      	<h3 class="text-center mb-0">Admin Login</h3>
		      	
						<form action="adminlogincheck" method="post" class="login-form">
		      		<div class="form-group">
		      			<div class="icon d-flex align-items-center justify-content-center"><span class="fa fa-user"></span></div>
		      <input type="text" class="form-control" placeholder="Username" name="uname" required>
		      		</div>
	            <div class="form-group">
	            	<div class="icon d-flex align-items-center justify-content-center"><span class="fa fa-lock"></span></div>
     <input type="password" class="form-control" placeholder="Password" name="password" required>
	             </div>
	            <div class="form-group d-md-flex">
								
	            </div>
	            <div class="form-group">
	<button type="submit" class="btn form-control btn-primary rounded submit px-3">Login</button>
	            </div>
	          </form>
	          
	        </div>
				</div>
			</div>
		</div>
	</section>
  

	

	</body>
</html>

