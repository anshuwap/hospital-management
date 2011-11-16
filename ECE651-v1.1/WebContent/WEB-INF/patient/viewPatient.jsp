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
  <td><s:a href="patient/toMainPage.action">Back</s:a></td>
  <h2>View Patient</h2><br>
    <s:form action="viewPatient" method="post" namespace="/patient">
                  <s:textfield name="healthCardID" label="HealthCardID"/>
                  <s:token name="token"></s:token>
 		          <s:submit value="Search"/>
                  </s:form>
 <hr> 
 Patient name: <s:property value="retrievePatient.patientName"/><br>
 Patient healthcard: <s:property value="retrievePatient.healthCardId"/><br>
 
 
 
 
</body>
</html>
