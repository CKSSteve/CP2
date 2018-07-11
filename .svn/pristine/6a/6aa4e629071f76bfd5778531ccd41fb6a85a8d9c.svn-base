<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body id="scroller">
    <h1>使用者存取記錄 - 請選擇查詢方式</h1>
<form:form name="queryForm" id="queryForm" method="POST" commandName="searchBean">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
      	<tr>
          <th width="110"><span>使用日期</span></th>
            <td><div align="left">自
                <input type="Date pickers" id="searchLogTimeStartDate" name="searchLogTimeStartDate" value="${searchBean.searchLogTimeStartDate }" class="textfield dateISO" size="10"/>
                	至
                <input type="Date pickers" id="searchLogTimeEndDate" name="searchLogTimeEndDate" value="${searchBean.searchLogTimeEndDate }" class="textfield dateISO" size="10" />
                </div>
            </td>
          <th><span>使用者帳號</span></th>
          	<td>
          		<div align="left">
          			<input type="text" id="searchUserId" name="searchUserId" value="${searchBean.searchUserId }" size="30" class="textfield" />
          		</div>
          	</td>  
        </tr>
		<tr>
			<th width="110">功能類別</th>
			<td colspan="3">
				<div align="left">
					<form:select style="width: 91; height: 18"
						path="searchFunctionId" multiple="false">
						<form:option value="0" label="全部" />
						<c:forEach var="function" items="${functionList}">
							<form:option value="${function.functionId }">
								<c:out value="${function.functionName }" />
							</form:option>
						</c:forEach>
					</form:select>
				</div>
			</td>
		</tr>
	  </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
        <tr>
            <td width="50"><span class="contant"><img src="<c:url value="/resources/images/page.gif"/>" width="15" height="20" border="0" align="middle" alt=""></span><span class="red">注意:</span></td>
              <td>1.未輸入或勾選任何查詢條件，視同查詢全部</td>
          </tr>          
      </table>
      </td>
  </tr>
</table>
<p class="pbtn">
  <input id="resetBtn" name="cancel" type="button" class="button-brown" value="重設"/>
	&nbsp;
  <input id="doQuery" type="button" class="button-brown" value="查詢" />
</p>
<input id="toPage" type="hidden" name="page">
</form:form>
<c:if test="${showForm}">


	<h1>使用者存取記錄-<span class="title-l">查詢列表</span></h1>
	
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td>
	    <div id="htmlBody">
	    <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" id="table01">
	      <tr class="cell-title">
	      	<th nowrap="nowrap">使用者名稱</th>
	      	<th nowrap="nowrap">使用者帳號</th>
	      	<th nowrap="nowrap">使用日期</th>
	        <th nowrap="nowrap">存取記錄</th>
	        <th nowrap="nowrap">執行</th>
	        <th nowrap="nowrap">執行狀態</th>
		  </tr>
      <c:if test="${resultList.totalElements > 0}">	      
	      <c:forEach items="${resultList.content }" var="item" varStatus="idx" >
		      <tr class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
		        <td align="center" nowrap="nowrap"><c:out value="${item.userName}"/></td>
		        <td align="center" nowrap="nowrap"><c:out value="${item.userId}"/></td>
		        <td align="center" nowrap="nowrap"><fmt:formatDate value="${item.logTime}" pattern="yyyy.MM.dd a hh:mm:ss"/></td>
		        <td align="center" nowrap="nowrap">
		        	<a href="#" onclick="showDetail('${item.logId }')"><c:out value="${item.function.functionName}"/></a> 
		        </td>
		      	<td align="center" nowrap="nowrap"><c:out value="${item.action}"/></td>
		        <td align="center" nowrap="nowrap"><tags:codeMap type="USERLOG_STATUS" code="${item.status.trim()}"/></td>
		       </tr>
		  </c:forEach>
       </c:if>
		<c:if test="${resultList.totalElements == 0}">
		     <tr>
			     <td colspan="7">
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
<c:if test="${resultList.totalPages > 1}">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table-count">
		<tags:pagination page="${resultList}" paginationSize="5"/>
	</table>
</c:if>
<form:form id="queryDetailForm">
    <input class="searchHiddenLogTimeStartDate" id="searchHiddenLogTimeStartDate" type="hidden" name="searchLogTimeStartDate" value=""/>
    <input class="searchHiddenLogTimeEndDate" id="searchHiddenLogTimeEndDate" type="hidden" name="searchLogTimeEndDate" value=""/>
    <input class="searchHiddenUserId" id="searchHiddenUserId" type="hidden" name="searchUserId" value=""/>
    <input class="searchHiddenFunctionId" id="searchHiddenFunctionId" type="hidden" name="searchFunctionId" value=""/>
    <input id="hiddenLogId" type="hidden" name="logId" value=""/>
</form:form>

<script type="text/javascript">
$(function() {
	$("#searchLogTimeStartDate").datepicker({showOn: 'both', showOtherMonths: true,
	showWeeks: true, firstDay: 1, changeFirstDay: false,
	buttonImageOnly: true, buttonImage: '<%=request.getContextPath()%>/resources/images/calendar.gif'}); 
		
	$("#searchLogTimeEndDate").datepicker({showOn: 'both', showOtherMonths: true,
    showWeeks: true, firstDay: 1, changeFirstDay: false,
	buttonImageOnly: true, buttonImage: '<%=request.getContextPath()%>/resources/images/calendar.gif'});
	
	$("#doQuery").click(function(){		
		var applyStartDate = $("#searchLogTimeStartDate").val();
		var applyEndDate = $("#searchLogTimeEndDate").val();		
		
		if(applyStartDate != ""){
			var startDate = new Date(applyStartDate).getTime();			
		}
		
		if(applyEndDate != ""){
			var endDate = new Date(applyEndDate).getTime();			
		}
		
		
		if(applyStartDate.length == 0 && applyEndDate.length == 0){
			alert("請輸入使用日期");
			return false;
		}else if(applyStartDate.length == 0 || applyEndDate.length == 0){
			alert("起迄日皆需輸入");
			return false;
		}else if (applyStartDate.length != 0 && applyEndDate.length != 0 && applyStartDate > applyEndDate) {
			alert("查詢起始日期不能小於結束日期");
			return false;
		} else if(applyStartDate.length != 0 && applyEndDate.length != 0 && ( (endDate - startDate)/(1000 * 60 * 60 * 24) > 365 ) ){
			alert("查詢日期區間以一年為限");
			return false;
	    }else{
			$("#queryForm").attr("action", '<c:url value="/queryUserAccessRecord/doQuery"/>');
			$("#queryForm").submit();
		}
	});
	
	$("#resetBtn").click(function() {
		$("input[name='searchLogTimeStartDate']").val('');
		$("input[name='searchLogTimeEndDate']").val('');
		$("input[name='searchUserId']").val('');
		$("select[name='searchFunctionId']").val('0');
	});
});

function showDetail(logId) {
	
	$(".searchHiddenLogTimeStartDate").val($("#searchLogTimeStartDate").val());
	$(".searchHiddenLogTimeEndDate").val($("#searchLogTimeEndDate").val());
	$(".searchHiddenUserId").val($("#searchUserId").val());
	$(".searchHiddenFunctionId").val($("#searchFunctionId option:selected").val());
	
	$("#hiddenLogId").val(logId);
		
	$("#queryDetailForm").attr("action", '<c:url value="/queryUserAccessRecord/showDetail"/>');
	$("#queryDetailForm").submit(); 
}

function showToday(obj){
	if(!obj.value){
		var time=new Date(); 
		time.setDate(time.getDate());
		get_year=time.getFullYear(); 
		get_month=time.getMonth()+1; 
		get_day=time.getDate();
		get_month= get_month.toString().length > 1 ? get_month : '0' + get_month;
		get_day= get_day.toString().length > 1 ? get_day : '0' + get_day;
		obj.value = get_year +"/"+ get_month +"/"+ get_day;
	}
}
</script>
</body>
