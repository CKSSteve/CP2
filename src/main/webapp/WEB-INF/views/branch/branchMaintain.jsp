<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>

<body id="scroller">
	<h1>
		<span class="title-l">分行資料維護</span>
	</h1>
	<form:form name="queryForm" id="queryForm" method="post">
		<input id="toPage" type="hidden" name="page">
		<input id="branchId" type="hidden" name="branchId">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td><table width="98%" border="0" align="center"
						cellpadding="0" cellspacing="0" id="table_none">
						<tr>
							<td height="30"><img
								src="<c:url value="/resources/images/page.gif"/>" border="0"
								align="absmiddle" alt="">以下為所有分行明細資料，請點選<span class="red">
									[新增分行]</span> 功能，或利用 <img
								src="<c:url value="/resources/images/icon/icon_03.gif"/>"
								width="9" height="9" alt=""> 點選分行資料進行使用者資料修改。</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="0" id="table01">
						<TR>
							<th align="middle">修改</th>
							<th align="middle">分行代碼</th>
							<th align="middle">分行名稱</th>
							<th align="middle">隸屬類別</th>
							<th align="middle" nowrap>轄下分行</th>
						</TR>
						<c:forEach var="branch" items="${branchMaintainList.content}">
							<TR>
								<TD><a href="#"
									onclick="modifyBranch('<c:out value="${branch.branchId}" />');">
										<img src="<c:url value="/resources/images/icon/icon_03.gif"/>"
										width="11" height="11" border="0" alt="" title="修改" id="<c:out value="${branch.branchId}" />">
								</a></TD>
								<TD><c:out value="${fn:substring(branch.branchId,3,7)}" /></TD>
								<TD><c:out value="${fn:replace(branch.chineseName,'　','')}" /></TD>
								<TD><c:out value="${branch.branchType.typeName}" /></TD>
								<TD>
									<c:if test="${!empty branch.serviceCenterMapping}">
										<c:forEach var="serviceCenterMapping" items="${branch.serviceCenterMapping}">
										<c:out value="${fn:substring(serviceCenterMapping.oneBranch.branchId,3,7)}" />&nbsp;<c:out value="${fn:replace(serviceCenterMapping.oneBranch.chineseName,'　','')}" /></br>
									     </c:forEach>           
									</c:if>
								</TD>
							</TR>
						</c:forEach>
					</table></td>
			</tr>
			<!-- 分頁  -->
			<c:if test="${branchMaintainList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${branchMaintainList}" paginationSize="5" />
					</tr>
				</table>
			</c:if>
			<!-- 分頁結束 -->
		</table>
		<p class="pbtn">
			<input name="cancel" type="button" class="button-brown"
				onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁"> &nbsp; <input name="Submit2"
				type="button" class="button-brown" onClick="javascript:location.href='<%=request.getContextPath()%>/branchMaintain/newBranch'"
				value="新增分行">
		</p>
		<p>&nbsp;</p>
	</form:form>
	<script type="text/javascript">
	function modifyBranch(branchId){
		$("#branchId").val(branchId);
		$("#queryForm").attr("action", '<c:url value="/branchMaintain/modifyBranch"/>');
		$("#queryForm").submit();
	}
	
	</script>
</body>
</html>

