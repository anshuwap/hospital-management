<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <jsp:include page="/WEB-INF/mis/DoctorMenuHeader.jsp"/>
  
  <br><br>
  <s:form action="searchForViewPatient" method="post" namespace="/patient">
    <table>
      <tr>
        <td>HealthCardID: </td>
		<td><s:textfield name="healthCardID" theme="simple"/></td>
		<td><s:submit value="Search" theme="simple"/></td>
	  </tr>
	</table>
  </s:form>
  <br>
  <h1>Today's Appointments:</h1>
  <br>
  <s:if test='retrieveAppointments!=null && retrieveAppointments.size()>0'>
   	   <table border="1">
	      <tr>
			<td>Appointment ID</td>
			<td>Patient Name</td>
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
   </s:if>
   <s:else>
     <h1>None</h1>
   </s:else>
</html>
