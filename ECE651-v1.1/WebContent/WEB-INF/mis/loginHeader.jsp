<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>   
  <title>HMS</title>
  <base href="<%=basePath%>">
  <link href="<s:url value="/css/form_style.css"/>" rel="stylesheet" type="text/css"/>
  <sx:head/>
</head>

<body>
<h2>Login As: <em> <s:property value="%{#session.CurrentUser.firstName}"/> <s:property value="%{#session.CurrentUser.lastName}"/> </em>     Role: <em><s:property value="%{#session.Role}"/></em></h2>
<h2><a href="<s:url action='logout' namespace="/" ></s:url>">Logout</a></h2>
</body>



