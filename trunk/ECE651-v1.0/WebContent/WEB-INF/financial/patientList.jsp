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
  	<!--<h2>Part 1: Input form</h2>-->
	<h3>Patient List</h3>
	<hr>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">      
       	<td><b>Patient Number: <s:property value="patientNum" /></b></td>
	 	<table class="Patient visit times" border="1">
    		<tr class="odd">
            <th>List No.</th>
        	<td><b>Patient Id</b></td>
        	<td><b>Visit Times</b></td>
    		</tr>
    		<s:iterator value="visitNumMap" status="userStatus">
            	<tr
                	class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>"
                >
                            <th><s:property value="#userStatus.index"/></th>
                            <td><s:property value="key"/></td>
                            <td><s:property value="value"/></td>
                	
            	</tr>
			</s:iterator>
	 	</table>	 
	      
	 	<table class="Patient info list" border="1">
    		<tr class="odd">
        	<td><b>Patient Id</b></td>
        	<td><b>OHIP</b></td>
        	<td><b>Default Doctor Id</b></td>
        	<td><b>Curr Health</b></td>
        	<td><b>Comments</b></td>
    		</tr>
    		<s:iterator value="patientListresultlines" status="userStatus">
            	<tr
                	class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>"
                >
                	<td><s:property value="userId" /></td>
                	<td><s:property value="ohip" /></td>
                	<td><s:property value="defaultDoctor.userId" /></td>
                	<td><s:property value="currHealth" /></td>
                	<td><s:property value="comments" /></td>
            	</tr>
			</s:iterator>
	 	</table>	 
 	<s:form action="GetPatientRecord" method="post" namespace="/">
 		<s:textfield name="patientId" label="Patient Id" required="true"/>
 		<s:textfield name="doctor.userId" label="Doctor Id" required="true" readonly="true"/>
 		<sx:datetimepicker name="fromDate" label="Start Date" displayFormat="yyyy-MM-dd"/>
 		<sx:datetimepicker name="toDate" label="End Date" displayFormat="yyyy-MM-dd"/>
 		<s:submit value="Get Visitation Record"/>
	 </s:form>	 
	 		
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>List Patient <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/searchDoctorPage.action">Return to Search Doctor Page</a><br>

  </body>
</html>
