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
	height: 30px !important;
}

td input,td select {
	height: 100% !important;
	width: 100% !important;
	/* 	height: 37px !important; */
	margin: 0px !important;
	padding: 0px !important;
	border: 0px !important;
	text-align: center !important;
	/* font-size: 16px !important; */
	background-color: transparent !important;
}

td input {
	line-height: 28px !important;
}
.img{
	width : 30px !important;
	margin-top:5px;
}
</style>
<div class="tab-pane" ng-controller="meihyCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<!-- 日期 -->
				<label style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">日期:</label> 
				<input style="width:120px; float: left;" id="datepicker" ng-model="riq"
					ng-change="selectriq()" type="text"> 
				<label style="width:60px; margin-right:5px;margin-left:20px;height: 30px; line-height: 30px; float: left;"
					class="control-label">单位名称:</label> 
				<select style="width:120px; float: left;"  ng-model="DIANCXXB_ID" ng-change="refresh()"
					ng-options="option.value as option.name for option in diancList">
				</select>
				<button style="margin-left: 20px;" class="btn btn-primary"
					id="add" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save"
					ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table- hover" id="example">
					<thead>
						<tr>
							<th width="15%">耗用日期</th>
							<th width="15%">入炉班组</th>
							<th width="10%">入炉机组</th>
							<th width="10%">发电耗用(吨)</th>
							<th width="10%">供热耗用(吨)</th>
							<th width="10%">其他用(吨)</th>
							<th width="10%">非生产用</th>
							<th width="10%">制样人</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in list.meihylist track by $index">
							<!-- 耗用日期 -->
							<td><input class="datepicker0 haoyrq" type="text"
								ng-model="row.HAOYRQ" /></td>
							<!--  入炉班组 -->
							<td><select class="kuczz"
								style="line-height: 28px; padding: 2px 0" ng-model="row.RULBZB_ID"
								ng-options="option.ID as option.MINGC for option in list.banzlist">
							</select></td>
							<!-- 入炉机组 -->
							<td><select class="kuczz" ng-model="row.JIZFZB_ID"
								ng-options="option.ID as option.MINGC for option in list.jizlist">
							</select></td>
							<!-- 发电耗用(吨) -->
							<td><input type="text" ng-model="row.FADHY" /></td>
							<!-- 供热耗用(吨) -->
							<td><input type="text" ng-model="row.GONGRHY" /></td>
							<!-- 其他用(吨) -->
							<td><input type="text" ng-model="row.QITY" /></td>
							<!-- 非生产用 -->
							<td><input type="text" ng-model="row.FEISCY" /></td>
							<!-- 制样人 -->
							<td><input type="text" ng-model="row.ZHIYR" /></td>
							<!-- 操作 -->
							<td id="{{$index}}" class="update"><img
								ng-click="del($event.target)" class="img" 
								src="/fuelmis/images/kucgl/delete.png" /></td>
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
