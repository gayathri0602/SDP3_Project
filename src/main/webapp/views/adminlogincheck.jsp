<%@ page import="java.sql.*,java.io.*" %>

<%


try
{

String email=request.getParameter("uname");
String pwd=request.getParameter("pwd");
System.out.println(email+" "+pwd);
Connection con = null; 
Class.forName("com.mysql.cj.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp3", "root","root");
PreparedStatement pst=con .prepareStatement("select * from admin where name=? and  password=? "); 
pst.setString(1,email); 
pst.setString(2,pwd);
System.out.println("Hii1");

ResultSet rs = pst.executeQuery();

if(rs.next())
{
	System.out.println("Hii");
	response.sendRedirect("adminhome");
}
else
{
	out.println("<b>Invalid Login</b>");
	RequestDispatcher rd=request.getRequestDispatcher("home");
	rd.include(request,response);
}

pst.close(); 
con.close(); 
}
catch(Exception e)
{
	System.out.println(e);
}
%>