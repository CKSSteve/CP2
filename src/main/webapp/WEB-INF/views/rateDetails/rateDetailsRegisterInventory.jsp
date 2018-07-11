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

	function rateDetailsRegister(varQuationNo,varItemNo) {
		$("#quationNo").val(varQuationNo);
		$("#itemNo").val(varItemNo);
    	$("#queryForm").attr("action", '<c:url value="/rateDetails/rateDetailsRegister"/>');
    	$("#queryForm").submit();
	}

</script>
</head>
<body id="scroller">

	<h1><span class="title-l">本票帳務明細待登錄清冊</span></h1>
	<form:form id="queryForm" name="queryForm" method="POST">
	<input id="toPage" type="hidden" name="page">
	<input id="quationNo" type="hidden" name="quationNo">
	<input id="itemNo" type="hidden" name="itemNo">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td>
	    <div id="htmlBody">
	    <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" id="table01">
	      <tr class="cell-title">
	      	<th nowrap="nowrap">編號</th>
	      	<th nowrap="nowrap">票券批號</th>
	      	<th nowrap="nowrap">發行人</th>
	        <th nowrap="nowrap">起始日期</th>
	        <th nowrap="nowrap">到期日期</th>
	        <th nowrap="nowrap">類別</th>
	        <th nowrap="nowrap">天期</th>
	        <th nowrap="nowrap">幣別</th>
	        <th nowrap="nowrap">得標金額</th>
	        <th nowrap="nowrap">ALL IN(%)</th>	        
		  </tr>
      <c:if test="${awardDataList.totalElements > 0}">	     
	      <c:forEach items="${awardDataList.content }" var="item" varStatus="idx" >
		      <tr class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
		      <td align="center" nowrap="nowrap"><c:out value="${idx.index+1}"/></td>
		        <td align="center" nowrap="nowrap"><a href="#" onclick="rateDetailsRegister('<c:out value="${item.id.quationNo}"/>','<c:out value="${item.id.itemNo}"/>')"><c:out value="${item.batchNo}"/></a></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.issuerId}"/><c:out value="${item.issuerName}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.effectDate}"/>&nbsp;</td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.expiredDate}"/></td>
		        <td align="center" nowrap="nowrap">		        <tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${item.loanType.trim()}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.days}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.currencyName}"/></td> 
		        <td align="center" nowrap="nowrap"><c:out value="${item.awardAmount}"/>&nbsp;<c:out value="${item.amountUnit}"/></td>
		    	<td align="center" nowrap="nowrap"><c:out value="${item.quationRate}"/>&nbsp;%</td>
		    	
		      </tr>
		  </c:forEach>
       </c:if>
		<c:if test="${awardDataList.totalElements == 0}">
				     <tr>
					     <td colspan="10">
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
			<c:if test="${awardDataList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${awardDataList}" paginationSize="5" />
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
