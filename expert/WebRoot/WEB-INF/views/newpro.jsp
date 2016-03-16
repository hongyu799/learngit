<%@ page language="java" import="java.util.*,com.exp.model.WcmUser" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<iframe id="gm" width="100%" height="100%" src="<%=request.getAttribute("prourl") %>" name="<%=((WcmUser)session.getAttribute("userinfo")).getUserName() %>"></iframe>
