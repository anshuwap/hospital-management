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
 	<h3>List Diagnosis Set</h3>
	<hr>
	<s:if test="diagnosisSet.size()==0">
		There is no diagnosis!!!
	</s:if>
	<s:else>
	<table class="Diagnosis info list" border="1">
    	<tr class="even">
        <td><b>DiagnosisId</b></td>
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>
        <td><b>Description</b></td>
        <td><b>Result</b></td>
        <s:if test="editMode=='true'">
        <td><b> </b></td>              	
        <td colspan="1"><b>Operation</b></td>
        </s:if>
    	</tr>
    	<s:iterator value="diagnosisSet" status="diagnosisStatus">
           	<tr
               	class="<s:if test="#diagnosisStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="diagnosisId" /></td>                	
               	<td><s:property value="visitationId" /></td>
               	<td><s:property value="versionNo" /></td>
               	<td><s:property value="description" /></td>
               	<td><s:property value="result" /></td>
               	<s:if test="editMode=='true'">
               	<td>  </td>               	
               	<td><a href="<s:url action='linkEditDiagnosis'>
               		<s:param name="diagnosis.diagnosisId" value="%{diagnosisId}"/>
					<s:param name="diagnosis.visitationId" value="%{visitationId}"/>
					<s:param name="diagnosis.versionNo" value="%{versionNo}"/>
					<s:param name="diagnosis.description" value="description"/>
					<s:param name="diagnosis.result" value="%{result}"/>					
					</s:url>">Edit</a></td>
				</s:if>
           	</tr>
		</s:iterator>
	 </table>
	 </s:else>
	 <s:if test="editMode=='true'">
	 	<s:if test="needInput=='true'">
	 	<h3>Edit Diagnosis</h3>
	 	<hr>
	  	<s:form action="updateDiagnosis" method="post" namespace="/">
 			<s:textfield name="diagnosis.diagnosisId" label="DiagnosisId" />
 			<!--<s:textfield name="diagnosis.visitationId" label="VisitationId"/>-->
 			<!--<s:textfield name="diagnosis.versionNo" label="VersionNo"/>-->
 			<s:textfield name="diagnosis.description" label="Description"/>
 			<s:textfield name="diagnosis.result" label="result"/>
 			<s:submit value="Update Diagnosis"/>
	 	</s:form>
	 	</s:if>	 
	 	<h3>Add Diagnosis</h3> 
	 	<hr>
	  	<s:form action="addDiagnosis" method="post" namespace="/">
 			<!--<s:textfield name="newDiagnosis.diagnosisId" label="DiagnosisId" />-->
 			<!--<s:textfield name="newDiagnosis.visitationId" label="VisitationId"/>-->
 			<!--<s:textfield name="newDiagnosis.versionNo" label="VersionNo"/>-->
 			<s:textfield name="newDiagnosis.description" label="Description"/>
 			<s:textfield name="newDiagnosis.result" label="result"/>
 			<s:submit value="Add Diagnosis"/>
		</s:form>
	 </s:if>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Add/Edit Diagnosis <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Add/Edit Diagnosis <s:property value="returnCode" /><br/><br/>
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

