<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
<body>
  <div class="menucontainer">
    <div class="menu">
	  <ul>
	    <li><s:a href="systemuser/toMainPage.action">Back to Main</s:a></li>
	    <li><s:a href="systemuser/toCreateSystemUserPage.action">Create User</s:a></li>
	    <li><s:a href="systemuser/toViewEditSystemUser.action">View/Edit User</s:a></li>
	  </ul>
	</div>
  </div>
</body>


