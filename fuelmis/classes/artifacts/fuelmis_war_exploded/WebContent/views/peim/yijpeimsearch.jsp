   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="yijpeimeisearchCtrl" >
	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{yijpeimeisearchTitle}}</div>
	    </div>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 45px;float: left;" class="control-label">时间:</label>
					<input style="width: 100px;float: left;" id="datepicker" ng-model="search.riq" ng-change="selectriq()"   type="text">
					<label  style="width: 80px;float: left;" class="control-label span1">配煤单位:</label>
					<select style="width: 120px;float: left;" ng-model="search.peimeidanwid" ng-change="selectpeimeidanw()" ng-options="option.value as option.name for option in PeimeidwList"></select>
		            <label  style="width: 45px;float: left;" class="control-label span1">煤山:</label>
					<select style="width: 120px;float: left;" ng-model="search.meishanid" ng-change="selectmeishan()" ng-options="option.value as option.name for option in MeishanList"></select>
		            <button style="margin-left: 5px;"  id="refresh" ng-click="refresh()" class="btn btn-success"><i class=" icon-refresh"></i>查询</button>
				</div>
			</div>
		</div>
		<div class="row-fluid" >
			<table class="table table-striped table-bordered table-hover" id="example">
             <thead>
                 <tr>
                 	<th style="text-align: center; width: 100px;">配煤单位</th>
                     <th style="text-align: center; width: 100px;">煤山</th>
                     <th style="text-align: center; width: 100px;">煤源</th>
                     <th style="text-align: center; width: 50px;">调用量</th>
                     <th style="text-align: center; width: 50px;">车数</th>
                     <th style="text-align: center; width: 50px;">热值</th>
                     <th style="text-align: center; width: 50px;">硫分</th>
                     <th style="text-align: center; width: 50px;">挥发分</th>
                     <th style="text-align: center; width: 50px;">标单</th>
                     <th style="text-align: center; width: 100px;">进煤日期</th>
                 </tr>
             </thead>
         </table>
       </div>
			<!-- END FORM-->
</div>
