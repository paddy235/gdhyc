<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="huayejshCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{huayejshTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 80px;" class="control-label">单位名称:</label>
					<select ng-model="search.diancid"  style="float: left;width: 120px;"
						ng-options="option.value as option.name for option in diancList"></select>
					<label style="width: 80px;" class="control-label">起始日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate"
						onblur="loadJincpch()">
					<label style="width: 80px;" class="control-label">截止日期</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate"
						onblur="loadJincpch()">
					<label style="width: 100px;" class="control-label">进厂批次号:</label>
					<select id="selJincpch"  style="float: left;width: 100px;"></select>
					<button style="margin-left: 20px;" ng-click="searchData()"
						class="btn btn-success">
						<i class="icon-search icon-white"></i>刷新
					</button>
					<button style="margin-left: 10px;" ng-click="shenh()" class="btn">审核</button>
					<!-- <button style="margin-left: 10px;" ng-click="huit()" class="btn">回退</button>  -->
					<button style="margin-left: 10px;" ng-click="showAvg()" class="btn">查看平均值</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<table class="table table-striped table-bordered table-hover"
			id="example">
			<thead>
				<tr>
					<th style="width: 20px;"></th>
					<th></th>
					<th style="text-align: center; width: 250px;">煤矿单位</th>
					<th style="text-align: center; width: 150px;">发站</th>
					<th style="text-align: center; width: 150px;">品种</th>
					<th style="text-align: center; width: 150px;">化验时间</th>
					<th style="text-align: center; width: 150px;">化验编号</th>
					<th style="text-align: center; width: 150px;">车数</th>
					<th style="text-align: center; width: 150px;">数量(吨)</th>
					<th style="text-align: center; width: 150px;">收到基低位热量<br/>Qnet,ar(Mj/kg)</th>
					<th style="text-align: center; width: 150px;">收到基灰分<br/>Aar(%)</th>
					<th style="text-align: center; width: 150px;">干燥基灰分<br/>Ad(%)</th>
					<th style="text-align: center; width: 200px;">干燥无灰基挥发分<br/>Vdaf(%)</th>
					<th style="text-align: center; width: 100px;">全水分<br/>Mt(%)</th>
					<th style="text-align: center; width: 150px;">空气干燥基全硫<br/>St,ad(%)</th>
					<th style="text-align: center; width: 150px;">空气干燥基灰分<br/>Aad(%)</th>
					<th style="text-align: center; width: 150px;">空气干燥基水分<br/>Mad(%)</th>
					<th style="text-align: center; width: 100px;">外水<br/>Mf(%)</th>
					<th style="text-align: center; width: 200px;">空气干燥基弹筒热值<br/>Qb,ad(Mj/kg)</th>
					<th style="text-align: center; width: 150px;">空气干燥基氢<br/>Had(%)</th>
					<th style="text-align: center; width: 200px;">空气干燥基挥发分<br/>Vad(%)</th>
					<th style="text-align: center; width: 100px;">固定碳<br/>FCad(%)</th>
					<th style="text-align: center; width: 100px;">干燥基全硫<br/>St,d(%)</th>
					<th style="text-align: center; width: 200px;">空气干燥基高位热值<br/>Qgr,ad(Mj/kg)</th>
					<th style="text-align: center; width: 150px;">干燥无灰基氢<br/>Hdaf(%)</th>
					<th style="text-align: center; width: 200px;">干燥无灰基高位热值<br/>Qgr,daf(Mj/kg)</th>
					<th style="text-align: center; width: 150px;">干燥无灰基全硫<br/>Sdaf(%)</th>
					<th style="text-align: center; width: 150px;">干燥基高位热值<br/>Qgr,d(Mj/kg)</th>
					<th style="text-align: center; width: 100px;">T1(℃)</th>
					<th style="text-align: center; width: 100px;">T2(℃)</th>
					<th style="text-align: center; width: 100px;">T3(℃)</th>
					<th style="text-align: center; width: 100px;">T4(℃)</th>
					<th style="text-align: center; width: 150px;">化验员</th>
					<th style="text-align: center; width: 150px;">化验备注</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">
	var oTable;
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
			'ajax' : '',
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
			"sScrollXInner": "4770px",
			"bScrollCollapse": true,
			"aoColumnDefs": [{
            	"bVisible": false,
                "aTargets": [0]
            },{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
                },
                "bSortable": false,
                "aTargets": [1]
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