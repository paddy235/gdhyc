<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.red {
	/* color: #ff0000; */
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
	font-family:Microsoft YaHei;
	text-align: center !important;
	background-color: transparent !important;
	line-height: 35px !important;
}
td select{
padding:8px 0 !important;
text-indent: 60px !important; 
}
.operation{
margin-top:5px;
width: 30px !important;
}
</style>
<div class="tab-pane" ng-controller="kuczzCtrl">
	<div class="block-content collapse in">
		<div class="span12" style="margin-bottom:20px;">
			<div class="table-toolbar">
				<button class="btn btn-primary" id="add" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button class="btn btn-success" id="save" ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
					<thead>
						<tr>
							<th style="">库存组织代码</th>
							<th style="">库存组织描述</th>
							<th style="width:15%">操作</th>
							<th style="">所属单位</th>
							<th style="">状态</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in kuczzList track by $index" class="option">
							<!-- 库存组织代码 -->
							<td><input type="text" class="kuczzdm"
								ng-blur="checkKuczzdm($event.target)"
								ng-model="row.value.KUCZZDM" /></td>
							<!-- 库存组织描述 -->
							<td><input type="text" class="kuczzmc"
								ng-blur="checkKuczzms($event.target)"
								ng-model="row.value.KUCZZMC" /></td>
	
							<!-- 操作 -->
							<td id="{{$index}}" class="update">
								<!-- Unnamed (Image) --> 
								<img id=""
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
							<!-- 所属单位 -->
							<td><select id="meik" ng-model="row.value.DIANC_ID"
								ng-options="option.value as option.name for option in diancList">
							</select></td>
							<!-- 状态 -->
							<td class="zhuangt"><input type="text"
								ng-model="row.value.ZHUANGT" onfocus=this.blur() class="state" />
							</td>
						</tr>
						<tr ng-repeat="row in kuczzArray track by $index" class="option">
							<!-- 库存组织代码 -->
							<td><input type="text" class="kuczzdm"
								ng-blur="checkKuczzdm($event.target)"
								ng-model="row.value.KUCZZDM" /></td>
							<!-- 库存组织描述 -->
							<td><input type="text" class="kuczzmc"
								ng-blur="checkKuczzms($event.target)"
								ng-model="row.value.KUCZZMC" /></td>
	
							<!-- 操作 -->
							<td id="{{$index}}" class="insert">
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
	</div>


	<!-- END FORM-->
</div>

<script type="text/javascript">
/* $(document).ready(function() {
    var table = $('#example').DataTable();
} */
</script>