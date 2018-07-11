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
	  	 <td><h1><span class="title-l">票券批號登錄(得標確認)</span></h1></td>
	  </tr>
	  <tr>
	    <td>
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
      	</table>
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
                <td><div align="left"><c:out value="${awardData.issuerId}"/><c:out value="${awardData.issuerName}"/></div></td>
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
	    		<th width="110">報價金額</th>
                <td><div align="left"><c:out value="${awardData.quationAmount}"/></div></td>
                <th width="110">得標金額</th>
                <td><div align="left"><c:out value="${awardData.awardAmount}"/></div></td>
                <th width="110">天數</th>
                <td><div align="left"><c:out value="${awardData.days}"/></div></td>
	    	</tr>
	    	<tr>
	    		<th width="110">all in(%)</th>
                <td><div align="left"><c:out value="${awardData.quationRate}"/></div></td>
                <th width="110">票券批號</th>
                <td colspan="3"><div align="left">
               		<div id="batchNoInit">
               			<input id="batchNo" type="text" class="textfield" size="30" value="<c:out value="${awardData.batchNo}"/>">
               		</div>
               		<div id="batchNoPreview">
               		</div>
                </div></td>
	    	</tr>
	    </table>
	   </td>
	  </tr>
	 </table>
	</form:form>  
 <br/>
	 	<table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0" > 
	 		<c:if test="${awardData.approveStatus1 == '1'}">   
			  <tr>	
			    <td colspan="2">退回原因&nbsp;:&nbsp;<a style="color:red"><c:out value="${awardData.rejectReason1}"/></a></td>
			  </tr>	
		    </c:if>    
	  	</table>  
	 
     <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  	    <tr class="pbtn">
		   <td>
		      <br/>
		      <div id="detailDiv">
			  		<input id="doCancel" type="button" class="button-brown" value="取消" />&nbsp;
			  		<input id="doPreview" type="button" class="button-brown" value="預覽" />&nbsp;
					<input id="doAbandon" type="button" class="button-brown" value="放棄" />&nbsp;
			  </div>
			  <div id="previewDiv">
					<input id="doBack" type="button" class="button-brown" value="回上頁" />&nbsp;
			  		<input id="doConfirm" type="button" class="button-brown" value="確認" />&nbsp;
			  </div>
		    </td>
		 </tr>
      </table>
    <form:form id="abandonForm">
    	<input id="quationNo" type="hidden" name="quationNo" value="<c:out value="${awardData.id.quationNo}"/>"/>
    	<input id="itemNo" type="hidden" name="itemNo" value="<c:out value="${awardData.id.itemNo}"/>"/>
    	<input id="hiddenBatchNo" type="hidden" name="batchNo" />
	</form:form>
<script type="text/javascript">
$(function() {
	$("#previewDiv").hide();
	$("#detailDiv").show();
	
	$("#doCancel").click(function(){
		$("#queryForm").attr("action", '<c:url value="/awardDataRegister"/>');
		$("#queryForm").submit();
	});
	
	$("#doPreview").click(function() {
		var batchNo = $("#batchNo").val();
		if(batchNo == ''){
			alert("請輸入票券批號！");
			return false;
		}else{
			//新增檢查使用者輸入的票券批號不得重複
			var header = $("meta[name='_csrf_header']").attr("content");
			var token = $("meta[name='_csrf']").attr("content");
			$.ajax({
				traditional: true,
		        url : '<%=request.getContextPath()%>/awardDataRegister/checkAwardDataBatchNo',
		        type : "POST",                                                                    
		        data:"batchNo=" + batchNo + "&quationNo="+ '<c:out value="${awardData.id.quationNo}"/>' + "&itemNo=" +'<c:out value="${awardData.id.itemNo}"/>',
		        beforeSend: function(xhr){
		            xhr.setRequestHeader(header, token);
		        },
		        success : function(data) {
		        	if(data){
						alert("["+data+"]票券批號已存在，請確認後再輸入")
					}else{					  
						$("#detailDiv").hide();
						$("#batchNoInit").hide();
						$("#previewDiv").show();
						document.getElementById('batchNoPreview').innerHTML = $("#batchNo").val();
					}
		        },
		        error : function(xhr, ajaxOptions, thrownError) {//執行程式失敗的話 等不到回應了 因為可能發生Null....等
					alert("系統錯誤，請聯絡系統管理員");
				}
		    });
		}
	});
	
	$("#doBack").click(function() {
		$("#detailDiv").show();
		$("#batchNoInit").show();
		$("#previewDiv").hide();
		document.getElementById('batchNoPreview').innerHTML = "";
	});
	
	$("#doConfirm").click(function() {
		var batchNo = $("#batchNo").val();
		
		if(batchNo == ''){
			alert("請輸入票券批號！");
			return false;
		}
		
		if(confirm("是否確認這次得標?")){
			$("#hiddenBatchNo").val($("#batchNo").val());
			$("#abandonForm").attr("action",'<c:url value="/awardDataRegister/doConfirm"/>');
			$("#abandonForm").submit();
		}	
	});
	
	$("#doAbandon").click(function() {
		if(confirm("是否確認放棄這次得標?")){
			$("#abandonForm").attr("action",'<c:url value="/awardDataRegister/doAbandon"/>');
			$("#abandonForm").submit();
		}	
	});
});
</script>		
</body>
</html>
