<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>

  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <body>
  <br> 
  <a href="<s:url value='visitation/searchVisitation.action'>
                            <s:param name="visitationId" value="#session.CurrentVisitation.visitationId"/>  
                            </s:url>">Back To Visitation Page</a>

		<s:if
			test='#session.CurrentUser.roleType!="N"&&#session.CurrentUser.roleType!="D"'>
			<table border="1" align="center">
				<caption>
					Surgery
				</caption>
				<s:textfield name="surgery.patient.patientName" label="Patient Name"
					readonly="true" />
				<s:textfield name="surgery.patient.healthCardId"
					label="Patient HealthCard ID" readonly="true" />
				<s:textfield name="surgery.issueDoctor.firstName"
					label="Issue Doctor" readonly="true" />
				<s:textfield name="surgeryDate" label="Surgery Date"
					displayFormat="yyyy-MM-dd" readonly="true" />
				<s:textarea name="surgery.arrangementDescription"
					label="Surgery Arrangement" cols="40" rows="10" readonly="true" />
				<s:textfield name="surgery.nurse.firstName" label="Arranged Nurse" readonly="true"/>	
				<s:textarea name="surgery.surgerySummary" label="Surgery Summary"
					cols="40" rows="10" readonly="true" />
				<s:textfield name="surgery.surgetyDoctor.firstName"
					label="Surgery Doctor" readonly="true" />
			</table>
		</s:if>

		<s:elseif test='#session.CurrentUser.roleType=="N"'>
			<s:form action="editSurgery" method="post" namespace="/surgery">
				<table border="1" align="center">
					<caption>
						Surgery
					</caption>
					<s:textfield name="surgery.patient.patientName"
						label="Patient Name" readonly="true" />
					<s:textfield name="surgery.patient.healthCardId"
						label="Patient HealthCard ID" readonly="true" />
					<s:textfield name="surgery.issueDoctor.firstName"
						label="Issue Doctor" readonly="true" />
					<sx:datetimepicker name="surgeryDate" label="Surgery Date"
						displayFormat="yyyy-MM-dd" />
					<s:textarea name="surgery.arrangementDescription"
						label="Surgery Arrangement" cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Update" />
				</table>
			</s:form>
		</s:elseif>

		<s:if test='#session.CurrentUser.roleType=="D"'>
			<s:form action="editSurgery" method="post" namespace="/surgery">
				<table border="1" align="center">
					<caption>
						Surgery
					</caption>
					<s:textfield name="surgery.patient.patientName"
						label="Patient Name" readonly="true" />
					<s:textfield name="surgery.patient.healthCardId"
						label="Patient HealthCard ID" readonly="true" />
					<s:textfield name="surgery.issueDoctor.firstName"
						label="Issue Doctor" readonly="true" />
					<s:textfield name="surgeryDate" label="Surgery Date"
						displayFormat="yyyy-MM-dd" readonly="true" />
					<s:textarea name="surgery.arrangementDescription"
						label="Surgery Arrangement" cols="40" rows="10" readonly="true" />
					<s:textfield name="surgery.nurse.firstName" label="Arranged Nurse" readonly="true"/>	
					<s:textarea name="surgery.surgerySummary" label="Surgery Summary"
						cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Update" />
				</table>
			</s:form>
		</s:if>
		</table>	     		
  </body>
</html>

