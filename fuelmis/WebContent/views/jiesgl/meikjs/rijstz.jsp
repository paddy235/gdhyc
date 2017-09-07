<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="rijstzCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<form class="form-horizontal ng-pristine ng-valid">
				<div class="table-toolbar">
					<label style="width:40px;margin-right:5px;" class="control-label">煤矿:</label>
					<select ng-model="search.diancid"  style="float: left;width: 120px;"
						ng-options="option.value as option.name for option in meikxxList"></select>
					<label style="width:70px;margin-right:5px;" class="control-label">入厂日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width:15px;margin:0 5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
					<label style="width:70px;margin-right:5px;" class="control-label">品种:</label>
					<select ng-model="search.gongys"  style="float: left;width: 120px;margin-right:30px;"
						ng-options="option.value as option.name for option in pinzList">
					</select>
				   <!--  <input type="checkbox" id="ruzrq"><label style="display:inline-block;height:30px;line-height:30px;width:70px;margin-left:5px;">入账日期</label> -->
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="日结算台账.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</div>
			    <div style="margin-top:20px;text-align:center;">
				</div>
			</form>
		</div>
		<div class="row-fluid" id="report" style="width: 100%;margin-left: auto;margin-right: auto;overflow: auto;"></div>
		<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
			<ul id="pagination_zc" ></ul>
		</div>
	</div>
</div>

<script type="text/javascript">
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
		
		/* $('#ruzrq').iCheck({
		    checkboxClass: 'icheckbox_minimal',
		    radioClass: 'iradio_minimal',
		    increaseArea: '20%'
		}); */
	});
</script>