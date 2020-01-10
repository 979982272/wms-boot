(function($){
	$.fn.loadForm = function(options){
		var $form = this;
		options = options || {};
		options.type = options.type || "GET";
		options.cache = false;
		options.dataType = options.dataType || "json";
		options.success = function(res){
			for(var o in res){							
				$form.find("select[name='"+o+"']").val($.trim(res[o]));
				$form.find("textarea[name='"+o+"']").val(res[o]);
				if($form.find("input[name='"+o+"']").attr("type")=='checkbox'){
					var value = ","+res[o]+",";
					$form.find("input[name='"+o+"']").each(function(){
						var val = ","+$(this).val()+",";
						$(this).prop("checked", value.indexOf(val)>-1);						
					});										
				}else if($form.find("input[name='"+o+"']").attr("type")=='radio'){
					$form.find("input[name='"+o+"']").each(function(){
						var val = $(this).val();		
						$(this).prop("checked", res[o]==val);						
					});	
				}else{
					$form.find("input[name='"+o+"']").val(res[o]);
				}
			}
			if(options.callback) options.callback(res);
		};
		options.error = function(XMLHttpRequest, textStatus, errorThrown){
			alert("表单加载失败"+errorThrown);
		};
		$.ajax(options);
	};
	$.exAjax = function(options){
		options = options || {};
		options.type = options.type || "POST";
		options.cache = false;
		options.dataType = options.dataType || "json";
		options.success = options.success || function(res){
			if(res.error){
				$.Zebra_Dialog(res.msg,{
					"type" : 'error',
					"title" : "系统提示"
				});
			}else{
				options.successful(res);
			}
		};
		options.error = options.error || function(XMLHttpRequest, textStatus, errorThrown){
			alert(XMLHttpRequest.responseText);
		};
		$.ajax(options);
	};
	//扩展AJAX-POST
	$.exPost = function(options){
		showLoading(options.loadingMsg, true);
		$.post(options.url, options.params, function(res){
			hideMsg("loadingMsg");
			hideMask();
			if(res.error){
				showTipsMsg(res.msg, "info");
			}else{
				options.callback(res);
			}						
		});
	};
	$.fn.serializeObject = function()  
	{  
	   var o = {};  
	   var a = this.serializeArray();  
	   $.each(a, function() {  
	       if (o[this.name]) {  
	           if (!o[this.name].push) {  
	               o[this.name] = [o[this.name]];  
	           }  
	           o[this.name].push(this.value || '');  
	       } else {  
	           o[this.name] = this.value || '';  
	       }  
	   });  
	   return o;  
	}; 
})(jQuery);