<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
  <sx:head/>
  </head>  
  <body>
 	<h3>Book Appointment</h3>
	<hr>
 	<s:form action="retrieveTime" method="post" namespace="/">
		<table border="1">
 		<sx:datetimepicker name="sAppDate" label="Date" displayFormat="yyyy-MM-dd"/>
 		<s:select name="doctor.userId" label="Choose a doctor" list="doctorMap" headerKey="" headerValue="-- select --" required="true"/>
 		<s:submit value="Refresh"/>
 		</table>
 	 </s:form>
 	 
	<s:form action="bookAppoint" method="post" namespace="/">
		<table border="1">
 		<!--  s:textfield name="appointment.appointmentId" label="AppointmentId" required="true"/> -->
 		<s:textfield name="sAppDate" label="Appointment Date" readonly="true"/>
		<s:textfield name="appointment.doctor.userId" label="DcotorID" value="%{doctor.userId}" readonly="true"/>
 		<s:select id="timeIndex" name="startTimeIndex" label="Choose Time" list="doctorScheduleMap" headerKey="" headerValue="-- select --" required="true"/>
  		<s:textfield name="appointment.patient.userId" label="PatientID" value="%{patient.userId}" readonly="true"/>
 		<s:submit value="Book Appointment"/>
  		</table>	
	 </s:form>

	<s:if test="appointmentList.size()==0">
		There is no appointment!!!
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
        <td colspan="1"><b>Operation</b></td>
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
                	<td><a href="<s:url action='changeAppointStat'>
					<s:param name="appointment.appointmentId" value="%{appointmentId}"/>
					<s:param name="appointment.patient.userId" value="%{patient.userId}"/>					
					<s:param name="appointment.status">C</s:param>
					</s:url>">Cancel</a></td>
  <!--              	<td><a href="<s:url action='changeAppointStat'>
					<s:param name="appointment.appointmentId" value="%{appointmentId}"/>
					<s:param name="appointment.patient.userId" value="%{patient.userId}"/>					
					<s:param name="appointment.status">V</s:param>
					</s:url>">Visited</a></td>
	-->			
           	</tr>
		</s:iterator>
	 </table>	 	
	 </s:else>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Update appointment <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr><h3>Book appointment <s:property value="returnCode" /><br/><br/></h3>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToStaffPatientSearch.action">Return to patient search</a><br>
  </body>
</html>
