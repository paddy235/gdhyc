<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<style>
.ui-datepicker-calendar {
	display: none;
}
table input{
color: blue !important;
}
</style>
<link rel="stylesheet" type="text/css" href="/fuelmis/css/table.css">
<div class="tab-pane" ng-controller="rucbmdjCtrl">
	<div class="block-content collapse in ">
		<form class="form-horizontal ng-pristine ng-valid">
			<div class="row-fluid" style="overflow:auto;">
				<div class="table-head" style=" width:2000px;">			
					<div class="table-toolbar" style="height: 42px;">
                        <label style="width:50px;margin-right:5px;" class="control-label">时间:</label>
                        <input id="datepicker1" type="text" style="float: left; width: 120px;margin-right:5px;margin-left:5px;" ng-model="search.riq">
                        <label style="width:40px;margin-right:5px;" class="control-label">单位:</label>
                        <select ng-model="search.diancid" style="float: left; width: 120px;" ng-options="option.value as option.name for option in diancList"></select>
                        <button style="float: left;margin-left: 20px;" ng-click="searchData()"
                                class="btn btn-success"><i class="icon-search icon-white"></i>刷新
                        </button>
                        <!-- <button style="margin-left: 10px;" ng-click="createData_Win()" class="btn" id="createBtn">生成</button>
                        <button style="margin-left: 10px;" ng-click="delData_Win()" class="btn" id="delBtn">删除</button> -->
                        <button style="float: left;margin-left: 10px;" ng-click="createData()" class="btn" >生成</button>
                        <button style="float: left;margin-left: 10px;" ng-click="delData()" class="btn" >删除</button>
                        <button style="float: left;margin-left: 10px;" ng-click="saveData()" class="btn" id="saveBtn">保存</button>
                        <button style="float: left;margin-left: 10px;" ng-click="printTable()" class="btn" id="printBtn">打印</button>
					</div>							
					<table class="table table-striped table-bordered table-hover my-table-header">
							<thead>
							<tr id="tablehead" >
								<th style="width: 5.5%;">供应商</th>
								<th style="width: 5.5%;">计划口径</th>
								<th style="width: 5.5%;">品种</th>
								<th style="width: 5.5%;">运输</th>
								<th style="width: 5.5%;">分项</th>
								<th style="width: 5.5%;">结算量</th>
								<th style="width: 5.5%;">含税煤价</th>
								<th style="width: 5.5%;">煤价税</th>
								<th style="width: 5.6%;">交货前杂费</th>
								<th style="width: 5.6%;">总含税运价</th>
								<th style="width: 5.5%;">总运价税</th>
								<th style="width: 5.5%;">到站杂费</th>
								<th style="width: 5.5%;">杂费</th>
								<th style="width: 5.5%;">杂费税</th>
								<th style="width: 5.5%;">其他</th>
								<th style="width: 5.5%;">结算热量</th>
								<th style="width: 5.5%;">标煤单价</th>
								<th style="width: 5.8%;">不含税标煤单价</th>
								<th style="width: 0.3%;"></th>
							</tr>
				         </thead>	
					</table>
				</div>
				<div class="table-body" style="width:2000px;">
					<table class="table table-striped table-bordered table-hover my-table-header">
						<tbody>
							<tr ng-repeat="sub in rucbmdjList">
							<td style="width: 5.5%;">{{sub.GMINGC}}</td>
							<td style="width: 5.5%;">{{sub.JMINGC}}</td>
							<td style="width: 5.5%;">{{sub.PMINGC}}</td>
							<td style="width: 5.5%;">{{sub.YSMINGC}}</td>
							<td style="width: 5.5%;">{{sub.FENX}}</td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.JIESL}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.JIESL">
							</td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.MEIJ}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.MEIJ">
							</td>
							<td style="text-align: right;width: 5.5%;">
								<!-- <span ng-if="sub.FENX!='本月'">{{sub.MEIJS=sub.MEIJ}}</span> -->
								<span>{{sub.MEIJS=$eval(sub.MEIJ+"/"+1.17+"*"+0.17)| number : 2}}</span>
								<!-- <input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.MEIJS"> -->
							</td>
							<td style="text-align: right;width: 5.6%;">0</td>
							<td style="text-align: right;width: 5.6%;">
								<span ng-if="sub.FENX!='本月'">{{sub.YUNJ}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.YUNJ">
							</td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.YUNJS}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.YUNJS"></td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.DAOZZF}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.DAOZZF">
							</td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.ZAF}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.ZAF">
							</td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.ZAFS}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.ZAFS">
							</td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.QIT}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.QIT">
							</td>
							<td style="text-align: right;width: 5.5%;">
								<span ng-if="sub.FENX!='本月'">{{sub.QNET_AR}}</span>
								<input ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.QNET_AR">
							</td>
							<td style="text-align: right;width: 5.5%;">{{sub.BIAOMDJ}}</td>
							<td style="text-align: right;width: 5.8%;">{{sub.BUHSBMDJ}}</td>
						</tr>
						</tbody>
					</table>	
				</div>
			</div>
			</form></div></div>
			
	
	<!-- <div class="modal fade" id="myModal_Create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
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
</div> -->

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