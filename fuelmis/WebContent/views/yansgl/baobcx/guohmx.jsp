<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
 #example th,td{white-space: nowrap;padding:5px 15px;}
</style>
   <div class="tab-pane" ng-controller="guohmxCtrl">
 
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
			<form class="form-horizontal ng-pristine ng-valid">
				 <label style="width:50px;margin-right:5px;margin-left:5px;float:left;line-height:30px;height:30px;">供应商:</label>
			 	<select id="selectType" ng-model="search.gongys_id" style="float:left;width:150px;line-height:30px;height:30px;" ng-options="option.value as option.name for option in gongysList"></select>
			 	<label  style="width:40px;margin-right:5px;margin-left:5px;float:left;line-height:30px;height:30px;">煤矿:</label>
			 	<select id="selectType" ng-model="search.meikxxb_id" style="float:left;width:150px;line-height:30px;height:30px;" ng-options="option.value as option.name for option in meikxxList"></select>
			 	<label style="width:70px;margin-left:10px;margin-right:5px;float:left;line-height:30px;height:30px;">来煤日期:</label>
			 	<input class="datepicker" ng-model="search.sDate" type="text" style="width:150px;float:left;line-height:30px;">
			 	<label  style="width:15px;margin-left:5px;margin-right:5px;float:left;line-height:30px;height:30px;">至</label>
			 	<input class="datepicker" ng-model="search.eDate" type="text" style="width:150px;float:left;line-height:30px;">
				<button style="margin-left: 5px;float: left;"   ng-click="refresh();" class="btn btn-info"><i class="icon-search icon-white"></i> 查询</button>
				<button style="margin-left: 5px;float: left;"   my-export="过衡明细.xls" class="btn btn-info"><i class="icon-search icon-white"></i> 导出</button>
				</form>
			</div>
		</div>
	
<div class="row-fluid" id="report"></div>
		<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
			<ul id="pagination_zc" ></ul>
		</div>
    </div>
        
</div>

