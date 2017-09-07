<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>

	<style type="text/css">
		#table1 th,td{white-space: nowrap;padding:5px 15px;}
        #table2 th,td{white-space: nowrap;padding:5px 15px;}
		/*#liufen-table input[type="text"]{margin-bottom:0;border:none;}
		#liufen-table td{padding:2px 8px;}
		#rezhi-table input[type="text"]{margin-bottom:0;border:none;}
		#rezhi-table td{padding:2px 8px;}*/
        #table1 th {
            text-align: center !important;
        }

        #table1 td {
            text-align: center !important;
            margin: 0px !important;
            padding: 0px !important;
        }

        #table1 td input,td select {
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
	</style>
</head>
</html>
<div class="block" ng-controller="caigddbAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{caigddbTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="caigddb.id">
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">订单编号</label>
						<div class="controls">
							<input type="text" ng-model="caigddb.bianh" style="float: left" required />
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">订单类型</label>
						<div class="controls">
							<select  ng-model="caigddb.caigddlx" ng-change="typeselect()" style="float: left">
								<option value="燃煤采购">燃煤采购</option>
								<option value="运费订单">运费订单</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">需方</label>
						<div class="controls">
							<select id="selectType" ng-model="caigddb.diancxxb_id" style="float: left"  ng-options="option.value as option.name for option in diancList"></select>
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">供方</label>
						<div class="controls">
							<select id="selectType" ng-model="caigddb.gongys_id" style="float: left"  ng-options="option.value as option.name for option in gongysList2"></select>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">开始时间</label>
						<div class="controls">
							<input id="datepicker2" ng-model="caigddb.kaissj" type="text" style="float:left">
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">结束时间</label>
						<div class="controls">
							<input id="datepicker3" ng-model="caigddb.jiessj" type="text" style="">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">订单日期</label>
						<div class="controls">
							<input id="datepicker1" ng-model="caigddb.dingdrq" type="text" style="float: left" ng-change="getBianh()">
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">口径类型</label>
						<div class="controls">
							<select id="selectType" ng-model="caigddb.kouj_id" style="float: left"  ng-options="option.value as option.name for option in jihkjList2"></select>
						</div>
					</div>
				</div>
			</form>

			<legend></legend>
			<div class="pull-left" style="width:100%;padding: 20px 100px 19px 50px;">
				<button type="button" class="btn btn-primary" ng-click="addCaigddbsub()">
					<i class="icon-plus icon-white"></i> 添加
				</button>
			</div>
			<div id="table1" style="width:100%;overflow: auto; overflow-y:hidden">
				<table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
					<thead>
					<tr>
						<th style="text-align:center;">序号</th>
						<!-- <th style="text-align:center;">物料编码</th> -->
						<th style="text-align:center;">供应商</th>
						<th style="text-align:center;">煤矿</th>
						<th style="text-align:center;">品种</th>
						<th style="text-align:center;">数量(吨)</th>
						<th style="text-align:center;">价格类型</th>
						<th style="text-align:center;">合同价<br/>(元/吨)</th>
						<th style="text-align:center;">合同价（不含税）<br/>(元/吨)</th>
						<th style="text-align:center;display:none;">运杂费（元/吨）</th>
						<th style="text-align:center;display:none;">运杂费（不含税）<br/>(元/吨)</th>
						<th style="text-align:center;">到厂价</th>
						<th style="text-align:center;">到厂价（不含税）</th>
						<th style="text-align:center;">运输方式</th>
						<th style="text-align:center;">有效期开始</th>
						<th style="text-align:center;">有效期结束</th>
						<th style="text-align:center;">质量结算依据</th>
						<th style="text-align:center;">数量结算方式</th>
						<th style="text-align:center;">是否加权平均</th>
						<th style="text-align:center;">计价方式</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody >
					<tr ng-repeat="sub in caigddbsubList track by $index">
						<td>{{$index+1}}</td>
						<!-- <td>
							<select style="width: 80px;" ng-model="sub.meiz_id"  ng-options="option.value as option.name for option in kucwlList">
							</select>
						</td> -->
						<td>
							<select style="width: 100px;" ng-model="sub.gongys_id"  ng-options="option.value as option.name for option in gongysList2">
							</select>
						</td>
						<td>
							<select style="width: 100px;" ng-model="sub.meikxxb_id"  ng-options="option.value as option.name for option in meikxxList2">
							</select>
						</td>
						<td>
							<select style="width: 80px;" ng-model="sub.pinz_id"  ng-options="option.value as option.name for option in pinzList2">
							</select>
						</td>
						<td><input type="text" style="width:45px;height:15px" ng-model="sub.shul" value={{sub.shul}}></td>
						<td>
							<select style="width: 100px;" ng-model="sub.hetjsfsb_id"  ng-options="option.value as option.name for option in jiagtypeList">
							</select>
						</td>
						<td><input type="text" style="width:45px;height:15px" ng-model="sub.pingcj" value={{sub.pingcj}} ng-change="fillRmData({{$index}})"></td>
						<td><input type="text" style="width:55px;height:15px" ng-model="sub.pingcj_bhs" value={{sub.pingcj_bhs}} ng-change="fillRmData2({{$index}})"></td>
						<td style="display:none;"><input type="text" style="width:60px;height:15px;" ng-model="sub.yunzf" value={{sub.yunzf}} ng-change="fillRmData({{$index}})"></td>
						<td style="display:none;"><input type="text" style="width:60px;height:15px;" ng-model="sub.yunzf_bhs" value={{sub.yunzf_bhs}} ng-change="fillRmData2({{$index}})"></td>
						<td><input type="text" style="width:55px;height:15px" ng-model="sub.daocj" value={{sub.daocj}}></td>
						<td><input type="text" style="width:55px;height:15px" ng-model="sub.daocj_bhs" value={{sub.daocj_bhs}}></td>
						<td>
							<select style="width: 80px;" ng-model="sub.yunsfs_id"  ng-options="option.value as option.name for option in yunsfsList2">
							</select>
						</td>
						<td><input type="text" class="datepicker0" style="width:80px;height:15px" ng-model="sub.startdate" value={{sub.startdate}}></td>
						<td><input type="text" class="datepicker1" style="width:80px;height:15px" ng-model="sub.enddate" value={{sub.enddate}}></td>
						<td>
							<select style="width: 100px;"  ng-model="sub.zhiljsyj" >
								<option value="厂方化验">厂方化验</option>
								<option value="矿方化验">矿方化验</option>
								<option value="第三方化验">第三方化验</option>
							</select>
						</td>
						<td>
							<select style="width: 90px;" ng-model="sub.shuljsyj" >
								<option value="厂方">厂方</option>
								<option value="矿方">矿方</option>
								<option value="第三方">第三方</option>
							</select>
						</td>
						<td>
							<select style="width: 50px;" ng-model="sub.jiesfs" >
								<option value="是">是</option>
								<option value="否">否</option>
							</select>
						</td>
						<td style="text-align:center;">
							<button ng-if="sub.id!=0" class="btn btn-danger btn-mini" id="jijfsbutton{{index}}" ng-click="showPricSecheme(sub);">
								<i class="icon-list icon-white"></i>
							</button>
							<button ng-if="sub.id==0" disabled class="btn  btn-mini" id="jijfsbutton{{index}}" ng-click="showPricSecheme(sub);">
								<i class="icon-list icon-white"></i>
							</button>
						</td>
						<td style="text-align:center;">
							<button class="btn btn-danger btn-mini" ng-click="deleteCaigddbsub(sub);">
								<i class="icon-remove icon-white" title="删除"></i>
							</button>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div id="table2" style="display:none">
				<table id="yunfdd"  cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
					<thead>
					<tr>
						<th style="text-align:center;">序号</th>
						<th style="text-align:center;">费用项目</th>
						<th style="text-align:center;">煤矿</th>
						<th style="text-align:center;">发站</th>
						<th style="text-align:center;">到站</th>
						<th style="text-align:center;">运输方式</th>
						<th style="text-align:center;">数量</th>
						<th style="text-align:center;">含税运费单价</th>
						<th style="text-align:center;">不含税运费单价</th>
						<th style="text-align:center;">含税杂费单价</th>
						<th style="text-align:center;">不含税杂费单价</th>
						<th style="text-align:center;">数量结算方式</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody >
					<tr ng-repeat="sub in caigyunfsubList track by $index">
						<td>{{$index+1}}</td>
						<td>
							<select style="width: 80px;" ng-model="sub.feiyxm_id"  ng-options="option.value as option.name for option in FeiyxmList">
							</select>
						</td>
						<td>
							<select style="width: 100px;" ng-model="sub.meikxxb_id"  ng-options="option.value as option.name for option in meikxxList">
							</select>
						</td>
						<td>
							<select style="width: 140px;" ng-model="sub.faz_id"  ng-options="option.value as option.name for option in chezList">
							</select>
						</td>
						<td>
							<select style="width: 80px;" ng-model="sub.daoz_id"  ng-options="option.value as option.name for option in chezList">
							</select>
						</td>
						<td>
							<select style="width: 140px;" ng-model="sub.yunsfsb_id"  ng-options="option.value as option.name for option in yunsfsList">
							</select>
						</td>
						<td><input type="text" style="width:50px;height:15px" ng-model="sub.shul" value={{sub.shul}}></td>
						<td><input type="text" style="width:50px;height:15px" ng-model="sub.hansyfdj" value={{sub.hansyfdj}} ng-change="fillYfData({{$index}})"></td>
						<td><input type="text" style="width:50px;height:15px" ng-model="sub.buhsyfdj" value={{sub.buhsyfdj}}></td>
						<td><input type="text" style="width:50px;height:15px" ng-model="sub.hanszfdj" value={{sub.hanszfdj}} ng-change="fillYfData({{$index}})"></td>
						<td><input type="text" style="width:50px;height:15px" ng-model="sub.bhszfdj" value={{sub.bhszfdj}}></td>
						<td><input type="text" style="width:50px;height:15px" ng-model="sub.shuljsfs" value={{sub.shuljsfs}}></td>
						<td style="text-align:center;">
							<button class="btn btn-danger btn-mini" ng-click="deleteCaigyunfsub(sub);">
								<i class="icon-remove icon-white" title="删除"></i>
							</button>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div style="margin-left:70px;margin-bottom: 50px;">
		<button type="button" class="btn btn-primary" ng-click="saveCaigddb()"><i class="icon-check icon-white"></i> 保存</button>
		<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			orientation: "bottom auto",
			autoclose : true
		});
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			orientation : "bottom auto",
			autoclose : true
		});
		$("#datepicker3").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			orientation : "bottom auto",
			autoclose : true
		});
	});
</script>
