/**
  * 檢核自行編輯 , 必要欄位
  */
  function checkSELF(form) {
    if(checkValue(form.swift40a)) {
      alert("<40A>為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.swift20)) {
      alert("<20>為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.swift40e)) {
      alert("<40E>為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.swift31d)) {
      alert("<31D>為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.place)) {
      alert("<PLACE>為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.swift50)) {
      alert("<50>為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.swift59)) {
      alert("<59>為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.currency32b)) {
      alert("<32B>CURRENCY為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.amount32b)) {
      alert("<32B>AMOUNT為必填欄位\r\n");
      return false;
    }

    if(checkValue(form.swift41a)) {
      alert("<41a>為必填欄位\r\n");
      return false;
    }

    if(!checkValue(form.swift41a)) {
      if(checkValue(form.swift41aID)) {
        alert("【41a】請選擇【41A】或【41D】\r\n");
        return false;
      }
    }

    if(checkValue(form.swift49)) {
      alert("<49>為必填欄位\r\n");
      return false;
    }
    if(!(form.swift39a.value=='') && !(form.swift39b.value=='')){
      alert("<39A>、<39B>只能擇一填列\r\n");
      form.swift39a.focus();
      form.swift39a.select();
      return false;
    }

    if(!checkValue(form.swift42a)) {
      if(checkValue(form.swift42aID)) {
        alert("【42a】請選擇【42A】或【42D】\r\n");
        return false;
      }
    }

    if(!checkValue(form.swift51a)) {
      if(checkValue(form.swift51aID)) {
        alert("【51a】請選擇【51A】或【51D】\r\n");
        return false;
      }
    }

    if(!checkValue(form.swift53a)) {
      if(checkValue(form.swift53aID)) {
        alert("【53a】請選擇【53A】或【53D】\r\n");
        return false;
      }
    }

    if(!checkValue(form.swift57a)) {
      if(checkValue(form.swift57aID)) {
        alert("【57a】請選擇【57A】或【57B】或【57D】\r\n");
        return false;
      }
    }

    return true;
  }

  function checkFile(form){
    var message ="";
    if(checkValue(form.file)){
      alert("[上傳資料檔]為必要欄位\r\n");
      message+="[上傳資料檔]為必要欄位\r\n";
    }
    return message;
  }

  /**
  * 檢核必填欄位是否有輸入值
  * true : 未填資料 , 返回並focus on element
  * false : pass validation
  */
  function checkValue(obj){
	  
	  //if(obj.disabled==false)return false;
    var focus = obj.value==""?true:false;
    
    if(focus){
    	//alert('objType: '+obj.type+' '+obj.name+' disabled: '+obj.disabled +' focus: '+focus);
      switch(obj.type) {
        case 'select-one':
          obj.focus();
          break;
        default:
          obj.focus();
          obj.select();
      }
    }

    return focus;
  }
