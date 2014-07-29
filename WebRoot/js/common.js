/** 
 *  显示dialog的方法
 * 	显示的ID
 *  显示的宽度
 *  显示的高度
 */
function showdialog(id,width,height){
	$("#"+id).dialog({
		resizable: false,
		height:height,
		width:width,
		modal: true
	});
}
/** 
 *  隐藏dialog的方法
 * 	隐藏的ID
 */
function hideId(id){
	$("#"+id).hide();
	$(".ui-widget-overlay").remove();
	$(".ui-dialog").remove();
}

/**
 * 只能输入数字
 * @param e
 */
function onlyNum(e){
	var demovalue=e.value;
	e.value=demovalue.replace(/\D/g,"");
}

var lazyId;
var iE6 = navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1;
var isQuirks = document.compatMode == "BackCompat";

//定义form对象
var Form = {};
Form.uniqueEle = function (f) {
	var b = f.elements;
	var a = [];
	if (b.length == 0) {
		return a;
	}
	var uniq;
	for (var i = 0; i < b.length; i++) {
		uniq = true;
		for (var j = 0; j < a.length; j++) {
			if (b[i].name == a[j].name) {
				uniq = false;
				break;
			}
		}
		if (uniq&&b[i].name!=null&&b[i].name.length>0) {
			a[a.length] = b[i];
		}
	}
	return a;
};
Form.setValue = function (d, b,dw) {
	b = $F(b,dw);
	var c = Form.uniqueEle(b);
	for (var a = 0; a < c.length; a++) {
		var g = c[a];
		if (g.getAttribute("ztype") == "select") {
			g = g.parentElement;
		}
		if (g.type == "checkbox" || g.type == "radio") {
			if (g.name) {
				$NS(g.name, d.get(g.name));
				continue;
			}
		}
		var f = g.name;
		if (d.get(f)) {
			$S(g, d.get(f));
		}
	}
};
Form.getData = function (g,dw) {
	if (!g) {
		alert("Query form element failed!" + g);
		return;
	}
	var d = new DataCollection();
	var b = Form.uniqueEle(g);
	for (var f = 0; f < b.length; f++) {
		var h = b[f];
		var a = h.name;
		if (!h.type) {
			continue;
		}
		if (h.type == "checkbox" || h.type == "radio") {
			if (h.name) {
				d.add(h.name, getValue(h.name));//$NV(h.name,dw)  旧
				continue;
			}
		}
		if (!a) {
			continue;
		}
		if (h.getAttribute("ztype") == "select") {
			h = h.parentElement;
		}
		d.add(h.name, h.value);
	}
	return d;
};
function DataCollection() {
	this.map = {};
	this.valuetype = {};
	this.keys = [];
	DataCollection.prototype.get = function (ID) {
		if (typeof (ID) == "number") {
			return this.map[this.keys[ID]];
		}
		return this.map[ID];
	};
	DataCollection.prototype.getKey = function (index) {
		return this.keys[index];
	};
	DataCollection.prototype.size = function () {
		return this.keys.length;
	};
	DataCollection.prototype.remove = function (ID) {
	   var temp="";
		if (typeof (ID) == "number") {
		    temp=this.map[this.keys[ID]];
			this.map[this.keys[ID]] = null;
			return temp;
		}
		temp=this.map[ID];
		this.map[ID]=null;
		return temp;
	};
	DataCollection.prototype.toQueryString = function () {
		var arr = [];
		for (var i = 0; i < this.keys.length; i++) {
			if (this.map[this.keys[i]] == null || this.map[this.keys[i]] == "") {
				continue;
			}
			if (i != 0) {
				arr.push("&");
			}
			arr.push(this.keys[i] + "=" + this.map[this.keys[i]]);
		}
		return arr.join("");
	};
	//刘善根增加 转换为json数组
	DataCollection.prototype.toJSON = function () {
	    var arr = [];
		for (var i = 0; i < this.keys.length; i++) {
			arr.push({name:this.keys[i], value:this.map[this.keys[i]]});
		}
		return arr;
	};
	
	//刘善根增加 转换为json数组
	DataCollection.prototype.toJSONFilter = function () {
	    var arr = [];
		for (var i = 0; i < this.keys.length; i++) {
			if (this.map[this.keys[i]] == null || this.map[this.keys[i]] == "") {
				continue;
			}
			arr.push({name:this.keys[i], value:this.map[this.keys[i]]});
		}
		return arr;
	};
	DataCollection.prototype.add = function (ID, Value, Type) {
		this.map[ID] = Value;
		this.keys.push(ID);
		if (Type) {
			this.valuetype[ID] = Type;
		} else {
			if (Value && Value.getDataRow) {
				this.valuetype[ID] = "DataTable";
			} else {
				this.valuetype[ID] = "String";
			}
		}
	};
}
function getpos(element) {
	if (arguments.length != 1 || element == null) {
		return null;
	}
	var elmt = element;
	var offsetTop = elmt.offsetTop;
	var offsetLeft = elmt.offsetLeft;
	var offsetWidth = elmt.offsetWidth;
	var offsetHeight = elmt.offsetHeight;
	while (elmt = elmt.offsetParent) {
		if (elmt.style.position == "absolute" || (elmt.style.overflow != "visible" && elmt.style.overflow != "")) {
			break;
		}
		offsetTop += elmt.offsetTop;
		offsetLeft += elmt.offsetLeft;
	}
	return {top:offsetTop, left:offsetLeft, right:offsetWidth + offsetLeft, bottom:offsetHeight + offsetTop};
}


var AjaxServer = {};
AjaxServer.sendRequest = function (b, d, g,e) {
	$.ajax({url:b, type:"post", data:d, dataType:"json", error:function () {
	   if(e)
	      e();
	    else
		  alert("execute failed!");
	}, success:function (json) {
		if (g) {
			g(json);
		}
	}});
};

function rveFirst(str)
{
 if(str==null || str.length==0) return "";
 if(str.length==1) return str;
 return str.substring(1);
}

//input 只能输入数字
function onlyNum(e){
  if(!e){
   e = window.event;
  }
  
  if($.browser.mozilla){
   if(e.charCode==35 ||e.charCode==45 ||e.charCode==0){}else{
    if(e.charCode<47||e.charCode>57) {
     e.preventDefault()
    }
   }
  }else{
   if(e.keyCode==35 ||e.keyCode==45){}else{
    if(e.keyCode<47||e.keyCode>57) {
     e.returnValue =false;
    }
   }
  }
}

//array  对象   单选  多选
function getValue(name){
	var checkValue;
	var array=$("input:radio[name="+name+"]");
	array.each(function(index, element) {
			if(array[index].checked==true){
				checkValue=element.value;
			}
        });
	return checkValue;
}


/**
 * @param id 标签的ID 
 * @param length 标签的允许输入的长度
 */
function countLength(id,length){
	if($("#"+id).val().length>length){
		alert("introducir hasta 500 caracteres!");
		$("#"+id).attr("value",$("#"+id).val().substring(0,500));
		return false;
	}
	$("#"+id+"Count").text("Ahora "+(length-$("#"+id).val().length));
}