<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'expmobile.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		html{font-size: 14px;color: #000000; background: #ffffff;}
		html,body,img,table,tr,td,dl,dt,dd,p,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,select,fieldset,input,button,textarea,blockquote{margin: 0;padding: 0;font-family:"宋体","黑体","微软雅黑"; font-size:14px;}
		dl,ul,li{list-style:none;}
		.sjyz { width:400px; overflow:hidden; margin:50px auto;}
		.sjyz dl { width:450px; height:30px; padding:6px 0;}
		.sjyz dl dt { float:left; width:100px; text-align:right; margin-right:20px;line-height:26px; color:#666;}
		.sjyz dl dd { float:left; width:250px;}
		.sjyz .input1 { height:26px; line-height:26px; width:220px;}
		.sjyz .input2 { height:26px; line-height:26px; width:140px;}
		.sjyz .but { width:90px; margin:15px auto;}
		.sjyz .butstyle { width:90px; line-height:30px; height:30px; background:#33F; color:#fff; border:none; cursor:pointer;}
		.sjyz .butstyle1 { width:70px; line-height:28px; height:28px; background:#bfbfbf; color:#fff; border:none; cursor:pointer; margin-left:8px;}
	</style>
	<script type="text/javascript" src="<%=basePath %>js/jquery1.42.min.js"></script>
  </head>
  
  <body>
	<div class="sjyz">
		<dl>
			<dt>手机号码:</dt>
		    <dd><input id="mobile" name="mobile" type="text" class="input1" /></dd>
		</dl>
		<dl>
			<dt>验证码:</dt>
		    <dd><input id="vcode" name="vcode" type="text"  class="input2" />
		    <input id="btn" value="发送验证码" type="button" onclick="sendSms();" class="butstyle1" /></dd>
		</dl>
		<div class="but"><input name="" type="button" onclick="expRegister();" class="butstyle" value="确定"/></div>
	</div>
  </body>
  <script type="text/javascript">
  	function sendSms(){
  		var mobile=document.getElementById('mobile');
  		// 验证手机号
		var pattern_phoneUrl = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
		var phoneNumber = mobile.value;
		if (!pattern_phoneUrl.test(phoneNumber)) {
			alert("请输入有效的手机号！");
			return false;
		}
  		var btn=document.getElementById('btn');
  		btn.value="(30)秒后重新获取";
  		timeout=false;
  		btn.disabled="disabled";
  		time();
  		$.ajax({  
	      type: "post",  
	       	url: "<%=basePath %>exp.do?method=sms",    
	       	async:false,  
	      	dataType:"json",
	        data: {
	        	phone : mobile.value
			}, 
	         success: function(data){
				if(data.success){
				}else{			
				}
	        }  
		});
  	}
  	
  	var timeout = false; //启动及关闭按钮  
  	var maxT=30;
	function time(){  
	  if(timeout) return;  
	  setButton();  
	  setTimeout(time,1000); //time是指本身,延时递归调用自己,100为间隔调用时间,单位毫秒
	}
  	
  	function setButton(){
  		maxT--;
  		btn.value="("+maxT+")秒后重新获取";
  		if(maxT<=0){
  			btn.value="发送验证码";
  			maxT=30;
  			timeout=true;
  			btn.disabled="";
  		}
  	}
  	
  	function expRegister(){
  		$.ajax({  
	        type: "post",  
	       	url: "<%=basePath %>exp.do?method=vcode",    
	       	async:false,  
	      	dataType:"json",
	        data: {
	        	code : document.getElementById('vcode').value,
	        	phone : document.getElementById('mobile').value
			}, 
	         success: function(data){ 
				if(!data.success){
					alert(data.msg);
					return false;
				}else{
					parent.lstbox.close(0);
				}
				
	        }  

	});
  	
  	}
  </script>
</html>
