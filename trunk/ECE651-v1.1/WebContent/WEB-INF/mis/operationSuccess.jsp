<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <s:debug></s:debug>
  </head>
  
  <body>
     <jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
    <hr><br>
    <h3>Operation: <s:property value="%{#request.Operation}"/></h3><br>
  </body>
</html>
