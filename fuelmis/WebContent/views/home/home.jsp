<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
 	String path = request.getContextPath();
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="gddlMain">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/js/bootstrap-3.3.5/css/bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/bootstrap-table/bootstrap-table.css">
<!-- <script type="text/javascript" src="${ctx}/js/bootstrap-3.3.5/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="${ctx}/js/bootstrap-table/bootstrap-table.js?version=20160422115342"></script>
<!-- 首页home -->
<script type="text/javascript" src="${ctx}/js/views/main/home.js"></script>
<style>
*{margin:0;padding:0;}
body{font-size:12px;}
a{font-weight: bold;}
li{list-style:none;}
#top{height:40px;line-height:40px;position:relative;color:#ddd;padding-left:10px;margin-top:10px;font-weight:bold;background-color:#333;background-image:linear-gradient(#4d4d4d,#2a2a2a);border-radius:5px;}
#top li{float:left;height:40px;line-height:40px;font-size:15px;margin-right:20px;width:100px;text-align:center;cursor:pointer;}
#top .liactived{background:#000;border-radius:3px;color:#fff;}
#nowLocation{padding-left:10px;height:30px;line-height:30px;margin-top:10px;border:1px solid #ddd;border-radius:5px;}
#scrollDiv{margin-top:10px;background:#d6f6fd;height:30px;line-height:30px;border:1px solid #ddd;font-size:15px;font-weight:bold;}
.firstlayer{width:100%; margin:10px 0;height:300px;}
#fl-left{width:49%;height:300px;margin-right:10px;float:left;}
@media all and (max-width: 1300px) {
#fl-left{width:100%;height:300px;margin-right:10px;float:left;}
}
#fl-middle{width:49%;height:300px;margin-right:10px;float:left;}
#fl-right{width:49%;height:300px;float:left;}
#fl-rightf{width:50%;height:300px;float:left;border:1px solid #ddd;border-radius:3px;}
@media all and (max-width: 1300px) {
#fl-rightf{width:100%;height:300px;float:left;border:1px solid #ddd;border-radius:3px;}
}
.secondlayer{margin:10px 5px 10px 0;height:610px; display:inline-block;width:49%}
.thirdlayer{margin:10px 5px 10px 0;height:610px; display:inline-block;width:49.9%}
.fourthlayer{margin:10px 0;height:300px;margin-bottom:40px;}

.glyphicon-arrow-up{  color:green;}
.glyphicon-arrow-down{  color:red;}
#gyspie{height:267px;}
#mzpie{height:267px;}

#sl-left{width:100%;height:300px;margin-right:10px;float:left;}
.sll-navs{height:30px;border-bottom:1px solid #ddd;color:#337ab7;}
.sll-navs li{float:left;height:29px;line-height:29px;font-size:12px;font-weight:bold;padding:0 10px;cursor:pointer;border:1px solid #f5f5f5;border-bottom-color:#ddd;}
.sliactive{background:#fff;height:29px;line-height:29px;color:#555;border:1px solid #ddd;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-color:#fff;}
#sl-right{width:100%;height:300px;float:left;}
@media all and (min-width: 1300px) {
#sl-right{margin-top:10px;}
}
.tab-border{border:1px solid #ddd;border-bottom-left-radius:3px;border-bottom-right-radius:3px;border-top:none;}
#kucundiv{height:268px;}
#laihaomeidiv{height:268px;}
#kucunqkline{height: 237px;}
#laihaomeiqkline{height: 237px;}
@media all and (max-width: 1300px) {
.secondlayer{margin:10px 5px 10px 0;height:310px; display:inline-block;width:100%}
#sl-left{width:49%;height:300px;margin-right:10px;float:left;}
#sl-right{width:49%;height:300px;float:left;}
}
/*#tl-left{width:100%;height:300px;margin-right:10px;float:left;}*/
#tl-right{width:100%;height:610px;float:left;}
#kucunmeiline{height:267px;}
#fenkrcbmdjdiv{height:268px;}
#ructrmjdiv{height:268px;}
#rucbmdjdiv{height:268px;}
#fenkrcbmdjline{height: 237px;}
#ructrmjline{height: 237px;}
#rucbmdjline{height: 237px;}
@media all and (max-width: 1300px) {
.thirdlayer{margin:10px 5px 10px 0;height:610px; display:inline-block;width:100%}
#tl-right{width:100%;height:610px;float:left;}

}

/* tab panel 不显示Echarts  图表问题   */
/* bootstrap hack: fix content width inside hidden tabs */
#sl-left .tab-content > .tab-pane,
.pill-content > .pill-pane {
    display: block;     /* undo display:none          */
    height: 0;          /* height:0 is also invisible */
    overflow-y: hidden; /* no-overflow                */
}
#sl-left .tab-content > .active,
.pill-content > .active {
    height: 258px;       /* let the content decide it  */
} /* bootstrap hack end */

#tl-right .tab-content > .tab-pane,
.pill-content > .pill-pane {
    display: block;     /* undo display:none          */
    height: 0;          /* height:0 is also invisible */
    overflow-y: hidden; /* no-overflow                */
}
#tl-right .tab-content > .active,
.pill-content > .active {
    height: 258px;       /* let the content decide it  */
} /* bootstrap hack end */

#fol-left{width:49%;height:300px;margin-right:10px;float:left;}
#fol-right{width:50%;height:298px;float:left;border:1px solid #ddd;border-radius:3px;}
@media all and (max-width: 1300px) {
#fol-left{width:100%;height:300px;margin-right:10px;float:left;}
#fol-right{width:100%;height:298px;float:left;border:1px solid #ddd;border-radius:3px;}
}
.nav {
	margin-bottom: 0;
}
ul, ol {
    padding: 0;
    margin: 0;
}
#pengding{
		width:25%;
		height:38%;
		position:fixed;
		bottom:-300px;
		right:30px;
		transition: bottom 2s;
		-moz-transition: bottom 2s;	/* Firefox 4 */
		-webkit-transition: bottom 2s;	/* Safari 和 Chrome */
		-o-transition: bottom 2s;	/* Opera */
		border:1px solid #337ab7;
	}
	#pengdingT{
		padding:5px 15px;
	}
	#pengdingB{
		height:200px;
		overflow-y: auto;
	}
	.title{
		font-size: 14px;
		color:#fff;
		background-color:#337ab7;
	}
	.close{
		width:30px;
		text-align: center;
		color:white;
		border:1px solid white;
	}
	#dataShow{
		display: inline-block;
		width:33%;
		margin-right:20px;
	}
	
</style>
</head>
<body>
<div class="container-fluid" style="padding:10px;" ng-controller="homeCtrl">
	<!-- <a href="javascript:void(0);" ng-click="showView()">综合展示</a> -->
	<!-- 顶部tab切换导航条 -->
	<!-- 
		<div id="top">
		<li class="liactived">红雁池</li>
		<li>燃料成本</li>
	</div>-->
	<!-- 当前位置 -->
	<!-- 
	<div id="nowLocation">
		<span style="color:#369;font-size:13px;font-weight:bold;">刷新</span>&gt;&gt;红雁池
	</div>
	-->
	
	<!-- 滚动新闻 -->
	<div id="scrollDiv">
		<marquee behavior="alternate" scrollamount="5" onmouseover="this.stop()" onmouseout="this.start()">
		
		</marquee>
	</div>
	<!-- 主内容区第一块 -->
	<div class="firstlayer">
		<div id="fl-left">
			<div class="panel panel-default">
			  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
			  	<b>来耗存数据</b>&nbsp;&nbsp;|日期:
			  	<input id="lhcrq" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
			  	<span style="float:right;margin-right:10px;">单位:吨,%</span>
			  </div>
			  <div class="panel-body" style="padding:0;height:268px;">
				<div>
					<table class="table table-striped table-bordered table-hover" id="tableL01"></table>
			  	</div>
		  	  </div>
			</div>
		</div>
		<div id="fl-right">
			<%--<div id="fl-middle">
				<div class="panel panel-default">
					  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
					  	<b>供应商</b>&nbsp;&nbsp;|年月:
					  	<input id="gysny" class="ymdatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:70px;" >
					  	<span style="float:right;margin-right:10px;">单位:%</span>
					  </div>
					  <div class="panel-body" style="padding:0;">
					    <div id="gyspie"></div>
					  </div>
				</div>
			</div>--%>
<%--			<div id="fl-right">
				<div class="panel panel-default">
				  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
				  	<b>煤种</b>&nbsp;&nbsp;|年月:
				  	<input id="mzny" class="ymdatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:70px;" >
				  	<span style="float:right;margin-right:10px;">单位:%</span>
				  </div>
				  <div class="panel-body" style="padding:0;">
				    <div id="mzpie"></div>
				  </div>
				</div>
			</div>--%>
            <!--文件通知-->
            <div ng-include="'views/home/wenjtz.jsp'">
            </div>
		</div>
		
	</div>
	<!-- 主内容区第二块 -->
	<div class="secondlayer">
		<div id="sl-left">
			<ul class="sll-navs" style="background:#f5f5f5;border-radius:3px;">
			   <li class="sliactive" onclick="showSecLeft(1,this)">库存情况曲线图</li>
			   <li onclick="showSecLeft(2,this)">来耗煤情况曲线图</li>
			</ul>
			<div class="span12 tab-border" style="margin-left:0;">
			   <div id="kucundiv">
			   		<div class="panel panel-default" style="border: none;">
					  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
					  	起始日期&nbsp;:&nbsp;
					  	<input id="kcqxqs" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
			
					  	&nbsp;&nbsp;&nbsp;&nbsp;截止日期&nbsp;:&nbsp;
					  	<input id="kcqxjz" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >

					  	<span style="float:right;margin-right:10px;">单位:万吨</span>
					  </div>
					  <div class="panel-body" style="padding:0;">
					    <div id="kucunqkline"></div>
					  </div>
					</div>
			   </div>
			   <div id="laihaomeidiv" style="display:none;">
			   		<div class="panel panel-default" style="border: none;">
					  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
					  	起始日期&nbsp;:&nbsp;
					  	<input id="lhmqxqs" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	&nbsp;&nbsp;&nbsp;&nbsp;截止日期&nbsp;:&nbsp;
					  	<input id="lhmqxjz" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	<span style="float:right;margin-right:10px;">单位:万吨</span>
					  </div>
					  <div class="panel-body" style="padding:0;">
					    <div id="laihaomeiqkline"></div>
					  </div>
					</div>
			   </div>
			</div>
		</div>
		<div id="sl-right">
			<ul class="sll-navs" style="background:#f5f5f5;border-radius:3px;">
			   <li class="sliactive" onclick="showTlright(1,this)" >分矿入厂标煤单价</li>
			   <li onclick="showTlright(2,this)" style="">入厂天然煤价</li>
			   <li onclick="showTlright(3,this)">入厂标煤单价</li>
			</ul>
			<div class="span12 tab-border" style="margin-left:0;">
			   <div id="fenkrcbmdjdiv">
			   		<div class="panel panel-default" style="border: none;">
					  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
					  	起始日期&nbsp;:&nbsp;
					  	<input id="fkrcdjqs" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	&nbsp;&nbsp;&nbsp;&nbsp;截止日期&nbsp;:&nbsp;
					  	<input id="fkrcdjjz" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	<span style="float:right;margin-right:10px;">单位:吨,元/吨</span>
					  </div>
					  <div class="panel-body" style="padding:0;">
					    <div id="fenkrcbmdjline"></div>
					  </div>
					</div>
			   </div>
			   <div id="ructrmjdiv" style="display:none;">
			   		<div class="panel panel-default" style="border: none;">
					  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
					  	起始日期&nbsp;:&nbsp;
					  	<input id="rctrqs" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	&nbsp;&nbsp;&nbsp;&nbsp;截止日期&nbsp;:&nbsp;
					  	<input id="rctrjz" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	<span style="float:right;margin-right:10px;">单位:吨,元/吨</span>
					  </div>
					  <div class="panel-body" style="padding:0;">
					    <div id="ructrmjline"></div>
					  </div>
					</div>
			   </div>
			   <div id="rucbmdjdiv" style="display:none;">
			   		<div class="panel panel-default" style="border: none;">
					  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
					  	起始日期&nbsp;:&nbsp;
					  	<input id="rcbmqs" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	&nbsp;&nbsp;&nbsp;&nbsp;截止日期&nbsp;:&nbsp;
					  	<input id="rcbmjz" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					  	<span style="float:right;margin-right:10px;">单位:吨,元/吨</span>
					  </div>
					  <div class="panel-body" style="padding:0;">
					    <div id="rucbmdjline"></div>
					  </div>
					</div>
			   </div>
			</div>
		</div>
	</div>
	<!-- 主内容区第三块 -->
	<div class="thirdlayer">
	<!--  	<div id="tl-left">
			<div class="panel panel-default">
			  <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
			  	<b>库存煤曲线</b>&nbsp;&nbsp;|日期:
			  	<input id="kcmqx" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
			  	<span style="float:right;margin-right:10px;">单位:万吨,Mj/kg,元/吨</span>
			  </div>
			  <div class="panel-body" style="padding:0;">
			    <div id="kucunmeiline"></div>
			  </div>
			</div>
		</div>-->
		<div id="tl-right">
				<div class="sll-navs" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;background:#f5f5f5;border-radius:3px;">
					<b>来煤信息</b>|日期:
					<input id="lmxx" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					<span style="float:right;margin-right:10px;">单位:MJ/KG,元/吨,%</span>
				</div>
				<div class="panel-body" style="padding:0;height:268px;">
				<div>
					<table class="table table-striped table-bordered table-hover" id="tableL02"></table>
				</div>
				</div>
		</div>	
	</div>
	<!-- 主内容区第四块 -->
	<div class="fourthlayer">
		<div id="fol-left">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
					<b>入厂，入炉主要指标</b>|起始日期:
					<input id="rcrlqs" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					<i style="margin-right:20px;" class="glyphicon glyphicon-th"></i>
					截止日期:
					<input id="rcrljz" class="fydatepicker" type="text" style="margin-top:4px;height:22px;line-height:22px;width:100px;" >
					<span style="float:right;margin-right:10px;">单位:MJ/KG,%,吨</span>
				</div>
				<div class="panel-body" style="padding:0;height:268px;">
				<div>
					<table class="table table-striped table-bordered table-hover" id="tableL03"></table>
				</div>
				</div>
			</div>
		</div>
		<div id="fol-right"></div>
	</div>
	<!---------------------------------------右下弹出框----------------------------------------------------------------->

<%--	<div id="pengding" class="panel panel-primary">
		<div id="pengdingT" class="panel-heading title">
			<span id="dataShow"></span>			
		</div>
		<div id="pengdingB" class="panel-body">
			<div>
				<a href="#">测试代办事件，请在今天处理1</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理2</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理3</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理4</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理5</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理6</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理7</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理8</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理9</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理10</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理4</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理5</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理6</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理7</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理8</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理9</a>
			</div>
			<div>
				<a href="#">测试代办事件，请在今天处理10</a>
			</div>
		</div>
	</div>--%>
</div>
</body>
</html>
