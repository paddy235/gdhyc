<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="huayrbCtrl">
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">			
		 	<fieldset>
        		<label class="control-label" style="width:80px;margin-right:5px;float:left">电厂:</label>
			 	<select id="selectType" ng-model="search.diancid" style="float:left;width:150px;" ng-options="option.value as option.name for option in diancList"></select>
		    	<label class="control-label" style="width:70px;margin-left:10px;margin-right:5px;float:left">来煤日期:</label>
			 	<input id="datepicker1" ng-change="refresh()" ng-model="search.date" type="text" style="width:150px;float:left">
                 <button style="margin-left:20px;" ng-click="refresh()" class="btn btn-success">
                 	<i class="icon-search icon-white"></i> 刷新
                 </button>
                 <button style="margin-left:10px;" ng-click="printPage()" class="btn btn-primary">
                 	<i class="icon-print icon-white"></i> 打印
                 </button>
                 <button style="margin-left:10px;" my-export="化验日报.xls" class="btn btn-primary">
                 	<i class="icon-download-alt icon-white"></i> 导出
                 </button>
		 	</fieldset>
		 	
        </form>
        </div>
		<div class="row-fluid" id="report" style="width: 90%;margin-left: auto;margin-right: auto;overflow: auto;" ></div>
		<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
			<ul id="pagination_zc" ></ul>
		</div>
    </div>
        
</div>

<script type="text/javascript">
	$(document).ready(function(){	    
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>