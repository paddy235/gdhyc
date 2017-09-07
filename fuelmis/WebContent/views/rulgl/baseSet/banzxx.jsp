'<%@ page language="java" pageEncoding="UTF-8"%>
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
<div class="tab-pane" ng-controller="banzxxCtrl">
	<div class="block-content collapse in ">
		
		<form id="form1" runat="server">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<label
					style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">单位:</label> 
				<select
					style="width: 120px; float: left;" id="meik" ng-model="DIANCXXB_ID"
					ng-change="refresh()"
					ng-options="option.value as option.name for option in diancList">
				</select>
				<button style="margin-left: 20px;" class="btn btn-primary"
					id="adddata" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
 				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save"
					ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
				<!-- <input type="text" ng-model="s" /> -->
			</div>
		</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" style="margin-bottom: 0px"
					id="example">
					<thead>
						<tr>
							<th>排序号</th>
							<th>班组名称</th>
							<th>备注</th>
							<th style="width:20%">操作</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr ng-repeat="row in list | filter : s track by $index  " class="option" > -->
						<tr ng-repeat="row in list track by $index  " class="option" >
							<!-- 排序号 -->
							<td><input type="text" ng-model="row.XUH"
								onfocus=this.blur() /></td>
							<!-- 班组名称 -->
							<td><input type="text" ng-model="row.MINGC" /></td>
							<!-- 备注 -->
							<td><input type="text" ng-model="row.BEIZ" /></td>
							<!-- 操作 -->
							<td id="{{$index}}" class="update"><img id=""
								ng-if="row.ZHUANGT=='启用'" ng-click="on($event.target)"
								class="img"  src="/fuelmis/images/kucgl/on-gray.png" />
								<img id="" ng-if="row.ZHUANGT=='停用'" ng-click="on($event.target)"
								class="img"  src="/fuelmis/images/kucgl/on-green.png" />
								&nbsp <img id="" ng-if="row.ZHUANGT=='停用'"
								ng-click="off($event.target)" class="img" 
								src="/fuelmis/images/kucgl/off-gray.png" /> <img id=""
								ng-if="row.ZHUANGT=='启用'" ng-click="off($event.target)"
								class="img"  src="/fuelmis/images/kucgl/off-red.png" /></td>
							<!-- 状态 -->
							<td class="zhuangt"><input type="text" ng-model="row.ZHUANGT"
								onfocus=this.blur() class="state" /></td>
						</tr>
						<tr ng-repeat="row in array track by $index" class="option">
			
							<!-- 排序号 -->
							<td><input type="text" ng-model="row.XUH"
								onfocus=this.blur() /></td>
							<!-- 班组名称 -->
							<td><input type="text" ng-model="row.MINGC" /></td>
							<!-- 备注 -->
							<td><input type="text" ng-model="row.BEIZ" /></td>
							<!-- 操作 -->
							<td id="{{$index}}" class="insert"><img id=""
								ng-if="row.ZHUANGT=='启用'" ng-click="on($event.target)"
								class="img"  src="/fuelmis/images/kucgl/on-gray.png" />
								<img id="" ng-if="row.ZHUANGT=='停用'" ng-click="on($event.target)"
								class="img"  src="/fuelmis/images/kucgl/on-green.png" />
								&nbsp <img id="" ng-if="row.ZHUANGT=='停用'"
								ng-click="off($event.target)" class="img" 
								src="/fuelmis/images/kucgl/off-gray.png" /> <img id=""
								ng-if="row.ZHUANGT=='启用'" ng-click="off($event.target)"
								class="img"  src="/fuelmis/images/kucgl/off-red.png" /></td>
							<!-- 状态 -->
							<td class="zhuangt"><input type="text" ng-model="row.ZHUANGT"
								onfocus=this.blur() class="state" /></td>
						</tr>
					</tbody>
				</table>
				<!-- <span>{{result}}</span> -->
			</div>
		</form>
	</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
	
</script>
