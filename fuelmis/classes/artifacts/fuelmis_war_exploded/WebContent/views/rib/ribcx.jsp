<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="ribcxCtrl">
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">
             <div class = "form-group">
	             <div class="span12">
		             <label style="width: 60px;margin-right:5px;" class="control-label">开始日期:</label> 
					 <input id="datepicker1" ng-model="search.kaisriq" type="text" style="width: 80px;float: left;">
					 <label style="width: 60px;margin:0 5px 0 10px;float: left;" class="control-label">截止日期:</label> 
					 <input id="datepicker2" ng-model="search.jiezriq" type="text" style="width: 80px;float: left;">
					 <label style="margin:0 5px 0 10px;width:40px;float: left;" class="control-label span1">单位:</label>
				 	 <select id="selectType" style="width:130px; float: left;" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select> 
				 	 <label style="margin:0 5px 0 10px;width:60px;float: left;" class="control-label span1">报表类型:</label>
				 	 <select id="selectType" style="width:130px; float: left;" ng-model="search.baobleix"  ng-options="option.value as option.name for option in baobleixlist"></select> 
		             <button style="margin-left: 20px;float: left;" ng-click="refresh()" class="btn btn-success">
		             	<i class="icon-search icon-white"></i> 刷新
		             </button>
					 <button style="margin-left: 5px;float: left;" ng-click="printPage()" class="btn btn-primary">
					 	<i class="icon-print icon-white"></i> 打印
					 </button>
					 <button style="margin-left: 5px;float: left;" my-export="日报查询.xls" class="btn btn-primary">
					 	<i class="icon-download-alt icon-white"></i> 导出
					 </button>
	             </div>
             </div>
            </form>
        </div>
        </div>
        
	<div class="row-fluid" id="report"></div>
	<div class="pagination pagination-right" id="pagination"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});
</script>