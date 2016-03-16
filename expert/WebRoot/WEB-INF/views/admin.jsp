<%@ page language="java" import="java.util.*,com.exp.model.*" pageEncoding="utf-8"%>
<%@page import="org.apache.taglibs.bsf.expression"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Project project = (Project)request.getAttribute("pro");
List<Expert> exps = (List<Expert>)request.getAttribute("exps");

%>

<jsp:include page="/header.jsp"></jsp:include>
<iframe id="gm" width="100%" height="100%" src="<%=request.getAttribute("adminurl") %>" name="admin"></iframe>
<jsp:include page="/footer.jsp"></jsp:include>