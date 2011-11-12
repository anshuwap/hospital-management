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
	<h3>Search Doctor</h3>
	<hr>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Find Patient Histories <s:property value="returnCode" /> ! <br/><br/> 
	 	<table class="Patient Histories list">
    		<tr class="even">
        	<td><b>Date</b></td>
        	<td><b>Time</b></td>
        	<td><b>DoctorId</b></td>
        	<td><b>VisitationId</b></td>
        	<td><b>Duration</b></td>
    		</tr>
    		<s:iterator value="visitationList" status="patientStatus">
            	<tr
                	class="<s:if test="#patientStatus.odd == true ">odd
                	       </s:if>
                	       <s:else>
                	       			even
                	       </s:else>"
                >
                	<td><s:property value="appointment.date" /></td>
                	<td><s:property value="appointment.startTime" /></td>
                	<td><s:property value="appointment.doctor.userId" /></td>
                	<td><s:property value="visitationId" /></td>
                	<td><s:property value="duration" /></td>
            	</tr>
			</s:iterator>
	 	</table>	 
            	<tr>

 	<s:form action="patientVTH" method="post" namespace="/">
 		<td><s:submit value="View Treatment Histories"/></td>
	 </s:form>	 
	 		
 	<s:form action="patientVPH" method="post" namespace="/">
 		<td><s:submit value="View Prescribtion Histories"/></td>
	 </s:form>	 

 	<s:form action="patientVDH" method="post" namespace="/">
 		<td><s:submit value="View Diagnosis Histories"/></td>
	 </s:form>	 
            	</tr>

	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Find Patient Histories <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:elseif test="returnCode=='input'">
	 	<hr><s:property value="errorMessage" /><br/>
	 </s:elseif>
	 
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToPatientMain.action">Return to main menu</a><br>


  </body>
</html>
