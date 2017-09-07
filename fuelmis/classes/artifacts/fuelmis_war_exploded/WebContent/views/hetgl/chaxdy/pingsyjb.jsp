<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="pingsyjbCtrl">

	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:40px;margin-right:5px;" class="control-label">日期:</label>
					<input id="datepicker1"  style="float: left;width: 120px;" ng-model="search.riq" ng-change="loadHetbh()" type="text">
					<label style="width: 80px;margin-right:5px;" class="control-label">合同编号:</label>
					<select id="selHetbh" ng-model="search.hetbh"  style="float: left;width: 200px;"
						ng-options="option.value as option.name for option in hetbhList"></select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="评审意见表.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid" id="report" style="width:100%;margin-left: auto;margin-right: auto;overflow: auto;"></div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>