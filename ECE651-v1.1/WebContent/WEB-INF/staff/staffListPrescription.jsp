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
	<h3>List Prescription</h3>
	<hr>
	<s:if test="prescriptionSet.size() == 0">
		There is no prescription!!!
	</s:if>
	<s:else>
	<table class="Prescription info list" border="1">
    	<tr class="even">
        <td><b>PrescriptionId</b></td>
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>        
        <td><b>Operation</b></td>
    	</tr>
    	<s:iterator value="prescriptionSet" status="prescriptionStatus">
           	<tr
               	class="<s:if test="#prescriptionStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="prescriptionId" /></td>                	
               	<td><s:property value="visitationId" /></td>
               	<td><s:property value="versionNo" /></td>
               	<td><a href="<s:url action='linkStaffListPrescribedmedicineSet'>
					<s:param name="prescription.prescriptionId" value="%{prescriptionId}"/>
					<s:param name="prescription.visitationId" value="%{visitationId}"/>					
					<s:param name="prescription.versionNo" value="%{versionNo}"/>		
					</s:url>">View Prescribedmedicine</a></td>
           	</tr>
		</s:iterator>
	</table>
	</s:else>
	 <hr>	 
	 <a href="<%=path%>/returnToStaffListVisitation.action">Return to list visitation</a><br>
  </body>
</html>
