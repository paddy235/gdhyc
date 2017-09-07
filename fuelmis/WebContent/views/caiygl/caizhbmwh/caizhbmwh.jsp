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

label {
	height: 30px !important;
	line-height: 30px !important;
	margin: 0 5px 0 10px !important;
	float: left !important;
}
</style>
<div class="tab-pane" ng-controller="caizhbmwhCtrl">

	<div class="block-content collapse in ">
		<div class="span12" style="margin-bottom:20px;">
			<div class="table-toolbar">
				<label style="width: 60px;" class="control-label span1">单位名称:</label> 
				<select style="width: 120px; float: left;"  ng-model="diancid" ng-change="refresh()"
					ng-options="option.value as option.name for option in diancList">
				</select> 
				<label style="width: 60px;" class="control-label">采样时间:</label>
				<!-- 日期 -->
				<input style="width: 80px; float: left;" id="datepicker" ng-model="search.riq" ng-change="refresh()" type="text"> 

				<button style="margin-left: 20px;" class="btn btn-primary" id="generate" ng-click="generate()">
					<i class="icon-plus icon-white"></i> 生成
				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save" ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
					<thead>
						<tr>
							<th width="20%">采样日期</th>
							<th width="20%">采样编码</th>
							<th width="20%">制样编码</th>
							<th width="20%">化验编码</th>
							<th width="20%">化验状态</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in list track by $index" class="option">
							<!-- 采样日期 -->
							<td><input type="text" ng-model="row.CAIYRQ" onfocus=this.blur() /></td>
							<!-- 采样编码 -->
							<td><input class="caiybm" type="text" ng-model="row.CAIYBM" onfocus=this.blur() /></td>
							<!-- 制样编码 -->
							<td><input ng-blur="check('.zhiybm')" class="zhiybm" type="text" ng-model="row.ZHIYBM" /></td>
							<!-- 化样编码 -->
							<td><input ng-blur="check('.huaybm')" class="huaybm" type="text" ng-model="row.HUAYBM" /></td>
							<!-- 化验状态 -->
							<td>
								<input type="text" ng-if="row.HUAYZT==null" onfocus=this.blur() value="未化验" />
								<input type="text" ng-if="row.HUAYZT!=null" onfocus=this.blur() value="已化验" />
							</td>
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
