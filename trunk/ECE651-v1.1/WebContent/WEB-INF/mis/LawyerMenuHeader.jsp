<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
<div class="menucontainer">
  <div class="menu">
	<ul>
	  <li><s:a href="toMainPage.action">Back to Main</s:a></li>
	  <li><s:a href="patient/toViewPatientPage.action">View Patient</s:a></li>
	</ul>
  </div>
</div>