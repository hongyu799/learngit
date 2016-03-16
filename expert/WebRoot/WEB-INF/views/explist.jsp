<%@ page language="java" import="java.util.*,com.exp.model.Expert" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 List<Expert> exps = (List<Expert>)request.getAttribute("explist");
 request.getSession().setAttribute("explist",exps);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'explist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="<%=basePath %>css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath %>js/jquery1.42.min.js"></script>
	<script type="text/javascript">
	
	
	
		function closewBox(){
		<%if(null==exps||exps.size()==0){ %>
			return false; 
		<%} %>
		var nexp=getvalue();
		var oexp=parent.getvalue();
			$.ajax({  
		        type: "post",  
		       	url: "<%=basePath%>exp.do?method=update",    
		       	async:false,  
		      	dataType:"json",
		        data: {
					proid : <%=request.getAttribute("proId") %>,
					expnew : nexp,
					expold : oexp
				}, 
		         success: function(data){  
		        	parent.lstbox.close(0);				
		        }  
			});	

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
	</script>
  </head>
  
  <body>
    <table cellpadding="0" cellspacing="0" class="gxj_tab1">
	<colgroup>
			<col width="8%" />
			<col width="15%" />
			<col width="" />			
	</colgroup>
	<thead>
		<tr>
    		<th>全选</th>
            <th>专家姓名</th>
            <th>联系方式</th>
            <th>专业</th>
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
		            <td><%=exps.get(i).getYiDongDianHua() %></td>
		            <td><%=exps.get(i).getZhuanYe1() %>&nbsp;<%=exps.get(i).getZhuanYe2() %>&nbsp;<%=exps.get(i).getZhuanYe3() %></td>
		    	</tr>
    			<%
    		}
    	}
     %>
</table>
<div class="gxj_button"><a href="javascript:closewBox();">确定</a></div><div class="gxj_button"><a href="javascript:qfdx();" id="duanxin">群发短信</a></div>
  </body>
</html>
