<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <s:debug></s:debug>
   <script type="text/javascript" language="javascript">
function ClearForm(){
    document.createSystemUserForm.reset();
}
</script>
</head>
  
  <body onload="ClearForm()"> 
    <jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
    <hr><br>
    <td><s:a href="systemuser/toMainPage.action">Back</s:a></td>
  	<s:form name="createSystemUserForm" action="createSystemUserAction" method="post" namespace="/systemuser" onsubmit="">
  	<h2>Create New System User</h2>
 		<table border="1">
 		<s:textfield name="systemUser.username" label="User Name" required="true" />
 		<s:textfield name="systemUser.firstName" label="First Name" required="true" />
 		<s:textfield name="systemUser.lastName" label="Last Name" required="true" />
 		<s:textfield name="systemUser.password" label="Password" required="true" />
 		<s:radio list="#{'M':'male' ,'F': 'female'}" name="systemUser.gender" value="M" label="Gender"/>
 		<s:select label="Role Type" name="systemUser.roleType" list="#{'D':'Doctor', 'N':'Nurse', 'L':'Lawyer', 'I':'IT adminstrator'}" value="D" required="true" />
   		<s:textfield name="systemUserBirthday" label="User Birthday (Format:yyyy-mm-dd)" displayFormat="yyyy-MM-dd"/>
        <s:token name="token"></s:token>
        <tr>
          <td><s:reset value="Reset" /></td>
 		  <td><s:submit value="Create"/></td>
 		</tr>
 		</table> 		
	 </s:form>
	<s:property value="%{#request.OperationStatus}"/> <br/>
 
</body>
</html>
