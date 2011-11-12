<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>   
  <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
  </head>
  
  <body>
    <h2>Welcome Staff: <s:property value="user.loginId"/></h2>
    <hr><br>
    <!-- 
    <a href="<%=path%>/linkAddPatient.action">Add Patient</a><br>
    <a href="<%=path%>/linkStaffSearchPatient.action">Search Patient</a><br>
    <a href="<%=path%>/logout.action">Logout</a><br>
     -->
     <s:form action="linkAddPatient" method="post">
    	<s:submit value="Add Patient"/>
    </s:form>
     <s:form action="linkStaffSearchPatient" method="post">
    	<s:submit value="Search Patients"/>
    </s:form>
     <s:form action="logout" method="post">
    	<s:submit value="Logout"/>
    </s:form>
  </body>
</html>
