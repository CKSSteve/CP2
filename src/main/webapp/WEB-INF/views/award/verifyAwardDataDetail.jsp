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
	<form:form id="queryForm" name="queryForm" method="POST" commandName="searchBean">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	  	<td><h1><span class="title-l">票券批號(得標確認)審核</span></h1></td>
	  </tr>
	  <tr>
	    <td>
	      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
	    	<tr>
	    		<th width="10%">標單號碼</th>
                <td width="20%"><div align="left"><c:out value="${awardData.tenderNo}"/></div></td>
                <th width="10%">報價單號碼</th>
                <td width="20%"><div align="left"><c:out value="${awardData.id.quationNo}"/></div></td>
                <th width="10%">決標日期</th>
                <td width="20%"><div align="left"><c:out value="${awardData.awardDate}"/></div></td>
	    	</tr>
	    	<tr>
	    	    <th>發行人</th>
                <td><div align="left"><c:out value="${awardData.issuerId}"/><c:out value="${awardData.issuerName}"/></td>
	    		<th>發行日</th>
                <td><div align="left"><c:out value="${awardData.effectDate}"/></div></td>
                <th>到期日</th>
                <td><div align="left"><c:out value="${awardData.expiredDate}"/></div></td>
	    	</tr>
	    	<tr>
	    	    <th>票券類別</th>
                <td><div align="left">
                <c:choose>
                	<c:when test="${awardData.loanType.trim() =='A'}">CP2融資性商業本票</c:when>
                	<c:otherwise>短貸</c:otherwise>
                </c:choose>
                </div></td>
                <th>籌資金額</th>
                <td><div align="left"><c:out value="${awardData.loanAmount}"/><c:out value="${awardData.amountUnit}"/></div></td>
	    		<th>幣別</th>
                <td><div align="left"><c:out value="${awardData.currencyName}"/></div></td>
	    	</tr>
	    	<tr>
	    	    <th>報價金額</th>
                <td><div align="left"><c:out value="${awardData.quationAmount}"/></div></td>
                <th>得標金額</th>
                <td><div align="left"><c:out value="${awardData.awardAmount}"/></div></td>
	    		<th>天數</th>
                <td><div align="left"><c:out value="${awardData.days}"/></div></td>
	    	</tr>
	    	<tr>
	    	    <th>all in(%)</th>
                <td><div align="left"><c:out value="${awardData.quationRate}"/></div></td>
                <th>票券批號</th>
                <td colspan = "3"><div align="left"><c:out value="${awardData.batchNo}"/></div>
                </td>
	    	</tr>
	    	<c:if test="${awardData.status == '290'}">
	    		<tr>
	    			<td colspan = "6"><font color="red"  size="5%">得標放棄</font></td>
	    		</tr>
	    	</c:if>
	    </table>
	  </td>
	</tr>
  </table>
<br/>
	<table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0" >    
		<tr>   
			<td colspan = "4">退回原因：
				<select id="rejectReasonSelect" name="rejectReasonSelect" >
		    	 	<option value="">請選擇退回原因</option>
		    	 	<c:forEach items="${rejectReasonList}" var="item">
		    	 		<option value="${item.codeValue}"><c:out value="${item.codeValue}" /></option>
		    	 	</c:forEach>
		    	 </select>
			</td>
		 </tr>
	</table>
   </form:form>
   <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
		<input id="doBack" type="button" class="button-brown" value="回上頁" />&nbsp;
		<input id="doReturn" type="button" class="button-brown" value="退回" />&nbsp;
  		<input id="doConfirm" type="button" class="button-brown" value="確認" />&nbsp;
	  </td>
    </tr>
  </table>
  <form:form id="returnForm">
    	<input id="quationNo" type="hidden" name="quationNo" value="<c:out value="${awardData.id.quationNo}"/>"/>
    	<input id="itemNo" type="hidden" name="itemNo" value="<c:out value="${awardData.id.itemNo}"/>"/>
    	<input id="hiddenRejectReason1" type="hidden" name="rejectReason1" />
  </form:form>
<script type="text/javascript">
$(function() {	
	$("#doBack").click(function(){
		$("#queryForm").attr("action", '<c:url value="/awardDataVerify"/>');
		$("#queryForm").submit();
	});
	
	$("#doConfirm").click(function() {
		if(confirm("是否確認審核票券批號登錄(得標確認)?")){
			$("#returnForm").attr("action",'<c:url value="/awardDataVerify/doConfirm"/>');
			$("#returnForm").submit();
		}	
	});
	
	$("#doReturn").click(function() {
		var rejectReasonSelect =  $("select[name='rejectReasonSelect'] option:selected").text();
		var rejectReason = $("#rejectReason1").val();
		
		if(rejectReasonSelect == '請選擇退回原因'){
			alert("請選擇退回原因！");
			return false;
		}
		
		if(confirm("是否確認退回票券批號登錄(得標確認)?")){
			$("#hiddenRejectReason1").val(rejectReasonSelect);
			$("#returnForm").attr("action",'<c:url value="/awardDataVerify/doReturn"/>');
			$("#returnForm").submit();
		}	
	});
	
});
</script>		
</body>
</html>
