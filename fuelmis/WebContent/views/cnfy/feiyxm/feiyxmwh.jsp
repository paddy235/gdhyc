
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
#example th,td {
	white-space: nowrap;
}

tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>
<div class="tab-pane" ng-controller="feiyxmwhCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{feiyxmwhTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<button style="margin-left: 10px;" id="adddata"
					class="btn btn-primary" ng-click="addfeiyxmwh()">
					<i class="icon-plus icon-white"></i> 新增
				</button>
				<button style="margin-left: 10px;" disabled id="updatefeiyxmwh"
					ng-click="updatefeiyxmwh()" class="btn btn-info">
					<i class="icon-edit icon-white"></i> 修改
				</button>
				<button style="margin-left: 20px;" disabled id="changestate"
					class="btn btn-danger" ng-click="changestate()">
					<i class=" icon-refresh icon-white"></i> 修改状态
				</button>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 20px;"></th>
						<th style="text-align: center; width: 7%;">序号</th>
						<th style="text-align: center; width: 10%;">编码</th>
						<th style="text-align: center; width: 10%;">名称</th>
						<th style="text-align: center; width: 9%;">单位</th>
						<th style="text-align: center; width: 10%;">说明</th>
						<th style="text-align: center; width: 10%;">属性</th>
						<th style="text-align: center; width: 15%;">费用项目分类</th>
						<th style="text-align: center; width: 10%;">管理分类</th>
						<th style="text-align: center; width: 10%;">财务分类</th>
						<th style="text-align: center; width: 9%;">启动状态</th>
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
			$("#changestate").addClass("btn-primary");
			$("#changestate").removeAttr("disabled"); //移除disabled属性 
			$("#updatefeiyxmwh").addClass("btn-primary");
			$("#updatefeiyxmwh").removeAttr("disabled"); //移除disabled属性 
			$("input[type='checkbox']").attr("checked", false);
			$(args).attr("checked", true);
		} else {
			$("#changestate").removeClass("btn-primary");
			$("#changestate").attr('disabled', true);
			$("#updatefeiyxmwh").removeClass("btn-primary");
			$("#updatefeiyxmwh").attr('disabled', true);
		}
	}
</script>