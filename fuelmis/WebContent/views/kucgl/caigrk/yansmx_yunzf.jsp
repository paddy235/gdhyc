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

<div class="block" ng-controller="yansmx_yunzfCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{yansmx_yunzfTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 80px;" class="control-label">创建日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width: 15px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 查询
					</button>
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
						<th style="text-align: center; width: 100px;">供应商</th>
						<th style="text-align: center; width: 100px;">车号</th>
						<th style="text-align: center; width: 100px;">日期</th>
						<th style="text-align: center; width: 100px;">车数</th>
						<th style="text-align: center; width: 100px;">净重(吨)</th>
						<th style="text-align: center; width: 100px;">票重(吨)</th>
						<th style="width: 40px;text-align: center;">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<div class="modal fade" id="myModal_View" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  style="display: none;width:950px;left:35%;">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" 
	               aria-hidden="true">×
	            </button>
	            <h4 class="modal-title" id="myModalLabel">查看明细</h4>
	         </div>
	         <div class="modal-body" >
	            <div class="block-content collapse in">
					<div class="row-fluid" style="width: 100%;">
						<table class="table table-striped table-bordered table-hover" id="example2">
							<thead>
								<tr>
									<th style="text-align: center; width: 100px;">供应商</th>
									<th style="text-align: center; width: 100px;">车号</th>
									<th style="text-align: center; width: 100px;">日期</th>
									<th style="text-align: center; width: 100px;">净重(吨)</th>
									<th style="text-align: center; width: 100px;">票重(吨)</th>
									<th style="text-align: center; width: 100px;">备注</th>
								</tr>
							</thead>
						</table>
					</div>
			 	</div>
	         </div>
	      </div>
	   </div>
	</div>
</div>

<script type="text/javascript">
	var oTable;
	var oTable2;
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
		
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
		
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'kucgl/caigrk/getYansmx?yewlx=2',
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
            },{"aTargets": [1,2,3],"sClass": "center"},{"aTargets": [4,5,6],"sClass": "right"},
            {
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<button onclick="chakmx('+oObj+')" class="btn btn-success"><i class="icon-ok-sign icon-white"></i>查看</button>';
                },
                "bSortable": false,
                "aTargets": [7]
            }]
		});
		
		oTable2 = $('#example2').dataTable({
			"processing" : true,
			'ajax' : '',
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
			"aoColumnDefs": [{"aTargets": [0,1,2,5],"sClass": "center"},{"aTargets": [3,4],"sClass": "right"}]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
</script>