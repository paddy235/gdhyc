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
	font-family:Microsoft YaHei;
}

td input,td select {
	width: 100% !important;
	height: 37px !important;
	margin: 0px !important;
	padding: 0px !important;
	border: 0px !important;
	text-align: center !important;
	font-family:Microsoft YaHei;
	background-color: transparent !important;
	line-height: 35px !important;
}
/* td select{
padding:8px 0 !important;
text-indent: 60px !important; 
} */
.operation{
margin-top:5px;
width: 30px !important;
}
</style>
<div class="tab-pane" ng-controller="kucwzCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<button class="btn btn-primary" id="adddata" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button class="btn btn-success" id="fujsc" ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>

			</div>
		</div>
	</div>

	<form id="form1" runat="server">
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover display"
				id="example" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>库存物料代码</th>
						<th>库存物料描述</th>
						<th style="width :16%">操作</th>
						<th >所属库存物料</th>
						<th>所属单位</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="row in kucwzList track by $index" class="option">
						<!-- 库存物料代码 -->
						<td ng-dblclick="changeInput($event.target)"><input
							type="text" ng-model="row.value.WUZBM" class="wuzbm"
							ng-blur="checkWuzbm($event.target)" /> <!-- {{row.value.WUZBM}} -->
						</td>

						<!-- 库存物料描述 -->
						<td><input type="text" ng-model="row.value.MIAOS" /></td>
						<!-- 操作 -->
						<td id="{{$index}}" class="update">
							<!-- Unnamed (Image) --> 
							<img 
							ng-if="row.value.ZHUANGT=='启用'" ng-click="on($event.target)"
							class="img operation"  src="/fuelmis/images/kucgl/on-gray.png" />
							<img  ng-if="row.value.ZHUANGT=='停用'"
							ng-click="on($event.target)" class="img operation" 
							src="/fuelmis/images/kucgl/on-green.png" /> &nbsp <!-- Unnamed (Image) -->
							<img  ng-if="row.value.ZHUANGT=='停用'"
							ng-click="off($event.target)" class="img operation" 
							src="/fuelmis/images/kucgl/off-gray.png" /> 
							<img 
							ng-if="row.value.ZHUANGT=='启用'" ng-click="off($event.target)"
							class="img operation"  src="/fuelmis/images/kucgl/off-red.png" />

						</td>
						<!-- 所属库存物料 -->
						<td><input type="text" ng-model="row.value.WUZMC" /></td>
						<!-- 所属单位 -->
						<td><select id="meik" ng-model="row.value.DIANC_ID"
							ng-options="option.value as option.name for option in diancList">
						</select></td>
						<!-- 状态 -->
						<td class="zhuangt"><input type="text"
							ng-model="row.value.ZHUANGT" onfocus=this.blur() class="state" />
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
	</form>

	<!-- END FORM-->
</div>

<script type="text/javascript">
	/* $(document).ready(function() {
	 var table = $('#example').DataTable();
	
	
	 } ); */
</script>