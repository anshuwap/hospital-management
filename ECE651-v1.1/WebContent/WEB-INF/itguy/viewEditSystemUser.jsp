<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="/WEB-INF/mis/ITGuyMenuHeader.jsp"/>

<body> 
<s:form name="searchSystemUserForm" action="searchSystemUserAction" method="post" namespace="/systemuser" onsubmit="">
  <s:token name="token"></s:token>
  <table>
    <tr>
      <td><s:textfield name="searchContent" label="System User" required="true" theme="simple" /></td>
      <td><s:select name="searchType" list="#{'userName':'User Name', 'userId':'User ID'}" theme="simple"/>
	  <td><s:submit name="searchAll" value="search" theme="simple" /></td>
	  <td><s:submit name="searchAll" value="View all users" theme="simple" /></td>
    </tr>
  </table>	
</s:form>

<s:if test='retrieveSystemUsers!=null'>
   <s:if test='retrieveSystemUsers.size()==1'>
      <s:form name="editPatientForm" action="editSystemUserAction" method="post" namespace="/systemuser">
 		<table border="1">
 		   <s:textfield name="systemUser.systemUserId" value="%{retrieveSystemUsers[0].systemUserId}" label="System User ID (ReadOnly)" readonly="true"/>
 		   <s:textfield name="systemUser.username" value="%{retrieveSystemUsers[0].username}" label="User Name" required="true"/>
 		   <s:textfield name="systemUser.password" value="%{retrieveSystemUsers[0].password}" label="Password" required="true"/>
 		   <s:textfield name="systemUser.firstName" value="%{retrieveSystemUsers[0].firstName}" label="First Name" required="true"/>
 		   <s:textfield name="systemUser.lastName" value="%{retrieveSystemUsers[0].lastName}" label="Last Name" required="true"/>
 		   <s:radio name="systemUser.gender" value="%{retrieveSystemUsers[0].gender}" list="#{'M':'male' ,'F': 'female'}" label="Gender" required="true"/>
 		   <s:select name="systemUser.roleType" value="%{retrieveSystemUsers[0].roleType}" list="#{'D':'Doctor', 'N':'Nurse', 'L':'Lawyer', 'I':'IT adminstrator'}" label="Role Type" required="true" />
 		   <s:textfield name="systemUserBirthday" value="%{retrieveSystemUsers[0].birthday.toString().substring(0,10)}" label="Birthday" />
 		   <s:textfield name="systemUser.email" value="%{retrieveSystemUsers[0].email}" label="Email" />
 		   <s:textfield name="systemUser.phone" value="%{retrieveSystemUsers[0].phone}" label="Phone" />
 		   <s:textfield name="systemUser.sin" value="%{retrieveSystemUsers[0].sin}" label="SIN" />
 		   <s:select name="systemUser.active" value="%{retrieveSystemUsers[0].active}" list="#{'A':'Active', 'D':'De-Active'}" label="Status" required="true" />
 		   <s:submit value="Update"/>
 		</table> 		
	   </s:form>
   </s:if>
   <s:else>
   	   <table border="1">
	      <tr>
			<td>User ID</td>
			<td>User Name</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Role Type</td>
			<td>view/edit</td>
		 </tr>
	     <s:iterator value="retrieveSystemUsers">
	     <tr>
			<td><s:property value="systemUserId" /></td>
			<td><s:property value="username" /></td>
			<td><s:property value="firstName" /></td>
			<td><s:property value="lastName" /></td>
			<td><s:property value="roleType" /></td>
			<td><a href="<s:url value='/systemuser/searchSystemUserAction.action'>
								<s:param name="searchType">userId</s:param> 
								<s:param name="searchAll">false</s:param> 
	                            <s:param name="searchContent" value="systemUserId" />
	                            </s:url>">View/Edit</a></td>
		 </tr>
	     </s:iterator>
	     </table>
   </s:else>
</s:if>
 
Operation Status: <s:property value="operationStatus"/><br>

</body>
</html>