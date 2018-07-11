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

	function quotionEntry(varTenderNo) {
		$("#tenderNo").val(varTenderNo);
    	$("#queryForm").attr("action", '<c:url value="/quotion/quotionReview"/>');
    	$("#queryForm").submit();
	}

</script>
</head>
<body id="scroller">

	<h1>報價待審核清冊</h1>
	<form:form id="queryForm" name="queryForm" method="POST">
	<input id="toPage" type="hidden" name="page">
	<input id="tenderNo" type="hidden" name="tenderNo">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td>
	    <div id="htmlBody">
	    <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" id="table01">
	      <tr class="cell-title">
	      	<th nowrap="nowrap">編號</th>
	      	<th nowrap="nowrap">標單號碼</th>
	      	<th nowrap="nowrap">報價單號碼</th>
	      	<th nowrap="nowrap">發行人</th>
	        <th nowrap="nowrap">邀標日期</th>
	        <th nowrap="nowrap">截標日期</th>
	        <th nowrap="nowrap">截標時間</th>
	        <th nowrap="nowrap">幣別</th>
	        <th nowrap="nowrap">籌資總金額</th>	        
		  </tr>
      <c:if test="${quotionReviewList.totalElements > 0}">	     
	      <c:forEach items="${quotionReviewList.content }" var="item" varStatus="idx" >
		      <tr class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
		      <td align="center" nowrap="nowrap"><c:out value="${idx.index+1}"/></td>
		        <td align="center" nowrap="nowrap"><a href="#" onclick="quotionEntry('<c:out value="${item.tenderNo}"/>')"><c:out value="${item.tenderNo}"/></a></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.quationNo}"/>&nbsp;</td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.issuerId}"/><c:out value="${item.issuerName}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.ctbflbt1.inviteDate}"/>&nbsp;</td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.ctbflbt1.closeDate}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${fn:substring(item.ctbflbt1.closeTime, 0, 8)}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.currencyName}"/></td> 
		        <td align="center" nowrap="nowrap"><c:out value="${item.loanAmount}"/>&nbsp;<c:out value="${item.amountUnit}"/></td>
		    
		      </tr>
		  </c:forEach>
       </c:if>
		<c:if test="${quotionReviewList.totalElements == 0}">
				     <tr>
					     <td colspan="9">
					           <font size="3" color="red">查無資料!</font>
					      </td>
					 </tr>
		</c:if>       		  
	    </table>
	    </div>
	    </td>
	  </tr>
	</table>
	</form:form>
			<!-- 分頁  -->
			<c:if test="${quotionReviewList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${quotionReviewList}" paginationSize="5" />
					</tr>
				</table>
			</c:if>
			<!-- 分頁結束 -->
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="cancel" type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁" />&nbsp;
      </td>
    </tr>
  </table>
			
</body>
</html>
