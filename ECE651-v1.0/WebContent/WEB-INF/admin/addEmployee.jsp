<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
   <sx:head/>
  </head>  
  <body>
  	<!--<h2>Part 1: Input form</h2>-->
	<h3>Add Employee</h3>
	<hr>
 	<s:form action="getEmployeeRole" method="post" namespace="/">
 		<table border="1">
 		<s:select list="#{'D':'Doctor', 'S':'Staff', 'F':'Financial', 'L':'Legal', 'A':'Admin'}" name="employee.role" label="Employee Role"/>
  		<s:submit value="Select Role"/>
  		</table> 		
	 </s:form>	 
 	<s:form action="addEmployee" method="post" namespace="/">
 		<table border="1">
 		
 		<s:textfield name="employee.fName" label="First Name" required="true"/>
 		<s:textfield name="employee.mName" label="Middle Name"/>
 		<s:textfield name="employee.lName" label="Last Name" required="true"/>
 		<s:password name="employee.password" label="Password" required="true"/>
 		<!--<s:textfield name="employee.userId" label="UserId"/>-->
 		<!--<s:textfield name="employee.loginId" label="LoginId"/>-->
 		<s:radio list="#{'1':'male' ,'0': 'female'}" name="employee.gender" value="1" label="Gender"/>
   		<sx:datetimepicker name="employee.birthday" label="Date of Birth" startDate="1970-01-01" displayFormat="yyyy-MM-dd"/>
 		<!--<s:date name="employee.birthDay" format="yyyy-MM-dd"/>-->
 		<!--<s:checkbox name="employee.active" value="false" label="Active" />-->
 		<s:radio list="#{1:'active' ,0: 'inactive'}" name="employee.active" value="1" label="Active"/>
 		<s:textfield name="employee.sin" label="SIN"/>
 		<s:textfield name="employee.address.addressLine" label="AddressLine"/>
 		<s:textfield name="employee.address.city" label="City"/>
 		<s:textfield name="employee.address.province" label="Province"/>
 		<s:textfield name="employee.address.postCode" label="PostCode"/>
 		<s:textfield name="employee.phone" label="Phone Number"/>
 		<s:textfield name="employee.email" label="Email"/>
 		<s:textfield name="employee.role" label="Role" value="%{employee.role}" readonly="true"/>
	 	<s:textfield name="employee.salary" label="Salary"/>
   		<sx:datetimepicker name="employee.startDate" label="StartDate" startDate="1970-01-01" displayFormat="yyyy-MM-dd"/>	 	
	 	<!--<s:select list="#{'D':'Doctor', 'S':'Staff', 'F':'Financial', 'L':'Legal', 'A':'Admin'}" name="employee.role" label="Role" value="%{employee.role}" readonly="true" />-->
	 	<!--<s:checkboxlist list="#{1:'Doctor', 2:'Patient', 3:'Staff', 4:'Financial', 5:'Legal', 6:'Admin'}" name="employee.userType" label="User Type"/>-->
 		<!--<s:combobox list="#{1:'Doctor', 2:'Patient', 3:'Staff', 4:'Financial', 5:'Legal', 6:'Admin'}" name="employee.Comments" label="Comments"/>-->
 		<!--<s:textarea name="employee.comments" label="Comments" cols="40" rows="10"/>-->
 		<!--<s:text name="employee.comments" />-->
 		<!--<s:select list="#{'D':'Doctor', 'S':'Staff', 'F':'Financial', 'L':'Legal', 'A':'Admin'}" name="employee.role" label="Employee Role"/>-->	
 		<s:if test="needDoctorInfo=='true'">
 			<s:textfield name="doctor.title" label="DoctorTitle" required="true"/> 			
 			<s:select name="doctorSpecialIds" list="specialMap" label="Specialization" multiple="true" headerKey="" headerValue="-- select --" required="true" /> 			
 		</s:if>
 		<s:elseif test="needStaffInfo=='true'">
 			<s:textfield name="staff.title" label="StaffTitle" required="true"/>
 			<s:select name="staffWorksforIds" list="doctorMap" label="Works for" multiple="true" headerKey="" headerValue="-- select --" required="true" />
 		</s:elseif> 		 		
 		<s:submit value="Add Employee"/>
 		</table> 		
	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Add employee <s:property value="returnCode" /><br/><br/> 
	 	<table class="Employee info list" border="1">
    		<tr class="even">
        	<td><b>UserId</b></td>
        	<td><b>LoginId</b></td>
        	<td><b>First Name</b></td>
        	<td><b>Last Name</b></td>
        	<td><b>Gender</b></td>
        	<td><b>AddressId</b></td>
        	<td><b>UserType</b></td>
        	<td><b>Role</b></td>
        	<td><b>Title</b></td>
    		</tr>
    		<s:iterator value="returnList" status="userStatus">
            	<tr          		
                	class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
                	<td><s:property value="userId" /></td>
                	<td><s:property value="loginId" /></td>                	
                	<td><s:property value="fName" /></td>
                	<td><s:property value="lName" /></td>
                	<td><s:property value="gender" /></td>
                	<td><s:property value="address.addressId" /></td>
                	<!--
                	<td><a href="<s:url action='addressIdSearchMethodLink'>
						<s:param name="addressId" value="%{addressId}"/>
						</s:url>"><s:property value="address.addressId" /></a></td>
					-->
                	<td><s:property value="userType" /></td>
                	<td><s:property value="role" /></td>
                	<td><s:property value="title" /></td>
            	</tr>
			</s:iterator>
	 	</table>	 	
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Add Employee <s:property value="returnCode" /><br/><br/>
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
