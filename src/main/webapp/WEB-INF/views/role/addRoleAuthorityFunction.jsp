<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script type="text/javascript">
function check(){
  if($('input[name="roleName"]').val().length<1){
    alert("角色名稱,為必要欄位");
    return false;
  }else
		$("#addRoleForm").attr("action", '<c:url value="/roleSetting/addNewRole"/>')
		$("#addRoleForm").submit();
}
</script>
</head>
<body id="scroller">
<h1><span class="title-l">角色權限維護</span>-新增角色</h1>
<form:form name="addRoleForm" id="addRoleForm" method="post">
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
        <tr>
          <td height="30"><span class="contant"><img src="<c:url value="/resources/images/page.gif"/>" width="15" height="20" border="0" align="absmiddle"></span>標有<span class="red">*</span>的欄位為必要資料，請正確填寫。</td>
        </tr>
      </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="110">角色代碼</th>
          <td>
            <div align="left">系統自動產生</div></td>
        </tr>
        <tr>
          <th width="110"><span class="red">＊&nbsp;</span>角色名稱</th>
          <td><div align="left"><span>
            <input type="text" name="roleName" class="textfield" size="40"/>
          </span></div></td>
        </tr>
        <tr>
          <th width="110">類型</th>
          <td><div align="left">自訂 - (一般使用者)</div></td>
        </tr>
        <tr>
          <th width="110">說明</th>
          <td><div align="left"><textarea name="memo" cols="40" rows="5" class="textfield"></textarea></div></td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
  <p class="pbtn">
    <input name="cancel2" type="reset" class="button-brown" value="重設">
    &nbsp;
    <input name="button" type="button" class="button-brown" value="確認" onclick="check();">
  </p>
  <p>&nbsp;</p>

</form:form>
<p>&nbsp;</p>
</body>
</html>
