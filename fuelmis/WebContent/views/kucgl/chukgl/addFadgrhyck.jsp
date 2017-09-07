<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<style>
	#table_h td{
	height: 40px !important;
	}
	</style>
<div class="tab-pane" ng-controller="addFadgrhyckCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="margin-bottom: 20px;">
			<div class="table-toolbar span6" >
				<form class="form-horizontal ng-pristine ng-valid">
				<div class="span12">
					<button style="margin-left: 10px;" ng-click="add()"
						class="btn btn-primary" id="add" ng-disabled="chukd.ZHUANGT==1">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="save()"
						class="btn btn-success" id="save" ng-disabled="chukd.ZHUANGT==1">
						<i class="icon-check icon-white"></i> 保存
					</button>
					<button style="margin-left: 10px;" ng-click="chex()"
						class="btn btn-danger" id="chex" ng-disabled="chukd.ZHUANGT==1">
						<i class="icon-remove-sign icon-white"></i> 撤销
					</button>
					<button style="margin-left: 10px;" ng-click="chuk()"
						class="btn btn-primary" id="chuk" ng-disabled="chukd.ZHUANGT==1">
						<i class="icon-inbox icon-white"></i> 出库
					</button>
					<button style="margin-left: 10px;" ng-click="cancel()" class="btn">
						<i class="icon-repeat"></i> 返回
					</button>
					</div>
					<div class="span12" style="margin-top: 10px;">
						<div class="span6">
							<table id="table_h">
								<tr>
									<td><label style="width: 100px; margin-right: 5px;"
										class="control-label">出库单号:</label></td>
									<td><input type="text" style="float: left; width: 151px;"
										ng-model="chukd.CHUKDBH" readonly="readonly"></td>
									<td><label style="width: 100px; margin-right: 5px;"
										class="control-label">状态:</label></td>
									<td><select ng-model="chukd.ZHUANGT"
										style="float: left; width: 163px;"
										ng-options="option.value as option.name for option in kucztList"
										disabled="disabled">
									</select></td>
								</tr>
								<tr>
									<td><label style="width: 100px; margin-right: 5px;"
										class="control-label">库存组织:</label></td>
									<td><select ng-model="chukd.KUCZZ"
										style="float: left; width: 165px;"
										ng-disabled="chukd.ZHUANGT==1"
										ng-options="option.value as option.name for option in kuczzList">
									</select></td>
									<td><label style="width: 100px; margin-right: 5px;"
										class="control-label"> 出库时间: </label></td>
									<td><input id="datepicker1" type="text"
										style="float: left; width: 150px;" ng-model=chukd.CHUKRQ
										ng-disabled="chukd.ZHUANGT==1"
										ng-change="getChukdbh(chukd.CHUKRQ)"></td>
								</tr>
								<tr>
									<td><label style="width: 100px; margin-right: 5px;"
										class="control-label">货主:</label></td>
									<td><select ng-model="chukd.HUOZ"
										style="float: left; width: 165px;"
										ng-disabled="chukd.ZHUANGT==1"
										ng-options="option.value as option.name for option in diancList">
									</select></td>
									<td><label style="width: 100px; margin-right: 5px;"
										class="control-label">出库数量:</label></td>
									<td><input type="text" style="float: left; width: 135px;"
										ng-model="chukd.CHUKZSL" readonly="readonly"> <label
										style="width: 10px; margin-right: 5px;" class="control-label">吨</label></td>
								</tr>
							</table>
						</div>
					</div>
					
				</form>
			</div>
			<div class="span6"><div  class="span12"><h4>当日结存</h4></div>
				<div  class="span11">
				<table ng-repeat="sub in dangrjcList">
					<tr><td>{{sub.first}}</td><td>{{sub.second}}</td><td>{{sub.third}}</td></tr>
				</table>						
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<table id="asynTr" cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered">
				<thead>
					<tr>
						<th style="text-align: center;">行号</th>
						<th style="text-align: center;">库存位置</th>
						<th style="text-align: center;">库存物料</th>
						<th style="text-align: center;">机组</th>
						<th style="text-align: center;">班组</th>
						<th style="text-align: center;">业务类型</th>
						<th style="text-align: center;">出库数量(吨)</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="sub in chukdhList">
						<td style="text-align: right;">{{$index+1}}</td>
						<td style="text-align: center;"><select
							style="width: 100px; text-align: center;" ng-model="sub.KUCWZ"
							ng-disabled="sub.ZHUANGT==1"
							ng-options="option.value as option.name for option in kucwzList">
						</select></td>
						<td style="text-align: center;"><select
							style="width: 100px; text-align: center;"
							ng-disabled="sub.ZHUANGT==1" ng-model="sub.KUCWL"
							ng-options="option.value as option.name for option in kucwlList">
						</select></td>
						<td style="text-align: center;"><select
							style="width: 100px; text-align: center;" ng-model="sub.JIZ_ID"
							ng-disabled="sub.ZHUANGT==1"
							ng-options="option.value as option.name for option in ruljzList">
						</select></td>
						<td style="text-align: center;"><select
							style="width: 100px; text-align: center;"
							ng-disabled="sub.ZHUANGT==1" ng-model="sub.BANZ_ID"
							ng-options="option.value as option.name for option in rulbzList">
						</select></td>
						<td style="text-align: center;"><select ng-model="sub.YEWLX"
							style="float: left; width: 165px;" ng-disabled="chukd.ZHUANGT==1"
							ng-options="option.value as option.name for option in churkywlx_fdckList">
						</select></td>
						<td style="text-align: center;"><input type="text"
							style="width: 100px; text-align: right;" ng-model="sub.CHUKSL"
							ng-disabled="sub.ZHUANGT==1" ng-change="convertNumbers()">
						</td>

						<td style="text-align: center;">
							<button class="btn btn-danger btn-mini" id="{{$index}}"
								ng-click="del($event.target)" ng-disabled="sub.ZHUANGT==1">
								<i id="{{$index}}" class="icon-remove icon-white" title="删除"></i>
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});

	});
</script>