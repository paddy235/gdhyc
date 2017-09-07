   <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <div class="tab-pane" ng-controller="fahbCtrl">
	<%--    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{title}}</div>
	    </div>--%>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label span1">日期:</label>
					<input style="width: 120px;float: left;" datepicker0 ng-model="search.sdate"   ng-change="" type="text">
                    <label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label span1">至</label>
                    <input style="width: 120px;float: left;" datepicker0 ng-model="search.edate"  ng-change="" type="text">
                    <button style="margin-left:10px;" ng-click="update()" class="btn btn-primary"><i class="icon-plus icon-white"></i>生成</button>
		            <button style="margin-left: 5px;"  id="refresh" class="btn btn-success" ng-click="refresh()">
		            	<i class=" icon-refresh icon-white"></i>刷新
		            </button>
				</div>
			</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
					<thead>
						<tr>
							<th style="text-align: center; width: 20px;"></th>
							<th style="text-align: center;">日期</th>
							<th style="text-align: center;">供应商名称</th>
							<th style="text-align: center;">来煤量</th>
							<th style="text-align: center;">车数</th>
							<th style="text-align: center;">低位热</th>
							<th style="text-align: center;">收到基硫</th>
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
			"ajax" : "gongys/fahb/getFahbInfo",
			"bAutoWidth" : true,
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
			'scrollX': "100%",
			'scrollCollapse': true,
			"aoColumnDefs": [{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
                },
                "bSortable": false,
                "aTargets": [0]
            }]
		});
	});
</script>