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
	<h3>List Treatment Set</h3>
	<hr>
	<s:if test="treatmentSet.size()==0">
		There is no Treatment!!!
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
            	</tr>
		</s:iterator>
	 </table>
	 </s:else><!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToStaffListVisitation.action">Return to list visitation</a><br>
  </body>
</html>