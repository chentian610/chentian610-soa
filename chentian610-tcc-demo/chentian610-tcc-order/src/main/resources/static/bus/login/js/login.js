var dataMap = {};
var user_type;
var school_id;
var uuid;
var msk = $('<div id="msk"></div>').css({
	'width':'100%',
	'height':$(window).height(),
	'position':'absolute',
	'top':0,
	'left':0,
	'bottom':0,
	'background':'rgba(0,0,0,.4)',
	'z-index':1
});

function initPulicParam(userType,schoolID){
	user_type = userType;
	school_id = schoolID;
}

function bindSuperAdminBtnClickEvent(){
	$('#btn-SuperAdmin').on('click',function(){
		$('body').append(msk);
		$('.tc-tit').html("超级管理员登录");
		$('#inputUserType').val("003099");
		$('#SuperAdmin-tc').show();
		$('#msk').on('click',function(e){
			e.stopPropagation();
			$(this).remove();
			$('#SuperAdmin-tc').hide();
		});
	});
}
function bindAdminBtnClickEvent(){
	$('#btn-Admin').on('click',function(){
		$('body').append(msk);
        $('.tc-tit').html("登录");
		$('#SuperAdmin-tc').show();
		$('#msk').on('click',function(e){
			e.stopPropagation();
			$(this).remove();
			$('#SuperAdmin-tc').hide();
		});
	});
}

//忘记密码功能
function bindForgetPassWordClickEvent(){
	$('#forget').on('click',function(){
		$('#SuperAdmin-tc').hide();
		$('body').append(msk);
		$('.tc-tit').html("忘记密码");
		$('#phone1').empty();
		$('#validateCode').val('');
		$('#fpassword').val('');
		$('#phone1').val($('#phone').val());
		$('#fpass_word').click();
		$('#forgetPassword').show();
		$('#msk').on('click',function(e){
			e.stopPropagation();
			$(this).remove();
			$('#forgetPassword').hide();
		});
	});
}

//获取验证码
function bindValidateCodeBtnClickEvent(){
	$('#getValidateCode').on('click',function(){
		settime($('#getValidateCode'));
		$.myajax({
			url:"userAction/getValidateCodePhone",
			data:{phone:$('#phone1').val()},
			datatype:'json',
			success:function(data){}
		});
	});
}

//修改密码
function bindUpdateForgetPassword(){
	$('#changeBtn').on('click',function(){
		var phone = $('#phone1').val();
		var validateCode = $('#validateCode').val();
		var fpassword = $('#fpassword').val();
		if (phone==null || phone == ''
			||validateCode == null || validateCode == ''
			||fpassword == null || fpassword == ''){
            toastrMassage("电话号码、验证码或者新密码不能为空!");
		} else {
			$.myajax({
				url:"userAction/forgetPassword",
				data:{phone:phone,pass_word:fpassword,validate_code:validateCode},
				datatype:'json',
				success:function(data){
					$('#changeBtn').attr('disabled','disabled');
                    toastrMassage("密码修改成功,请重新登录!");
					setShowDuration();
				}
			});
		}
	});
}
var showDuration=1; 
function setShowDuration() {
	if (showDuration == 0) { 
		window.location.href = window.location.href;
	} else { 
		--showDuration; 
		setTimeout(function(){				//设置倒计时速度时时间走起来
			setShowDuration();
		},1000);
	} 	
}

var countdown=60; 							//设置倒计时最大时间
function settime(val) { 
	if (countdown == 0) { 
		val.val("重新发送验证码");
		val.attr("disabled",false);
	} else { 
		val.val("重新发送("+countdown+")");
		--countdown; 
		setTimeout(function(){				//设置倒计时速度时时间走起来
			settime(val);
		},1000);
	} 	
}

function bindAgentBtnClickEvent(){
	$('.btn-agent').on('click',function(){
		$('body').append(msk);
		$('#SuperAdmin-tc').show();
		$('#msk').on('click',function(e){
			e.stopPropagation();
			$(this).remove();
			$('#SuperAdmin-tc').hide();
		});
	});
}

function bindLoginClickEvent(){
	$("#loginBtn").unbind("click").on('click', function() {
		$('#pass_word').val($.md5($("#password").val()));
		$('#loginForm').submit();
	});
}

function bindSubmitCallBackEvent(){
	// $(".loginForm").ajaxForm({
	//     beforeSend: function() {
	//     	toastr.clear();
	//     },
     //    async: false,
	//     success: function(data) {
	//     	var code = data.code;
	//     	if (isEmpty(code)) toastrMassage(data.msg);
	//     	else initUserInfo(data);
	//     }
	// });
} 

function initUserInfo(data) {
    var result = data.result.data;
    localStorage.setItem('user_name', result.user_name);
    localStorage.setItem('phone', result.phone);
    localStorage.setItem('user_id',result.user_id);
    localStorage.setItem('head_url', result.head_url);
	localStorage.setItem('token',result.token);
    $('#skipView').submit();
}

function bindScanCodeClickEvent(){
	$("#aScanCode").unbind("click").on('click', function() {
		refreshScanCode();
	    $('#loginForm').hide();
	    $('#userInfoForm').hide();
		$('#scanLoginForm').show();
	});
};


function refreshScanCode(){
	var uuid =getUuid();
	var content = '{"user_type":"'+localStorage.getItem("user_type")+'","school_id":"'+school_id+'","uuid":"'+uuid+'","module_code":"009000"}';
	var scanCode = '{"school_id":'+school_id+',"uuid":"'+uuid+'","module_code":"009000"}';
	$('#scanCode').html("");
    $("#scanCode").qrcode({render: "table",text: scanCode,width:"150",height:"150"});
	$.ajax({
		url:"scanAction/createCode",
		data:$.parseJSON(content),
		datatype:'json',
		success:function(data){
			if (data.code==0) {
				refreshScanCode();
			} else {
				var user = data.result.data;
				$("#headUrl").attr("src",user.head_url);
				$('#scanLoginForm').hide();
				$('#loginForm').hide();
				$('#userInfoForm').show();
				secondConfirm();
			}
		}
	});
}

/**
 * 扫码二次确认登录操作
 */
function secondConfirm(){
	var content = '{"user_type":"'+localStorage.getItem("user_type")+'","school_id":"'+school_id+'","uuid":"'+uuid+'","module_code":"009000"}';
	$.ajax({
		url:"scanAction/secondConfirm",
		data:$.parseJSON(content),
		datatype:'json',
		success:function(data){
			if (data.code==0) {toastrMassage(data.msg);$('#aBackScanLogin').click();return;}
	    	var user = data.result.data;
	    	localStorage.setItem('user_name', user.user_name);
	    	localStorage.setItem('phone', user.phone);
	    	localStorage.setItem('head_url', user.head_url);
			localStorage.setItem('user_id',user.user_id);
	    	$('#skipView').submit();
		}
	});
}



function bindBackDefaulLoginClickEvent(){
	$("#aBackDefaultLogin").unbind("click").on('click', function() {
		$('#scanLoginForm').hide();
		$('#userInfoForm').hide();
		$('#loginForm').show();
	});
};

function bindBackScanLoginClickEvent(){
	$("#aBackScanLogin").unbind("click").on('click', function() {
		$('#userInfoForm').hide();
		$('#loginForm').hide();
		$('#scanLoginForm').show();
        refreshScanCode();
	});
};


function getUuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "";
    uuid = s.join("");
    return uuid;
}

function isEmpty(str) {
    return str == 0 || str === null || str === ''|| str === ""|| str === '""'|| str === undefined || str === '[]'||str === '{}'|| str.length ==0;
}

function isNotEmpty(str) {
    return str !== null && str !== ''&& str !== ""&& str !== '""'&& str !== undefined && str !== '[]'&& str !== '{}'&&str.length !=0;
}

function toastrMassage(content) {
	if (isEmpty(content)) return;
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-top-center",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "1500",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    toastr.info(content,"提示");
}