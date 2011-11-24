<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
<div class="menucontainer">
  <div class="menu">
	<ul>
	  <li><s:a href="toMainPage.action">Back to Main</s:a></li>
	  <li><s:a href="patient/toEditPatientPage.action">Edit Patient</s:a></li>
	  <li><s:a href="patient/toViewPatientPage.action">View Patient</s:a></li>
	  <li><a href="<s:url value='appointment/searchAppointment.action'>
								<s:param name="searchType">docID</s:param> 
								<s:param name="searchAll">false</s:param> 
	                            <s:param name="searchContent" value="#session.CurrentUser.systemUserId" />
	                            </s:url>">View Appointments</a></li>
	</ul>
  </div>
</div>