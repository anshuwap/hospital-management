<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
   <s:debug></s:debug>
</head>
  
  <body> 
  <jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
  <hr><br>
   <s:a href="patient/toMainPage.action">Back</s:a>
 
  <s:form action="searchForViewPatient" method="post" namespace="/patient">
                  <s:textfield name="healthCardID" label="HealthCardID"/>
                  <s:token name="token"></s:token>
 		          <s:submit value="Search"/>
                  </s:form>
  <h2>View Patient</h2><br>
 		<table border="1">
 		<s:textfield name="retrievePatient.patientId" label="PatientID" readonly="true"/>
 		<s:textfield name="retrievePatient.patientName" label="Patient Name" readonly="true" />
 		<s:if test="retrievePatient==null">
 		  <div><s:textfield name="retrievePatient.gender"  label="Gender" value="" readonly="true"/></div>
 		</s:if>
 		<s:elseif test='retrievePatient.gender=="1"'>
 		  <div><s:textfield name="retrievePatient.gender"  label="Gender" value="Male" readonly="true"/></div>
 		  </s:elseif>
 		<s:elseif test='retrievePatient.gender=="0"'>
 		  <div><s:textfield name="retrievePatient.gender"  label="Gender" value="Female" readonly="true"/></div>
 		  </s:elseif>
   		<s:textfield name="patientBirthday" label="Patient Birthday" displayFormat="yyyy-MM-dd" readonly="true"/> 
 		<s:textfield name="retrievePatient.healthCardId" label="HealthCard ID" readonly="true"/>
 		<s:textarea name="retrievePatient.medication" label="Medication" cols="40" rows="10" readonly="true"/>
        <s:textarea name="retrievePatient.allergy" label="Allergy" cols="40" rows="10" readonly="true"/>
 		</table>
<br>
 Operation Status: <s:property value="operationStatus"/><br>
 
 <s:if test='roleName=="Doctor"&&retrievePatient!=null'>
 <s:a href="visitation/createVisitation.action" >Create New Visitation</s:a>
 </s:if>

 
 
</body>
</html>
