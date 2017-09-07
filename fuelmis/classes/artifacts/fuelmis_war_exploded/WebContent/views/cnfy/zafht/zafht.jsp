
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>
<div class="tab-pane" ng-controller="zafhtCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{zafhtTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label
					style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label">年份:</label> <input
					style="width: 100px; float: left;" id="datepicker"
					ng-model="search.nianf" ng-change="refresh()" type="text">
				<button style="margin-left: 5px;" id="refresh" ng_click="refresh()"
					class="btn btn-success">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
				<button class="btn btn-primary" id="adddata" ng-click="addzafht()">
					<i class="icon-plus icon-white"></i> 新增
				</button>
				<button disabled class="btn btn-info" id="updatezafht"
					ng-click="updatezafht()">
					<i class="icon-edit icon-white"></i> 修改
				</button>
				<button disabled id="delzafht" ng_click="delzafht()"
					class="btn btn-danger">
					<i class=" icon-trash icon-white"></i> 删除
				</button>
				<button style="margin-left: 10px;" class="btn btn-primary"
						id="submit" ng-click="sumbmit()">
					<i class="icon-file icon-white"></i> 提交
				</button>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 20px;"></th>
						<th style="text-align: center; width: 50px;">序号</th>
						<th style="text-align: center; width: 150px;">合同编号</th>
						<th style="text-align: center; width: 150px;">合同名称</th>
						<th style="text-align: center; width: 150px;">合同对方</th>
						<th style="text-align: center; width: 150px;">合同有效期</th>
						<th style="text-align: center; width: 150px;">签订日期</th>
						<th style="text-align: center; width: 150px;">是否提交</th>
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
			$("#delzafht").addClass("btn-danger");
			$("#delzafht").attr('disabled', false);

			$("#updatezafht").addClass("btn-info");
			$("#updatezafht").attr('disabled', false);

			$("input[type='checkbox']").attr("checked", false);
			$(args).attr("checked", true);
		} else {
			$("#delzafht").removeClass("btn-danger");
			$("#delzafht").attr('disabled', true);

			$("#updatezafht").removeClass("btn-info");
			$("#updatezafht").attr('disabled', true);
		}
	}
</script>