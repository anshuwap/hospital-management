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
    <h2>Welcome Admin: <s:property value="user.loginId"/></h2>
    <hr><br>
 <!-- 
    <a href="<%=path%>/linkAddEmpl.action">Add Employee</a><br>
    <a href="<%=path%>/linkSearchEmpl.action">Search Employee</a><br>
    <a href="<%=path%>/logout.action">Logout</a><br>
 -->
    <s:form action="linkAddEmpl" method="post">
    	<s:submit value="Add Employee"/>
    </s:form>
     <s:form action="linkSearchEmpl" method="post">
    	<s:submit value="Search Employee"/>
    </s:form>
     <s:form action="logout" method="post">
    	<s:submit value="Logout"/>
    </s:form>
  </body>
</html>
