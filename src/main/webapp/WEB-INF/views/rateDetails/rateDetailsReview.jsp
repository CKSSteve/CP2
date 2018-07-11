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


function doReturn(){

	var selectValue= $('#reasonForReturn :selected').val();

	 if(confirm('是否確認退回本票帳務明細？')){

			 var selectText=$('#reasonForReturn :selected').text();
			 $("#reasonForReturnString").val(selectText);
			$("#rateDetailsRegisterForm").attr("action", '<c:url value="/rateDetails/doReturn"/>');
			$("#rateDetailsRegisterForm").submit();
	}
}

function doReview(){
	if(confirm('是否確認審核本票帳務明細?')){
		$("#rateDetailsRegisterForm").attr("action", '<c:url value="/rateDetails/doReview"/>');
		$("#rateDetailsRegisterForm").submit();
	}
}

</script>
</head>

<body id="scroller">

<div id="rateDetailsRegisterDiv">
 <form:form id="rateDetailsRegisterForm" name="rateDetailsRegisterForm">
 <input type="hidden" name="quationNo" value="${awardData.id.quationNo}"/>
 <input type="hidden" name="itemNo" value="${awardData.id.itemNo}"/>
 <input type="hidden" name="batchNo" value="${awardData.batchNo}"/>
 <input type="hidden" name="reasonForReturnString" id="reasonForReturnString"/>
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 	<tr><td><h1><span class="title-l">本票帳務明細審核</span></h1></td></tr>
    <tr>
      <td>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="10%">票券批號</th>
          <td width="20%"><div align="left"><c:out value="${awardData.batchNo}" /></div></td>
          <th width="10%">標單號碼</th>
          <td width="20%"><div align="left"><c:out value="${awardData.tenderNo}" /></div></td>
          <th width="10%">報價單號碼</th>
   		  <td width="20%"><div align="left"><c:out value="${awardData.id.quationNo}" /></div></td>
        </tr>
        <tr>      
          <th>發行人</th>
          <td><div align="left"><c:out value="${awardData.issuerId}" />&nbsp;<c:out value="${awardData.issuerName}" /></div></td>
          <th>發行日期</th>
          <td><div align="left"><c:out value="${awardData.effectDate}" />&nbsp;</div></td>
          <th>到期日期</th>
          <td><div align="left"><c:out value="${awardData.expiredDate}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>決標日期</th>
          <td><div align="left"><c:out value="${awardData.awardDate}" /></div></td>
		  <th>籌資金額</th>
          <td><div align="left"><c:out value="${awardData.loanAmount}" />&nbsp;<c:out value="${awardData.amountUnit}" /></div></td>
          <th>幣別</th>
          <td><div align="left"><c:out value="${awardData.currencyName}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>報價金額</th>
          <td><div align="left"><c:out value="${awardData.quationAmount}" />&nbsp;<c:out value="${awardData.amountUnit}" /></div></td>
          <th>天數</th>
          <td><div align="left"><c:out value="${awardData.days}" />&nbsp;</div></td>
          <th>類別</th>
          <td><div align="left"><tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${awardData.loanType.trim()}"/>&nbsp;</div></td>
        </tr>          
        <tr>
          <th>得標金額</th>
          <td><div align="left"><c:out value="${awardData.awardAmount}" />&nbsp;<c:out value="${awardData.amountUnit}" /></div></td>
          <th>All In(%)</th>
          <td><div align="left"><c:out value="${awardData.quationRate}" />&nbsp;%</div></td>
          <th>交割服務費率 </th>
          <td><div align="left"><c:out value="${awardData.deliveryRate}" />&nbsp;%</div></td>
        </tr>
        <tr>
          <th>承銷手續費率 </th>
          <td><div align="left"><c:out value="${awardData.underwritingRate}" />&nbsp;%</div></td>
          <th>簽證費率 </th>
          <td><div align="left"><c:out value="${awardData.visaRate}" />&nbsp;%</div></td>
          <th>保證費率 </th>
          <td><div align="left"><c:out value="${awardData.guaranteeRate}" />&nbsp;%</div></td>
        </tr>         
      </table>
      </td>
    </tr>
    <tr><td colspan="4"><br></td></tr>
    <tr>
	  <td>
	    <table  width="98%" border="0" align="center" cellpadding="0" cellspacing="0" >
	    <tr>
	    	<td colspan="4">
	    	退回原因 :
	    		<select  name="reasonForReturn" id="reasonForReturn">
					<c:forEach var="RejectReason" items="${reasonForReturnList}">
						<option value="${RejectReason.id.codeKey}"><c:out value="${RejectReason.codeValue}" />
					</c:forEach>
				</select>
			<td>
		</tr>
	    </table>
	  </td>
    </tr>   
  </table>
  
</form:form>

  <table  width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
		<input name="cancel"  type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/rateDetails/rateDetailsReviewInventory'" value="回上頁" />&nbsp;
  		<input name="doReturn" type="button" class="button-brown" onClick="doReturn();" value="退回">
  		<input name="doReview" type="button" class="button-brown" onClick="doReview();" value="確認">		
      </td>
    </tr>
  </table>
</div>

</body>
</html>
