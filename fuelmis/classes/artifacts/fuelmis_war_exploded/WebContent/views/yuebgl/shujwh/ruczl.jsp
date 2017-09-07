<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" id="ruczl" ng-controller="ruczlCtrl">
	<!-- <div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{ruczlTitle}}</div>
	</div> -->
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">时间:</label> <input
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
			<div style="width: 4370px;">
				<table class="table table-striped table-bordered table-hover" id="example">
					<thead>
						<tr>
							<th style="width: 20px;"></th>
							<th style="text-align: center; width: 100px;">供货单位</th>
							<th style="text-align: center; width: 100px;">计划口径</th>
							<th style="text-align: center; width: 100px;">品种</th>
							<th style="text-align: center; width: 100px;">运输方式</th>
							<th style="text-align: center; width: 100px;">分项</th>
							<th style="text-align: center; width: 150px;">收到基地位热<br/>(Qnet,ar(Mj/kg))</th>
							<th style="text-align: center; width: 100px;">全水<br/>(Mt)</th>
							<th style="text-align: center; width: 100px;">空干基水<br/>(Mad)</th>
							<th style="text-align: center; width: 150px;">收到基灰分<br/>(Aar)</th>
							<th style="text-align: center; width: 150px;">空干基灰分<br/>(Aad)</th>
							<th style="text-align: center; width: 100px;">干基灰分<br/>(Ad)</th>
							<th style="text-align: center; width: 200px;">干燥无灰基挥发分<br/>(Vdaf)</th>
							<th style="text-align: center; width: 100px;">干基硫<br/>(Std)</th>
							<th style="text-align: center; width: 100px;">空干基氢<br/>(Had)</th>
							<th style="text-align: center; width: 100px;">固定碳<br/>(Fcad)</th>
							<th style="text-align: center; width: 150px;">矿方低位热<br/>(Qnet,ar(Mj/kg))</th>
							<th style="text-align: center; width: 100px;">矿方全水<br/>(Mt)</th>
							<th style="text-align: center; width: 150px;">矿方空干基水<br/>(Mad)</th>
							<th style="text-align: center; width: 200px;">矿方收到基灰分<br/>(Aar)</th>
							<th style="text-align: center; width: 200px;">矿方空干基灰分<br/>(Aad)</th>
							<th style="text-align: center; width: 150px;">矿方挥发分<br/>(Vdaf)</th>
							<th style="text-align: center; width: 150px;">矿方干基硫<br/>(Std)</th>
							<th style="text-align: center; width: 150px;">矿方空干基氢<br/>(Had)</th>
							<th style="text-align: center; width: 150px;">矿方固定碳<br/>(Fcad)</th>
							<th style="text-align: center; width: 100px;">质值不符<br/>煤量(吨)</th>
							<th style="text-align: center; width: 100px;">质值不符<br/>金额(元)</th>
							<th style="text-align: center; width: 100px;">质值不符<br/>灰分(元)</th>
							<th style="text-align: center; width: 150px;">质值不符<br/>挥发分(元)</th>
							<th style="text-align: center; width: 100px;">质值不符<br/>热值(元)</th>
							<th style="text-align: center; width: 100px;">质值不符<br/>硫分(元)</th>
							<th style="text-align: center; width: 150px;">质值不符<br/>灰熔点(元)</th>
							<th style="text-align: center; width: 100px;">索赔<br/>金额(元)</th>
							<th style="text-align: center; width: 100px;">碳索赔<br/>数量(吨)</th>
							<th style="text-align: center; width: 100px;">碳索赔<br/>金额(元)</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="sub in ruczlList">
							<td>{{$index+1}}</td>
							<td style="text-align: center;">{{sub.GONGYSB_ID}}</td>
							<td style="text-align: center;">{{sub.JIHKJB_ID}}</td>
							<td style="text-align: center;">{{sub.PINZB_ID}}</td>
							<td style="text-align: center;">{{sub.YUNSFSB_ID}}</td>
							<td style="text-align: center;">{{sub.FENX}}</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.QNET_AR}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.QNET_AR">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.MT}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.MT">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.MAD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.MAD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.AAR}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.AAR">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.AAD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.AAD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.AD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.AD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.VDAF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.VDAF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.STD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.STD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.HAD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.HAD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.FCAD}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.FCAD">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.QNET_AR_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.QNET_AR_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.MT_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.MT_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.MAD_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.MAD_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.AAR_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.AAR_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.AAD_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.AAD_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.VDAF_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.VDAF_KF">
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.STD_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.STD_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.HAD_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.HAD_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.FCAD_KF}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.FCAD_KF">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.ZHIJBFML}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.ZHIJBFML">
							</td>
							</td>
							<td style="text-align: right;">{{sub.ZHIJBFJE}}</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.ZHIJBFJE_A}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.ZHIJBFJE_A">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.ZHIJBFJE_V}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.ZHIJBFJE_V">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.ZHIJBFJE_Q}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.ZHIJBFJE_Q">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.ZHIJBFJE_S}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.ZHIJBFJE_S">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.ZHIJBFJE_T}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.ZHIJBFJE_T">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.SUOPJE}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.SUOPJE">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.LSUOPSL}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.LSUOPSL">
							</td>
							<td style="text-align: right;">
								<span ng-if="sub.FENX!='本月'||sub.ZHUANGT==1">{{sub.LSUOPJE}}</span>
								<input ng-if="sub.FENX=='本月'&&sub.ZHUANGT==0" type="text" style="width: 50px;text-align: right;" ng-model="sub.LSUOPJE">
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