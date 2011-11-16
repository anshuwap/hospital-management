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
			<td><h4>Operation on Patient</h4></td>
				<td><h4><s:a href="patient/toCreatePatientPage.action">Create Patient</s:a></h4></td>
				<td><h4><s:a href="patient/toEditPatientPage.action">Edit Patient</s:a></h4></td>
				<td><h4><s:a href="patient/toViewPatientPage.action">View Patient</s:a></h4></td>
			</tr>
		</table>
  </body>
</html>
