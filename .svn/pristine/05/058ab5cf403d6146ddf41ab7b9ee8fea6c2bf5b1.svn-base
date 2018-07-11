/**
 * 一般表述示
 * 使用 thml id tag 作為判斷是否讀取的依據
 * 搭配 thml title tag 內容作為alert訊息的內容
 * id tag : 單數型態者為非檢核欄位 | 複數型態為必要檢核欄位
 *  empty
 *  literal
 *  literals
 *  integer
 *  integers
 *  numeric
 *  numerics
 *  date
 *  dates
 *  time
 *  times
 *  string
 *  strings
 *  email
 *  emails
 */


/**
 * 空字串
 */
var empty = /^$/;

/**
 * 文數字 | 0 or more ,只有數字及英文
 */
//var literal=/^[\w\d\s/\-()\?.,+{}':\\]*$/;
var literal=/^[^=!\"%&\*<>;]*$/;

/**
 * 文數字 | at least one or more ,只有數字及英文
 */
var literals=/^[\w\d\s\/\-()\?.,+{}\':\\]+$/;

/**
 * 數字 | 0 or more
 */
var integer=/^[0-9]*$/;

/**
 * 數字 |  at least one  or more
 */
var integers=/^[0-9]+$/;

/**
 * 浮點 | 0 or more
 */
var numeric= /^(([0-9]*.[0-9]*)|([0-9]*.[0-9]*)|([0-9]*))/;

/**
 * 浮點 |  at least one  or more
 */
var numerics = /^(([0-9]+.[0-9]*)|([0-9]*.[0-9]+)|([0-9]+))/;

/**
 * 日期	yyyy.MM.dd or yy.MM.dd Non Requie Format
 */
var date = /^(\d{2,4})\.(\d{2})\.(\d{2})$|^$/;

/**
 * 日期	yyyy.MM.dd or yy.MM.dd Requie Format
 */
var dates = /^(\d{2,4})\.(\d{2})\.(\d{2})$/;

/**
 * 西元日期	yyyy.MM.dd Non Requie Format
 */
var adDate = /^(\d{4})\.(\d{2})\.(\d{2})$|^$/;

/**
 * 西元日期	yyyy.MM.dd Requie Format
 */
var adDates = /^(\d{4})\.(\d{2})\.(\d{2})$/;

/**
 * 時間	HH:mm:ss	Non Requie Format
 */
var time = /^(\d{2})\:(\d{2})\:(\d{2})$|^$/;

/**
 * 時間	HH:mm:ss	Require Format
 */
var times = /^(\d{2})\:(\d{2})\:(\d{2})$/;

/**
 * 字串 Word Non Requie Format,包含特殊符號及中文
 */
var string = /^.*$/;

/**
 * 字串 Word Require Format,包含特殊符號及中交
 */
var strings = /^.+$/;

/**
 * 電子郵件 | 0 or 1
 */
var email = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$|^$/;

/**
 * 電子郵件
 */
var emails = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

/**
*檢核 Form 中定義 id 為
*literal, number,numeric ,
*literals,numbers,numerics ,
*swiftX, swiftXs, email,
*emails, time, times,
*string, strings
*/
function checkInputData() {

  var isError = false;


  isError = isString();
  if(isError) return true;

  isError = isStrings();
  if(isError) return true;

  isError = isLiteral();
  if(isError) return true;

  isError = isInteger();
  if(isError) return true;

  isError = isNumeric();
  if(isError) return true;

  isError = isTime();
  if(isError) return true;

  isError = isDate();
  if(isError) return true;
  
  isError = isADDate();
  if(isError) return true;

  isError = isEmail();
  if(isError) return true;

  isError = isLiterals();
  if(isError) return true;

  isError = isIntegers();
  if(isError) return true;

  isError = isNumerics();
  if(isError) return true;

  isError = isTimes();
  if(isError) return true;

  isError = isDates();
  if(isError) return true;
  
  isError = isADDates();
  if(isError) return true;

  isError = isEmails();
  if(isError) return true;

  return false;
}

function setInputIllegal(obj) {
  //obj.scrollIntoView(false);
  //obj.className = 'errortextfield';
  switch(obj.type) {
    case 'select-one':
    obj.focus();
    break;
    default:
    obj.focus();
    obj.select();
  }
}

/**
* 檢核欄位為英,數字型態 , 允許未輸入資料
*/
function isLiteral(){
  form = document.forms[0];
  var elements = form.elements("literal");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("literal");
      var value = trim(element.value);
      var isValidate = literal.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[中、英、數字及SWIFT允許符號 /\-()?.,+{}':\\]");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = literal.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") +"]欄柆之輸入資料必需為[中、英、數字及SWIFT允許符號 /\-()?.,+{}':\\]");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

/**
* 檢核欄位為整數型態 , 允許未輸入資料
*/
function isInteger(){
  form = document.forms[0];
  var elements = form.elements("integer");

  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("integer");
      var value = trim(element.value);
      if(value.length >0){
        if((isNaN(value)) || (value.indexOf(".")!=-1)){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[整數]");
          flag = true;
        }
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        if(value.length > 0){
          if((isNaN(value)) || (value.indexOf(".")!=-1)){

            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[整數]");
            flag = true;
            break;
          }
        }
      }
    }
  }
  return flag;
}

/**
* 檢核欄位為整數加小數點型態 , 允許未輸入資料
*/
function isNumeric(){
  form = document.forms[0];
  var elements = form.elements("numeric");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("numeric");
      var value = trim(element.value);
      if(value.length>0){
        if(isNaN(value)){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[整數]及[小數點]位數");
          flag = true;
        }
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        if(value.length>0){
          if(isNaN(value)){

            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[整數]及[小數點]位數");
            flag = true;
            break;
          }
        }
      }
    }
  }
  return flag;
}

/**
* 檢核欄位為英,數字型態 , 至少輸入一個英文或數字資料
*/
function isLiterals(){
  var form = document.forms[0];
  var elements = form.elements("literals");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("literals");
      var value = trim(element.value);
      var isValidate = literals.test(value);
      if(!isValidate){

        setInputIllegal(element);
       alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? '['+element.title.replace(/^(.*);(.*)$/, '$1')+']' : "游標") + "]欄柆之輸入資料至少[一個字元]，且必需為[英、數字,不包含(中文)]");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = literals.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料至少[一個字元]，且必需為[英、數字,不包含(中文)]");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

/**
* 檢核欄位為整數型態 , 至少輸入一個整數資料
*/
function isIntegers(){
  form = document.forms[0];
  var elements = form.elements("integers");

  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("integers");
      var value = trim(element.value);
      if((isNaN(value)) || (value.indexOf(".")!=-1)|| (value =='')){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料至少[一個字元]，且必需為[整數]");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);

        if((isNaN(value)) || (value.indexOf(".")!=-1) || (value =='') ){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料至少[一個字元]，且必需為[整數]");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

/**
* 檢核欄位為整數加小數型態 , 至少輸入一個整數加小數資料
*/
function isNumerics(){
  form = document.forms[0];
  var elements = form.elements("numerics");

  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("numerics");
      var value = trim(element.value);
      if(isNaN(value)|| (value =='')){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料至少[一個字元]，且必需為[整數]或[小數點]位數");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        if(isNaN(value)|| (value =='')){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料至少[一個字元]，且必需為[整數]或[小數點]位數");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

/**
* swift 內容為X型態 , 允許未輸入資料
*/
function isSwiftX(){
  form = document.forms[0];
  var elements = form.elements("swiftX");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("swiftX");
      var value = trim(element.value);
      var isValidate = swiftX.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之顯示處,格式有誤，\n\n可接受字元為 英、數字及 \/ \- \? \: \( \) \. \, \+ \{ \} \'  等符號，\n\n且請勿輸入全形字型。");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = swiftX.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之顯示處,格式有誤，\n\n可接受字元為 英、數字及 \/ \- \? \: \( \) \. \, \+ \{ \} \'  等符號，\n\n且請勿輸入全形字型。");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

/**
* swift 內容為X型態 , 不允許未輸入資料
*/
function isSwiftXs(){
  form = document.forms[0];
  var elements = form.elements("swiftXs");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("swiftXs");
      var value = trim(element.value);
      var isValidate = swiftXs.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料至少[一個字元]，不能為空白，\n\n可接受字元為 英、數字及 \/ \- \? \: \( \) \. \, \+ \{ \} \'  等符號，\n\n且請勿輸入全形字型。");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = swiftXs.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料至少[一個字元]，不能為空白，\n\n可接受字元為 英、數字及 \/ \- \? \: \( \) \. \, \+ \{ \} \'  等符號，\n\n且請勿輸入全形字型。");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}


//刪除前後空白
function trim(obj) {
  return obj.replace(/^\s+|\s+$/g, "");
}

/**
* 檢核輸入的時間為 HH:mm:ss
*/
function isTime()
{
  form = document.forms[0];
  var elements = form.elements("time");
  var flag = false;
  if(elements != null){
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("time");
      var value = trim(element.value);
      var isValidate = time.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之顯示處,格式有誤，輸入資料必需為[HH:mm:ss]之時間格式。");
        flag = true;
      }
      else {
        if(!empty.test(value)) {
          if(isIllegalTime(value.replace(time, '$1'), value.replace(time, '$2'), value.replace(time, '$3'))) {

            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[HH:mm:ss]之時間格式。");
            flag = true;
          }
        }
      }
    }
    else {
      for(var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = time.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[HH:mm:ss]之時間格式。");
          flag = true;
          break;
        }
        else {
          if(!empty.test(value)) {
            if(isIllegalTime(value.replace(time, '$1'), value.replace(time, '$2'), value.replace(time, '$3'))) {

              setInputIllegal(element);
              alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[HH:mm:ss]之時間格式。");
              flag = true;
              break;
            }
          }
        }
      }
    }
  }

  return flag;
}

function isTimes()
{
  form = document.forms[0];
  var elements = form.elements("times");
  var flag = false;
  if(elements != null){
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("times");
      var value = trim(element.value);
      var isValidate = times.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[HH:mm:ss]之時間格式。");
        flag = true;
      }
      else {
        if(isIllegalTime(value.replace(times, '$1'), value.replace(times, '$2'), value.replace(times, '$3'))) {

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[HH:mm:ss]之時間格式。");
          flag = true;
        }
      }
    }
    else {
      for(var i=0; i < elements.length; i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = times.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[HH:mm:ss]之時間格式。");
          flag = true;
          break;
        }
        else {
          if(isIllegalTime(value.replace(times, '$1'), value.replace(times, '$2'), value.replace(times, '$3'))) {

            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為[HH:mm:ss]之時間格式。");
            flag = true;
            break;
          }
        }
      }
    }
  }

  return flag;
}

function isDate()
{
  form = document.forms[0];
  var elements = form.elements("date");
  var flag = false;
  if(elements != null){
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("date");
      var value = trim(element.value);
      var isValidate = date.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
        flag = true;
      }
      else {
        if(!empty.test(value)) {
          if(isIllegalDate(value.replace(date, '$1'), value.replace(date, '$2'), value.replace(date, '$3'))) {

            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
            flag = true;
          }
        }
      }
    }
    else {
      for(var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = date.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
          flag = true;
          break;
        }
        else {
          if(!empty.test(value)) {
            if(isIllegalDate(value.replace(date, '$1'), value.replace(date, '$2'), value.replace(date, '$3'))) {

              setInputIllegal(element);
              alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
              flag = true;
              break;
            }
          }
        }
      }
    }
  }

  return flag;
}

function isDates()
{
  form = document.forms[0];
  var elements = form.elements("dates");
  var flag = false;
  if(elements != null){
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("dates");
      var value = trim(element.value);
      var isValidate = dates.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
        flag = true;
      }
      else {
        if(!empty.test(value)) {
          if(isIllegalDate(value.replace(dates, '$1'), value.replace(dates, '$2'), value.replace(dates, '$3'))) {

            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
            flag = true;
          }
        }
      }
    }
    else {
      for(var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = dates.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
          flag = true;
          break;
        }
        else {
          if(isIllegalDate(value.replace(dates, '$1'), value.replace(dates, '$2'), value.replace(dates, '$3'))) {

            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01] 或 民國[97.01.01]之日期格式。");
            flag = true;
            break;
          }
        }
      }
    }
  }

  return flag;
}

function isADDate() {
	form = document.forms[0];
	var elements = form.elements("adDate");
	var flag = false;
	if(elements != null){
		var len = elements.length;
		if (len == undefined) {
			var element = document.getElementById("adDate");
			var value = trim(element.value);
			var isValidate = adDate.test(value);
			if(!isValidate) {
				setInputIllegal(element);
				alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
				flag = true;
			} else {
				if(!empty.test(value)) {
					if(isIllegalADDate(value.replace(adDate, '$1'), value.replace(adDate, '$2'), value.replace(adDate, '$3'))) {
						setInputIllegal(element);
						alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
						flag = true;
					}
				}
			}
		} else {
			for(var i = 0; i < elements.length; i++) {
				var element = elements[i];
				var value = trim(element.value);
				var isValidate = adDate.test(value);
				if(!isValidate){
					setInputIllegal(element);
					alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
					flag = true;
					break;
				} else {
					if(!empty.test(value)) {
						if(isIllegalADDate(value.replace(adDate, '$1'), value.replace(adDate, '$2'), value.replace(adDate, '$3'))) {
							setInputIllegal(element);
							alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
							flag = true;
							break;
						}
					}
				}
			}
		}
	}
  return flag;
}

function isADDates() {
  form = document.forms[0];
  var elements = form.elements("adDates");
  var flag = false;
  if(elements != null){
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("adDates");
      var value = trim(element.value);
      var isValidate = adDates.test(value);
      if(!isValidate){
        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
        flag = true;
      }
      else {
        if(!empty.test(value)) {
          if(isIllegalADDate(value.replace(adDates, '$1'), value.replace(adDates, '$2'), value.replace(adDates, '$3'))) {
            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
            flag = true;
          }
        }
      }
    }
    else {
      for(var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = adDates.test(value);
        if(!isValidate){
          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
          flag = true;
          break;
        }
        else {
          if(isIllegalADDate(value.replace(adDates, '$1'), value.replace(adDates, '$2'), value.replace(adDates, '$3'))) {
            setInputIllegal(element);
            alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為西元[1911.01.01]之日期格式。");
            flag = true;
            break;
          }
        }
      }
    }
  }

  return flag;
}

function isIllegalTime(hour, minute, second) {
  if(parseInt(hour, 10) >= 24)	return true;
  if(parseInt(minute, 10) >= 60)	return true;
  if(parseInt(second, 10) >= 60)	return true;

  return false;
}

function isIllegalDate(year, month, day) {
  var dayInMonth;
  var flag = false;
  year = (year.length < 4) ? (parseInt(year, 10) + 1911) : parseInt(year, 10);

  switch (parseInt(month, 10)) {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
    dayInMonth = 31;
    break;
    case 2:
    dayInMonth = ((parseInt(year, 10) % 4 == 0) && ((!(parseInt(year, 10) % 100 == 0)) || (parseInt(year, 10) % 400 == 0))) ? 29 : 28;
    break;
    case 4:
    case 6:
    case 9:
    case 11:
    dayInMonth = 30;
    break;
    default:
    flag = true;
    break;
  }

  if(parseInt(day, 10) < 0 && parseInt(day, 10) > dayInMonth) {
    flag = true;
  }

  return flag;
}


function isIllegalADDate(year, month, day) {
  var dayInMonth;
  var flag = false;
  year = (year.length < 4) ? (parseInt(year, 10) + 1911) : parseInt(year, 10);
  if (parseInt(year, 10) < 1000) {
	  flag = true;
  }
  switch (parseInt(month, 10)) {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
    dayInMonth = 31;
    break;
    case 2:
    dayInMonth = ((parseInt(year, 10) % 4 == 0) && ((!(parseInt(year, 10) % 100 == 0)) || (parseInt(year, 10) % 400 == 0))) ? 29 : 28;
    break;
    case 4:
    case 6:
    case 9:
    case 11:
    dayInMonth = 30;
    break;
    default:
    flag = true;
    break;
  }

  if(parseInt(day, 10) < 0 && parseInt(day, 10) > dayInMonth) {
    flag = true;
  }

  return flag;
}

/**
* 檢核欄位字串 , 允許未輸入資料
*/
function isString(){
  form = document.forms[0];
  var elements = form.elements("string");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("string");
      var value = trim(element.value);
      var isValidate = string.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為字串。");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = string.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需為字串。");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

/**
* 檢核欄位字串  , 至少輸入一個字元字串
*/
function isStrings(){
  var form = document.forms[0];
  var elements = form.elements("strings");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("strings");
      var value = trim(element.value);
      var isValidate = strings.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料不能是空白。");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = strings.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料不能是空白。");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

function isEmail(){
  form = document.forms[0];
  var elements = form.elements("email");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("email");
      var value = trim(element.value);
      var isValidate = email.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需符合 Email 格式。Ex.test@mail.com.tw");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = email.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需符合 Email 格式。Ex.test@mail.bot.com.tw");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

function isEmails(){
  var form = document.forms[0];
  var elements = form.elements("emails");
  var flag = false;
  if(elements !=null){
    // 只有一個定義欄位時
    var len = elements.length;
    if(len == undefined){
      var element = document.getElementById("emails");
      var value = trim(element.value);
      var isValidate = emails.test(value);
      if(!isValidate){

        setInputIllegal(element);
        alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需符合 Email 格式。Ex.test@mail.bot.com.tw");
        flag = true;
      }
    }
    else{
      for(var i=0;i<elements.length;i++) {
        var element = elements[i];
        var value = trim(element.value);
        var isValidate = emails.test(value);
        if(!isValidate){

          setInputIllegal(element);
          alert("["+(element.title.replace(/^(.*);(.*)$/, '$1').length != 0 ? element.title.replace(/^(.*);(.*)$/, '$1') : "游標") + "]欄柆之輸入資料必需符合 Email 格式。Ex.test@mail.bot.com.tw");
          flag = true;
          break;
        }
      }
    }
  }
  return flag;
}

