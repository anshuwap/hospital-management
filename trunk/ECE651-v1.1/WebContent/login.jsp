<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
  <title>login HMS</title>
  <style type=text/css>
  BODY {BACKGROUND-COLOR: #3068b5}
  </style>
  <base href="<%=basePath%>">
  <link href="<s:url value="/css/login.css"/>" rel="stylesheet" type="text/css"/>
  </head>  
<BODY text=#000000 bgColor=#3068b5 leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<div align=center></div>
<table height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <tbody>
  <s:property value="errorMessage" />
    <tr>
      <td align=middle bgColor=#3068b5>
        <table cellSpacing=0 cellPadding=0 width="100%" border=0>
          <tbody>
            <tr>
              <td align=middle background="<s:url value="/css/images/bgImage.gif" />">
                <table cellSpacing=0 cellPadding=0 width=504 border=0>
                  <tbody>
                    <tr>
                      <td background="<s:url value="/css/images/homeTop.gif" />" height=82><img height=82 src="<s:url value="/css/images/shim.gif" />" width=5></td>
                    </tr>
                    <tr>
                      <td background="<s:url value="/css/images/main.jpg" />" height=177><img height=177 src="<s:url value="/css/images/shim.gif" />" width=5></td>
                    </tr>
                    <tr>
                      <td vAlign=bottom background="<s:url value="/css/images/homeBottom.gif" />" height=81>
                        <s:form action="login" method="post" namespace="/">
                          <table height= cellSpacing=0 cellPadding=3 width=443 align=center border=0>
                            <tbody>
                              <tr>
                                <td vAlign=bottom align=middle>
                                  <table cellSpacing=0 cellPadding=2 align=center border=0>
                                    <tbody>
                                      <tr>
                                        <td align=left height=20>
                                        	<s:textfield name="user.username" label="Username"/>
                                        	<s:password name="user.password" label="Password" required="true"/>	
	 										<s:submit value="Login"/>
                                        </td>
                                      </tr>
                                    </tbody>
                                  </table>
                                </td>
                              </tr>
                            </tbody>
                          </table>
                        </s:form>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
  </tbody>
</table>
</body>
</html>
