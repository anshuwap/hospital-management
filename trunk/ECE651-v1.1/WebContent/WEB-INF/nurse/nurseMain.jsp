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
  </head>
  <body>
    <jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
    <hr><br>
		<center>
			<h2>Nurse Main Page</h2>
		</center>
		<table>
			<tr>
			<td>Operation on Patient</td>
				<td><a href="/WEB-INF/patient/viewPatient.jsp">View Patient</a></td>
				<td><a href="/WEB-INF/patient/editPatient.jsp">Edit Patient</a></td>
				<td><a href="/WEB-INF/patient/createPatient.jsp">Add New Patient</a></td>
			</tr>
		</table>
  </body>
</html>
