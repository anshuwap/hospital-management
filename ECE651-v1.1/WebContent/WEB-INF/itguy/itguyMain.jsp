<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>   
  <!--<link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>-->
  <s:debug></s:debug>
  </head>
  <body>
   <jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
    <hr><br>
		<center>
			<h2>IT Guy's Main Page</h2>
			<tr>
				<td><h4><s:a href="systemuser/toCreateSystemUserPage.action">Create User</s:a></h4></td>
				<td><h4><s:a href="systemuser/toEditPatientPage.action">Edit User</s:a></h4></td>
				<td><h4><s:a href="systemuser/toViewPatientPage.action">View Users</s:a></h4></td>
			</tr>
		</center>
  </body>
</html>
