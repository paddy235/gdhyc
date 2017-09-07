<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%-- ${ctx}/js/bootstrap/css/ --%>
<link href="${ctx}/js/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="${ctx}/js/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

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
	font-family: Microsoft YaHei;
}

td input,td select,td div {
	margin-bottom: 0px !important;
	width: 100% !important;
	height: 37px !important;
	margin: 0px !important;
	padding: 0px !important;
	border: 0px !important;
	text-align: center !important;
	font-family: Microsoft YaHei;
	background-color: transparent !important;
	/* vertical-align: middle !important; */
	line-height: 35px !important;
}
td select{
margin-top:5px;
padding:8px 0 !important;
}

</style>
<div class="tab-pane" ng-controller="kuaijqdyCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="margin-bottom:20px;">
			<div class="table-toolbar">
				<button class="btn btn-primary" id="add" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button class="btn" id="save" ng-click="save()" ng-disabled="!isSave" ng-class="{'btn-success':isSave}">
					<i class="icon-check icon-white"></i> 保存
				</button>
			</div>
		</div>

		<form id="form1" runat="server">
			<div class="row-fluid">
	
				<table class="table table-striped table-bordered table-hover" id="example">
					<thead>
						<tr>
							<th>顺序号</th>
							<th>会计期间</th>
							<th>起始日期</th>
							<th>截止日期</th>
							<th>电厂名称</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in kuaijqdyList track by $index" class="option">
							<td>
							<input class="" ng-model="row.SHUNXH"  type="text" onfocus=this.blur() >
							</td>
							<td>
							<input class="datepicker0 kuaijq" ng-model="row.KUAIJRQ"  type="text" >
							</td>
							<td>
							<input class="datepicker"  type="text" ng-model="row.KAISRQ" >
							</td>
							<td>
							<input class="datepicker"  type="text" ng-model="row.JIESRQ">
							</td>
							<td>
							<select  ng-model="row.DIANC_ID" class="dianc" ng-options="option.value as option.name for option in diancList">
							</select>
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
	var ctx = '${ctx}';
</script>
