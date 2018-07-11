<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<html>
<head>
<title>UXB2B-外匯作業系統</title>
<link href="../css/body.css" type="text/css" rel="stylesheet">
<link href="../css/table.css" type="text/css" rel="stylesheet">
<link href="../css/content.css" type="text/css" rel="stylesheet">
<script language=JavaScript src="../javascript/join.js" type=""></script>
<script language=JavaScript src="../javascript/checkAll.js" type=""></script>
</head>

<script type="text/javascript">
function confirm(){
  branchSettingForm.method.value="confirm";
  branchSettingForm.submit();
}

function back(){
  branchSettingForm.method.value="back";
  branchSettingForm.submit();
}


var inputEamil = new Array();

function init(){
  <c:forTokens var="eamils" items="${company.email}" delims="," varStatus="status" >
       inputEamil[<c:out value="${status.index}" />]='<c:out value="${eamils}" />';
  </c:forTokens>
  writeInputEamil();
}

function writeInputEamil(){
  var select = "";
  var email="";
  if(inputEamil.length>0){
    inputEamil.sort();
    select +="<table width=\"200\"  border=\"0\" cellpadding=\"4\" cellspacing=\"1\" id=\"table_none\">";
    for(var i=0 ; i< inputEamil.length ; i++){
      if(i%2==0)
        select+="<tr class=\"cell-gray\">";
      else
        select+="<tr class=\"contant\">";

      select+="<td class=\"contant\">"+inputEamil[i]+"</td></tr>";

       if(i==0)
         email+=inputEamil[i];
       else
         email+=","+inputEamil[i];

    }
    branchSettingForm.email.value = email;
    select+="</table>";
    document.getElementById('inputEmail').innerHTML = select;
  }else{
    document.getElementById('inputEmail').innerHTML = select;
  }
}

</script>

<body id="scroller" onload="init();">
<h1><span class="title-l">分行資料維護-</span>預覽</h1>
<html:form action="/eissuei/BranchSettingAction.do" method="post">
<html:hidden property="companyId" value="${company.companyId}"/>
<html:hidden property="boss" value="${company.boss}"/>
<html:hidden property="chineseName" value="${company.chineseName}"/>
<html:hidden property="englishName" value="${company.englishName}"/>
<html:hidden property="chineseAddr" value="${company.chineseAddr}"/>
<html:hidden property="englishAddr" value="${company.englishAddr}"/>
<html:hidden property="tel" value="${company.tel}"/>
<html:hidden property="fax" value="${company.fax}"/>
<html:hidden property="email" value="${company.email}"/>
<html:hidden property="typeName" value="${company.companyType.typeName}"/>
<html:hidden property="typeId" value="${company.companyType.typeId}"/>
<html:hidden property="branchs" value="${branchs}"/>
<html:hidden property="method" value=""/>

<c:forTokens delims="," items="" >
</c:forTokens>

<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="8"><img src="../images/table/top_left.gif" width="8" height="8" alt=""></td>
    <td style="background:url(../images/table/top_bg.gif) repeat-x"><img src="../images/spacer.gif" width="1" height="1" alt=""></td>
    <td width="8"><img src="../images/table/top_right.gif" width="8" height="8" alt=""></td>
  </tr>
  <tr>
    <td width="8" style="background:url(../images/table/left_bg.gif) repeat-y">&nbsp;</td>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_none">
      <tr>
        <td height="30"><img src="../images/page.gif" border="0" align="absmiddle" alt="">標有<span class="red">*</span>的欄位為必要資料，請正確填寫。</td>
      </tr>
    </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table05">
        <tr>
          <th>分行代碼</th>
          <td><div align="left"><c:out value="${fn:substring(company.companyId,3,7)}" /></div></td>
        </tr>
        <tr>
          <th width="110">負責人</th>
          <td><div align="left"><c:out value="${company.boss}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>分行中文名稱</th>
          <td><div align="left"><c:out value="${company.chineseName}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>分行英文名稱</th>
          <td><div align="left"><c:out value="${company.englishName}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>分行中文地址</th>
          <td><div align="left"><c:out value="${company.chineseAddr}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>分行英文地址</th>
          <td><div align="left"><c:out value="${company.englishAddr}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th width="110">電話</th>
          <td><div align="left"><c:out value="${company.tel}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>傳真</th>
          <td><div align="left"><c:out value="${company.fax}" />&nbsp;</div></td>
        </tr>
        <tr>
          <th>Email</th>
          <td>
            <div align="left">
              <div id="inputEmail"></div>
              &nbsp;
             </div>
          </td>
        </tr>
        <tr>
          <th>*隸屬類別</th>
          <td>
            <div align="left"><c:out value="${company.companyType.typeName}" /><br>
            <c:if test="${company.companyType.typeId == 'ServiceCenter'}">
                    轄下分行<br>
              ${fn:replace(branchs,",","<br>")}
            </c:if>
            </div>
          </td>
        </tr>
      </table>
    </td>
    <td width="8" style="background:url(../images/table/right_bg.gif) repeat-y">&nbsp;</td>
  </tr>
  <tr>
    <td width="8"><img src="../images/table/bottom_left.gif" width="8" height="8" alt=""></td>
    <td style="background:url(../images/table/bottom_bg.gif) repeat-x"><img src="../images/spacer.gif" width="1" height="1" alt=""></td>
    <td width="8"><img src="../images/table/bottom_right.gif" width="8" height="8" alt=""></td>
  </tr>
</table>
<p class="pbtn">
  <input name="cancel" type="button" class="button-brown" onClick="back();" value="回上頁">
  &nbsp;
<input name="Submit22" type="button" class="button-brown" onClick="confirm();" value="確認">
</p>
<p>&nbsp;</p>
</html:form>
</body>
</html>
