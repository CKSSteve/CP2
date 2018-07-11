String.prototype.trim = function() {
  return this.replace(/(^\s*)|(\s*$)/g, "");
};

String.prototype.isFloat = function() {
  return /^\d+\.?\d*$/.test(this);
};

String.prototype.isInteger = function() {
  return /^\d+$/.test(this);
};

String.prototype.toNumber = function() {
  	return Number(this);
};

String.prototype.isUpperCase = function() {
	return /^[A-Z]*$/.test(this);
};

String.prototype.isLowerCase = function() {
	return /^[a-z]*$/.test(this);
};


String.prototype.isEnglish = function() {
	return /^[A-Za-z]*$/.test(this);
};

String.prototype.isNumber = function() {
	return /^\d+$/.test(this);
};

String.prototype.format=function() {
  if(arguments.length==0) {
    return this;
  }

  var newString = this;
  for(var i=0;i<arguments.length;i++) {
    var regex = new RegExp("%\\{" + (i+1) + "\\}","g");
    newString = newString.replace(regex,arguments[i]);
  }

  return newString;
};

String.prototype.formatByForm=function(form) {
  if(form==null) {
    return this;
  }
  var newString = this;
  //var re = /%\{(\w+)\}/g;
  var re = /%\{([^\}]*)\}/g;
  var arr = null;
  while((arr = re.exec(this))!=null) {
    var name = RegExp.$1.trim();
    var value = null;
    try {
      value = eval("form." + name);
    }
    catch(e){}
    if(value!=null && typeof(value)!="undefined") {
      if(typeof(value)!="string") {
        value = value.value;
      }
    }
    else {
      value = "";
    }
    newString = newString.replace(arr[0],value);
  }
  return newString;
};

String.prototype.eval=function() {
  var newString = this;
  var re = /%\{([^\}]*)\}/g;
  var arr = null;
  while((arr = re.exec(this))!=null) {
    var expression = RegExp.$1;
    var value = "";
    try {
      value = eval(expression);
      if(typeof(value)=='undefined') {
        value = "";
      }
    }
    catch(e){}
    newString = newString.replace(arr[0],value);
  }
  return newString;
};

Date.prototype.addDays = function(days) {
	if(days==null) {
		return this;
	}
	return this.addHours(24 * days);
};

Date.prototype.addHours = function(hours) {
	if(hours==null) {
		return this;
	}
	return this.addMinutes(60 * hours);
};

Date.prototype.addMinutes = function(minutes) {
	if(minutes==null) {
		return this;
	}
	return this.addSeconds(60 * minutes);
};

Date.prototype.addSeconds = function(seconds) {
	if(seconds==null) {
		return this;
	}
	var time = this.getTime();
	var oneSecond = 1000;
	time += seconds * oneSecond;
	this.setTime(time);
	return this;
};

Date.prototype.formatDate = function(pattern) {
	return formatDate(this,pattern);
};

Date.prototype.format = function(pattern) {
	return formatDate(this,pattern);
};

Date.prototype.formatChineseDate = function(pattern) {
	return formatChineseDate(this,pattern);
};

Date.prototype.chineseFormat = function(pattern) {
	return formatChineseDate(this,pattern);
};

function referPosition(ref,dest) {
  var left = realLeft(ref);
  var top = realTop(ref);
  dest.style.left=left;
  dest.style.top=top;
}

function realLeft(obj) {
	var left = 0;
	for(;obj && obj.tagName.toUpperCase() != "BODY";obj=obj.offsetParent) {
		left += obj.offsetLeft;
	}
	return left;
}

function realRight(obj) {
	var right = obj.offsetWidth;
	for(;obj && obj.tagName.toUpperCase() != "BODY";obj=obj.offsetParent) {
		right += obj.offsetLeft;
	}
	return right;
}

function realBottom(obj) {
	var bottom = obj.offsetHeight;
	for(;obj && obj.tagName.toUpperCase() != "BODY";obj=obj.offsetParent) {
		bottom += obj.offsetTop;
	}
	return bottom;
}

function realTop(obj) {
	var top = 0;
	for(;obj && obj.tagName.toUpperCase() != "BODY";obj=obj.offsetParent) {
		top += obj.offsetTop;
	}
	return top;
}

window.sendForm = function(form,callback) {
	if(form==null) {
		form = document.forms[0];
	}
	var elements = form.elements;
	var param = "";
	for(var i=0;i<elements.length;i++) {
		var element = elements[i];
		switch(element.tagName) {
			case "INPUT":
				switch(element.type) {
					case "text":
					case "hidden":
					case "password":
						param += element.name + "=" + element.value + "&";
						break;
					case "radio":
					case "checkbox":
						if(element.checked) {
							param += element.name + "=" + element.value + "&";
						}
						break;
				}
				break;
			case "TEXTAREA":
					param += element.name + "=" + element.value + "&";
					break;
			case "SELECT":
					if(element.selectedIndex!=-1) {
						param += element.name + "=" + element.options(element.selectedIndex).value + "&";
					}
		}
	}
	window.httpRequest(form.action,param,callback);
};

function onKeyPressNumberOnly() {
	var key = event.keyCode;
	if(key<0x30 || key>0x39) {
		event.returnValue = false;
	}
}

function onChangeComboSelect(dest) {
    var src = window.event.srcElement;
    if(src.selectedIndex!=-1) {
      var value = src.options[src.selectedIndex].value;
      var options = dest.options;
      for(var i=0;i<options.length;i++) {
        if(options[i].value == value) {
          dest.selectedIndex = i;
          return;
        }
      }
    }
    dest.selectedIndex = -1;
}

function onChangeMultiSelect() {
	var src = window.event.srcElement;
	var destKeyName = src.getAttribute("destKey");
	var destName = src.getAttribute("dest");
	var keyName = src.getAttribute("key");

	for(var destIndex=0;destName!=null;destIndex++) {
	var dest = document.all[destName];
	var options;
	var key;

	if(dest==null) {
		break;
	}

	if(eval("src." + destName + "==null")) {
		eval("src." + destName + " = new Array(dest.options.length)");
		options = eval("src." + destName);
		for(var i=0;i<dest.options.length;i++) {
			options[i] = dest.options.item(i);
		}
	}
	else {
		options = eval("src." + destName);
	}

	dest.options.length = 0;

	if(src.selectedIndex>-1) {
		if(keyName==null) {
			key = src.options[src.selectedIndex].value;
		}
		else {
			key = src.options[src.selectedIndex].getAttribute(keyName);
		}
		for(var i=0;i<options.length;i++) {
			if(options[i].getAttribute(destKeyName)==key || options[i].getAttribute("alwaysDisplay")=="true") {
				dest.options.add(options[i]);
			}
		}
	}

	dest.fireEvent("onchange");

	destName = src.getAttribute("dest" + destIndex);
  }
}


function initChangeComboSelect() {
    for(var i=0;i<document.forms.length;i++) {
        var form = document.forms[i];
        for(var j=0;j<form.elements.length;j++) {
            var item = form.elements(j);
            if(item.onchange!=null && item.onchange.toString().indexOf("onChangeComboSelect")>-1) {
                item.fireEvent("onchange");
            }
            if(item.onchange!=null && item.onchange.toString().indexOf("onChangeMultiSelect")>-1) {
                item.fireEvent("onchange");
            }
        }
    }
}

function formatNumber(number,pattern) {
}

function initDocument() {
    if(!(document.readyState=="complete")) {
        window.setTimeout(initDocument,500);
        return;
    }
    initChangeComboSelect();
}

function formatInteger(nNumber,length) {
  var numberText = parseInt(nNumber).toString();

  if(numberText.length>=length) {
    return numberText.slice(numberText.length-length);
  }
  while(length > numberText.length) {
    numberText = "0" + numberText;
  }
  return numberText;
}

function formatDate(vDate, vFormat){
  var vDay = formatInteger(vDate.getDate(),2);
  var vMonth = formatInteger(vDate.getMonth()+1,2);
  var vYearLong = formatInteger(vDate.getFullYear(),4);
  var vYearShort = formatInteger(vDate.getFullYear(),2);
  var vYear = (vFormat.indexOf("yyyy")>-1?vYearLong:vYearShort);
  var vHour = formatInteger(vDate.getHours(),2);
  var vMinute = formatInteger(vDate.getMinutes(),2);
  var vSecond = formatInteger(vDate.getSeconds(),2);
  var vDateString = vFormat.replace(/dd/g, vDay).replace(/MM/g, vMonth).replace(/y{1,4}/g, vYear);
  vDateString = vDateString.replace(/hh/g, vHour).replace(/mm/g, vMinute).replace(/ss/g, vSecond);
  return vDateString;
}

function formatChineseDate(vDate, vFormat){
  var vDay = formatInteger(vDate.getDate(),2);
  var vMonth = formatInteger(vDate.getMonth()+1,2);
  var vYear = formatInteger(vDate.getFullYear()-1911,2);
  var vHour = formatInteger(vDate.getHours(),2);
  var vMinute = formatInteger(vDate.getMinutes(),2);
  var vSecond = formatInteger(vDate.getSeconds(),2);
  var vDateString = vFormat.replace(/dd/g, vDay).replace(/MM/g, vMonth).replace(/y{1,4}/g, vYear);
  vDateString = vDateString.replace(/hh/g, vHour).replace(/mm/g, vMinute).replace(/ss/g, vSecond);
  return vDateString;
}

function newWindow(url) {
  window.open(url,'_new','scrollbars=yes,left=20,top=20,width=800,height=600,toolbar=no,resizable=yes');
}
window.setTimeout(initDocument,500);

function isChecked(form,name) {
  var list = form.all(name);
  if(list.length!=undefined) {
    for(var i=0;i<list.length;i++) {
      if(list[i].tagName.toLowerCase()=='input' && list[i].type.toLowerCase()=='checkbox' && list[i].checked) {
        return true;
      }
    }
    return false;
  }
  else if(list.tagName.toLowerCase()=='input' && list.type.toLowerCase()=='checkbox'){
    return list.checked;
  }
  else {
    return false;
  }
}

function openWindow(url,name,width,height,option,isTop) {
    if(width==null) {
      width = screen.availWidth;
    }
    if(height==null) {
      height = screen.availHeight;
    }

    var left = (screen.availWidth - width)/2;
    var top = (screen.availHeight - height)/2;

    if(option==null || option.trim()=="") {
      option = "scrollbars=yes,toolbar=no,titlebar=no,status=no,menubar=no,directories=no,resizable=no,location=no";
    }
    option = "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + "," + option;

    var topWindow = window.open(url ,name,option);

    if(isTop) {
    showTopWindow(topWindow,width,height);
    }
    else {
      return topWindow;
    }
}

var _showTopWindow;
var _openWindow;

function showTopWindow(form) {
  if(form!=null) {
    _showTopWindowOldOnFocus = window.onfocus;
    _showTopWindow = form;
    window.onfocus = showTopWindowAction;
  }
}

function maxWindow(form) {
  form.moveTo(0,0);
  form.resizeTo(screen.availWidth,screen.availHeight);
}

function centerWindow(form,width,height) {
    if(width==null) {
      width = screen.availWidth;
    }
    if(height==null) {
      height = screen.availHeight;
    }
    var left = (screen.availWidth - width)/2;
    var top = (screen.availHeight - height)/2;
    form.moveTo(left,top);
    form.resizeTo(width,height);
}

function showTopWindowAction() {
  if(_showTopWindow!=null) {
    try {
      _showTopWindow.focus();
    }
    catch(message) {}
  }
  else {
    window.onfocus = _showTopWindowOldOnFocus;
    _showTopWindowOldOnFocus = null;
    try {
      window.focus();
    }
    catch(e){}
  }
}

function trimForm(form) {
  if(form==null) {
    form = document.forms[0];
  }

  var list = form.elements;
  for(var i=0;i<list.length;i++) {
    var input = list[i];
    if(input.tagName.toLowerCase()=="input" && (input.type.toLowerCase()=="text" || input.type.toLowerCase=="password")) {
      input.value = input.value.trim();
    }
  }
}

var dateFormat = function () {
  var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
    timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
    timezoneClip = /[^-+\dA-Z]/g,
    pad = function (val, len) {
      val = String(val);
      len = len || 2;
	while (val.length < len) val = "0" + val;
	  return val;
	};

	// Regexes and supporting functions are cached through closure
	return function (date, mask, utc) {
		var dF = dateFormat;

		// You can't provide utc if you skip other args (use the "UTC:" mask prefix)
		if (arguments.length == 1 && (typeof date == "string" || date instanceof String) && !/\d/.test(date)) {
			mask = date;
			date = undefined;
		}

		// Passing date through Date applies Date.parse, if necessary
		date = date ? new Date(date) : new Date();
		if (isNaN(date)) throw new SyntaxError("invalid date");

		mask = String(dF.masks[mask] || mask || dF.masks["default"]);

		// Allow setting the utc argument via the mask
		if (mask.slice(0, 4) == "UTC:") {
			mask = mask.slice(4);
			utc = true;
		}

		var	_ = utc ? "getUTC" : "get",
			d = date[_ + "Date"](),
			D = date[_ + "Day"](),
			m = date[_ + "Month"](),
			y = date[_ + "FullYear"](),
			H = date[_ + "Hours"](),
			M = date[_ + "Minutes"](),
			s = date[_ + "Seconds"](),
			L = date[_ + "Milliseconds"](),
			o = utc ? 0 : date.getTimezoneOffset(),
			flags = {
				d:    d,
				dd:   pad(d),
				ddd:  dF.i18n.dayNames[D],
				dddd: dF.i18n.dayNames[D + 7],
				m:    m + 1,
				mm:   pad(m + 1),
				mmm:  dF.i18n.monthNames[m],
				mmmm: dF.i18n.monthNames[m + 12],
				yy:   String(y).slice(2),
				yyyy: y,
				h:    H % 12 || 12,
				hh:   pad(H % 12 || 12),
				H:    H,
				HH:   pad(H),
				M:    M,
				MM:   pad(M),
				s:    s,
				ss:   pad(s),
				l:    pad(L, 3),
				L:    pad(L > 99 ? Math.round(L / 10) : L),
				t:    H < 12 ? "a"  : "p",
				tt:   H < 12 ? "am" : "pm",
				T:    H < 12 ? "A"  : "P",
				TT:   H < 12 ? "AM" : "PM",
				Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
				o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
				S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
			};

		return mask.replace(token, function ($0) {
			return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
		});
	};
}();

// Some common format strings
dateFormat.masks = {
	"default":      "ddd mmm dd yyyy HH:MM:ss",
	shortDate:      "m/d/yy",
	mediumDate:     "mmm d, yyyy",
	longDate:       "mmmm d, yyyy",
	fullDate:       "dddd, mmmm d, yyyy",
	shortTime:      "h:MM TT",
	mediumTime:     "h:MM:ss TT",
	longTime:       "h:MM:ss TT Z",
	isoDate:        "yyyy-mm-dd",
	isoTime:        "HH:MM:ss",
	isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
	isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};

// Internationalization strings
dateFormat.i18n = {
	dayNames: [
		"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat",
		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
	],
	monthNames: [
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
		"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
	]
};

// For convenience...
Date.prototype.format = function (mask, utc) {
	return dateFormat(this, mask, utc);
};

