<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <s:if test='#session.CurrentUser.roleType=="N"'>
    <jsp:include page="/WEB-INF/mis/NurseMenuHeader.jsp"/>
    <a href="<s:url value='patient/searchForViewPatient.action'>
      <s:param name="healthCardID">%{currentPatientHC}</s:param> 
      </s:url>">Back to View Patient Page</a>
  </s:if>
   
  <body>
  
    <br/><br/>
  
    <s:form name="getDoctorsForm" action="getDoctors" namespace="/appointment" method="post">
       <table>
       <s:textfield name="currentDoctorID" value="%{currentDoctorID}" label="Doctor ID" readOnly="true" required="true" />
       <s:hidden name="currentNurseID" value="%{currentNurseID}" />
       <s:hidden name="currentPatientID" value="%{currentPatientID}" />
       <s:hidden name="currentPatientHC" value="%{currentPatientHC}" />
       <s:submit value="Choose doctor" theme="simple" />
       </table>
    </s:form>
    
    <s:if test='retrieveDoctors!=null'>
       	  <table border="1">
	      <tr>
			<td>Doctor ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Select</td>
		 </tr>
	     <s:iterator value="retrieveDoctors">
	     <tr>
			<td><s:property value="systemUserId" /></td>
			<td><s:property value="firstName" /></td>
			<td><s:property value="lastName" /></td>
			<td><a href="<s:url value='appointment/toCreateAppointmentPageWithoutInit.action'>
	                            <s:param name="currentDoctorID" value="systemUserId" />
	                            <s:param name="currentNurseID">%{currentNurseID}</s:param>
                                <s:param name="currentPatientID">%{currentPatientID}</s:param>
                                <s:param name="currentPatientHC">%{currentPatientHC}</s:param>
	                            </s:url>">select</a></td>
		 </tr>
	     </s:iterator>
	     </table>
    </s:if>
    
  	<s:form name="createAppointmentForm" action="createAppointment" method="post" namespace="/appointment" onsubmit="">
  	    <p>Create New Appointment</p>
  	    <table border="1">
  	    <s:hidden name="currentDoctorID" value="%{currentDoctorID}" />
 		<s:textfield name="currentNurseID" value="%{currentNurseID}" label="Nurse ID" readOnly="true" required="true" />
 		<s:textfield name="currentPatientID" value="%{currentPatientID}" label="Patient ID" readOnly="true" required="true" />
 		<s:hidden name="currentPatientHC" value="%{currentPatientHC}" />
   		<sx:datetimepicker name="appointmentDate" label="Date" displayFormat="yyyy-MM-dd" required="true" />
   		<s:textfield name="appointment.startTime" label="Start Time" />
 		<s:textfield name="appointment.endTime" label="End Time" />
 		<s:select name="appointment.status" list="#{'A':'Active', 'X':'Cancelled', 'V':'Visited'}" label="Status" required="true" />
        <s:submit value="Create" />	
        </table>
	 </s:form>

Operation Status: <s:property value="operationStatus"/><br>
  </body>
</html>
