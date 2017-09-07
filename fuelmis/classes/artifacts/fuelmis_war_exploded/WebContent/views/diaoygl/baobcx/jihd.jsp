<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="ranlrcgjhdCtrl">
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">			
		 	<fieldset>
		 	<div class="control-group ">
		    	<label class="control-label" style="width:100px;margin-right:5px;">日期:</label>
			 	<input id="datepicker1" ng-change="refresh()" ng-model="riq" type="text" style="width:120px;float:left">
        		<label class="control-label" style="width:40px;margin-right:5px;">电厂:</label>
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select>
		    </div>

		    <div class="table-toolbar">
	              <div class="btn-group">
	                 <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success">
	                 	<i class="icon-search icon-white"></i> 刷新
	                 </button>
	              </div>
	              
	              <div class="btn-group">
	                 <button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
	                 	<i class="icon-print icon-white"></i> 打印
	                 </button>
	              </div>	              

	              <div class="btn-group">
	                 <button style="margin-left: 10px;" my-export="燃料采购计划单.xls" class="btn btn-primary">
	                 	<i class="icon-download-alt icon-white"></i> 导出
	                 </button>
	              </div>	              
            </div>
            		    		    		    
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
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});
</script>