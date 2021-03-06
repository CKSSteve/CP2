<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/join.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/checkAll.js"></script>
</head>

<body id="scroller">
<h1><span class="title-l">角色權限維護清冊</span>-權限設定</h1>
<form:form name="RoleAuthoritySetting" id="RoleAuthoritySetting" method="post">
  <input type="hidden" name="roleId" value="${roleId}"/>
  <input type="hidden" name="roleName" value="${roleName}"/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
      <tr>
        <td height="30"><img src="<c:url value="/resources/images/page.gif"/>" border="0" align="absmiddle" alt="">下列為<span class="red"> [無實體票券系統]</span> 中需要權限管控的功能，請設定 <span class="red">[${roleName}] </span>所能執行的功能。</td>
      </tr>
      <tr>
        <td height="30"><img src="<c:url value="/resources/images/page.gif"/>" border="0" align="absmiddle" alt="">選取設定主功能項目將一併設定子功能項目。 </td>
      </tr>
    </table>

<jsp:include flush="true" page="function/tenderNoFunctions.jsp"/>

<jsp:include flush="true" page="function/promissoryNoteFunction.jsp"/>

<%-- <jsp:include flush="true" page="function/guaranteeFunction.jsp"/> --%>

<jsp:include flush="true" page="function/manageFunctions.jsp"/>


    </td>
  </tr>
</table>
<p class="pbtn">
  <input name="cancel" type="reset" class="button-brown" value="重設">
  &nbsp;
<input name="button" type="submit" class="button-brown" value="確認" onclick="backUpdate();">
</p>
<p>&nbsp;</p>
</form:form>
<script type="text/javascript">
function backUpdate(){
	$("#RoleAuthoritySetting").attr("action", '<c:url value="/roleSetting/updateFunction"/>')
	$("#RoleAuthoritySetting").submit();
}
</script>
</body>
</html>
