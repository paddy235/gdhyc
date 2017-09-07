'<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/fuelmis/css/table.css">
<style type="text/css">
 #example thead tr>th{white-space: nowrap !important;padding:5px 15px!important;}
</style>
<div class="tab-pane" ng-controller="zafjswhzaCtrl">
	<div class="block-content collapse in ">
		
		<form id="form1" runat="server">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">			
				<button style="margin-left: 20px;" class="btn btn-primary"  ng-click="add()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save" ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
				<button style="margin-left: 5px;"  id="refresh" class="btn btn-success" ng-click="refresh()">
		            	<i class=" icon-refresh icon-white"></i> 刷新
		        </button>
			</div>
		</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" style="margin-bottom: 0px"
					id="example">
					<thead>
						<tr>
							<th>供货单位</th>
							<th>合同编号</th>
							<th>运杂费项目</th>
							<th>开始日期</th>
							<th>结束日期</th>
							<th>车数</th>
							<th>票重</th>
							<th>单价合计</th>
							<th>不含税价</th>
							<th>煤款</th>
							<th>税金</th>
							<th>实付金额</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr ng-repeat="row in list | filter : s track by $index  " class="option" > -->
						<tr ng-repeat="row in list track by $index  " class="option" >
							<!-- 供货单位 -->
							<td><select  ng-model="row.GONGYSB_ID" ng-options="option.value as option.name for option in gongysList"
								 ></select></td>
							<td>
								<input type="text" ng-model="row.HETBH">
							</td>
							<!--运杂费项目 -->
							<td>
							<select  ng-model="row.YZFXM" ng-options="option.value as option.name for option in yzfxmList">
							</select>
							</td>
							<td>
							<input class="datepicker"  type="text" ng-model="row.KAISRQ" >
							</td>
							<td>
							<input class="datepicker"  type="text" ng-model="row.JIESRQ"> 
							</td>
							<!-- 车数-->
							<td><input type="text" ng-model="row.CHES" /></td>
							<!-- 票重-->
							<td><input type="text" ng-model="row.PIAOZ" /></td>
							<!-- 单价合计-->
							<td><input type="text" ng-model="row.JIESJG" /></td>
							<!-- 不含税价-->
							<td><input type="text" ng-model="row.MEIKJE" /></td>
							<!-- 煤款-->
							<td><input type="text" ng-model="row.MEIKJE" /></td>
							<!-- 税金-->
							<td><input type="text" ng-model="row.SHUIK" /></td>
							<!-- 实付金额-->
							<td><input type="text" ng-model="row.JIESJE" /></td>
							<td><button class="btn btn-danger"  ng-click="del($index)" >删除</button></td>
						</tr>
					</tbody>
				</table>
				<!-- <span>{{result}}</span> -->
			</div>
		</form>
	</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
	
</script>
