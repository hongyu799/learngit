<%@ page language="java" import="java.util.*,com.exp.model.*" pageEncoding="utf-8"%>
<%@page import="org.apache.taglibs.bsf.expression"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Project project = (Project)request.getAttribute("pro");
List<Expert> exps = (List<Expert>)request.getAttribute("exps");
%>

<jsp:include page="/header.jsp"></jsp:include>
<a id="texplist"></a>
<body>
<!-- 表单 -->
<div class="sqd_contentbg" style="background: url(images/sqd_conterbg1.png) no-repeat top center;">
	<div class="sqd_content" style=" padding-top:239px;">
	<div id="myPrintArea">
	
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
	    
		<span class="span1">当前位置：<span style="color:#3d7fbb;"><a href="<%=basePath %>pro.do?method=prolist">项目列表</a>&nbsp;>&nbsp;查看项目</span></span>
	</div>

<% boolean isAdmin=(Boolean)request.getAttribute("isAdmin");
if(isAdmin||(null!=project && (project.isNewDoc()||project.isFinishDoc()))){ %>
<input type="text" style="BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: solid; width: 180px; height: 24px; color:#666666; " 
onfocus="if(this.value=='        在这里输入地点   ') {this.value='';}this.style.color='#333333';" onblur="if(this.value=='') {this.value='        在这里输入地点   ';this.style.color='#666666';}" value="        在这里输入地点   ">
	     <input type="text" style="float:right; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: solid; width: 180px; height: 24px; color:#666666;  " 
	     onfocus="if(this.value=='        在这里输入时间   ') {this.value='';}this.style.color='#333333';" onblur="if(this.value=='') {this.value='        在这里输入时间   ';this.style.color='#666666';}" value="        在这里输入时间   "><br/>
<table cellpadding="0" cellspacing="0" class="sqd_tab1">
	<colgroup>
				<col width="8%" />
				<col width="25%" />
				<col width="10%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="15%" />
				<col width="5%" />
		</colgroup>
		<thead>
			<tr>
	            <th>姓名</th>
	            <th>专业</th>
	            <th>电话</th>
	            <th>职位</th>
	            <th>职称</th>
	            <th>金额</th>
	            <th>工作单位</th>
	            <th>学历</th>
	    	</tr>
	    </thead>
    <tbody>
    	<%
	    	if(null != exps){
	    		for(int i=0; i<exps.size(); i++){
	    			%>
	    			<tr>
			            <td><%=exps.get(i).getXingMing() %></td>
			            <td><%=exps.get(i).getZhuanYe1() %>&nbsp;<%=exps.get(i).getZhuanYe2() %>&nbsp;<%=exps.get(i).getZhuanYe3() %></td>
			            <td><%=exps.get(i).getYiDongDianHua() %></td>
			            <td><%=exps.get(i).getZhiWu() %></td>
			            <td><%=exps.get(i).getZhiCheng() %></td>
			            <td><input type="text" style=" width: 70px; height: 24px ; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: 
			            none; BORDER-BOTTOM-STYLE: solid ;">元</td>
			            <td><%=exps.get(i).getGongZuoDanWei() %></td>
			            <td><%=exps.get(i).getXueLi() %></td>
			    	</tr>
	    			<%
	    		}
	    	}
	     %>
   </tbody>
</table>
<input type="text" style="float:right; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: solid; width: 180px; height: 16px; color:#666666;  " 
         onfocus="if(this.value=='        在这里输入单位   ') {this.value='';}this.style.color='#333333';" onblur="if(this.value=='') {this.value='        在这里输入单位   ';this.style.color='#666666';}" value="        在这里输入单位   "><br/>
	     <input type="text" style="float:right; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: solid; width: 180px; height: 16px; color:#666666;  " 
	     onfocus="if(this.value=='        在这里输入日期   ') {this.value='';}this.style.color='#333333';" onblur="if(this.value=='') {this.value='        在这里输入日期   ';this.style.color='#666666';}" value="        在这里输入日期   ">
	
<!-- 专家列表 -->
</div>

<div class="sqd_print"><a href="javascript:window.print();">打印</a></div>
<script type="text/javascript">
function dayin(){



}
		var listurl="<%=basePath %>exp.do?method=query&proid=<%=null==project?"0":project.getWcmivTableId() %>";
		var lstbox=$("#texplist").wBox({requestType:"iframe",iframeWH:{width:800,height:400},title: "",target:listurl});
		
		function getList(){
			lstbox.showBox();
		}
		
		function showDel(delid,pid){
			var delurl="<%=basePath %>exp.do?method=query&proid=<%=null==project?"0":project.getWcmivTableId() %>";
			var delbox=$(delid).wBox({requestType:"iframe",iframeWH:{width:800,height:400},title: "",target:delurl});
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