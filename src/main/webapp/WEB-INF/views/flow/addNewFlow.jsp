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
		<span class="title-l">簽核流程維護</span>-<c:if test="${searchBean.action == 'add'}">新增</c:if><c:if test="${searchBean.action == 'edit'}">修改</c:if>簽核流程
	</h1>
	<form:form name="addNewFlowForm" id="addNewFlowForm" method="post"
		commandName="searchBean">
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table_none">
						<tr>
							<td height="30"><img
								src="<c:url value="/resources/images/page.gif"/>" border="0"
								align="absmiddle" alt="">簽核流程資料:標有<span class="red">*</span>的欄位為必要資料，請正確填寫。</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table05">
						<tr>
							<th width="80">流程代碼</th>
							<c:if test="${searchBean.action == 'add'}">
								<td>
									<div align="left">系統自動產生</div>
								</td>
							</c:if>
							<c:if test="${searchBean.action == 'edit'}">
								<td>
									<div align="left"><c:out value="${searchBean.flowId}"></c:out></div>
								</td>
							</c:if>
							
						</tr>
						<tr>
							<th width="80"><span class="red">＊&nbsp;</span>流程名稱</th>
							<td>
								<div align="left">
									<input name="flowName" id="flowName" type="text" class="textfield" size="40" value="${searchBean.flowName}">
								</div>
							</td>
						</tr>
						<tr>
							<th width="80">流程說明</th>
							<td>
								<div align="left">
									<input name="flowMemo" type="text" class="textfield" size="100" value="${searchBean.flowMemo}">
								</div>
							</td>
						</tr>
						<tr>
							<th width="80"><span class="red">＊&nbsp;</span>套用功能</th>
							<td>
								<div align="left">
									<c:forEach items="${flowFunctionList}" var="flowFunction">
										<c:if test="${searchBean.flowFunctionId == flowFunction.functionId}">
											<form:checkbox name="flowFunctionId" path="flowFunctionId" id="flowFunctionId" value="${flowFunction.functionId}"  checked="checked"/> <c:out value="${flowFunction.functionName}"/>
										</c:if>
										<c:if test="${searchBean.flowFunctionId != flowFunction.functionId}">
											<form:checkbox name="flowFunctionId" path="flowFunctionId" id="flowFunctionId" value="${flowFunction.functionId}" /> <c:out value="${flowFunction.functionName}" />
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
										height="20" border="0" align="absmiddle">簽核流程步驟設定，若執行角色選擇「空白」，表示刪除該步驟。</td>
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
											<form:select style="width: 91; height: 18" path="roleId" name="roleId" multiple="false">
												<c:forEach var="role" items="${roleList}">
													<form:option value="${role.roleId}_${loop.index}"><c:out value="${role.roleName}" /></form:option>
												</c:forEach>
												<form:option value="0_${loop.index}" label="" />
											</form:select>
										</div></td>
								</tr>
							</c:forEach>
						</table>
					</table>
					<p class="pbtn">
						<input id="resetBtn" name="cancel" type="button" class="button-brown" value="重設" /> &nbsp; 
						<input id="doPreview" type="button" class="button-brown" value="確認" />
					</p>
			</td>
		</tr>
		</table>
		<input id="hiddenAction" type="hidden" name="action" value="${searchBean.action}">
		<input id="hiddenFlowId" type="hidden" name="flowId" value="${searchBean.flowId}">
	</form:form>
<script type="text/javascript">
$(function() {
	
	$("#doPreview").click(function(){
		var flowName = $("#flowName").val();
		var flowFunctionIdArray = $('input[name="flowFunctionId"]:checked').map(function(){
										return $(this).val();
									}).toArray();
		var roleId =  $("select[name='roleId'] option:selected").text();
		var roleIdArray = $.map($("select[name='roleId'] option:selected"), function(a) { return a.value; }).join(',');
		var checkStep2 = false;
		var checkStep3 = false;
		var idStep1 = "";
		var idStep2 = "";
		var idStep3 = "";
		var roleIdArrayNew = roleIdArray.split(',');
		for(var i=0;i<roleIdArrayNew.length;i++){
			var id = roleIdArrayNew[i].split('_');
			if(id[0]==0 && id[1]==1){
				alert("流程步驟1:執行角色不可為空");
				return false;
			}else if(id[0]==0 && id[1]==2){
				checkStep2 = true;
			}else if(id[0]==0 && id[1]==3){
				checkStep3 = true;
			}
		}
		idStep1 = roleIdArrayNew[0].split('_')[0];
		idStep2 = roleIdArrayNew[1].split('_')[0];
		idStep3 = roleIdArrayNew[2].split('_')[0];
		//檢查角色身分不可重複
		if((idStep2 != "0" && idStep1 == idStep2) || (idStep3 != "0" && idStep1 == idStep3) || (idStep2 != "0" && idStep3 != "0" && idStep2==idStep3) ){
			alert("流程步驟之角色身分不可重複");
			return false;
		}
		if (flowName == '' || flowFunctionIdArray =='' || roleId == '') {
			alert("必填欄位不完整");
			return false;
		}else if(checkStep2 && !checkStep3){
			alert("流程步驟須照順序填寫");
			return false;
		} else {
			if(confirm("是否確認執行簽核流程")){
				$("#addNewFlowForm").attr("action", '<c:url value="/flowMaintain/doAdd"/>');
				$("#addNewFlowForm").submit();
			}
		}
	});
	
	$("#resetBtn").click(function() {
		$("input[name='flowName']").val('');
		$("input[name='flowMemo']").val('');
		$("input[name='flowFunctionId']").prop('checked', false);
		$("select[name='roleId']").val('0');
	});
});

</script>
</body>
</html>
