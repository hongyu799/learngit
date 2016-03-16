<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="/header.jsp"></jsp:include>
<div><a href="<%=basePath %>valc.do?method=get">添加验证码</a></div>
<jsp:include page="/footer.jsp"></jsp:include>