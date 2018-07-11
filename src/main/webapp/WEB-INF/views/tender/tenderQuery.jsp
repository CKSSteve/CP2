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

$(function() {
	$("#inviteDateStartDate").datepicker({showOn: 'both', showOtherMonths: true,
	   	showWeeks: true, firstDay: 1, changeFirstDay: false,
	   	buttonImageOnly: true, buttonImage: '<c:url value="/resources/images/calendar.gif"/>'});
   	
	$("#inviteDateEndDate").datepicker({showOn: 'both', showOtherMonths: true,
	   	showWeeks: true, firstDay: 1, changeFirstDay: false,
	   	buttonImageOnly: true, buttonImage: '<c:url value="/resources/images/calendar.gif"/>'});
	
	$("#closeDateTimeStartDate").datepicker({showOn: 'both', showOtherMonths: true,
	   	showWeeks: true, firstDay: 1, changeFirstDay: false,
	   	buttonImageOnly: true, buttonImage: '<c:url value="/resources/images/calendar.gif"/>'});
	
	$("#closeDateTimeEndDate").datepicker({showOn: 'both', showOtherMonths: true,
	   	showWeeks: true, firstDay: 1, changeFirstDay: false,
	   	buttonImageOnly: true, buttonImage: '<c:url value="/resources/images/calendar.gif"/>'});
 	
   	$("#query").click(function(){		
		var inviteStartDate = $("#inviteDateStartDate").val();
		var inviteEndDate = $("#inviteDateEndDate").val();
		var closeStartDate = $("#closeDateTimeStartDate").val();
		var closeEndDate = $("#closeDateTimeEndDate").val();
		
		if(inviteStartDate != ""){
			var startDate1 = new Date(inviteStartDate).getTime();			
		}
		
		if(inviteEndDate != ""){
			var endDate1 = new Date(inviteEndDate).getTime();			
		}

		if(closeStartDate != ""){
			var startDate2 = new Date(closeStartDate).getTime();			
		}
		
		if(closeEndDate != ""){
			var endDate2 = new Date(closeEndDate).getTime();			
		}
		
		if(closeStartDate.length == 0 && closeEndDate.length == 0){
			if (inviteStartDate.length != 0 && inviteEndDate.length != 0 && inviteStartDate > inviteEndDate) {
				alert("標單起始日期不能小於結束日期");
				return false;
			}else if(inviteStartDate.length == 0 && inviteEndDate.length == 0){
				alert("請輸入標單日期或截標日期");
				return false;
			}else if(inviteStartDate.length == 0 || inviteEndDate.length == 0){
				alert("標單起迄日皆需輸入");
				return false;
			} else if(inviteStartDate.length != 0 && inviteEndDate.length != 0 && ( (endDate1 - startDate1)/(1000 * 60 * 60 * 24) > 365 ) ){
				alert("標單日期查詢區間以一年為限");
				return false;
		    }
		}
		
		if(inviteStartDate.length == 0 && inviteEndDate.length == 0){
			if (closeStartDate.length != 0 && closeEndDate.length != 0 && closeStartDate > closeEndDate) {
				alert("截標起始日期不能小於結束日期");
				return false;
			}else if(closeStartDate.length == 0 && closeEndDate.length == 0){
				alert("請輸入截標日期");
				return false;
			}else if(closeStartDate.length == 0 || closeEndDate.length == 0){
				alert("截標起迄日皆需輸入");
				return false;
			} else if(closeStartDate.length != 0 && closeEndDate.length != 0 && ( (endDate2 - startDate2)/(1000 * 60 * 60 * 24) > 365 ) ){
				alert("截標日期查詢區間以一年為限");
				return false;
		    }
		}
		
		if((inviteStartDate.length != 0 && inviteEndDate.length == 0)||(inviteStartDate.length == 0 && inviteEndDate.length != 0)){
			alert("標單起迄日皆需輸入");
			return false;
		}
		
		if((closeStartDate.length != 0 && closeEndDate.length == 0)||(closeStartDate.length == 0 && closeEndDate.length != 0)){
			alert("截標起迄日皆需輸入");
			return false;
		}
			$("#queryForm").attr("action", '<c:url value="/tenderQuery/doQuery"/>');
			$("#queryForm").submit();
		
	});
   	
   	$("#reset").click(function(){
   		$("#inviteDateStartDate").val("");
   		$("#inviteDateEndDate").val("");
   		$("#closeDateTimeStartDate").val("");
   		$("#closeDateTimeEndDate").val("");
   		$("#issuerId").val("0");
   		$("#currencyId").val("0");  		
   		
   	});
})

function showDetail(vartenderNo){
	$("#detailTenderNo").val(vartenderNo);
	$("#method").val("/tenderQuery/doQuery");
	$("#queryForm").attr("action", '<c:url value="/tenderQuery/showDetail"/>');
	$("#queryForm").submit();
}

</script>
</head>

<body id="scroller">
<h1><span class="title-l">標單查詢</span>-請選擇查詢方式</h1>
<form:form id="queryForm" name="queryForm" method="POST" commandName="searchBean">
   <input id="method" type="hidden" name="method">
   <input id="toPage" type="hidden" name="page">
   <input id="detailTenderNo" type="hidden" name="detailTenderNo">
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
      		<tr>
			<th width="110">發行人統編</th>
			<td colspan="3">
				<div align="left">
					<select  name="issuerId" id="issuerId">
						<option value="0" label="全部">全部</option>
						<c:forEach var="issuerId" items="${issuerIdList}">
							<c:choose>
							<c:when test="${searchBean.issuerId == issuerId.uni}">
		           				<option value="${issuerId.uni}" selected="selected">
		           				<c:out value="${issuerId.uni}" />&nbsp;<c:out value="${issuerId.chineseName}" /></option>
		           			</c:when>
		           			<c:otherwise>
								<option value="${issuerId.uni}">
									<c:out value="${issuerId.uni}" />&nbsp;<c:out value="${issuerId.chineseName}" />
								</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</td>
		</tr>
      	<tr>
          <th width="110"><span>標單日期</span></th>
            <td><div align="left">自
                <input type="Date pickers" id="inviteDateStartDate" name="inviteDateStartDate" value="${searchBean.inviteDateStartDate }" class="textfield dateISO" size="10"/>
                	至
                <input type="Date pickers" id="inviteDateEndDate" name="inviteDateEndDate" value="${searchBean.inviteDateEndDate }" class="textfield dateISO" size="10" />
                </div>
            </td>
        </tr>
        <tr>
          <th width="110"><span>截標日期</span></th>
            <td><div align="left">自
                <input type="Date pickers" id="closeDateTimeStartDate" name="closeDateTimeStartDate" value="${searchBean.closeDateTimeStartDate }" class="textfield dateISO" size="10"/>
                	至
                <input type="Date pickers" id="closeDateTimeEndDate" name="closeDateTimeEndDate" value="${searchBean.closeDateTimeEndDate}" class="textfield dateISO" size="10" />
                </div>
            </td>
        </tr>
		<tr>
			<th width="110">幣別</th>
			<td colspan="3">
				<div align="left">
					<select  name="currencyId" id="currencyId">
						<option value="0" label="全部">全部</option>
						<c:forEach var="currency" items="${currencyList}">
						<c:choose>
							<c:when test="${searchBean.currencyId == currency.id.codeKey}">
			           			<option value="${currency.id.codeKey}" selected="selected">
									<c:out value="${currency.id.codeKey}" />&nbsp;<c:out value="${currency.codeValue}" />
								</option>
			           		</c:when>
			           		<c:otherwise>
								<option value="${currency.id.codeKey}">
									<c:out value="${currency.id.codeKey}" />&nbsp;<c:out value="${currency.codeValue}" />
								</option>
							</c:otherwise>
						</c:choose>

						</c:forEach>
					</select>
				</div>
			</td>
		</tr>
	  </table>      
      </td>
  </tr>
</table>


  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr class="pbtn">
      <td>
        <br />
        <input type="button" name="reset" id="reset" value="重設" class="button-brown"/>
        <input type="button" name="query" id="query" value="確認" class="button-brown"/>
      </td>
    </tr>
  </table>
</form:form>

<c:if test="${showForm}">


	<h1>標單查詢-<span class="title-l">查詢列表</span></h1>
	
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td>
	    <div id="htmlBody">
	    <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" id="table01">
	      <tr class="cell-title">
	      	<th nowrap="nowrap">標單號碼</th>
	      	<th nowrap="nowrap">發行人</th>
	      	<th nowrap="nowrap">標單日期</th>
	        <th nowrap="nowrap">截標日期</th>
	        <th nowrap="nowrap">截標時間</th>
	        <th nowrap="nowrap">幣別</th>
	        <th nowrap="nowrap">籌資總金額</th>
	        <th nowrap="nowrap">狀態</th>
		  </tr>
      <c:if test="${queryTenderList.totalElements > 0}">	     
	      <c:forEach items="${queryTenderList.content }" var="item" varStatus="idx" >
		      <tr class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
		        <td align="center" nowrap="nowrap"><a href="#" onclick="showDetail('<c:out value="${item.tenderNo}"/>')"><c:out value="${item.tenderNo}"/></a></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.issuerId }"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.inviteDate }"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.closeDate}"/></td> 
		        
		        <td align="center" nowrap="nowrap"><c:out value="${fn:substring(item.closeTime, 0, 8)}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.currencyName}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.loanAmount}"/>&nbsp;<c:out value="${item.amountUnit}"/></td>
				<td align="center" nowrap="nowrap"><tags:codeMap type="CTBFLBT1_STATUS" code="${item.status}"/></td>
		        
		      </tr>
		  </c:forEach>
       </c:if>
		<c:if test="${queryTenderList.totalElements == 0}">
				     <tr>
					     <td colspan="8">
					           <font size="3" color="red">查無資料!</font>
					      </td>
					 </tr>
		</c:if>       		  
	    </table>
	    </div>
	    </td>
	  </tr>
	</table>
</c:if>

			<!-- 分頁  -->
			<c:if test="${queryTenderList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${queryTenderList}" paginationSize="5" />
					</tr>
				</table>
			</c:if>
			<!-- 分頁結束 -->
			
</body>
</html>
