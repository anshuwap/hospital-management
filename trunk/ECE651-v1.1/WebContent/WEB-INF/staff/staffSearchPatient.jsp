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
 	<h3>Search Patient by Patient info</h3>
	<hr>
 	<s:form action="staffSearchPatient" method="post" namespace="/"> 		
 		<table border="1">
  		<s:select name="doctor.userId" label="Choose a doctor" list="doctorMap" headerKey="" headerValue="-- select --"/>
 		<s:textfield name="patient.fName" label="Patient first name"/>
 		<s:textfield name="patient.lName" label="Patient last name"/>
 		<s:textfield name="patient.ohip" label="Patient Ohip" /> 		
 		<s:submit value="Search Patient"/>
  		</table> 		
	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Search patient <s:property value="returnCode" /><br/><br/> 
	 	<s:if test="patientList.size() == 0">
	 	</s:if>
	 	<s:else>
	 	<table class="Patient info list" width="800" border="1">
    		<tr class="even">
        	<td><b>PatientId</b></td>
        	<td><b>First name</b></td>
        	<td><b>Last name</b></td>
        	<td><b>Ohip</b></td>
        	<td><b>CurrHealth</b></td>
        	<td><b>DefaultDoctor</b></td>
        	<td><b>Comments</b></td>
        	<td colspan="5"><b>Operation</b></td>
    		</tr>
    		<s:iterator value="patientList" status="patientStatus">
            	<tr
                	class="<s:if test="#patientStatus.odd == true ">odd</s:if><s:else>even</s:else>">                		
					<td><s:property value="userId" /></td>
					<td><s:property value="fName" /></td>
					<td><s:property value="lName" /></td>          	
                	<td><s:property value="ohip" /></td>
                	<td><s:property value="currHealth" /></td>
                	<td><s:property  value="defaultDoctor.userId" /></td>
                	<td><s:property value="Comments" /></td>
                	<td><a href="<s:url action='linkStaffBookAppoint'>
						<s:param name="patient.userId" value="%{userId}"/>
						<s:param name="patient.fName" value="%{fName}"/>
						<s:param name="patient.lName" value="%{lName}"/>
						<s:param name="doctor.userId" value="%{defaultDoctor.userId}"/>						
						</s:url>">BookAppointment</a></td>
                	<td><a href="<s:url action='linkStaffListAppoint'>
						<s:param name="patient.userId" value="%{userId}"/>						
						</s:url>">ListAppointment</a></td>
                	<td><a href="<s:url action='linkStaffListVisit'>
						<s:param name="patient.userId" value="%{userId}"/>						
						</s:url>">ListVisitation</a></td>
                	<td>
                		 <s:form action="staffAssignDoctor" method="post" namespace="/"> 		
 							<s:select name="assignDoctor.userId" label="Choose a doctor" list="doctorMap" headerKey="" headerValue="-- select --" labelposition="top"/>
 							<s:hidden name="assignPatient.userId" value="%{userId}" ></s:hidden>
 							<s:submit value="AssignDoctor"/>
	 					</s:form>	 
 					</td>
                	<td><a href="<s:url action='linkStaffUpdatePatient'>
						<s:param name="assignPatient.userId" value="%{userId}"/>						
						</s:url>">UpdatePatient</a></td>
            	</tr>
			</s:iterator>
	 	</table>
	 	</s:else>	 	
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Search patient <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToStaffMain.action">Return to staff main menu</a><br>
  </body>
</html>
