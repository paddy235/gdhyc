<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<style>
tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>

<div class="tab-pane" ng-controller="yuezbpfCtrl">
	<%--<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{rizbpfTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">供应商:</label>
					<select ng-model="search.gongysid" style="float: left;width: 120px;" 	
					ng-options="option.value as option.name for option in gongysList">					
					</select>
					<label style="width: 80px;margin-right:5px;" class="control-label">开始日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width: 15px;margin:0 5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
					<button style="margin-left: 5px;" id="refresh" ng-click="refresh()" class="btn btn-success">
						<i class=" icon-refresh icon-white"></i> 刷新
					</button>
					<button style="margin-left: 5px;" id="count" ng_click="computeScore()" class="btn btn-danger">
						<i class=" icon-refresh icon-white"></i> 计算
					</button>
					<button style="margin-left: 5px;" id="publish" ng_click="fab()" class="btn btn-info">
						<i class=" icon-refresh icon-white"></i> 发布
					</button>
					<button style="margin-left: 5px;" id="back" ng_click="fabCancel()" class="btn btn-info">
						<i class=" icon-refresh icon-white"></i> 取消
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 40px;text-align:center;">
							全选 <br>
							<input type="checkbox" ng-model="selectAll" id="selectAll">
						</th>
						<th style="text-align: center; width: 50px;vertical-align: middle">序号</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">供应商名称</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">开始日期</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">结束日期</th>
						<th style="text-align: center; width: 100px;vertical-align: middle">合同量</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">收煤量</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">合同量得分</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">日平均分</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">总分</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">发布时间</th>
					</tr>
				</thead>
				<tbody ng-click="fun()" id="page" >
                        <tr ng-repeat="data in datalist track by $index">
                            <td style="width: 20px;text-align:center;"><input id="{{$index}}" type="checkbox" ng-checked="selectAll"></td>
                            <td style="text-align:center;">{{$index+1}}</td>
                            <td style="text-align:center;">{{data.GONGYSMC}}</td>
                            <td style="text-align:center;">{{data.KAISRQ}}</td>
                            <td style="text-align:center;">{{data.JIESRQ}}</td>
                            <td style="text-align:center;">{{data.HETL}}</td>
                            <td style="text-align:center;">{{data.LAIML}}</td>
                            <td style="text-align:center;">{{data.HETLJF}}</td>
                            <td style="text-align:center;">{{data.RIJF}}</td>
                            <td style="text-align:center;">{{data.ZONGF}}</td>
                            <td style="text-align:center;">{{data.FABSJ}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
       <my-page data="datalist"></my-page>
</div>
<script type="text/javascript">
	//日期插件
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