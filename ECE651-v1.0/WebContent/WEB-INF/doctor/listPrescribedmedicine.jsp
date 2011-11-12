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
	<h3>List Prescribedmedicine Set</h3>
	<hr>
	<s:if test="prescribedmedicineSet.size()==0">
		Theres is no Prescribed medicine!!!
	</s:if>
	<s:else>
	<table class="Prescription info list" border="1">
    	<tr class="even">
        <td><b>PrescriptionId</b></td>
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>        
        <td><b>MedicineId</b></td>
        <td><b>MedicineName</b></td>
        <td><b>Manufacturer</b></td>
        <td><b>Instruction</b></td>
        <s:if test="editMode=='true'">
        <td><b> </b></td>              	
        <td colspan="1"><b>Operation</b></td>
        </s:if>
    	</tr>
    	<s:iterator value="prescribedmedicineSet" status="pmStatus">
           	<tr
               	class="<s:if test="#pmStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="prescriptionId" /></td>                	
               	<td><s:property value="visitationId" /></td>
               	<td><s:property value="versionNo" /></td>
               	<td><s:property value="medicine.medicineId" /></td>         
               	<td><s:property value="medicine.medicineName" /></td>
               	<td><s:property value="medicine.manufacturer" /></td>
               	<td><s:property value="medicine.instruction" /></td> 
               	<s:if test="editMode=='true'">
               	<td>  </td>
               	<td><a href="<s:url action='linkEditPrescribedmedicine'>
               		<s:param name="prescribedmedicine.prescriptionId" value="%{prescriptionId}"/>
					<s:param name="prescribedmedicine.visitationId" value="%{visitationId}"/>
					<s:param name="prescribedmedicine.versionNo" value="%{versionNo}"/>
					<s:param name="prescribedmedicine.medicine.medicineId" value="%{medicine.medicineId}"/>	
					<s:param name="prescribedmedicine.medicine.medicineName" value="%{medicine.medicineName}"/>
					<s:param name="prescribedmedicine.medicine.manufacturer" value="%{medicine.manufacturer}"/>
					<s:param name="prescribedmedicine.medicine.instruction" value="%{medicine.instruction}"/>						
					</s:url>">Edit</a></td>
				</s:if>
           	</tr>
		</s:iterator>
	 </table>
	 </s:else>
	 <s:if test="editMode=='true'">
	 <s:if test="needInput=='true'">
	 <h3>Edit Prescribedmedicine</h3>
	 <hr>
	  	<s:form action="updatePrescribedmedicine" method="post" namespace="/">
 		<s:textfield name="prescribedmedicine.prescriptionId" label="PrescriptionId" readonly="true"/>
 		<s:textfield name="prescribedmedicine.visitationId" label="VisitationId" readonly="true"/>
 		<s:textfield name="prescribedmedicine.versionNo" label="VersionNo" readonly="true"/>
 		<s:select name="prescribedmedicine.medicine.medicineId" label="MedicineId" list="legalmedicineMap" headerKey="" headerValue="-- select --" required="true" />
 		<s:select name="newPrescribedmedicine.medicine.medicineId" label="New MedicineId" list="legalmedicineMap" headerKey="" headerValue="-- select --" required="true" />
 		<s:submit value="Update Prescribedmedicine"/>
 	 </s:form>
	 </s:if>
	 <h3>Add Prescribedmedicine</h3> 
	 <hr>
	  	<s:form action="addPrescribedmedicine" method="post" namespace="/">
 		<!--
 		<s:textfield name="newPrescribedmedicine.PrescriptionId" label="PrescriptionId" readonly="true"/>
 		<s:textfield name="newPrescribedmedicine.visitationId" label="VisitationId" readonly="true"/>
 		<s:textfield name="newPrescribedmedicine.versionNo" label="VersionNo" readonly="true"/>
 		<s:textfield name="newPrescribedmedicine.medicine.medicineId" label="MedicineId"/>
 		-->	
 		<s:select name="newPrescribedmedicine.medicine.medicineId" label="MedicineId" list="legalmedicineMap" headerKey="" headerValue="-- select --" required="true" />
 		<s:submit value="Add Prescribedmedicine"/>
	 </s:form>
	 </s:if>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Add/Edit Prescribedmedicine <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Add/Edit Prescribedmedicine <s:property value="returnCode" /><br/><br/>
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

