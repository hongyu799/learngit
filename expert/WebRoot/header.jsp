<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>专家库</title>
<link href="<%=basePath %>css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/wbox.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery1.42.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.jqprint.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript" src="<%=basePath %>js/wbox.js"></script>
</head>

<!-- top -->

<!-- 
<body>

<div class="gxj_header">
	<h1>吉林省电子政务专家资讯库</h1>
</div>
<%
Object tp = request.getAttribute("tp");
if(null != tp && (Integer)tp != 3){ %>
<div class="dq">当前位置：<a href="<%=basePath %>pro.do?method=prolist">项目列表</a>
<%

if(null != tp && (Integer)tp == 1){
%>
&nbsp;>&nbsp;<a href="#">抽取专家</a>
<%
}else if(null != tp && (Integer)tp == 2){
%>
&nbsp;>&nbsp;<a href="#">项目详情</a>
<%
}
}else if(null == tp){
%>
<div class="dq">当前位置：<a href="<%=basePath %>pro.do?method=prolist">项目列表</a>
<%
} %>
</div>
 -->
<!-- // top -->
