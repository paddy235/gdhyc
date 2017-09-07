<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="changeCaigdd_yunzfCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{changeCaigdd_yunzfTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<button style="margin-left: 20px;" ng-click="queren()" class="btn btn-success">
						<i class="icon-ok-sign icon-white"></i> 确认
					</button>
					<button style="margin-left: 10px;" ng-click="cancel()" class="btn">
						<i class="icon-remove-circle"></i> 取消
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
						<th style="text-align: center; width: 100px;">采购订单编号</th>
						<th style="text-align: center; width: 100px;">供应商</th>
						<th style="text-align: center; width: 100px;">开始时间</th>
						<th style="text-align: center; width: 100px;">结束时间</th>
						<th style="text-align: center; width: 100px;">计划口径</th>
						<th style="text-align: center; width: 100px;">采购订单类型</th>
						<th style="text-align: center; width: 100px;">品种</th>
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
			'ajax' : 'kucgl/caigrk/getCaigdd',
			//"sPaginationType" : "full_numbers",
			'bAutoWidth' : true,
			'bFilter':false,
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
			"bScrollCollapse": true,
			"aoColumnDefs": [{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)"/>';
                },
                "bSortable": false,
                "aTargets": [0]
            }]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
</script>