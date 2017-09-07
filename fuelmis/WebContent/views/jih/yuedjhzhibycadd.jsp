<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
/* .inputTd *{
padding: 0px 0px !important; 
	margin: 0px 0px !important;
} */
.inputTd {
	height: 100% !important;
	width: 100% !important;
/* 	margin: 0px 0px !important;*/
	padding: 0px 0px !important; 
 	padding-right: 4px !important; 
	line-height: 32px!important;  
		text-indent: 10px !important;  
}
tbody input {
 position:relative !important;;
	height: 32px !important;
	left:-10px !important;
	margin: 0px 0px !important;
	padding: 0px 0px !important; 
	text-indent: 10px !important;  
}

form {
	hight: 100% !important;
	width: 100% !important;
	margin: 0px 0px !important;
	padding: 0px 0px !important;
}
</style>
<div class="tab-pane" ng-controller="yuedjihzhibycCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{yuedjihzhibTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label
					style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label">日期:</label> <input
					style="width: 68px; float: left;" id="datepicker"
					ng-model="search.riq" ng-change="selectriq()" type="text">
				<label
					style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">单位:</label> <select
					style="width: 100px; float: left;" id="selectdanw"
					ng-model="search.diancid" ng-change="selectdianc()"
					ng-options="option.value as option.name for option in diancList"></select>
				<button style="margin-left: 5px;" id="refresh" ng-click="refresh()"
					class="btn btn-success">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
				<!--  <button  class="btn btn-primary" id="adddata"  ng-click="addyuedranlzfjh()"><i class="icon-plus icon-white"></i> 新增</button> -->
				<!-- <button  disabled id="updateranlzf" ng-click="updateyuedcgjh()" class="btn"><i class="icon-edit icon-white"></i> 修改</button> -->
				<button id="delranlzf" ng-click="delranlzf()" class="btn btn-danger">
					<i class="icon-trash icon-white"></i> 删除
				</button>
				<button class="btn btn-primary" id="copyranlzf"
					ng-click="copyranlzfjh()">
					<i class="icon-file icon-white"></i> 复制上月计划
				</button>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="display: none;"></th>
						<th style="text-align: center; width: 10%;">序号</th>
						<th style="text-align: center; display: none;">字段名</th>
						<th style="text-align: center; width: 30%;">项目1</th>
						<th style="text-align: center; width: 30%;">单位1</th>
						<th style="text-align: center; width: 30%;">值1</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- END FORM-->
</div>

