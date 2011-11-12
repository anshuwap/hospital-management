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
	<h3>Grant Doctor</h3>
	<hr>
	<h3>Visitation List</h3>
	<hr>
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
        <td colspan="1"><b>Operation</b></td>
    	</tr>
    	<s:iterator value="visitList" status="visitStatus">
           	<tr	class="<s:if test="#visitStatus.odd == true ">odd</s:if><s:else>even</s:else>">
               	<td><s:property value="visitationId" /></td>                	
               	<td><s:property value="versionNo" /></td>
               	<td><s:property value="appointment.date" /></td>
               	<td><s:property value="appointment.startTime" /></td>
               	<td><s:property value="appointment.doctor.userId" /></td>
               	<td><s:property value="duration" /></td>
               	<td><s:property value="comments" /></td>
               	<td><s:property value="topInd" /></td>
               	<td>  </td>           	
               	<td><a href="<s:url action='linkSelectToGrant'>
					<s:param name="visit.visitationId" value="%{visitationId}"/>
					<s:param name="visit.versionNo" value="%{versionNo}"/>					
					<s:param name="visit.topInd" value="%{topInd}"/>		
					</s:url>">Select to grant</a>
				</td>
                <!--<td>
                	<s:form action="linkSelectToGrant" method="post" namespace="/"> 						
 						<s:hidden name="visit.visitationId" value="%{visitationId}" ></s:hidden>
 						<s:hidden name="visit.versionNo" value="%{versionNo}" ></s:hidden>
 						<s:hidden name="visit.topInd" value="%{topInd}" ></s:hidden>
 						<s:submit value="Select to grant"/>
	 				</s:form>	 
 				</td>-->			
           	</tr>
		</s:iterator>
	 </table>
	 <s:if test="needInput=='true'">
 	 	<s:form action="grantDoctor" method="post" namespace="/">
 			<s:textfield name="permits.fromDoctorId" label="FromDoctorId" required="true" readonly="true" />
 			<s:select name="permits.toDoctorId" label="ToDoctorId" list="doctorMap" headerKey="" headerValue="-- select --" required="true" />
 			<s:textfield name="permits.visitationId" label="VisitationId" required="true" readonly="true"/>
 			<s:textfield name="permits.versionNo" label="VersionNo" required="true" readonly="true"/>
 			<s:submit value="Grant Doctor"/>
		 </s:form>
	 </s:if>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Grant doctor <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Grant doctor <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToDoctorMain.action">Return to doctor main menu</a><br>
  </body>
</html>
