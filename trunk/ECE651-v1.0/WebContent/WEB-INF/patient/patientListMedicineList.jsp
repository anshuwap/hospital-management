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
	<h3>Medicine Name List</h3>
	<hr>

	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Find the medicine list <s:property value="returnCode" /> ! <br/><br/> 
		<table class="Medicine info list">
    		<tr class="even">
        	<td><b>Medicine id</b></td>	 	 
        	<td><b>Medicine Name</b></td>	 	 
        	<td><b>Manufacturer</b></td>	 	 
        	<td><b>Instruction</b></td>	 	 
    		</tr>
    		<s:iterator value="medicineList" status="MedicineStatus">
            	<tr
                	class="<s:if test="#MedicineStatus.odd == true ">odd
                	       </s:if>
                	       <s:else>
                	       			even
                	       </s:else>"
                >
                	<td><s:property value="medicineId" /></td>
                	<td><s:property value="medicineName" /></td>
                	<td><s:property value="manufacturer" /></td>
                	<td><s:property value="instruction" /></td>
	 	
			</s:iterator>
	 	</table>	
   		<s:iterator value="medicineList" status="MedicineStatus">
			</s:iterator>
	 		
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>list doctor <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
   	<a href="<s:url action='patientVPH'>
	</s:url>">Return to patient record page</a><br>
	 
  </body>
</html>
