<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
       
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		html{font-size: 14px;color: #000000; background: #ffffff;}
		html,body,img,table,tr,td,dl,dt,dd,p,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,select,fieldset,input,button,textarea,blockquote{margin: 0;padding: 0;font-family:"����","����","΢���ź�"; font-size:14px;}
		dl,ul,li{list-style:none;}
		.sjyz { width:400px; overflow:hidden; margin:50px auto;}
		.sjyz .input2 { width:360px; line-height:20px; height:200px; margin:0 auto;}
		.sjyz .but { width:90px; margin:15px auto;}
		.sjyz .butstyle { width:90px; line-height:30px; height:30px; background:#33F; color:#fff; border:none; cursor:pointer;}
	</style>
	
	<script type="text/javascript" src="<%=basePath %>js/jquery1.42.min.js"></script>
	<script type="text/javascript">
	
		function frmSubmit(){
			$.ajax({  
				type: "post",  
				url: "<%=basePath %>exp.do?method=dele",    
				async:false,  
				dataType:"json",
				data: {
					proid : <%=request.getAttribute("proid") %>,
					expid : <%=request.getAttribute("expid") %>,
					res : document.getElementById('res').value
					}, 
				success: function(data){
				if(!data.success){
					alert(data.msg);
					return false;
					}else{
					parent.delbox.close(0);
					}
				}  
			});
		}
	</script>

  </head>
  <body>
		<div class="sjyz">
			<div style="width:350px; margin:30px auto 0 auto;"><textarea  id="res" cols="" rows="" class="input2"></textarea></div>
			<div class="but"><input name="" type="button" onclick="frmSubmit();"class="butstyle" value="确定"/></div>
		</div>
  </body>
</html>
