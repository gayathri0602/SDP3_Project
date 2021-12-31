<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>User Login form</title>
<meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='${pageContext.request.contextPath}/css/bootstrap.min.css'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ustyle.css">

</head>
<body>
<!-- partial:index.partial.html -->


<div class="container">
   <section id="formHolder">

      <div class="row">

         <!-- Brand Box -->
         <div class="col-sm-6 brand">
            <a href="#" class="logo">SN</a>

            <div class="heading">
               <h2>Scout Ninja</h2>
               <p>Your Right Choice</p>
            </div>

            <div class="success-msg">
               <p>Great! You are one of our members now</p>
               <a href="#" class="profile">Your Profile</a>
            </div>
         </div>


         <!-- Form Box -->
         <div class="col-sm-6 form">

            <!-- Login Form -->
            <div class="login form-peice switched">
               <form class="login-form" action="userlogincheck" method="post">
                  <div class="form-group">
                     <label for="loginemail">Email Adderss</label>
                     <input type="email" name="aemail" id="loginemail" required>
                  </div>

                  <div class="form-group">
                     <label for="loginPassword">Password</label>
                     <input type="password" name="apassword" id="loginPassword" required>
                  </div>

                  <div class="CTA">
                     <input type="submit" value="Login" >
                     <a href="#" class="switch">I'm New</a>
                  </div>
               </form>
            </div><!-- End Login Form -->


            <!-- Signup Form -->
            <div class="signup form-peice">
               <form class="login-form" action="userlogincheck" method="post">

                 <div class="form-group">
                     <label for="loginemail">Email Adderss</label>
                     <input type="email" name="aemail" id="loginemail" required>
                  </div>

                  <div class="form-group">
                     <label for="loginPassword">Password</label>
                     <input type="password" name="apassword" id="loginPassword" required>
                  </div>

                  <div class="CTA">
                     <input type="submit" value="Login" >
                     
                  </div>
               </form>
            </div><!-- End Signup Form -->
         </div>
      </div>

   </section>


</div>
<!-- partial -->
  <script src='${pageContext.request.contextPath}/js/bootstrap.min.js'></script>
<script src='${pageContext.request.contextPath}/js/jquery.min.js'>
</script><script  src="${pageContext.request.contextPath}js/script.js"></script>

</body>
</html>
