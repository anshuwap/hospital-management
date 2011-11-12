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
	<h3>Patient Personal Information <s:property value="#session.CurrentUserLoginId"/></h3>
	<hr>
	<s:form action="editPatient" method="post" namespace="/">
 	
 	
	<!--<s:property value="#session.CurrentUserID"/>-->
		
	<s:textfield name="patient.address.addressLine" label="AddressLine"/>
	<s:textfield name="patient.address.city" label="City"/>
	<s:textfield name="patient.address.province" label="Province"/>
	<s:textfield name="patient.address.postCode" label="PostCode"/>
	<s:textfield name="patient.phone" label="Phone" />
	<s:textfield name="patient.Email" label="Email" />
 	
	<s:submit value="Update Patient"/>
	 </s:form>	

	 <s:if test="formReturnCode=='success'">           
     <hr>Update patient info <s:property value="formReturnCode" /> ! <br/><br/> 
	 
	 </s:if>
	  
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <s:elseif test="formReturnCode=='error'">
	 	<hr>Update patient <s:property value="formReturnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
      
	 <a href="<%=path%>/returnToPatientMain.action">Return to main menu</a><br>
  </body>
</html>
