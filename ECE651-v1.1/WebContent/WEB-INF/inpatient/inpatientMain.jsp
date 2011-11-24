<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
  
  <body>
  <br> 
  <a href="<s:url value='visitation/searchVisitation.action'>
                            <s:param name="visitationId" value="#session.CurrentVisitation.visitationId"/>  
                            </s:url>">Back To Visitation Page</a>
   
		<s:if test='#session.CurrentUser.roleType!="N"&&#session.CurrentUser.roleType!="D"'>
			<table border="1" align="center">
				<caption>
					Inpatient
				</caption>
				<s:textfield name="inpatient.patient.patientName"
					label="Patient Name" readonly="true" />
				<s:textfield name="inpatient.patient.healthCardId"
					label="Patient HealthCard ID" readonly="true" />
				<s:textfield name="inpatient.issueDoctor.firstName"
					label="Issue Doctor" readonly="true" />
				<s:if test="inpatient.nurse!=null">
					<s:textfield name="inpatientDay" label="Inpatient Date"
						displayFormat="yyyy-MM-dd" readonly="true" />
					<s:textfield name="dischargeDay" label="Discharge Date"
						displayFormat="yyyy-MM-dd" readonly="true" />
					<s:textarea name="inpatient.arrangementDescription"
						label="Inpatient Arrangement" cols="40" rows="10" readonly="true" />
					<s:textfield name="inpatient.nurse.firstName" label="Record Nurse"
						readonly="true" />
				</s:if>
				<s:if test="inpatient.inpatientDoctor!=null">
					<s:textarea name="inpatient.dischargeSummary"
						label="Discharge Summary" cols="40" rows="10" readonly="true" />
					<s:textfield name="inpatient.inpatientDoctor.firstName"
						label="Record Doctor" readonly="true" />
				</s:if>
				<s:property value="operationStatus" />
			</table>
		</s:if>

		<s:elseif test='#session.CurrentUser.roleType=="N"'>
			<s:form action="editInpatient" method="post" namespace="/inpatient">
				<table border="1" align="center">
					<caption>
						Inpatient
					</caption>
					<s:textfield name="inpatient.patient.patientName"
						label="Patient Name" readonly="true" />
					<s:textfield name="inpatient.patient.healthCardId"
						label="Patient HealthCard ID" readonly="true" />
					<s:textfield name="inpatient.issueDoctor.firstName"
						label="Issue Doctor" readonly="true" />
					<sx:datetimepicker name="inpatientDay" label="Inpatient Date"
						displayFormat="yyyy-MM-dd" />
					<sx:datetimepicker name="dischargeDay" label="Discharge Date"
						displayFormat="yyyy-MM-dd" />
					<s:textarea name="inpatient.arrangementDescription"
						label="Inpatient Arrangement" cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Update" />
					<s:property value="operationStatus" />
				</table>
			</s:form>
		</s:elseif>


		<s:elseif
			test='#session.CurrentUser.roleType=="D"'>
			<s:form action="editInpatient" method="post" namespace="/inpatient">
				<table border="1" align="center">
					<caption>
						Inpatient
					</caption>
					<s:textfield name="inpatient.patient.patientName"
						label="Patient Name" readonly="true" />
					<s:textfield name="inpatient.patient.healthCardId"
						label="Patient HealthCard ID" readonly="true" />
					<s:textfield name="inpatient.issueDoctor.firstName"
						label="Issue Doctor" readonly="true" />
					<s:textfield name="inpatientDay" label="Inpatient Date"
						displayFormat="yyyy-MM-dd" readonly="true" />
					<s:textfield name="dischargeDay" label="Discharge Date"
						displayFormat="yyyy-MM-dd" readonly="true" />
					<s:textarea name="inpatient.arrangementDescription"
						label="Inpatient Arrangement" cols="40" rows="10" readonly="true" />
					<s:textfield name="inpatient.nurse.firstName" label="Record Nurse"
						readonly="true" />
					<s:textarea name="inpatient.dischargeSummary"
						label="Discharge Summary" cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Update" />
					<s:property value="operationStatus" />
				</table>
			</s:form>
		</s:elseif>

   		
 <hr><br>
 		<s:if test='#session.CurrentUser.roleType=="N"'>
 		<table align="center" >
			<s:a href="inpatientdairy/createInpatientDairy.action">Create New Inpatient Dairy</s:a><br>
			</table>
		</s:if>
        
        <s:if test="currentInpatientDairySet!=null">
			<table border="1" align="center">
				<tr>
					<td>
						Record Nurse
					</td>
					<td>
					    Record Date
					</td>
					<td>
					    View
					</td>
				</tr>
				<s:iterator value="currentInpatientDairySet">
					<tr>
						<td>
							<s:property value="nurse.firstName" />
						</td>
						<td>
						    <s:property value="recordDate"/>
						</td>
						<td>
						    <a href="<s:url value='inpatientdairy/searchInpatientDairy.action'>
						    <s:param name="inpatientDairyId" value="inpatientDairyId"/>
                            <s:param name="inpatientId" value="inpatientId"/>  
                            </s:url>">View</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
        
         		
  </body>
</html>


