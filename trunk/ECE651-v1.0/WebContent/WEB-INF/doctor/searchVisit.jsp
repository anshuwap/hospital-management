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
	<h3>Search Visit</h3>
	<hr>
 	<s:form action="searchVisit" method="post" namespace="/">
 		<s:textfield name="visit.visitationId" label="VisitationId" required="true"/>
 		<s:textfield name="visit.versionNo" label="VersionNo"/>
 		<s:textfield name="visit.duration" label="Duration"/> 		
 		<s:textarea name="visit.comments" label="Comments" cols="40" rows="10"/>
 		<s:textfield name="visit.topInd" label="TopInd"/> 		
 		<s:submit value="Search Visit"/>
 	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Search visit <s:property value="returnCode" /><br/><br/> 
	 	<table class="Visit info list">
    		<tr class="even">
        	<td><b>VisitationId</b></td>
        	<td><b>VersionNo</b></td>
        	<td><b>Duration</b></td>
        	<td><b>Comments</b></td>
        	<td><b>TopInd</b></td>        	        	
        	<td><b>Operation</b></td>
    		</tr>
    		<s:iterator value="visitList" status="visitStatus">
            	<tr
                	class="<s:if test="#visitStatus.odd == true ">odd</s:if><s:else>even</s:else>">
                	<td><s:property value="visitationId" /></td>                	
                	<td><s:property value="versionNo" /></td>
                	<td><s:property value="duration" /></td>
                	<td><s:property value="comments" /></td>
                	<td><s:property value="topInd" /></td>                	
                	<s:if test="topInd==1">       	
                		<td><a href="<s:url action='linkEditVisit'>
							<s:param name="visit.visitationId" value="%{visitationId}"/>						
							</s:url>">Edit</a></td>  
					</s:if>              	
            	</tr>
			</s:iterator>
	 	</table>	 	
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Search visit <s:property value="returnCode" /><br/><br/>
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
