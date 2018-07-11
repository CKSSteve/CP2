<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/javascript/join.js"></script>
</head>
<body id="scroller">
	<h1>
		<span class="title-l">簽核流程維護清冊</span>
	</h1>
	<form:form name="queryForm" id="queryForm" method="post" commandName="searchBean">
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table_none">
						<tr>
							<td height="30"><img
								src="<c:url value="/resources/images/page.gif"/>" border="0"
								align="absmiddle" alt="">以下為所有簽核流程明細資料，請點選<span
								class="red">[新增簽核流程]</span> 功能，或利用 <img
								src="<c:url value="/resources/images/icon/icon_03.gif"/>"
								width="9" height="9"> 點選簽核流程資料進行修改。</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="0" id="table01">
						<tr>
							<th align="middle" nowrap>流程設定</th>
							<th align="middle" nowrap>流程代碼</th>
							<th align="middle" nowrap>流程名稱</th>
							<th align="middle" nowrap>套用功能</th>
							<th align="middle" nowrap>刪除</th>
						</tr>
						<c:if test="${resultList.totalElements > 0}">
							<c:forEach items="${flowList}" var="item" varStatus="idx">
								<tr
									class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
									<td align="center" nowrap="nowrap">
										<c:if test="${item.alreadyDel == false}">此流程尚有審核中案件</c:if>
										<c:if test="${item.alreadyDel == true}"><a href="#" onClick="modify('${item.flowId}');" title="流程設定" ><img src="<c:url value="/resources/images/icon/icon_03.gif"/>" width="11" height="11" border="0"></a></c:if>
									</td>
									<td align="center" nowrap="nowrap"><c:out value="${item.flowId}" /></td>
									<td align="center" nowrap="nowrap"><c:out value="${item.flowName }" /></td>
									<td align="center" nowrap="nowrap">
										<c:forEach items="${item.functions}" var="functions" varStatus="idx">
											<c:out value="${functions.functionName}" />
											<c:if test="${!idx.last}">、</c:if>
										</c:forEach>
									</td>
									<td align="center" nowrap="nowrap">
										<c:if test="${item.alreadyDel == false}">此流程尚有審核中案件</c:if>
										<c:if test="${item.alreadyDel == true}"><a href="#" onClick="del('${item.flowId}','${item.flowName}');" title="刪除"><img src="<c:url value="/resources/images/del.gif"/>" width="20" height="20" border="0"></a></c:if>
									</td>

								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${resultList.totalElements == 0}">
							<tr>
								<td colspan="7"><font size="3" color="red">查無資料!</font></td>
							</tr>
						</c:if>
					</table>
					<input id="toPage" type="hidden" name="page">
					<c:if test="${resultList.totalPages > 1}">
						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table-count">
							<tags:pagination page="${resultList}" paginationSize="5"/>
						</table>
					</c:if>
				</td>
			</tr>
		</table>
		<p class="pbtn">
			<input name="cancel" type="button" class="button-brown"
				onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'"
				value="回首頁"> &nbsp; 
				<c:if test="${doAdd}">
					<input id="doAdd" type="button" class="button-brown" value="新增簽核流程" />
				</c:if>
				
		</p>
	</form:form>
	<form:form id="addNewFlowForm">
    	<input id="flowMemo" type="hidden" name="flowMemo" value=""/>
	    <input id="flowFunctionId" type="hidden" name="flowFunctionId" value=""/>
	    <input id="roleId" type="hidden" name="roleId" value=""/>
	</form:form>
	
	<form:form id="delFlowForm">
    	<input id="flowId" type="hidden" name="flowId"/>
    	<input id="flowName" type="hidden" name="flowName" />
	</form:form>
	<script type="text/javascript">
		$(function() {

			$("#doAdd").click(function() {
				$("#addNewFlowForm").attr("action",'<c:url value="/flowMaintain/newFlow"/>');
				$("#addNewFlowForm").submit();
			});

		});
		
		function del(flowId,flowName) {
			if(confirm("確定刪除簽核流程代碼["+flowId+"]?")){
				$("#flowId").val(flowId);
				$("#flowName").val(flowName);
				$("#delFlowForm").attr("action",'<c:url value="/flowMaintain/delFlow"/>');
				$("#delFlowForm").submit();
			}	
		}
		
		function modify(flowId) {
			
			$("#flowId").val(flowId);
			$("#delFlowForm").attr("action",'<c:url value="/flowMaintain/doModify"/>');
			$("#delFlowForm").submit();
		}
		
	</script>
</body>
</html>
