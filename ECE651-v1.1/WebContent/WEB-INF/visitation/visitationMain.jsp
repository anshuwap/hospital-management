<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 
  <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
   
  <body>
  <s:debug></s:debug>
  <br> 
  <table width="300">
     <tr>
    <td><a href="<s:url value='patient/searchForViewPatient.action'>
        <s:param name="healthCardID" value="visitation.patient.healthCardId"/>  
        </s:url>">To Patient Page</a>
     </td>
      <s:if test="visitation.appointment!=null">
       <td><a href="<s:url value='appointment/searchAppointmentFromVisitation.action'>
        <s:param name="searchType" value="#session.AppointmentSearchType"/>  
        <s:param name="searchContent" value="%{visitation.appointment.appointmentId}"/>
        </s:url>">To Appointment Page</a></td>
      </s:if>
      </tr>
   </table>
   
   <s:if test='#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
   <s:form action="editVisitation" method="post" namespace="/visitation">
    <table border="1" align="center">
     <caption>VISITATION</caption> 
 		<s:hidden value="%{visitation.visitationId}" label="Visitation ID" readonly="true"/>
 		<s:textfield value="%{visitation.patient.patientName}" label="Patient Name" readonly="true" />
 		<s:textfield value="%{visitation.patient.healthCardId}" label="Patient HealthCard ID" readonly="true" />
 		<s:textfield value="%{visitation.doctor.firstName}" label="Doctor Name" readonly="true" />
   		<s:textfield value="%{visitation.visitationDate}" label="Visitation Date" displayFormat="yyyy-MM-dd" readonly="true"/> 
 		<s:textarea name="visitation.symptomDescription" label="Symptom" cols="40" rows="10"/>
        <s:textarea name="visitation.diagnosisResult" label="Diagnosis Result" cols="40" rows="10" />
 		   <s:submit value="Update"/>	  	 
   	</table>
   	 </s:form>
   	</s:if>
   	
   	<s:else>
   	  <table border="1" align="center">
   	    <caption>VISITATION</caption>
 		<s:hidden value="%{visitation.visitationId}" label="Visitation ID" readonly="true"/>
 		<s:textfield value="%{visitation.patient.patientName}" label="Patient Name" readonly="true" />
 		<s:textfield value="%{visitation.patient.healthCardId}" label="Patient HealthCard ID" readonly="true" />
 		<s:textfield value="%{visitation.doctor.firstName}" label="Doctor Name" readonly="true" />
   		<s:textfield value="%{visitation.visitationDate}" label="Visitation Date" displayFormat="yyyy-MM-dd" readonly="true"/> 
 		<s:textarea name="visitation.symptomDescription" label="Symptom" cols="40" rows="10" readonly="true"/>
        <s:textarea name="visitation.diagnosisResult" label="Diagnosis Result" cols="40" rows="10" readonly="true"/>
   	</table>
   	</s:else>
	
       <table align="center">
       <tr>
		<td>Operation Status:</td>
		<td><s:property value="operationStatus" /></td>
		</tr>
	   </table>
	   
    <hr><br> 
    
    <s:if test='#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
     <s:form action="createDiagnosisTest" method="post" namespace="/diagnosistest">
      <table align="center">
      <caption>Issue New Diagnosis Test</caption>
       <tr>
			<s:select label="Select Test Type" name="diagnosisTest.testType"
						headerKey="1" headerValue="-- Please Select --" value=""
						list="#{'B UltraSound':'B UltraSound','Blood Test':'Blood Test','Urine Test':'Urine Test','X Ray':'X Ray',
                                'CT Scan':'CT Scan'}" /><td>
			<s:token name="token"></s:token>
			
				<s:submit value="Create New DiagnosisTest" /><td>
			</tr>
	</table>
	</s:form>
	</s:if>
	
		<s:if test="visitation.diagnosisTestSet!=null&&visitation.diagnosisTestSet.size()>0">
			<table border="1" align="center">
			<caption>View DiagnosisTest</caption>
				<tr>
					<td>Test Type</td>
					<td>Issue Doctor</td>
					<td>Issue Date</td>
					<td>View</td>
				</tr>
				<s:iterator value="diagnosisTestList">
					<tr>
						<td><s:property value="testType" /></td>
						<td><s:property value="doctor.firstName" /></td>
						<td><s:property value="issueDate"/></td>
						<td>
						    <a href="<s:url value='diagnosistest/searchDiagnosisTest.action'>
						    <s:param name="diagnosisTestId" value="diagnosisTestId"/>
                            <s:param name="visitationId" value="visitationId"/>  
                            </s:url>">View</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
	<hr><br> 
	<table align="center">	
	<caption>Treatments</caption>
	<tr>
	<td>Prescription</td>
     <s:if test='visitation.prescription==null&&#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
        <td><p style="text-align:center;"><s:a href="prescription/createPrescription.action" >Issue</s:a></p></td>
    </s:if>
    <s:elseif test="visitation.prescription!=null">
        <td><p style="text-align:center;"><a href="<s:url value='prescription/searchPrescription.action'>
						    <s:param name="prescriptionId" value="#session.CurrentVisitation.prescription.prescriptionId"/>  
                            </s:url>">View</a></p></td>
    </s:elseif>
    <s:elseif test="visitation.prescription==null">
         <td>Has Not Been Issued</td>
    </s:elseif>
    </tr>
    <tr>
    <td>Surgery</td>
    <s:if test='visitation.surgery==null&&#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
       <td><p style="text-align:center;"><s:a href="surgery/createSurgery.action" >Issue</s:a></p></td>
    </s:if>
    <s:elseif test ="visitation.surgery!=null">
      <td><p style="text-align:center;"><s:a href="surgery/searchSurgery.action" >View</s:a></p></td>
    </s:elseif>
    <s:elseif test="visitation.surgery==null">
    	<td>Has Not Been Issued</td>
    </s:elseif> 
    </tr>
    <tr>
    <td>Inpatient</td>
     <s:if test='visitation.inpatient==null&&#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>     
       <td><p style="text-align:center;"><s:a href="inpatient/createInpatient.action" >Issue</s:a></p></td>
     </s:if>
     <s:elseif test="visitation.inpatient!=null">
       <td><p style="text-align:center;"><s:a href="inpatient/searchInpatient.action" >View</s:a></p></td>
     </s:elseif>
     <s:elseif test="visitation.inpatient==null">
         <td>Has Not Been Issued</td>
     </s:elseif>
     <tr>    
     </table>	 		
  </body>
</html>
