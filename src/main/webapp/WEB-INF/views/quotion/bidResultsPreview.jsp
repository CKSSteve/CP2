<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<script type="text/javascript" language="javascript">
function print() {
// alert("請滑鼠點右鍵選列印")
	  var divToPrint=document.getElementById('printDiv');

	  var newWin=window.open('','Print-Window');

	  newWin.document.open();

	  newWin.document.write('<html><body onload="window.print()">'+divToPrint.innerHTML+'</body></html>');

	  newWin.document.close();

// 	  setTimeout(function(){newWin.close();},10);
}

function printPdf(bodyId) {
 	$(".nonPrintable").prop("style", "display:none");
    if (bodyId == undefined) {
      bodyId = 'htmlBody';
    }
    document.forms['printForm'].target = '_blank';
    document.forms['printForm']['type'].value = '1';
    callPd4ml(bodyId);
    $(".nonPrintable").prop("style", "");
}

function callPd4ml(bodyId) {
    if (bodyId == undefined) {
      bodyId = 'htmlBody';
    }
    //拿掉會影響列印的<script> XSS attack
    element = document.getElementById("pdfScript");
    if (element != null){
        element.parentNode.removeChild(element);
    }
    //var postHtml = document.getElementById(bodyId).innerHTML;
    var postHtml = '<html><head><meta http-equiv=Content-Type content="text/html; charset=UTF-8"><base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>"><link href="<%=request.getContextPath()%>/resources/css/table-print.css" rel="stylesheet" type="text/css"/></head><body><font face="DFKai-SB">' + document.getElementById(bodyId).innerHTML + '</font></body></html>';
    document.forms['printForm']['htmlBody'].value = postHtml;
    document.forms['printForm'].action = '<c:url value="/common/generatePDF"/>';
    document.forms['printForm'].submit();
}

function back(){
	$("#backForm").attr("action", '<c:url value="/quotionQuery/showDetail"/>');
	$("#backForm").submit();
}
</script>
</head>
<body>
<form:form target="_blank" method="post" name="printForm">
    <input type="hidden" name="htmlBody" value="" />
    <input type="hidden" name="type" value="" />
    <input type="hidden" name="printFmt" value="width" />
</form:form>
<form:form id="backForm" name="backForm" commandName="searchBean" method="POST">
	<form:hidden path="issuerId"/>
	<form:hidden path="currencyId"/>
	<form:hidden path="quotesDateStartDate"/>
	<form:hidden path="quotesDateEndDate"/>
	<form:hidden path="statusTypeArray"/>
	<form:hidden path="approveStatusTypeArray"/>
	<input type="hidden" name="detailQuationNo" value='<c:out value="${ctbflbq1.quationNo}"/>'>
	<input type="hidden" name="method" value="/quotionQuery/doQuery">
</form:form>
<div id="htmlBody">
  
<c:if test="${winningTheMark}">
<table width="75%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
  	<td colspan = "3">
  		<h2>${ctbflbq1.issuerName}短期籌資競價結果通知</h2>
  	</td>
  </tr>
    <tr>
    <td align="center">
    	<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" id="table05">
      <tr>
          <th width="5%">得標機構</th>
          <td colspan="5">
          	 <div align="left"><c:out value="${branchName}"/></div>
          </td>
      </tr>
      <tr>
        <th width="5%">競價日期</th>
        <td width="20%">
        	<div align="left"><fmt:formatDate value="${ctbflbq1.quationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div> 
        </td>
        <th width="5%">發行期間</th>
        <td width="20%">
        	<div align="left"><c:out value="${ctbflbq1.effectDate}"/>&nbsp;~&nbsp;<c:out value="${ctbflbq2.expiredDate}"/></div>
		</td>
        <th width="5%">融資天期</th>
        <td width="20%">
        	<div align="left"><c:out value="${ctbflbq2.days}"/>&nbsp;天&nbsp;</div>
		</td>
	 </tr> 
	 <tr>
        <th width="5%">金額取得</th>
        <td width="20%">
        	<div align="left"><c:out value="${ctbflbq2.loanAmount}"/><c:out value="${ctbflbq1.amountUnit}"/>&nbsp;元&nbsp;</div>
		</td>
        <th width="5%">得標利率</th>
        <td width="20%">
        	<div align="left"><tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${ctbflbq2.loanType.trim()}"/>&nbsp;<c:out value="${ctbflbq2.quationRate}"/>%</div>
		</td>
        <th width="5%">得標金額</th>
        <td width="20%">
        	<div align="left"><c:out value="${ctbflbq2.awardAmount}"/><c:out value="${ctbflbq1.amountUnit}"/>&nbsp;元&nbsp;</div>
		</td>
	 </tr>
  </table>
  </td>
  </tr>
    <tr>
  	<td colspan="6">
		  <c:choose>
			<c:when test="${ctbflbq1.issuerId==30414175}">
				<h3 align="left">中國鋼鐵股份有限公司財務處	&nbsp;&nbsp;&nbsp;<c:out value="${ctbflbq1.awardDate}"/></h3>	
			</c:when>
			<c:otherwise>
					<h3 align="left"><c:out value="${ctbflbq1.issuerName}"/>&nbsp;&nbsp;&nbsp;<c:out value="${ctbflbq1.awardDate}"/></h3>			
			</c:otherwise>
		 </c:choose>
 	</td>
  </tr>
</table>

</c:if>
<c:if test="${!winningTheMark}">
<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td colspan = "3">
  		<h2>${ctbflbq1.issuerName}短期籌資競價結果通知</h2>
  	</td>
  </tr>
    <tr>
    <td align="center">
    	<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" id="table05">
      <tr>
        <th width="5%">競價日期</th>
        <td width="15%">
        	<div align="left"><fmt:formatDate value="${ctbflbq1.quationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div> 
        </td>
      </tr>
	  <tr>
        <th width="5%">發行期間</th>
        <td width="15%">
        	<div align="left"><c:out value="${ctbflbq1.effectDate}"/>&nbsp;~&nbsp;<c:out value="${ctbflbq2.expiredDate}"/></div>
		</td>
	 </tr>
	 	  <tr>
        <th width="5%">最高利率</th>
        <td width="15%">
        	<div align="left"><c:out value="${ctbflbq1.awardRate}"/>&nbsp;%</div>
		</td>
	 </tr>
  </table>
  </td>
  </tr>
  <tr>
  	<td colspan="6">
		  <c:choose>
			<c:when test="${ctbflbq1.issuerId==30414175}">
				<h3>中國鋼鐵股份有限公司財務處	&nbsp;&nbsp;&nbsp;<c:out value="${ctbflbq1.awardDate}"/></h3>	
			</c:when>
			<c:otherwise>
					<h3><c:out value="${ctbflbq1.issuerName}"/>&nbsp;&nbsp;&nbsp;<c:out value="${ctbflbq1.awardDate}"/></h3>			
			</c:otherwise>
		 </c:choose>
 	</td>
  </tr>
</table>
</c:if>
</div>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="back" type="button" class="button-brown" onClick="back();" value="回上頁">&nbsp;
  		<input name="println" type="button" class="button-brown" onClick="printPdf();" value="列印">
      </td>
    </tr>
  </table>
  
</body>
</html>