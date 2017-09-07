<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="gongmjhtzCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{gongmjhtzTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
				 	<fieldset>
				 	<label class="control-label" style="width:50px;margin-right:5px;margin-left:10px;">供应商:</label>
					<select id="selectType" ng-model="search.gongysb_id" style="width:150px;float: left;" ng-options="option.value as option.name for option in gongysList"></select>
				 	<label style="width: 35px;float:left;height:30px;line-height:30px;">日期:</label>
					<input style="width: 150px;float: left;" id="datepicker" ng-model="search.Date"  type="text">
					<button style="margin-left: 5px;float: left;"  id="refresh" ng-click="getGongmjhtzAll();" class="btn btn-info">刷新<i class="icon-search icon-white"></i></button>
					<button style="margin-left: 5px;float: left;"  id="add" ng-click="updateGongmjh();"  class="btn btn-info">保存<i class="icon-plus icon-white"></i></button>
					<button style="margin-left: 5px;float: left;"  id="save" ng-click="gongmjhfb();"  class="btn btn-info">发布<i class="icon-plus icon-white"></i></button>
					</fieldset>
				</form>
			</div>
		</div>
		<div class="row-fluid" style="padding-top: 5px;">
		<div width="100%" height="100px" align="center">
		<div width ="100%" align="center">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="text-align: center;">供应商简称</th>
						<th style="text-align: center;">日期</th>
						<th style="text-align: center;">计划量</th>
						<th style="text-align: center;">备注</th>
					</tr>
					
					
					<tr ng-repeat="data in list track by $index">
					        <td width="10%"><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MINGC"></td>
					         <td width="10%"><input  class="datepicker0 JIHGMRQ" style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.JIHGMRQ"></td>
					          <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.ZHIBZ"></td>
					           <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.BEIZ"></td>
				</thead>
			</table>
		</div>
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