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
	<h3>List Prescription</h3>
	<hr>
	<s:if test="prescriptionSet.size()==0">
		There is no prescription!!!
	</s:if>
	<s:else>
	<table class="Prescription info list" border="1">
    	<tr class="even">
        <td><b>PrescriptionId</b></td>
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>
        <s:if test="editMode=='true'">
        <td><b> </b></td>              	
        <td><b>Operation</b></td>
        </s:if>
        <s:if test="viewMode=='true'">
        <td><b> </b></td>              	
        <td><b>Operation</b></td>
        </s:if>
    	</tr>
    	<s:iterator value="prescriptionSet" status="prescriptionStatus">
           	<tr
               	class="<s:if test="#prescriptionStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="prescriptionId" /></td>              	
               	<td><s:property value="visitationId" /></td>
               	<td><s:property value="versionNo" /></td>
               	<s:if test="editMode=='true'">
               	<td>  </td>
               	<td><a href="<s:url action='linkListPrescribedmedicineSet'>
					<s:param name="prescription.prescriptionId" value="%{prescriptionId}"/>
					<s:param name="prescription.visitationId" value="%{visitationId}"/>					
					<s:param name="prescription.versionNo" value="%{versionNo}"/>		
					</s:url>">Edit Prescribedmedicine</a></td>
				</s:if>
               	<s:if test="viewMode=='true'">
               	<td>  </td>
               	<td><a href="<s:url action='linkListPrescribedmedicineSet'>
					<s:param name="prescription.prescriptionId" value="%{prescriptionId}"/>
					<s:param name="prescription.visitationId" value="%{visitationId}"/>					
					<s:param name="prescription.versionNo" value="%{versionNo}"/>		
					</s:url>">View Prescribedmedicine</a></td>
				</s:if>
           	</tr>
		</s:iterator>
	</table>
	</s:else>
	<s:if test="editMode=='true'">
	<s:if test="needInput=='true'">
	</s:if>
	<h3>Add Prescription</h3>
	<hr>
 	<s:form action="addPrescription" method="post" namespace="/">
 		<!--<s:textfield name="prescription.prescriptionId" label="PrescriptionId" readonly="true"/>-->
 		<!--<s:textfield name="prescription.visitationId" label="VisitationId" readonly="true"/>-->
 		<!--<s:textfield name="prescription.versionNo" label="VersionNo" readonly="true"/>-->
 		<!--<s:textfield name="prescribedmedicine.medicine.medicineId" label="MedicineId"/>-->
 		<s:select name="prescribedmedicine.medicine.medicineId" label="MedicineId" list="legalmedicineMap" headerKey="" headerValue="-- select --" required="true" />
 		<s:submit value="Add Prescription"/>
 	 </s:form>
	 </s:if>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Add/Update Prescription <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Add/Update Prescription <s:property value="returnCode" /><br/><br/>
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
