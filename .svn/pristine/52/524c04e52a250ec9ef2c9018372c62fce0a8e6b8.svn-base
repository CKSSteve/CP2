<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UXB2B-無實體票券系統<sitemesh:write property='title' /></title>
<link href="<%=request.getContextPath()%>/resources/css/index.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/body.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/content.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/table.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/left-menu.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/resources/css/theme.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/resources/css/preload.css" type="text/css" rel="stylesheet"/>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-3.2.1.slim.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/menu.js"></script>
<link href="<%=request.getContextPath()%>/resources/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/jQueryAssets/jquery.ui.datepicker.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jQueryAssets/jquery-1.11.1.min.js" ></script>	
<script src="<%=request.getContextPath()%>/resources/jQueryAssets/jquery.ui-1.10.4.datepicker.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/jQueryAssets/jquery.ui.datepicker-zh-TW.js" type="text/javascript" ></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-accordion-menu.js"></script> --%>
<style>
  dd { margin: 2px; }
  
  #overlay {
    background: #000;
    filter: alpha(opacity=50); /* IE的透明度 */
    opacity: 0.5;  /* 透明度 */
    display: none;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    z-index: 100; /* 此處的圖層要大於頁面 */
    display:none;
	_background-color:#a0a0a0; /* 解決IE6的不透明問題 */
}
#loadingImg{
     width: 150px;
     margin: -75px 0 0 -250px;
/* 	height:150px; */
/*     width: 150px; */
/*     margin: -75px 0 0 -75px; */
    position: absolute;     /*絕對位置*/
    top: 50%;               /*從上面開始算，下推 50% (一半) 的位置*/
    left: 50%;              /*從左邊開始算，右推 50% (一半) 的位置*/
}


	


 </style>
<script language="JavaScript" type="text/JavaScript">
$(function() {

	$('#loader-wrapper').hide();
	hideOverlay();
	menuJs();
	if('<c:out value="${messageCount}" />'>0){	
		alert("您有"+'<c:out value="${messageCount}" />'+"條訊息");	
	}
	$('form').submit(function() {
		showOverlay();
		$('#loader-wrapper').show();
		return true; 
	});
	
	$("#menu-list li").click(function(){
		$("#menu-list li.active").removeClass("active")
		$(this).addClass("active");
	});
// 	$("#jquery-accordion-menu").jqueryAccordionMenu();
});

function noShowLoading(){
	$('#loader-wrapper').hide();
	hideOverlay();
}


function menuJs(){
    $("dd").hide();
    
    //按下dt元素連結
    $("dt a").click(function() {
    	$("dd").slideUp(); //子分頁
    		 
       	//下一個主選單以下的內容要slide down
       	$(this).parent().next().slideDown();
        return false;
    })
	
}

function show_date(){
	  var time=new Date(); //宣告日期物件，儲存目前系統時間
	  t_year=time.getFullYear(); //取得今年年分
	  if(t_year > 2011){
	    document.write(" - " + t_year);
	  }
	}
	
/* 顯示遮罩層 */
function showOverlay() {
    $("#overlay").height(document.body.scrollHeight);
    $("#overlay").width(document.body.scrollWidth);
    // fadeTo第一個參數為速度，第二個為透明度
    // 多重方式控制透明度，保證兼容性，但也帶來修改麻煩的問題
    $("#overlay").fadeTo(200, 0.5);
	// 解決窗口縮小時放大後不全屏遮罩的問題
	// 簡單來說，就是窗口重置的問題
	$(window).resize(function(){
		$("#overlay").height(document.body.scrollHeight);
    	$("#overlay").width(document.body.scrollWidth);  
	}); 
}
/* 隱藏覆蓋層 */
function hideOverlay() {
    $("#overlay").fadeOut(200);
}

/*禁用右鍵*/
$(document).bind("contextmenu", function(event){
	    return false;
	});
</script>
<sitemesh:write property='head' />
<sec:csrfMetaTags />
</head>

<body style="height: 100%">
		<div id="loader-wrapper">
			<div id="loader">
			</div>
<!-- 			<div id="loadingImg"> -->
<%-- 				<img src="<c:url value="/resources/images/work.png"/>"> --%>
<%-- 				<img src="<c:url value="/resources/images/work1.gif"/>" style="width:150px;height:150px"> --%>
<!-- 			</div> -->
		</div>
		<div id="overlay"></div>
	<div id="top">
    <springform:form method="post" id="logout">        
       <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </springform:form>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>		
				<td align="right"><img
					src="<c:url value="/resources/images/header_logo2.jpg"/>"
					width="100%" alt="" /></td>
			</tr>
		</table>
<sec:authentication var="roleId" property="principal.roleId" />
	<div id="menu-wrapper">
		<div class="main-menu-wrapper">
			<div class="main-menu">
				<p>
					<a class="tolist" href="<%=request.getContextPath()%>/todoMain">[&nbsp;&nbsp;待辦事項&nbsp;&nbsp;]</a>
					<a class="tolist" href="#" onClick="window.location.href='<c:out value="${pageContext.request.contextPath}" />/MessageInbox'">[&nbsp;&nbsp;訊息匣&nbsp;&nbsp;]</a>
					<a class="tolist01">[&nbsp;&nbsp;密碼有效剩餘天數:&nbsp;<font color="red"><sec:authentication property="principal.changePwdtime" /></font>&nbsp;天]</a>
					<a class="tolist01">登入者：<sec:authentication property="principal.viewUserName"/></a>
					<a class="logout" href="javascript:formSubmit()">[&nbsp;&nbsp;登出系統&nbsp;&nbsp;]</a>
				</p>

				<ul>
				
					<c:if test="${!empty functionA[roleId]}">
					  <li class="m1"><a href="#">標單作業</a></li>	
					</c:if>
					
					<c:if test="${!empty functionB[roleId]}">
						<li class="m2"><a href="#">本票作業</a></li>
					</c:if>
					
					<c:if test="${!empty functionD[roleId]}">
						<li class="m3"><a href="#">管理作業</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		
		<div class="sub-menu-wrapper">
			<div class="sub-menu">
				<c:if test="${!empty functionA[roleId]}">
					<ul class="sub-memu-0">
						<c:forEach items="${functionA[roleId]}" var="functionA">
								<li><a href="<c:url value="${functionA.uri}"/>">
									<c:out value="${functionA.functionName}" />
									</a>
								</li>
							</c:forEach>
					</ul>
				</c:if>
				
				<c:if test="${!empty functionB[roleId]}">
					<ul class="sub-memu-1">
						<c:forEach items="${functionB[roleId]}" var="functionB">
								<li><a href="<c:url value="${functionB.uri}"/>">
									<c:out value="${functionB.functionName}" />
									</a>
								</li>
							</c:forEach>
					</ul>
				</c:if>
				
				<c:if test="${!empty functionD[roleId]}">
					<ul class="sub-memu-2">
						<c:forEach items="${functionD[roleId]}" var="functionD">
								<li>
									<a href="<c:url value="${functionD.uri}"/>">
										<c:out value="${functionD.functionName}" />
									</a>
								</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
		
	<img src="<c:url value="/resources/images/header_item_bg1.jpg"/>" style="width: 100%;height:25px "/>
		
	</div>
	
	<div id="main">
		<sitemesh:write property='body' />
	</div>
	<div id="footer">
		<jsp:useBean id="attr" scope="application"
			class="java.util.jar.Attributes" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="right" style="background:url(<c:url value="/resources/images/bottom_footer.jpg"/> ) no-repeat ;background-size:100%" width="100%" height="30%">			
				   <table>
         				<tr>
         				<td align="left" class="copyright" style="padding:0px 0px 0px 0px" >&copy; 2006<script type="text/javascript" language="javascript">show_date();</script> UXB2B Inc. All Rights Reserved.&nbsp;&nbsp;
            			</td>
         				</tr>
         </table>
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		function formSubmit() {
				if(confirm("是否要登出")){
					  $('#logout').attr('action','<%=request.getContextPath()%>/logout');
					  $('#logout').submit();
				}
			
		}
	</script>
</body>
</html>
