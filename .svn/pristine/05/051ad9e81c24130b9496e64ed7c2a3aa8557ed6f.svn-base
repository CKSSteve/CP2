<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#table-count{
 	padding:0px 10px; 
 	margin-top:10px; 
 	margin-bottom:30px;
}
#table-count td{
	font-family:Arial,Futura,"新細明體","細明體",sans-serif;
	font-size: 12px;
	color:#df1a00;
	/*padding-top:15px;*/
	border:0px;
}

#table-count ul{
	list-style-type:none;
	margin:0;
	padding:0;
	overflow:hidden;
	float:left;
}
#table-count ul li{
	float:left;
}
#table-count ul li a.first_page,
#table-count ul li a.last_page,
#table-count ul li a.goto,
#table-count ul li a.num_page{
	display:block;
	line-height:23px;
	font-size: 12px;
	color:#3a3a3a;
	padding-left:10px;
	padding-right:10px;
	margin-left:2px;
	margin-right:2px;
	background:url(<%=request.getContextPath()%>/resources/images/icon_nb.gif) repeat-x left center;
/* 	border:1px solid #3a3a3a; */
	-webkit-border-radius:5px;
	-moz-border-radius:5px;
	border-radius:5px;
	text-decoration:none !important;
}
#table-count ul li a.first_page:hover,
#table-count ul li a.last_page:hover,
#table-count ul li a.num_page:hover{
	display:block;
	line-height:23px;
	font-size: 12px;
	color:#FFF;
	padding-left:10px;
	padding-right:10px;
	margin-left:2px;
	margin-right:2px;
	background:url(<%=request.getContextPath()%>/resources/images/icon_nr.gif) repeat-x left center;
	border:1px solid #f1db6c;
	-webkit-border-radius:5px;
	-moz-border-radius:5px;
	border-radius:5px;
	text-decoration:none !important;
}
#table-count ul li a.selected{
	display:block;
	line-height:23px;
	font-size: 12px;
	color:#FFF;
	padding-left:10px;
	padding-right:10px;
	margin-left:2px;
	margin-right:2px;
	background:url(<%=request.getContextPath()%>/resources/images/icon_nr.gif) repeat-x left center;
	border:1px solid #f1db6c;
	-webkit-border-radius:5px;
	-moz-border-radius:5px;
	border-radius:5px;
}
#table-count ul li a.prev_page,
#table-count ul li a.next_page{
	display:block;
	line-height:23px;
	font-size: 11px;
	color:#333;
	padding-left:5px;
	padding-right:5px;
	border:none;
}
#table-count ul li a.prev_page:hover,
#table-count ul li a.next_page:hover{
	display:block;
	line-height:23px;
	font-size: 11px;
	color:#b51407;
	padding-left:5px;
	padding-right:5px;
	border:none;
}
</style>
<%
	int current =  page.getNumber() + 1;
	int begin = Math.max(1, current - paginationSize/2);
	int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

	request.setAttribute("current", current);
	request.setAttribute("begin", begin);
	request.setAttribute("end", end);
%>
<div>
<table id="table-count" width="98%" border="0" align="left" cellpadding="0" cellspacing="0" >
<tbody>
	<tr>
		<td>
			<ul>
				 <% if (page.hasPrevious()){%>
		               	<li><a class="first_page" thisPage="1">第一頁</a></li>
		                <li><a class="prev_page" thisPage="${current-1}"><img src="<%=request.getContextPath()%>/resources/images/icon_left_arror.gif" width="25" height="25" /></a></li>
		         <%}else{%>
		                <li><a class="first_page" thisPage="1">第一頁</a></li>
		                <li><a class="prev_page"><img src="<%=request.getContextPath()%>/resources/images/icon_left_arror.gif" width="25" height="25" /></a></li>
		         <%} %>
		 
				<c:forEach var="i" begin="${begin}" end="${end}">
		            <c:choose>
		                <c:when test="${i == current}">
		                    <li><a class="selected" thisPage="${i}">${i}</a></li>
		                </c:when>
		                <c:otherwise>
		                    <li><a class="num_page" thisPage="${i}">${i}</a></li>
		                </c:otherwise>
		            </c:choose>
		        </c:forEach>
			  	 <% if (page.hasNext()){%>
		               	<li><a class="next_page" thisPage="${current+1 }"><img src="<%=request.getContextPath()%>/resources/images/icon_right_arror.gif" width="25" height="25" /></a></li>
		                <li><a class="last_page" thisPage="${page.totalPages }">最後一頁</a></li>
		         <%}else{%>
<%-- 		                <li><a class="next_page"><img src="<%=request.getContextPath()%>/resources/images/icon_right_arror.gif" width="25" height="25" /></a></li> --%>
<!-- 		                <li><a class="last_page">最後一頁</a></li> -->
		                <li><a class="next_page" thisPage="${page.totalPages }"><img src="<%=request.getContextPath()%>/resources/images/icon_right_arror.gif" width="25" height="25" /></a></li>
		                <li><a class="last_page" thisPage="${page.totalPages }">最後一頁</a></li>
		         <%} %>
		
			</ul>
			<div class="pn">
	        	<input id="pageTo" type="text" class="textfield" size="3">
	       		<input id="goto" type="reset" class="button-brown" value="頁數">
	       		<input id="totalPage" type="hidden" value="${page.totalPages}">
	      	</div>
		</td>
		<td align="right">總筆數：${page.totalElements}&nbsp;&nbsp;&nbsp;總頁數：${page.totalPages}</td>
	</tr>
</tbody>
</table>
<script type="text/javascript">
	$("#table-count a").click(function() {
		$("#toPage").val($(this).attr("thisPage"));
		$("#queryForm").submit();
	});

	$("#goto").click(function(){
		var totalPage = $("#totalPage").val();
		var pageTo = jQuery.trim($("#pageTo").val());
		if (isNaN(pageTo) || pageTo === '') {
			alert("請輸入正確頁數");
			return false;
		} else {
			totalPage = parseInt(totalPage);
			pageTo = parseInt(pageTo);
		}
		if (pageTo > totalPage) {
			alert("輸入頁數大於查詢範圍");
			return false;
		}
		
		$("#toPage").val(pageTo);
		$("#queryForm").submit();
	});
</script>
