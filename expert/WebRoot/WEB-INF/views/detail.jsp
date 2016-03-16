<%@ page language="java" import="java.util.*,com.exp.model.*" pageEncoding="utf-8"%>
<%@page import="org.apache.taglibs.bsf.expression"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Project project = (Project)request.getAttribute("pro");
List<Expert> exps = (List<Expert>)request.getAttribute("exps");
request.getSession().setAttribute("smsUserList",exps);
%>

<jsp:include page="/header.jsp"></jsp:include>
<a id="texplist"></a>
<body>
<!-- 表单 -->
<div class="sqd_contentbg" style="background: url(images/sqd_conterbg1.png) no-repeat top center;">
	<div class="sqd_content" style=" padding-top:239px;">
    <div class="sqd_project">
    	<dl>
    	<dt>项目名称:</dt>
        <dd><%=null==project?"":project.getXiangMuMingCheng() %></dd>
    </dl>
    <dl>
    	<dt>项目背景:</dt>
        <dd><%=null==project?"":project.getXiangMuBeiJing() %></dd>
    </dl>
    <dl>
    	<dt>项目类型:</dt>
        <dd>
        	<ul>
            	<li><%=null==project?"":project.getRuanJianKaiFa() %></li>
                <li><%=null==project?"":project.getXiTongJiCheng() %></li>
                <li><%=null==project?"":project.getWangLuoGongCheng() %></li>
                <li><%=null==project?"":project.getXinXiAnQuan() %></li>
                <li><%=null==project?"":project.getJiFangJianShe() %></li>
                <li><%=null==project?"":project.getYunWeiGuanLi() %></li>
            </ul>
        </dd>
    </dl>
    <dl>
    	<dt>项目审批人:</dt>
        <dd><%=null==project?"":project.getNickName() %></dd>
    </dl>
     <dl>
    	<dt>专家人数:</dt>
        <dd><%=null==project?"":project.getZhuanJiaRenShu() %> 人</dd>
    </dl>
    </div>
    <div class="sqd_experttit">
	    <div class="sqd_anniu">项目专家</div>
		<span class="span1">当前位置：<span style="color:#3d7fbb;"><a href="<%=basePath %>pro.do?method=prolist">项目列表</a>&nbsp;>&nbsp;抽取专家</span></span>
	</div>

<% boolean isAdmin=(Boolean)request.getAttribute("isAdmin");
if(isAdmin||(null!=project && (project.isNewDoc()))){ %>
<table cellpadding="0" cellspacing="0" class="sqd_tab1">
	<colgroup>
			<col width="8%" />
			<col width="15%" />
			<col width="32%" />
			<col width="30%" />
			<col width="15%" />
			
	</colgroup>
	<thead>
		<tr>
    		<th>全选</th>
            <th>专家姓名</th>
            <th>专业</th>
            <th>联系电话</th>
            <th>操作</th>
    	</tr>
    </thead>
    <tbody>
    	<%
	    	if(null != exps){
	    		for(int i=0; i<exps.size(); i++){
	    			%>
	    			<tr>
			    		<td><input name="expid" value="<%=exps.get(i).getWcmivTableId() %>" type="checkbox" /></td>
			            <td><%=exps.get(i).getXingMing() %></td>
			            <td><%=exps.get(i).getZhuanYe1() %>&nbsp;<%=exps.get(i).getZhuanYe2() %>&nbsp;<%=exps.get(i).getZhuanYe3() %></td>
			            <td><%=exps.get(i).getYiDongDianHua() %></td>
			            <td><a id="del<%=exps.get(i).getWcmivTableId() %>" href="javascript:showDel('del<%=exps.get(i).getWcmivTableId() %>',<%=exps.get(i).getWcmivTableId() %>,<%=project.getWcmivTableId() %>);">删除</a></td>
			            <!-- <td><a href="<%=basePath %>exp.do?method=del&proid=<%=null==project?"0":project.getWcmivTableId() %>&tp=<%=request.getAttribute("tp") %>&exp=<%=exps.get(i).getWcmivTableId() %>">删除</a></td> -->
			    	</tr>
	    			<%
	    		}
	    	}
	     %>
   </tbody>
</table>
<div class="sqd_an">
<div class="sqd_zdcq"><a href="<%=basePath %>pro.do?method=fpro&id=<%=project.getWcmivTableId() %>&proid=<%=project.getDocumentId() %>">确定</a></div>
		<%if(null==exps||exps.size()==0) {
		%>
		<div class="sqd_zdcq"><a href="<%=basePath %>exp.do?method=exps&tp=<%=request.getAttribute("tp") %>&proid=<%=null==project?"0":project.getWcmivTableId() %>">自动抽取</a></div>
		<%
		}else{
		%>
		<!-- <div class="gxj_anniu"><a  href="javascript:getList();">选择修改</a></div> -->
		<div class="sqd_zdcq"><a href="<%=basePath %>exp.do?method=rexps&tp=<%=request.getAttribute("tp") %>&proid=<%=null==project?"0":project.getWcmivTableId() %>">补抽专家</a></div>
		<div class="sqd_zdcq"><a href="#" id="lstbox2">发送短信</a></div>
		<%
		}%>
 
</div>
<a id="ifres"></a>
<script type="text/javascript">
		var listurl="<%=basePath %>exp.do?method=query&proid=<%=null==project?"0":project.getWcmivTableId() %>";
		var smsurl="<%=basePath %>exp.do?method=smsUserList";
		var lstbox=$("#texplist").wBox({requestType:"iframe",iframeWH:{width:800,height:400},title: "",target:listurl});
		var lstbox2=$("#lstbox2").wBox({requestType:"iframe",iframeWH:{width:300,height:180},title: "",target:smsurl});
		//var smsSuccess=$("#texplist").wBox({requestType:"iframe",iframeWH:{width:300,height:150},title: "",target:<%=basePath%>smsSuccess.jsp});
		function getList(){
			lstbox.showBox();
		}
		
		var delbox;
		function showDel(delid,expid,pid){
			var delurl="<%=basePath %>exp.do?method=res&proid="+pid+"&expid="+expid;
			delbox=$(delid).wBox({requestType:"iframe",iframeWH:{width:800,height:400},title: "",target:delurl});
			delbox.showBox();
		}
		
		function appc(type) {
			location.reload();
		}
		
		function getvalue(){
			var ids="";
	    	var chkObjs = document.getElementsByName("expid");
	     	for(var i=0;i<chkObjs.length;i++){
	    		if(chkObjs[i].checked){
	   			ids=ids+(chkObjs[i].value+",");
	    		}
	 		}
			return ids;
		}
		
		function checkAll(){
			var chkObjs = document.getElementsByName("expid");
			for(var i=0;i<chkObjs.length;i++){
				chkObjs[i].checked = true;
			}
		}
	</script>
<%} %>


</div>
</div>
<!-- // 表单 -->

<jsp:include page="/footer.jsp"></jsp:include>