<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<html>
<head>
</head>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
  <tr>
    <td class="menu_title">標單作業</td>
  </tr>
</table>
<TABLE width="98%" border="0" align="center" cellPadding="4" cellSpacing="1" id="table02_noborder" style="border-bottom:2px solid #8B4513;border-top:2px solid #8B4513">
<!--
 total : function 總筆數
 tr ~ tr 為一組橫向資料
 functionCount 每三個一組
 functionId : 讀取 parentId 為null的 functionId
 startTable 同一組資料 html tag ,其餘parentId != null 時
  其functionId substring parentId length 所得的值 , 是否與目前的functionId 相同
 fn:substring(function.functionId,0,fn:length(functionId)) ==functionId
-->
<c:set var="total" value="0"/>
<c:forEach var="i" items="${functionListA}" begin="1" step="1">
	<c:set var="total" value="${total+1}"/>
</c:forEach>
<c:set var="functionCount" value="0"/>
<c:set var="functionId" value=""/>
<c:set var="startTable" value="ture"/>

<c:forEach var="function" items="${functionListA}" varStatus="status">
	<c:set var="startTable" value="${fn:substring(function.functionId,0,fn:length(functionId)) == functionId}"/>
    <c:if test="${function.parentId==null}">
		<c:set var="functionId" value="${function.functionId}"/>
    </c:if>
    <!-- 每組三個function開始 -->
    <c:if test="${function.parentId==null && functionCount%3 ==0}">
		<c:if test="${status.index==0}">
			<TR>
		</c:if>
    </c:if>

    <!-- 每個 function html tag 判斷 -->
    <c:if test="${!startTable || status.index==0}">
	    <c:if test="${status.index==0}">
	    	<TD vAlign="top">
			<TABLE cellSpacing="0" cellPadding="0" border="0" id="table_none">
	    </c:if>
        <c:if test="${!startTable}">
	        <c:out value="</TABLE>" escapeXml="false" />
	        <c:out value="</TD>" escapeXml="false" />
	        <c:if test="${function.parentId==null && functionCount%3 ==0}">
	            <c:if test="${functionCount%3 ==0 && status.index >0}">
             		<c:out value="</TR>" escapeXml="false" />
              		<c:out value="<TR>" escapeXml="false" />
	            </c:if>
            </c:if>
            <TD vAlign="top">
            <TABLE cellSpacing="0" cellPadding="0" border="0" id="table_none">
		</c:if>
	</c:if>
	<c:set var="isCheck" value="false"/>
    <c:forEach var="roleFunction"  items="${roleFunction}">
   
    	<c:choose>
        	<c:when test="${roleFunction.id.functionId == function.functionId}">
            	<c:set var="isCheck" value="true"/>
			</c:when>
		</c:choose>
	</c:forEach>
    <!-- 每個function html 內容 -->
    <c:choose>
      <c:when test="${empty function.parentId}">
            <TR>
              <TD><img src="<c:url value="/resources/images/folderopen.gif"/>" width="24" height="22" alt=""></TD>
              <TD colSpan="3">
                <c:choose>
                  <c:when test="${isCheck}">
                    <input type="checkbox" name="<c:out value="${function.functionId}" />" value="<c:out value="${function.functionId}" />" onclick="checkAll('<c:out value="${function.functionId}" />');" checked="checked" id="<c:out value="${function.functionId}" />" />
                  </c:when>
                  <c:otherwise>
                    <input type="checkbox" name="<c:out value="${function.functionId}" />" value="<c:out value="${function.functionId}" />" onclick="checkAll('<c:out value="${function.functionId}" />');" id="<c:out value="${function.functionId}" />" />
                  </c:otherwise>
                </c:choose>
                <c:out value="${function.functionName}" /></TD>
            </TR>
      </c:when>
      <c:otherwise>
            <TR>
              <TD><img src="<c:url value="/resources/images/node.gif"/>" width="16" height="22" alt=""></TD>
              <TD valign="top"><img src="<c:url value="/resources/images/page.gif"/>" border="0" align="absmiddle" alt=""></TD>
              <TD>
                <c:choose>
                  <c:when test="${isCheck}">
                    <input type="checkbox" name="<c:out value="${fn:substring(function.functionId,0,fn:length(functionId))}" />" value="<c:out value="${function.functionId}" />" checked="checked" onclick="checkRoot('<c:out value="${fn:substring(function.functionId,0,fn:length(functionId))}" />');" >
                  </c:when>
                  <c:otherwise>
                    <input type="checkbox" name="<c:out value="${fn:substring(function.functionId,0,fn:length(functionId))}" />" value="<c:out value="${function.functionId}" />" onclick="checkRoot('<c:out value="${fn:substring(function.functionId,0,fn:length(functionId))}" />');" >
                  </c:otherwise>
                </c:choose>
              </TD>
              <TD><c:out value="${function.functionName}" /></TD>
            </TR>
      </c:otherwise>
    </c:choose>

    <!-- function 結束 -->
    <c:if test="${status.index == total}">
      <c:out value="</table>" escapeXml="false" />
      <c:out value="</td>" escapeXml="false" />
      <c:out value="</tr>" escapeXml="false" />
    </c:if>

    <!-- function parent 及 廻圈計算 -->
    <c:if test="${function.parentId==null}">
          <c:set var="functionCount" value="${functionCount+1}"/>
    </c:if>

</c:forEach>
</TABLE>
