<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  
  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <body>
  <s:if test='#session.CurrentUser.roleType=="N"||#session.CurrentUser.roleType=="L"'>
  <s:form name="searchAppointmentForm" action="searchAppointment" method="post" namespace="/appointment" onsubmit="">
  <table>
    <tr>
      <td><s:textfield name="searchContent" label="Appoinment" required="true" theme="simple" /></td>
      <td><s:select name="searchType" list="#{'appID':'Appointment ID', 'docID':'Doctor ID', 'patID':'Patient ID'}" theme="simple"/>
	  <td><s:submit name="searchAll" value="search" theme="simple" /></td>
	  <td><s:submit name="searchAll" value="View all appointments" theme="simple" /></td>
    </tr>
  </table>	
  </s:form>
  </s:if>
  <s:elseif test='#session.CurrentUser.roleType=="D"'>
    <s:form name="searchAppointmentForm" action="searchAppointment" method="post" namespace="/appointment" onsubmit="">
      <s:hidden name="searchContent" value="%{currentSystemUserID}" />
      <s:hidden name="searchType" value="docID" />
  	  <s:submit value="View my appointments" theme="simple" />
  	</s:form>
  </s:elseif>
  
  <s:if test='retrieveAppointments!=null || retrieveAppointments.size()>0'>
   <s:if test='retrieveAppointments.size()==1'>
      <s:form name="editAppointmentForm" action="editAppointment" method="post" namespace="/appointment">
        <p>Nurse:</p>
        <table>
        	<s:textfield value="%{retrieveAppointments[0].nurse.systemUserId}" label="ID" readonly="true"/>
        	<s:textfield value="%{retrieveAppointments[0].nurse.firstName}" label="First Name" readonly="true"/>
        	<s:textfield value="%{retrieveAppointments[0].nurse.lastName}" label="Last Name" readonly="true"/>
        </table>
        <p>Doctor:</p>
        <table>
        	<s:textfield value="%{retrieveAppointments[0].doctor.systemUserId}" label="ID" readonly="true"/>
        	<s:textfield value="%{retrieveAppointments[0].doctor.firstName}" label="First Name" readonly="true"/>
        	<s:textfield value="%{retrieveAppointments[0].doctor.lastName}" label="Last Name" readonly="true"/>
        </table>
        <p>Patient:</p>
        <table>
        	<s:textfield value="%{retrieveAppointments[0].patient.patientId}" label="Patient ID" readonly="true"/>
        	<s:textfield value="%{retrieveAppointments[0].patient.patientName}" label="Patient Name" readonly="true"/>
        </table>
        <p>Appointment Information:</p>
 		<table border="1">
 		   <s:textfield name="currentAppID" value="%{retrieveAppointments[0].appointmentId}" label="Appointment ID" readonly="true"/>
   		   <sx:datetimepicker name="appointmentDate" value="%{retrieveAppointments[0].appointmentDate.toString()}" label="Date" displayFormat="yyyy-MM-dd" required="true" />
   		   <s:select name="appointment.startTime" value="%{retrieveAppointments[0].startTime}" label="Start Time" list="timeMap"  required="true"/>
   		   <s:select name="appointment.endTime" value="%{retrieveAppointments[0].endTime}" label="End Time" list="timeMap"  required="true"/>
 		   <s:select name="appointment.status" value="%{retrieveAppointments[0].status}" list="#{'A':'Active', 'X':'Cancelled', 'V':'Visited'}" label="Status" required="true" />
 		</table> 		
 		<s:if test='isEdit==true'><s:submit value="Update" theme="simple"/></s:if>
 		<s:if test='#session.CurrentUser.roleType=="D"&&visitation==null'>
 		<a href="<s:url value='visitation/createVistationFromAppointment.action'>
						    <s:param name="appointmentId" value="%{retrieveAppointments[0].appointmentId}"/> 
                            </s:url>">Create New Visitaiton</a>
 		</s:if>
 		<s:elseif test="visitation!=null" >
 		<a href="<s:url value='visitation/searchVisitation.action'>
						    <s:param name="visitationId" value="visitation.visitationId"/>   
                            </s:url>">View Visitation</a>
 		</s:elseif>
	   </s:form>
   </s:if>
   <s:else>
   	   <table border="1">
	      <tr>
			<td>Appointment ID</td>
			<td>Patient Name</td>
			<td>Doctor Name</td>
			<td>Appointment Date</td>
			<td>Start Time</td>
			<td>End Time</td>
			<td>Status</td>
			<td>view/edit</td>
		 </tr>
	     <s:iterator value="retrieveAppointments">
	     <tr>
			<td><s:property value="appointmentId" /></td>
			<td><s:property value="patient.patientName" /></td>
			<td><s:property value="doctor.firstName" /> <s:property value="doctor.lastName" /></td>
			<td><s:property value="appointmentDate.toString().substring(0,10)" /></td>
			<td><s:property value="startTime" /></td>
			<td><s:property value="endTime" /></td>
			<td><s:property value="status" /></td>
			<td><a href="<s:url value='/appointment/searchAppointment.action'>
								<s:param name="searchType">appID</s:param> 
								<s:param name="searchAll">false</s:param> 
	                            <s:param name="searchContent" value="appointmentId" />
	                            </s:url>">View<s:if test='isEdit==true'>/Edit</s:if></a></td>
		 </tr>
	     </s:iterator>
	     </table>
   </s:else>
</s:if>
 
Operation Status: <s:property value="operationStatus"/><br>
  
  </body>
</html>
