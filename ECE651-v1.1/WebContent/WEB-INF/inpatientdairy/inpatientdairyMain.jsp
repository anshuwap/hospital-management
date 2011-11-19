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
  <a href="<s:url value='inpatient/searchInpatient.action'>
                            <s:param name="inpatientId" value="#session.CurrentInpatient.inpatientId"/>  
                            </s:url>">Back To Visitation Page</a>
   
   <h2>Inpatient Dairy</h2><br>
    <table border="1">
 		<s:textfield name="inpatientDairy.patient.patientName" label="Patient Name" readonly="true"/>
 		<s:textfield name="inpatientDairy.patient.healthCardId" label="Patient HealthCard ID" readonly="true" />
 		<s:textfield name="inpatientDairy.nurse.firstName" label="Issue Nurse" readonly="true" />
   		<s:textfield name="inpatientDairy.recordDate" label="Record Date" displayFormat="yyyy-MM-dd" readonly="true"/> 
      	<s:if test='#session.CurrentUser.roleType=="N"'>
      	<s:form action="editInpatientDairy" method="post" namespace="/inpatientdairy">
           <s:textarea name="inpatientDairy.dairyDescription" label="Inpatient Dairy" cols="40" rows="10"/>
           <s:token name="token"></s:token>
           <s:if test='true'>
 		   <s:submit value="Record"/>
 		   </s:if>
        </s:form>
       </s:if>
       <s:else>
       <s:textarea name="inpatientDairy.dairyDescription" label="Inpatient Dairy" cols="40" rows="10" readonly="true"/>
       </s:else>
       
   	</table>	
 
         		
  </body>
</html>
