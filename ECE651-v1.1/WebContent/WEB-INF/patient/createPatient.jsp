<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <s:if test='#session.CurrentUser.roleType=="N"'>
    <jsp:include page="/WEB-INF/mis/NurseMenuHeader.jsp"/>
  </s:if>

   <script type="text/javascript" language="javascript">
      function ClearForm(){
         document.createPatientForm.reset();
      }
  </script>
  
  <body onload="ClearForm()"> 
  	<s:form name="createPatientForm" action="createPatient" method="post" namespace="/patient" onsubmit="">
  	<h2>Create New Patient</h2>
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
