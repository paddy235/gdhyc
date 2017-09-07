<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="shultzCtrl">
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">			
		 	<fieldset>
        	<div class="control-group ">
        	<!-- <label class="control-label" style="width:40px;margin-right:5px;">品种:</label> -->
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.shijtj"  ng-options="option.value as option.name for option in shijtjList"></select>
				<!-- <label class="control-label" style="width:100px;margin-right:5px;">来煤时间:</label> -->
			 	<input id="datepicker1" ng-chang="refresh()" ng-model="search.strdate" type="text" style="width:120px;float: left">
				<label class="control-label" style="width:20px;margin-right:5px;">至</label>
			 	<input id="datepicker2" ng-change="refresh()" ng-model="search.enddate" type="text" style="width:120px;float: left">
				<label class="control-label" style="width:40px;margin-right:5px;">电厂:</label>
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select>
				<label class="control-label" style="width:70px;margin-right:5px;">计划口径:</label>
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.jihkj"  ng-options="option.value as option.name for option in jihkjList"></select>
				<label class="control-label" style="width:70px;margin-right:5px;">供货单位:</label>
				<select id="selectType" style="width:120px;float: left" ng-model="search.gongysid"  ng-options="option.value as option.name for option in gongysList">
				</select>
				<!-- <label class="control-label" style="width:40px;margin-right:5px;">品种:</label>
			 	<select id="selectType" style="width:120px;float: left" ng-model="search.pinzid"  ng-options="option.value as option.name for option in pinzList"></select> -->
		    </div>

		    <div class="table-toolbar">
		   		
	              <div class="btn-group">
	                 <button style="margin-left: 40px;" ng-click="refresh()" class="btn btn-success">
	                 	<i class="icon-search icon-white"></i> 刷新
	                 </button>
	              </div>
	              
	              <div class="btn-group">
	                 <button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
	                 	<i class="icon-print icon-white"></i> 打印
	                 </button>
	              </div>	              

	              <div class="btn-group">
	                 <button style="margin-left: 10px;" my-export="数量台账.xls" class="btn btn-primary">
	                 	<i class="icon-download-alt icon-white"></i> 导出
	                 </button>
	              </div>	              
	           
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