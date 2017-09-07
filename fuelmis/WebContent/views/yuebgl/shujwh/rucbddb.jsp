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
<div class="tab-pane" ng-controller="rucbddb1">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<!-- 日期 -->
				<label style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">日期:</label> 
				<input style="width:120px; float: left;" id="datepicker" ng-model="search.riq"
					ng-change="selectriq()" type="text"> 
				<label style="width:60px; margin-right:5px;margin-left:20px;height: 30px; line-height: 30px; float: left;"
					class="control-label">单位名称:</label> 
				<select style="width:120px; float: left;"  ng-model="search.DIANCXXB_ID" ng-change="refresh()"
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

				<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
					<i class="icon-search icon-white"></i> 刷新
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table- hover" id="example">
					<thead>
						<tr>
							<th width="10%">日期</th>
							<th width="10%">本月完成值</th>
							<th width="10%">对标单位本月完成值</th>
							<th width="10%">集团下发对标目标值</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in list track by $index">
							<!-- 日期 -->
							<td><input class="datepicker0 haoyrq" type="text"
									   ng-model="row.RIQ" /></td>
							<!-- 本月完成值 -->
							<td><input type="text" ng-model="row.BENYWCZ" /></td>
							<!-- 对标单位本月完成值 -->
							<td><input type="text" ng-model="row.DUIBDWBYWCZ" /></td>
							<!-- 集团下发对标目标值 -->
							<td><input type="text" ng-model="row.JITXFDBMBZ" /></td>
							<td id="{{$index}}" class="update"><img
									ng-click="del($event.target)" class="img"
									src="/fuelmis/images/kucgl/delete.png" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	
</script>
