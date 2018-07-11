//第1組勾選欄
    function Select1(e)
    {
	if (e.checked) {
	    document.form1.toggleAll.checked = AllChecked();
	}
	else {
	    document.form1.toggleAll.checked = false;
	}
    }
    function Select(e,e2)
    {
		if(e.checked == true && e2.checked == true)
			e2.checked = false;

	Select2CheckAll();
    }
        function Select2CheckAll()
    {
		var verify = document.form1.VERIFY_STATUS;
		var verifyAll = document.form1.checkVerifyAll;
		var reject = document.form1.REJECT_STATUS;
		var rejectAll = document.form1.checRejectkAll;
		var isCheck = false;

		// 若所有VERIFY_STATUS有一個未選取時, checkVerifyAll便取消選取
		for(var i=0; i<verify.length; i++)
            	{
                   if(verify[i].checked == false){
                   	verifyAll.checked = false;
                   	break;
                   } else{
                   	verifyAll.checked = true;
                   }
            	}
            	// 若所有REJECT_STATUS有一個未選取時, checRejectkAll便取消選取
            	for(var i=0; i<reject.length; i++)
            	{
                   if(reject[i].checked == false){
                   	rejectAll.checked = false;
                   	break;
                   }  else{
                   	rejectAll.checked = true;
                   }
            	}


	}
    function ToggleAll(e)
    {
	if (e.checked) {
	    CheckAll();
	}
	else {
	    ClearAll();
	}
    }

    function Check(e)
    {
	e.checked = true;
    }

    function Clear(e)
    {
	e.checked = false;
    }

    function CheckAll()
    {
	var ml = document.form1;
	var len = ml.elements.length;
	for (var i = 0; i < len; i++) {
	    var e = ml.elements[i];
	    if (e.name == "Mid") {
		Check(e);
	    }
	}
	ml.toggleAll.checked = true;
    }

    function ClearAll()
    {
	var ml = document.form1;
	var len = ml.elements.length;
	for (var i = 0; i < len; i++) {
	    var e = ml.elements[i];
	    if (e.name == "Mid") {
		Clear(e);
	    }
	}
	ml.toggleAll.checked = false;
    }

    function AllChecked()
    {
	ml = document.form1;
	len = ml.elements.length;
	for(var i = 0 ; i < len ; i++) {
	    if (ml.elements[i].name == "Mid" && !ml.elements[i].checked) {
		return false;
	    }
	}
	return true;
    }

//第2組勾選欄

   function Select2(e)
    {
	if (e.checked) {
	    document.form1.toggleAll2.checked = AllChecked2();
	}
	else {
	    document.form1.toggleAll2.checked = false;
	}
    }
    function ToggleAll2(e)
    {
	if (e.checked) {
	    CheckAll2();
	}
	else {
	    ClearAll2();
	}
    }

    function CheckAll2()
    {
	var ml = document.form1;
	var len = ml.elements.length;
	for (var i = 0; i < len; i++) {
	    var e = ml.elements[i];
	    if (e.name == "Mid2") {
		Check(e);
	    }
	}
	ml.toggleAll2.checked = true;
    }

    function ClearAll2()
    {
	var ml = document.form1;
	var len = ml.elements.length;
	for (var i = 0; i < len; i++) {
	    var e = ml.elements[i];
	    if (e.name == "Mid2") {
		Clear(e);
	    }
	}
	ml.toggleAll2.checked = false;
    }

    function AllChecked2()
    {
	ml = document.form1;
	len = ml.elements.length;
	for(var i = 0 ; i < len ; i++) {
	    if (ml.elements[i].name == "Mid2" && !ml.elements[i].checked) {
		return false;
	    }
	}
	return true;
    }


//------------- jessica add  --------------------//
function selectAll2(checkAllName, checkboxName)
{

    if(document.all == null)    //nc
    {
        target = document;
    }
    else                        //ie
    {
        target = document.all;
    }

	var flag = checkAllName.checked;
    if(!checkboxName)
    {
        alert("目前無資料可被選取");
		checkAllName.checked = false;
    }
    else
    {
        if(checkboxName.length == null)
        {
            checkboxName.checked = flag;
		}
        else
        {
            for(var i=0; i<checkboxName.length; i++)
            {
                checkboxName[i].checked = flag;
            }
        }
    }
}
//------------- kans add  --------------------//
function selectAll(checkAllName, checkboxName,checkAllName2, checkboxName2)
{

    if(document.all == null)    //nc
    {
        target = document;
    }
    else                        //ie
    {
        target = document.all;
    }

	var flag = checkAllName.checked;

    if(!checkboxName)
    {
        alert("目前無資料可被選取");
		checkAllName.checked = false;
    }
    else
    {
    			if(flag==true)
			checkAllName2.checked = !flag;
        if(checkboxName.length == null)
        {
            checkboxName.checked = flag;
			if(flag==true)
				checkboxName2.checked = !flag;
		}
        else
        {
            for(var i=0; i<checkboxName.length; i++)
            {

                checkboxName[i].checked = flag;
				if(flag==true)
					checkboxName2[i].checked = !flag;
            }
        }
    }
}

//------------- jessica add  on 2006.09.22--------------------//
/**
 * form eissuei
 */
function select_all(formName, elementName, selectAllName) {
var undefined;

	if(document.forms[formName].elements[selectAllName].checked){
       //若只有一個checkbox
		if(document.forms[formName].elements[elementName].length == undefined){
			document.forms[formName].elements[elementName].checked = true;
			return;
		}
		for(var i = 0; i < document.forms[formName].elements[elementName].length; i++) {
			document.forms[formName].elements[elementName][i].checked = true;
		}

   }else{
	   if(document.forms[formName].elements[elementName].length == undefined){
		   document.forms[formName].elements[elementName].checked = false;
		   return;
	   }

	   for(var i = 0; i < document.forms[formName].elements[elementName].length; i++) {
		   document.forms[formName].elements[elementName][i].checked = false;
	   }
   	}
}

function select_one(formName, elementName, selectAllName) {
        var checkCount = 0;
        var undefined;

       //若只有一個checkbox
        if(document.forms[formName].elements[elementName].length == undefined){
           document.forms[formName].elements[selectAllName].checked = document.forms[formName].elements[elementName].checked;
           return;
        }

        for(var i = 0; i < document.forms[formName].elements[elementName].length; i++){
                if(!document.forms[formName].elements[elementName][i].checked)
                        break;
                else
                        checkCount++;
        }
        if(checkCount == document.forms[formName].elements[elementName].length)
                document.forms[formName].elements[selectAllName].checked = true;
        else
                document.forms[formName].elements[selectAllName].checked = false;
}

// 清除form資料
function resetForm(form) {
	for(var i = 0; i < form.length; i++){
		var element = form.elements[i];
		
		if(element.type == 'text'){
			element.value = '';
		}
		if(element.type == 'textarea'){
			element.value = '';
		}
		if(element.type == 'select-one'){
			element.value = element.options[0].value;
		}
		if(element.type == 'select-multiple'){
			element.value = element.options[0].value;
		}
		if(element.type == 'checkbox'){
			Clear(element);
		}
	}

}
