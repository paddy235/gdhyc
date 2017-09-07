<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style>
tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>

<div class="block" ng-controller="shujsh_mxCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{shujsh_mxTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<button style="margin-left: 10px;" ng-click="cancel()" class="btn">返回</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<table class="table table-striped table-bordered table-hover"
			id="example">
			<thead>
				<tr>
					<!-- 
					<th style="text-align: center; width: 200px;">供货单位</th>
					<th style="text-align: center; width: 200px;">煤矿单位</th>
					<th style="text-align: center; width: 100px;">发站</th>
					<th style="text-align: center; width: 100px;">品种</th>
					<th style="text-align: center; width: 150px;">发货日期</th>
					<th style="text-align: center; width: 150px;">重车日期</th>
					 -->
					<th style="text-align: center; width: 100px;">序号</th>
					<th style="text-align: center; width: 100px;">车号</th>
					<th style="text-align: center; width: 100px;">毛重</th>
					<th style="text-align: center; width: 100px;">皮重</th>
					<th style="text-align: center; width: 100px;">票重</th>
					<th style="text-align: center; width: 100px;">净重</th>
					<th style="text-align: center; width: 100px;">运损</th>
					<th style="text-align: center; width: 100px;">总扣吨</th>
					<th style="text-align: center; width: 100px;">录入员</th>
					<th style="text-align: center; width: 200px;">录入时间</th>
					<!-- 
					<th style="text-align: center; width: 200px;">计划口径</th>
					<th style="text-align: center; width: 200px;">到货日期</th>
					<th style="text-align: center; width: 100px;">车次</th>
					<th style="text-align: center; width: 200px;">重车衡号</th>
					<th style="text-align: center; width: 100px;">到站</th>
					-->
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : '',
			//"sPaginationType" : "full_numbers",
			'bFilter' : false,
			"language" : {
				"sLengthMenu" : "每页显示 _MENU_条",
				"sZeroRecords" : "没有找到符合条件的数据",
				"sProcessing" : "数据加载中...",
				"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
				"sInfoEmpty" : "没有记录",
				"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
				"sSearch" : "搜索：",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "前一页",
					"sNext" : "后一页",
					"sLast" : "尾页"
				}
			},
//			"sScrollX": "100%",
//			"sScrollXInner": "900px",
			"bScrollCollapse": true,
			"aoColumnDefs": [{"aTargets": [1,8,9],"sClass": "center"},
			                 {"sClass": "right","aTargets": [0,2,3,4,5,6,7]}
           ]
		});
	});
</script>