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
	<h3>Patient Personal Information UserId: <s:property value="assignPatient.userId"/></h3>
	<hr>
 	<s:form action="staffUpdatePatient" method="post" namespace="/">
 	
 	<s:hidden name="assignPatient.userId"/>
 	<table border="1">
	<tr><td>First  Name:</td><td><s:property value="assignPatient.fName" /><td></tr>
	<tr><td>Middle Name:</td><td><s:property value="assignPatient.mName"/></td></tr>
	<tr><td>Last   Name:</td><td><s:property value="assignPatient.lName"/></td></tr>

	<s:textfield name="assignPatient.address.addressLine" label="AddressLine"/>
	<s:textfield name="assignPatient.address.city" label="City"/>
	<s:textfield name="assignPatient.address.province" label="Province"/>
	<s:textfield name="assignPatient.address.postCode" label="PostCode"/>
	<s:textfield name="assignPatient.phone" label="Phone" />
	<s:textfield name="assignPatient.Email" label="Email" />
 	
	<s:submit value="Update Patient"/>
	</table>
	</s:form>	 
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
<!-- 	 
	<s:if test="returnCode=='success'">           
      <hr>Update Patient info <s:property value="returnCode" /> ! <br/><br/> 
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Update Patient <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
-->     
	 <a href="<%=path%>/returnToStaffPatientSearch.action">Return to main menu</a><br>
  </body>
</html>
