<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <body> 
		<s:form action="searchForViewPatient" method="post"
			namespace="/patient">
			<table align="center">
				<tr>
					<td>
						HealthCardID:
					</td>
					<td>
						<s:textfield name="healthCardID" theme="simple" />
					</td>
					<td>
						<s:submit value="Search" theme="simple" />
					</td>
					<td>
					     <s:property value="operationStatus" />
					</td>
				</tr>
			</table>
		</s:form>
		
 <hr><br>                 
  <s:form name="editPatientForm" action="editPatient" method="post" namespace="/patient">
 		<table border="1" align="center">
 		<caption>Edit Patient</caption>
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
