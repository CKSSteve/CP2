<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body id="scroller">
<h1>使用者存取記錄 - <span class="title-l">明細列表</h1>
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
      <c:forEach items="${result}" var="item" varStatus="idx" >
	      <tr class="<c:out value="${idx.index%2 eq 0 ? '' : 'td-lighty' }" />">
	       	 <td align="center" nowrap="nowrap"><c:out value="${item.userName}"/></td>
	         <td align="center" nowrap="nowrap"><c:out value="${item.userId}"/></td>
	         <td align="center" nowrap="nowrap"><fmt:formatDate value="${item.logTime}" pattern="yyyy.MM.dd a hh:mm:ss"/></td>
	         <td align="center" nowrap="nowrap"><c:out value="${item.function.functionName}"/></td>
	      	 <td align="center" nowrap="nowrap"><c:out value="${item.action}"/></td>
	         <td align="center" nowrap="nowrap"><tags:codeMap type="USERLOG_STATUS" code="${item.status}"/></td>   
	      </tr>
      </c:forEach>
    </table>
    <br>
    <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" id="table01">
    	<tr class="cell-title">
        	<th>內容</th>
      	</tr>
      	<c:forEach var="log" items="${result}" varStatus="status">
      		<tr class="<c:out value="${status.index%2==0 ? '':'td-lighty'}" />">
        		<td><textarea cols="80" rows="20"><c:out value="${log.content}" /></textarea></td>
      		</tr>
      	</c:forEach>
    </table>
    </div>
    </td>
  </tr>
</table>
<form:form id="backForm" name="backForm" commandName="searchBean" method="POST">
	<form:hidden path="searchLogTimeStartDate"/>
	<form:hidden path="searchLogTimeEndDate"/>
	<form:hidden path="searchUserId"/>
	<form:hidden path="searchFunctionId"/>
</form:form>

<p class="pbtn">
	<input id="goBack" type="button" class="button-brown" value="回上頁"/>
</p>
<script type="text/javascript">
$(function() {
	
	$("#goBack").click(function(){
		$("#backForm").attr("action", '<c:url value="/queryUserAccessRecord/doQuery"/>');
		$("#backForm").submit();
	});
	
});
</script>
</body>
