<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>

	<body>
		<br>
		<a
			href="<s:url value='visitation/searchVisitation.action'>
                            <s:param name="visitationId" value="#session.CurrentVisitation.visitationId"/>  
                            </s:url>">Back To Visitation Page</a>

		<br>
		<s:if test='#session.CurrentUser.roleType!="N"'>
       <table border="1" align="center">
       <caption>Diagnosis Test</caption>
		<s:textfield name="diagnosisTest.patient.patientName"
			label="Patient Name" readonly="true" /><tr>
		<s:textfield name="diagnosisTest.patient.healthCardId"
			label="Patient HealthCard ID" readonly="true" /><tr>
		<s:textfield name="diagnosisTest.doctor.firstName"
			label="Issued Doctor Name" readonly="true" /><tr>
		<s:textfield name="diagnosisTest.issueDate" label="Issued Date"
			readonly="true" /><tr>		
	    <s:textfield name="diagnosisTest.testType" label="Test Type" readonly="true" readonly="true"/><tr>
        <s:if test="diagnosisTest.nurse!=null">
        <s:textfield name="diagnosisTest.nurse.firstName"
				label="Test Operation Nurse" readonly="true" /><tr>
			<s:textarea name="diagnosisTest.testResultDescription"
				label="Test Result Description" cols="40" rows="10" readonly="true" /><tr>
		</s:if>
		</table>	
		</s:if>

		<s:else>
			<s:form action="editDiagnosisTest" method="post"
				namespace="/diagnosistest">
				<table border="1" align="center">
					<caption>
						Diagnosis Test
					</caption>
					<s:textfield name="diagnosisTest.patient.patientName"
						label="Patient Name" readonly="true" />
					<tr>
						<s:textfield name="diagnosisTest.patient.healthCardId"
							label="Patient HealthCard ID" readonly="true" />
					<tr>
						<s:textfield name="diagnosisTest.doctor.firstName"
							label="Issued Doctor Name" readonly="true" />
					<tr>
						<s:textfield name="diagnosisTest.issueDate" label="Issued Date"
							readonly="true" />
					<tr>
						<s:textfield name="diagnosisTest.testType" label="Test Type"
							readonly="true" readonly="true" />
					<tr>
					<s:if test="diagnosisTest.nurse!=null">
					 <s:textfield name="diagnosisTest.nurse.firstName"
				         label="Test Operation Nurse" readonly="true" /><tr>
				     </s:if>    
						<s:textarea name="diagnosisTest.testResultDescription"
							label="Test Result Description" cols="40" rows="10" />
					<tr>
						<s:token name="token"></s:token>
					<tr>
						<s:submit value="Update" />
					<tr>
				</table>
			</s:form>
		</s:else>

	</body>
</html>
