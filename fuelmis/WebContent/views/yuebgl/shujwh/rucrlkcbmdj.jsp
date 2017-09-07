<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" id="rucsl" ng-controller="rucrlkcbmdj1" style="height: 100%;">
<!-- 	<div class="navbar navbar-inner block-header "> 
		<div class="muted pull-left">{{rucslTitle}}</div>
	</div> -->
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">日期:</label> <input
						id="datepicker1" type="text" style="float: left; width: 120px;"
						ng-model="search.riq">
					<%--<label style="width:40px;margin-right:5px;"
						class="control-label">单位:</label>--%>
					<%--<select
						ng-model="search.diancid" style="float: left; width: 120px;"
						ng-options="option.value as option.name for option in diancList"></select>--%>
					<button style="margin-left: 20px;" ng-click="searchData()"
						class="btn btn-success"><i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="createData_Win()" class="btn" id="createBtn">生成</button>
					<%--<button style="margin-left: 10px;" ng-click="delData_Win()" class="btn" id="delBtn">删除</button>--%>
					<button style="margin-left: 10px;" ng-click="saveData()" class="btn" id="saveBtn">保存</button>
					<button style="margin-left: 10px;" ng-click="printTable()" class="btn" id="printBtn">打印</button>
				</form>
			</div>
		</div>
		<div class="row-fluid" style="width: 100%;height: 100%;overflow: auto;">
			<div >
				<table id="mytable" class="table table-striped table-bordered table-hover table-fixed-header" id="example">
					<thead class="header">
						<tr>
							<th style="text-align: center; width: 16%;">年月</th>
							<th style="text-align: center; width: 16%;">入厂标单</th>
							<th style="text-align: center; width: 15%;">入炉标单</th>
							<th style="text-align: center; width: 15%;">库存标单</th>
							<th style="text-align: center; width: 15%;">库存较入炉升降</th>
							<th style="text-align: center; width: 15%;">入炉较入厂升降</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="sub in rucslList">
							<td style="text-align: center;">{{sub.RIQ}}</td>
							<td style="text-align: center;">{{sub.RUCBD}}</td>
							<td style="text-align: center;">{{sub.RULBD}}</td>
							<td style="text-align: right;"><input type="text" ng-model="sub.KUCBD"/></td>
							<td style="text-align: center;">{{sub.KUCJRLSJ=sub.KUCBD-sub.RULBD |number:2}}</td>
							<td style="text-align: center;">{{sub.RULBD-sub.RUCBD |number:2}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="myModal_Create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	            <h4 class="modal-title" id="myModalLabel">生成数据</h4>
	         </div>
	         <div class="modal-body">
	            <div class="block-content collapse in">
					<div class="span12">
						生成数据将覆盖原有数据，是否生成？
					</div>
			 	</div>
	         </div>
	         <div class="modal-footer">
	          	<button type="button" class="btn btn-danger" ng-click="createData()"><i class="icon-ok-sign icon-white"></i> 确认</button>
	            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
	         </div>
	      </div>
   		</div>
	</div>
	
	<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	            <h4 class="modal-title" id="myModalLabel">删除数据</h4>
	         </div>
	         <div class="modal-body">
	            <div class="block-content collapse in">
					<div class="span12">
						数据删除后无法还原，确认删除？
					</div>
			 	</div>
	         </div>
	         <div class="modal-footer">
	          	<button type="button" class="btn btn-danger" ng-click="delData()"><i class="icon-ok-sign icon-white"></i> 确认</button>
	            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
	         </div>
	      </div>
   		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy',
			minViewMode :2,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>