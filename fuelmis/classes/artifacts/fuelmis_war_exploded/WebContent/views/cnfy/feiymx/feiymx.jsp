<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="cnfymxCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{cnfymxTitle}}</div>
	</div>--%>
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">
            <label style="width:45px;margin-right:5px;float:left;" class="control-label">日期:</label> 
			<input style="width: 100px;float:left;" id="datepicker" ng-change="refresh()" ng-model="search.riq" type="text" >
            <button style="margin-left: 15px;" ng-click="refresh()" class="btn btn-success">
            	<i class="icon-search icon-white"></i> 刷新
            </button>
			<button style="margin-left: 5px;" ng-click="printPage()" class="btn btn-primary">
				<i class="icon-print icon-white"></i> 打印
			</button>
			<button style="margin-left: 5px;" my-export="费用明细.xls" class="btn btn-primary">
				<i class="icon-download-alt icon-white"></i> 导出
			</button>
        </form>
        </div>
		<div class="row-fluid" id="report"></div>
		<div class="pagination pagination-right" id="pagination"></div>
    </div>
        
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