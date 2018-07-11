<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.Cookie"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="dashboard">
<title>央行外匯線上申報系統</title>
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/index.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/body.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/table.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/content.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/login.css" type="text/css" rel="stylesheet">
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
<div class="header"><img src="<%=request.getContextPath()%>/resources/images/personal_header_zh.png" width="231" height="36" alt=""/></div>
<p>&nbsp;</p>
		<table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/top_left.gif" width="8" height="8"></td>
		    <td style="background:url(<%=request.getContextPath()%>/resources/images/table/top_bg.gif) repeat-x"><img src="<%=request.getContextPath()%>/resources/images/spacer.gif" width="1" height="1"></td>
		    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/top_right.gif" width="8" height="8"></td>
		  </tr>
		  <tr>
		    <td width="8" style="background:url(<%=request.getContextPath()%>/resources/images/table/left_bg.gif) repeat-y">&nbsp;</td>
		    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_msg">
		      <tr>
		        <th>錯誤訊息</th>
		      </tr>
		      <tr>
		        <td align="center"><table width="100%" border="0" cellpadding="0" cellspacing="5" id="table_msg">
		                  <tr>
		                    <td width="70"><img src="<%=request.getContextPath()%>/resources/images/icon_failed.gif" width="64" height="64"></td>
		                    <td nowrap><span class="title-l">
								<%-- <c:if test="${pageContext.response.locale eq 'zh_TW'}">
									<c:out value="使用者不存在!"/>
								</c:if>
								<c:if test="${pageContext.response.locale eq 'en'}">
									<c:out value="User does not exist!"/>
								</c:if> --%>
								<c:out value="${errorMsg }"/>		                    	
		                    </td>
		                  </tr>
		            </table>
		                </td>
		      </tr>
		    </table></td>
		    <td width="8" style="background:url(<%=request.getContextPath()%>/resources/images/table/right_bg.gif) repeat-y">&nbsp;</td>
		  </tr>
		  <tr>
		    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/bottom_left.gif" width="8" height="8"></td>
		    <td style="background:url(<%=request.getContextPath()%>/resources/images/table/bottom_bg.gif) repeat-x">
		    	<img src="<%=request.getContextPath()%>/resources/images/spacer.gif" width="1" height="1">
		    </td>
		    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/bottom_right.gif" width="8" height="8"></td>
		  </tr>
		</table>
<p>&nbsp;</p>
<input id="localeLanguage" type="hidden" value="${pageContext.response.locale}">
</body>
</html>