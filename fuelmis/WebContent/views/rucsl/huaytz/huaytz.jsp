<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="huaytzCtrl">
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">			
		 	<fieldset>
		 	<div class="control-group ">
		    	<label class="control-label" style="width:100px;margin-right:5px;">来煤开始时间:</label>
			 	<input id="datepicker1" ng-change="refresh()" ng-model="search.strdate" type="text" style="width:120px;float:left">
				<label class="control-label" style="width:100px;margin-right:5px;">来煤结束时间:</label>
			 	<input id="datepicker2" ng-change="refresh()" ng-model="search.enddate" type="text" style="width:120px;float:left">
        		<label class="control-label" style="width:40px;margin-right:5px;">电厂:</label>
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select>
				<label class="control-label" style="width:40px;margin-right:5px;">品种:</label>
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.pinzid"  ng-options="option.value as option.name for option in pinzList"></select>
				<label class="control-label" style="width:70px;margin-right:5px;">运输方式:</label>
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.yunsfs"  ng-options="option.value as option.name for option in yunsfsList"></select>
		    </div>

		    <div class="table-toolbar">
		   		<center>
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
	                 <button style="margin-left: 10px;" my-export="化验台账.xls" class="btn btn-primary">
	                 	<i class="icon-download-alt icon-white"></i> 导出
	                 </button>
	              </div>	              
	            </center>
            </div>
            		    		    		    
		 	</fieldset>
		 	
        </form>
        </div>
		<div class="row-fluid" id="report"></div>
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