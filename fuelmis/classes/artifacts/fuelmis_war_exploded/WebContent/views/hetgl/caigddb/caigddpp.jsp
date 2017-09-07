<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<style>
tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
.arrow{
	color:blue
}
</style>

<div class="tab-pane" ng-controller="caigddppCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{caigddppTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">单位:</label>
					<select style="width: 120px;float:left;" id="dianc_id" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select> 
					<label style="width:100px;margin-right:5px;" class="control-label">煤矿单位:</label>
					<select style="width: 120px;float:left;" id="meikdw_id" ng-model="search.meikid"  ng-options="option.value as option.name for option in meikxxList"></select> 
					<label style="width:50px;margin-right:5px;" class="control-label">煤种:</label>
					<select style="width: 120px;float:left;" id="pinz_id" ng-model="search.pinzid"  ng-options="option.value as option.name for option in pinzList"></select> 
					<button style="margin-left: 5px;" id="refresh" ng_click="refresh()" class="btn btn-success">
						<i class=" icon-refresh icon-white"></i> 刷新
					</button>
					<button style="margin-left: 5px;" id="count" ng_click="add()" class="btn btn-danger">
						<i class="icon-plus icon-white"></i> 新增
					</button>
					<button style="margin-left: 5px;" id="publish" ng_click="save()" class="btn btn-info">
						<i class="icon-check icon-white"></i> 保存
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid" style="overflow-x:scroll">
			<table class="table table-striped table-bordered table-hover"
				id="example" >
				<thead>
					<tr>
						<th style="white-space: nowrap;width: 40px;" rowspan="2">全选 <br> <input type="checkbox" ng-model="selectAll" id="selectAll" > </th>
						<th style="white-space: nowrap; text-align: center; width: 50px; vertical-align: middle"  >序号</th>
						<th style="text-align: center; width: 150px; vertical-align: middle;cursor:pointer" ng-click="dwsort()" >
							煤矿单位
							<ul style="float:right">
								<li id="mka" class="glyphicon glyphicon-arrow-down"></li>
							</ul>
						</th>
						<th style="text-align: center; width: 150px; vertical-align: middle;cursor:pointer" ng-click="mzsort()" >煤种
						<ul style="float:right">
								<li id="mza" class="glyphicon glyphicon-arrow-down"></li>
						</ul>
						</th>
						<th style="text-align: center; width: 150px; vertical-align: middle;cursor:pointer" ng-click="qsrqsort()" >起始日期
						<ul style="float:right">
								<li id="qsa" class="glyphicon glyphicon-arrow-down"></li>
						</ul>
						</th>
						<th style="text-align: center; width: 150px; vertical-align: middle;cursor:pointer" ng-click="jzrqsort()" >结束日期
						<ul style="float:right">
								<li id="jza" class="glyphicon glyphicon-arrow-down"></li>
						</ul>
						</th>
						<th style="text-align: center; width: 150px; vertical-align: middle"  >货主</th>
						<th style="text-align: center; width: 450px; vertical-align: middle"  >合同发货订单信息</th>
						<th style="text-align: center; width: 150px; vertical-align: middle"  >操作</th>
					</tr>
				</thead>
				<tbody ng-click="fun()" id="page" >
					<tr ng-repeat="data in datalist track by $index">
						<td style="width: 20px;"> <input type="checkbox" ng-checked="selectAll" id="{{$index}}" > </td>
						<td style="text-align: center;">{{$index+1}}</td>
						<td style="text-align: center;"><select style="width: 140px;float:left;"  ng-model="data.MEIKXXB_ID"  ng-options="option.value as option.name for option in meikxxList"></select></td><!-- 日期 -->
						<td style="text-align: center;"><select style="width: 140px;float:left;"  ng-model="data.MEIZ_ID"  ng-options="option.value as option.name for option in pinzList"></select> </td><!-- 供方 -->
						<td style="text-align: center;"><input class="datepicker0" ng-model="data.QISRQ" style="width:140px;float:left;" type="text" style="float:left"></td><!-- 厂方 -->
						<td style="text-align: center;"><input class="datepicker0" ng-model="data.JIESRQ" style="width:140px;float:left;" type="text" style="float:left"></td><!-- 得分 -->
						<td style="text-align: center;"><select style="width: 140px;float:left;"  ng-model="data.HUOZ_ID"  ng-options="option.value as option.name for option in kuczzList"></select></td><!-- 供方 -->
						<td style="text-align: center;"><select style="width: 450px;float:left;"  ng-model="data.CAIGDDB_SUB_ID":14515623455146},{"ID":2,"MEIKXXB_ID"  ng-options="option.value as option.name for option in caigddxxlist"></select></td><!-- 厂方 -->
						<td style="text-align: center;"><input class="btn btn-danger" type="button" ng-click="deleted(data.ID)" value="删除"></td><!-- 得分 -->
					</tr>
				</tbody>
			</table>
		</div>
		<my-page data="datalist" ></my-page>
        <!-- END FORM-->
    </div>
</div>


<script type="text/javascript">
</script>