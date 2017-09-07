<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="gddlMain">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@include file="../../common/common.jsp"%>
<script type="text/javascript" src="${ctx}/js/views/main.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/changnfycxMX.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="${ctx}/js/libs/html5shiv.min.js"></script>
  <script src="${ctx}/js/libs/respond.min.js"></script>
<![endif]-->
<title>欢迎</title>
</head>
<body>
	<div class="block" ng-controller="changnfycxMXCtrl">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left" style="margin-top: 10px;">{{changnfycxMXTitle}}</div>
		</div>
		<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<button style="margin-left: 10px;" ng-click="printPage()"
						class="btn">
						<i class="icon-print"></i>打印
					</button>
					<button style="margin-left: 10px;" my-export="厂内费用明细.xls"
						class="btn">
						<i class="icon-download-alt"></i>导出
					</button>
				</div>
			</div>
		</div>
		<div class="row-fluid" id="report"
			style="width: 97%; margin-left: auto; margin-right: auto;overflow: auto;"></div>
		<div id="pagination_box" class="pagination pagination-right"
			style="width: 97%; margin-left: auto; margin-right: auto;">
			<ul id="pagination_zc"></ul>
		</div>
	</div>
</body>
</html>