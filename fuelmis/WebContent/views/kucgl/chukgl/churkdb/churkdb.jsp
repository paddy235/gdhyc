<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.ui-datepicker-calendar {
	display: none;
}

th {
	width:100px;
	text-align: center !important;
}

td {
	text-align: center !important;
	margin: 0px !important;
	padding: 0px !important;
	height: 30px !important;
}

td input,td select {
	height: 100% !important;
	width: 100% !important;
	/* 	height: 37px !important; */
	margin: 0px !important;
	padding: 0px !important;
	border: 0px !important;
	text-align: center !important;
	/* font-size: 16px !important; */
	background-color: transparent !important;
}

.img{
	width : 30px !important;
	margin-top:5px;
}
#example th,td{white-space: nowrap;padding:5px 15px;}
</style>
<div class="tab-pane" ng-controller="churkdbCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<!-- 日期 -->
				<label style="width: 64px; height: 30px; line-height: 30px; float: left;"
					class="control-label span1">业务日期:</label>
				<input style="width:120px; float: left;" id="datepicker" ng-model="search.riq"
					ng-change="load()" type="text">
				<label style="width:60px; margin-right:5px;margin-left:20px;height: 30px; line-height: 30px; float: left;"
					class="control-label">单位名称:</label> 
				<select style="width:120px; float: left;"  ng-model="search.DIANCXXB_ID" ng-change="load()"
					ng-options="option.value as option.name for option in diancList">
				</select>
				<label style="width:60px; margin-right:5px;margin-left:20px;height: 30px; line-height: 30px; float: left;"
					   class="control-label">业务类型:</label>
				<select ng-model="search.YEWLX" style="float: left; width: 165px;"
						ng-options="option.ID as option.LEIXMC for option in churkywlxListForChurkdhd">
				</select>
				<button style="margin-left: 20px;" ng-click="load()" class="btn btn-success">
					<i class="icon-search icon-white"></i> 刷新
				</button>
				<button style="margin-left: 20px;" class="btn btn-primary"
					id="add" ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save"
					ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
				<button style="margin-left: 10px;" class="btn btn-danger" id="del"
					ng-click="del()">
					<i class="icon-check icon-white"></i> 删除
				</button>
			</div>
		</div>
			<div style="overflow:scroll;width:99%" class="row-fluid">
				<table style="width:2000px" class="table table-striped table-bordered table- hover" id="example">
					<thead>
						<tr>
							<th style="width:50px">全选<input type="checkbox" ng-model="checkAll" ng-click="checkAllFun()"></th>
							<th style="width:50px">序号</th>
							<th style="width:90px">入库单编号</th>
							<th style="width:90px">组织</th>
							<th style="width:90px">库存组织</th>
							<th style="width:90px">库存位置</th>
							<th style="width:90px">库存物料</th>
							<th style="width:90px">货主</th>
							<th style="width:90px">金额</th>
							<th style="width:90px">调整金额</th>
							<th style="width:90px">业务类型</th>
							<th style="width:90px">出入库类型</th>
							<th style="width:90px">业务日期</th>
							<th style="width:90px">会计日期</th>
							<th style="width:90px">记账日期</th>
							<th style="width:90px">运单数量</th>
							<th style="width:90px">入库数量</th>
							<th style="width:90px">结算数量</th>
							<th style="width:90px">机组</th>
							<th style="width:90px">班组</th>
							<th style="width:90px">结算单入账日期</th>
							<th style="width:90px">供应商</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in list track by $index">
							<td><input type="checkbox" id={{$index}} ng-checked="row.CHECK==1"></td>
							<!-- 序号 -->
							<td>{{$index+1}}</td>
							<!--  入库单编号 -->
							<td ng-model="row.RUKDBH">{{row.RUKDBH}}</td>
							<!-- 组织 -->
							<td><select style="line-height: 28px; padding: 2px 0" ng-model="row.ZUZ"
										ng-options="option.value as option.name for option in zuzList">
							</select></td>
							<!-- 库存组织 -->
							<td><select style="line-height: 28px; padding: 2px 0" ng-model="row.KUCZZ"
										ng-options="option.value as option.name for option in kuczzList">
							</select></td>
							<!-- 库存位置 -->
							<td><select
								style="line-height: 28px; padding: 2px 0" ng-model="row.KUCWZ"
								ng-options="option.value as option.name for option in kucwzList">
								</select></td>
							<!-- 库存物料 -->
							<td><select style="line-height: 28px; padding: 2px 0" ng-model="row.KUCWL"
								ng-options="option.value as option.name for option in kucwlList">
								</select></td>
							<!-- 货主 -->
							<td><select style="line-height: 28px; padding: 2px 0" ng-model="row.HUOZ"
										ng-options="option.value as option.name for option in diancList">
							</select></td>
							<!-- 金额 -->
							<td><input type="text" ng-model="row.JINE" /></td>
							<!-- 调整金额 -->
							<td ><input type="text" ng-model="row.TIAOZJE" /></td>
							<!-- 业务类型 -->
							<td>
								<select ng-model="row.YEWLX" style="float: left; width: 165px;"
									ng-options="option.value as option.name for option in churkywlxList">
								</select>
							</td>
							<!--  出入库类型 -->
							<td>
								<select style="line-height: 28px; padding: 2px 0" ng-model="row.CHURKLX">
									<option value="出库">出库</option>
									<option value="入库">入库</option>
								</select>

							</td>
							<!-- 业务日期 -->
							<td>
								<input  class="datepicker" ng-model="row.YEWRQ" type="text">
							</td>
							<!-- 会计日期 -->
							<td>
								<input  class="datepicker0" ng-model="row.KUAIJRQ" type="text">
							</td>
							<!-- 记账日期-->
							<td>
								<input  class="datepicker" ng-model="row.JIZRQ" type="text">
							</td>
							<!-- 运单数量 -->
							<td><input type="text" ng-model="row.YUNDSL" /></td>
							<!-- 验收数量 -->
							<%--<td><input type="text" ng-model="row.YANSSL" /></td>--%>
							<!-- 入库数量 -->
							<td><input type="text" ng-model="row.RUKSL" /></td>
							<!-- 结算数量 -->
							<td><input type="text" ng-model="row.JIESSL" /></td>
							<!-- 机组 -->
							<td >
								<select  ng-model="row.JIZ_ID"
										 ng-options="option.value as option.name for option in ruljzList">
								</select>
							</td>
							<!--班组-->
							<td >
								<select  ng-model="row.BANZ_ID"
										 ng-options="option.value as option.name for option in rulbzList">
								</select>
							</td>
							<!--计算单入账日期-->
							<td >
								<input  class="datepicker" ng-model="row.JIESDRZRQ" type="text">
							</td>
							<!--供应商-->
							<td >
								<select  ng-model="row.GONGYSB_ID"
										 ng-options="option.value as option.name for option in gongysList">
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});
</script>
