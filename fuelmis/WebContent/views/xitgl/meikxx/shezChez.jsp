<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="block" ng-controller="shezChezCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{shezChezTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<input type="hidden" id="meikxxb_id" name="meikxxb_id" value="">
			<table cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered" id="example">
				<thead>
					<tr>
						<th></th>
						<th>车站名称</th>
					</tr>
				</thead>
				<tbody>
					<tr class="odd gradeX" ng-repeat="chez in chezList"
						on-table-finish-render-filters>
						<td class="center"><input type="checkbox" id="{{chez.value}}"></td>
						<td class="center">{{chez.name}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div style="margin-left: 450px; margin-bottom: 50px;">
		<button type="button" class="btn btn-primary" ng-click="saveKuangzglb()"><i class="icon-check icon-white"></i> 保存</button>
		<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
	</div>
</div>

<script type="text/javascript">
	
</script>