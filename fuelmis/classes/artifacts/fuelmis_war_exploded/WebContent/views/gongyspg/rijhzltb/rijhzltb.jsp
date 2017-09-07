<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="rijhzltbCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{gongmjhtzTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
				 	<fieldset>
				 	<label class="control-label" style="width:50px;margin-right:5px;margin-left:10px;">状态:</label>
					<select id="selectType" ng-model="search.zhuangt" style="width:150px;float: left;">
						<option value=0>未审核</option>
						<option value=1>已审核</option>
					</select>
					<label class="control-label" style="width:50px;margin-right:5px;margin-left:10px;">单位:</label>
					<select id="selectType" ng-model="search.mingc" style="width:150px;float: left;" ng-options="option.value as option.name for option in gongysList"></select>
				 	<label style="width: 80px;float:left;height:30px;line-height:30px;">计划日期:</label>
					<input style="width: 150px;float: left;" id="datepicker" ng-model="search.Date"  type="text">
					<button style="margin-left: 5px;float: left;"  id="refresh" ng-click="getRijhzltbAll();" class="btn btn-info">刷新<i class="icon-search icon-white"></i></button>
					<button style="margin-left: 5px;float: left;"  id="add" ng-click="insertRijhzltb();"  class="btn btn-info">保存<i class="icon-plus icon-white"></i></button>
					<button style="margin-left: 5px;float: left;"  id="save" ng-click="updateRijhzltb();"  class="btn btn-info">审核<i class="icon-plus icon-white"></i></button>
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
						<th style="text-align: center;">名称</th>
						<th style="text-align: center;">日期</th>
						<th style="text-align: center;">计划煤量</th>
						<th style="text-align: center;">低位热QENT_AR(Kcal/kg)</th>
						<th style="text-align: center;">收到基硫STAR(%)</th>
						<th style="text-align: center;">审核日期</th>
						<th style="text-align: center;">审核人员</th>
					</tr>
					
					
					<tr ng-repeat="data in list track by $index">
					        <td width="10%"><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MINGC"></td>
					         <td width="10%"><input disabled  class="datepicker0 riq" style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.RIQ"></td>
					          <td width="10%"><input disabled  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.JIHML"></td>
					           <td width="10%"><input style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.ZHIBZ1"></td>
					           	<td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.ZHIBZ2"></td>
					           	 <td width="10%"><input  disabled  class="datepicker1 SHENHRQ" style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.SHENHRQ"></td>
					           	  <td width="10%"><input  disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.SHENHRY"></td>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});

 
</script>