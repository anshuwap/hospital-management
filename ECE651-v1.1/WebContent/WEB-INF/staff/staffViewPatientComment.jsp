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
 	<h3>View Comments</h3>
	<hr>
 	<s:form action="updatePatientComment" method="post" namespace="/">  		
 		<s:textfield name="assignPatient.userId" label="PatientId"/>
 		<s:textfield name="assignPatient.fName" label="Patient first name"/>
 		<s:textfield name="assignPatient.lName" label="Patient last name"/>
 		<s:textfield name="assignPatient.ohip" label="Patient Ohip" /> 		
 		<s:textfield name="assignPatient.defaultDoctor.userId" label="DefaultDoctorId" />
 		<s:textarea name="assignPatient.comments" label="Comments" cols="40" rows="10"/>	
 		<s:submit value="Update Comments"/>
	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Update comments <s:property value="returnCode" /><br/><br/> 
	 	<table class="Patient info list">
    		<tr class="even">
        	<td><b>PatientId</b></td>        	
        	<td><b>Comments</b></td>        	
    		</tr>
    		<s:iterator value="patientList" status="patientStatus">
            	<tr
                	class="<s:if test="#patientStatus.odd == true ">odd</s:if><s:else>even</s:else>">                		
					<td><s:property value="userId" /></td>					
                	<td><s:property value="Comments" /></td> 
            	</tr>
			</s:iterator>
	 	</table>	 	
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Update comments <s:property value="returnCode" /><br/><br/>
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
