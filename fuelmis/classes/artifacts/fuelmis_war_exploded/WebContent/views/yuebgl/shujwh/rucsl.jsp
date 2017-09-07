<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" id="rucsl" ng-controller="rucslCtrl" style="height: 100%;">
<!-- 	<div class="navbar navbar-inner block-header "> 
		<div class="muted pull-left">{{rucslTitle}}</div>
	</div> -->
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">日期:</label> <input
						id="datepicker1" type="text" style="float: left; width: 120px;"
						ng-model="search.riq"><label style="width:40px;margin-right:5px;"
						class="control-label">单位:</label> <select
						ng-model="search.diancid" style="float: left; width: 120px;"
						ng-options="option.value as option.name for option in diancList"></select>
					<button style="margin-left: 20px;" ng-click="searchData()"
						class="btn btn-success"><i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="createData_Win()" class="btn" id="createBtn">生成</button>
					<button style="margin-left: 10px;" ng-click="delData_Win()" class="btn" id="delBtn">删除</button>
					<button style="margin-left: 10px;" ng-click="saveData()" class="btn" id="saveBtn">保存</button>
					<button style="margin-left: 10px;" ng-click="printTable()" class="btn" id="printBtn">打印</button>
				</form>
			</div>
		</div>
		<div class="row-fluid" style="width: 100%;height: 100%;overflow: auto;">
			<div style="width: 1770px;">
				<table id="mytable" class="table table-striped table-bordered table-hover table-fixed-header" id="example">
					<thead class="header">
						<tr>
							<th style="width: 20px;"></th>
							<th style="text-align: center; width: 150px;">供货单位</th>
							<th style="text-align: center; width: 100px;">计划口径</th>
							<th style="text-align: center; width: 100px;">品种</th>
							<th style="text-align: center; width: 100px;">运输方式</th>
							<th style="text-align: center; width: 100px;">分项</th>
							<th style="text-align: center; width: 100px;">净重</th>
							<th style="text-align: center; width: 100px;">票重</th>
							<th style="text-align: center; width: 100px;">盈吨</th>
							<th style="text-align: center; width: 100px;">亏吨</th>
							<th style="text-align: center; width: 100px;">运损</th>
							<th style="text-align: center; width: 100px;">总扣吨</th>
							<th style="text-align: center; width: 100px;">检斤量</th>
							<th style="text-align: center; width: 100px;">入厂调整量</th>
							<th style="text-align: center; width: 100px;">盈吨折金额</th>
							<th style="text-align: center; width: 100px;">亏吨折金额</th>
							<th style="text-align: center; width: 100px;">索赔数量</th>
							<th style="text-align: center; width: 100px;">索赔金额</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="sub in rucslList |subArray :0:2">
							<td></td>
							<td style="text-align: center;">{{sub.GONGYSB_ID}}</td>
							<td style="text-align: center;">{{sub.JIHKJB_ID}}</td>
							<td style="text-align: center;">{{sub.PINZB_ID}}</td>
							<td style="text-align: center;">{{sub.YUNSFSB_ID}}</td>
							<td style="text-align: center;">{{sub.FENX}}</td>
							<td style="text-align: right;">{{sub.JINGZ}}</td>
							<td style="text-align: right;">{{sub.BIAOZ}}</td>
							<td style="text-align: right;">{{sub.YINGD}}</td>
							<td style="text-align: right;">{{sub.KUID}}</td>
							<td style="text-align: right;">{{sub.YUNS}}</td>
							<td style="text-align: right;">{{sub.ZONGKD}}</td>
							<td style="text-align: right;">{{sub.JIANJL}}</td>
							<td style="text-align: right;">{{sub.RUCTZL}}</td>
							<td style="text-align: right;">{{sub.YINGDZJE}}</td>
							<td style="text-align: right;">{{sub.KUIDZJE}}</td>
							<td style="text-align: right;">{{sub.SUOPSL}}</td>
							<td style="text-align: right;">{{sub.SUOPJE}}</td>
						</tr>
						<tr ng-repeat="sub in rucslList |subArray :2">
							<td>{{$index+1}}</td>
							<td style="text-align: center;">{{sub.GONGYSB_ID}}</td>
							<td style="text-align: center;">{{sub.JIHKJB_ID}}</td>
							<td style="text-align: center;">{{sub.PINZB_ID}}</td>
							<td style="text-align: center;">{{sub.YUNSFSB_ID}}</td>
							<td style="text-align: center;">{{sub.FENX}}</td>
							<td style="text-align: right;">
								<%--<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.JINGZ}}</span>--%>
								<input  ng-disabled="sub.FENX!='本月'||sub.ZHUANGT==1"  type="text" style="width: 50px;text-align: right;" ng-model="sub.JINGZ">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.BIAOZ}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.BIAOZ">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.YINGD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.YINGD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.KUID}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.KUID">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.YUNS}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.YUNS">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.ZONGKD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.ZONGKD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.JIANJL}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.JIANJL">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.RUCTZL}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.RUCTZL">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.YINGDZJE}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.YINGDZJE">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.KUIDZJE}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.KUIDZJE">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.SUOPSL}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.SUOPSL">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.SUOPJE}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.SUOPJE">
							</td>
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
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>