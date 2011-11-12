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
 	<h3>List Prescribed medicine Set</h3>
	<hr>
	<s:if test="prescribedmedicineSet.size()==0">
		There is no prescribed medicine!!!
	</s:if>
	<s:else>
	<table class="Prescription info list">
    	<tr class="even">
        <td><b>PrescriptionId</b></td>
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>        
        <td><b>MedicineId</b></td>
        <td><b>MedicineName</b></td>
        <td><b>Manufacturer</b></td>
        <td><b>Instruction</b></td>
        <td><b> </b></td>              	
        <td><b>Operation</b></td>
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
            	</tr>
		</s:iterator>
	 </table>
	 </s:else>
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToStaffListVisitation.action">Return to list visitation</a><br>
  </body>
</html>

