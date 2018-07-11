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
		<span class="title-l">簽核流程維護</span>-新增簽核流程預覽
	</h1>
	<form:form name="addNewFlowFormPreview" id="addNewFlowFormPreview"
		method="post" commandName="searchBean">
		<table width="98%" border="0" align="left" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="8"><img
					src="<c:url value="/resources/images/table/top_left.gif"/>"
					width="8" height="8" alt=""></td>
				<td
					style="background:url(<c:url value="/resources/images/table/top_bg.gif"/>) repeat-x">
					<img src="<c:url value="/resources/images/spacer.gif"/>" width="1"
					height="1" alt="">
				</td>
				<td width="8"><img
					src="<c:url value="/resources/images/table/top_right.gif"/>"
					width="8" height="8" alt=""></td>
			</tr>
			<tr>
				<td width="8"
					style="background:url(<c:url value="/resources/images/table/left_bg.gif"/>) repeat-y">&nbsp;</td>
				<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table_none">
						<tr>
							<td height="30"><img
								src="<c:url value="/resources/images/page.gif"/>" border="0"
								align="absmiddle" alt="">簽核流程資料:</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table05">
						<tr>
							<th width="80">流程代碼</th>
							<td>
								<div align="left">
									<c:out value="${searchBean.flowId}" />
								</div>
							</td>
						</tr>
						<tr>
							<th width="80">*流程名稱</th>
							<td>
								<div align="left">
									<c:out value="${searchBean.flowName}" />
								</div>
							</td>
						</tr>
						<tr>
							<th width="80">流程說明</th>
							<td>
								<div align="left">
									<c:out value="${searchBean.flowMemo}" />
								</div>
							</td>
						</tr>
						<tr>
							<th width="80">*套用功能</th>
							<td>
								<div align="left">
									<c:forEach items="${flowFunctionList}" var="flowFunction">
										<c:if test="${searchBean.flowFunctionId == flowFunction.functionId}">
											<form:checkbox path="flowFunctionId" id="flowFunctionId" value="${flowFunction.functionId}"  checked="checked" disabled="true" /> <c:out value="${flowFunction.functionName}" />
										</c:if>
										<c:if test="${searchBean.flowFunctionId != flowFunction.functionId}">
											<form:checkbox path="flowFunctionId" id="flowFunctionId" value="${flowFunction.functionId}"  disabled="true" /> <c:out value="${flowFunction.functionName}" />
										</c:if>
									</c:forEach>
								</div>
							</td>
						</tr>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0" id="table_none">
							<tr>
								<td height="30"><span class="contant"><img
										src="<c:url value="/resources/images/page.gif"/>" width="15"
										height="20" border="0" align="absmiddle">簽核流程步驟:</td>
							</tr>
						</table>

						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0" id="table05">
							<tr>
								<th width="20%">流程步驟</th>
								<th width="80%">執行角色</th>
							</tr>
							<c:forEach begin="1" end="${step}" varStatus="loop">
								<tr>
									<th width="20%">${loop.index}</th>
									<td><div align="left">
											<form:select style="width: 91; height: 18" path="roleId"
												multiple="false">
												<form:option value="0_${loop.index}" label="" />
												<c:forEach var="role" items="${roleList}">
													<form:option value="${role.roleId}_${loop.index}" disabled="true" >
														<c:out value="${role.roleName}" />
													</form:option>
												</c:forEach>
											</form:select>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
						<td width="8"
							style="background:url(<c:url value="/resources/images/table/right_bg.gif"/>) repeat-y">&nbsp;</td>
						<tr>
							<td width="8"><img
								src="<c:url value="/resources/images/table/bottom_left.gif"/>"
								width="8" height="8" alt=""></td>
							<td
								style="background:url(<c:url value="/resources/images/table/bottom_bg.gif"/>) repeat-x">
								<img src="<c:url value="/resources/images/spacer.gif"/>"
								width="1" height="1" alt="">
							</td>
							<td width="8"><img
								src="<c:url value="/resources/images/table/bottom_right.gif"/>"
								width="8" height="8" alt=""></td>
						</tr>
					</table>
					<p class="pbtn">
						<input id="doback" name="cancel" type="button"
							class="button-brown" value="回上頁" /> &nbsp; <input id="doAdd"
							type="button" class="button-brown" value="確認" />
					</p>
				</td>
			</tr>
		</table>
		<input id="hiddenFlowId" type="hidden" name="flowId" value="${searchBean.flowId}">
		<input id="hiddenFlowName" type="hidden" name="flowName" value="${searchBean.flowName}">
		<input id="hiddenFlowMemo" type="hidden" name="flowMemo" value="${searchBean.flowMemo}"/>
		<input id="hiddenAction" type="hidden" name="action" value="${searchBean.action}">
		<div style="display:none"">
			<c:forEach items="${flowFunctionList}" var="flowFunction">
				<c:if test="${searchBean.searchFlowFunctionId == flowFunction.functionId}">
					<form:checkbox path="searchFlowFunctionId" id="searchFlowFunctionId" value="${flowFunction.functionId}"  checked="checked" /> <c:out value="${flowFunction.functionName}" />
				</c:if>
				<c:if test="${searchBean.searchFlowFunctionId != flowFunction.functionId}">
					<form:checkbox path="searchFlowFunctionId" id="searchFlowFunctionId" value="${flowFunction.functionId}"  /> <c:out value="${flowFunction.functionName}" />
				</c:if>
			</c:forEach>
			
			<c:forEach begin="1" end="${step}" varStatus="loop">
				<form:select style="width: 91; height: 18" path="searchRoleId"
					multiple="false">
					<form:option value="0_${loop.index}" label="" />
					<c:forEach var="role" items="${roleList}">
						<form:option value="${role.roleId}_${loop.index}" >
							<c:out value="${role.roleName}" />
						</form:option>
					</c:forEach>
				</form:select>
			</c:forEach>
		</div>
	</form:form>
<script type="text/javascript">

$(function() {
	
	$("#doAdd").click(function(){
		
		$("#addNewFlowFormPreview").attr("action", '<c:url value="/flowMaintain/doAdd"/>');
		$("#addNewFlowFormPreview").submit();
	});
	
	
	$("#doback").click(function() {
		var action = $("#hiddenAction").val();
		
		if(action == 'add'){
			$("#addNewFlowFormPreview").attr("action", '<c:url value="/flowMaintain/newFlow"/>');
			$("#addNewFlowFormPreview").submit();
		}else{
			$("#addNewFlowFormPreview").attr("action", '<c:url value="/flowMaintain/doModify"/>');
			$("#addNewFlowFormPreview").submit();
		}
		
	});
});

</script>
</body>
</html>
