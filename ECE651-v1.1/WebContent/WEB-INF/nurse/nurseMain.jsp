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
				<td><s:a href="patient/toCreatePatientPage.action">Create Patient</s:a></td>
				  <s:form action="viewPatient" method="post" namespace="/patient">
                  <s:textfield name="healthCardID" label="HealthCardID"/>
                  <s:token name="token"></s:token>
 		          <s:submit value="Search"/>
                  </s:form>
<!--				<td><s:a href="patient/toEditPatientPage.action">Edit Patient</s:a></td>-->
<!--				<td><s:a href="patient/toViewPatientPage.action">View Patient</s:a></td>-->
			</tr>
		</table>
  </body>
</html>
