<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="yuebscCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 70px;margin-right:5px;" class="control-label">日期:</label> 
					<input id="datepicker1" type="text" style="float: left; width: 120px;" ng-model="search.riq"> 
					<label style="width: 70px;margin-right:5px;" class="control-label">单位:</label> 
					<select ng-model="search.diancid" style="float: left; width: 120px;"
						ng-options="option.value as option.name for option in diancList">
					</select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="submitYueb()" class="btn btn-primary">
						<i class="icon-upload icon-white"></i> 提交
					</button>
				</form>
			</div>

			<table cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered" id="example">
				<thead>
					<tr>
						<th></th>
						<th>月报</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<tr class="odd gradeX" ng-repeat="yueb in yuebList"
						on-table-finish-render-filters>
						<td class="center"><input type="checkbox"  ng-model="yueb.SHIFSB"   ng-disabled="yueb.COLOR=='已上报'"></td>
						<td class="center">{{yueb.MINGC}}</td>
						<td class="center">{{yueb.COLOR}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 提示框 -->
<div my-progress="showProgress"></div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>