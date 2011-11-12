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
 	<h3>Search Patient by DefaultDoctorId or Patient info</h3>
	<hr>
 	<s:form action="searchPatient" method="post" namespace="/">
  		<s:select name="doctor.userId" label="Choose a doctor" list="doctorMap" headerKey="" headerValue="-- select --"/>
 		<s:textfield name="patient.fName" label="Patient first name"/>
 		<s:textfield name="patient.lName" label="Patient last name"/>
 		<s:textfield name="patient.ohip" label="Patient Ohip" />
 		<sx:datetimepicker name="lastVisitDate" label="LastVisitDate" displayFormat="yyyy-MM-dd"/>
 		<s:submit value="Search Patient"/>
 	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Search patient <s:property value="returnCode" /><br/><br/> 
	 	<table class="Patient info list" border="1">
    		<tr class="even">
        	<td><b>PatientId</b></td>
        	<td><b>First name</b></td>
        	<td><b>Last name</b></td>
        	<td><b>Ohip</b></td>
        	<td><b>CurrHealth</b></td>
        	<td><b>DefaultDoctorId</b></td>
        	<td><b>Comments</b></td>
        	<td><b> </b></td>
        	<td colspan="3"><b>Operation</b></td>
    		</tr>
    		<s:iterator value="patientList" status="patientStatus">
            	<tr
                	class="<s:if test="#patientStatus.odd == true ">odd</s:if><s:else>even</s:else>">                		
					<td><s:property value="userId" /></td>
					<td><s:property value="fName" /></td>
					<td><s:property value="lName" /></td>          	
                	<td><s:property value="ohip" /></td>
                	<td><s:property value="currHealth" /></td>
 					<td><s:property value="defaultDoctor.userId" /></td>
                	<td><s:property value="Comments" /></td>
                	<td>  </td>
               <s:if test="defaultDoctor.userId==#session.CurrentUser.userId">
                	<td><a href="<s:url action='linkListAppoint'>
						<s:param name="patient.userId" value="%{userId}"/>						
						</s:url>">ListAppointment</a></td>
                	<td><a href="<s:url action='linkListVisit'>
						<s:param name="patient.userId" value="%{userId}"/>						
						</s:url>">ListVisitation</a></td>
                	<td><a href="<s:url action='linkViewPatientComment'>
						<s:param name="patient.userId" value="%{userId}"/>						
						</s:url>">ViewPatientComment</a></td>
				</s:if>
            	</tr>
			</s:iterator>
	 	</table>	 	
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
	 <a href="<%=path%>/returnToDoctorMain.action">Return to doctor main menu</a><br>
  </body>
</html>
