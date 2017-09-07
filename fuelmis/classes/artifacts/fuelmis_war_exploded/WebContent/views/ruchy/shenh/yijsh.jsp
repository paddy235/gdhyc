<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.b {
display: none;
}

</style>
<div class="tab-pane" ng-controller="yijshCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label style="width:40px;height:30px;line-height:30px; float: left;" class="control-label span1">单位:</label>
				<select style="width: 120px; float: left;" id="meik" ng-model="search.diancID" ng-change="load()" ng-options="option.value as option.name for option in diancList">
				</select>
				
				<label style="width:40px;height:30px;line-height:30px; float:left;" class="control-label span1">状态:</label>
				<select style="width:84px; float:left;" ng-model="search.zhuangt"
					ng-change="load()">
					<option value=0  >未审核</option>
					<option value=1  >已审核</option>	
				</select>
				<label style="width:70px;height:30px;line-height:30px; float: left;" class="control-label span1">开始时间:</label>
			 	<input class="datepicker" ng-model="search.sDate" style="width:92px;float:left;" type="text" style="float:left" ng-change="load()">
		    	<label style="width:70px;height:30px;line-height:30px; float: left;" class="control-label span1">结束时间:</label>
			 	<input class="datepicker" ng-model="search.eDate" style="width:92px;float:left;" type="text" style="float:left" ng-change="load()">
        	
				<div id="example_filter" class="dataTables_filter">
					<input type="search" style="float:right;" placeholder="请输入化验时间或化验编码" aria-controls="example" ng-model="search.condition" 
						ng-change="load()" />
					<label style="height:30px;line-height:30px;">搜索:&nbsp;&nbsp;</label>
				</div>

			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" id="example">
				<thead>
					<tr>
						<th style="text-align: center; width: 50px;">序号</th>
						<th style="text-align: center;">审核内容</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="row in list track by $index" class="option">
						<td style="text-align: center; width: 50px;">
							<br><br><br><br>
							{{$index+1}}
						</td>
						<td style="text-align: center;">
							<table style="text-align: center; width: 100%;">
								<tr>
									<td style=" width: 50%;">化验时间:{{row.HUAYSJ}}</td>
									<td style=" width: 50%;">化验编号:{{row.HUAYBM}}</td>
								</tr>
								<tr>
									<td>全水分MT(%):{{row.MT}}</td>
									<td>空气干燥基水分Mad(%):{{row.MAD}}</td>
								</tr>
								<tr>
									<td>空气干燥基灰分Aad(%):{{row.AAD}}</td>
									<td>空气干燥基挥发分Vad(%):{{row.VAD}}</td>
								</tr>
								<tr>
									<td>空气干燥基全硫St.ad(%):{{row.STAD}}</td>
									<td>弹筒热值Qb.ad(MJ/Kg):{{row.QBAD}}</td>
								</tr>
								<tr>
									<td>收到基全硫St.d(%):{{row.STD}}</td>
									<td>收到基低热值(MJ/Kg):{{row.QNET_AR}}</td>
								</tr>
								<tr>
								<!-- 1焦耳(J)=0.2389卡(cal) -->
								<td>收到基低位热值(kcal/Kg):{{row.QNET_AR_K}}</td>
									<td></td>
								</tr>
	
							</table>
						</td>
						<td style="text-align: center; width: 80px;"><br><br><br>
							<button id="refresh" ng-class="{'b':dis}" ng-click="shenhtg($event.target,row.ID,search.zhuangt)" class="btn btn-success">
								<i class="icon-plus icon-white"></i> 审核
							</button> <br> <br> <br>
							<button class="btn btn-primary" id="adddata" ng-click="huit(row)">
								<i class=" icon-refresh icon-white"></i> 回退
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- END FORM -->	
		<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
			<ul id="pagination_zc" ></ul>
		</div>
	<!-- 	<my-page data="datalist"></my-page> -->
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$(".datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>