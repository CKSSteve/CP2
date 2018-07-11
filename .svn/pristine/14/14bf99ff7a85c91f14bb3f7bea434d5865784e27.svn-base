<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%response.setHeader("Cache-Control","private");%>
<html>
<head>
<script type="text/javascript">

</script>
</head>

<body id="scroller">

<p>&nbsp;</p>
<table width="550" border="0" align="center" cellpadding="0" cellspacing="0" style="border-color:#3C8AD8;">
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_msg">
      <tr>
        <th><c:out value="${functionName}" />-作業訊息</th>
      </tr>
      <tr>
        <td align="center">
          <table width="100%" border="0" cellpadding="0" cellspacing="5" id="table_msg">
              <tr>
                <td width="100"><img src="<c:url value="/resources/images/note_icon.gif"/>" width="100" height="97" alt=""></td>
                <td align="left" nowrap><a><c:out value="${message}" /></a> </td>
              </tr>
          <c:if test="${!empty errorMessage}">
             <tr>
             	<td></td>
              	<td align="left" nowrap><a style="color:red">失敗原因:&nbsp;<c:out value="${errorMessage}" /></a> </td>
              </tr>
      </c:if>
		  </table>
		</td>
      </tr>
     
      <tr>
        <td height="50" align="center" class="dline01">
          <c:forEach var="mb" items="${messageButton}">
             <!-- 如需導頁至特定apply no 時 , 送出時必須於attribute加入 applNo 屬性 , Action method接收時-->
             <!-- 不能以ActionForm 接收 , 必需以request.getParameter 的方式才可以 -->
             <c:if test="${!empty select}">
                <input name="action" type="button" class="button-brown" style="cursor:hand" onClick="window.location='<c:out value="${pageContext.request.contextPath}${mb.functionLink}" />&select=<c:out value="${select}" />'" value="<c:out value="${mb.functionName}" />">
             </c:if>
             <c:if test="${!empty applNo}">
                <input name="action" type="button" class="button-brown" style="cursor:hand" onClick="window.location='<c:out value="${pageContext.request.contextPath}${mb.functionLink}" />&applNo=<c:out value="${applNo}" />'" value="<c:out value="${mb.functionName}" />">
             </c:if>
             <c:if test="${empty applNo && empty select}">
                <input name="action" type="button" class="button-brown" style="cursor:hand" onClick="window.location='<c:out value="${pageContext.request.contextPath}${mb.functionLink}" />'" value="<c:out value="${mb.functionName}" />">
             </c:if>

          </c:forEach>
          <c:if test="${!empty functionUrl}">
          	<input name="button" type="button" style="cursor:hand" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/<c:out value="${functionUrl}"/>'" value="回${functionMenuName}">
          </c:if>	
            <input name="button" type="button" style="cursor:hand" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁"></td>
      </tr>
    </table></td>
  </tr>

</table>
<p>&nbsp;</p>
</body>
</html>
