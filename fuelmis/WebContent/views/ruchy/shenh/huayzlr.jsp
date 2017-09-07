<%@ page language="java" pageEncoding="UTF-8"%>
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
<div class="tab-pane" ng-controller="huayzlrCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
			<!-- 日期 -->
				<label style="width: 80px; height: 30px; line-height: 30px; float: left;" class="control-label span1">采样日期:</label> 
				<input style="width: 92px; float: left;" id="datepicker" ng-model="search.riq" ng-change="load()" type="text">
				<label
					style="width: 40px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">单位:</label> <select
					style="width: 120px; float: left;" id="meik" ng-model="search.DIANCXXB_ID"
					ng-change="load()"
					ng-options="option.value as option.name for option in diancList">
				</select>
				<button style="margin-left: 20px;" class="btn btn-primary"
					id="adddata" ng-click="load()">
					<i class="icon-plus icon-white"></i> 刷新
				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save"
					ng-click="save()">
					<i class="icon-check icon-white"></i> 提交
				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save"
					ng-click="empty()">
					<i class="icon-check icon-white"></i> 清空
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover"
					id="example">
					<thead>
						<tr>
							<th style="width:10%">化验编号</th>
							<th style="width:5%">全水分Mt(%)</th>
							<th style="width:5%">水分Mad(%)</th>
							<th style="width:5%">灰分Aad(%)</th>
							<th style="width:5%">挥发分Vad(%)</th>
							<th style="width:5%">硫分Stad(%)</th>
							<th style="width:5%">氢Had(%)</th>
							<th style="width:6%">弹筒热量Qbad</th>
							<th style="width:5%">T1(℃)</th>
							<th style="width:5%">T2(℃)</th>
							<th style="width:5%">T3(℃)</th>
							<th style="width:5%">T4(℃)</th>
							<th style="width:10%">化验员</th>
							<th style="width:8%">化验录入员</th>
							<th style="width:8%">化验备注</th>
							<th style="width:8%">化验类别</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in list track by $index" class="option">					
							<!-- 化验编号 -->
							<td><input type="text" ng-model="row.HUAYBM" onfocus=this.blur() /></td>
							<!-- 全水分Mt(%) -->
							<td><input type="text" ng-model="row.MT" /></td>
							<!-- 水分Mad(%)-->
							<td><input type="text" ng-model="row.MAD" /></td>
							<!-- 灰分Aad(%) -->
							<td><input type="text" ng-model="row.AAD" /></td>
							<!-- 挥发分Vad(%) -->
							<td><input type="text" ng-model="row.VAD" /></td>
							<!-- 硫分Stad(%) -->
							<td><input type="text" ng-model="row.STAD" /></td>
							<!-- 氢Had(%) -->
							<td><input type="text" ng-model="row.HAD" /></td>
							<!-- 弹筒热量Qbad -->
							<td><input type="text" ng-model="row.QBAD" /></td>
							<!-- T1(℃) -->
							<td><input type="text" ng-model="row.T1" /></td>
							<!-- T2(℃) -->
							<td><input type="text" ng-model="row.T2" /></td>
							<!-- T3(℃) -->
							<td><input type="text" ng-model="row.T3" /></td>
							<!-- T4(℃) -->
							<td><input type="text" ng-model="row.T4" /></td>
							<!-- 化验员 -->
							<td><input type="text" ng-model="row.HUAYY" /></td>
							<!-- 化验录入员 -->
							<td><input type="text" ng-model="row.LURY" /></td>
							<!-- 化验备注 -->
							<td><input type="text" ng-model="row.BEIZ" /></td>
							<!-- 化验类别 -->
							<td><input type="text" ng-model="row.DANJLX" /></td>
						</tr>

					</tbody>
				</table>
			</div>
		</form>
	</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
	
</script>
