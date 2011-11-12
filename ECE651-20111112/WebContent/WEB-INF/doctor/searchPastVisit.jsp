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
	<h3>Search Past Visitation</h3>
	<hr>
 	<s:form action="searchPastVisit" method="post" namespace="/">
 		<s:textfield name="patient.fName" label="First Name"/>
 		<s:textfield name="patient.lName" label="Last Name"/>
 		<!--<s:textfield name="visitDate" label="VisitDate"/>-->
 		<sx:datetimepicker name="visitDate" label="VisitDate" displayFormat="yyyy-MM-dd"/>
 		<s:textfield name="diagnosisinfo" label="Diagnosis"/> 		
 		<s:textarea name="commentsinfo" label="Comments" />
 		<s:textfield name="prescriptioninfo" label="Prescriptions"/> 
 		<s:textfield name="surgeryinfo" label="Surgery"/>		
 		<s:submit value="Search"/>	
	 </s:form>
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Search visit <s:property value="returnCode" /><br/><br/> 
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Search visit <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToDoctorMain.action">Return to doctor main menu</a><br>
  </body>
</html>
