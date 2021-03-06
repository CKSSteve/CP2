<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.security.Principal" %>
<html>
<head>
	<title>UXB2B-無實體票券系統</title>
	<link href="<%=request.getContextPath()%>/resources/css/login.css" type="text/css" rel="stylesheet" />
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-3.2.1.slim.js"></script> --%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
  $(function() {
	  $("#login").click(function(){
		  if($('input[name="userId"]').val().length<=0||$('input[name="password"]').val().length<=0){
			  alert("帳號跟密碼皆須輸入");
		  }else{
		  	var userIdAndPassword=$('input[name="userId"]').val()+"_"+$('input[name="password"]').val();
		  	$('input[name="userIdAndPassword"]').val(userIdAndPassword);
			$("#queryForm").attr("action", '<c:url value="/security/check"/>');
			$("#queryForm").submit();
		  }
		});
	  
	  setTimeout("location.reload()",1740000);
	  
	  /*在密碼欄位按enter=登入*/
	  $('input[name="password"]').keyup(function () {
		  	/*firefox沒有event*/
		  var event = window.event ||arguments.callee.caller.arguments[0];
	      if (event.keyCode == 13||event.keyCode == 108) {
		  if($('input[name="userId"]').val().length<=0||$('input[name="password"]').val().length<=0){
			  alert("帳號跟密碼皆須輸入");
		  }else{
		  	
	        var userIdAndPassword=$('input[name="userId"]').val()+"_"+$('input[name="password"]').val();
	   		$('input[name="userIdAndPassword"]').val(userIdAndPassword);
	   		$("#queryForm").attr("action", '<c:url value="/security/check"/>');
	   		$("#queryForm").submit();	        
		  }
	     }
	    });
	  
	  /*在帳號欄位按enter=登入*/
	  $('input[name="userId"]').keyup(function () {
		  	/*firefox沒有event*/
		  var event = window.event ||arguments.callee.caller.arguments[0];
	      if (event.keyCode == 13||event.keyCode == 108) {
		  if($('input[name="userId"]').val().length<=0||$('input[name="password"]').val().length<=0){
			  alert("帳號跟密碼皆須輸入");
		  }else{
		  	
	        var userIdAndPassword=$('input[name="userId"]').val()+"_"+$('input[name="password"]').val();
	   		$('input[name="userIdAndPassword"]').val(userIdAndPassword);
	   		$("#queryForm").attr("action", '<c:url value="/security/check"/>');
	   		$("#queryForm").submit();	        
		  }
	     }
	    });

	  
  })
  
  
  function show_date(){
    var time=new Date(); //宣告日期物件，儲存目前系統時間
    t_year=time.getFullYear(); //取得今年年分
    if(t_year > 2007){
      document.write(" - " + t_year);
    }
  }
  if(window.self != window.parent) {
	window.parent.location="<%=request.getContextPath()%>/login.jsp";
  }
  
  (function (global) { 

	    if(typeof (global) === "undefined") {
	        throw new Error("window is undefined");
	    }

	    var _hash = "!";
	    var noBackPlease = function () {
	        global.location.href += "#";

	        // making sure we have the fruit available for juice (^__^)
	        global.setTimeout(function () {
	            global.location.href += "!";
	        }, 50);
	    };

	    global.onhashchange = function () {
	        if (global.location.hash !== _hash) {
	            global.location.hash = _hash;
	        }
	    };

	    global.onload = function () {            
	        noBackPlease();

	        // disables backspace on page except on input fields and textarea..
	        document.body.onkeydown = function (e) {
	            var elm = e.target.nodeName.toLowerCase();
	            if (e.which === 8 && (elm !== 'input' && elm  !== 'textarea')) {
	                e.preventDefault();
	            }
	            // stopping event bubbling up the DOM tree..
	            e.stopPropagation();
	        };          
	    }

	})(window);
</script>

</head>


<%--先拿掉 <jsp:useBean id="attr" scope="application" class="java.util.jar.Attributes" /> --%>
<body>
<form:form name="queryForm" id="queryForm" method="POST">
 	<div align="center" style="margin:10% 10% 10% 10% " >
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><img src="<c:url value="/resources/images/index_01.png"/>" width="434" height="315" alt=""></td>
          <td><img src="<c:url value="/resources/images/index_02.png"/>" width="434" height="315" alt=""></td>
        </tr>
        <tr>
          <td  style="background:url(<c:url value="/resources/images/index_03.png"/> ) no-repeat" width="434" height="315" >
         <table>
         	<tr>
         	<td align="left" class="copyrightBlack" style="padding:180px 0px 0px 60px" >&copy; 2006<script type="text/javascript" language="javascript">show_date();</script> UXB2B Inc. All Rights Reserved.
            </td>
         	</tr>
         </table>
          </td>
          
            <td style="background:url(<c:url value="/resources/images/index_04.png"/> ) no-repeat" width="434" height="315" >
              <table width="226" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td></td>
                </tr>
                <tr>
                  <td height="100" align="left" valign="top">
                    <table border="0" cellspacing="0" cellpadding="4" id="logintable"  style="padding:0px 0px 50px 70px" >
                      <tr >
                        <td>帳號</td>
                        <td><input name="userId" type="text" class="inputtxt" size="10" ></td>
                      </tr>
                      <tr>
                        <td>密碼</td>
                        <td><input name="password" type="password" class="inputtxt" size="10" ></td>
                        </tr>
                        <tr>
                        <td align="right" colspan="2">
<!--                        <input name="送出" type="button" class="loginbtn" value="登入" id="login" > -->
							<img src="<c:url value="/resources/images/login_bt.png"/>" name="送出" id="login">
                        </td>
                      </tr>
                    </table>
                   </td>
                </tr>
              </table>
            </td>
        </tr>
<!--         <tr> -->
<%--           <td><img src="<c:url value="/resources/images/index_05.png"/>" alt=""></td> --%>
<!--             <td align="right" class="copyright" style="padding:5px 10px 0 0">&copy; 2006<script type="text/javascript" language="javascript">show_date();</script> UXB2B.com All Rights Reserved -->
<!--             </td> -->
<!--         </tr> -->
       
       </table> 
    </div>

  <input name="userIdAndPassword" type="hidden"/>
<<<<<<< .mine
  <input name="lockOn" type="hidden"/>
||||||| .r108
=======
  <input name="mandatoryLogin" type="hidden"/>
>>>>>>> .r212
  <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form:form>
  	      	
  <c:if test="${!empty errorMsg}">
    <script type="text/javascript">
    alert('<c:out value="${errorMsg}" />');
    </script>
   </c:if>
  <c:if test="${!empty showMessage}">
    <script type="text/javascript">
    alert('<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />');
    </script>
  </c:if>
  
    <c:if test="${!empty sessiontimeout}">
    <script type="text/javascript">
    alert("畫面停滯時間過久或被其他使用者強制登出");
    </script>
  </c:if>


<c:if test="${!empty duplicate}">
  <script type="text/javascript">
<<<<<<< .mine
  if(confirm("帳號已被登陸，是否把那個87踢掉")){
	  $('input[name="lockOn"]').val("true");
  }else{
	  $('input[name="lockOn"]').val("false");
||||||| .r108
  if(confirm('<c:out value="${duplicate}" />')){
    loginForm.userId.value='<c:out value="${loginForm.userId}" />';
    loginForm.method.value="relogin";
    loginForm.submit();
=======
  if(confirm("使用者"+'<c:out value="${duplicate}" />'+"重覆登入是否強制登出")){
	  $('input[name="mandatoryLogin"]').val("true");
  }else{
	  $('input[name="mandatoryLogin"]').val("false");
>>>>>>> .r212
  }
	$("#queryForm").attr("action", '<c:url value="/login/loginDuplicate"/>');
	$("#queryForm").submit();
  </script>
  </c:if>
 
  </body>
</html>
