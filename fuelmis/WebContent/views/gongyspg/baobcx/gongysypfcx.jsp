<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
</style>
   <div class="tab-pane" ng-controller="gongysypfcxCtrl">
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 50px;margin-right:5px;" class="control-label span1">单位:</label>
				 	<select style="width: 100px;float:left;" ng-model="search.diancxxb_id"  ng-options="option.value as option.name for option in diancList"></select> 
					<label style="width: 50px;margin-right:5px;" class="control-label">供应商:</label>
		           <select style="width:120px; float: left;"  ng-model="search.gongys_id" 
					ng-options="option.value as option.name for option in gongysList">
					</select>
					<label style="width: 50px;margin-right:5px;" class="control-label">日期:</label>
					<input id="datepicker" type="text" style="float: left;width: 120px;" ng-model="search.riqBegin">
					<label style="width: 20px;margin-right:5px;" class="control-label">至</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.riqEnd">
				
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="供应商月评分报表.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid" id="report" style="width: 100%;margin-left: auto;margin-right: auto;overflow: auto;">
	</div>
	<div id="pagination_box" class="pagination pagination-right" style="width: 100%;margin-left: auto;margin-right: auto;">
		<ul id="pagination_zc" ></ul>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true,
		});
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true,
		});
	});
</script>