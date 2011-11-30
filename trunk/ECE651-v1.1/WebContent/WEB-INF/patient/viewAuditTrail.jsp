<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

 <jsp:include page="/WEB-INF/mis/MainHeader.jsp"/>
 
  
  <body>
  <a href="<s:url value='patient/searchForViewPatient.action'>
        <s:param name="healthCardID" value="#session.CurrentPatient.healthCardId"/>  
        </s:url>">To Patient Page</a>
  <br>
		<s:form action="searchAuditTrailByPatientAndTable" method="post"
			namespace="/audittrail">
			<table align="center">
				<caption>
					Retrieve Revision Information
				</caption>
				<tr>
					<s:select label="Select Table" name="tableName" headerKey="1"
						headerValue="-- Please Select --" value=""
						list="#{'Patient':'Patient'}" />
					<td>
						<s:token name="token"></s:token>
						<s:submit value="Retrieve" />
					<td>
				</tr>
			</table>
		</s:form>

		<s:if test="auditTrailList!=null&&auditTrailList.size()>0">
			<table border="1" align="center">
				<caption>
					<s:property value="tableName" /> Revision Information
				</caption>
				<s:iterator value="auditTrailList">
					<tr>
						<td>
							Column Name
						</td>
						<td>
							Old Version
						</td>
						<td>
							New Version
						</td>
						<td>
							Previous User
						</td>
						<td>
							Updated User
						</td>
						<td>
							Updated Time
						</td>
					</tr>
					<tr>
						<td>
							<s:property value="columnName" />
						</td>
						<td>
							<s:property value="oldVersion" />
						</td>
						<td>
							<s:property value="newVersion" />
						</td>
						<td>
							<s:property value="dbUser" />
						</td>
						<td>
							<s:property value="lastUpdtUser" />
						</td>
						<td>
							<s:property value="time" />
						</td>
					</tr>
					<tr>
						<td>
							Previous Value
						</td>
						<td colspan="5">
							<s:property value="oldValue" />
						</td>
					</tr>
					<tr>
						<td>
							Updated Value
						</td>
						<td colspan="5">
							<s:property value="newValue" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
		
		<table align="center">
			<tr>
				<td>
					<s:property value="operationStatus" />
				</td>
			</tr>
		</table>
		
	</body>
</html>
