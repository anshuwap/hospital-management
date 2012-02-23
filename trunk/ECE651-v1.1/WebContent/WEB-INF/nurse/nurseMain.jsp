<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <jsp:include page="/WEB-INF/mis/NurseMenuHeader.jsp"/>

  <body>
  <s:form action="searchForViewPatient" method="post" namespace="/patient">
    <table align="center">
    <caption>Search For Patients</caption>
      <tr>
        <td>HealthCardID: </td>
		<td><s:textfield name="healthCardID" theme="simple"/></td>
		<td><s:submit value="Search" theme="simple"/></td>
	  </tr>
	</table>
  </s:form>
  </body>
  
</html>
