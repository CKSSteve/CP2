str=location.search;
str=str.substring(1,str.length);
var str1 = "待核定";
if(str == str1 ){
 document.form1.status1.value = "核定";
 }
if(str != str1 ){
 document.form1.status1.value = "放行";
 }
