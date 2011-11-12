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
	<h3>List Visitation</h3>
	<hr>
	<s:if test="visitList.size()==0">
		There is no visitation!!!
	</s:if>
	<s:else>
	 <table class="Visitation info list" border="1">
    	<tr class="even">
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>
        <td><b>Date</b></td>
        <td><b>StartTime</b></td>
        <td><b>Doctor Id</b></td>
        <td><b>Duration</b></td>
        <td><b>Comments</b></td>
        <td><b>TopInd</b></td>
        <td><b> </b></td>              	
        <td colspan="3"><b>Operation</b></td>
    	</tr>
    	<s:iterator value="visitList" status="visitStatus">
           	<tr
               	class="<s:if test="#visitStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="visitationId" /></td>                	
               	<td><s:property value="versionNo" /></td>
               	<td><s:property value="appointment.date" /></td>
               	<td><s:property value="appointment.startTime" /></td>
               	<td><s:property value="appointment.doctor.userId" /></td>
               	<td><s:property value="duration" /></td>
               	<td><s:property value="comments" /></td>
               	<td><s:property value="topInd" /></td>
               	<td>  </td>
	 			<s:if test="appointment.doctor.userId==#session.CurrentUser.userId">
	 				<s:if test="topInd==1">
	               		<td><a href="<s:url action='linkListDiagnosisSet'>
							<s:param name="visit.visitationId" value="%{visitationId}"/>
							<s:param name="visit.versionNo" value="%{versionNo}"/>					
							<s:param name="visit.topInd" value="%{topInd}"/>		
							</s:url>">Edit Diagnosis</a></td>
	               		<td><a href="<s:url action='linkListTreatmentSet'>
							<s:param name="visit.visitationId" value="%{visitationId}"/>
							<s:param name="visit.versionNo" value="%{versionNo}"/>					
							<s:param name="visit.topInd" value="%{topInd}"/>		
							</s:url>">Edit Treatment</a></td>
	               		<td><a href="<s:url action='linkListPrescriptionSet'>
							<s:param name="visit.visitationId" value="%{visitationId}"/>
							<s:param name="visit.versionNo" value="%{versionNo}"/>					
							<s:param name="visit.topInd" value="%{topInd}"/>		
							</s:url>">Edit Prescription</a></td>
					</s:if>
					<s:else>
	             		<td><a href="<s:url action='linkListDiagnosisSet'>
							<s:param name="visit.visitationId" value="%{visitationId}"/>
							<s:param name="visit.versionNo" value="%{versionNo}"/>					
							<s:param name="visit.topInd" value="%{topInd}"/>		
							</s:url>">View Diagnosis</a></td>
	               		<td><a href="<s:url action='linkListTreatmentSet'>
							<s:param name="visit.visitationId" value="%{visitationId}"/>
							<s:param name="visit.versionNo" value="%{versionNo}"/>					
							<s:param name="visit.topInd" value="%{topInd}"/>		
							</s:url>">View Treatment</a></td>
	               		<td><a href="<s:url action='linkListPrescriptionSet'>
							<s:param name="visit.visitationId" value="%{visitationId}"/>
							<s:param name="visit.versionNo" value="%{versionNo}"/>					
							<s:param name="visit.topInd" value="%{topInd}"/>		
							</s:url>">View Prescription</a></td>
					</s:else>
	 			</s:if>
	 			<s:else>
               		<td><a href="<s:url action='linkListDiagnosisSet'>
						<s:param name="visit.visitationId" value="%{visitationId}"/>
						<s:param name="visit.versionNo" value="%{versionNo}"/>					
						<s:param name="visit.topInd" value="%{topInd}"/>		
						</s:url>">View Diagnosis</a></td>
               		<td><a href="<s:url action='linkListTreatmentSet'>
						<s:param name="visit.visitationId" value="%{visitationId}"/>
						<s:param name="visit.versionNo" value="%{versionNo}"/>					
						<s:param name="visit.topInd" value="%{topInd}"/>		
						</s:url>">View Treatment</a></td>
               		<td><a href="<s:url action='linkListPrescriptionSet'>
						<s:param name="visit.visitationId" value="%{visitationId}"/>
						<s:param name="visit.versionNo" value="%{versionNo}"/>					
						<s:param name="visit.topInd" value="%{topInd}"/>		
						</s:url>">View Prescription</a></td>
	 			</s:else>
           	</tr>
		</s:iterator>
	 </table>
	 </s:else>	 	
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Update visitation <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Update visitation <s:property value="returnCode" /><br/><br/>
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
