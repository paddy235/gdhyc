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

<div class="tab-pane" ng-controller="rizbpfCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{rizbpfTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">单位:</label>
					<select id="dianc_id" style="float: left;width: 120px;">
						<option ng-repeat="dianc in diancList" value="{{dianc.value}}">{{dianc.name}}</option>
					</select>
					<label style="width: 80px;margin-right:5px;" class="control-label">开始日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width: 15px;margin:0 5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
					<button style="margin-left: 5px;" id="refresh" ng_click="refresh()" class="btn btn-success">
						<i class=" icon-refresh icon-white"></i> 刷新
					</button>
					<button style="margin-left: 5px;" id="count" ng_click="computeScore()" class="btn btn-danger">
						<i class=" icon-refresh icon-white"></i> 计算
					</button>
					<button style="margin-left: 5px;" id="publish" ng_click="fab()" class="btn btn-info">
						<i class=" icon-refresh icon-white"></i> 发布
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 40px;" rowspan="2">全选 <br> <input
							type="checkbox" ng-model="selectAll" id="selectAll" >
						</th>
						<th
							style="text-align: center; width: 50px; vertical-align: middle"
							rowspan="2">序号</th>
						<th
							style="text-align: center; width: 150px; vertical-align: middle"
							rowspan="2">名称</th>
						<th
							style="text-align: center; width: 150px; vertical-align: middle"
							rowspan="2">日期</th>
						<th style="text-align: center; width: 150px;" colspan="3">数量SL（吨）</th>
						<th style="text-align: center; width: 150px;" colspan="3">低位热QNET_AR(Kcal/Kg)</th>
						<th style="text-align: center; width: 150px;" colspan="3">收到基硫STAR(%)</th>
						<th
							style="text-align: center; width: 150px; vertical-align: middle"
							rowspan="2">总得分</th>
						<th
							style="text-align: center; width: 150px; vertical-align: middle"
							rowspan="2">发布时间</th>
					</tr>
					<tr>
						<th style="text-align: center; width: 80px;">供方</th>
						<th style="text-align: center; width: 80px;">厂方</th>
						<th style="text-align: center; width: 80px;">得分</th>
						<th style="text-align: center; width: 80px;">供方</th>
						<th style="text-align: center; width: 80px;">厂方</th>
						<th style="text-align: center; width: 80px;">得分</th>
						<th style="text-align: center; width: 80px;">供方</th>
						<th style="text-align: center; width: 80px;">厂方</th>
						<th style="text-align: center; width: 80px;">得分</th>
					</tr>
				</thead>
				<tbody ng-click="fun()" id="page" >
					<tr ng-repeat="data in datalist track by $index">
						<td style="width: 20px;">
							<input type="checkbox" ng-checked="selectAll" id="{{$index}}" >
						</td>
						<td style="text-align: center;">{{$index+1}}</td>
						<td style="text-align: center;">{{data.MINGC}}</td><!-- 名称 -->
						<td style="text-align: center;">{{data.RIQ}}</td><!-- 日期 -->
						<td style="text-align: center;">{{data.GONGFZB1}}</td><!-- 供方 -->
						<td style="text-align: center;">{{data.CHANGFZB1}}</td><!-- 厂方 -->
						<td style="text-align: center;">{{data.JIF1}}</td><!-- 得分 -->
						<td style="text-align: center;">{{data.GONGFZB2}}</td><!-- 供方 -->
						<td style="text-align: center;">{{data.CHANGFZB2}}</td><!-- 厂方 -->
						<td style="text-align: center;">{{data.JIF2}}</td><!-- 得分 -->
						<td style="text-align: center;">{{data.GONGFZB5}}</td><!-- 供方 -->
						<td style="text-align: center;">{{data.CHANGFZB3}}</td><!-- 厂方 -->
						<td style="text-align: center;">{{data.JIF5}}</td><!-- 得分 -->
						<td style="text-align: center;">{{data.ZONGDF}}</td><!-- 总得分 -->
						<td style="text-align: center;">{{data.FABSJ}}</td><!-- 发布时间 -->
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