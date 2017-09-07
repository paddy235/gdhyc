<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
 #asynTr th,#asynTr td{white-space: nowrap !important;padding:5px 15px!important;}
</style>
<div class="tab-pane" ng-controller="ribtbCtrl">
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
					<button style="margin-left: 10px;" ng-click="RibtbShangc()" class="btn" id="ShangcBtn"><i class="icon-upload"></i> 上传</button>
				</form>
			</div>
		</div>
		<div class="row-fluid" style="width: 100%;height: 100%;overflow: auto;">
			 <table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="example table table-striped table-bordered">
				<thead>
					<tr>
						<th style="text-align: center;">发电量<br>(万千瓦时)</th>
						<th style="text-align: center;">供热量(吉焦)</th>
						<th style="text-align: center;">净重(吨)</th>
						<th style="text-align: center;">票重(吨)</th>
						<th style="text-align: center;">运损(吨)</th>
						<th style="text-align: center;">盈吨(吨)</th>
						<th style="text-align: center;">亏吨(吨)</th>
						<th style="text-align: center;">发电用(吨)</th>
						<th style="text-align: center;">供热用(吨)</th>
						<th style="text-align: center;">其他用(吨)</th>
						<th style="text-align: center;">非生成用(吨)</th>
						
						<th ng-if="ribList[0].CUNS!=null" style="text-align: center;">存损(吨)</th>
						<th ng-if="ribList[0].TIAOZL!=null" style="text-align: center;">调整量(吨)</th>
						<th ng-if="ribList[0].SHUIFCTZ!=null" style="text-align: center;">水分差调整(吨)</th>
						<th ng-if="ribList[0].PANYK!=null" style="text-align: center;">盘盈亏(吨)</th>
						
						<th style="text-align: center;">厂外煤场<br>进煤量(吨)</th>
						<th style="text-align: center;">库存(吨)</th>
						<th style="text-align: center;">不可调煤量(吨)</th>
						<th style="text-align: center;">是否停机</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="sub in ribList">
						<td style="text-align: right;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.FADL"></td>
						<td style="text-align: right;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.GONGRL"></td>
						<td style="text-align: right;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.JINGZ"></td>
						<td style="text-align: right;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.BIAOZ"></td>
						<td style="text-align: right;">{{sub.YUNS}}</td>
						<td style="text-align: right;">{{sub.YINGD}}</td>
						<td style="text-align: right;">{{sub.KUID}}</td>
						<td style="text-align: center;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.FADY"></td>
						<td style="text-align: center;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.GONGRY"></td>
						<td style="text-align: center;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.QITY"></td>
						<td style="text-align: center;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.FEISCY"></td>
						
						<td ng-if="sub.CUNS!=null" style="text-align: center;"><input  type="text" style="width: 50px;text-align: right;" ng-model="sub.CUNS"></td>
						<td ng-if="sub.TIAOZL!=null" style="text-align: center;"><input  type="text" style="width: 50px;text-align: right;" ng-model="sub.TIAOZL"></td>
						<td ng-if="sub.SHUIFCTZ!=null" style="text-align: center;"><input  type="text" style="width: 50px;text-align: right;" ng-model="sub.SHUIFCTZ"></td>
						<td ng-if="sub.PANYK!=null" style="text-align: center;"><input  type="text" style="width: 50px;text-align: right;" ng-model="sub.PANYK"></td>
						
						<td style="text-align: center;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.CHANGWML"></td>
						<td ng-if="sub.PANYK!=null" style="text-align: right;">{{sub.KUCX=$eval('('+sub.JINGZ+')'+'+'+'('+sub.LKUC+')'+'-'+'('+sub.FADY+')'+'-'+'('+sub.GONGRY+')'+'-'+'('+sub.QITY+')'+'-'+'('+sub.FEISCY+')'+'+'+'('+sub.CUNS+')'+'+'+'('+sub.TIAOZL+')'+'+'+'('+sub.SHUIFCTZ+')'+'+'+'('+sub.PANYK+')') | number : 2}}</td>
						<td ng-if="sub.PANYK==null" style="text-align: right;">{{sub.KUCX=$eval('('+sub.JINGZ+')'+'+'+'('+sub.LKUC+')'+'-'+'('+sub.FADY+')'+'-'+'('+sub.GONGRY+')'+'-'+'('+sub.QITY+')'+'-'+'('+sub.FEISCY+')') | number : 2}}</td>
						
						<td style="text-align: center;"><input type="text" style="width: 50px;text-align: right;" ng-model="sub.BUKDML"></td>
						<td style="text-align: center;"><select style="width: 100px;" ng-model="sub.SHIFTJ"
								ng-options="option.value as option.name for option in sZhuangtList">
						</select></td>
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