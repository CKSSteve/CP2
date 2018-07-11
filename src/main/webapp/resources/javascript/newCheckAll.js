function CheckAll(name,form)
{
  var list = document.getElementsByName(name);
  var length = list.length;
  for(var i=0;i<length;i++) {
    if(list[i].type=='checkbox') {
      if(form==null || form==list[i].form) {
      list[i].checked = true;
      }
    }
  }
  var all = document.all(name + "All");
  if(all!=null) {
    try {
    all.checked = true;
    }
    catch(e){}
  }
}

function ClearAll(name,form)
{
  var list = document.getElementsByName(name);
  var length = list.length;
  for(var i=0;i<length;i++) {
    if(list[i].type=='checkbox') {
      if(form==null || form==list[i].form) {
        list[i].checked = false;
      }
    }
  }
  var all = document.all(name + "All");
  if(all!=null) {
    try {
    all.checked = false;
    }
    catch(e){}
  }
}

function AllChecked(name,form)
{
  var list = document.getElementsByName(name);
  var length = list.length;
  var all = document.all(name + "All");
  if(all==null) {
    return;
  }
  for(var i=0;i<length;i++) {
    if(list[i].type=='checkbox' &&(form==null || list[i].form==form) && !list[i].checked) {
      try {
      all.checked = false;
      }
      catch(e){}
      return;
    }
  }
  try {
  all.checked = true;
  }
  catch(e){}
  return;
}

function ToggleAll(e,form)
{
    if (e.checked) {
        CheckAll(e.name.substring(0,e.name.length-3),form);
    }
    else {
        ClearAll(e.name.substring(0,e.name.length-3),form);
    }
}

function Select(e)
{
  AllChecked(e.name,e.form);
}
