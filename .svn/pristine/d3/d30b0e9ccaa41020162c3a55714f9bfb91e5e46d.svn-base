<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/checkAll.js"></script>
<script type="text/javascript" language="javascript">
$(function() {
	$("#del").click(function(){	
	  var list = document.getElementsByName("messageIdSelect");
	  var length = list.length;
	  var isCheck = false;
	  for(var i=0;i<length;i++) {
	    if(list[i].type=='checkbox' && list[i].checked) {
	      isCheck = true;
	      break;
	    }
	  }
	  if(isCheck) {
	    if(confirm('確定將刪除所選取之資料?')){
			$("#queryForm").attr("action", '<c:url value="/MessageInbox/delMessage"/>');
			$("#queryForm").submit();
	    }
	  }
	  else {
	    alert("未勾選任一筆或多筆訊息！");
	  }
	})
})

</script>
</head>


<body id="scroller">
<h1>訊息匣</h1>

<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <c:if test="${messageInBoxList.totalElements > 0}">
        <form:form name="queryForm" id="queryForm" method="POST">       
        <input id="toPage" type="hidden" name="page">
          <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table01">
            <tr class="cell-title">
              <th align="center">
                <input  name="selectAll" type="checkbox" title="選擇或取消選擇全部資料" onClick="select_all(this.form.name, 'messageIdSelect', this.name)">
              </th>
              <th>主旨</th>
              <th>內容</th>
              <th>發送者<br/>時間</th>
            </tr>
            <c:set var="index" value="1" scope="page" />
            <c:forEach var="messages" items="${messageInBoxList.content}" varStatus="status">
              <c:set var="styleClass" value=""/>
              <c:if test="${(index % 2) == 0}">
                <c:set var="styleClass" value="td-lighty"/>
              </c:if>
              <tr class="<c:out value="${styleClass}" />">
                <td>
                  <input name="messageIdSelect" type="checkbox" onClick="select_one(this.form.name, this.name, 'selectAll')" value="<c:out value="${messages.messageId}" />">
                </td>
                <td><c:out value="${messages.subject}" /></td>
                <td><div align="left"><c:out value="${messages.content}" /></div></td>
                <td><c:out value="${messages.sender}" /><br/>
                  <fmt:formatDate value="${messages.messageTime}" pattern="yyyy.MM.dd HH:mm:ss"/>
      </td>
              </tr>
              <c:set var="index" value="${index+1}" />
            </c:forEach>
          </table>
          
        </form:form>
      </c:if>
      <c:if test="${messageInBoxList.totalElements == 0}">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table">
          <tr align="center">
            <td><font color="#FF0000">無 任 何 訊 息 資 料 內 容 !!!</font>
    </td>
          </tr>
        </table>
      </c:if>
</td>
  </tr>
</table>
			<!-- 分頁  -->
			<c:if test="${messageInBoxList.totalPages > 1}">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="table-count">
					<tr>
						<tags:pagination page="${messageInBoxList}" paginationSize="5" />
					</tr>
				</table>
			</c:if>
			<!-- 分頁結束 -->
<c:if test="${messageInBoxList.totalElements > 0}">
  <p class="pbtn">
    <input name="del" id="del" type="button" class="button-brown" value="刪除" >
    &nbsp;<input name="cancel" type="button" class="button-brown" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/todoMain'" value="回首頁" />&nbsp;
  </p>
</c:if>

</body>
</html>
