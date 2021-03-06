<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<script type="text/javascript" language="javascript">

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

</script>
</head>
<body>
<form:form target="_blank" method="post" name="printForm">
    <input type="hidden" name="htmlBody" value="" />
    <input type="hidden" name="type" value="" />
    <input type="hidden" name="printFmt" value="width" />
</form:form>

<div id="htmlBody">
  <h1>${ctbflbq1.issuerName}短期籌資競價結果通知</h1>
<c:if test="${winningTheMark}">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/top_left.gif" width="8" height="8" /></td>
    <td style="background:url(<%=request.getContextPath()%>/resources/images/table/top_bg.gif) repeat-x"><img src="<%=request.getContextPath()%>/resources/images/spacer.gif" width="1" height="1" /></td>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/top_right.gif" width="8" height="8" /></td>
  </tr>
    <tr>
    <td width="8" style="background:url(<%=request.getContextPath()%>/resources/images/table/left_bg.gif) repeat-y">&nbsp;</td>
    <td align="center">
    	<table width="98%" border="0" align="left" cellpadding="0" cellspacing="0" id="table02">
      <tr>
          <th width="110">得標機構</th>
          <td>
          	 <div align="left"><c:out value="${branchName}"/></div>
          </td>
      </tr>
      <tr>
        <th width="110">競價日期</th>
        <td>
        	<div align="left"><c:out value="${ctbflbq1.quationTime}"/></div>
        </td>
      </tr>
	  <tr>
        <td align="right" nowrap="nowrap"><font color="red"><b></b></font>發行期間：</td>
        <td>
        	<div align="left"><c:out value="${ctbflbq1.effectDate}"/>&nbsp;~&nbsp;<c:out value="${ctbflbq2.expiredDate}"/></div>
		</td>
	 </tr>
     <tr>
        <td align="right" nowrap="nowrap"><font color="red"><b></b></font>融資天期：</td>
        <td>
        	<div align="left"><c:out value="${ctbflbq2.days}"/>&nbsp;天&nbsp;</div>
		</td>
	 </tr> 
	 <tr>
        <td align="right" nowrap="nowrap"><font color="red"><b></b></font>金額取得：</td>
        <td>
        	<div align="left"><c:out value="${ctbflbq2.loanAmount}"/><c:out value="${ctbflbq1.amountUnit}"/>&nbsp;元&nbsp;</div>
		</td>
	 </tr>
	 <tr>
        <td align="right" nowrap="nowrap"><font color="red"><b></b></font>得標利率：</td>
        <td>
        	<div align="left"><tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${ctbflbq2.loanType.trim()}"/>&nbsp;<c:out value="${ctbflbq2.quationRate}"/>%</div>
		</td>
	 </tr>
	 <tr>
        <td align="right" nowrap="nowrap"><font color="red"><b></b></font>得標金額：</td>
        <td>
        	<div align="left"><c:out value="${ctbflbq2.awardAmount}"/><c:out value="${ctbflbq1.amountUnit}"/>&nbsp;元&nbsp;</div>
		</td>
	 </tr>
  </table>
  </td>
    <td width="8" style="background:url(<%=request.getContextPath()%>/resources/images/table/right_bg.gif) repeat-y">&nbsp;</td>
  </tr>
  <tr>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/bottom_left.gif" width="8" height="8" /></td>
    <td style="background:url(<%=request.getContextPath()%>/resources/images/table/bottom_bg.gif) repeat-x"><img src="<%=request.getContextPath()%>/resources/images/spacer.gif" width="1" height="1" /></td>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/bottom_right.gif" width="8" height="8" /></td>
  </tr>
</table>
</c:if>
<c:if test="${!winningTheMark}">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/top_left.gif" width="8" height="8" /></td>
    <td style="background:url(<%=request.getContextPath()%>/resources/images/table/top_bg.gif) repeat-x"><img src="<%=request.getContextPath()%>/resources/images/spacer.gif" width="1" height="1" /></td>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/top_right.gif" width="8" height="8" /></td>
  </tr>
    <tr>
    <td width="8" style="background:url(<%=request.getContextPath()%>/resources/images/table/left_bg.gif) repeat-y">&nbsp;</td>
    <td align="center">
    	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table02">
      <tr>
        <th width="110">競價日期：</th>
        <td>
        	<div align="left"><c:out value="${ctbflbq1.quationTime}"/></div>
        </td>
      </tr>
	  <tr>
        <th width="110">發行期間</th>
        <td>
        	<div align="left"><c:out value="${ctbflbq1.effectDate}"/>&nbsp;~&nbsp;<c:out value="${ctbflbq2.expiredDate}"/></div>
		</td>
	 </tr>
	 	  <tr>
        <th width="110">最高利率</th>
        <td>
        	<div align="left"><c:out value="${ctbflbq1.awardRate}"/>&nbsp;%</div>
		</td>
	 </tr>
  </table>
  </td>
    <td width="8" style="background:url(<%=request.getContextPath()%>/resources/images/table/right_bg.gif) repeat-y">&nbsp;</td>
  </tr>
  <tr>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/bottom_left.gif" width="8" height="8" /></td>
    <td style="background:url(<%=request.getContextPath()%>/resources/images/table/bottom_bg.gif) repeat-x"><img src="<%=request.getContextPath()%>/resources/images/spacer.gif" width="1" height="1" /></td>
    <td width="8"><img src="<%=request.getContextPath()%>/resources/images/table/bottom_right.gif" width="8" height="8" /></td>
  </tr>
</table>
</c:if>
<table>
 <c:choose>
	<c:when test="${ctbflbq1.issuerId==30414175}">
		<h1>中國鋼鐵股份有限公司財務處	&nbsp;&nbsp;&nbsp;<c:out value="${ctbflbq1.awardDate}"/></h1>	
	</c:when>
	<c:otherwise>
			<h1><c:out value="${ctbflbq1.issuerName}"/>&nbsp;&nbsp;&nbsp;<c:out value="${ctbflbq1.awardDate}"/></h1>			
	</c:otherwise>
 </c:choose>
</table>
</div>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="cancel" type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁" />&nbsp;
  		<input name="println" type="button" class="button-brown" onClick="printPdf();" value="列印">
      </td>
    </tr>
  </table>
</body>
</html>