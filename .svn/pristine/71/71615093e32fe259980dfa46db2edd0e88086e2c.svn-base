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


function rateDetailsConfirm(){
	
		$("#rateDetailsRegisterForm").attr("action", '<c:url value="/rateDetails/doRegister"/>');
		$("#rateDetailsRegisterForm").submit();

}

function backtoRateDetailsRegister(){
	
	$("#rateDetailsRegisterPreviewDiv").hide();  
	$("#rateDetailsRegisterDiv").show();
}

function preview(){
	
	var errorMsg="";
	deliveryRateValue=$('input[name="deliveryRate"]').val();
	underwritingRateValue=$('input[name="underwritingRate"]').val();
	visaRateValue=$('input[name="visaRate"]').val();
	guaranteeRateValue=$('input[name="guaranteeRate"]').val();
	
	if(deliveryRateValue.length<1){
		errorMsg+="交割服務費率 \r\n";
	}	
	if(underwritingRateValue.length<1){
		errorMsg+="承銷手續費率 \r\n";
	}	
	if(visaRateValue.length<1){
		errorMsg+="簽證費率 \r\n";
	}	
	if(guaranteeRateValue.length<1){
		errorMsg+="保證費率 \r\n";
	}
	
	if(errorMsg){
		alert("請輸入 :\r\n"+errorMsg);
	}else{
		
		$("#deliveryRatePreview").html(deliveryRateValue+"&nbsp;%");
		$("#underwritingRatePreview").html(underwritingRateValue+"&nbsp;%");
		$("#visaRatePreview").html(visaRateValue+"&nbsp;%");
		$("#guaranteeRatePreview").html(guaranteeRateValue+"&nbsp;%");
		
		$("#rateDetailsRegisterDiv").hide();  
		$("#rateDetailsRegisterPreviewDiv").show();

	}



	  
}



$(function (){
	
	
	$("#rateDetailsRegisterPreviewDiv").hide();
   	$('#deliveryRate').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
    });
   	$('#underwritingRate').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
    });
   	$('#visaRate').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
    });
   	$('#guaranteeRate').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
    });
  	
});
</script>
</head>

<body id="scroller">

<div id="rateDetailsRegisterDiv">
 <form:form id="rateDetailsRegisterForm" name="rateDetailsRegisterForm">
 <input type="hidden" name="quationNo" value="${awardData.id.quationNo}"/>
 <input type="hidden" name="itemNo" value="${awardData.id.itemNo}"/>
 <input type="hidden" name="batchNo" value="${awardData.batchNo}"/>
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 	<tr><td><h1><span class="title-l">本票帳務明細登錄</span></h1></td></tr>
    <tr>
      <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
        <tr>
      		<td height="30"><img src="<c:url value="/resources/images/page.gif"/>" border="0" align="middle" alt="">標有<span class="red">＊</span>的欄位為必要資料，請正確填寫。</td>
  		</tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">

        <tr>
          <th width="10%">票券批號</th>
          <td width="20%"><div align="left"><c:out value="${awardData.batchNo}" /></div></td>
          <th width="110">標單號碼</th>
          <td><div align="left"><c:out value="${awardData.tenderNo}" /></div></td>
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
          <td><div align="left"><c:out value="${awardData.loanAmount}" />&nbsp;<c:out value="${awardData.amountUnit}"/></div></td>
          <th>幣別</th>
          <td><div align="left"><c:out value="${awardData.currencyName}" />&nbsp;</div></td>
        </tr>
        <tr>
       	  <th>報價金額</th>
          <td><div align="left"><c:out value="${awardData.quationAmount}" />&nbsp;<c:out value="${awardData.amountUnit}"/></div></td>
          <th>天數</th>
          <td><div align="left"><c:out value="${awardData.days}" />&nbsp;</div></td>
          <th>類別</th>
          <td><div align="left"><tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${awardData.loanType.trim()}"/>&nbsp;</div></td>
        </tr>        
        <tr>
          <th>得標金額</th>
          <td><div align="left"><c:out value="${awardData.awardAmount}" />&nbsp;<c:out value="${awardData.amountUnit}"/></div></td>
          <th>All In(%)</th>
          <td><div align="left"><c:out value="${awardData.quationRate}" />&nbsp;%</div></td>
          <th><a style="color: red">＊</a>&nbsp;交割服務費率 </th>
          <td><div align="left"><input type="text" id="deliveryRate" name="deliveryRate" value="<c:out value="${awardData.deliveryRate}" />">&nbsp;%</div></td>
        </tr>
        <tr>
          <th><a style="color: red">＊</a>&nbsp;承銷手續費率 </th>
          <td><div align="left"><input type="text" id="underwritingRate" name="underwritingRate" value="<c:out value="${awardData.underwritingRate}" />">&nbsp;%</div></td>
          <th><a style="color: red">＊</a>&nbsp;簽證費率 </th>
          <td><div align="left"><input type="text" id="visaRate" name="visaRate" value="<c:out value="${awardData.visaRate}" />">&nbsp;%</div></td>
          <th><a style="color: red">＊</a>&nbsp;保證費率 </th>
          <td><div align="left"><input type="text" id="guaranteeRate" name="guaranteeRate" value="<c:out value="${awardData.guaranteeRate}" />">&nbsp;%</div></td>
        </tr>         
      </table>
      </td>
    </tr>

  </table>
  <br/>
 	<table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0" >    
		<c:if test="${awardData.approveStatus2=='1'}">
	    	<tr><td colspan="2">退回原因 &nbsp;:&nbsp;<a style="color:red">${awardData.rejectReason2}</a></td></tr>
	    </c:if>
	</table>

</form:form>

  <table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="cancel"  type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="取消" />&nbsp;
  		<input name="preview" type="button" class="button-brown" onClick="preview();" value="預覽"> 		
      </td>
    </tr>
  </table>
</div>
<div id="rateDetailsRegisterPreviewDiv">
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 	<tr><td><h1>本票帳務明細登錄<span class="title-l">-預覽</span></h1></td></tr>
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
          <td><div align="left"><c:out value="${awardData.loanAmount}" />&nbsp;<c:out value="${awardData.amountUnit}"/></div></td>
          <th width="110">幣別</th>
          <td><div align="left"><c:out value="${awardData.currencyName}" />&nbsp;</div></td>        
        </tr>
        <tr>
          <th>報價金額</th>
          <td><div align="left"><c:out value="${awardData.quationAmount}" />&nbsp;<c:out value="${awardData.amountUnit}"/></div></td>
          <th>天數</th>
          <td><div align="left"><c:out value="${awardData.days}" />&nbsp;</div></td>
          <th>類別</th>
          <td><div align="left"><tags:codeMap type="CTBFLBQ2_LOAN_TYPE" code="${awardData.loanType.trim()}"/>&nbsp;</div></td>
        </tr> 
        <tr>
          <th>得標金額</th>
          <td><div align="left"><c:out value="${awardData.awardAmount}" />&nbsp;<c:out value="${awardData.amountUnit}"/></div></td>
          <th>All In(%)</th>
          <td><div align="left"><c:out value="${awardData.quationRate}" />&nbsp;%</div></td>
          <th>交割服務費率 </th>
          <td><div align="left" id="deliveryRatePreview"></div></td>
        </tr>
        <tr>
          <th>承銷手續費率 </th>
          <td><div align="left" id="underwritingRatePreview"></div></td>
          <th>簽證費率 </th>
          <td><div align="left" id="visaRatePreview"></div></td>
          <th>保證費率 </th>
          <td><div align="left" id="guaranteeRatePreview"></div></td>
        </tr>  
      </table>
      </td>
      <td width="8" style="background:url(<c:url value="/resources/images/table/right_bg.gif"/>) repeat-y">&nbsp;</td>
    </tr>
    <tr>
      <td width="8"><img src="<c:url value="/resources/images/table/bottom_left.gif"/>" width="8" height="8"></td>
      <td style="background:url(<c:url value="/resources/images/table/bottom_bg.gif"/>) repeat-x"><img src="<c:url value="/resources/images/spacer.gif"/>" width="1" height="1"></td>
      <td width="8"><img src="<c:url value="/resources/images/table/bottom_right.gif"/>" width="8" height="8"></td>
    </tr>
  </table>
  <table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="preview" type="button" class="button-brown" onClick="backtoRateDetailsRegister();" value="回上頁">
  		<input name="println" type="button" class="button-brown" onClick="rateDetailsConfirm();" value="確認">
  		
      </td>
    </tr>
  </table>
</div> 
</body>
</html>
