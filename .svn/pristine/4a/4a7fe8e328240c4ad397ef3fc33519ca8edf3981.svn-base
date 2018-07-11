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

function back(){
	$("#backForm").attr("action", '<c:url value="${method}"/>');
	$("#backForm").submit();
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


</script>
</head>

<body id="scroller">
<form:form id="backForm" name="backForm" commandName="searchBean" method="POST">
	<form:hidden path="issuerId"/>
	<form:hidden path="inviteDateStartDate"/>
	<form:hidden path="inviteDateEndDate"/>
	<form:hidden path="closeDateTimeStartDate"/>
	<form:hidden path="closeDateTimeEndDate"/>
	<form:hidden path="currencyId"/>
	<form:hidden path="quotesDateStartDate"/>
	<form:hidden path="quotesDateEndDate"/>
	<form:hidden path="statusTypeArray"/>
	<form:hidden path="approveStatusTypeArray"/>
</form:form>

<form:form target="_blank" method="post" name="printForm">
    <input type="hidden" name="htmlBody" value="" />
    <input type="hidden" name="type" value="" />
    <input type="hidden" name="printFmt" value="width" />
</form:form>
<div id="htmlBody">
<h1><span class="title-l">標單明細</span></h1>
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="8%">標單號碼</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.tenderNo}" /></div></td>
          <th width="8%">幣別</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.currencyName}" /></div></td>
          <th width="8%">金額單位</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.amountUnit}" /></div></td>
        </tr>
        <tr>
          <th width="8%">起迄期間(起)</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.effectDate}" />&nbsp;</div></td>
          <th width="8%">起迄期間(迄)</th>
          <td colspan="3">
	          	<c:forEach var="ctbflbt2" items="${tenderDetail.ctbflbt2}">
				<div style="text-align:left;"><c:out value="${ctbflbt2.expiredDate}"/>&nbsp;籌資金額<c:out value="${ctbflbt2.loanAmount}"/>&nbsp;天數:<c:out value="${ctbflbt2.days}"/></br></div>
				</c:forEach>

          </td>
        </tr>
        <tr>
          <th width="8%">邀標日期</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.inviteDate}" />&nbsp;</div></td>
          <th width="8%">截標日期</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.closeDate}" />&nbsp;</div></td>
          <th width="8%">截標時間</th>
          <td width="12%"><div align="left"><c:out value="${fn:substring(tenderDetail.closeTime, 0, 8)}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th width="8%">聯絡人</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.contactUser}" />&nbsp;</div></td>
          <th width="8%">聯絡電話</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.contactPhone}" />&nbsp;</div></td>
          <th width="8%">傳真</th>
          <td width="12%"><div align="left"><c:out value="${tenderDetail.contactFax}" />&nbsp;</div></td>
        </tr>      
      </table>
      </td>
    </tr>
  </table>
 </div> 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="back" type="button" class="button-brown" onClick="back();" value="回上頁">
  		<input name="println" type="button" class="button-brown" onClick="printPdf();" value="列印">
      </td>
    </tr>
  </table>
</body>
</html>
