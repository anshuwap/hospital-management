<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>   
  <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
  </head>
<body>
    <h2>Welcome Financial officer: <s:property value="user.loginId"/></h2>
    <hr><br>
    <!-- 
    <a href="<%=path%>/searchDoctorPage.action">List Doctors</a><br>
    <a href="<%=path%>/logout.action">Logout</a><br>
     -->
      <s:form action="searchDoctorPage" method="post" >
		<s:submit  align="center" value="List Doctors"/>
     </s:form>
     <s:form action="logout" method="post" >
		<s:submit value="Logout"/>
     </s:form>

</body>
</html>