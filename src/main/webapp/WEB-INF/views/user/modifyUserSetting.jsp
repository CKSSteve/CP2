<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html>
<head>
<script type="text/javascript">
$(function() {
	  $("#modifyUserSettingPreview").hide();
// 	if('<c:out value="${modifyUser.businessTypeQ}" />'=='Y'){
// 		$("input[name='businessTypeQ']").prop("checked", true);
// 	}
// 	if('<c:out value="${modifyUser.businessTypeC}" />'=='Y'){
// 		$("input[name='businessTypeC']").prop("checked", true);
// 	}
// 	if('<c:out value="${modifyUser.businessTypeG}" />'=='Y'){
// 		$("input[name='businessTypeG']").prop("checked", true);
// 	}
	$('#branchName').val('<c:out value="${modifyUser.branchId}"/>').prop("selected",true);
	$('#roleId').val('<c:out value="${modifyUser.roleId}"/>').prop("selected",true);
})

function preview(){
  if(checkAll()){
	  userNameValue=$("input[name='userName']").val();
	  branchNameValue=$('#branchName :selected').text();
	  roleIdValue=$('#roleId :selected').text();
	  newPwdValue=$("input[name='newPwd']").val();
	  telNoValue=$("input[name='telNo']").val();
	  emailValue=$("input[name='email']").val();
	  
	  $("#userNamePreview").html(userNameValue);
	  $("#branchPreview").html(branchNameValue);
	  $("#roleNamePreview").html(roleIdValue);
	  var pwdmsg = "";
	  if(newPwdValue.length<1){
		  pwdmsg="密碼";
	  }else{
		  pwdmsg="新密碼";
	  }
	  $("#pwdPreview").html(pwdmsg);
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

	  $("#modifyUserSetting").hide();
	  $("#modifyUserSettingPreview").show();
  }
}

function checkAll(){
  var msg = "";
  var str = $("input[name='newPwd']").val();
  str= str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
 
  if($("input[name='newPwd']").val() != $("input[name='chkPwd']").val()){
    msg+="[使用者密碼]與[密碼確認]不符\r\n";
  }
	if($("input[name='userName']").val().length <=0){
		msg+="請輸入使用者名稱\r\n";
	}
//   if (!$("input[name='businessTypeQ']").prop("checked") && !$("input[name='businessTypeC']").prop("checked")&&!$("input[name='businessTypeG']").prop("checked")) {
// 	  msg += "承作業務類別，不可以為空白\r\n";
//   }

  if(str.length<8 && str.length >0){
    msg+="[使用者密碼]長度不可小於8碼";
    $("input[name='newPwd']").val("");
    $("input[name='chkPwd']").val("");
  }

  if(msg!=""){
    alert(msg);
    return false;
  }else{
    return true;
  }
}

function toSetting(){
	  $("#modifyUserSetting").show();
	  $("#modifyUserSettingPreview").hide();
}

function updateModifyUser(){
	
	$("#modifyUserSettingForm").attr("action", '<c:url value="/accountManagement/updateMoidfyAccount"/>')
	$("#modifyUserSettingForm").submit();
}

</script>

<c:if test="${!empty message}">
  <script type="text/javascript">
  alert('<c:out value="${message}" />');
  </script>
  </c:if>
</head>
  <body id="scroller">
  <div id="modifyUserSetting">
  <h1><span class="title-l">帳號管理-</span>修改使用者資料</h1>
  <form:form name="modifyUserSettingForm" id="modifyUserSettingForm" method="post">
    <input type="hidden" name="userId" value="${modifyUser.userId}"/>
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
          <tr>
            <td height="30"><img src="<c:url value="/resources/images/page.gif"/>" border="0" align="middle" alt="">標有<span class="red">*</span>的欄位為必要資料，請正確填寫。</td>
          </tr>
    </table>
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
      <tr>
        <th width="25%">帳號</th>
        <td width="75%"><div align="left"><c:out value="${modifyUser.userId}" /></div></td>
      </tr>
      <tr>
        <th><span class="red">＊&nbsp;</span>使用者名稱</th>
        <td>
          <div align="left">
              <input type="text" name="userName" size="30" maxlength="30" class="textfield" value="${modifyUser.userName}"/>
          </div>
        </td>
      </tr>
      <tr>
        <th><span class="red">＊&nbsp;</span> 所屬分行代碼 / 名稱 </th>
        <td>
          <div align="left">
            <c:if test="${branchList!=null && !empty branchList}">
             <select name="branchName" id="branchName">
                <c:forEach items="${branchList}" var="branch">
                  <option value="${branch.branchId}"><c:out value="${fn:substring(branch.branchId,3,7)}" />&nbsp;/&nbsp;<c:out value="${branch.chineseName}" /></option>
                </c:forEach>
              </select>
            </c:if>
          </div>
        </td>
      </tr>
      <tr>
        <th><span class="red">＊&nbsp;</span>角色選擇</th>
        <td>
          <div align="left">
            <select name="roleId" id="roleId" class="textfield">
              <c:forEach var="role" items="${roleList}">
                <option value="${role.roleId}"><c:out value="${role.roleName}" /></option>
              </c:forEach>
            </select>
          </div>
        </td>
      </tr>
      <tr>
        <th>新密碼</th>
        <td>
          <div align="left">
            <input type="password" name="newPwd" size="40" maxlength="50" class="textfield" />&nbsp;&nbsp;空白則為原有密碼
          </div>
        </td>
      </tr>
      <tr>
        <th>新密碼確認</th>
        <td>
          <div align="left">
            <input type="password" name="chkPwd" size="40" maxlength="50" class="textfield" />&nbsp;&nbsp;空白則為原有密碼
          </div>
        </td>
      </tr>
      <tr>
        <th>聯絡電話</th>
        <td>
          <div align="left">
            <input type=text name="telNo" size="30" maxlength="20" class="textfield" value="${modifyUser.telNo}"/>
          </div>
        </td>
      </tr>
      <tr>
        <th>電子郵件</th>
        <td>
          <div align="left">
            <input type="text" name="email" size="60" maxlength="100" class="textfield" value="${modifyUser.email}"/>
          </div>
        </td>
      </tr>
      <tr>
        <th>上次修改密碼時間</th>
        <td>
          <div align="left"><fmt:formatDate value="${modifyUser.changePasswordTime}" pattern="yyyy/MM/dd" />
            <br /><font color="#B22222">(密碼尚餘 <c:out value="${pwdChangeday}" /> 天有效)</font></div>
        </td>
      </tr>
<!-- 	  <tr> -->
<!-- 		  <th>＊&nbsp;承作業務類別</th> -->
<!-- 		  <td> -->
<!-- 			  <div align="left">             -->
<!--               	<input type="checkbox" name="businessTypeQ" value="1" /> 承作標單業務 -->
<!--               	<input type="checkbox" name="businessTypeC" value="1" /> 承作本票業務 -->
<!-- 				<input type="checkbox" name="businessTypeG" value="1" /> 承作保證業務 -->
<!-- 			  </div> -->
<!-- 		  </td> -->
<!-- 	  </tr> -->
      <tr>
        <th>狀態</th>
        <td>
          <div align="left">
            <c:if test="${modifyUser.status==0}">
              停用<br /><font color="#B22222">請洽管理人員啟用方才可以操作</font>
            </c:if>
            <c:if test="${modifyUser.status==1}">
            啟用
            </c:if>
            <c:if test="${modifyUser.status>1}">
            N/A
            </c:if>
          </div>
        </td>
      </tr>
    </table>
        </td>
      </tr>
</table>
<p class="pbtn">
  <input name="cancel" type="button" class="button-brown" onClick="javascript:location.href='<%=request.getContextPath()%>/accountManagement'" value="回上頁">
  &nbsp;
  <input name="Submit22" type="button" class="button-brown" onClick="preview();" value="預覽">
</p>
<p>&nbsp;</p>
  </form:form>
  </div>
  <div id="modifyUserSettingPreview">
  <h1><span class="title-l">帳號管理-</span>修改使用者資料-預覽</h1>
<form:form name="modifyUserSettingPreviewform" method="post">
 
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
            <th width="25%">帳號</th>
            <td width="75%"><div align="left">&nbsp;<c:out value="${modifyUser.userId}" /></div></td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>使用者名稱</th>
            <td>
              <div align="left" id="userNamePreview"></div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>所屬分行代碼 / 名稱 </th>
            <td>
              <div align="left" id="branchPreview">
          
              </div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>所屬角色</th>
            <td>
              <div align="left" id="roleNamePreview">
              </div>
            </td>
          </tr>
          <tr>
             <th><div id="pwdPreview"></div></th>
            <td><div align="left">****************</div></td>
          </tr>
          <tr>
            <th>聯絡電話</th>
            <td>
              <div align="left" id="telNoPreview"></div>
            </td>
          </tr>
          <tr>
            <th>電子郵件</th>
            <td>
              <div align="left" id="emailPreview">
              </div>
            </td>
          </tr>
          <tr>
            <th>上次修改密碼時間</th>
            <td>
              <div align="left">&nbsp;<fmt:formatDate value="${modifyUser.changePasswordTime}" pattern="yyyy/MM/dd" />
                <br /><font color="#B22222">(密碼尚餘<c:out value=" ${pwdChangeday}" /> 天有效)</font></div>
            </td>
          </tr>
<!-- 		  <tr> -->
<!--             <th>承作業務類別</th> -->
<!--             <td> -->
<!--               <div align="left" id="businessTypePreview"> -->
<!--               </div> -->
<!--             </td> -->
<!--           </tr> -->
          <tr>
            <th>狀態</th>
            <td>
              <div align="left">&nbsp;
                <c:if test="${modifyUser.status == 0}">
                  停用<br /><font color="#B22222">請洽管理人員啟用方才可以操作</font>
                </c:if>
                <c:if test="${modifyUser.status==1}">
                啟用
                </c:if>
                <c:if test="${modifyUser.status>1}">
                N/A
                </c:if>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p class="pbtn">
    <input name="cancel" type="button" class="button-brown" onClick="toSetting();" value="回上頁">
    &nbsp;
    <input name="Submit22" type="button" class="button-brown" onClick="updateModifyUser();" value="確認">
  </p>
  <p>&nbsp;</p>
</form:form>
  
  </div>
  </body>
</html>
