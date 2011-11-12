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
    <h2>Welcome <s:property value="user.loginId"/>, Please select actions:</h2>
    <hr><br>
    <a href="<%=path%>/updatePatientInfo.action">Update Patient Information</a><br>
    <a href="<%=path%>/viewPatientRecords.action">View Patient Records</a><br>
    <a href="<%=path%>/logout.action">Logout</a><br>
  </body>
</html>