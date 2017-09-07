
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
<div class="tab-pane" ng-controller="feiyxmsqCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{yustzsqTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label
					style="width: 65px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">组织单位:</label> <select
					style="width: 150px; float: left;" ng-model="search.diancid"
					ng-change="selectdianc()"
					ng-options="option.value as option.name for option in diancList"></select>
				<label
					style="width: 93px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">费用项目分类:</label> <select
					style="width: 150px; float: left;" ng-model="search.feiyxmfl"
					ng-change="selectfeiyxmfl()"
					ng-options="option.value as option.name for option in feiyxmflList">
				</select>
				<button style="margin-left: 20px;" class="btn btn-success"
					ng-click="refresh()">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
				<button style="margin-left: 10px;" disabled id="adddata"
					class="btn btn-primary" ng-click="addfeiyxmsq()">
					<i class="icon-plus icon-white"></i> 新增
				</button>
				<button style="margin-left: 10px;" disabled id="updatefeiyxm"
					ng-click="updatefeiyxmsq()" class="btn">
					<i class="icon-edit icon-white"></i> 修改
				</button>
				<button style="margin-left: 10px;" disabled id="delfeiyxm"
					ng-click="delfeiyxm()" class="btn">
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
						<th style="width: 20px;"></th>
						<th style="text-align: center; width: 50px;">序号</th>
						<th style="text-align: center;">组织单位</th>
						<th style="text-align: center;">编码</th>
						<th style="text-align: center;">费用项目分类</th>
						<th style="text-align: center;">费用名称</th>
						<th style="text-align: center;">管理分类</th>
						<th style="text-align: center;">财务分类</th>
						<th style="text-align: center;">状态</th>
						<th style="text-align: center;">说明</th>
						<th style="text-align: center;">属性</th>
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
			$("#delfeiyxm").addClass("btn-danger");
			$("#delfeiyxm").removeAttr("disabled"); //移除disabled属性 
			$("#submit").addClass("btn-primary");
			$("#submit").removeAttr("disabled"); //移除disabled属性 
			$("#updatefeiyxm").addClass("btn-info");
			$("#updatefeiyxm").removeAttr("disabled"); //移除disabled属性 
			$("input[type='checkbox']").attr("checked", false);
			$(args).attr("checked", true);
		} else {
			$("#delfeiyxm").removeClass("btn-danger");
			$("#delfeiyxm").attr('disabled', true);
			$("#submit").removeClass("btn-primary");
			$("#submit").attr('disabled', true);
			$("#updatefeiyxm").removeClass("btn-info");
			$("#updatefeiyxm").attr('disabled', true);
		}
	}
</script>