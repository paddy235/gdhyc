<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="rulhydCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<form class="form-horizontal ng-pristine ng-valid">
				<div class="table-toolbar">
					<label style="width:70px;margin-right:5px;" class="control-label">单位名称:</label>
					<select ng-model="search.diancid"  style="float: left;width: 120px;"
						ng-options="option.value as option.name for option in diancList"></select>
					<label style="width:70px;margin-right:5px;" class="control-label">入炉日期:</label>
					<input id="datepicker" type="text" style="float: left;width: 120px;" ng-model="search.riq">					
					<label style="width:70px;margin-right:5px;" class="control-label">班组信息:</label>
					<select ng-model="search.banz"  style="float: left;width: 120px;"
						ng-options="option.value as option.name for option in rulbzList"></select>
					<label style="width:70px;margin-right:5px;" class="control-label">机组信息:</label>
					<select ng-model="search.jiz"  style="float: left;width: 120px;" 
						ng-options="option.value as option.name for option in ruljzList"></select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="入炉化验单.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</div>
				<div style="clear:both;"></div>
			</form>
		</div>
		<div class="row-fluid" id="report" style="width:100%;margin-left: auto;margin-right: auto;overflow: auto;margin-bottom: 20px;"></div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
	});
</script>