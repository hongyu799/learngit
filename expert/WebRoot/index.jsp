<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//response.sendRedirect(basePath+"main.do?method=pagelogin");
%>
<html>
	<head></head>
	<body>
		<form id="fpriv" action="<%=basePath %>main.do?method=pagelogin" method="post">
		<div style="display: none;">
		<%
									String jUserName = "";
	String jPassWord = "";
	jUserName = request.getHeader("username");
	jPassWord = request.getHeader("password");
	//jUserName = "admin";
	if (jUserName != null){
		jUserName = new String(jUserName.getBytes("ISO8859-1"),"GBK");
		jPassWord = new String(jPassWord.getBytes("ISO8859-1"),"GBK");
	}
	else {
		jUserName = "admin";
		jPassWord = "trsadmin";

	}
	
%>						
			证书主题:<input type="text" name="dnname" value="<%=null==request.getHeader("dnname") ? "" : new String(request.getHeader("dnname").getBytes("ISO8859-1"),"UTF-8") %>" /><br />
			证书序列号:<input type="text" name="serialnumber" value="<%=request.getHeader("serialnumber") %>" /><br />
			证书颁发者:<input type="text" name="issuerdn" value="<%=null==request.getHeader("issuerdn") ? "" : new String(request.getHeader("issuerdn ").getBytes("ISO8859-1"),"UTF-8") %>" /><br />
			证书其实有效期:<input type="text" name="notbefore" value="<%=request.getHeader("notbefore") %>" /><br />
			证书终止有效期:<input type="text" name="notafter" value="<%=request.getHeader("notafter") %>" /><br />
			证书Base64编码:<input type="text" name="certinfo" value="<%=request.getHeader("certinfo") %>" /><br />
			用户客户端IP:<input type="text" name="clientip" value="<%=request.getHeader("clientip") %>" /><br />
			设备名称:<input type="text" name="devicename" value="<%=request.getHeader("devicename ") %>" /><br />
			认证方式:<input type="text" name="austyle" value="<%=request.getHeader("austyle") %>" /><br />
			用户权限:<input type="text" name="privilege" value="<%=request.getHeader("privilege ") %>" /><br />
			用户帐号:<input type="text" name="username" value="<%=jUserName %>" /><br />
			用户口令:<input type="text" name="password" value="<%=jPassWord %>" /><br />
			默认权限:<input type="text" name="defaultprivilege" value="<%=request.getHeader("defaultprivilege") %>" /><br />
			<%
			
				String dnName=null==request.getHeader("dnname") ? "" : new String(request.getHeader("dnname").getBytes("ISO8859-1"),"UTF-8");
				String emailValue="";
				if(!dnName.equals("")){
					int start = dnName.indexOf("E=")+2;
					int end = dnName.indexOf(",", start);
					emailValue = dnName.substring(start, end);
				}
				
				
			%>
			获取DN中email项的值:<input type="text" name="emailValue" value="<%=emailValue %>" /><br />
			获取服务列表:<input type="text" name="servicelist" value="<%=request.getHeader("servicelist") %>" /><br />
			获取应用码:<input type="text" name="appflag" value="<%=request.getHeader("appflag") %>" /><br />
		</div></form>
	</body>
</html>
<script type="text/javascript">
	document.getElementById('fpriv').submit();
</script>