<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <s:if test='#session.CurrentUser.roleType=="N"'>
    <jsp:include page="/WEB-INF/mis/NurseMenuHeader.jsp"/>
    <a href="<s:url value='patient/searchForViewPatient.action'>
      <s:param name="healthCardID" value="visitation.patient.healthCardId"/>  
      </s:url>">Back to View Patient Page</a>
  </s:if>
  <s:elseif test='#session.CurrentUser.roleType=="D"'>
    <jsp:include page="/WEB-INF/mis/DoctorMenuHeader.jsp"/>
  </s:elseif>
   
  	<s:form name="createAppointmentForm" action="createAppointment" method="post" namespace="/appointment" onsubmit="">
  	    <p>Create New Appointment</p>
  	    <table border="1">
 		<s:textfield name="appointment.doctor.systemUserId" value="appointment.doctor.systemUserId" label="Doctor ID" required="true" />
 		<s:textfield name="appointment.nurse.systemUserId" value="appointment.nurse.systemUserId" label="Nurse ID" required="true" />
 		<s:textfield name="appointment.patient.patientId" value="appointment.patient.patientId" label="Patient ID" required="true" />
   		<s:textfield name="systemUserBirthday" label="User Birthday (Format:yyyy-mm-dd)" displayFormat="yyyy-MM-dd"/>
   		<s:textfield name="systemUser.email" label="Email" />
 		<s:textfield name="systemUser.phone" label="Phone" />
 		<s:select name="appointment.status" value="appointment.status" list="#{'A':'Active', 'X':'Cancelled', 'V':'Visited'}" label="Status" required="true" />
        <s:reset value="Reset" />
        <s:submit value="Create" />	
        </table>
	 </s:form>
	 <s:property value="operationStatus"/><br>	
  </body>
</html>

