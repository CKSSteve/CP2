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

var inputquotionMoneyArrays = new Array();
var inputAllInArrays = new Array();
var inputLoanTypeArrays = new Array();
var inputDaysArrays = new Array();

function doReturn(){

	var selectValue= $('#reasonForReturn :selected').val();
	if(selectValue=='else'){
		if($("#reasonForReturnInput").val().length<1){
			alert("請輸入退回原因")
			return;
		}
	}
	 if(confirm('是否退回?')){
			 var selectText=$('#reasonForReturn :selected').text();
			 $("#reasonForReturnString").val(selectText);
			$("#quotionReviewForm").attr("action", '<c:url value="/quotion/doReturn"/>');
			$("#quotionReviewForm").submit();
	}
}

function doReview(){
	if(confirm('是否確認審核報價?')){
		$("#quotionReviewForm").attr("action", '<c:url value="/quotion/doReview"/>');
		$("#quotionReviewForm").submit();
	}
}

</script>
</head>

<body id="scroller">

 <form:form id="quotionReviewForm" name="quotionReviewForm">
 <input type="hidden" id="tenderNo" name="tenderNo" value="${quotionReview.tenderNo}"/>
 <input type="hidden" id="quationNo" name="quationNo" value="${quotionReview.quationNo}"/>
 <input type="hidden" id="reasonForReturnString" name="reasonForReturnString"/>
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 	<tr><td><h1><span class="title-l">報價審核</span></h1></td></tr>
    <tr>
      <td>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="10%">標單號碼</th>
          <td width="20%"><div align="left"><c:out value="${quotionReview.tenderNo}" /></div></td>
          <th width="10%">報價單號碼</th>
          <td width="20%"><div align="left"><c:out value="${quotionReview.quationNo}" /></div></td>
          <th width="10%">幣別</th>
          <td width="20%"><div align="left"><c:out value="${quotionReview.currencyName}" /></div></td>
        </tr>
        <tr>
          <th>起迄期間(起)</th>
          <td><div align="left"><c:out value="${quotionReview.effectDate}" />&nbsp;</div></td>
          <th>起迄期間(迄)</th>
          <td>
	          	<c:forEach var="ctbflbt2" items="${quotionReview.ctbflbt1.ctbflbt2}">
				<div style="text-align:left;"><c:out value="${ctbflbt2.expiredDate}"/>&nbsp;籌資金額<c:out value="${ctbflbt2.loanAmount}"/>&nbsp;天數:<c:out value="${ctbflbt2.days}"/></br></div>
				</c:forEach>
          </td>
          <th width="10%">金額單位</th>
          <td width="20%"><div align="left"><c:out value="${quotionReview.amountUnit}" /></div></td>
        </tr>
        <tr>
          <th>邀標日期</th>
          <td><div align="left"><c:out value="${quotionReview.ctbflbt1.inviteDate}" />&nbsp;</div></td>
          <th>截標日期</th>
          <td><div align="left"><c:out value="${quotionReview.ctbflbt1.closeDate}" />&nbsp;</div></td>
          <th>截標時間</th>
          <td><div align="left"><c:out value="${fn:substring(quotionReview.ctbflbt1.closeTime, 0, 8)}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>聯絡人</th>
          <td><div align="left"><c:out value="${quotionReview.contactUser}" />&nbsp;</div></td>
          <th>聯絡電話</th>
          <td><div align="left"><c:out value="${quotionReview.contactPhone}" />&nbsp;</div></td>
          <th>傳真</th>
          <td><div align="left"><c:out value="${quotionReview.contactFax}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>本次可承作總金額</th>
          <td><div align="left"><c:out value="${quotionReview.maxAmount}" />&nbsp;</div></td>
          <th>尚可承作總金額</th>
          <td><div align="left"><c:out value="${quotionReview.extraAmount}" />&nbsp;<tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${quotionReview.extraType}"/></div>
          </td>
          <th>30天all in rate </th>
          <td><div align="left"><c:out value="${quotionReview.extraRate}" />&nbsp;</div></td>
        </tr>             
      </table>
      </td>
    </tr>
<tr>
<td>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table02">
    	<c:if test="${quotionReview.status !='090'}">
    		<tr>
				<th width="20%">項次</th>
				<th width="20%">報價金額(<c:out value="${quotionReview.amountUnit}"/>)</th>
				<th width="20%">all in(%)</th>
				<th width="20%">類別</th>
				<th width="20%">天期</th>
			</tr>
    		<c:forEach items="${ctbflbq2List}" var="item" varStatus="i">
    		<tr class="<c:out value="${(i.index%2==0) ? '' : 'td-lighty'}" />">
    			<td><div align="center"><c:out value="${item.id.itemNo}" /></div></td>
    			<td><div align="center"><c:out value="${item.quationAmount}" /></div></td>
    			<td><div align="center"><c:out value="${item.quationRate}" /></div></td>
    			<td><div align="center"><tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${item.loanType.trim()}"/></div></td>
    			<td><div align="center"><c:out value="${item.days}" /></div></td>
    		</tr>
    		</c:forEach>
    	</c:if>
		<c:if test="${quotionReview.status =='090'}">
		<tr><td><div align="center"><a style="color:red;font-size: 20px">不報價</a></div></td></tr>
		</c:if>
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
							<c:forEach var="rejectReason" items="${reasonForReturnList}">
								<option value="${rejectReason.id.codeKey}"><c:out value="${rejectReason.codeValue}" /></option>
							</c:forEach>
						</select>
					<td>
				</tr>
		    </table>
	    </td>
    </tr>
  </table>

  <table  width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="cancel"  type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/quotion/quotionReviewInventory'" value="回上頁" />&nbsp;
  		<input name="preview" type="button" class="button-brown" onClick="doReturn();" value="退回">
  		<input name="println" type="button" class="button-brown" onClick="doReview();" value="確認">
  		
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
