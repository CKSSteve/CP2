<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<html>
<head>
<c:if test="${message!=null && !empty message}">
  <script type="text/javascript">
  alert('<c:out value="${message}" />');
  </script>
  </c:if>
  
<script type="text/javascript" language="javascript">


function moidfyUser(varUserId,varUserName) {
  
	 $('input[name="userId"]').val(varUserId);
	 $('input[name="userName"]').val(varUserName);
	 $("#queryForm").attr("action", '<c:url value="/accountManagement/moidfyAccount"/>')
	 $("#queryForm").submit();
}


function addUser() {
  userDataForm.method.value = "addUser";
  userDataForm.personalUserId.value = "";
  userDataForm.submit();
}


function startUsingUser(varUserId,varUserName) {

  if(confirm('您確定要變更 ('+varUserId+') 使用者狀態嗎？')) {
		 $('input[name="userId"]').val(varUserId);
		 $('input[name="userName"]').val(varUserName);
		 $("#queryForm").attr("action", '<c:url value="/accountManagement/disableAccount"/>')
		 $("#queryForm").submit();
  } else {
    return false;
  }
}


function detail(varUserId) {
	$('input[name="userId"]').val(varUserId);
	$("#queryForm").attr("action", '<c:url value="/accountManagement/previewAccount"/>')
	 $("#queryForm").submit();
}

</script>
</head>

<body id="scroller">
<form:form id="queryForm" name="queryForm" method="POST">
  <input id="toPage" type="hidden" name="page">
  <input type="hidden" name="userId" value="">
  <input type="hidden" name="userName" value=""/>

  <h1><span class="title-l">帳號管理清冊</span></h1>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
          <tr>
            <td height="30">
              <img src="<c:url value="/resources/images/page.gif"/>" border="0" align="middle" alt="">以下為所有使用者明細資料，請點選
                <span class="red">[新增帳號]</span> 功能，或利用
                <img src="<c:url value="/resources/images/icon/icon_03.gif"/>" width="10" height="10" alt="key"> 點選使用者資料進行使用者資料修改。
            </td>
          </tr>
        </table>
        <c:if test="${users!=null && !empty users}">
          <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table01">
            <tr>
              <th align="middle" nowrap>修改</th>
              <th align="middle" nowrap>使用者帳號</th>
              <th align="middle" nowrap>使用者名稱</th>
              <th align="middle" nowrap>隸屬分行代碼&nbsp;/&nbsp;名稱</th>
              <th align="middle" nowrap>隸屬角色</th>
<!-- 			  <th align="middle" nowrap>承作業務類別</th> -->
              <th colspan="2" align="middle" nowrap>啟用&nbsp;/&nbsp;關閉</th>
            </tr>
            <c:set var="index" value="1" scope="page" />
            <c:forEach var="user" items="${users.content}" varStatus="status">
              <tr class="<c:out value="${status.index%2==0 ? '' : 'td-lighty'}" />">
                <td>
                  <a href="#" onClick="moidfyUser('<c:out value="${user.userId}" />','<c:out value="${user.userName}" />');">
                  <img src="<c:url value="/resources/images/icon/icon_03.gif"/>" width="11" height="11" border="0"  alt="修改" title="修改"></a>
                </td>
                <td><a href="#" onClick="detail('<c:out value="${user.userId}" />');"><c:out value="${user.userId}" /></a></td>
                <td><c:out value="${user.userName}" /></td>
                <td><c:out value="${fn:substring(user.branchId,3,7)}" />&nbsp;/&nbsp;<c:out value="${user.branch.chineseName}" /></td>
                <td>
<%--                   <c:forEach items="${user.role}" var="role"> --%>
                  <c:out value="${user.role.roleName}" />
<%--                   </c:forEach> --%>
                </td>
<!-- 				<td> -->
<%-- 				<c:if test="${user.businessTypeQ == 'Y'}">承作標單業務</c:if> --%>
<%-- 				<c:if test="${user.businessTypeC == 'Y'}">承作本票業務</c:if> --%>
<%-- 				<c:if test="${user.businessTypeG == 'Y'}">承作保證業務</c:if> --%>
<%-- 				<c:if test="${user.businessTypeQ != 'Y'&&user.businessTypeC != 'Y'&&!user.businessTypeG != 'Y'}">無選業務類別</c:if> --%>
<!-- 				</td> -->
                <td>
                  <font color="#0000FF">
<%--                     <c:forEach items="${user.role}" var="role"> --%>
                      <c:choose>
                        <c:when test="${user.status==1}">
                        	已啟用
                        </c:when>
                        <c:otherwise>
                          <font color="#FF0000">已關閉</font>
                        </c:otherwise>
                      </c:choose>
<%--                     </c:forEach> --%>
                   </font>
                </td>
                <td>
                  <!-- 總行系統管理員及登入者都不行關閉自己的登入權限 -->
<%--                   <c:forEach items="${user.role}" var="role"> --%>
                    <c:choose>
                      <c:when test="${(user.role.roleId==1 && user.branch.typeId eq 'HeadOffice') || user.userId eq signUserId}">
                      </c:when>
                      <c:otherwise>
                      		<c:if test="${user.status==1}">
                      			<a href="#" onClick="startUsingUser('<c:out value="${user.userId}" />','<c:out value="${user.userName}" />');">
                          			<img src="<c:url value="/resources/images/close.gif"/>" border="0" alt="關閉" title="關閉"/>
                        		</a>
							</c:if>
							<c:if test="${user.status==0}">
                      			<a href="#" onClick="startUsingUser('<c:out value="${user.userId}" />','<c:out value="${user.userName}" />');">
                          			<img src="<c:url value="/resources/images/open.gif"/>" border="0" alt="啟用" title="啟用"/>
                        		</a>
							</c:if>
                      </c:otherwise>
                    </c:choose>
<%--                   </c:forEach> --%>
                </td>
              </tr>
            </c:forEach>
          </table>
        </c:if>
        <c:if test="${users==null || empty users}">
          <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table01">
            <tr>
              <th width="10%">修改</th>
              <th width="17%">使用者帳號</th>
              <th width="17%">使用者名稱</th>
              <th width="20%">隸屬分行代碼&nbsp;/&nbsp;名稱</th>
              <th width="17%">隸屬角色</th>
              <th width="19%">啟用&nbsp;/&nbsp;關閉</th>
            </tr>
            <tr>
              <td colspan="6"><font color="#FF0000">無 任 何 使 用 者 資 料 !!!</font></td>
            </tr>
          </table>
        </c:if>
</td>
    </tr>

    			<!-- 分頁  -->
			<c:if test="${users.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${users}" paginationSize="5" />
					</tr>
				</table>
			</c:if>
			<!-- 分頁結束 -->
  </table>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr class="pbtn">
      <td>
        <br />
        <input name="cancel" type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁" />&nbsp;
        <input name="Submit2" type="button" class="button-brown" onClick="javascript:location.href='<%=request.getContextPath()%>/accountManagement/newAccount'" value="新增帳號">&nbsp;
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
