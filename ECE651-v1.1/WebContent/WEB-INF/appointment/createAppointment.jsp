<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <s:if test='#session.CurrentUser.roleType=="N"'>
    <a href="<s:url value='patient/searchForViewPatient.action'>
      <s:param name="healthCardID">%{currentPatientHC}</s:param> 
      </s:url>">Back to View Patient Page</a>
  </s:if>
   
  <body>
  
    <br/><br/>
  
    <s:form name="getDoctorsForm" action="getDoctors" namespace="/appointment" method="post">
       <table>
       <s:textfield name="doctorSelected" value="%{doctorSelected}" label="Doctor ID" readOnly="true" required="true" />
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
			<td><a href="<s:url value='appointment/toCreateAppointmentPage.action'>
	                            <s:param name="doctorSelected" value="systemUserId" />
	                            </s:url>">select</a></td>
		 </tr>
	     </s:iterator>
	     </table>
    </s:if>
    
  	<s:form name="createAppointmentForm" action="createAppointment" method="post" namespace="/appointment" onsubmit="">
  	    <p>Create New Appointment</p>
  	    <table border="1">
  	    <s:hidden name="doctorSelected" value="%{doctorSelected}" />
 		<s:textfield value="%{currentSystemUserID}" label="Nurse ID" readOnly="true" required="true" />
 		<s:textfield name="currentPatientID" value="%{currentPatientID}" label="Patient ID" readOnly="true" required="true" />
 		<s:hidden name="currentPatientHC" value="%{currentPatientHC}" />
   		<sx:datetimepicker name="appointmentDate" label="Date" displayFormat="yyyy-MM-dd" required="true" />
   		<s:textfield name="appointment.startTime" label="Start Time" required="true" />
 		<s:textfield name="appointment.endTime" label="End Time" required="true" />
 		<s:select name="appointment.status" list="#{'A':'Active', 'X':'Cancelled', 'V':'Visited'}" label="Status" required="true" />
        <s:submit value="Create" />	
        </table>
	 </s:form>

Operation Status: <s:property value="operationStatus"/><br>
  </body>
</html>
