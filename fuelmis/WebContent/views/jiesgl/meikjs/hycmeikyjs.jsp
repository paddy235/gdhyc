<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<style>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/bootstrap-3.3.5/css/bootstrap.min.css">
	</style>
    <div class="tab-pane" ng-controller="hycmeikjsCtrl" >
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 40px;height: 30px;line-height: 30px;float: left;margin-left:5px;" class="control-label" >单位:</label>
		           	<select style="width: 108px; float: left;" id="selectdanw" ng-model="search.diancid" ng-change="refresh()"
                    	ng-options="option.value as option.name for option in diancList">
                    </select>
					<label style="width: 65px;height: 30px;line-height: 30px;float: left;" class="control-label">日期:</label>
					<input style="width: 80px;float: left;" id="datepicker" ng-model="search.sDate" ng-change="getGongysList()" type="text">
					<label style="width:15px;height: 30px;line-height: 30px;margin-left:5px;margin-right:5px;float: left;" class="control-label">至</label>
					<input style="width: 80px;float: left;" id="datepicker1" ng-model="search.eDate" ng-change="getGongysList()" type="text">
		           <label style="width: 55px;height: 30px;line-height: 30px;float: left;margin-left:5px;" class="control-label">供应商:</label>
		           <select style="width:120px; float: left;"  ng-model="search.GONGYSB_ID" ng-change="getPinzList()"
					ng-options="option.ID as option.MINGC for option in gongysList_meikjs">
					</select>
					<label style="width: 45px;height: 30px;line-height: 30px;float: left;margin-left:5px;" class="control-label">煤矿:</label>
		           <select style="width: 120px;float:left;" id="meikdw_id" ng-model="search.MEIKID"  ng-options="option.ID as option.MINGC for option in meikxxList_meikjs"></select>
					<label style="width: 45px;height: 30px;line-height: 30px;float: left;margin-left:5px;" class="control-label" >品种:</label>
		           <select style="width:120px; float: left;"  ng-model="search.PINZB_ID" ng-change="refresh()"
					ng-options="option.value as option.name for option in pinzList">
					</select>
					<label style="width: 53px;height: 30px;line-height: 30px;float: left;margin-left:5px;" class="control-label" >合同号:</label>
		           <select style="width:193px; float: left;"  ng-model="search.HETB_ID"
					ng-options="option.value as option.name for option in hetbhlist">
					</select>
					<button style="margin-left:5px;"  id="create" ng-click="refresh()" class="btn btn-success">
		            	<i class=" icon-refresh icon-white"></i>查询
		            </button>
		            <button style="margin-left:5px;"  id="create" ng-click="jies()" class="btn btn-success">
		            	<i class=" icon-refresh icon-white"></i>结算
		            </button>
				</div>
			</div>
			<div class="row-fluid" >
			<table class="table table-striped table-bordered table-hover" >
	             <thead>
	             	<tr>
	                 	 <th style="text-align: center; width: 40px;">已经选中合计</th>
	                    <%-- <th style="text-align: center; width: 120px;">标重</th>--%>
	                   <%--  <th style="text-align: center; width: 80px;">{{}}</th>--%>
	                     <th style="text-align: center; width: 60px;">净重</th>
	                     <th style="text-align: center; width: 50px;">{{hetj.JINGZ}}</th>
	                     <th style="text-align: center; width: 80px;">发热量</th>
	                     <th style="text-align: center; width: 50px;">{{hetj.QNETAR}}</th>
	                     <th style="text-align: center; width: 50px;">硫分</th>
	                     <th style="text-align: center; width: 50px;">{{hetj.STD}}</th>
	                     <th style="text-align: center; width: 50px;">水分</th>
	                     <th style="text-align: center; width: 50px;">{{hetj.MT}}</th>
	                     <th style="text-align: center; width: 50px;">干基灰(Ad)</th>
	                     <th style="text-align: center; width: 50px;">{{hetj.AD}}</th>
	                     <th style="text-align: center; width: 50px;">挥发分(Vdaf)</th>
	                     <th style="text-align: center; width: 50px;">{{hetj.VDAF}}</th>
	                 </tr>
	                 <tr>
	                 	<th style="text-align:center;width:30px;">
	                 	<input type="checkbox" ng-model="all" ng-click="selectall($event)">
	                 	</th>
	                 	 <th colspan ="2" style="text-align: center; width: 80px;">供应商</th>
	                     <th style="text-align: center; width: 120px;">煤矿</th>
	                     <th style="text-align: center; width: 80px;">品种</th>
	                     <th style="text-align: center; width: 60px;">到货日期</th>
	                     <th style="text-align: center; width: 80px;">车数</th>
	                     <th style="text-align: center; width: 50px;">三方数量</th>
	                     <th style="text-align: center; width: 50px;">净重</th>
	                     <th style="text-align: center; width: 50px;">化验状态</th>
	                     <th style="text-align: center; width: 50px;">低位发热量（Qnet,ar）</th>
	                     <th style="text-align: center; width: 50px;">硫分(St,d)</th>
	                     <th style="text-align: center; width: 50px;">水分(Mt)</th>
	                     <th style="text-align: center; width: 50px;">灰分(Ad)</th>
	                     <th style="text-align: center; width: 50px;">挥发分(Vdaf)</th>
	                 </tr>
	             </thead>
	             <tbody>
	             	<tr ng-repeat="data in yuejsList track by $index" on-finish-render-filters>
	             		<td style="text-align:center;width:30px;">
	             			<input  type="checkbox" ng-checked="all" ng-click="selectone(data,$event)">
	             		</td>
	             		<td colspan ="2">{{data.GONGYSMC}}</td>
	             		<td>{{data.MEIKMC}}</td>
	             		<td>{{data.PINZ}}</td>
	             		<td>{{data.DAOHRQ}}</td>
	             		<td>{{data.CHES}}</td>
	             		<td>{{data.SANFSL}}</td>
	             		<td>{{data.JINGZ}}</td>
	             		<td>{{data.HUAYZT}}</td>
	             		<td>{{data.QNET_AR}}</td>
	             		<td>{{data.STD}}</td>
	             		<td>{{data.MT}}</td>
	             		<td>{{data.AD}}</td>
	             		<td>{{data.VDAF}}</td>
	             	</tr>
	             </tbody>
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