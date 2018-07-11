<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

  <c:if test="${message!=null && !empty message}">
    <script type="text/javascript">
    alert('${message}');
    </script>
    </c:if>
    
    <script type="text/javascript">
$(function() {
	var typMsg = "";
	if('<c:out value="${modifyUser.businessTypeQ}" />'=='Y'){
		typMsg+="承作標單業務   ";
	}
	if('<c:out value="${modifyUser.businessTypeC}" />'=='Y'){
		typMsg+="承作本票業務    ";
	}
	if('<c:out value="${modifyUser.businessTypeG}" />'=='Y'){
		typMsg+="承作保證業務";
	}
	$("#businessTypePreview").html(typMsg);
})
</script>
</head>

<body id="scroller">
<h1><span class="title-l">帳號管理-個人資料-</span>預覽</h1>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="8"><img src="<c:url value="/resources/images/table/top_left.gif"/>" width="8" height="8" alt=""></td>
        <td style="background:url(<c:url value="/resources/images/table/top_bg.gif"/>) repeat-x"><img src="<c:url value="/resources/images/spacer.gif"/>" width="1" height="1" alt=""></td>
          <td width="8"><img src="<c:url value="/resources/images/table/top_right.gif"/>" width="8" height="8" alt=""></td>
    </tr>
    <tr>
      <td width="8" style="background:url(<c:url value="/resources/images/table/left_bg.gif"/>) repeat-y">&nbsp;</td>
      <td>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
          <tr>
            <th width="25%">帳號</th>
            <td width="75%"><div align="left">&nbsp;<c:out value="${modifyUser.userId}" /></div></td>
          </tr>
          <tr>
            <th>使用者名稱</th>
            <td>
              <div align="left">&nbsp;<c:out value="${modifyUser.userName}" /></div>
            </td>
          </tr>
          <tr>
            <th>所屬分行代碼 / 名稱 </th>
            <td><div align="left">&nbsp;<c:out value="${fn:substring(modifyUser.branchId,3,7)}" />&nbsp;&nbsp;/&nbsp;&nbsp;<c:out value="${modifyUser.branch.chineseName}" /></div></td>
          </tr>
          <tr>
            <th>所屬角色</th>
            <td>
              <div align="left">&nbsp;
                <c:if test="${!empty modifyUser.role}">
                  <c:out value="${modifyUser.role.roleName}" />
   
                </c:if>
                <c:if test="${empty modifyUser.role}">
                N/A
                </c:if>
              </div>
            </td>
          </tr>
          <tr>
            <th>聯絡電話</th>
            <td>
              <div align="left">&nbsp;<c:out value="${modifyUser.telNo}" /></div>
            </td>
          </tr>
          <tr>
            <th>電子郵件</th>
            <td>
              <div align="left">&nbsp;<c:out value="${modifyUser.email}" />
              </div>
            </td>
          </tr>
          <tr>
            <th>上次修改密碼時間</th>
            <td>
              <div align="left">&nbsp;<fmt:formatDate value="${modifyUser.changePasswordTime}" pattern="yyyy/MM/dd" />
                <br /><font color="#B22222">(密碼尚餘 <c:out value="${pwdChangeday}" /> 天有效)</font></div>
            </td>
          </tr>
<!--           <tr> -->
<!-- 			  <th>承作業務類別</th> -->
<!-- 			  <td> -->
<!-- 				  <div align="left" id="businessTypePreview"> -->
	
<!-- 				  </div> -->
<!-- 			  </td> -->
<!-- 		  </tr> -->
          <tr>
            <th>狀態</th>
            <td>
              <div align="left">&nbsp;
              <c:if test="${modifyUser.status==0}">
                停用<br /><font color="#B22222">請洽管理人員啟用方才可以操作</font>
              </c:if>
              <c:if test="${modifyUser.status==1}">
              啟用
              </c:if>
              <c:if test="${modifyUser.status > 1}">
              N/A
              </c:if>
              </div>
            </td>
          </tr>
        </table>
            </td>
            <td width="8" style="background:url(<c:url value="/resources/images/table/right_bg.gif"/>) repeat-y">&nbsp;</td>
    </tr>
    <tr>
      <td width="8"><img src="<c:url value="/resources/images/table/bottom_left.gif"/>" width="8" height="8" alt=""></td>
        <td style="background:url(<c:url value="/resources/images/table/bottom_bg.gif"/>) repeat-x"><img src="<c:url value="/resources/images/spacer.gif"/>" width="1" height="1" alt=""></td>
          <td width="8"><img src="<c:url value="/resources/images/table/bottom_right.gif"/>" width="8" height="8" alt=""></td>
    </tr>
  </table>

  <p class="pbtn">
    <input name="button" type="button" class="button-brown" onClick="javascript:location.href='<%=request.getContextPath()%>/accountManagement'" value="回上頁">&nbsp;&nbsp;
  </p>
</body>
</html>
