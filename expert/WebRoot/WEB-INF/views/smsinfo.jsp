<%@ page language="java" import="java.util.*,com.exp.model.Expert" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'smsinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <jsp:include page="/header.jsp"></jsp:include>
  <body>
  <form action="<%=basePath %>exp.do?method=smsSet" id="smsSet" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="110">
													<textarea name="dx_info" id="dx_info" style="width: 370px; height: 90px" 
													onKeyDown="LimitTextArea(this)" 
													onKeyUp="LimitTextArea(this)"
													onkeypress="LimitTextArea(this)"></textarea>
												</td>
											</tr>
											<tr>
												<td height="20" class="hui12">
													<font color="red" size="2" id="ts">*最多输入70个字</font>
													<font color="red" size="2" id="fsz" style="display:none">发送中...</font>
													<font color="red" size="2" id="fsz2" style="display:none">输入字数不能超过70字</font>
												</td>
											</tr>
											<tr><td><div class="sqd_fs"><a href="javascript:subsms();">发送</a></div></td></tr>
										</table></form>
  </body>
  <script type="text/javascript">
  function subsms(){ var sizes = document.getElementById('dx_info').value;
  if(sizes.length>70){ 
  document.getElementById('fsz2').style.display='';
  document.getElementById('ts').style.display='none';
  return;
  }else{if(sizes.length==0){ 
  alert("发送内容不能为空");
  return;
  }else{fsdx();}}
  
 }
  function fsdx(){
 
  document.getElementById('fsz').style.display='';
  document.getElementById('ts').style.display='none';
  document.getElementById('fsz2').style.display='none';
		$.ajax({  
        type: "post",  
       	url: "<%=basePath%>exp.do?method=smsSet",    
       	async:false,  
      	dataType:"json",
        data: {
			dx_info : document.getElementById("dx_info").value,
			
		}, 
         success: function(data){  
        	
			if(data.success){
			alert("发送成功！");
				parent.lstbox2.close(0);
			}else{
			alert("发送失败");
				parent.lstbox2.close(0);
			}
			
        }  

});	
  
  }
  
  </script>
</html>
