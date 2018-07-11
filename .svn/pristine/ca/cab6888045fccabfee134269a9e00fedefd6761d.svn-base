<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/javascript/join.js"></script>
</head>
<body id="scroller">
	<h1>
		<span class="title-l">退回原因維護</span>-<c:if test="${searchBean.action == 'add'}">新增</c:if><c:if test="${searchBean.action == 'edit'}">修改</c:if>退回原因
	</h1>
	<form:form name="editForm" id="editForm" method="post" commandName="searchBean">
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table_none">
						<tr>
							<td height="30"><img
								src="<c:url value="/resources/images/page.gif"/>" border="0"
								align="absmiddle" alt="">有紅色<span class="red">*</span>者為必填欄位。</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table05">
						<tr>
							<th width="80">退回原因代碼</th>
							<c:if test="${searchBean.action == 'add'}">
								<td>
									<div align="left">系統自動產生</div>
								</td>
							</c:if>
							<c:if test="${searchBean.action == 'edit'}">
								<td>
									<div align="left">
										<c:out value="${searchBean.codeKey}"></c:out>
									</div>
								</td>
							</c:if>

						</tr>
						<tr>
							<c:if test="${searchBean.action == 'add'}">
								<th width="80"><span class="red">＊&nbsp;</span>退回原因類別</th>
								<td>
									<div align="left">
										<form:select style="width: 91; height: 18" name="codeType"
											path="codeType" multiple="false">
											<c:forEach var="syscode" items="${sysCodeList}">
												<form:option value="${syscode.id.codeKey}"><c:out value="${syscode.codeValue}" /></form:option>
											</c:forEach>
										</form:select>
									</div>
								</td>
							</c:if>
							<c:if test="${searchBean.action == 'edit'}">
								<th width="80">退回原因類別</th>
								<td><div align="left"><c:out value="${searchBean.codeKeyView}"></c:out></div></td>
							</c:if>
						</tr>
						<tr>
							<th width="80"><span class="red">＊&nbsp;</span>退回原因</th>
							<td>
								<div align="left">
									<input id="codeValue" name="codeValue" type="text"
										class="textfield" size="30" value="${searchBean.codeValue}">
								</div>
							</td>
						</tr>
						<tr>
							<th width="80">使用狀態</th>
							<td>
								<div align="left">
									<form:radiobutton path="status" value="1" />
									可使用
									<form:radiobutton path="status" value="0" />
									暫停使用
								</div>
							</td>
						</tr>
						<tr>
							<th width="80">備註</th>
							<td><div align="left"><input name="memo" type="text" class="textfield"
								size="100" value="${searchBean.memo}"></div></td>
						</tr>


					</table>

				</td>
			</tr>
		</table>
		<p class="pbtn">
			<input id="resetBtn" name="cancel" type="button" class="button-brown"
				value="重設" /> &nbsp; <input id="doPreview" type="button"
				class="button-brown" value="確認" />
		</p>
		<input id="hiddenAction" type="hidden" name="action"
			value="${searchBean.action}">
		<input id="hiddenAction" type="hidden" name="codeKey"
			value="${searchBean.codeKey}">
		<input id="hiddenAction" type="hidden" name="codeTypeEdit"
			value="${searchBean.codeType}">
	</form:form>
	<script type="text/javascript">
		$(function() {

			$("#doPreview")
					.click(
							function() {
								var codeValue = $("#codeValue").val();

								if (codeValue == '') {
									alert("必填欄位不完整");
									return false;
								} else {
									if (confirm("是否確認執行退回原因維護")) {
										$("#editForm")
												.attr("action",
														'<c:url value="/rejectReasonMaintain/doSave"/>');
										$("#editForm").submit();
									}
								}
							});

			$("#resetBtn").click(function() {
				$("input[name='codeValue']").val('');
				$("input[name='memo']").val('');
				$("input[name='status']")[0].checked = true;
			});
		});
	</script>
</body>
</html>
