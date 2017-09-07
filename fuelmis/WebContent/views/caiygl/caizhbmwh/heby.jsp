<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.ui-datepicker-calendar {
	display: none;
}

th {
	text-align: center !important;
}

td {
	text-align: center !important;
	margin: 0px !important;
	padding: 0px !important;
}

td input,td select {
	width: 100% !important;
	height: 37px !important;
	margin: 0px !important;
	padding: 0px !important;
	border: 0px !important;
	text-align: center !important;
	font-size: 16px !important;
	background-color: transparent !important;
	line-height: 35px !important;
}

label {
	height: 30px !important;
	line-height: 30px !important;
	margin: 0 5px 0 10px !important;
	float: left !important;
}
.converged{
	background-color:yellow !important;
}
</style>
<div class="tab-pane" ng-controller="hebyCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="margin-bottom:20px;">
			<div class="table-toolbar">
				<!-- 日期 -->
				<label style="width: 60px;" class="control-label">采样日期:</label>
				<input style="width: 80px; float: left;" id="datepicker" ng-model="riq" ng-change="load()" type="text"> 
				<label style="width: 60px;" class="control-label span1">采样编号:</label> 
				<select style="width: 180px; float: left;"  ng-model="caiybh" 
					ng-options="option.VALUE as option.NAME for option in samcodeList">
				</select> 
				<button style="margin-left: 20px;" class="btn btn-primary" id="converge" ng-click="converge()">
					<i class="icon-plus icon-white"></i> 合并
				</button>
				<button style="margin-left: 20px;" class="btn btn-danger" id="concel" ng-click="cancel()">
					<i class="icon-refresh icon-white"></i> 撤销
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
					<thead>
						<tr>
							<th width="5%"></th>
							<th width="15%">供应商</th>
							<th width="10%">品种</th>
							<th width="15%">发货日期</th>
							<th width="15%">到货日期</th>
							<th width="20%">采样编号</th>
							<th width="10%">车数</th>
							<th width="10%">票重</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in list track by $index" class="option" ng-class="{'converged':row.ISCONVERGED=='1'}">
							<td><input id="{{$index}}"  type="checkbox" ng-model="row.check"  ></td>
							<!-- 供应商 -->
							<td><input  type="text" ng-model="row.GONGYSMC" onfocus=this.blur() /></td>
							<!-- 品种 -->
							<td><input  type="text" ng-model="row.PINZ" onfocus=this.blur()  /></td>
							<!-- 发货日期 -->
							<td><input  type="text" ng-model="row.FAHRQ" onfocus=this.blur() /></td>
							<!-- 到货日期 -->
							<td><input  type="text" ng-model="row.DAOHRQ" onfocus=this.blur() /></td>
							<!-- 采样编号 -->
							<td>
							<input ng-if="row.check!=true"  type="text" ng-model="row.SAMCODE" onfocus=this.blur() />
							<input ng-if="row.check==true" type="text" ng-model="caiybh" onfocus=this.blur() />
							</td>
							<!-- 车数-->
							<td><input  type="text" ng-model="row.CHES" onfocus=this.blur() /></td>
							<!-- 票重-->
							<td><input  type="text" ng-model="row.PIAOZ" onfocus=this.blur() /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">

</script>
