
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
th {
	text-align: center !important;
}
tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>
<div class="tab-pane" ng-controller="zafjswhCtrl">
	<%--<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{zafjswhTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label
					style="width: 45px; height: 30px; line-height: 30px; float: left;"
					class="control-label">日期:</label> <input
					style="width: 90px; float: left;" id="datepicker"
					ng-model="search.nianf" ng-change="selectnianf()" type="text">
				<button style="margin-left: 5px;" id="refresh"
					class="btn btn-success" ng-click="refresh()">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
				<button class="btn btn-primary" id="adddata"
					ng-click="addzafjsbxd()">
					<i class="icon-plus icon-white"></i> 新增
				</button>
				<button disabled id="updateniandcaig" ng-click="updatezfjs()"
					class="btn btn-info">
					<i class="icon-edit icon-white"></i> 修改
				</button>
				<button disabled id="delzafjswh" ng-click="delzafjswh()"
					class="btn btn-danger">
					<i class=" icon-trash icon-white"></i> 删除
				</button>
				<button class="btn btn-primary" id="copyniandcaigjh"
					ng-click="copyniandcgjh()">
					<i class="icon-file icon-white"></i> 提交审核
				</button>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 4%;"></th>
						<th style="width: 4%;">序号</th>
						<th style="width: 6%;">单据编号</th>
						<th style="width: 6%;">制单日期</th>
						<th style="width: 6%;">制单人</th>
						<th style="width: 5%;">总金额</th>
						<th style="width: 6%;">提交状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- END FORM-->
</div>

<script type="text/javascript">
	function check(args) {
		if ($(args).attr("checked") != undefined) {
			$("#delzafjswh").removeAttr("disabled"); //移除disabled属性 
			$("#updateniandcaig").removeAttr("disabled"); //移除disabled属性 
			$("input[type='checkbox']").attr("checked", false);
			$(args).attr("checked", true);
		} else {
			$("#delzafjswh").attr('disabled', true);
			$("#updateniandcaig").attr('disabled', true);
		}
	}
</script>