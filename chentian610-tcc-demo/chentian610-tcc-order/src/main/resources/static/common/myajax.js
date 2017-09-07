(function($){  
    //备份jquery的ajax方法  
    var _ajax=$.ajax;  
      
    //重写jquery的ajax方法  
    $.myajax=function(opt){  
        //备份opt中error和success方法  
        var fn = {  
            error:function(XMLHttpRequest, textStatus, errorThrown){},  
            success:function(data, textStatus){},
            beforeSend:function(request){}
        };
        if(opt.beforeSend){
            fn.beforeSend=opt.beforeSend;
        };

        if(opt.error){  
            fn.error=opt.error;  
        };  
        
        if(opt.success) {
            fn.success=opt.success;  
        };  
        
        if (!opt.datatype) opt.datatype="json";
        
        var action_name = opt.url+"#"+JSON.stringify(opt.data);
        if  (opt.data) opt.data.cache_version = localStorage.getItem(action_name+"#V");
        else opt.data = {cache_version:localStorage.getItem(action_name+"#V")};
        //扩展增强处理  
        var _opt = $.extend(opt,{
			beforeSend:function(request){
				//文件服务器不能加，否则文件无法识别
				var token = window.token;//获取token值
                if (this.url.indexOf("fileAction") == -1)
				    request.setRequestHeader("token", token);
				fn.beforeSend(request);
			},
			error:function(response, textStatus, errorThrown){
                //错误方法增强处理  
    			var errorData = $.parseJSON(response.responseText);
    			if (errorData)
    				jConfirm(errorData.msg, '警告');
                fn.error(response, textStatus, errorThrown);  
            },  
            success:function(data, textStatus){
            	if (data.code==100) {
            		var data2 = JSON.parse(localStorage.getItem(action_name));
            		fn.success(data2, textStatus); 
            		return;
            	}
            	
            	if (data.cache) {
            		localStorage.setItem(action_name,JSON.stringify(data));
            		localStorage.setItem(action_name+"#V",data.cache_version);
            	}
            	
    			if (data.code!=1)
            	{
            		if (toastr) {
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
            			toastr.info(data.msg,"提示");
            		} else alert(data.msg);
            		return;
            	}
            	if (this.url.indexOf('showAlert=true')>-1)
            		{
	            		if (toastr) {
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
	            			toastr.info(data.msg,"提示");
	            		} else alert(data.msg);
            		}
                fn.success(data, textStatus);  
            }  
        });  
        _ajax(_opt);  
    };  
})(jQuery);