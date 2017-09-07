<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class=tab-pane ng-controller="editQitrkCtrl">
<!-- 	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{addQitrkTitle}}</div>
	</div> -->
	<div class="block-content collapse in ">
		<div class="span12" style="margin-bottom:20px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<button style="margin-left: 10px;" ng-click="add()" id="add" class="btn btn-primary">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="save()" id="save" class="btn btn-success">
						<i class="icon-check icon-white"></i> 保存
					</button>
					<button style="margin-left: 10px;" ng-click="chex()" id="chex" class="btn btn-danger">
						<i class="icon-remove-sign icon-white"></i> 撤销
					</button>
					<button style="margin-left: 10px;" ng-click="ruk()" id="ruk" class="btn btn-primary">
						<i class="icon-inbox icon-white"></i> 入库
					</button>
					<button style="margin-left: 10px;" ng-click="cancel()" class="btn">
						<i class="icon-repeat"></i> 返回
					</button>
					<div class="span12" style="margin-top: 10px;">
						<label style="width: 100px;margin-right:5px;" class="control-label">入库单号:</label> 
						<input type="text" style="float: left; width: 150px;" ng-model="ranlrkd.rukdbh" readonly="readonly"> 
						<label style="width: 100px;margin-right:5px;" class="control-label">状态:</label> 
						<select ng-model="ranlrkd.zhuangt" style="float: left; width: 165px;"
							ng-options="option.value as option.name for option in kucztList" disabled="disabled">
						</select>
					</div>
					<div class="span12" style="margin-top: 10px;">
						<label style="width: 100px;margin-right:5px;" class="control-label">库存组织:</label> 
						<select ng-model="ranlrkd.kuczz" style="float: left; width: 165px;"
							ng-options="option.value as option.name for option in kuczzList">
						</select>
						<label style="width: 100px;margin-right:5px;" class="control-label">业务类型:</label> 
						<select ng-model="ranlrkd.yewlx" style="float: left; width: 165px;"
							ng-options="option.value as option.name for option in churkywlxList">
						</select>
					</div>
					<div class="span12" style="margin-top: 10px;">
						<label style="width: 100px;margin-right:5px;" class="control-label">货主:</label> 
						<select ng-model="ranlrkd.huoz" style="float: left; width: 165px;"
							ng-options="option.value as option.name for option in diancList">
						</select>
						<label style="width: 100px;margin-right:5px;" class="control-label">入库数量:</label> 
						<input type="text" style="float: left; width: 100px;" ng-model="ranlrkd.ruksl" readonly="readonly"> 
						<label style="width: 10px;" class="control-label">吨</label> 
						<label style="width: 100px;margin-right:5px;" class="control-label">总额:</label> 
						<input type="text" style="float: left; width: 100px;" ng-model="ranlrkd.jine" readonly="readonly"> 
						<label style="width: 10px;" class="control-label">元</label>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			  <table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th style="text-align: center;">行号</th>
						<th style="text-align: center;">库存位置</th>
						<th style="text-align: center;">库存物料</th>
						<th style="text-align: center;">入库数量(吨)</th>
						<th style="text-align: center;">金额(元)</th>
						<!-- <th style="text-align: center;">调整金额(元)</th> -->
						<th style="text-align: center;">总额(元)</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="sub in ranlcgrkList">
						<td style="text-align: right;">{{$index+1}}</td>
						<td style="text-align: center;">
							<select style="width: 100px;text-align: center;" ng-model="sub.kucwz" ng-if="sub.zhuangt==1"
								ng-options="option.value as option.name for option in kucwzList" disabled="disabled">
							</select>
							<select style="width: 100px;text-align: center;" ng-model="sub.kucwz" ng-if="sub.zhuangt!=1"
								ng-options="option.value as option.name for option in kucwzList">
							</select>
						</td>
						<td style="text-align: center;">
							<select style="width: 100px;text-align: center;" ng-model="sub.kucwl" ng-if="sub.zhuangt==1"
								ng-options="option.value as option.name for option in kucwlList" disabled="disabled">
							</select>
							<select style="width: 100px;text-align: center;" ng-model="sub.kucwl" ng-if="sub.zhuangt!=1"
								ng-options="option.value as option.name for option in kucwlList">
							</select>
						</td>
						<td style="text-align: center;"><span ng-if="sub.zhuangt==1" style="text-align: right;">{{sub.shul}}</span><input ng-if="sub.zhuangt!=1"
								type="text" style="width: 100px;text-align: right;" ng-model="sub.shul" ng-change="convertNumbers()" onkeyup='this.value=this.value.replace(/[^(0-9,.)]/gi,"")'></td>
						<td style="text-align: center;"><span ng-if="sub.zhuangt==1" style="text-align: right;">{{sub.jine}}</span><input ng-if="sub.zhuangt!=1"
								type="text" style="width: 100px;text-align: right;" ng-model="sub.jine" ng-change="convertMoney()" onkeyup='this.value=this.value.replace(/[^(0-9,.)]/gi,"")'></td>
						<!-- <td style="text-align: center;"><span ng-if="sub.zhuangt==1" style="text-align: right;">{{sub.tiaozje}}</span><input ng-if="sub.zhuangt!=1"
								type="text" style="width: 100px;text-align: right;" ng-model="sub.tiaozje" ng-change="convertMoney()" onkeyup='this.value=this.value.replace(/[^(0-9,.)]/gi,"")'></td>
						 --><td style="text-align: right;">{{sub.zongje}}</td>
						<td style="text-align: center;">
	                     	<button ng-if="sub.zhuangt!=1" class="btn btn-danger btn-mini" ng-click="del(sub)"><i class="icon-remove icon-white" title="删除"></i></button>
	                    </td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>