   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="tab-pane" ng-controller="meikyjsCtrl" >
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 65px;height: 30px;line-height: 30px;float: left;" class="control-label">入厂日期:</label>
					<input style="width: 120px;float: left;" id="datepicker" ng-model="search.sDate" ng-change="getGongysList()" type="text">
					<label style="width:15px;height: 30px;line-height: 30px;margin-left:5px;margin-right:5px;float: left;" class="control-label">至</label>
					<input style="width: 120px;float: left;" id="datepicker1" ng-model="search.eDate" ng-change="getGongysList()" type="text">
		           <label style="width: 55px;height: 30px;line-height: 30px;float: left;margin-left:5px;" class="control-label">供应商:</label>
		           <select style="width:120px; float: left;"  ng-model="search.GONGYSB_ID" ng-change="getPinzList()"
					ng-options="option.value as option.name for option in gongysList">
					</select>
					<label style="width: 50px;height: 30px;line-height: 30px;float: left;margin-left:5px;" class="control-label" >品种:</label>
		           <select style="width:120px; float: left;"  ng-model="search.PINZB_ID" ng-change="refresh()"
					ng-options="option.value as option.name for option in pinzList">
					</select>
		            <button style="margin-left:20px;"  id="create" ng-click="create()" class="btn btn-success">
		            	<i class=" icon-refresh icon-white"></i>生成
		            </button>
				</div>
			</div>
			<div class="row-fluid" >
			<table class="table table-striped table-bordered table-hover" 
			my-table="overrideOptions"
            aa-data="yuejsList"
        ao-column-defs="columnDefs"
        fn-row-callback="myCallback"
				id="example">
	             <thead>
	                 <tr>
	                 	 <th style="text-align: center; width: 80px;">结算日期</th>
	                     <th style="text-align: center; width: 120px;">结算单号</th>
	                     <th style="text-align: center; width: 80px;">供应商</th>
	                     <th style="text-align: center; width: 60px;">品种</th>
	                     <th style="text-align: center; width: 50px;">车数</th>
	                     <th style="text-align: center; width: 80px;">结算数量</th>
	                     <th style="text-align: center; width: 50px;">结算热值</th>
	                     <th style="text-align: center; width: 50px;">结算硫分</th>
	                     <th style="text-align: center; width: 50px;">结算价格</th>
	                     <th style="text-align: center; width: 50px;">结算金额</th>
	                 </tr>
	             </thead>
	         </table>
	     </div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
		
	});
</script>