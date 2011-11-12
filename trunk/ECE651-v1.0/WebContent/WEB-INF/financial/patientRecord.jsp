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
	<h3>Patient Visitation Records</h3>

	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Find the patient visitation records <s:property value="returnCode" /> ! <br/><br/> 
	 	<table class="Record info list" border="1">
    		<tr class="even">
        	<td><b>Date</b></td>
        	<td><b>Time</b></td>
        	<td><b>DoctorId</b></td>
        	<td><b>VisitationId</b></td>
        	<td><b>Duration</b></td>
        	<td><b>PrescriptionList</b></td>
        	<td><b>TreatmentList</b></td>
        	<td><b>DiagnosisList</b></td>
        	
    		</tr>
    		<s:iterator value="visitationList" status="userStatus" id="aaa">
            	<tr
                	class="<s:if test="#userStatus.odd == true ">odd
                	       </s:if>
                	       <s:else>
                	       			even
                	       </s:else>"
                >
                	<td><s:property value="appointment.date" /></td>
                	<td><s:property value="appointment.startTime" /></td>
                	<td><s:property value="appointment.doctor.userId" /></td>
					<s:param name="doctor.userId" value="%{appointment.doctor.userId}"/>
                	<td><s:property value="visitationId" /></td>
                	<td><s:property value="duration" /></td>
 	               	<td><a href="<s:url action='linkListPrescriptionList'>
						</s:url>">Prescription List</a></td>
 	               	<td><a href="<s:url action='linkListTreatmentList'>
						</s:url>">Treatment List</a></td>
 	               	<td><a href="<s:url action='linkListDiagnosisList'>
						</s:url>">Diagnosis List</a></td>
                	

           	</tr>
                	
			</s:iterator>
	 	</table>	
	 		
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>list doctor <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/searchDoctorPage.action">Return to Search Doctor Page</a><br>
  </body>
</html>
