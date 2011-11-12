<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
  </head>  
  <body>
  	<!--<h2>Part 1: Input form</h2>-->
	<h3>Search Employee</h3>
	<hr>
 	<s:form action="searchEmployee" method="post" namespace="/">
 		<table border="1">
 		<s:textfield name="employee.fName" label="First Name" />
 		<s:textfield name="employee.lName" label="Last Name" />
		<s:submit value="Search Employee"/>
 		</table>		
	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
       	<s:if test="employeeList.size()==0">
       		There is no such Employee!!!
       	</s:if>
       	<s:else>
		 	<table class="Employee info list" border="1">
	    		<tr class="even">
	    		<td><b>UserId</b></td>
	        	<td><b>LoginId</b></td>
	        	<td><b>First Name</b></td>
	        	<td><b>Last Name</b></td>
	        	<td><b>Phone</b></td>
	        	<td><b>Email</b></td>
	        	<td><b>UserType</b></td>        	
	        	<td colspan="2"><b>Operation</b></td>
	        	<td><s:property value="employeeList.size()"/></td>
	    		</tr>
	    		<s:iterator value="employeeList" status="userStatus">
	            	<tr
	                	class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
	                	<td><s:property value="userId" /></td>
	                	<td><a href="<s:url action='linkEditEmpl'>
	                		<s:param name="employee.userId" value="%{userId}"/>
							<s:param name="employee.loginId" value="%{loginId}"/>
							</s:url>"><s:property value="loginId" /></a></td>                	
	                	<td><s:property value="fName" /></td>
	                	<td><s:property value="lName" /></td>
				<!--  
	                	<td><a href="<s:url action='showAddressId'>
							<s:param name="employee.address.addressId" value="%{addressId}"/>
							</s:url>"><s:property value="address.addressId" /></a></td>
	              -->
	                	<td><s:property value="phone" /></td>
	                	<td><s:property value="email" /></td>
	                	<td><s:property value="role" /></td>
	                	<td><a href="<s:url action='linkEditEmpl'>
	                		<s:param name="employee.userId" value="%{userId}"/>
							<s:param name="employee.loginId" value="%{loginId}"/>						
							</s:url>">Edit</a></td>
	                	<td><a href="<s:url action='removeEmployee'>
	                		<s:param name="employee.userId" value="%{userId}"/>
							<s:param name="employee.loginId" value="%{loginId}"/>
							<s:param name="employee.role" value="%{role}"/>
							</s:url>">Remove</a></td>
	            	</tr>
				</s:iterator>
		 	</table>
	</s:else>
	 <s:if test="returnCode=='success'">         
	 	<hr>Employee operation <s:property value="returnCode" /><br/><br/>
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<!--  <hr>Search Employee <s:property value="returnCode" /><br/><br/>-->
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToAdminMain.action">Return to admin main menu</a><br>
  </body>
</html>
