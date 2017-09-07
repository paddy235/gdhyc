   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="yijpeimeiCtrl" >
	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{yijpeimeiTitle}}</div>
	    </div>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 45px;float: left;" class="control-label">时间:</label>
					<input style="width: 105px;float: left;" id="datepicker" ng-model="search.riq" ng-change="selectriq()"   type="text">
					<button style="margin-left: 5px;"  id="refresh" ng-click="refresh()" class="btn btn-success"><i class=" icon-refresh"></i>查询</button>
				</div>
			</div>
		</div>
		<div class="row-fluid" >
			<table class="table table-striped table-bordered table-hover" id="example">
             <thead>
                 <tr>
                 	<th style="text-align: center; width: 120px;">煤源</th>
                 	<th style="text-align: center; width: 100px;">车数</th>
                    <th style="text-align: center; width: 100px;">到厂量预估</th>
                    <th style="text-align: center; width: 100px;">实际到厂量</th>
                    <th style="text-align: center; width: 100px;">进煤日期</th>
                 </tr>
             </thead>
         </table>
       </div>
			<!-- END FORM-->
</div>