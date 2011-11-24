<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <body>

  <br> 
  <a href="<s:url value='inpatient/searchInpatient.action'>
                            <s:param name="inpatientId" value="#session.CurrentInpatient.inpatientId"/>  
                            </s:url>">Back To Inpatient Page</a>

		<s:if test='#session.CurrentUser.roleType!="N"'>
			<table border="1" align="center">
				<caption>
					Inpatient Dairy
				</caption>
				<s:textfield name="inpatientDairy.patient.patientName"
					label="Patient Name" readonly="true" />
				<s:textfield name="inpatientDairy.patient.healthCardId"
					label="Patient HealthCard ID" readonly="true" />
				<s:textfield name="inpatientDairy.nurse.firstName"
					label="Issue Nurse" readonly="true" />
				<s:textfield name="inpatientDairy.recordDate" label="Record Date"
					displayFormat="yyyy-MM-dd" readonly="true" />
				<s:textarea name="inpatientDairy.dairyDescription"
					label="Inpatient Dairy" cols="40" rows="10" readonly="true" />
			</table>
		</s:if>
		<s:else>
			<s:form action="editInpatientDairy" method="post"
				namespace="/inpatientdairy">
				<table border="1" align="center">
					<caption>
						Inpatient Dairy
					</caption>
					<s:textfield name="inpatientDairy.patient.patientName"
						label="Patient Name" readonly="true" />
					<s:textfield name="inpatientDairy.patient.healthCardId"
						label="Patient HealthCard ID" readonly="true" />
					<s:textfield name="inpatientDairy.nurse.firstName"
						label="Issue Nurse" readonly="true" />
					<s:textfield name="inpatientDairy.recordDate" label="Record Date"
						displayFormat="yyyy-MM-dd" readonly="true" />
					<s:textarea name="inpatientDairy.dairyDescription"
						label="Inpatient Dairy" cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Record" />
				</table>
			</s:form>
		</s:else>
	</body>
</html>
