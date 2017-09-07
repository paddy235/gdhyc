<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="tab-pane" ng-controller="niandjhtjCtrl">
	<div class="block-content collapse in">
        <div class="span12">
	        <form id="ruczlAdd_form" class="form-horizontal">
	            <label style="width: 45px;margin-right:5px;float:left;" class="control-label">年份:</label> 
				<input style="width: 100px;float:left;" id="datepicker" ng-model="search.year" type="text" ng-change="refresh()" >
				<label style="width: 45px;margin-right:5px;float:left;" class="control-label span1">单位:</label>
			 	<select style="width: 120px;float:left;" id="selectType" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select> 
	            <button style="margin-left: 5px;" ng-click="refresh()" class="btn btn-success"><i class="icon-search icon-white"></i> 刷新</button>
				<button style="margin-left: 5px;" id="niandjhtj" ng-click="submit()" class="btn btn-primary"><i class="icon-file icon-white"></i> 提交</button>
				<button style="margin-left: 5px;" ng-click="printPage()" class="btn btn-primary"><i class="icon-print icon-white"></i> 打印</button>
				<button style="margin-left: 5px;" my-export="年度计划提交.xls" class="btn btn-primary"><i class="icon-download-alt icon-white"></i> 导出</button>
	        </form>
        </div>
        
		<div class="row-fluid" id="report"></div>
		<div class="pagination pagination-right" id="pagination"></div>
    </div>
    <!-- 提示框 -->
	<div my-progress="isDisplay"></div>
        
</div>

<script type="text/javascript">
$(document).ready(function(){
    $("#datepicker").datepicker({
		format : 'yyyy',
		minViewMode : 2,
		language : "zh-CN",
		autoclose : true
	});
});
</script>