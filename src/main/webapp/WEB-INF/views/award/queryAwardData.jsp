<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
</head>
<body id="scroller">
	<h1><span class="title-l">得標待確認清冊</span></h1>
	<form:form id="queryForm" name="queryForm" method="POST" commandName="searchBean">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td>
	    <div id="htmlBody">
	    <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" id="table01">
	      <tr class="cell-title">
	      	<th nowrap="nowrap">編號</th>
	      	<th nowrap="nowrap">報價單號碼</th>
	      	<th nowrap="nowrap">項次</th>
	        <th nowrap="nowrap">發行人</th>
	        <th nowrap="nowrap">決標日期</th>
	        <th nowrap="nowrap">起始日期</th>
	        <th nowrap="nowrap">到期日期</th>
	        <th nowrap="nowrap">天期</th>
	        <th nowrap="nowrap">幣別</th>
	        <th nowrap="nowrap">報價金額</th>
	        <th nowrap="nowrap">得標金額</th>
	        <th nowrap="nowrap">ALL IN(%)</th>
	      </tr>
      <c:if test="${resultList.totalElements > 0}">	     
	      <c:forEach items="${resultList.content }" var="item" varStatus="idx" >
		      <tr class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
		      	<td align="center" nowrap="nowrap"><c:out value="${idx.index+1}"/></td>
		        <td align="center" nowrap="nowrap"><a href="#" onClick="detail('${item.id.quationNo}','${item.id.itemNo}');"><c:out value="${item.id.quationNo}"/></a></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.id.itemNo}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.issuerId}"/><c:out value="${item.issuerName}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.awardDate}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.effectDate}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.expiredDate}"/></td> 
		        <td align="center" nowrap="nowrap"><c:out value="${item.days}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.currencyName}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.quationAmount}"/><c:out value="${item.amountUnit}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.awardAmount}"/><c:out value="${item.amountUnit}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.quationRate}"/>%</td>
		      </tr>
		  </c:forEach>
       </c:if>
		<c:if test="${resultList.totalElements == 0}">
		     <tr>
			     <td colspan="12">
			           <font size="3" color="red">無任何得標待確認資料</font>
			      </td>
			 </tr>
		</c:if>       		  
	    </table>
	    </div>
	    </td>
	  </tr>
	</table>
	<input id="toPage" type="hidden" name="page">
	</form:form>
			<!-- 分頁  -->
			<c:if test="${resultList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${resultList}" paginationSize="5" />
					</tr>
				</table>
			</c:if>
			<!-- 分頁結束 -->
 <table width="88%" border="0" align="left" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="cancel" type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁">
      </td>
    </tr>
  </table>
  <form:form id="showDetailAwardDataForm" name="showDetailAwardDataForm" action="awardDataRegister/doDetail">
	<input id="quationNo" name="quationNo" type="hidden">
	<input id="itemNo" name="itemNo" type="hidden">
  </form:form>

<script type="text/javascript">

	function detail(quationNo, itemNo) {		
		$("#quationNo").val(quationNo);
		$("#itemNo").val(itemNo);
		$("#showDetailAwardDataForm").submit();
	}
	
</script>		
</body>
</html>
