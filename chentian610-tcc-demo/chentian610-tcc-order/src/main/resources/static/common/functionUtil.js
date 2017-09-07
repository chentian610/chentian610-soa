/**
 * 获取JSON值：null转空字符串
 */
function getStrValue(Obj){ 
	if (Obj) return Obj;
	else return "";
};

/**
 * 获取JSON值：null转空字符串
 */
function getJsonValue(Obj,field){ 
	if (Obj[field]) return Obj[field];
	else return "";
};

/**
 * 获取JSON值:null转0
 */
function getJsonMathValue(Obj,field){ 
	if (Obj[field]) return Obj[field];
	else return 0;
};

/**
 * 按yyyy-MM-dd HH:mm:ss 转化日期
 * format == 'day' 表示 yyyy-MM-dd
 * format == 'minute' 表示 yyyy-MM-dd HH:mm
 * format == 'second' 表示 yyyy-MM-dd HH:mm:ss
 */
function getDateStr(date, format){
	  var myDate = new Date(date);   
      var year = myDate.getFullYear();  
      var month = ("0" + (myDate.getMonth() + 1)).slice(-2);  
      var day = ("0" + myDate.getDate()).slice(-2);  
      var h = ("0" + myDate.getHours()).slice(-2);  
      var m = ("0" + myDate.getMinutes()).slice(-2);  
      var s = ("0" + myDate.getSeconds()).slice(-2);   
      if(format=='day'){
    	  return year + "-" + month + "-" + day;
      }else if(format=='minute'){
    	  return year + "-" + month + "-" + day+ " " + h + ":" + m;
      }else if(format=='second'){
    	  return year + "-" + month + "-" + day+ " " + h + ":" + m + ":" + s;
      }
} 


/**
 * 截取字符串
 */
function getSubString(str){
	var subStr = str.substr(0, 60)+'......'; 
	return subStr;
}

function getUrlParam(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function GetRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
	var str = url.substr(1); 
	strs = str.split("&"); 
	for(var i = 0; i < strs.length; i ++) { 
	theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
	} 
	} 
	return theRequest; 
}


//获取页面中的参数
function getParameterByUrl(parameter){
	var main_url = window.location.href;
	var n = main_url.indexOf('?');
	if(n>0){
		var paramStr = main_url.substring(n+1);
		var params = paramStr.split('&');
		for(var i in params){
			var paramKey = params[i].split('=')[0].trim();
			var paramValue = params[i].split('=')[1].trim();
			if (paramValue.indexOf('?')>0) paramValue = paramValue.split('?')[0].trim();
			if(parameter == paramKey){
				return paramValue;
			}
		}
	}
}

//session过期后，跳转到登录页面
function pageJump() {
	$.ajaxSetup({
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.status == 403) {
				toastr.info('您没有权限访问此资源或进行此操作',"提示");
				return false;
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus， 
			var url = XMLHttpRequest.getResponseHeader("url");//lezhi.test.classtao.cn
			if (sessionstatus == 'timeout') {
				//如果超时就处理 ，指定要跳转的页面  
				var top = getTopWinow(); //获取当前页面的顶层窗口对象
				toastr.info('您长时间没有操作，登录超时，请重新登录',"提示");
				//top.location.href="http://"+url;
				top.location.href=localStorage.getItem('url');
			}
		}
	});
}

function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}