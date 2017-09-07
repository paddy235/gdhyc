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

<div class="tab-pane" ng-controller="yunsdwCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{yunsdwTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 80px;margin-right:5px;" class="control-label">单位名称:</label>
					<select id="dianc_id" style="float: left;width: 120px;" ng-model="search.diancid"
						ng-options="option.value as option.name for option in diancList">
					</select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="addYunsdw()" class="btn btn-primary">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="editYunsdw()" class="btn btn-info">
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
						<th style="text-align: center; width: 120px;">名称</th>
						<th style="text-align: center; width: 200px;">全称</th>
						<th style="text-align: center; width: 200px;">单位地址</th>
						<th style="text-align: center; width: 100px;">邮政编码</th>
						<th style="text-align: center; width: 100px;">税号</th>
						<th style="text-align: center; width: 100px;">法定代表人</th>
						<th style="text-align: center; width: 100px;">委托代理人</th>
						<th style="text-align: center; width: 100px;">开户银行</th>
						<th style="text-align: center; width: 100px;">账号</th>
						<th style="text-align: center; width: 100px;">电话</th>
						<th style="text-align: center; width: 100px;">传真</th>
						<th style="text-align: center; width: 200px;">备注</th>
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
			'ajax' : 'yunsdw/getAll',
			//"sPaginationType" : "full_numbers",
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
			"sScrollX": "100%",
			//"sScrollXInner": "1540px",
			"bScrollCollapse": true,
			"aoColumnDefs": [{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
                },
                "bSortable": false,
                "aTargets": [0]
            },{"aTargets": [1,2,3,4,5,6,7,8,9,10,11,12],"sClass": "center"}]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
</script>