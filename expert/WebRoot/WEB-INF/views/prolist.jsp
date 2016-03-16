<%@ page language="java" import="java.util.*,com.exp.model.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			PageBean pageInfo=(PageBean)request.getAttribute("page");
%>

<jsp:include page="/header.jsp"></jsp:include>

<body>
<!-- 表单 -->
<div class="sqd_contentbg" style="background: url(images/sqd_conterbg1.png) no-repeat top center;">
	<div class="sqd_content" style=" padding-top:239px;">
    <div class="sqd_experttit">
    <div class="sqd_anniu"><a href="javascript:opennew();">创建项目</a></div>
	<span class="span1">当前位置：<span style="color:#3d7fbb;">项目列表</span></span>
    
</div>


<table cellpadding="0" cellspacing="0" class="sqd_tab2">
	<colgroup>
			<col width="8%" />
			<col width="20%" />
			<col width="20%" />
			<col width="20%" />
			<col width="20%" />
            <col width="12%" />
	</colgroup>
	<thead>
		<tr>
    		<th></th>
            <th>项目名称</th>
            <th>项目背景</th>
            <th>所属类型</th>
            <th>项目状态</th>
            <th>操作</th>
    	</tr>
    </thead>
    <tbody>
    <%
    	List<Project> pros = (List<Project>) request.getAttribute("pros");
    	if (null != pros) {
    		for (int i = 0; i < pros.size(); i++) {
	%>
		<tr>
    			<td><%=i+1 %></td>
            <td><%=pros.get(i).getXiangMuMingCheng() %></td>
            <td><%=pros.get(i).getXiangMuBeiJing() %></td>
            <td>
            	<%=pros.get(i).getRuanJianKaiFa() %>&nbsp;
            	<%=pros.get(i).getXiTongJiCheng() %>&nbsp;
            	<%=pros.get(i).getWangLuoGongCheng() %>&nbsp;
            	<%=pros.get(i).getXinXiAnQuan() %>&nbsp;
            	<%=pros.get(i).getJiFangJianShe() %>&nbsp;
            	<%=pros.get(i).getYunWeiGuanLi() %>
            </td>
            <td><%=pros.get(i).getStatus() %></td>
            <td>
            <%if(pros.get(i).getDocStatus()!=23){ %>
           	 &nbsp;<a href="<%=basePath %>pro.do?method=viewpro&tp=1&proid=<%=pros.get(i).getWcmivTableId() %>">修改</a>&nbsp;
            <%} %>
            <a href="<%=basePath %>pro.do?method=viewpro&tp=2&proid=<%=pros.get(i).getWcmivTableId() %>">查看</a><!-- |&nbsp;删除&nbsp; --></td>
    	</tr>
	<%
    		}
    	}
    %>
    	
   </tbody>
</table>
<!-- 专家列表 -->

<!-- 分页 -->
<div class="sqd_cre sqd_mb70">
	<%
	if(pageInfo.getPageNum()==1){
	%>
	<a class="sqd_leftinf">上一页</a>
	<%
	}else{
	%>
	<a class="sqd_leftinf" href="<%=basePath %>pro.do?method=prolist&p=<%=pageInfo.getPageNum()-1 %>">上一页</a>
	<%
	}
	%>
	<%for(int i=0;i<pageInfo.getMaxPage();i++){
		if(pageInfo.getPageNum()==(i+1)){
	%>
		<div class="sqd_num"><%=i+1 %></div>
	<%
		}else{	
	%>
		<div class="sqd_num"><a href="<%=basePath %>pro.do?method=prolist&p=<%=i+1 %>"><%=i+1 %></a></div>
	<%} 
	}%>
	
	<%
	if(pageInfo.getPageNum()==pageInfo.getMaxPage()){
	%>
	<a class="sqd_rightinf">下一页</a>
	<%
	}else{
	%>
	<a class="sqd_rightinf" href="<%=basePath %>pro.do?method=prolist&p=<%=pageInfo.getPageNum()+1 %>">下一页</a>
	<%
	}
	%>
	
</div>

<script type="text/javascript">
	var winObj; 
	var loop;
	function opennew(){
		winObj = window.open("<%=basePath %>pro.do?method=apro","新建项目","alwaysRaised=yes, resizable=no, toolbar=no, scrollbars=no, titlebar=no, z-look=yes, menubar=no, location=no");
		loop = setInterval(function() {     
	    if(winObj.closed) {    
	        clearInterval(loop);
	        location.reload();  
	        //getproid(<%=null!=request.getAttribute("pro")?((Project)request.getAttribute("pro")).getWcmivTableId():-1 %>);
	    }    
	}, 1000);
	}
	
	function getproid(proid){
		$.ajax({  
        type: "post",  
       	url: "<%=basePath %>adm.do?method=getn",    
       	async:false,  
      	dataType:"json",
        data: {}, 
         success: function(data){
         location.reload();
			if(data.success && data.pid > proid){
				window.location.href="<%=basePath %>adm.do?method=view";
			}else{
				alert("项目添加失败");
				location.reload();
			}
        }  
	});
	}
</script>
<!-- // 分页 -->
</div>
</div>
<!-- // 表单 -->

<jsp:include page="/footer.jsp"></jsp:include>