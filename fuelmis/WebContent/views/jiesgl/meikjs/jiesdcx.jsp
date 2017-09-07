<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="jiesdcxCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 80px;margin-right:5px;" class="control-label">入厂日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 75px;" ng-model="search.sDate"
						ng-change="searchData()">
					<label style="width: 10px;margin-right:5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 75px;" ng-model="search.eDate"
						ng-change="searchData()">
					<label style="width:70px;margin-right:5px;" class="control-label">供应商:</label>
					<select ng-model="search.gongys"  style="float: left;width: 120px;margin-right:30px;" ng-change="searchData()"
						ng-options="option.value as option.name for option in gongysList">
					</select>
					<label style="width: 80px;margin-right:5px;" class="control-label">结算编号:</label>
					<select ng-model="search.jiesbh"  style="float: left;width: 200px;" ng-change="refresh()"
						ng-options="option.value as option.name for option in jiesbhList"></select>
					<label style="width: 80px;margin-right:5px;" class="control-label">报表查看:</label>
					<select ng-model="search.chaxlx"  style="float: left;width: 120px;"
						ng-options="option.value as option.name for option in chaxlxList"></select>
					<button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="结算单.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid" id="report" style="margin-top:-20px"></div>
	<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
		<ul id="pagination_zc" ></ul>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			//minViewMode : 1,
			language : "zh-CN",
			autoclose : true,
		});
		
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			//minViewMode : 1,
			language : "zh-CN",
			autoclose : true,
		});
	});
</script>