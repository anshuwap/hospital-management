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
  	<!--<h2>Part 1: Input form</h2>-->
	<h3>Search Appointment</h3>
	<hr>
 	<s:form action="searchAppoint" method="post" namespace="/">
 		<s:textfield name="appointment.appointmentId" label="AppointmentId" required="true"/>
 		<s:textfield name="appointment.date" label="Date"/>
 		<s:textfield name="appointment.startTime" label="StartTime"/>
 		<s:textfield name="appointment.endTime" label="EndTime"/>
 		<s:textfield name="appointment.patient.patientID" label="PatientID"/>
 		<s:textfield name="appointment.doctor.doctorId" label="DoctorId"/>
		<s:textfield name="appointment.status" label="Status" required="true"/>
 		<s:submit value="Search Appointment"/>
 	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Search appointment <s:property value="returnCode" /><br/><br/> 
	 	<table class="Appointment info list">
    		<tr class="even">
        	<td><b>AppointmentId</b></td>
        	<td><b>Date</b></td>
        	<td><b>StartTime</b></td>
        	<td><b>EndTime</b></td>
        	<td><b>PatientID</b></td>
        	<td><b>DoctorId</b></td>
        	<td><b>Status</b></td>        	
        	<td><b>Operation</b></td>
    		</tr>
    		<s:iterator value="appointmentList" status="appointStatus">
            	<tr
                	class="<s:if test="#appointStatus.odd == true ">odd</s:if><s:else>even</s:else>">
                	<td><s:property value="appointmentId" /></td>                	
                	<td><s:property value="date" /></td>
                	<td><s:property value="startTime" /></td>
                	<td><s:property value="endTime" /></td>
                	<td><s:property value="patient.patientID" /></td>
                	<td><s:property value="doctor.doctorId" /></td>
                	<td><s:property value="status" /></td>
                	<td><a href="<s:url action='changeAppointStat'>
						<s:param name="appointment.appointmentId" value="%{appointmentId}"/>
						</s:url>">ChangeToDoneStstus</a></td>
            	</tr>
			</s:iterator>
	 	</table>	 	
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Search appointment <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToDoctorMain.action">Return to doctor main menu</a><br>
  </body>
</html>
