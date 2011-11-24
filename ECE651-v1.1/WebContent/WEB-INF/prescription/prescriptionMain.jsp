<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <body>
  <br> 
  <a href="<s:url value='visitation/searchVisitation.action'>
                            <s:param name="visitationId" value="#session.CurrentVisitation.visitationId"/>  
                            </s:url>">Back To Visitation Page</a>

		<s:if
			test='#session.CurrentUser.username!=#session.CurrentVisitation.doctor.username'>
			<table border="1" align="center">
				<caption>
					Prescription
				</caption>
				<s:textfield name="prescription.patient.patientName"
					label="Patient Name" readonly="true" />
				<s:textfield name="prescription.patient.healthCardId"
					label="Patient HealthCard ID" readonly="true" />
				<s:textfield name="prescription.doctor.firstName"
					label="Doctor Firstname" readonly="true" />
				<s:textfield name="prescription.PrescriptionDate"
					label="Prescription Date" displayFormat="yyyy-MM-dd"
					readonly="true" />
				<s:textarea name="prescription.PrescriptionDescription"
					label="Prescription Description" cols="40" rows="10"
					readonly="true" />
			</table>
		</s:if>

		<s:else>
			<s:form action="editPrescription" method="post"
				namespace="/prescription">
				<table border="1" align="center">
					<caption>
						Prescription
					</caption>
					<s:textfield name="prescription.patient.patientName"
						label="Patient Name" readonly="true" />
					<s:textfield name="prescription.patient.healthCardId"
						label="Patient HealthCard ID" readonly="true" />
					<s:textfield name="prescription.doctor.firstName"
						label="Doctor Firstname" readonly="true" />
					<s:textfield name="prescription.PrescriptionDate"
						label="Prescription Date" displayFormat="yyyy-MM-dd"
						readonly="true" />
					<s:textarea name="prescription.PrescriptionDescription"
						label="Prescription Description" cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Update" />
				</table>
			</s:form>
		</s:else>

	</body>
</html>
