<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tab-pane" ng-controller="shulysbbCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
						<label style="width: 50px;margin-right:5px;" class="control-label">日期:</label>
						<input id="datepicker" type="text" style="float: left;width: 120px;" ng-model="search.riq">
					<label style="width: 80px;margin-right:5px;" class="control-label">单位:</label>
					<select ng-model="search.diancid"  style="float: left;width: 120px;" 
						ng-options="option.value as option.name for option in diancList"></select>
					<label style="width: 80px;margin-right:5px;" class="control-label">报表类型:</label>
					<select ng-model="search.leix"  style="float: left;width: 120px;">
						<option value=0 >地区汇总</option>
						<option value=1 >分矿明细</option>							
					</select>
					<button style="margin-left: 20px;" ng-click="searchData()"
						class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="数量验收报表.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid" id="report" style="width: 96%;margin-left: auto;margin-right: auto;overflow: auto;"></div>
	<div id="pagination_box" class="pagination pagination-right" style="width: 96%;margin-left: auto;margin-right: auto;">
		<ul id="pagination_zc" ></ul>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>