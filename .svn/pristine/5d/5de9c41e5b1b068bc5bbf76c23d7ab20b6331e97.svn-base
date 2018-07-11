<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<c:if test="${errMsg!=null && !empty errMsg}">
  <script type="text/javascript" language="javascript">
  var errorMsg = "";
  <c:forEach items="${errMsg}" var="errMsgs" step="1">
  errorMsg += '<c:out value="${errMsgs}" />'+"\r\n";
  </c:forEach>
  alert(errorMsg);
  </script>
  </c:if>
  
<script type="text/javascript" language="javascript">
$(function() {
	$("#addUserSettingPreview").hide();
})
function addUserPreview() {
  var errorMsg = "";
  userIdValue=$("input[name='userId']").val();
  userNameValue=$("input[name='userName']").val();
  newPwdValue=$("input[name='newPwd']").val();
  chkPwdValue=$("input[name='chkPwd']").val();
  branchNameValue=$('#branchName :selected').text();
  roleIdValue=$('#roleId :selected').text();
  telNoValue=$("input[name='telNo']").val();
  emailValue=$("input[name='email']").val();

  
  if(userIdValue.length <1) {
    errorMsg += "使用者帳號，不可以為空白\r\n";
  }

  if(userNameValue.length <1) {
    errorMsg += "使用者名稱，不可以為空白\r\n";
  }

  if(newPwdValue.length >=1 && chkPwdValue.length >=1) {
    if(newPwdValue!=chkPwdValue) {
      errorMsg += "密碼與密碼確認，內容不一致\r\n";
    }
  } else if(newPwdValue.length >=1 && chkPwdValue.length <1) {
    errorMsg += "密碼確認，不可以為空白\r\n";
  } else if(newPwdValue.length <1 && chkPwdValue.length >=1) {
    errorMsg += "密碼，不可以為空白\r\n";
  } else {
    errorMsg += "密碼，不可以為空白\r\n";
    errorMsg += "密碼確認，不可以為空白\r\n";
  }
//   if (!$("input[name='businessTypeQ']").prop("checked") && !$("input[name='businessTypeC']").prop("checked")&&!$("input[name='businessTypeG']").prop("checked")) {
// 	  errorMsg += "承作業務類別，不可以為空白\r\n";
//   }

  var str= newPwdValue.replace(/^\s\s*/, '').replace(/\s\s*$/, '');

  if(str.length<8 && str.length >0){
    errorMsg+="[使用者密碼]長度不可小於8碼";
    $("input[name='newPwd']").val("");
    $("input[name='chkPwd']").val("");
  }

  if(errorMsg.length>1) {
    alert(errorMsg);
    return false;
  } else {
	  $("#userIdPreview").html(userIdValue);
	  $("#userNamePreview").html(userNameValue);
	  $("#branchPreview").html(branchNameValue);
	  $("#roleNamePreview").html(roleIdValue);
	  $("#telNoPreview").html(telNoValue);
	  $("#emailPreview").html(emailValue);
// 	  var typMsg = "";

// 	  if($("input[name='businessTypeQ']").prop('checked')){
// 		  typMsg+="承作標單業務   ";
// 	  }
// 	  if($("input[name='businessTypeC']").prop('checked')){
// 		  typMsg+="承作本票業務    ";
// 	  }
// 	  if($("input[name='businessTypeG']").prop('checked')){
// 		  typMsg+="承作保證業務";
// 	  }
// 	  $("#businessTypePreview").html(typMsg);
	  $("#addUserSetting").hide();
	  $("#addUserSettingPreview").show();
  }
}

function backSetting(){
	  $("#addUserSettingPreview").hide();
	  $("#addUserSetting").show();	
}

function addUserData(){
	$("#addUserSettingForm").attr("action", '<c:url value="/accountManagement/addNewAccount"/>')
	$("#addUserSettingForm").submit();
	
}
</script>
</head>

<body id="scroller">
<div id="addUserSetting">
<h1><span class="title-l">帳號管理-</span>新增帳號</h1>
<form:form name="addUserSettingForm" id="addUserSettingForm" method="POST">
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
          <tr>
            <td height="30"><img src="<c:url value="/resources/images/page.gif"/>" border="0" alt="">標有<span class="red">＊</span>的欄位為必要資料，請正確填寫。</td>
          </tr>
        </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
          <tr>
            <th width="25%"><span class="red">＊&nbsp;</span>使用者帳號</th>
            <td width="75%">
              <div align="left">
                  <input type="text" name="userId" size="44" maxlength="20" class="textfield" />
              </div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>使用者名稱</th>
            <td>
              <div align="left">
                <input type="text" name="userName" size="24" maxlength="12" class="textfield" />
              </div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>所屬分行代碼 / 名稱 </th>
            <td>
              <div align="left">
                <c:if test="${branchs!=null && !empty branchs}">
                 <select name="branchName" id="branchName">
                <c:forEach items="${branchs}" var="branch">
                  <option value="${branch.branchId}"><c:out value="${fn:substring(branch.branchId,3,7)}" />&nbsp;/&nbsp;<c:out value="${branch.chineseName}" /></option>
                </c:forEach>
              </select>
                </c:if>
              </div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>所屬角色</th>
            <td>
              <div align="left">
                <select name="roleId" id="roleId">
                  <c:forEach items="${roles}" var="role" step="1">
                    <option value="${role.roleId}"><c:out value="${role.roleName}" /></option>
                  </c:forEach>
                </select>
              </div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>密碼</th>
            <td>
              <div align="left">
                <input type="text" name="newPwd" size="66" maxlength="50" class="textfield" />
              </div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>密碼確認</th>
            <td>
              <div align="left">
                <input type="text" name="chkPwd" size="66" maxlength="50" class="textfield" />
              </div>
            </td>
          </tr>
          <tr>
            <th>聯絡電話</th>
            <td>
              <div align="left">
                  <input type="text" name="telNo" size="44" maxlength="20" class="textfield" />
              </div>
            </td>
          </tr>
          <tr>
            <th>電子郵件</th>
            <td>
              <div align="left">
                  <input type="text" name="email" size="80" maxlength="60" class="textfield" />
              </div>
            </td>
          </tr>
<!-- 		  <tr> -->
<!--             <th>＊&nbsp;承作業務類別</th> -->
<!--             <td> -->
<!--               <div align="left"> -->
<!--               	<input type="checkbox" name="businessTypeQ" value="1" /> 承作標單業務 -->
<!--               	<input type="checkbox" name="businessTypeC" value="1" /> 承作本票業務 -->
<!-- 				<input type="checkbox" name="businessTypeG" value="1" /> 承作保證業務 -->
<!--               </div> -->
<!--             </td> -->
<!--           </tr> -->
        </table>
      </td>
    </tr>
  </table>

  <p class="pbtn">
    <input name="button" type="button" class="button-brown" onClick="javascript:history.go(-1);" value="回上頁">
    &nbsp;
    <input name="cancel2" type="reset" class="button-brown" value="重設">
    &nbsp;
    <input type="button" name="confirm" value="預覽" class="button-brown" onclick="addUserPreview();"/>
  </p>
</form:form>
</div>
<div id="addUserSettingPreview">
<h1><span class="title-l">帳號管理-</span>新增使用者資料-預覽</h1>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
          <tr>
            <td height="30"><img src="<c:url value="/resources/images/page.gif"/>" border="0" alt="">標有<span class="red">*</span>的欄位為必要資料，請正確填寫。</td>
          </tr>
        </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
          <tr>
            <th width="25%"><span class="red">＊&nbsp;</span>使用者帳號</th>
            <td><div align="left" id="userIdPreview"></div></td>
          </tr>
          <tr>
            <th width="25%"><span class="red">＊&nbsp;</span>角色選擇</th>
            <td>
              <div align="left" id="roleNamePreview">
              </div>
            </td>
          </tr>
          <tr>
            <th width="25%"><span class="red">＊&nbsp;</span>所屬分行代碼 / 名稱</th>
            <td>
              <div align="left" id="branchPreview">
              </div>
            </td>
          </tr>
          <tr>
            <th width="25%">使用者姓名</th>
            <td>
              <div align="left" id="userNamePreview">
              </div>
            </td>
          </tr>
          <tr>
            <th width="25%"><span class="red">＊&nbsp;</span>使用者密碼</th>
            <td><div align="left">********</div></td>
          </tr>
          <tr>
            <th width="25%"><span class="red">＊&nbsp;</span>密碼確認</th>
            <td><div align="left">********</div></td>
          </tr>
          <tr>
            <th width="25%">電子郵件</th>
            <td><div align="left" id="emailPreview"></div></td>
          </tr>
          <tr>
            <th width="25%">聯絡電話</th>
            <td><div align="left" id="telNoPreview"></div></td>
          </tr>
<!-- 		  <tr> -->
<!--             <th>承作業務類別</th> -->
<!--             <td> -->
<!--               <div align="left" id="businessTypePreview"> -->
<!--               </div> -->
<!--             </td> -->
<!--           </tr> -->
        </table>
      </td>
    </tr>
  </table>
  <p class="pbtn">
    <input name="cancel" type="button" class="button-brown" onClick="backSetting()" value="回上頁">
    &nbsp;
    <input name="Submit22" type="button" class="button-brown" onClick="addUserData();" value="確認">
  </p>
  <p>&nbsp;</p>

</div>
</body>
</html>
