<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
 #example th,td{white-space: nowrap !important;padding:5px 15px!important;}
</style>
<div class="tab-pane" ng-controller="shangccwCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
			<form class="form-horizontal ng-pristine ng-valid">
		 	<fieldset>
			 	<div class="row-fluid" style="margin-bottom:20px;">
	        		<label class="control-label" style="width: 80px;margin-right:5px;">日期:</label>
				 	<input id="datepicker1" ng-model="search.riq" type="text" style="width:120px;float: left" ng-change="refresh()"> 
	        		<label class="control-label" style="width: 50px;margin-right:5px;">类型:</label>
				 	<select id="selectType" ng-model="search.leix" style="width:120px;"  ng-options="option.value as option.name for option in leixList" ng-change="change()"></select>
					<button style="margin-left: 10px;" ng-click="refresh()" class="btn btn-primary">
						<i class="icon-search icon-white"></i> 刷新
					</button>
	                 <button style="margin-left: 10px;" ng-click="shangbcw()" class="btn btn-primary">
	                 	<i class="icon-file icon-white"></i> 上报财务
	                 </button>
			    </div>
				</fieldset>
				</form>
			</div>
		</div>
		<div class="row-fluid" ng-if="search.leix==0">
			<table class="table table-striped table-bordered table-hover"
				   id="example"
				   my-table="overrideOptions"
				   aa-data="list"
				   ao-column-defs="columnDefs"
				   my-selection=true
			>
			<thead>
				<tr >
					<th><input type="checkbox"></th><th>结算单号</th><th>供货人</th><th>收款单位</th><th>合同编号</th><th>结算数量</th><th>单价</th><th>含税金额</th><th>不含税金额</th><th>结算类型</th><th>税款</th></th><th>状态</th>
				</tr>
			</thead>
			</table>
		</div>
		<div class="row-fluid" ng-if="search.leix==1">
			<table class="table table-striped table-bordered table-hover"
				   id="example"
				   my-table="overrideOptions"
				   aa-data="zanglist"
				   ao-column-defs="columnDefs"
				   my-selection=true
			>
			<thead>
				<tr><th><input type="checkbox"></th><th>煤种</th><th>暂估数量</th><th>暂估金额</th></tr>
			</thead>
			</table>
		</div>
		<div class="row-fluid" ng-if="search.leix==2">
			<table class="table table-striped table-bordered table-hover"
				   id="example"
				   my-table="overrideOptions"
				   aa-data="haoylist"
				   ao-column-defs="columnDefs"
				   my-selection=true
			>
			<thead>
				<tr>
					<th><input type="checkbox"></th><th>煤种</th><th>上月-结余数量</th><th>上月-结余金额</th><th>本月-发电耗用数量</th><th>本月-发电耗用金额</th><th>本月-供热耗用数量</th><th>本月-供热耗用金额</th>
				</tr>
			</thead>
			</table>
		</div>
	</div>	
</div>
