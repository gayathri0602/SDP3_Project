<html>
<head>
<title>Add Job</title>
</head>
<link href="${pageContext.request.contextPath}/css/orgaos.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/orgbootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/orgbootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/orgglightbox.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/orgswiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${pageContext.request.contextPath}/css/orghome.css" rel="stylesheet">
<style>
.form-container{
    background: linear-gradient(150deg,#1B394D 33%,#2D9DA7 34%,#2D9DA7 66%,#EC5F20 67%);
    font-family: 'Raleway', sans-serif;
    text-align: center;
    padding: 10px 10px 10px;
}
.form-container .title{
    color: #fff;
    font-size: 23px;
    text-transform: capitalize;
    letter-spacing: 1px;
    margin: 0 0 60px;
}
.form-container .form-horizontal{
    background-color: #fff;
    padding: 5px;
    border-radius: 5px;
    box-shadow: 0 0 20px rgba(0,0,0,0.4);
}
.form-horizontal .form-icon{
    color: #fff;
    background-color: #1B394D;
    font-size: 75px;
    line-height: 92px;
    height: 90px;
    width: 90px;
    margin: -65px auto 10px;
    border-radius: 50%;
}
.form-horizontal .form-group{
    margin: 0 0 10px;
    position: relative;
}
.form-horizontal .form-group:nth-child(3){ margin-bottom: 30px; }
.form-horizontal .form-group .input-icon{
    color: #e7e7e7;
    font-size: 23px;
    position: absolute;
    left: 0;
    top: 10px;
}
.form-horizontal .form-control{
    color: #000;
    font-size: 16px;
    font-weight: 600;
    height: 50px;
    padding: 20px 20px 20px 50px;
    margin: 0 0 5px;
    border: none;
    border-bottom: 2px solid #e7e7e7;
    border-radius: 0px;
    box-shadow: none;
}
.form-horizontal .form-control:focus{
    box-shadow: none;
    border-bottom-color: #EC5F20;
}
.form-horizontal .form-control::placeholder{
    color: #000;
    font-size: 16px;
    font-weight: 500;
}
.form-horizontal .forgot{
    font-size: 13px;
    font-weight: 600;
    text-align: right;
    display: block;
}
.form-horizontal .forgot a{
    color: #777;
    transition: all 0.3s ease 0s;
}
.form-horizontal .forgot a:hover{
    color: #777;
    text-decoration:  underline;
}
.form-horizontal .signin{
    color: #fff;
    background-color: #EC5F20;
    font-size: 17px;
    text-transform: capitalize;
    letter-spacing: 2px;
    width: 50%;
    padding: 12px;
    box-shadow: 0 0 10px rgba(0,0,0,0.3);
    transition: all 0.4s ease 0s;
}
.form-horizontal .signin:hover,
.form-horizontal .signin:focus{
    font-weight: 600;
    letter-spacing: 5px;
    box-shadow: 0 0 10px rgba(0,0,0,0.3) inset;
}
</style>
<body>

<header id="header" class="d-flex align-items-center ">
    <div class="container-fluid container-xxl d-flex align-items-center">

      <div id="logo" class="me-auto">
        <!-- Uncomment below if you prefer to use a text logo -->
        <h1><a href="index.html">Scout <span>Ninja</span></a></h1>
      <!--  <a href="index.html" class="scrollto"><img src="logo.png" alt="" title=""></a> -->
      </div>


      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
          <li><a class="nav-link scrollto active" href="#hero">Home</a></li> &nbsp;&nbsp;&nbsp;
          <li><a class="nav-link" href="/AddJob">Add Jobs</a></li>          &nbsp;&nbsp;&nbsp;
          <li><a class="nav-link scrollto" href="#speakers">View Jobs</a></li> &nbsp;&nbsp;&nbsp;
  <li><a class="nav-link scrollto" href="#schedule">View Applicants</a></li> &nbsp;&nbsp;&nbsp;
  <li><a class="nav-link scrollto" href="#venue">Accepted Applicants</a></li> &nbsp;&nbsp;&nbsp;
  <li><a class="nav-link scrollto" href="#hotels">Rejected Applicants</a></li> &nbsp;&nbsp;&nbsp;
         
        </ul>

      </nav><!-- .navbar -->
      <a class="buy-tickets scrollto" href="#buy-tickets">Logout</a>

    </div>
    
  </header><!-- End Header -->

<div class="form-bg" style="margin-top:100px; margin-left:500px; margin-right:500px;">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-4 col-md-4 col-sm-offset-3 col-sm-6">
                <div class="form-container">
                    <h3 class="title">My Account</h3>
                    <form method="post" action="/submitjobdata" class="form-horizontal">
                        <div class="form-icon">
                            <i class="fa fa-user-circle"></i>
                        </div>
                        <div class="form-group">
                            <span class="input-icon"><i class="fa fa-user"></i></span>
                            <input type="text" name="org" class="form-control" placeholder="Your Organization">
                        </div>
                        <div class="form-group">
                            <span class="input-icon"><i class="fa fa-user"></i></span>
                            <input type="text" name="jtitle" class="form-control" placeholder="Job Role/Title">
                        </div>
                        <div class="form-group">
                            <span class="input-icon"><i class="fa fa-user"></i></span>
                            <input type="text" name="loc" class="form-control" placeholder="Location">
                        </div>
                        <div class="form-group">
                            <span class="input-icon"><i class="fa fa-user"></i></span>
                            <input type="number" name="tp" class="form-control" placeholder="Time Period(In years)">
                        </div>
                        <div class="form-group">
                            <span class="input-icon"><i class="fa fa-user"></i></span>
                            <input type="number" name="amt" class="form-control" placeholder="Amount per annum">
                        </div>
                        <div class="form-group">
                            <span class="input-icon"><i class="fa fa-user"></i></span>
                            <input type="number" name="yr" class="form-control" placeholder="Expected Graduation Year">
                        </div>
                        <button class="btn signin">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
                            