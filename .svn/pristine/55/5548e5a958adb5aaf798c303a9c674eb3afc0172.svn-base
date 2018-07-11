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

function showPdfDoc(varBatchNo,varDocType){

	$("#detailBatchNo").val(varBatchNo);
	$("#docType").val(varDocType);
	$("#docForm").attr("action", '<c:url value="/batchQuery/showPdfDoc"/>');
	$("#docForm").submit();
	noShowLoading();
}




</script>
</head>

<body id="scroller">
<div id="htmlBody">

<form:form id="backForm" name="backForm" commandName="searchBean" method="POST">
	<form:hidden path="issuerId"/>
	<form:hidden path="effectDateStartDate"/>
	<form:hidden path="effectDateEndDate"/>
	<form:hidden path="expiredDateStartDate"/>
	<form:hidden path="expiredDateEndDate"/>
	<form:hidden path="currencyId"/>
	<form:hidden path="batchNo"/>
</form:form>

<form id="docForm" name="docForm" method="POST" target="_blank">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" id="detailBatchNo" name="detailBatchNo"> 
	<input type="hidden" id="docType" name="docType"> 
	
</form>
	
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 	<tr><td><h1><span class="title-l">本票資料明細</span></h1></td></tr>
    <tr>
      <td>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="10%">票券批號</th>
          <td width="20%"><div align="left"><c:out value="${batchDetail.batchNo}" /></div></td>
		  <th width="10%">發行人</th>
          <td width="20%"><div align="left"><c:out value="${batchDetail.issuerId}"/>&nbsp;<c:out value="${batchDetail.issuerName}"/></div></td>
          <th width="10%">發行編號</th>
          <td width="20%"><div align="left"><c:out value="${batchDetail.issueNo}" /></div></td>
        </tr>        
        <tr>
          <th>票券商名稱</th>
          <td><div align="left"><c:out value="${batchDetail.brokerName}" /></div></td>
          <th>票券商參加單位代號</th>
          <td colspan="3"><div align="left"><c:out value="${batchDetail.brokerId}" /></div></td>      
        </tr> 
        <tr>
          <th>類別</th>
          <td><div align="left">
          			<c:if test="${batchDetail.loanType.trim()=='2'}">
          				<c:choose>
          					<c:when test="${batchDetail.currencyId=='TWD'}">
          						CP2融資性商業本票
          					</c:when>
          					<c:otherwise>
          						外幣商業本票
          					</c:otherwise>
          				</c:choose>
          			</c:if>
          	  </div>
          </td>
          <th>發票總金額</th>
          <td><div align="left"><c:out value="${batchDetail.loanAmount}"/>&nbsp;</div></td>
          <th>幣別</th>
          <td><div align="left"><c:out value="${batchDetail.currencyName}" /></div></td>
        </tr>
        <tr>
          <th>天期</th>
          <td><div align="left"><c:out value="${batchDetail.days}" /></div></td>
          <th>發行日</th>
          <td><div align="left"><c:out value="${batchDetail.effectDate}" /></div></td>
          <th>到期日</th>
          <td><div align="left"><c:out value="${batchDetail.expiredDate}" /></div></td>
        </tr> 
        <tr>
          <th>保證方式</th>
          <td><div align="left"><tags:codeMap type="GUARANTEE_TYPE" code="${batchDetail.guaranteeType}"/></div></td>
          <th>保證人名稱</th>
          <td colspan="3"><div align="left"><c:out value="${batchDetail.guarantorName}" /></div></td>
        </tr> 
        <tr>
          <th>委請書編號</th>
          <td>
          <c:choose>
            <c:when test="${!empty batchDetail.entrustDoc}">
				<div align="left"><a href="#"  onclick="showPdfDoc('<c:url value="${batchDetail.batchNo}"/>','entrustDoc')"><c:out value="${batchDetail.entrustDocId}"/></a></div>
            </c:when>
            <c:otherwise>
				<div align="left"><c:out value="${batchDetail.entrustDocId}" /></div>
			</c:otherwise>
        </c:choose>         
          </td>
          <th>聲明書編號</th>
          <td colspan="3">
            <c:choose>
	            <c:when test="${!empty batchDetail.statementDoc}">
					<div align="left"><a href="#" onclick="showPdfDoc('<c:out value="${batchDetail.batchNo}"/>','statementDoc')"><c:out value="${batchDetail.statementDocId}"/></a></div>
	            </c:when>
	            <c:otherwise>
					<div align="left"><c:out value="${batchDetail.statementDocId}" /></div>
				</c:otherwise>
            </c:choose>
          </td>
        </tr> 
        <tr>
          <th>實際到期兌償日</th>
          <td><div align="left"><c:out value="${batchDetail.payDate}" /></div></td>
          <th>兌償總金額</th>
          <td><div align="left"><c:out value="${batchDetail.patAmount}" /></div></td>
          <th>擔當付款人</th>
          <td><div align="left"><c:out value="${batchDetail.payer}" /></div></td>
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
      </td>
    </tr>
  </table>
</body>
</html>
