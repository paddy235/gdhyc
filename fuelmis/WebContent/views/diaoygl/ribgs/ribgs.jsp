<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="ribgsCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">	
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width:40px;margin-right:5px;" class="control-label">单位:</label>
					<select style="width: 120px;" id="selectType" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success"><i class="icon-search icon-white"></i> 刷新</button>          
	                <button style="margin-left: 10px;" ng-click="createData()" class="btn" id="createBtn"><i class="icon-print"></i> 生成</button>
					<button style="margin-left: 10px;" ng-click="save()" class="btn" id="saveBtn"><i class="icon-download-alt"></i> 保存</button>
					<button style="margin-left: 10px;" ng-click="RibgsShangc()" class="btn" id="ShangcBtn"><i class="icon-upload"></i> 上传</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			 <table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th style="text-align: center;">供货单位</th>
						<th style="text-align: center;">煤矿单位</th>
						<th style="text-align: center;">品种</th>
						<th style="text-align: center;">统计口径</th>
						<th style="text-align: center;">运输方式</th>
						<th style="text-align: center;">票重</th>
						<th style="text-align: center;">车数</th>
						<th style="text-align: center;">来煤数量</th>
						<th style="text-align: center;">热值<br>(MJ/kg)</th>
						<th style="text-align: center;">含税煤价<br>(元/吨)</th>
						<th style="text-align: center;">煤价税<br>(元/吨)</th>
						<th style="text-align: center;">总运价<br>(元/吨)</th>
						<th style="text-align: center;">含税标煤单价<br>(元/吨)</th>
						<th style="text-align: center;">不含税标煤单价<br>(元/吨)</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="sub in ribList">
						<td style="text-align: center;">{{sub.GONGYSB_ID}}</td>
						<td style="text-align: center;">{{sub.MEIKXXB_ID}}</td>
						<td style="text-align: center;">{{sub.PINZB_ID}}</td>
						<td style="text-align: center;">{{sub.JIHKJB_ID}}</td>
						<td style="text-align: center;">{{sub.YUNSFSB_ID}}</td>
						<td style="text-align: right;">{{sub.PIAOZ}}</td>
						<td style="text-align: right;">{{sub.CHES}}</td>
						<td style="text-align: right;">{{sub.LAIMSL}}</td>
						<td style="text-align: right;">{{sub.REZ}}</td>
						<td style="text-align: center;" ng-if="sub.GONGYSB_ID=='合计'"><input type="text" disabled  style="width: 50px;text-align: right;" ng-model="sub.MEIJ"></td>
						<td style="text-align: center;" ng-if="sub.GONGYSB_ID!='合计'"><input type="text" ng-change="changeMeij()" style="width: 50px;text-align: right;" ng-model="sub.MEIJ"></td>
						<td style="text-align: right;">{{sub.MEIJS}}</td>
						<td style="text-align: center;" ng-if="sub.GONGYSB_ID=='合计'"  >
							<input  disabled type="text" style="width: 50px;text-align: right;" ng-model="sub.YUNJ">
						</td>
						<td style="text-align: center;" ng-if="sub.GONGYSB_ID!='合计'">
							<input  ng-change="changeMeij()" type="text" style="width: 50px;text-align: right;" ng-model="sub.YUNJ">
						</td>
						<td style="text-align: right;">{{sub.HANSBMDJ}}</td>
						<td style="text-align: right;">{{sub.BUHSBMDJ}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	            <h4 class="modal-title" id="myModalLabel">提示信息</h4>
	         </div>
	         <div class="modal-body">
	            <div class="block-content collapse in">
					<div class="span12">
						新生成数据将同时覆盖：日收耗存和日估计<span id="tt"></span>的已存在数据，是否继续？
					</div>
			 	</div>
	         </div>
	         <div class="modal-footer">
	          	<button type="button" class="btn btn-danger" ng-click="create()"><i class="icon-ok-sign icon-white"></i>是</button>
	            <button type="button" class="btn"data-dismiss="modal"><i class="icon-remove-circle"></i>否</button>
	         </div>
	      </div>
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