<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>

td div{
	width: 100% !important;
}
#oData1{
width: 855px;

}
#report{
	margin-top:100px;
}
</style>

<div class="tab-pane" ng-controller="ruchybgCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<form class="form-horizontal ng-pristine ng-valid">
				<div class="table-toolbar">
					<label style="width: 80px;margin-right:5px;" class="control-label">化验日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate"
						ng-change="selJiesbh()">
					<label style="width: 10px;margin-right:5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate"
						ng-change="selJiesbh()">
					<label style="width: 80px;margin-right:5px;" class="control-label">化验编号:</label>
					<select ng-model="search.huaybh"  style="float: left;width: 200px;"
						ng-options="option.value as option.name for option in huaybhList">
						</select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="入厂化验报告.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</div>
				<div style="clear:both;"></div>
			</form>
		</div>
		<div class="row-fluid" id="report"></div>
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