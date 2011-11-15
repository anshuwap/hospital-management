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
 <hr> 
 <s:form action="editPatient" method ="post" namespace="/patient">
 <s:textfield name="patient.patientName" Label="Patient Name" value="#session.RetrievedPatient.patientName"/>
 <s:textfield name="patient.healthCardId" Label="Patient HealthCard ID" value="%{retrievePatient.healthCardId}"/>
 <s:textfield name="patient.medication" Label="Patient Medication" value="%{retrievePatient.medication}"/>
 <s:textfield name="patient.allergy" Label="Patient Allergy" value="%{retrievePatient.allergy}"/>
  <s:token name="token"></s:token>
        <s:if test = "%{#session.Role=='Nurse'}">
 		   <s:submit value="Update"/>
 		</s:if>
 </s:form>
 
 
 
</body>
</html>
