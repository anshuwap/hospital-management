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
	<h3>List Treatment Set</h3>
	<hr>
	<s:if test="treatmentSet.size()==0">
		There is no treatment!!!
	</s:if>
	<s:else>
	<table class="Treatment info list" border="1">
    	<tr class="even">
        <td><b>TreatmentId</b></td>
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>
        <td><b>ScheduledDate</b></td>
        <td><b>Description</b></td>
        <td><b>Result</b></td>
        <s:if test="editMode=='true'">
        <td><b> </b></td>
        <td colspan="1"><b>Operation</b></td>
        </s:if>
    	</tr>
    	<s:iterator value="treatmentSet" status="treatmentStatus">
           	<tr
               	class="<s:if test="#treatmentStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="treatmentId" /></td>                	
               	<td><s:property value="visitationId" /></td>
               	<td><s:property value="versionNo" /></td>
               	<td><s:property value="scheduledDate" /></td>
               	<td><s:property value="description" /></td>
               	<td><s:property value="result" /></td>
               	<s:if test="editMode=='true'">
               	<td>  </td>
               	<td><a href="<s:url action='linkEditTreatment'>
               		<s:param name="treatment.treatmentId" value="%{treatmentId}"/>
					<s:param name="treatment.visitationId" value="%{visitationId}"/>
					<s:param name="treatment.versionNo" value="%{versionNo}"/>
					<s:param name="editScheduledDate" value="%{scheduledDate}"/>
					<s:param name="treatment.description" value="%{description}"/>
					<s:param name="treatment.result" value="%{result}"/>	
					</s:url>">Edit</a></td>
				</s:if>
           	</tr>
		</s:iterator>
	 </table>
	 </s:else>
	 <s:if test="editMode=='true'">
	 <s:if test="needInput=='true'">
	 <h3>Edit Treatment</h3>
	 <hr>
	 <s:form action="updateTreatment" method="post" namespace="/">
 		<s:textfield name="treatment.treatmentId" label="TreatmentId" readonly="true" />
 		<s:textfield name="treatment.visitationId" label="VisitationId" readonly="true"/>
 		<s:textfield name="treatment.versionNo" label="VersionNo" readonly="true"/>
 		<sx:datetimepicker name="editScheduledDate" label="ScheduledDate" displayFormat="yyyy-MM-dd"/>
 		<!--<sx:datetimepicker name="treatment.scheduledDate" label="ScheduledDate" displayFormat="yyyy-MM-dd"/>-->
 		<!--<s:textfield name="treatment.scheduledDate" label="ScheduledDate"/>-->
 		<s:textfield name="treatment.description" label="Description"/>
 		<s:textfield name="treatment.result" label="result"/>
 		<s:submit value="Update Treatment"/>
	 </s:form>
	 </s:if>
	 <h3>Add Treatment</h3>
	 <hr>
	 <s:form action="addTreatment" method="post" namespace="/">
 		<!--<s:textfield name="newTreatment.treatmentId" label="TreatmentId" />-->
 		<!--<s:textfield name="newTreatment.visitationId" label="VisitationId"/>-->
 		<!--<s:textfield name="newTreatment.versionNo" label="VersionNo"/>-->
 		<sx:datetimepicker name="newScheduledDate" label="ScheduledDate" displayFormat="yyyy-MM-dd"/>
 		<!--<sx:datetimepicker name="newTreatment.scheduledDate" label="ScheduledDate" displayFormat="yyyy-MM-dd"/>-->
 		<!--<s:textfield name="newTreatment.scheduledDate" label="ScheduledDate"/>-->
 		<s:textfield name="newTreatment.description" label="Description"/>
 		<s:textfield name="newTreatment.result" label="result"/>
 		<s:submit value="Add Treatment"/>
	 </s:form>
	 </s:if>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Add/Edit Treatment <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Add/Edit Treatment <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToListVisitation.action">Return to list visitation</a><br>
  </body>
</html>
