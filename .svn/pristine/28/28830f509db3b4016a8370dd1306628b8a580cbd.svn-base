<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<head>
</head>
<body>
<h1>${bankName} <sec:authentication property="principal.bankChineseName" />-<sec:authentication property="principal.viewUserName" /> 歡迎您使用本系統</h1>
<h1>待辦事項</h1>
<c:set var="hasToDoList" value="false"></c:set>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table04">

      <c:forEach var="todo" items="${todoBeanList}">
	        <tr>
	          <td>
	            <img src="<c:url value="/resources/images/page.gif"/>" width="15" height="20" border="0" align="middle" alt=""/>
	            <a href="<c:out value="${pageContext.request.contextPath}/${todo.uri}" />">
	            <c:out value="${todo.functionName}" />(<c:out value="${todo.count}" />件)</a>
	          </td>
	        </tr>
      </c:forEach>

      <c:if test="${empty todoBeanList}">
        <tr>
          <td align="center"><a style="font-size: 14px">無任何待辦事項</a></td>
        </tr>
      </c:if>

    </table></td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>

