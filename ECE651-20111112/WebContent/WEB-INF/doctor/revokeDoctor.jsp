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
	<h3>Revoke permission</h3>
	<hr>
	<h3>Visitation List</h3>
	<hr>
	<s:if test="permitsList.size()==0">
		There is no granted visitation records!!!
	</s:if>
	<s:else>
	<table class="Visitation info list" border="1">
    	<tr class="even">
        <td><b>FromDoctorId</b></td>
        <td><b>ToDoctorId</b></td>
        <td><b>VisitationId</b></td>
        <td><b>VersionNo</b></td>
        <td><b>Date</b></td>
        <td><b>StartTime</b></td>
        <td><b>Duration</b></td>
        <td><b>VisitDoctor</b></td>
        <td><b>Comments</b></td>
        <td><b>TopInd</b></td>
        <td><b>Operation</b></td>
    	</tr>
    	<s:iterator value="permitsList" status="permitsStatus">
          	<tr	class="<s:if test="#visitStatus.odd == true ">odd</s:if><s:else>even</s:else>">
	               	<td><s:property value="fromDoctorId" /></td>                	
	               	<td><s:property value="toDoctorId" /></td>
	               	<td><s:property value="visitationId" /></td>                	
	               	<td><s:property value="versionNo" /></td>
	               	<td><s:property value="visitation.appointment.date" /></td>
	               	<td><s:property value="visitation.appointment.startTime" /></td>
	               	<td><s:property value="visitation.duration" /></td>
	               	<td><s:property value="visitation.appointment.doctor.userId"/></td>
	               	<td><s:property value="visitation.comments" /></td>
	               	<td><s:property value="visitation.topInd" /></td>

	               	<td><a href="<s:url action='revokeDoctor'>
						<s:param name="permits.fromDoctorId" value="%{fromDoctorId}"/>
						<s:param name="permits.toDoctorId" value="%{toDoctorId}"/>
						<s:param name="permits.visitationId" value="%{visitationId}"/>
						<s:param name="permits.versionNo" value="%{versionNo}"/>					
						</s:url>">Select to revoke</a>
					</td>
			</tr>
		</s:iterator>
	 </table>
 	 </s:else>
	 <hr>	 
	 <a href="<%=path%>/returnToDoctorMain.action">Return to doctor main menu</a><br>
  </body>
</html>
