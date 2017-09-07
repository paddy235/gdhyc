<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="yuedjhtjCtrl">
	<div class="block-content collapse in">
        <div class="span12 ">
	        <form id="ruczlAdd_form" class="form-horizontal">
				<label style="width: 45px;margin-right:5px;float:left;" class="control-label">日期:</label> 
				<input style="width: 100px;float:left;" id="datepicker" ng-model="search.riq" ng-change="refresh()" type="text" style="float: left">
				<label style="width: 45px;margin-right:5px;float:left;" class="control-label span1">单位:</label>
			 	<select style="width: 120px;float:left;" id="selectType" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select> 
	            <button style="margin-left: 5px;" ng-click="refresh()" class="btn btn-success">
	            	<i class="icon-search icon-white"></i> 刷新
	            </button>
	            <button style="margin-left: 5px;" id="yuedjhtj" ng-click="submit()" class="btn btn-primary">
	            	<i class="icon-file icon-white"></i> 提交
	            </button>
				<button style="margin-left: 5px;" ng-click="printPage()" class="btn btn-primary">
					<i class="icon-print icon-white"></i> 打印
				</button>
				<button style="margin-left: 5px;" my-export="月度计划提交.xls" class="btn btn-primary">
					<i class="icon-download-alt icon-white"></i> 导出
				</button>
	        </form>
        </div>
        
        <div class="row-fluid" id="report" style="width:100%;margin-left:auto;margin-right:auto;overflow:auto;"></div>
		<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
			<ul id="pagination_zc" ></ul>
		</div>
    </div>
    <!-- 提示框 -->
	<div my-progress="isDisplay"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#datepicker").datepicker({
		format : 'yyyy-mm',
		minViewMode : 1,
		language : "zh-CN",
		autoclose : true
	});
});
</script>