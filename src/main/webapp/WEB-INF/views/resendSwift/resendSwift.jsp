<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/resources/jQueryAssets/jquery-ui.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/checkAll.js"></script>
<script src="<%=request.getContextPath()%>/resources/jQueryAssets/jquery-ui.min.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
$(function() {
	$("#resend").click(function(){	
	  var list = document.getElementsByName("passRecordIdSelect");
	  var length = list.length;
	  var isCheck = false;
	  for(var i=0;i<length;i++) {
	    if(list[i].type=='checkbox' && list[i].checked) {
	      isCheck = true;
	      break;
	    }
	  }
	  if(isCheck) {
	    if(confirm('確定將重送所選取之資料?')){
			$("#queryForm").attr("action", '<c:url value="/rendSendSwift/reSendThread"/>');
			$("#queryForm").submit();
	    }
	  }
	  else {
	    alert("未勾選任一筆或多筆訊息！");
	  }
	})
})
function showReSendReason(reSendReason){
	$("#reSendReasonMsg").html($('<div/>').text(reSendReason).html());
	 $( "#dialog" ).dialog();
}



</script>
</head>


<body id="scroller">
<h1>重新傳送電文</h1>
<div id="dialog" title="重送原因">
  <p id="reSendReasonMsg"></p>
</div>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
    <td>
      <c:if test="${passRecordList.totalElements > 0}">
        <form:form name="queryForm" id="queryForm" method="POST">       
        <input id="toPage" type="hidden" name="page">
          <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table01">
            <tr class="cell-title">
              <th align="center">
                <input  name="selectAll" type="checkbox" title="選擇或取消選擇全部資料" onClick="select_all(this.form.name, 'passRecordIdSelect', this.name)">
              </th>
              <th>報價單號碼/票券批號</th>
              <th>交易類型</th>
              <th>放行時間</th>
              <th>最近一次傳送時間</th>
              <th>傳送狀態</th>
              <th>傳送次數</th>
              <th>票券商金資代碼</th>
              <th>發票人統編</th>
              <th>重送原因</th>
            </tr>
            <c:set var="index" value="1" scope="page" />
            <c:forEach var="passRecord" items="${passRecordList.content}" varStatus="status">
              <c:set var="styleClass" value=""/>
              <c:if test="${(index % 2) == 0}">
                <c:set var="styleClass" value="td-lighty"/>
              </c:if>
              <tr class="<c:out value="${styleClass}" />">
                <td>
                  <input name="passRecordIdSelect" type="checkbox" onClick="select_one(this.form.name, this.name, 'selectAll')" value="<c:out value="${passRecord.recordId}" />">
                </td>
                <td><c:out value="${passRecord.transactionNo}" /></td>
                <td><tags:codeMap type="PASS_RECORD_TXID" code="${passRecord.txId.trim()}" /></td>
                <td><fmt:formatDate value="${passRecord.transactionDate}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${passRecord.passTime}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                <td><tags:codeMap type="PASS_RECORD_STATUS" code="${passRecord.status.trim()}"/></td>
                <td><c:out value="${passRecord.reSendTimes}"/></td>
                <td><c:out value="${passRecord.branchId}"/></td>
                <td><c:out value="${passRecord.issuerId}"/></td>
                <td>
                	<c:if test="${!empty passRecord.reSendReason}">
                		<a href="#" onClick="showReSendReason('<c:out value="${passRecord.reSendReason}" />');">
                  		<img src="<c:url value="/resources/images/order.gif"/>" border="0"  alt="重送原因" title="重送原因"></a>
                 	</c:if>
                </td>
              </tr>
              <c:set var="index" value="${index+1}" />
            </c:forEach>
          </table>
          
        </form:form>
      </c:if>
      <c:if test="${passRecordList.totalElements == 0}">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table">
          <tr align="center">
            <td><font color="#FF0000">無 任 何  資 料 內 容 !!!</font>
    </td>
          </tr>
        </table>
      </c:if>
</td>
  </tr>
</table>
			<!-- 分頁  -->
			<c:if test="${passRecordList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${passRecordList}" paginationSize="5" />
					</tr>
				</table>
			</c:if>
			<!-- 分頁結束 -->
<c:if test="${passRecordList.totalElements > 0}">
  <p class="pbtn">
    <input name="resend" id="resend" type="button" class="button-brown" value="傳送" >
    &nbsp;<input name="cancel" type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁" />&nbsp;
  </p>
</c:if>

</body>
</html>
