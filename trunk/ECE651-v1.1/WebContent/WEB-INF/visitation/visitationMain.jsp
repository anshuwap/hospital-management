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
     <a href="<s:url value='patient/searchForViewPatient.action'>
      <s:param name="healthCardID" value="visitation.patient.healthCardId"/>  
      </s:url>">Back to View Patient Page</a>  
   
   <h2>Visitation</h2><br>
    <table border="1">
 		<s:textfield name="visitation.visitationId" label="PatientID" readonly="true"/>
 		<s:textfield name="visitation.patient.patientName" label="Patient Name" readonly="true" />
 		<s:textfield name="visitation.patient.healthCardId" label="Patient HealthCard ID" readonly="true" />
 		<s:textfield name="visitation.doctor.firstName" label="Doctor Firstname" readonly="true" />
   		<s:textfield name="visitation.visitationDate" label="Visitation Date" displayFormat="yyyy-MM-dd" readonly="true"/> 
      	<s:if test='#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
      	<s:form action="editVisitation" method="post" namespace="/visitation">
           <s:textarea name="visitation.symptomDescription" label="Symptom" cols="40" rows="10"/>
           <s:textarea name="visitation.diagnosisResult" label="Diagnosis Result" cols="40" rows="10" />
           <s:token name="token"></s:token>
 		   <s:submit value="Update"/>
 		   </s:form>
 		</s:if>
        <s:else>
        <s:textarea name="visitation.symptomDescription" label="Symptom" cols="40" rows="10" readonly="true"/>
        <s:textarea name="visitation.diagnosisResult" label="Diagnosis Result" cols="40" rows="10" readonly="true"/>
        </s:else>
   	</table>
   	    <br>
		Operation Status:
		<s:property value="operationStatus" />
		<br>	
    <hr><br> 
    
    <s:if test='#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
       	<s:form action="createDiagnosisTest" method="post" namespace="/diagnosistest">
			<s:select label="Select Test Type" name="diagnosisTest.testType"
						headerKey="1" headerValue="-- Please Select --" value=""
						list="#{'B UltraSound':'B UltraSound','Blood Test':'Blood Test','Urine Test':'Urine Test','X Ray':'X Ray',
                                'CT Scan':'CT Scan'}" /><tr>
			<s:token name="token"></s:token>
			<tr>
				<s:submit value="Create New DiagnosisTest" /><td>
			</tr>
		</s:form>
	</s:if>
		
		<s:if test="visitation.diagnosisTestSet!=null">
			<table border="1">
				<tr>
					<td>
						Test Type
					</td>
					<td>
						Issue Doctor
					</td>
					<td>
					    Issue Date
					</td>
					<td>
					    View
					</td>
				</tr>
				<s:iterator value="diagnosisTestList">
					<tr>
						<td>
							<s:property value="testType" />
						</td>
						<td>
							<s:property value="doctor.firstName" />
						</td>
						<td>
						    <s:property value="issueDate"/>
						</td>
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
    <s:if test='visitation.prescription==null&&#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
       <s:a href="prescription/createPrescription.action" >Issue Prescription</s:a> 
    </s:if>
    <s:else>
        <a href="<s:url value='prescription/searchPrescription.action'>
						    <s:param name="prescriptionId" value="#session.CurrentVisitation.prescription.prescriptionId"/>  
                            </s:url>">View Prescription</a>
    </s:else>
    <hr><br> 
    <s:if test='visitation.surgery==null&&#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
       <s:a href="surgery/createSurgery.action" >Issue Surgery</s:a> 
    </s:if>
    <s:else>
      <s:a href="surgery/searchSurgery.action" >View Surgery</s:a> 
    </s:else>
    <hr><br> 
     <s:if test='visitation.inpatient==null&&#session.CurrentUser.username==#session.CurrentVisitation.doctor.username'>
       <s:a href="inpatient/createInpatient.action" >Issue Inpatient</s:a>
     </s:if>
     <s:else>
       <s:a href="inpatient/searchInpatient.action" >View Inpatient</s:a>
     </s:else>    		
  </body>
</html>
