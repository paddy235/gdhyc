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
td select{
padding:8px 0 !important;
}
.operation{
margin-top:5px;
width : 30px !important;
}
  .mymodal {
        position: fixed;
        top: 22%;
        left: 63%;
        z-index: 1050;
        width: 109px; 
    }
</style>
<div class="tab-pane" ng-controller="guanlCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="margin-bottom:20px;">
			<div class="table-toolbar">
				<button class="btn btn-primary" id="add" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button class="btn btn-success" id="save" ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
				<button style="margin-left: 5px;"  id="refresh" ng-click="load()" class="btn btn-success"><i class="icon-refresh icon-white"></i> 刷新</button>
			</div>
		</div>
		
		<form id="form1" runat="server">
			<div class="row-fluid">
	
				<table class="table table-striped table-bordered table-hover"
					id="example">
					<thead>
						<tr>
							<th>会计期</th>
							<th>库存组织</th>
							<th style="width: 15%">操作</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in guanlList track by $index " class="option">
							<!-- 会计期 -->
							<td><select class="kuaijq" style="text-align: center;" 
								ng-change="check($event.target)"
								ng-model="row.KUAIJQ_ID"
								ng-options="option.value as option.name for option in kuaijqList">
							</select></td>
							<!-- 库存组织 -->
							<td><select class="kuczz" ng-change="check($event.target)"
								ng-model="row.KUCZZ_ID" 
								ng-options="option.value as option.name for option in kuczzList">
							</select></td>
							<!-- 操作 -->
							<td id="{{$index}}" class="update">
								<button class="btn btn-success"  ng-disabled="row.ZHUANGT=='启用'" ng-click="on($index)" >启用</button>
								<button class="btn btn-danger" ng-disabled="row.ZHUANGT=='停用'" ng-click="off($index)" >停用</button>
							</td>
							<!-- 状态 -->
							<td><input type="text" ng-model="row.ZHUANGT"
								onfocus=this.blur() /></td>
	
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
  <!-- 提示框 -->
<%--    <div style="background-color: transparent;" class="mymodal fade"
         id="myModal_Edit" tabindex="-1" role="dialog" data-backdrop="static" style="display:none;"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <img class="img operation" src="/fuelmis/images/circle-bar.gif"/>
    </div>--%>
	<div my-progress="isDisplay"></div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
	
</script>
