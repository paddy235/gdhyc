
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
<div class="tab-pane" ng-controller="yustssqCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{yustzsqTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label
					style="width: 35px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">单位:</label> <select
					style="width: 150px; float: left;" ng-model="search.diancid"
					ng-change="selectdianc()"
					ng-options="option.value as option.name for option in diancList"></select>
				<label
					style="width: 65px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">费用名称:</label> <select
					style="width: 150px; float: left;" ng-model="search.zafid"
					ng-change="selectzaf()"
					ng-options="option.value as option.name for option in zafList">
				</select>
				<button style="margin-left: 20px;" class="btn btn-success"
					ng-click="refresh()">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
				<button style="margin-left: 10px;" disabled id="adddata"
					class="btn btn-primary" ng-click="addyuedcgjh()">
					<i class="icon-plus icon-white"></i> 新增
				</button>
				<button style="margin-left: 10px;" disabled id="updateyustz"
					ng-click="updateyustzsq()" class="btn ">
					<i class="icon-edit icon-white"></i> 修改
				</button>
				<button style="margin-left: 10px;" disabled id="delyustz"
					ng-click="delyustz()" class="btn ">
					<i class=" icon-trash icon-white"></i> 删除
				</button>
				<button style="margin-left: 10px;" disabled class="btn" id="submit"
					ng-click="submit()">
					<i class="icon-file icon-white"></i> 提交
				</button>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 5%;"></th>
						<th style="text-align: center;width: 5%;">序号</th>
						<th style="text-align: center;width: 20%;">单位</th>
						<th style="text-align: center;width: 20%;">厂内费用名称</th>
						<th style="text-align: center;width: 10%;">预算额度（元）</th>
						<th style="text-align: center;width: 40%;">说明</th>
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
			$("#submit").addClass("btn-primary");
			$("#submit").removeAttr("disabled"); //移除disabled属性 
			$("#delyustz").addClass("btn-danger");
			$("#delyustz").removeAttr("disabled"); //移除disabled属性 
			$("#updateyustz").addClass("btn-info");
			$("#updateyustz").removeAttr("disabled"); //移除disabled属性 
			$("input[type='checkbox']").attr("checked", false);
			$(args).attr("checked", true);
		} else {
			$("#submit").removeClass("btn-primary");
			$("#submit").attr('disabled', true);
			$("#delyustz").removeClass("btn-danger");
			$("#delyustz").attr('disabled', true);
			$("#updateyustz").removeClass("btn-info");
			$("#updateyustz").attr('disabled', true);
		}
	}
</script>