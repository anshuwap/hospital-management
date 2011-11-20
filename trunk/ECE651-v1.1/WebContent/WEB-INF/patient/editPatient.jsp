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
 
  <s:form action="searchForEditPatient" method="post" namespace="/patient">
                  <s:textfield name="healthCardID" label="HealthCardID"/>
                  <s:token name="token"></s:token>
 		          <s:submit value="Search"/>
                  </s:form>
 <hr><br>                 
  <h2>Edit Patient</h2><br>
  <s:form name="editPatientForm" action="editPatient" method="post" namespace="/patient">
 		<table border="1">
 		<s:textfield name="retrievePatient.patientId" label="PatientID (ReadOnly)" readonly="true"/>
 		<s:textfield name="retrievePatient.patientName" label="First Name" required="true" />
 		<s:radio list="#{'1':'male' ,'0': 'female'}" name="retrievePatient.gender" label="Gender"/>
   		<s:textfield name="patientBirthday" label="Patient Birthday (Format:yyyy-mm-dd)" displayFormat="yyyy-MM-dd"/> 
 		<s:textfield name="retrievePatient.healthCardId" label="HealthCard ID (ReadOnly)" readonly="true"/>
 		<s:textarea name="retrievePatient.medication" label="Medication" cols="40" rows="10"/>
        <s:textarea name="retrievePatient.allergy" label="Allergy" cols="40" rows="10"/>
        <s:token name="token"></s:token>
        <tr>
 		<td><s:submit value="Update"/></td>
 		</tr>
 		</table> 		
	 </s:form>
 <hr> 
 Operation Status: <s:property value="operationStatus"/><br>

</body>
</html>