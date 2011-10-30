<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <font face="verdana,arial" size=-1>
<center><table cellpadding='2' cellspacing='0' border='0' id='ap_table'>
<tr><td bgcolor="white"><table cellpadding='0' cellspacing='0' border='0' width='548' height="246"><tr><td bgcolor="white" align=center style="padding:2;padding-bottom:4"><b><font  color="black" face="verdana,arial"><b>Hospital Management System</b></font></th></tr>

<center><table width="233" height="111">
<form action="login.action" method="post">
<tr><td>Login:</td><td><input type="text" name="username"></td></tr>
<tr><td>Password:</td><td><input type="password" name="password"></td></tr>
<tr><td></td><td><font face="verdana,arial" size=-1><input type="submit" value="Enter"></td></tr>
</form>
</table></center>
  </body>
</html>
