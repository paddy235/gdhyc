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

<div class="tab-pane" ng-controller="meikdqCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{meikdqTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<button style="margin-left: 10px;" ng-click="addMeikdq()" class="btn btn-primary">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="editMeikdq()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 20px;"></th>
						<th style="text-align: center; ">序号</th>
						<th style="text-align: center; ">名称</th>
						<th style="text-align: center; ">全称</th>
						<th style="text-align: center; ">编码</th>
						<th style="text-align: center; ">省份</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'meikdq/getAll',
			'bAutoWidth' : true,
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
			"aoColumnDefs": [{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
                },
                "bSortable": false,
                "aTargets": [0]
            },{"aTargets": [2,3,4,5],"sClass": "center"},{"aTargets": [1],"sClass": "right"}]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
</script>