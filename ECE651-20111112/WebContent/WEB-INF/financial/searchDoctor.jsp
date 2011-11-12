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
	<h3>Search Patient</h3>

	 <!--<h2>Part 2: Display results, also can implement links to details info page</h2>-->
	 <s:if test="returnCode=='success'">           
        <hr>Find the doctor <s:property value="returnCode" /> ! <br/><br/> 
	 	<table class="Doctor info list" border="1">
    		<tr class="even">
        	<td><b>Doctor Id</b></td>
        	<td><b>Title</b></td>
    		</tr>
    		<s:iterator value="resultlines" status="userStatus">
            	<tr
                	class="<s:if test="#userStatus.odd == true ">odd
                	       </s:if>
                	       <s:else>
                	       			even
                	       </s:else>"
                >
                	<td><s:property value="userId" /></td>
                	<td><s:property value="title" /></td>
            	</tr>
			</s:iterator>
	 	</table>	

 	<s:form action="searchPatientList" method="post" namespace="/">
	 	<table border="1">
 			<s:textfield name="doctor.userId" label="Doctor Id" required="true"/>
 			<sx:datetimepicker name="fromDate" label="Start Date" displayFormat="yyyy-MM-dd"/>
 			<sx:datetimepicker name="toDate" label="End Date" displayFormat="yyyy-MM-dd"/>
 		<s:submit value="Search Patient"/>
	 	</table> 
	 </s:form>	
	 		
	 </s:if>
	 <s:elseif test="returnCode=='error'">
	 	<hr>Search Doctor <s:property value="returnCode" /><br/><br/>
	 	<s:property value="errorMessage" /><br/>
	 </s:elseif>
	 <s:else>
		<!--<h2>can do something else?</h2>-->       
	 </s:else>	
	 <!--<h2>Part 3: Return to main menu</h2>-->
	 <hr>	 
	 <a href="<%=path%>/returnToFinancialMain.action">Return to financial main menu</a><br>

  </body>
</html>
