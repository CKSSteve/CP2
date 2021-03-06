<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<html>
<head>
<title>外幣網路開狀系統</title>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-3.2.1.slim.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-1.9.1.min.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
function show_date(){
    var time=new Date(); //宣告日期物件，儲存目前系統時間
    t_year=time.getFullYear(); //取得今年年分
    if(t_year > 2007){
      document.write(" - " + t_year);
    }
  }
</script>
</head>


<div align="center">
  <form:form name="changePwd" id="changePwd" method="post">
  <body>
<%-- <c:if test="${!empty changePasswordMsg}"> --%>
<%-- 	<h3><c:out value="${changePasswordMsg}" /></h3> --%>
<%-- </c:if> --%>
    <input type="hidden" name="userId" value="<sec:authentication property="name"/>"/>
    <input type="hidden" name="userName" value="<sec:authentication property="principal.viewUserName"/>"/>
    <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="<c:url value="/resources/images/index_01.png"/>" width="434" height="315" alt=""></td>
          <td><img src="<c:url value="/resources/images/index_02.png"/>" width="434" height="315" alt=""></td>
      </tr>
      <tr>
        <td><img src="<c:url value="/resources/images/index_03.png"/>" width="434" height="315" alt=""></td>
          <td style="background:url(<c:url value="/resources/images/index_04.png"/> ) no-repeat" width="434" height="315" >
            <table width="300" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
               <td align="left">
               <c:if test="${!empty changePasswordMsg}">
				<a style="color: red;"><c:out value="${changePasswordMsg}" /></a>
				</c:if>
               </td>
              </tr>
              <tr>
                <td height="129" valign="top" >
                  <table width="240" border="0" cellpadding="2" cellspacing="0" id="logintable_change" style="padding:0px 180px 0px 0px">
                    <tr>
                        <td colspan="2" align="center">
	                        <div id="msg" style="display:<c:out value="${message==null?'none':'block'}" />">
	                        	<b><font size="2pt" color="red"><c:out value="${message}" /></font></b>
	                        </div>
                        </td>
                    </tr>
                    <tr>
                      <td width="40%" align="right" nowrap>舊密碼：		  </td>
                      <td>
                        <input name="oldPassword" type="password" class="inputtxt" size="12"/>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" nowrap>新密碼：		  </td>
                      <td>
                        <input name="newPassword" type="password" class="inputtxt" size="12"/>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" nowrap>密碼確認：		  </td>
                      <td>
                        <input name="chkPassword" type="password" class="inputtxt" size="12"/>
                      </td>
                    </tr>
                    <tr>
<!--                       <td>&nbsp;</td> -->
                      <td colspan="2" align="right">
                       <input type="reset" class="loginbtn" value="重設" />
                        &nbsp;&nbsp;
                        <input name="send" type="button" class="loginbtn" value="修改密碼" onclick="chageIt();" />
                      </td>
                    </tr>
                  </table>
          </td>
              </tr>
            </table>
        </td>
      </tr>
      <tr>
        <td></td>
          <td align="right" class="copyright" style="padding:5px 10px 0 0">&copy; 2006<script type="text/javascript" language="javascript">show_date();</script> UXB2B Inc. All Rights Reserved.</td>
      </tr>
    </table>
  </form:form>

</div>
</body>
</html>

<script type="text/javascript" language="javascript">
$('input[name="oldPassword"]').focus();
// document.onkeydown=keyFunction;
// function keyFunction(){
//   var code= event.keyCode;
//   // 按enter key時 , 以submit event 方式處理
//   if(code==13){
//     chageIt();
//   }
// }

function chageIt() {
  var msg = "";
  oldPassword = $('input[name="oldPassword"]'); 
  newPassword = $('input[name="newPassword"]');
  chkPassword = $('input[name="chkPassword"]');

  if(oldPassword.val()=="" && oldPassword.val().length<1) {
    msg+="舊密碼欄位，尚未輸入\r\n";
  }
  if(oldPassword.val()!='<sec:authentication property="principal.password"/>') {
    msg+="舊密碼，輸入錯誤\r\n";
  }

  if(newPassword.val()=="" && newPassword.val().length<1) {
    msg+="新密碼欄位，尚未輸入\r\n";
  } else if(newPassword.val().length<8) {
    msg+="新密碼欄位資料不足八位\r\n";
  }
  if(chkPassword.val()=="" && chkPassword.val().length<1) {
    msg+="密碼確認欄位，尚未輸入\r\n";
  } else if(chkPassword.val().length<8) {
    msg+="密碼確認欄位資料不足八位\r\n";
  }
  if(newPassword.val()!=chkPassword.val()) {
    msg+="新密碼欄位與密碼確認欄位，內容不一致\r\n";
  } else {
    if(newPassword.val()=='<sec:authentication property="principal.password"/>') {
      msg+="新密碼不可以與舊密碼相同\r\n";
    }
  }
  if(msg.length>0) {
    alert(msg);
    return false;
  } else {
		$("#changePwd").attr("action", '<c:url value="/login/changePwd"/>');
		$("#changePwd").submit();
  }

}
</script>
