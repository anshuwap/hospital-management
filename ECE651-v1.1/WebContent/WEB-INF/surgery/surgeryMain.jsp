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
   
   <h2>Surgery</h2><br>
    <table border="1">
			<s:textfield name="surgery.patient.patientName" label="Patient Name" readonly="true" />
			<s:textfield name="surgery.patient.healthCardId" label="Patient HealthCard ID" readonly="true" />
			<s:textfield name="surgery.issueDoctor.firstName" label="Issue Doctor" readonly="true" />
			
			<s:if test='#session.CurrentUser.roleType=="N"'>
				<s:form action="editSurgery" method="post" namespace="/surgery">
					<s:textfield name="surgery.surgeryDate" label="Surgery Date" displayFormat="yyyy-MM-dd" />
					<s:textarea name="surgery.arrangementDescription" label="Surgery Arrangement" cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Update" />
				</s:form>
			</s:if>
			<s:else>
				<s:textfield name="surgery.surgeryDate" label="Surgery Date" displayFormat="yyyy-MM-dd" readonly="true" />
				<s:textarea name="surgery.arrangementDescription" label="Surgery Arrangement" cols="40" rows="10" readonly="true" />
			</s:else>		
			
           <s:if test='#session.CurrentUser.roleType=="D"'>
           		<s:form action="editSurgery" method="post" namespace="/surgery">
					<s:textarea name="surgery.surgerySummary" label="Surgery Summary" cols="40" rows="10" />
					<s:token name="token"></s:token>
					<s:submit value="Update" />
				</s:form>
           </s:if>
           <s:else>
				<s:textarea name="surgery.surgerySummary" label="Surgery Summary" cols="40" rows="10" readonly="true"/>
			</s:else>
   	</table>	
 
         		
  </body>
</html>

