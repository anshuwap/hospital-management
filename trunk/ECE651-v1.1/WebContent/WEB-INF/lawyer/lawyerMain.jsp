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

    <!--<a href="<s:url action='logout'><s:param name="loginId" value="%{user.loginId}"/></s:url>">Logout</a><br> -->
  </body>
</html>
