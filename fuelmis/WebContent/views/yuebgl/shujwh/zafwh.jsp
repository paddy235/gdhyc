<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="zafwhCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">时间:</label> <input
						id="datepicker1" type="text" style="float: left; width: 120px;margin-right:5px;margin-left:5px;"
						ng-model="search.riq"><label style="width:40px;margin-right:5px;"
						class="control-label">单位:</label> <select
						ng-model="search.diancid" style="float: left; width: 120px;"
						ng-options="option.value as option.name for option in diancList"></select>
					<button style="margin-left: 20px;" ng-click="searchData()"
						class="btn btn-success"><i class="icon-search icon-white"></i>刷新
					</button>
					<button style="margin-left: 20px; display: none;" class="btn btn-primary" id="add" ng-click="add()">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;  display: none;" ng-click="delSigle()" class="btn" id="delBtn">删除</button>
					<button style="margin-left: 10px; " ng-click="saveData()" class="btn" id="saveBtn">保存</button>
					<button class="btn btn-primary" id="copyzafei" ng-click="copyzafei()" id="copyBtn">
						<i class="icon-file icon-white"></i> 复制上月计划
				</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" id="example">
				<thead>
					<tr>
						<th style="width: 2%;"></th>
						<th style="text-align: center; width: 3%;">序号</th>
						<th style="text-align: center; width: 18%;">电厂名称</th>
						<th style="text-align: center; width: 25%;">杂费名称</th>
						<th style="text-align: center; width: 20%;">当月金额</th>
						<th style="text-align: center; width: 32%;">备注</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="row in list track by $index" ng-click="radioch()">
						<td ><input  type="radio"  name="check"  ng-model="row.ID" value="{{row.XUH}}"/></td>
						<!-- 序号 -->
						<td style="text-align: center;" ><input type="text" style="width: 50px;text-align: center;" ng-model="row.XUH" onfocus=this.blur() disabled="disabled"/></td>
						<!-- 电厂名称 -->
						<td style="text-align: center;"><input  type="text" ng-model="row.DIANCXXB_ID"  ></select></td>
						<!-- 杂费名称 -->
						<td style="text-align: center;"><input type="text" ng-model="row.MINGC" >							
						</select></td>
						<!-- 当月金额 -->
						<td style="text-align: center;">
							<input type="text" style="width: 150px;text-align: right;" ng-model="row.JINE" value="{{row.JINE}}">
						</td>
						<!-- 备注-->
						<td style="text-align:center;"><input class="datepicker0 haoyrq" type="text" style="width: 320px;" ng-model="row.BEIZ"  value="{{row.BEIZ}}"/></td>
						<td style="text-align:center;display: none;">{{row.RIQ=search.riq}}</td>
					</tr>
					<tr>
					<td></td>
					<td></td>
					<td></td>
					<td style="text-align: center;">合计</td>
					<td style="text-align: center;">{{hej|number : 2}}</td>
					<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#datepicker1").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
	});
</script>