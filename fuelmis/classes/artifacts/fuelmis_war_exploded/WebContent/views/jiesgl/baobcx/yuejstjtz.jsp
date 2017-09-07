<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="YuejstjtzCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<form class="form-horizontal ng-pristine ng-valid">
				<div class="table-toolbar">
					<label style="width:70px;margin-right:5px;" class="control-label">供应商:</label>
					<select ng-model="search.gongys"  style="float: left;width: 120px;"
						ng-options="option.value as option.name for option in gongysList"></select>
					<label style="width:70px;margin-right:5px;" class="control-label">结算日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 75px;" ng-model="search.sDate" ng-change="getHetbh()">
					<label style="width:15px;margin:0 5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 75px;" ng-model="search.eDate" ng-change="getHetbh()">
					<label style="width:70px;margin-right:5px;" class="control-label">品种:</label>
					<select ng-model="search.pinz"  style="float: left;width: 75px;margin-right:30px;"
						ng-options="option.value as option.name for option in pinzList">
					</select>
					<label style="width:70px;margin-right:5px;" class="control-label">合同编号:</label>
					<select ng-model="search.hetbh"  style="float: left;width: 198px;margin-right:30px;"
							ng-options="option.VALUE as option.NAME for option in yuejshetbhList">
					</select>
				   <!--  <input type="checkbox" id="ruzrq"><label style="display:inline-block;height:30px;line-height:30px;width:70px;margin-left:5px;">入账日期</label> -->
					<button style="margin-left: 20px; " ng-click="getYuejstjtz();" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button my-print style="margin-left: 10px;" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button my-export="月结算统计台帐.xls" style="margin-left: 10px;"  class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</div>
			    <div style="margin-top:20px;text-align:center;">
				</div>
			</form>
		</div>
	</div>
	<div class="row-fluid" id="report" style="width: 100%;margin-left: auto;margin-right: auto;overflow: auto;"></div>
	<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
		<ul id="pagination_zc" ></ul>
	</div>
		</div>
</div>
<style type="text/css">
#example th,td {
white-space: nowrap;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			/*minViewMode: 1,*/
			language : "zh-CN",
			/* orientation: "bottom", */
			autoclose : true
		});
		
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			/*minViewMode: 1,*/
			language : "zh-CN",
			/* orientation:"bottom", */
			autoclose : true

		});
	});
</script>