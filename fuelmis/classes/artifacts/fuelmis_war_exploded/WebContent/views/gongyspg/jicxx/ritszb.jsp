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
</style>

<div class="tab-pane" ng-controller="ritszbCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label class="control-label" style="float:left;width:50px;margin-right:5px;margin-left:20px;">供应商:</label>
					<select id="selectType" ng-model="search.gongysid" style="width:120px;float:left;" ng-options="option.value as option.name for option in gongysList" ng-change="load()"></select>
			              
					<label style="width: 80px;margin-right:5px;" class="control-label">开始日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width: 15px;margin:0 5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
					
					<button style="margin-left: 5px;" id="load" ng_click="load()" class="btn btn-success">
						<i class=" icon-refresh icon-white"></i> 刷新
					</button>
					<button style="margin-left: 5px;" id="save" ng_click="save()" class="btn btn-info">
						<i class=" icon-refresh icon-white"></i> 保存
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th width="20%">序号</th>
						<th width="20%">日期</th>
						<th width="20%">供应商</th>
						<th width="20%">指标名称</th>
						<th width="20%">考核</th>
					</tr>
				</thead>
				<tbody ng-click="fun()" id="page" >
					<tr ng-repeat="data in datalist track by $index">
						<td >{{$index+1}}</td><!-- 序号 -->
						<td >{{data.DAOHRQ}}</td><!-- 日期 -->
						<td >{{data.MINGC}}</td><!-- 供应商 -->
						<td >{{data.ZHIBMC}}</td><!-- 指标名称-->
						<td><select  ng-model="data.KAOHBZ">
						<option value=1>是</option>
						<option value=0>否</option>
						</select></td>
					</tr>
				</tbody>
			</table>
		</div>
		<my-page data="datalist" ></my-page>
        <!-- END FORM-->
    </div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
		
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
</script>