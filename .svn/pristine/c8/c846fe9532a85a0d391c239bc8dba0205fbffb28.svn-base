<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="dashboard">
<title>UXB2B-無實體票券系統</title>
<%
String currentServerName = request.getServerName();
Cookie jsessionidCookie = new Cookie("JSESSIONID", ""); 
jsessionidCookie.setDomain(currentServerName); 
jsessionidCookie.setMaxAge(0); 
jsessionidCookie.setPath("/"); 
response.addCookie(jsessionidCookie);
%>
</head>
<body>
<div class="header"></div>
<p>&nbsp;</p>
		<table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
系統訊息</th>

		      <tr>
		        <td align="center"><table width="100%" border="0" cellpadding="0" cellspacing="5" id="table_msg">
		                  <tr>
		                    <td width="70"><img src="<%=request.getContextPath()%>/resources/images/icon_successful.gif" width="64" height="64"></td>
		                    <td nowrap><span class="title-l">
								登出成功&nbsp;&nbsp;	                  	
		                    </td>
		                  </tr>
		            </table>


<p>&nbsp;</p>
<input id="localeLanguage" type="hidden" value="${pageContext.response.locale}">
</body>
</html>