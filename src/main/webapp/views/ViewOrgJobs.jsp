<!doctype html>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
  <head>
    <title>View Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vstyle.css"> 
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vsstyle.css">
   

  <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  

  </head>
  
  <style>




.bbbootstrap form {
    position: relative;
    width: 540px;
    margin: 22px auto 0
}

span {
    margin: 0;
    padding: 0;
    border: 0;
    outline: 0;
    font-weight: inherit;
    font-style: inherit;
    font-size: 100%;
    font-family: inherit;
    vertical-align: baseline
}

.bbbootstrap form input[type="text"] {
    padding: 15px 20px;
    padding-right: 100px;
    border-color: transparent;
    border-radius: 4px
}

input.InputBox {
    font-family: "lucida grande", "Lucida Sans Unicode", tahoma, sans-serif;
    color: #333;
    font-size: 15px;
    padding: 3px;
    margin: 0;
    width: 250px;
    background: #fff;
    border: 1px solid #999;
    border: 1px solid rgba(0, 0, 0, 0.4)
}

input[type=text] {
    box-sizing: border-box
}

.InputBox {
    display: block;
    width: 100% !important;
    padding: 6px 12px;
    font-size: 15px;
    line-height: 22px;
    border-radius: 4px
}

.bbbootstrap form input[type="submit"] {
    position: absolute;
    top: 5px;
    right: 5px;
    float: right;
    padding: 10px 25px
}

body .Button,
body .button {
    background-color: #1268b3;
    background-image: none
}

input[type="submit"] {
    -webkit-appearance: button;
    cursor: pointer
}

.Button,
.Button:hover,
.Button:focus,
.Button:active {
    text-shadow: none;
    border-color: transparent
}

.Button {
    display: inline-block;
    padding: 6px 12px;
    vertical-align: middle;
    font-size: 13px;
    font-weight: 700;
    line-height: 22px;
    text-transform: uppercase;
    border: transparent solid 1px;
    border-radius: 3px;
    -webkit-transition: -webkit-box-shadow 50ms;
    transition: -webkit-box-shadow 50ms;
    -o-transition: box-shadow 50ms;
    transition: box-shadow 50ms;
    transition: box-shadow 50ms, -webkit-box-shadow 50ms;
    -webkit-font-smoothing: inherit;
    color: #fff;
    background-color: #2e9df7;
    background-repeat: repeat-x;
    background-color: #38a2f7;
    background-image: -webkit-linear-gradient(#38a2f7, #2498f7);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#38a2f7), to(#2498f7));
    background-image: -o-linear-gradient(#38a2f7, #2498f7);
    background-image: linear-gradient(#38a2f7, #2498f7)
}
  
  </style>
  <body >



 <header>
    <div class="wrapper">
        <div class="logo">
            
        </div>
<ul class="nav-area">
<li><a href="#">Home</a></li>
<li><a href="addstudent">Add Student</a></li>
<li><a href="checkstudents1">View Students</a></li>
<li><a href="#">Add Organization</a></li>
<li><a href="#">View Organization</a></li>
<li><a href="#">Post</a></li>
<li><a href="home">Logout</a></li>
</ul>
</div>

 
<section class="ftco-section">

<div class="bbbootstrap">
     <div class="container">
         <form action="/searchbyrole/${email}" method="post">
             <span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
             <input type="text" id="Form_Search" value="" placeholder="Search by role" name="search" role="searchbox" class="InputBox " autocomplete="off">
             <input type="submit" id="Form_Go" class="Button" value="Search">
         </form>
     </div>
 </div>

    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6 text-center mb-4">
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          
            <table class="table">
              <thead class="thead-primary">
                <tr>
                  <th style="text-align:center;"><b>ID<b></th>
                  <th style="text-align:center;"><b>Organization</b></th>
                  <th style="text-align:center;"><b>Location</b></th>
                  <th style="text-align:center;"><b>Role</b></th>         
                  <th style="text-align:center;"><b>Package<b></th>
                  <th style="text-align:center;"><b>Delete<b></th>
                  
               </tr>
              </thead>
              <tbody>
              <c:forEach var="jobdata" items="${jobdata}">
                <tr class="alert" role="alert">
               <td style="text-align:center;"><b>${jobdata.id}</b></td>
    <td style="text-align:center;"><b>${jobdata.org}</b></td>
        <td style="text-align:center;"><b>${jobdata.loc}</b></td>
            <td style="text-align:center;"><b>${jobdata.role}</b></td>
             
                  <td style="text-align:center;"><b>${jobdata.amt}</b></td>
 <td class="border-bottom-0" style="text-align:center;"><a href="/sendrequest/${jobdata.id}/${jobdata.org}/${jobdata.role}" class="btn btn-primary" style="background-color:#f44336;">Request to delete</a></td>
                  
 </tr>
</c:forEach>
               
              </tbody>
            </table>
          
        </div>
      </div>
    </div>
  </section>


</header>

  </body>
</html>