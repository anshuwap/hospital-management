<%@ taglib uri="/struts-tags" prefix="s"%>

<s:if test='#session.CurrentUser.roleType=="N"'>
	<jsp:include page="/WEB-INF/mis/NurseMenuHeader.jsp"/>
</s:if>
<s:elseif test='#session.CurrentUser.roleType=="D"'>
    <jsp:include page="/WEB-INF/mis/DoctorMenuHeader.jsp"/>
</s:elseif>
<s:elseif test='#session.CurrentUser.roleType=="I"'>
	<jsp:include page="/WEB-INF/mis/ITGuyMenuHeader.jsp"/>
</s:elseif>
<s:elseif test='#session.CurrentUser.roleType=="L"'>
    <jsp:include page="/WEB-INF/mis/LawyerMenuHeader.jsp"/>
</s:elseif>
<s:else>
    <jsp:include page="/WEB-INF/mis/NurseMenuHeader.jsp"/>
</s:else>