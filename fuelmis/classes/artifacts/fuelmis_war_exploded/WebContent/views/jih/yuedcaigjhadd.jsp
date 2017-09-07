<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
#example th,td {
	white-space: nowrap;
}

tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>
<div class="tab-pane" ng-controller="yuedcaigjhwhCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{yuedcaigTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label
					style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label">日期:</label> <input
					style="width: 68px; float: left;" id="datepicker"
					ng-model="search.riq" ng-change="selectriq()" type="text">
				<label
					style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">单位:</label> <select
					style="width: 98px; float: left;" ng-model="search.diancid"
					ng-change="selectdianc()"
					ng-options="option.value as option.name for option in diancList"></select>
				<button style="margin-left: 5px;" id="refresh" ng-click="refresh()"
					class="btn btn-success">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
				<button id="adddata" name={{search.state}} class="btn btn-primary"
					ng-click="addyuedcgjh()">
					<i class="icon-plus icon-white"></i> 新增
				</button>
				<button disabled id="update" ng-click="updateyuedcgjh()"
					class="btn btn-info">
					<i class="icon-edit icon-white"></i> 修改
				</button>
				<button disabled id="delete" ng-click="delyuedcaig()"
					class="btn btn-danger">
					<i class=" icon-trash icon-white"></i> 删除
				</button>
				<button class="btn btn-primary" id="copyyuedcgjh"
					ng-click="copyyuedcgjh()">
					<i class="icon-file icon-white"></i> 复制上月计划
				</button>
			</div>
		</div>

		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 20px;"></th>
						<th style="text-align: center; width: 30px;">序号</th>
						<th style="text-align: center;">供应商</th>
						<th style="text-align: center;">煤矿单位</th>
						<th style="text-align: center;">计划口径</th>
						<th style="text-align: center;">品种</th>
						<th style="text-align: center;">发站</th>
						<th style="text-align: center;">采购量（吨）</th>
						<th style="text-align: center;">热值（MJ/Kg）</th>
						<th style="text-align: center;">硫分（%）</th>
						<th style="text-align: center;">挥发分（%）</th>
						<th style="text-align: center;">车板价(元/吨)</th>
						<th style="text-align: center;">车板价(不含税)(元/吨)</th>
						<th style="text-align: center;">运费(元/吨)</th>
						<th style="text-align: center;">运费(不含税)(元/吨)</th>
						<th style="text-align: center;">厂前杂费(元/吨）</th>
						<th style="text-align: center;">厂前杂费(不含税)（元/吨）</th>
						<th style="text-align: center;">到厂价(元/吨)</th>
						<th style="text-align: center;">到厂价(不含税)(元/吨)</th>
						<th style="text-align: center;">到厂标煤单价(元/吨)</th>
						<th style="text-align: center;">到厂标煤单价(不含税)(元/吨)</th>
					</tr>
				</thead>
			</table>
		</div>

	</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
	function check(args) {
		if ($("#adddata").attr("name") == '0') {
			if ($(args).attr("checked") != undefined) {
				$("#delete").addClass("btn-danger");
				$("#delete").attr("disabled", false); //移除disabled属性 
				$("#update").addClass("btn-info");
				$("#update").attr("disabled", false); //移除disabled属性 
				$("input[type='checkbox']").attr("checked", false);
				$(args).attr("checked", true);
				//obj.attr("id")
			} else {
				$("#update").removeClass("btn-info");
				$("#delete").removeClass("btn-danger");
				$("#delete").attr('disabled', true);
				$("#update").attr('disabled', true);
			}
		}
	}
</script>