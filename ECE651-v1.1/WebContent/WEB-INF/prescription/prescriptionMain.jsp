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
  </head>
  
  <body>
    <jsp:include page="/WEB-INF/mis/loginHeader.jsp"/>
  <hr><br> 
  <a href="<s:url value='visitation/searchVisitation.action'>
                            <s:param name="visitationId" value="#session.CurrentVisitation.visitationId"/>  
                            </s:url>">Back To Visitation Page</a>
   
   <h2>Prescription</h2><br>
    <table border="1">
 		<s:textfield name="prescription.patient.patientName" label="Patient Name" readonly="true"/>
 		<s:textfield name="prescription.patient.healthCardId" label="Patient HealthCard ID" readonly="true" />
 		<s:textfield name="prescription.doctor.firstName" label="Doctor Firstname" readonly="true" />
   		<s:textfield name="prescription.PrescriptionDate" label="Prescription Date" displayFormat="yyyy-MM-dd" readonly="true"/> 
      	<s:if test='#session.CurrentUser.roleType=="D"'>
      	<s:form action="editPrescription" method="post" namespace="/prescription">
           <s:textarea name="prescription.PrescriptionDescription" label="Prescription Description" cols="40" rows="10"/>
           <s:token name="token"></s:token>
           <s:if test='true'>
 		   <s:submit value="Update"/>
 		   </s:if>
        </s:form>
       </s:if>
       <s:else>
       <s:textarea name="prescription.PrescriptionDescription" label="Prescription Description" cols="40" rows="10" readonly="true"/>
       </s:else>
       
   	</table>	
 
         		
  </body>
</html>
