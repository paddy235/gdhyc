<%@ page language="java" pageEncoding="UTF-8"%>

<style>
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
td select{
padding:8px 0 !important;
}
.operation{
margin-top:5px;
width: 30px !important;
}
</style>
<div class="tab-pane" ng-controller="weizCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="margin-bottom:20px;">
			<div class="table-toolbar">
				<button class="btn btn-primary" id="adddata" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button class="btn btn-success" id="fujsc" ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover"
					id="example">
					<thead>
						<tr>
							<th style="width: 15%">库存位置代码</th>
							<th style="width: 20%">库存位置描述</th>
							<th style="width: 10%">操作</th>
							<th style="width: 20%">所属库存位置</th>
							<th style="width: 15%">所属库存组织</th>
							<th style="width: 15%">所属单位</th>
							<th style="width: 5%">状态</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in weizList track by $index" class="option">
							<!-- 库存位置代码 -->
							<td><input type="text" ng-model="row.KUCWZDM" class="kucwzdm"
								ng-blur="checkKucwzdm($event.target)" /></td>
							<!-- 库存位置描述 -->
							<td><input type="text" class="kucwzms"
								ng-blur="checkKucwzms($event.target)" ng-model="row.KUCWZMS" />
							</td>
							<!-- 操作 -->
							<td id="{{$index}}" class="update">
								<!-- Unnamed (Image) --> 
								<img id="" ng-if="row.ZHUANGT=='启用'"
								ng-click="on($event.target)" class="img operation" 
								src="/fuelmis/images/kucgl/on-gray.png" /> <img id=""
								ng-if="row.ZHUANGT=='停用'" ng-click="on($event.target)"
								class="img operation"  src="/fuelmis/images/kucgl/on-green.png" />
	
								&nbsp <!-- Unnamed (Image) --> <img id=""
								ng-if="row.ZHUANGT=='停用'" ng-click="off($event.target)"
								class="img operation"  src="/fuelmis/images/kucgl/off-gray.png" />
								<img id="" ng-if="row.ZHUANGT=='启用'"
								ng-click="off($event.target)" class="img operation" 
								src="/fuelmis/images/kucgl/off-red.png" />
	
							</td>
							<!-- 所属库存位置 -->
							<td><select class="fukucweiz" id="meik" ng-model="row.FUID"
								ng-options="option.ID as option.KUCWZMS for option in weizList">
							</select></td>
							<!-- 所属库存组织 -->
							<td><select class="kuczz" id="meik" ng-model="row.KUCZZ_ID"
								ng-options="option.value as option.name for option in kuczzList">
							</select></td>
							<!-- 所属单位 -->
							<td><select id="meik" ng-model="row.DIANC_ID"
								ng-options="option.value as option.name for option in diancList">
							</select></td>
							<!-- 状态 -->
							<td><input type="text" ng-model="row.ZHUANGT"
								onfocus=this.blur() /></td>
						</tr>
						<tr ng-repeat="row in weizArray track by $index" class="option">
							<!-- 库存位置代码 -->
							<td><input type="text" ng-model="row.KUCWZDM" class="kucwzdm"
								ng-blur="checkKucwzdm($event.target)" /></td>
							<!-- 库存位置描述 -->
							<td><input type="text" class="kucwzms"
								ng-blur="checkKucwzms($event.target)" ng-model="row.KUCWZMS" />
							</td>
							<!-- 操作 -->
							<td id="{{$index}}" class="insert">
								<!-- Unnamed (Image) --> 
								<img id="" ng-if="row.ZHUANGT=='启用'"
								ng-click="on($event.target)" class="img operation" 
								src="/fuelmis/images/kucgl/on-gray.png" /> 
								<img id=""
								ng-if="row.ZHUANGT=='停用'" ng-click="on($event.target)"
								class="img operation"  src="/fuelmis/images/kucgl/on-green.png" />
	
								&nbsp <!-- Unnamed (Image) --> 
								<img id=""
								ng-if="row.ZHUANGT=='停用'" ng-click="off($event.target)"
								class="img operation"  src="/fuelmis/images/kucgl/off-gray.png" />
								<img id="" ng-if="row.ZHUANGT=='启用'"
								ng-click="off($event.target)" class="img operation" 
								src="/fuelmis/images/kucgl/off-red.png" />
	
							</td>
							<!-- 所属库存位置 -->
							<td><select class="fukucweiz" id="meik" ng-model="row.FUID"
								ng-options="option.ID as option.KUCWZMS for option in weizList">
							</select></td>
							<!-- 所属库存组织 -->
							<td><select class="kuczz" id="meik" ng-model="row.KUCZZ_ID"
								ng-options="option.value as option.name for option in kuczzList">
							</select></td>
							<!-- 所属单位 -->
							<td><select id="meik" ng-model="row.DIANC_ID"
								ng-options="option.value as option.name for option in diancList">
							</select></td>
							<!-- 状态 -->
							<td><input type="text" ng-model="row.ZHUANGT"
								onfocus=this.blur() /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>

	<!-- END FORM-->
</div>

<script type="text/javascript">
	$(function() {
	});
</script>