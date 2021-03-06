<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <body> 
  	<s:form name="createSystemUserForm" action="createSystemUserAction" method="post" namespace="/systemuser">
  	    <table border="1" align="center">
  	    <caption>Create New System User</caption>
 		<s:textfield name="systemUser.username" label="User Name" required="true" />
 		<s:textfield name="systemUser.firstName" label="First Name" required="true" />
 		<s:textfield name="systemUser.lastName" label="Last Name" required="true" />
 		<s:password name="systemUser.password" label="Password" required="true" />
 		<s:radio name="systemUser.gender" value="M" list="#{'M':'male', 'F': 'female'}" label="Gender" required="true" />
 		<s:select label="Role Type" name="systemUser.roleType" list="#{'D':'Doctor', 'N':'Nurse', 'L':'Lawyer', 'I':'IT adminstrator'}" value="D" required="true" />
 		<s:textfield name="systemUserBirthday" label="User Birthday (Format:yyyy-mm-dd)" displayFormat="yyyy-MM-dd"/>
   		<s:textfield name="systemUser.email" label="Email" />
 		<s:textfield name="systemUser.phone" label="Phone" />
 		<s:textfield name="systemUser.sin" label="SIN" />
 		<s:select name="systemUser.active" list="#{'A':'Active', 'D':'De-Active'}" label="Status" required="true" />
        <s:reset value="Reset" />
        <s:submit value="Create" />	
        </table>
	 </s:form>

<table align="center">Operation Status: <s:property value="operationStatus"/></table><br>
</body>
</html>
