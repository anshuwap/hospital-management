<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<s:debug></s:debug>
	</head>

	<body>
		<jsp:include page="/WEB-INF/mis/loginHeader.jsp" />
		<hr>
		<br>
		<a
			href="<s:url value='visitation/searchVisitation.action'>
                            <s:param name="visitationId" value="#session.CurrentVisitation.visitationId"/>  
                            </s:url>">Back To Visitation Page</a>

		<h2>
			Diagnosis Test
		</h2>
		<br>
       <table border="1">
		<s:textfield name="diagnosisTest.patient.patientName"
			label="Patient Name" readonly="true" /><tr>
		<s:textfield name="diagnosisTest.patient.healthCardId"
			label="Patient HealthCard ID" readonly="true" /><tr>
		<s:textfield name="diagnosisTest.doctor.firstName"
			label="Issued Doctor Name" readonly="true" /><tr>
		<s:textfield name="diagnosisTest.issueDate" label="Issued Date"
			readonly="true" /><tr>		
		<s:if test='#session.CurrentUser.roleType=="D"&&diagnosisTest.testType==""&&diagnosisTest.testRequestDescription==""'>
			<s:form action="editDiagnosisTest" method="post" namespace="/diagnosistest">
			<s:select label="Select Test Type" name="diagnosisTest.testType"
						headerKey="1" headerValue="-- Please Select --" value=""
						list="#{'1':'B UltraSound','2':'Blood Test','3':'Urine Test','4':'X Ray',
                                '4':'CT Scan'}" /><tr>
            <s:textarea name="diagnosisTest.testRequestDescription"
					label="Test Request Description" cols="40" rows="10" /><tr>
			<s:token name="token"></s:token>
			<tr>
				<s:submit value="Submit" /><td>
			</tr>
			</s:form>
		</s:if>
		<s:else>
			
		</s:else>
		
		
		
		
		<s:else>
			<s:textfield name="diagnosisTest.testType" label="Test Type"
				readonly="true" /><tr>
			<s:textarea name="diagnosisTest.testRequestDescription"
				label="Test Request Description" cols="40" rows="10" readonly="true" /><tr>
		</s:else>
		</table>	
		<s:if test='#session.CurrentUser.roleType!="N"'>
		<table border ="1">
			<s:textfield name="diagnosisTest.nurse.firstName"
				label="Test Operation Nurse" readonly="true" /><tr>
			<s:textarea name="diagnosisTest.testResultDescription"
				label="Test Result Description" cols="40" rows="10" readonly="true" /><tr>
		</table>
		</s:if>
		<s:else>
        <table border="1">
			<s:form action="editDiagnosisTestByNurse" method="post"
				namespace="/diagnosistest">
				<s:textarea name="diagnosisTest.testResultDescription"
					label="Test Result Description" cols="40" rows="10" /><tr>
				<s:token name="token"></s:token>
				<tr>
				<s:submit value="Update" /><td>
				<tr>
			</s:form>
         </table>
		</s:else>

	</body>
</html>
