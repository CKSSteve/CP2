<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/javascript/join.js"></script>
<script type="text/javascript">
$(function() {
	init();
	$("#modifyBranchPreview").hide();
})
function preview(){
	bossValue=$('input[name="boss"]').val();
	chineseNameValue=$('input[name="chineseName"]').val();
	englishNameValue=$('input[name="englishName"]').val();
	chineseAddrValue=$('input[name="chineseAddr"]').val();
	englishAddrValue=$('input[name="englishAddr"]').val();
	telValue=$('input[name="tel"]').val();
	faxValue=$('input[name="fax"]').val();
	typeIdValue=$.trim($('#typeId :selected').text());
	EmailValue=$('input[name="email"]').val();
	BranchValue=$('input[name="branchs"]').val();


	
    if(chineseNameValue.length<1){
    	alert("[分行中文名稱]為必要欄位");
    	$('input[name="chineseName"]').focus();
   		 return false;
  }
  else{
	  $('#bossPreview').html(bossValue);
	  $('#chineseNamePreview').html(chineseNameValue);
	  $('#englishNamePreview').html(englishNameValue);
	  $('#chineseAddrPreview').html(chineseAddrValue);
	  $('#englishAddrPreview').html(englishAddrValue);
	  $('#telPreview').html(telValue);
	  $('#faxPreview').html(faxValue);
	  $('#typeIdPreview').html(typeIdValue);
	  $('#inputEmailPreview').html(EmailValue.replace(/\,/g,"</br>"));
	  if(BranchValue.length>=1){
	  	$('#inputBranchPreview').html("<hr noshade>轄下分行<ul><li>"+BranchValue.replace(/\,/g,"</li><li>")+"</li></ul>");
	  }
	  
	  $("#modifyBranch").hide();
	  $("#modifyBranchPreview").show();
  }
}


function modifyBack(){
	  $("#modifyBranchPreview").hide();
	  $("#modifyBranch").show();
}

function backUpdate(){
	$("#modifyBranchForm").attr("action", '<c:url value="/branchMaintain/updateBranch"/>')
	$("#modifyBranchForm").submit();
	
}

var inputEamilArrays = new Array();
var inputBranchArrays = new Array();

function init(){
  <c:forTokens var="eamils" items="${branch.email}" delims="," varStatus="status" >
  	inputEamilArrays[<c:out value="${status.index}" />]='<c:out value="${eamils}" />';
  </c:forTokens>
  writeInputEamil();
  <c:forEach var="serviceCenterMapping" items="${branch.serviceCenterMapping}" varStatus="status">
 	 inputBranchArrays[<c:out value="${status.index}" />]='<c:out value="${fn:substring(serviceCenterMapping.oneBranch.branchId,3,7)}" />'+" "+'<c:out value="${serviceCenterMapping.oneBranch.chineseName}" />';
  </c:forEach>
  <c:if test="${branch.branchType.typeId =='ServiceCenter'}">
   	changeCompanyType("ServiceCenter");
  </c:if>


  writeBranchHtml();

}

/**
新增-分行
*/
function addEmail(){
  var value = $('input[name="emailInputByUser"]').val();
  var index =  inputEamilArrays.length;
  var textvalue = value;
  var isSelected = false;

  if(checkEmail(value)){
    for(var i=0 ; i< index ;i++){
      if(inputEamilArrays[i] == textvalue){
        isSelected = true;
        break;
      }
    }

    if(isSelected){
      alert("email ["+textvalue+"] 已存在列表清單中 ");
      return false;
    }else{
      inputEamilArrays[index]=textvalue;
      writeInputEamil();
    }
  }else{
    alert("email ["+textvalue+"] 不符合格式規範 ");
  }
  $('input[name="emailInputByUser"]').val("");
}
/**
寫入分行html
*/
function writeInputEamil(){
  var select = "";
  var email="";
  if(inputEamilArrays.length>0){
    inputEamilArrays.sort();
    select +="<table width=\"200\"  border=\"0\" cellpadding=\"4\" cellspacing=\"1\" id=\"table_none\">";
    for(var i=0 ; i< inputEamilArrays.length ; i++){
      if(i%2==0)
      select+="<tr class=\"cell-gray\">";
      else
      select+="<tr class=\"contant\">";

      select+="<td class=\"contant\">"+inputEamilArrays[i]+"</td>";
      select+="<td align=\"left\">"
      +"<a href=\"#\" onclick=\"javascript:deleteEmail("+i+")\" >"
      +"<img src=\"<%=request.getContextPath()%>/resources/images/del.gif\" border=\"0\" title=\"刪除\">"
      +"</a>"
      +"</td></tr>";

      if(i==0)
      email+=inputEamilArrays[i];
      else
      email+=","+inputEamilArrays[i];

    }
    $('input[name="email"]').val(email);
    select+="</table>";
    document.getElementById('inputEmail').innerHTML = select;
  }else{
	  $('input[name="email"]').val(email);
    document.getElementById('inputEmail').innerHTML = select;
  }
}

function deleteEmail(index){
  inputEamilArrays.splice(index,1);
  writeInputEamil();
}

//選單
function changeCompanyType(x){
  if(x == "ServiceCenter"){
    branch.style.display="block";
  }else{
    //順便清空資料
    branch.style.display="none";
    inputBranchArrays = new Array();
    writeBranchHtml();
  }

}

function addBranch(){
  var text = $.trim($('#seriveCenterSelected :selected').text());
  var value = $.trim($('#seriveCenterSelected').val());
  var isSelected = false;
  var valuetext = value.substring(3)+" "+text;
  var index =  inputBranchArrays.length;

  for(var i=0 ; i< index ;i++){
    if(inputBranchArrays[i] == valuetext){
      isSelected = true;
      break;
    }
  }

  if(!isSelected){
    inputBranchArrays[index] = valuetext;
//     $("#seriveCenterSelected").find(":selected").remove();
    writeBranchHtml();
  }else{
    alert(valuetext +" 已存在列表清單中");
  }
}

function writeBranchHtml(){
  inputBranchArrays.sort();
  var select = "";
  var branchs = "";
  select+="轄下分行<br>";
  select +="<table width=\"200\"  border=\"0\" cellpadding=\"4\" cellspacing=\"1\" id=\"table_none\">";
  for(var i=0 ; i< inputBranchArrays.length ; i++){
    if(i%2==0)
    select+="<tr class=\"cell-gray\">";
    else
    select+="<tr class=\"contant\">";

    select+="<td align=\"left\" class=\"contant\">"+inputBranchArrays[i]+"</td>"
    select+="<td align=\"left\">"
    +"<a href=\"#\" onclick=\"javascript:deleteBranch("+i+")\" >"
    +"<img src=\"<%=request.getContextPath()%>/resources/images/del.gif\" border=\"0\" title=\"刪除\">"
    +"</a>"
    +"</td></tr>";

    if(i==0)
    branchs+=inputBranchArrays[i];
    else
    branchs+=","+inputBranchArrays[i];

  }
  $('input[name="branchs"]').val(branchs);
  select+="</table>";
  document.getElementById('inputBranch').innerHTML = select;
}

function deleteBranch(index){

//  var bankCode='<c:out value="${fn:substring(branch.branchId,0,3)}"/>';
//  var inputBranchArraysNew=inputBranchArrays[index].split(" ");
//   $("#seriveCenterSelected").append($("<option></option>").attr("value", bankCode+inputBranchArraysNew[0]).text(inputBranchArraysNew[1]));
  inputBranchArrays.splice(index,1);

  writeBranchHtml();
}

</script>
</head>
<body id="scroller" >
<div id="modifyBranch">
<h1><span class="title-l">分行資料維護-</span>修改分行</h1>
<form:form name="modifyBranchForm" id="modifyBranchForm" method="post">
  <input type="hidden" name="email" value=""/>
  <input type="hidden" name="branchs" value=""/>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
          <tr>
            <td height="30">
              <img src="<c:url value="/resources/images/page.gif"/>" border="0" align="absmiddle" alt="">標有<span class="red">*</span>的欄位為必要資料，請正確填寫。
            </td>
          </tr>
        </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
          <tr>
            <th>分行代碼</th>
            <td>
              <div align="left"><c:out value="${fn:substring(branch.branchId,3,fn:length(branch.branchId))}" /></div>
              <input type="hidden" name="branchId" value="${branch.branchId}"/>
            </td>
          </tr>
          <tr>
            <th width="110">負責人</th>
            <td>
              <div align="left">
                <input type="text" name="boss" class="textfield" maxlength="30" size="40" value="${branch.boss}"/>
              </div>
            </td>
          </tr>
          <tr>
            <th>分行中文名稱</th>
            <td>
              <div align="left">
                <input type="text" name="chineseName" class="textfield" maxlength="30" size="60" value="${branch.chineseName}"/>
              </div>
            </td>
          </tr>
          <tr>
            <th>分行英文名稱</th>
            <td><div align="left">
              <input type="text" name="englishName" class="textfield" maxlength="60" size="60" value="${branch.englishName}"/>
</div></td>
          </tr>
          <tr>
            <th>分行中文地址</th>
            <td>
              <div align="left">
                <input type="text" name="chineseAddr" class="textfield" maxlength="60" size="60" value="${branch.chineseAddr}"/>
              </div>
            </td>
          </tr>
          <tr>
            <th>分行英文地址</th>
            <td>
              <div align="left">
                <input type="text" name="englishAddr" class="textfield" maxlength="100" size="100" value="${branch.englishAddr}"/>
              </div>
            </td>
          </tr>
          <tr>
            <th width="110">電話</th>
            <td>
              <div align="left">
                <input type="text" name="tel" class="textfield" maxlength="30" size="40" value="${branch.tel}"/>
              </div>
            </td>
          </tr>
          <tr>
            <th>傳真</th>
            <td>
              <div align="left">
                <input type="text" name="fax" class="textfield" maxlength="30" size="40" value="${branch.fax}"/>
              </div>
            </td>
          </tr>
          <tr>
            <th>Email</th>
            <td>
              <div align="left">
                <input name="emailInputByUser" type="text" maxlength="50" size="50" class="textfield"/>
                <input name="B3" type="button" class="button-brown" value="新增電子郵件" onclick="addEmail();">
              </div>
              <hr noshade>
                <div align="left" id="inputEmail">
                </div>
            </td>
          </tr>
          <tr>
            <th><span class="red">＊&nbsp;</span>隸屬類別</th>
            <td>
              <div align="left">
                <select name="typeId" id="typeId" onchange="changeCompanyType(this.value);">
                  <c:forEach var="branchType" items="${branchTypeList}">
                    <c:choose>
                      <c:when test="${branchType.typeId==branch.branchType.typeId}">
                        <option value="<c:out value="${branchType.typeId}" />" selected="selected">
                        	<c:out value="${branchType.typeName}" />
                        </option>
                      </c:when>
                      <c:otherwise>
                        <option value="<c:out value="${branchType.typeId}" />">
                        	<c:out value="${branchType.typeName}" />
                        </option>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                </select>
              </div>
              <div align="left" id="branch" style="display:none;">
                <select name="seriveCenterSelected" id="seriveCenterSelected" class="textfield">
                  <c:forEach items="${noCenterbranch}" var="noCenterbranch">
                   <c:if test="${noCenterbranch.branchId!=branch.branchId}">
                      <option value="<c:out value="${noCenterbranch.branchId}" />">
                      	<c:out value="${noCenterbranch.chineseName}" />
                      </option>
                     </c:if> 
                  </c:forEach>
                </select>
                <input name="B32" type="button" class="button-brown" value="新增轄下分行" onclick="addBranch();">
                <hr noshade>
                  <div align="left" id="inputBranch">
                  </div>
              </div>

            </td>
          </tr>
        </table>
            </td>
    </tr>
  </table>
  <p class="pbtn">
    <input name="cancel" type="button" class="button-brown" onClick="javascript:location.href='<%=request.getContextPath()%>/branchMaintain'" value="回上頁">
    &nbsp;
    <input name="button" type="button" class="button-brown" onClick="preview();" value="預覽">
  </p>
  <p>&nbsp;</p>

</form:form>
</div>
<div id="modifyBranchPreview">
<h1><span class="title-l">分行資料維護-</span>預覽</h1>
<form:form name="modifyBranchPreview" id="modifyBranchPreview" method="post">


<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
      <tr>
        <td height="30"><img src="<c:url value="/resources/images/page.gif"/>" border="0" align="absmiddle" alt="">標有<span class="red">*</span>的欄位為必要資料，請正確填寫。</td>
      </tr>
    </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th>分行代碼</th>
          <td> <div align="left"><c:out value="${fn:substring(branch.branchId,3,fn:length(branch.branchId))}" /></div></td>
        </tr>
        <tr>
          <th width="110">負責人</th>
          <td><div align="left" id="bossPreview"></div></td>
        </tr>
        <tr>
          <th>分行中文名稱</th>
          <td><div align="left" id="chineseNamePreview"></div></td>
        </tr>
        <tr>
          <th>分行英文名稱</th>
          <td><div align="left" id="englishNamePreview"></div></td>
        </tr>
        <tr>
          <th>分行中文地址</th>
          <td><div align="left" id="chineseAddrPreview"></div></td>
        </tr>
        <tr>
          <th>分行英文地址</th>
          <td><div align="left" id="englishAddrPreview"></div></td>
        </tr>
        <tr>
          <th width="110">電話</th>
          <td><div align="left" id="telPreview"></div></td>
        </tr>
        <tr>
          <th>傳真</th>
          <td><div align="left" id="faxPreview"></div></td>
        </tr>
        <tr>
          <th>Email</th>
          <td>
            <div align="left">
              <div id="inputEmailPreview"></div>
             </div>
          </td>
        </tr>
        <tr>
          <th><span class="red">＊&nbsp;</span>隸屬類別</th>
          <td>
            <div align="left" id="typeIdPreview"></div>
            <div align="left" id="inputBranchPreview"></div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<p class="pbtn">
  <input name="cancel" type="button" class="button-brown" onClick="modifyBack();" value="回上頁">
  &nbsp;
<input name="Submit22" type="button" class="button-brown" onClick="backUpdate();" value="確認">
</p>
<p>&nbsp;</p>
</form:form>
</div>
</body>
</html>
