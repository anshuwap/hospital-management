<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
    <jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
  <hr><br> 
     <td><a href="<s:url value='patient/searchForViewPatient.action'>
      <s:param name="healthCardID" value="visitation.patient.healthCardId"/>  
      </s:url>">Back to View Patient Page</a></td>  
   
   <h2>Create New Appointment</h2><br>
 		<table border="1">
 		<s:textfield name="patient.patientName" label="First Name" required="true" />
 		<s:radio list="#{'1':'male' ,'0': 'female'}" name="patient.gender" value="1" label="Gender"/>
   		<s:textfield name="patientBirthday" label="Patient Birthday (Format:yyyy-mm-dd)" displayFormat="yyyy-MM-dd"/> 
 		<s:textfield name="patient.healthCardId" label="HealthCard ID" required="true"/>
 		<s:textarea name="patient.medication" label="Medication" cols="40" rows="10"/>
        <s:textarea name="patient.allergy" label="Allergy" cols="40" rows="10"/>
        <s:token name="token"></s:token>
         <tr>
        <td><s:reset value="Reset" /></td>
 		<td><s:submit value="Create"/></td>
 		</tr>
 		</table> 		
	 </s:form>
	 <s:property value="%{#request.OperationStatus}"/> <br/>
  
         		
  </body>
</html>
