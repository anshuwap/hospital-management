<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
  </head>  
  <body>
 	<h3>List Appointment</h3>
	<hr>
	<s:if test="appointmentList.size()==0">
		There is no appoinment!!!
	</s:if>
	<s:else>
	<table class="Appointment info list" border="1">
    	<tr class="even">
        <td><b>AppointmentId</b></td>
        <td><b>Date</b></td>
        <td><b>StartTime</b></td>
        <td><b>EndTime</b></td>
        <td><b>PatientID</b></td>
        <td><b>DoctorId</b></td>
        <td><b>Status</b></td>
        <td><b>  </b></td> 	
        <td colspan="2"><b>Operation</b></td>
    	</tr>
    	<s:iterator value="appointmentList" status="appointStatus">
           	<tr
               	class="<s:if test="#appointStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="appointmentId" /></td>                	
               	<td><s:property value="date" /></td>
               	<td><s:property value="startTime" /></td>
               	<td><s:property value="endTime" /></td>
               	<td><s:property value="patient.userId" /></td>
               	<td><s:property value="doctor.userId" /></td>
               	<td><s:property value="status" /></td>
               	<td>  </td>
 	 			<s:if test="doctor.userId==#session.CurrentUser.userId">
	               	<td><a href="<s:url action='changeAppointStat'>
						<s:param name="appointment.appointmentId" value="%{appointmentId}"/>
						<s:param name="appointment.patient.userId" value="%{patient.userId}"/>					
						<s:param name="appointment.status">C</s:param>
						</s:url>">Cancel</a></td>
	               	<td><a href="<s:url action='changeAppointStat'>
						<s:param name="appointment.appointmentId" value="%{appointmentId}"/>
						<s:param name="appointment.patient.userId" value="%{patient.userId}"/>					
						<s:param name="appointment.status">V</s:param>
						</s:url>">Visited</a></td>
				</s:if>
           	</tr>
		</s:iterator>
	</table>
	</s:else>
	<s:if test="needCreateVisitation=='true'"> 		
		<h3>Create Visitation</h3>
		<hr>
 		<s:form action="createVisitation" method="post" namespace="/">
 			<s:textfield name="newVisitation.visitationId" label="VisitationId" readonly="true"/>
 			<s:textfield name="newVisitation.versionNo" label="VersionNo" readonly="true"/>
 			<s:textfield name="newVisitation.duration" label="Duration" required="true"/>
 			<s:textfield name="newVisitation.comments" label="Comments" required="true"/>
 			<s:textfield name="newVisitation.topInd" label="TopInd" readonly="true"/>
 			<s:textfield name="newVisitation.appointment.appointmentId" label="AppointmentId" readonly="true"/>
 			<s:submit value="Create Visitation"/>
	 	</s:form>
	 </s:if>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Update appointment <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Update appointment <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToPatientSearch.action">Return to patient search</a><br>
  </body>
</html>
