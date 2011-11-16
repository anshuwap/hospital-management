<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

  <head>
    <base href="<%=basePath%>">
   

  </head>
  
  <body>
          <s:form action="searchPatient" method="post" namespace="/patient">
                  <s:textfield name="healthCardID" label="HealthCardID"/>
                  <s:token name="token"></s:token>
 		          <s:submit value="Search"/>
                  </s:form>
  </body>

