<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/header.jsp"></jsp:include>
<iframe id="gm" width="100%" height="1060px" name="<%=request.getAttribute("code") %>" src="<%=request.getAttribute("expurl") %>"></iframe>
<jsp:include page="/footer.jsp"></jsp:include>