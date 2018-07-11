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

function init(){
	
	if('<c:out value="${ctbflbq2List}" />'){
		<c:forEach var="ctbflbq2List" items="${ctbflbq2List}">
			inputquotionMoneyArrays.push('${ctbflbq2List.quationAmount}');
			inputAllInArrays.push('${ctbflbq2List.quationRate}');
			inputLoanTypeArrays.push('${ctbflbq2List.loanType.trim()}');
			inputDaysArrays.push('${ctbflbq2List.days}');
		</c:forEach>
		writeQuationHtml();
	}
	if('<c:out value="${quotionEntry.extraType}" />'=='B'){
		$('#extraType').val('B').prop("selected",true);
	}
	
}

function doNotQuation(){
	 if(confirm('是否確認不報價?')){
			$("#quotionEntryForm").attr("action", '<c:url value="/quotion/doNotQuotion"/>');
			$("#quotionEntryForm").submit();
	}
}

function quotionConfirm(){
		$("#inputquotionMoneyArrays").val(inputquotionMoneyArrays);
		$("#inputAllInArrays").val(inputAllInArrays);
		$("#inputLoanTypeArrays").val(inputLoanTypeArrays);
		$("#inputDaysArrays").val(inputDaysArrays);
		maxAmountValue=$("#maxAmountDiv").html();
		$('input[name="maxAmount"]').val(maxAmountValue);
		$("#quotionEntryForm").attr("action", '<c:url value="/quotion/doQuotion"/>');
		$("#quotionEntryForm").submit();

}

function backtoQuotionEntry(){
	
	$("#quotionEntryPreviewDiv").hide();  
	$("#quotionEntryDiv").show();
}

function preview(){
	
		maxAmountValue=$("#maxAmountDiv").html();
		extraAmountValue=$('input[name="extraAmount"]').val();
		allInRateValue=$('input[name="allInRate"]').val();
		extraTypeValue=$("#extraType :selected").val();
		inputQuotionHtml=$("#inputQuotion").val()
		$("#maxAmountPreview").html(maxAmountValue);

		if(extraTypeValue=='A'){
			extraTypeValue="CP2";
		}else{
			extraTypeValue="短貸";
		}
		if(extraAmountValue.length<=0){
			$('input[name="extraAmount"]').val("0.00");
			extraAmountValue='0.00';
		}
		$("#extraAmountTypePreview").html(extraAmountValue+"&nbsp;"+extraTypeValue);
		
		if(allInRateValue.length<=0){
			$('input[name="allInRate"]').val("0.00");
			allInRateValue='0.00';
		}
		$("#allInRatePreview").html(allInRateValue);
		writeQuationPreviewHtml();
	$("#quotionEntryDiv").hide();  
	$("#quotionEntryPreviewDiv").show();
	  
}

function checkEntry(){
	if(inputquotionMoneyArrays.length<=0){
		alert("請至少輸入一筆資料");
	}else{

		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$.ajax({ 
			traditional: true,
	        url : '<%=request.getContextPath()%>/quotion/ajaxCheck',
	        type : "POST",                                                                    
	        data:"inputquotionMoneyArrays=" + inputquotionMoneyArrays+"&inputDaysArrays="+inputDaysArrays+"&tenderNo="+$("#tenderNo").val(),
	        
	        beforeSend: function(xhr){
	            xhr.setRequestHeader(header, token);
	        },
	        success : function(data) {
					if(data){
						var errorArrays = new Array();
						errorArrays=data;
						var errorMsg="";
						for(i=0;i<errorArrays.length;i++){
							if(i==errorArrays.length-1){
								errorMsg+=errorArrays[i];
							}else{
								errorMsg+=errorArrays[i]+"、";
							}
						}
						alert(errorMsg+"天期之報價金額加總大於標單天期籌資金額，請修正")
					}else{
						preview();
					}
				
	        },
	        error : function(xhr, ajaxOptions, thrownError) {//執行程式失敗的話 等不到回應了 因為可能發生Null....等
				alert("系統錯誤，請聯絡系統管理員");
			}
	    });
	}
}

function checkChange(obj){
  if(obj.id == 'loanTypeA' && obj.checked == true){
	document.getElementById('loanTypeB').checked = false;
  }else if(obj.id == 'loanTypeB' && obj.checked == true){
	document.getElementById('loanTypeA').checked = false;
  }
}

function checkChangeIndex(obj,index){
	  if(obj.id == 'loanTypeAc'+index && obj.checked == true){
		document.getElementById('loanTypeBc'+index).checked = false;
	  }else if(obj.id == 'loanTypeBc'+index && obj.checked == true){
		document.getElementById('loanTypeAc'+index).checked = false;
	  }
	}

function deleteQuotion(index){
	
	inputquotionMoneyArrays.splice(index,1);
	inputAllInArrays.splice(index,1);
	inputLoanTypeArrays.splice(index,1);
	inputDaysArrays.splice(index,1);
	writeQuationHtml();
}

function modifyInput(index){
	
	var loanTypeCheckBox="<input type=\"checkbox\" id=\"loanTypeAc"+index+"\" value=\"A\" checked=\"checked\" onclick = 'checkChangeIndex(this,"+index+")'/>CP2<input type=\"checkbox\" id=\"loanTypeBc"+index+"\" value=\"B\" onclick = 'checkChangeIndex(this,"+index+")'/>短貸"
	var daySelect=$("#daySelect").html().replace(/days/g, "daysd"+index);
	$("#a"+index).html("<input type=\"text\" id=\"ai"+index+"\" align=\"left\" class=\"contant\" value=\""+inputquotionMoneyArrays[index]+"\">")
	$("#b"+index).html("<input type=\"text\" id=\"bi"+index+"\" align=\"left\" class=\"contant\" value=\""+inputAllInArrays[index]+"\">")
	$("#c"+index).html(loanTypeCheckBox);
	$("#d"+index).html(daySelect);
	$("#e"+index).html("<input type=\"button\" id=\"modifyCheck\" class=\"button-brown\" value=\"確認\" onclick=\"modifyQuotion("+index+")\"/>&nbsp;<input type=\"button\" id=\"modifyGiveUp\" class=\"button-brown\" value=\"放棄\" onclick=\"writeQuationHtml()\"/>");
	if(inputLoanTypeArrays[index]=='A'){
		document.getElementById('loanTypeAc'+index).checked = true;
		document.getElementById('loanTypeBc'+index).checked = false;
	}else{
		document.getElementById('loanTypeBc'+index).checked = true;
		document.getElementById('loanTypeAc'+index).checked = false;		
	}
	$("#daysd"+index).val(inputDaysArrays[index]);
	$('[id^=modifyRow]').attr('disabled', true);
	$('[id^=removeRow]').attr('disabled', true);
	
}

function modifyQuotion(index){
	
	var errorMsg;
	var loanAmountIndex=$("#ai"+index).val();
	var allInIndex=$("#bi"+index).val();
	var loanTypeIndex;
	if(document.getElementById('loanTypeAc'+index).checked){
		loanTypeIndex="A";
	}else if(document.getElementById('loanTypeBc'+index).checked){
		loanTypeIndex="B";	
	}else{
		errorMsg="請選擇類別";
	}
	var dayIndex=$("#daysd"+index+" :selected").val();
	if(loanAmountIndex.length <1||allInIndex.length <1||loanAmountIndex<=0||allInIndex<=0){
		alert("報價金額或all in欄位不得為空或小於0");
	}else if(isNaN(loanAmountIndex)||isNaN(allInIndex)){
		alert("報價金額或all in必須為數字");
	}else{	 
		var arrayLength = inputquotionMoneyArrays.length;
		for(var i=0;i<arrayLength;i++){
			if(index!=i){
				if(parseFloat(inputAllInArrays[i])==parseFloat(allInIndex)&&parseFloat(inputDaysArrays[i])==parseFloat(dayIndex)){			
					errorMsg="有2筆以上報價之天期與all in利率皆相同，請修正!";
				}	
			}
		}
		
		if(errorMsg){
			alert(errorMsg);
		}else{	
			 inputquotionMoneyArrays[index]=loanAmountIndex;
			 inputAllInArrays[index] = allInIndex;
			 inputLoanTypeArrays[index] = loanTypeIndex;
			 inputDaysArrays[index] = dayIndex;
		 	 writeQuationHtml();
		}
	}
}

function writeQuationPreviewHtml(){

	var select = "";
	var quationTotal=0;
	 for(var i=0 ; i< inputquotionMoneyArrays.length ; i++){
		 quationTotal+=parseFloat(inputquotionMoneyArrays[i]);
		if(i%2==0)
		 	select+="<tr class=\"td-lighty\">";
		else
			select+="<tr>";
			if(inputLoanTypeArrays[i]=='A'){
				loanTypeString='CP2';
			}else{
				loanTypeString='短貸';
			}
		select+="<td align=\"left\" class=\"contant\">"+(i+1)+"</td>"
		select+="<td align=\"left\" class=\"contant\">"+inputquotionMoneyArrays[i]+"</td>"
		select+="<td align=\"left\" class=\"contant\">"+inputAllInArrays[i]+"</td>"
		select+="<td align=\"left\" class=\"contant\">"+loanTypeString+"</td>"
		select+="<td align=\"left\" class=\"contant\">"+inputDaysArrays[i]+"</td></tr>"
		
	 }

	 $("#inputQuotionPreview").html(select); 

}


function writeQuationHtml(){
// 	inputquotionMoneyArrays.sort();
// 	inputAllInArrays.sort();
// 	inputLoanTypeArrays.sort();
// 	inputDaysArrays.sort();
	var select = "";
	var quationTotal=0;
// 	 select +="<table width=\"88%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"table_none\">";
	 for(var i=0 ; i< inputquotionMoneyArrays.length ; i++){
		 quationTotal+=parseFloat(inputquotionMoneyArrays[i]);
		if(i%2==0)
		 	select+="<tr class=\"td-lighty\">";
		else
			select+="<tr>";
			if(inputLoanTypeArrays[i]=='A'){
				loanTypeString='CP2';
			}else{
				loanTypeString='短貸';
			}
		select+="<td id=\"a"+i+"\" align=\"left\" class=\"contant\">"+inputquotionMoneyArrays[i]+"</td>"
		select+="<td id=\"b"+i+"\" align=\"left\" class=\"contant\">"+inputAllInArrays[i]+"</td>"
		select+="<td id=\"c"+i+"\" align=\"left\" class=\"contant\">"+loanTypeString+"</td>"
		select+="<td id=\"d"+i+"\" align=\"left\" class=\"contant\">"+inputDaysArrays[i]+"</td>"
		select+="<td id=\"e"+i+"\" align=\"left\">"+
				"<input type=\"button\" id=\"modifyRow"+i+"\" class=\"button-brown\" value=\"修改\" onclick=\"modifyInput("+i+")\"/>&nbsp;<input type=\"button\" id=\"removeRow"+i+"\" class=\"button-brown\" value=\"刪除\" onclick=\"deleteQuotion("+i+")\"/></td></tr>"
	 }
// 	 select+="</table>";
	 $("#inputQuotion").html(select); 
	 $("#maxAmountDiv").html(quationTotal);

}


$(function (){
	
	init();
	$("#quotionEntryPreviewDiv").hide();
	$("#addRow").click(function(){
		var quotionMoney=$("#quotionMoney").val();
		var allIn=$("#allIn").val();
		if(quotionMoney.length <1||allIn.length <1||quotionMoney<=0||allIn<=0){
			alert("報價金額或all in欄位不得為空");			
		}else{	 
			var loanType=$('input:checkbox:checked[name="loanType"]').val();
			var days=$("#days :selected").val();
			var index =  inputquotionMoneyArrays.length;
			var errorMsg;
			if(!loanType){
				errorMsg="請選擇類別";
			}
			for(var i=0;i<index;i++){
				if(parseFloat(inputAllInArrays[i])==parseFloat(allIn)&&parseFloat(inputDaysArrays[i])==parseFloat(days)){
					
					errorMsg="有2筆以上報價之天期與all in利率皆相同，請修正!";
				}		
			}
			if(errorMsg){
				alert(errorMsg);
			}else{
			
				 inputquotionMoneyArrays[index]=quotionMoney;
				 inputAllInArrays[index] = allIn;
				 inputLoanTypeArrays[index] = loanType;
				 inputDaysArrays[index] = days;
				 $("#quotionMoney").val("");
				 $("#allIn").val("");
				 $("#addRow").val("新增");
			 	 writeQuationHtml();
			}
		}
	});

   	$('#quotionMoney').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
    });

   	$('#allIn').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
    });
   
	$('input[name="extraAmount"]').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
        if (this.value <0) {
           alert("尚可承作總金額需大於0")
        }
    });
	
	$('input[name="allInRate"]').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.]/g, '');
        }
    });
   	
});
</script>
</head>

<body id="scroller">

<div id="quotionEntryDiv">

 <form:form id="quotionEntryForm" name="quotionEntryForm">
 
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 	<tr><td><h1><span class="title-l">報價登錄</span></h1></td></tr>
    <tr>
      <td>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="10%">標單號碼</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.tenderNo}" /></div></td>
          <th width="10%">報價單號碼</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.quationNo}" /></div></td>
          <th width="10%">幣別</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.currencyName}" /></div></td>
        </tr>
        <tr>
          <th>起迄期間(起)</th>
          <td><div align="left"><c:out value="${quotionEntry.effectDate}" />&nbsp;</div></td>
          <th>起迄期間(迄)</th>
          <td>
	          	<c:forEach var="ctbflbt2" items="${quotionEntry.ctbflbt1.ctbflbt2}">
					<div style="text-align:left;"><c:out value="${ctbflbt2.expiredDate}"/>&nbsp;
          				籌資金額:
         				<c:out value="${ctbflbt2.loanAmount}"/>&nbsp;/&nbsp;天數:<c:out value="${ctbflbt2.days}"/>
         			</div>
          		</c:forEach>
          </td>
          <th width="10%">金額單位</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.amountUnit}" /></div></td>
        </tr>
        <tr>
          <th>邀標日期</th>
          <td><div align="left"><c:out value="${quotionEntry.ctbflbt1.inviteDate}" />&nbsp;</div></td>
          <th>截標日期</th>
          <td><div align="left"><c:out value="${quotionEntry.ctbflbt1.closeDate}" />&nbsp;</div></td>
          <th>截標時間</th>
          <td><div align="left"><c:out value="${fn:substring(quotionEntry.ctbflbt1.closeTime, 0, 8)}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>聯絡人</th>
          <td><div align="left"><c:out value="${quotionEntry.contactUser}" />&nbsp;</div></td>
          <th>聯絡電話</th>
          <td><div align="left"><c:out value="${quotionEntry.contactPhone}" />&nbsp;</div></td>
          <th>傳真</th>
          <td><div align="left"><c:out value="${quotionEntry.contactFax}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>本次可承作總金額</th>
          <td><div align="left" id="maxAmountDiv">&nbsp;</div></td>
          <th>尚可承作總金額</th>
          <td><div align="left"><input type="text" name="extraAmount" value="<c:out value="${quotionEntry.extraAmount}" />">&nbsp;
		  <select  name="extraType" id="extraType">
          	<option value="A">CP2</option>
          	<option value="B">短貸</option>
          </select>
          </div>
          </td>
          <th>30天all in rate </th>
          <td><div align="left"><input type="text" name="allInRate" value="<c:out value="${quotionEntry.extraRate}" />">&nbsp;</div></td>
        </tr>             
      </table>
      </td>
    </tr>
  </table>

  <input type="hidden" id="inputquotionMoneyArrays" name="inputquotionMoneyArrays"/>
  <input type="hidden" id="inputAllInArrays" name="inputAllInArrays"/>
  <input type="hidden" id="inputLoanTypeArrays" name="inputLoanTypeArrays"/>
  <input type="hidden" id="inputDaysArrays" name="inputDaysArrays"/>
  <input type="hidden" id="tenderNo" name="tenderNo" value="${quotionEntry.tenderNo}"/>
  <input type="hidden" id="quationNo" name="quationNo" value="${quotionEntry.quationNo}"/>
  <input type="hidden" name="maxAmount"/>
  <table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0" >
<tr><td><br></td></tr>
	<tr><td>報價金額(<a style="color:red"><c:out value="${quotionEntry.amountUnit}"/></a>)</td><td>all in(%)</td><td>類別</td><td>天期</td><td></td></tr>
	<tr>
		<td>
			<input type="text" name="quotionMoney" id="quotionMoney"/>
		</td>
		<td>
			<input type="text" name="allIn" id="allIn"/>
		</td>
		<td >
			<input type="checkbox" id="loanTypeA" name="loanType" value="A" checked="checked" onclick = 'checkChange(this)'/>CP2<input type="checkbox" name="loanType" id="loanTypeB" value="B" onclick = 'checkChange(this)'/>短貸
		</td>
		<td id=daySelect>
			<select name="days" id="days">
				<c:forEach var="ctbflbt2" items="${quotionEntry.ctbflbt1.ctbflbt2}">
					<option value="${ctbflbt2.days}"><c:out value="${ctbflbt2.days}"/></option>
				</c:forEach>
			</select>
		</td>
		<td>
			<input name="addRow" id="addRow" type="button" class="button-brown" value="新增"/>
		</td>
	</tr>
    <tr>
    		<td colspan="5" style="background:url(<c:url value="/resources/images/table/bottom_bg.gif"/>) repeat-x"><img src="<c:url value="/resources/images/spacer.gif"/>" width="1" height="1"></td>
    </tr>
    <tbody id="inputQuotion"></tbody>
    <c:if test="${!empty quotionEntry.rejectReason}">
    <tr><td>退回原因 &nbsp;:&nbsp;<a style="color:red">${quotionEntry.rejectReason}</a></td></tr>
    </c:if>
  </table>

</form:form>

  <table  width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="cancel"  type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁" />&nbsp;
  		<input name="preview" type="button" class="button-brown" onClick="checkEntry();" value="預覽">
  		<input name="println" type="button" class="button-brown" onClick="doNotQuation();" value="不報價">
  		
      </td>
    </tr>
  </table>
</div>
<div id="quotionEntryPreviewDiv">
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 	<tr><td><h1>報價登錄<span class="title-l">-預覽</span></h1></td></tr>
    <tr>
      <td>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th width="10%">標單號碼</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.tenderNo}" /></div></td>
          <th width="10%">報價單號碼</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.quationNo}" /></div></td>
          <th width="10%">幣別</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.currencyName}" /></div></td>
        </tr>
        <tr>
          <th>起迄期間(起)</th>
          <td><div align="left"><c:out value="${quotionEntry.effectDate}" />&nbsp;</div></td>
          <th>起迄期間(迄)</th>
          <td>
	          	<c:forEach var="ctbflbt2" items="${quotionEntry.ctbflbt1.ctbflbt2}">
					<div style="text-align:left;"><c:out value="${ctbflbt2.expiredDate}"/>&nbsp;
  					籌資金額:        
          			<c:out value="${ctbflbt2.loanAmount}"/>&nbsp;/&nbsp;天數:<c:out value="${ctbflbt2.days}"/></div>
          		</c:forEach>
          </td>
          <th width="10%">金額單位</th>
          <td width="20%"><div align="left"><c:out value="${quotionEntry.amountUnit}" /></div></td>
        </tr>
        <tr>
          <th>邀標日期</th>
          <td><div align="left"><c:out value="${quotionEntry.ctbflbt1.inviteDate}" />&nbsp;</div></td>
          <th>截標日期</th>
          <td><div align="left"><c:out value="${quotionEntry.ctbflbt1.closeDate}" />&nbsp;</div></td>
          <th>截標時間</th>
          <td><div align="left"><c:out value="${fn:substring(quotionEntry.ctbflbt1.closeTime, 0, 8)}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>聯絡人</th>
          <td><div align="left"><c:out value="${quotionEntry.contactUser}" />&nbsp;</div></td>
          <th>聯絡電話</th>
          <td><div align="left"><c:out value="${quotionEntry.contactPhone}" />&nbsp;</div></td>
          <th>傳真</th>
          <td><div align="left"><c:out value="${quotionEntry.contactFax}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>本次可承作總金額</th>
          <td><div align="left" id="maxAmountPreview"></div></td>
          <th>尚可承作總金額</th>
          <td><div align="left" id="extraAmountTypePreview"></div>
          </td>
          <th>30天all in rate </th>
          <td><div align="left" id="allInRatePreview"></div></td>
        </tr>             
      </table>
      </td>
    </tr>
  </table>
  
   <input type="hidden" id="tenderNo" name="tenderNo" value="${quotionEntry.tenderNo}"/>
      <tr><td><br></td></tr>
  <table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0" >
	<tr>
		<td>項次</td>
		<td>報價金額(<a style="color:red"><c:out value="${quotionEntry.amountUnit}"/></a>)</td>
		<td>all in(%)</td>
		<td>類別</td>
		<td>天期</td>
	</tr>
	
    <tbody id="inputQuotionPreview"></tbody>		  
	<tr>	  
    		<td colspan="5" style="background:url(<c:url value="/resources/images/table/bottom_bg.gif"/>) repeat-x"><img src="<c:url value="/resources/images/spacer.gif"/>" width="1" height="1"></td>
    </tr>
  </table>



  <table  width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr class="pbtn">
      <td>
       	<br/>
  		<input name="preview" type="button" class="button-brown" onClick="backtoQuotionEntry();" value="回上頁">
  		<input name="println" type="button" class="button-brown" onClick="quotionConfirm();" value="確認">
  		
      </td>
    </tr>
  </table>
</div> 
</body>
</html>