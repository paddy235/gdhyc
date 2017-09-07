   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="tab-pane" ng-controller="meikrjsCtrl" >
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 65px;height: 30px;line-height: 30px;float: left;" class="control-label">发货日期:</label>
					<input style="width: 88px;float: left;" id="datepicker" ng-model="search.sDate" ng-change="refresh()" type="text">
					<label style="width:15px;height:30px;line-height:30px;margin-left:5px;margin-right:5px;float:left;" class="control-label">至</label>
					<input style="width: 88px;float:left;" id="datepicker1" ng-model="search.eDate" ng-change="refresh()" type="text">
					<!-- 
					<label  style="width: 65px;height: 30px;line-height: 30px;float: left;" class="control-label span1">供货单位:</label>
					<select style="width: 90px;float: left;" ng-model="search.gongys" ng-change="refresh()" ng-options="option.value as option.name for option in gongysList"></select>
		            <label  style="width: 65px;height: 30px;line-height: 30px;float: left;" class="control-label span1">采购订单:</label>
					<select style="width: 90px;float: left;" ng-model="search.caigdd" ng-change="refresh()" ng-options="option.value as option.name for option in caigddbList2"></select>
		            <label  style="width: 55px;height: 30px;line-height: 30px;float: left;" class="control-label span1">合同号:</label>
					<select style="width: 90px;float: left;" ng-model="search.hetbh" ng-change="refresh()" ng-options="option.value as option.name for option in hetbhList"></select>
		            -->
		            <button style="margin-left:20px;" id="jies" ng-click="jies()" class="btn btn-success">
		            	<i class="icon-list-alt icon-white"></i> 结算
		            </button>
				</div>
			</div>
			<div class="row-fluid" >
				<table class="table table-striped table-bordered table-hover" id="example">
	             <thead>
	                 <tr>
	                 	<th style="width: 30px;"></th>
	                 	<th style="text-align: center; width: 100px;">供货单位</th>
	                    <th style="text-align: center; width: 100px;">煤矿单位</th>
	                    <th style="text-align: center; width: 100px;">发货日期</th>
	                    <th style="text-align: center; width: 100px;">到货日期</th>
	                    <th style="text-align: center; width: 100px;">发站</th>
	                    <th style="text-align: center; width: 50px;">车数</th>
	                    <th style="text-align: center; width: 50px;">票重</th>
	                    <th style="text-align: center; width: 50px;">盈亏</th>
	                    <th style="text-align: center; width: 50px;">运损</th>
	                    <th style="text-align: center; width: 50px;">净重</th>
	                    <th style="text-align: center; width: 100px;">合同号</th>
	                    <th style="text-align: center; width: 50px;">Qnetar(mj/kg)</th>
	                    <th style="text-align: center; width: 100px;">std(%)</th>
	                    <th style="text-align: center; width: 50px;">mt(%)</th>
	                 </tr>
	             </thead>
	         </table>
	       </div>
		</div>
</div>
<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		$("#datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true/*,
			orientation:"bottom auto"*/
		});
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true/*,
			orientation:"bottom auto"*/
		});
		
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'jiesgl/meikjs/getJiesList',
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
			"sScrollXInner": "170%",
			"bScrollCollapse": true,
			"aoColumnDefs": [{
	            "sClass": "center",
	            "mRender": function(oObj, sVal) {
	            	if(oObj!=""){
	            		return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
	            	}
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