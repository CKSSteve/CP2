<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<html>
<html>
<head>
<script type="text/javascript">
function settingFunctionPage(){
	 $("#newAddRolePreview").attr("action", '<c:url value="/roleSetting/settingFunction"/>')
	 $("#newAddRolePreview").submit();
}

function linkPage(){
    roleAuthoritySettingForm.method.value="linkPage";
    roleAuthoritySettingForm.submit();
}
</script>
</head>
<body id="scroller">
<h1><span class="title-l">角色權限維護清冊</span>-新增角色</h1>
<form:form name="newAddRolePreview" id="newAddRolePreview" method="post">
  <input type="hidden" name="roleId" value="${role.roleId}"/>
  <input type="hidden" name="roleName" value="${role.roleName}"/>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="8"><img src="<c:url value="/resources/images/table/top_left.gif"/>" width="8" height="8"></td>
      <td style="background:url(<c:url value="/resources/images/table/top_bg.gif"/>) repeat-x"><img src="<c:url value="/resources/images/spacer.gif"/>" width="1" height="1"></td>
      <td width="8"><img src="../images/table/top_right.gif" width="8" height="8"></td>
    </tr>
    <tr>
      <td width="8" style="background:url(<c:url value="/resources/images/table/left_bg.gif"/>) repeat-y">&nbsp;</td>
      <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
        <tr>
          <td height="30"><span class="contant"><img src="<c:url value="/resources/images/page.gif"/>" width="15" height="20" border="0" align="absmiddle"></span>角色新增完成，以下為新設定的角色資料。</td>
        </tr>
      </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="110">角色代碼</th>
          <td><div align="left"><c:out value="${role.roleId}" /></div></td>
        </tr>
        <tr>
          <th width="110">角色名稱</th>
          <td><div align="left"><c:out value="${role.roleName}" /></div></td>
        </tr>
        <tr>
          <th width="110">類型</th>
          <td><div align="left">自訂 - (一般使用者)</div></td>
        </tr>
        <tr>
          <th width="110">說明</th>
          <td><div align="left"><c:out value="${role.memo}" />&nbsp;</div></td>
        </tr>
      </table>
      </td>
      <td width="8" style="background:url(<c:url value="/resources/images/table/right_bg.gif"/>) repeat-y">&nbsp;</td>
    </tr>
    <tr>
      <td width="8"><img src="<c:url value="/resources/images/table/bottom_left.gif"/>" width="8" height="8"></td>
      <td style="background:url(<c:url value="/resources/images/table/bottom_bg.gif"/>) repeat-x"><img src="<c:url value="/resources/images/spacer.gif"/>" width="1" height="1"></td>
      <td width="8"><img src="<c:url value="/resources/images/table/bottom_right.gif"/>" width="8" height="8"></td>
    </tr>
  </table>
  <p class="pbtn">
    <input name="cancel2" type="button" class="button-brown" onclick="settingFunctionPage();" value="權限設定">
    &nbsp;
    <input name="Submit22" type="button" class="button-brown" onclick="javascript:location.href='<%=request.getContextPath()%>/roleSetting'" value="回角色權限維護清冊">
  </p>
  <p>&nbsp;</p>
</form:form>
<p>&nbsp;</p>
</body>
</html>
