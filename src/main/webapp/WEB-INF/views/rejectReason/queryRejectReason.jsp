<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/javascript/join.js"></script>
</head>
<body id="scroller">
	<h1>
		<span class="title-l">退回原因維護清冊</span>
	</h1>
	<form:form name="queryForm" id="queryForm" method="post" commandName="searchBean">
		<input id="toPage" type="hidden" name="page">
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table_none">
						<tr>
							<td height="30"><img
								src="<c:url value="/resources/images/page.gif"/>" border="0"
								align="absmiddle" alt="">以下為所有退回原因明細資料，請點選<span
								class="red">[新增退回原因]</span> 功能，或利用 <img
								src="<c:url value="/resources/images/icon/icon_03.gif"/>"
								width="9" height="9"> 點選退回原因資料進行修改。</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="0" id="table01">
						<tr>
							<th align="middle" nowrap>設定</th>
							<th align="middle" nowrap>退回原因類別</th>
							<th align="middle" nowrap>退回原因代碼</th>
							<th align="middle" nowrap>退回原因</th>
							<th align="middle" nowrap>使用狀態</th>
							<th align="middle" nowrap>備註</th>
						</tr>
						<c:if test="${resultList.totalElements > 0}">
							<c:forEach items="${resultList.content}" var="item" varStatus="idx">
								<tr class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
									<td align="center" nowrap="nowrap">
										<a href="#" onClick="modify('${item.codeType}','${item.codeKey}');" title="設定" ><img src="<c:url value="/resources/images/icon/icon_03.gif"/>" width="11" height="11" border="0"></a>
									</td>
									<td align="center" nowrap="nowrap"><c:out value="${item.codeKeyView}"/></td>
									<td align="center" nowrap="nowrap"><c:out value="${item.codeKey}" /></td>
									<td align="center" nowrap="nowrap"><c:out value="${item.codeValue}" /></td>
									<td align="center" nowrap="nowrap"><tags:codeMap type="REJECT_REASON_STATUS" code="${item.status}"/></td>
									<td align="center" nowrap="nowrap"><c:out value="${item.memo}" /></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${resultList.totalElements == 0}">
							<tr>
								<td colspan="7"><font size="3" color="red">查無資料!</font></td>
							</tr>
						</c:if>
					</table>
				</td>
			</tr>
			<c:if test="${resultList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table-count">
					<tags:pagination page="${resultList}" paginationSize="5"/>
				</table>
			</c:if>
		</table>
		<p class="pbtn">
			<input name="cancel" type="button" class="button-brown"
				onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'"
				value="回首頁"> &nbsp; 
				<input id="doAdd" type="button" class="button-brown" value="新增退回原因" />
		</p>
	</form:form>
	<form:form id="modifyForm">
    	<input id="codeType" type="hidden" name="codeType"/>
    	<input id="codeKey" type="hidden" name="codeKey" />
	</form:form>
	<script type="text/javascript">
		$(function() {
			$("#doAdd").click(function() {
				$("#queryForm").attr("action",'<c:url value="/rejectReasonMaintain/doAdd"/>');
				$("#queryForm").submit();
			});
		});
		
		function modify(codeType, codeKey) {
			$("#codeType").val(codeType);
			$("#codeKey").val(codeKey);			
			$("#modifyForm").attr("action",'<c:url value="/rejectReasonMaintain/doModify"/>');
			$("#modifyForm").submit();
		}
		
	</script>
</body>
</html>
