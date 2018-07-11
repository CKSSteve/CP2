<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional //EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=320, target-densitydpi=device-dpi">
<style>
#table01{
	font-family:sans-serif, Geneva,Arial,Helvetica,"新細明體","細明體",sans-serif;
	border-left:1px solid #DEB887;
	border-bottom:2px solid #50CCB1;
	margin:0px 0px 0px 0px;
	color:#333333;
}
#table01 th {
	padding:3px 3px 3px 3px;
	border-right:1px solid #DEB887;
	font-size: 12px;
	font-weight:normal;
	line-height: 160%;
	color: #FFFFFF;
	background-color:#50CCB1;
}
#table01 td{
	padding:3px;
	border-right:1px solid #DEB887;
	border-bottom:1px solid #DEB887;
	font-size: 12px;
	line-height: 160%;
	text-align:center;
}
</style>
</head>

<body>
<div bgcolor="#FFFFFF" marginwidth="0" marginheight="0">
   <span>票券批號 : ${vo.batchNo} 於${vo.returnTime} 被 ${vo.returnPeople} 退回，退回原因 :${vo.rejectReason}，請儘速處理：</span>
</div>

<div bgcolor="#FFFFFF" marginwidth="0" marginheight="0">
<table border="1" cellspacing="0" cellpadding="0" style="width:1000px" id="table01">
<tbody>
   <tr>
    <th>票券批號</th>	
    <th>發行人</th>
    <th>起始日期</th>
    <th>到期日期</th>
    <th>類別</th>
    <th>天期</th>
    <th>幣別</th>
	<th>得標金額</th>
	<th>ALL IN(%)</th>
   </tr>
   <tr>
		<td>
	       ${vo.batchNo}
		</td>
		<td>
		   ${vo.issuerId}&nbsp;${vo.issuerName}
		</td>
		<td>
			${vo.effectDate}		   
		</td>
		<td>
		   ${vo.expiredDate}
		</td>
		<td>
		   	<#if "A" == vo.loanType>
		    	CP2
		    <#elseif "B" == vo.loanType>
		   		短貸
		   	<#else>
		  	
		  	</#if>
		</td>
		<td>
			${vo.days}
		</td>
		<td>
			${vo.currencyName}
		</td>
		<td>
			${vo.loanAmount}&nbsp;${vo.amountUnit}
		</td>
		<td>
			${vo.quationRate}&nbsp;%
		</td>		
   </tr>
</tbody>
</table>
</div>

</body>
</html>
