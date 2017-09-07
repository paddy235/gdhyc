<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<style>
.ui-datepicker-calendar {
	display: none;
}

th {
	text-align: center !important;
}

td {
	text-align: center !important;
	margin: 0px !important;
	padding: 0px !important;
}

td input,td select {
	width: 100% !important;
	height: 37px !important;
	margin: 0px !important;
	padding: 0px !important;
	border: 0px !important;
	text-align: center !important;
	font-size: 16px !important;
	background-color: transparent !important;
	line-height: 35px !important;
}
td select{
padding:8px 0 !important;
}
.img{
margin-top:5px;
width : 30px !important;
}
</style>
<div class="tab-pane" ng-controller="yuegmjhCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{yuegmjhTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
				 		<label class="control-label" style="width:70px;margin-right:5px;margin-left:10px;float: left">提交状态:</label>
					 	<select id="selectType" ng-model="search.zhuangt" style="width:150px;float: left" >
						 	<option value=0  >未提交</option>
							<option value=1  >已提交</option>	
						</select>
		        		<label class="control-label" style="width:35px;margin-right:5px;float: left">时间:</label>
					 	<input id="datepicker1" ng-model="search.date" type="text" style="width:150px;float:left">
		        		<label class="control-label" style="width:50px;margin-right:5px;margin-left:10px;float: left">供应商:</label>
					 	<select id="selectType" ng-model="search.gongysb_id" style="width:150px;float: left" ng-options="option.value as option.name for option in gongysList">
						</select>
		                <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success"><i class="icon-search icon-white"></i> 刷新</button>
		                <button id="save" style="margin-left: 20px;" ng-click="save()" class="btn btn-primary fbtn"><i class="icon-check icon-white"></i>保存</button>
		              	<!-- <button id="generate" style="margin-left: 10px;" ng-click="addYuegmjh()" class="btn btn-primary fbtn" ><i class="icon-ok-circle icon-white"></i> 生成</button>	 -->              
		                <button id="delete" style="margin-left: 10px;" ng-click="delYuegmjh()" class="btn btn-danger fbtn"><i class="icon-trash icon-white"></i> 删除</button>
		                <button id="submit" style="margin-left: 10px;" ng-click="submitYuegmjh()" class="btn btn-primary fbtn"><i class="icon-file icon-white"></i> 提交</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example" >
				<thead>
					<tr>
					<th style="width: 40px;" rowspan="2">全选 <br> <input
						type="checkbox" ng-model="selectAll" id="selectAll" >
					</th>
						<th style="text-align: center;">日期</th>
						<th style="text-align: center;">计划量</th>
						<th style="text-align: center;">备注</th>
					</tr>
				</thead>
				<tbody ng-click="fun()" id="page" >
					<tr ng-repeat="data in datalist track by $index">
						<td style="width: 20px;">
							<input type="checkbox" ng-checked="selectAll" id="{{$index}}" >
						</td>
						<td>{{data.JIHGMRQ}}</td>
						<td><input type="text" ng-model="data.ZHIBZ" /></td>
						<td><input type="text" ng-model="data.BEIZ" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	<!-- <my-page data="datalist" ></my-page>  -->	
	</div>
</div>


<div class="modal fade" id="myModal_Del" style="display:none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">删除调度计划</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除调度计划？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deleteYuegmjh()"><i class="icon-ok-sign icon-white"></i> 确认
            </button>
            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
            </button>
         </div>
      </div>
   </div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
/* 		function check(args){
			if($(args).attr("checked")!=undefined){
				$("input[type='checkbox']").attr("checked",false);
				$(args).attr("checked",true);
			}
		} */
	});
</script>