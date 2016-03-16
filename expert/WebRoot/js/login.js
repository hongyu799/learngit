//登陆前的验证
function loginValidate(param) {
	
	var userName = $("#userName").val();
	if (userName == '') {
		alert("请输入用户名！");
		$("#userName").focus();
		return false;
	}
	var password = $("#password").val();
	if (password == '') {
		alert('请输入用户名密码！');
		$("#password").focus();
		return false;
	}
	$('#loading').val("登录中...");
	 $('#loading').attr('disabled',"disabled");
	$.ajax({  
        type: "post",  
       	url: param+"main.do?method=login",    
       	async:false,  
      	dataType:"json",
        data: {
        	userName : document.getElementById("userName").value,
        	password : document.getElementById("password").value,
		}, 
         success: function(data){
        	 $('#loading').val("登录");
			 $("#loading").removeAttr("disabled");
			if(!data.success){
				alert(data.msg);
				return false;
			}else{
				window.location.href=param+"pro.do?method=prolist";
			}
        }  
});
	
	
}
$(document).ready(function() {
	$("#code").focus(function() {
		if ($("#code").val() == "请输入随机注册码") {
			$("#code").val("");
		}
	});
	$("#code").blur(function() {
		if ($("#code").val() == "") {
			$("#code").val("请输入随机注册码");
		}
	});
	

});



// 注册时的验证
function registerValidate(param) {
	var code = $("#code").val();
	if (code == '' || code == '请输入随机注册码') {
		alert("请输入随机注册码！");
		$("#code").focus();
		return false;
	} 
	else {
		$.ajax({  
	        type: "post",  
	       	url: param+"exp.do?method=vcode",    
	       	async:false,  
	      	dataType:"json",
	        data: {
	        	code : document.getElementById("code").value,
			}, 
	         success: function(data){ 
				if(!data.success){
					alert("验证码输入错误，请从新输入！");
					$("#code").focus();
					return false;
				}else{
					window.location.href=param+"register.do?method=reg&code="+code;
				}
				
	        }  

	});
	}
	
}


