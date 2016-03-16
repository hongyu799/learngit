<%@ page language="java" import="java.util.*,com.exp.model.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/header.jsp"></jsp:include>
<body>
<div class="sqd_contentbg">
	<div class="sqd_content">
    	<div class="sqd_news">
      
            <div class="sqd_abstract"> 
            	<ul>
					专家库设立的程序 (一)单位推荐及个人申请的专家须于2010年10月底前,将填写好的《吉林省环境保护专家库成员资格(申请)推荐表》提交至省环境保护厅。 (二)创新驱动发展专题培训班在广州 政务动态 >> 刘炜副厅长出席2015中国科技创业人才投融资集训营（... (2015-09-23)
             	</ul> 
                <ul>
					专家库设立的程序 (一)单位推荐及个人申请的专家须于2010年10月底前,将填写好的《吉林省环境保护专家库成员资格(申请)推荐表》提交至省环境保护厅。
             	</ul>
                <ul>
					专家库设立的程序 (一)单位推荐及个人申请的专家须于2010年10月底前,将填写好的《吉林省环境保护专家库成员资格(申请)推荐表》提交至省环境保护厅。
             	</ul>  
            </div>
            <ul class="sqd_slide">
            	<li> </li>
                <li> </li>
                <li> </li>
            </ul>
        </div>
        </div>      
        <div class="sqd_button">
        	<ul>
            	<li><a id="exp" href=""><img src="images/sqd_zjsq.png" alt="" /></a></li>
                <li><a href="javascript:login();"><img src="images/sqd_xmzj.png" alt="" /></a></li>
            </ul>
        </div>
    </div>
</div>
        <script type="text/javascript">
			jQuery(".sqd_news").slide({mainCell:".sqd_abstract",autoPlay:true,titCell:".sqd_slide li",interTime:3000,delayTime:500,effect:"fold"});
			jQuery(".sqd_expertadmin").slide({ titCell:".sqd_admintit li",mainCell:".sqd_maininfo" });
			
			var curl="<%=basePath %>main.do?method=ecm";
			var lstbox=$("#exp").wBox({requestType:"iframe",iframeWH:{width:450,height:200},title: "",target:curl});
			
			function appc(type){
				window.location.href="<%=basePath %>register.do?method=reg&code=xxx";
			}
			
			function login(){
					$.ajax({  
				        type: "post",  
				       	url: "<%=basePath %>main.do?method=login",    
				       	async:false,  
				      	dataType:"json",
				        data: {	}, 
				         success: function(data){

							if(!data.success){
								alert(data.msg);
								return false;
							}else{
								window.location.href="<%=basePath %>pro.do?method=prolist";
							}
				        }  
				});
			}
		</script>
<jsp:include page="/footer.jsp"></jsp:include>


