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
     <td><a href="<s:url value='patient/searchForViewPatient.action'>
      <s:param name="healthCardID" value="visitation.patient.healthCardId"/>  
      </s:url>">Back to View Patient Page</a></td>  
   
   <h2>Visitation</h2><br>
    <table border="1">
 		<s:textfield name="visitation.visitationId" label="PatientID" readonly="true"/>
 		<s:textfield name="visitation.patient.patientName" label="Patient Name" readonly="true" />
 		<s:textfield name="visitation.patient.healthCardId" label="Patient HealthCard ID" readonly="true" />
 		<s:textfield name="visitation.doctor.firstName" label="Doctor Firstname" readonly="true" />
   		<s:textfield name="visitation.visitationDate" label="Visitation Date" displayFormat="yyyy-MM-dd" readonly="true"/> 
      	<s:form action="editVisitation" method="post" namespace="/visitation">
           <s:textarea name="visitation.symptomDescription" label="Symptom" cols="40" rows="10"/>
           <s:textarea name="visitation.diagnosisResult" label="Diagnosis Result" cols="40" rows="10" />
           <s:token name="token"></s:token>
           <s:if test='true'>
 		   <s:submit value="Update"/>
 		   </s:if>
        </s:form>
   	</table>
   	    <br>
		Operation Status:
		<s:property value="operationStatus" />
		<br>	
    <hr><br> 
    
       	<s:form action="createDiagnosisTest" method="post" namespace="/diagnosistest">
			<s:select label="Select Test Type" name="diagnosisTest.testType"
						headerKey="1" headerValue="-- Please Select --" value=""
						list="#{'1':'B UltraSound','2':'Blood Test','3':'Urine Test','4':'X Ray',
                                '4':'CT Scan'}" /><tr>
			<s:token name="token"></s:token>
			<tr>
				<s:submit value="Create New DiagnosisTest" /><td>
			</tr>
		</s:form>
		
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
					    View
					</td>
				</tr>
				<s:iterator value="visitation.diagnosisTestSet">
					<tr>
						<td>
							<s:property value="testType" />
						</td>
						<td>
							<s:property value="doctor.firstName" />
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
		
		
       
       show the hyperlink list of Diagnosis Tests, click each will direct to the diagnosistestMain.jsp<br> 
    <hr><br> 
       <s:a href="prescription/createPrescription.action" >Issue Prescription</s:a> 
       if there already exist one prescription for the visitation, change the name of hyperlink to  View Prescription and change the action to search.
    <hr><br> 
       <s:a href="surgery/createSurgery.action" >Issue Surgery</s:a> 
       same to prescription
    <hr><br> 
       <s:a href="inpatient/createInpatient.action" >Issue Inpatient</s:a>
       same to prescription 
         		
  </body>
</html>
