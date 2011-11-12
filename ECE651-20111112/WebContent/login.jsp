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
  	<h1>Welcome to HMS system</h1>
	<hr>
   	<s:form action="login" method="post" namespace="/">
   		<table class="promo" border="1">
	 		<s:textfield name="user.username" label="Username"/>
	 		<s:password name="user.password" label="Password" required="true"/> 		
	 		<s:submit value="Login"/>
 		</table> 		
	 </s:form>
	 <s:if test="returnCode=='error'">
	 	<hr><s:property value="errorMessage" /><br/>
	 </s:if>
	 <s:elseif test="returnCode=='input'">
	 	<hr><s:property value="errorMessage" /><br/>
	 </s:elseif>
  </body>
</html>
