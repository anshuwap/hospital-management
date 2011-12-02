<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
    
	<body>
		<s:form action="searchForViewPatient" method="post"
			namespace="/patient">
			<table align="center">
				<tr>
					<td>
						HealthCardID:
					</td>
					<td>
						<s:textfield name="healthCardID" theme="simple" />
					</td>
					<td>
						<s:submit value="Search" theme="simple" />
					</td>
					
				</tr>
			</table>
		</s:form>
		<table align="center">
		<tr>
		   <td><s:property value="operationStatus" /></td>
		</tr>
		</table>

		<hr><br> 
		<table border="1" align="center">
			<caption>
				Patient Information
			</caption>
			<s:hidden name="retrievePatient.patientId" label="PatientID"
				readonly="true" />
			<s:textfield name="retrievePatient.version" label="Version (ReadOnly)" readonly="true"/>
			<s:textfield name="retrievePatient.lastUpdtUser" label="LastUpdtUser (ReadOnly)" readonly="true"/>
			<s:textfield name="retrievePatient.patientName" label="Patient Name"
				readonly="true" />
			<s:if test="retrievePatient==null">
				<div>
					<s:textfield name="retrievePatient.gender" label="Gender" value=""
						readonly="true" />
				</div>
			</s:if>
			<s:elseif test='retrievePatient.gender=="1"'>
				<div>
					<s:textfield name="retrievePatient.gender" label="Gender"
						value="Male" readonly="true" />
				</div>
			</s:elseif>
			<s:elseif test='retrievePatient.gender=="0"'>
				<div>
					<s:textfield name="retrievePatient.gender" label="Gender"
						value="Female" readonly="true" />
				</div>
			</s:elseif>
			<s:textfield name="patientBirthday" label="Patient Birthday"
				displayFormat="yyyy-MM-dd" readonly="true" />
			<s:textfield name="retrievePatient.healthCardId"
				label="HealthCard ID" readonly="true" />
			<s:textarea name="retrievePatient.medication" label="Medication"
				cols="40" rows="10" readonly="true" />
			<s:textarea name="retrievePatient.allergy" label="Allergy" cols="40"
				rows="10" readonly="true" />
		</table>

		<br>

		<table align="center">
			<s:if
				test='#session.CurrentUser.roleType=="D"&&retrievePatient!=null'>
				<s:a href="visitation/createVisitation.action">Create New Visitation</s:a>
			</s:if>

			<s:if
				test='#session.CurrentUser.roleType=="N"&&retrievePatient!=null'>
				<s:a href="appointment/toCreateAppointmentPage.action">Create New Appointment</s:a>
			</s:if>
			
			<s:if test='#session.CurrentUser.roleType=="L"&&retrievePatient!=null'>
			    <s:a href="audittrail/searchAuditTrailByPatientAndTable.action">View Patient Revision History</s:a>
			</s:if>
			
		</table>

		<br>
		<s:if test="patientVisitation!=null">
			<table border="1" align="center">
				<tr>
					<td>
						VisitationDate
					</td>
					<td>
						Doctor
					</td>
					<td>
						View
					</td>
				</tr>
				<s:iterator value="patientVisitation">
					<tr>
						<td>
							<s:property value="visitationDate" />
						</td>
						<td>
							<s:property value="doctor.firstName" />
						</td>
						<td>
							<a
								href="<s:url value='/visitation/searchVisitation.action'>
                            <s:param name="visitationId" value="visitationId"/>  
                            </s:url>">View</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
	</body>
</html>
