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
	<h3>Add Patient</h3>
	<hr>
 	<s:form action="addPatient" method="post" namespace="/">
 		<table border="1">
 		<s:textfield name="patient.fName" label="First Name" required="true"/>
 		<s:textfield name="patient.mName" label="Middle Name"/>
 		<s:textfield name="patient.lName" label="Last Name" required="true"/>
 		<s:password name="patient.password" label="Password" required="true"/>
 		<!--<s:textfield name="patient.userId" label="UserId"/>-->
 		<!--<s:textfield name="patient.loginId" label="LoginId"/>-->
 		<s:radio list="#{'1':'male' ,'0': 'female'}" name="patient.gender" value="1" label="Gender"/>
   		<sx:datetimepicker name="patient.birthday" label="Date of Birth" startDate="1970-01-01" displayFormat="yyyy-MM-dd"/>
 		<!--<s:date name="patient.birthDay" format="yyyy-MM-dd"/>-->
 		<!--<s:checkbox name="patient.active" value="false" label="Active" />-->
 		<s:radio list="#{1:'active' ,0: 'inactive'}" name="patient.active" value="1" label="Active"/>
 		<s:textfield name="patient.sin" label="SIN"/>
 		<s:textfield name="patient.address.addressLine" label="AddressLine"/>
 		<s:textfield name="patient.address.city" label="City"/>
 		<s:textfield name="patient.address.province" label="Province"/>
 		<s:textfield name="patient.address.postCode" label="PostCode"/>
 		<s:textfield name="patient.phone" label="Phone Number"/>
 		<s:textfield name="patient.email" label="Email"/>
  		<s:textfield name="patient.ohip" label="OHIP" required="true"/>
 		<s:combobox list="#{'Healthy':'Healthy', 'diagnosis in progress':'diagnosis in progress', 'prescription currently being taken':'prescription currently being taken','receiving treatment':'receiving treatment','scheduled for surgery':'scheduled for surgery','transferred to another hospital':'transferred to another hospital'}" name="patient.currHealth" label="Current Health"/>
 		<s:textarea name="patient.comments" label="Comments" cols="40" rows="10"/>
  		<s:if test="needDoctorInfo=='sent'">
 			<s:textfield name="doctor.title" label="DoctorTitle" required="true"/>
 		</s:if>
 		<s:elseif test="needStaffInfo=='sent'">
 			<s:textfield name="staff.title" label="StaffTitle" required="true"/>
 		</s:elseif>
 		<s:submit value="Add Patient"/>
 		</table> 		
	 </s:form>	 
	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Add patient <s:property value="returnCode" /><br/><br/> 
	 	<table class="Patient info list">
    		<tr class="even">
        	<td><b>UserId</b></td>
        	<td><b>LoginId</b></td>
        	<td><b>First Name</b></td>
        	<td><b>Last Name</b></td>
        	<td><b>Gender</b></td>
        	<!--  
        	<td><b>AddressId</b></td>
        	-->
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
	 	<hr>Add Patient <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToStaffMain.action">Return to staff main menu</a><br>
  </body>
</html>
