<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="caigddbShowCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{caigddbTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">订单编号</label>
						<div class="controls">
							<input disabled type="text" ng-model="caigddb.bianh" style="float: left" required />
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">订单类型</label>
						<div class="controls">
							<select  disabled ng-model="caigddb.caigddlx" ng-change="typeselect()" style="float: left">
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
							<select id="selectType" disabled ng-model="caigddb.diancxxb_id" style="float: left"  ng-options="option.value as option.name for option in diancList"></select>
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">供方</label>
						<div class="controls">
							<select id="selectType"  disabled ng-model="caigddb.gongys_id" style="float: left"  ng-options="option.value as option.name for option in gongysList2"></select>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">开始时间</label>
						<div class="controls">
							<input id="datepicker2" disabled ng-model="caigddb.kaissj" type="text" style="float: left">
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">结束时间</label>
						<div class="controls">
							<input id="datepicker3" disabled ng-model="caigddb.jiessj" type="text" style="float: left">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">订单日期</label>
						<div class="controls">
							<input id="datepicker1" disabled ng-model="caigddb.dingdrq" type="text" style="float: left" ng-change="getBianh()">
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">口径类型</label>
						<div class="controls">
							<select id="selectType"  disabled ng-model="caigddb.kouj_id" style="float: left"  ng-options="option.value as option.name for option in jihkjList2"></select>
						</div>
					</div>
				</div>
			</form>
			<div id="table1" style="">
				<table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
					<thead>
					<tr>
						<th style="text-align:center;">序号</th>
						<th style="text-align:center;">煤种简称</th>
						<th style="text-align:center;">供应商</th>
						<th style="text-align:center;">数量(吨)</th>
						<th style="text-align:center;">平仓价<br/>(元/吨)</th>
						<th style="text-align:center;">运杂费(元/吨)</th>
						<th style="text-align:center;">到厂价</th>
						<th style="text-align:center;">运输方式</th>
						<th style="text-align:center;">有效期开始</th>
						<th style="text-align:center;">有效期结束</th>
						<th style="text-align:center;">质量结算依据</th>
						<th style="text-align:center;">数量结算方式</th>
						<th style="text-align:center;">是否加权平均</th>
					</tr>
					</thead>
					<tbody >
					<tr ng-repeat="sub in caigddbsubList track by $index">
						<td>{{$index+1}}</td>
						<td>
							<select style="width: 80px;" disabled ng-model="sub.meiz_id"  ng-options="option.value as option.name for option in kucwlList">
							</select>
						</td>
						<td>
							<select style="width: 100px;" disabled ng-model="sub.gongys_id"  ng-options="option.value as option.name for option in gongysList2">
							</select>
						</td>
						<td><input type="text" disabled style="width:45px;height:15px" ng-model="sub.shul" value={{sub.shul}}></td>
						<td><input type="text" disabled style="width:45px;height:15px" ng-model="sub.pingcj" value={{sub.pingcj}}></td>
						<td><input type="text" disabled style="width:55px;height:15px" ng-model="sub.yunzf" value={{sub.yunzf}}></td>
						<td><input type="text" disabled style="width:45px;height:15px" ng-model="sub.daocj" value={{sub.daocj}}></td>
						<td>
							<select style="width: 80px;" disabled ng-model="sub.yunsfs_id"  ng-options="option.value as option.name for option in yunsfsList">
							</select>
						</td>
						<td><input type="text" class="datepicker0" disabled style="width:80px;height:15px" ng-model="sub.startdate" value={{sub.startdate}}></td>
						<td><input type="text" class="datepicker1" disabled style="width:80px;height:15px" ng-model="sub.enddate" value={{sub.enddate}}></td>
						<td>
							<select style="width: 100px;" disabled  ng-model="sub.zhiljsyj" >
								<option value="厂方化验">厂方化验</option>
								<option value="矿方化验">矿方化验</option>
								<option value="第三方化验">第三方化验</option>
							</select>
						</td>
						<td>
							<select style="width: 90px;" disabled ng-model="sub.shuljsyj" >
								<option value="厂方">厂方</option>
								<option value="矿方">矿方</option>
								<option value="第三方">第三方</option>
							</select>
						</td>
						<td>
							<select style="width: 50px;" disabled ng-model="sub.jiesfs" >
								<option value="是">是</option>
								<option value="否">否</option>
							</select>
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
						<th style="text-align:center;">发站</th>
						<th style="text-align:center;">到站</th>
						<th style="text-align:center;">运输方式</th>
						<th style="text-align:center;">数量</th>
						<th style="text-align:center;">含税运费单价</th>
						<th style="text-align:center;">不含税运费单价</th>
						<th style="text-align:center;">含税杂费单价</th>
						<th style="text-align:center;">不含税杂费单价</th>
						<th style="text-align:center;">数量结算方式</th>
					</tr>
					</thead>
					<tbody >
					<tr ng-repeat="sub in caigyunfsubList">
						<td>{{$index+1}}</td>
						<td>
							<select style="width: 80px;" disabled ng-model="sub.feiyxm_id"  ng-options="option.value as option.name for option in FeiyxmList">
							</select>
						</td>
						<td>
							<select style="width: 140px;" disabled ng-model="sub.faz_id"  ng-options="option.value as option.name for option in chezList">
							</select>
						</td>
						<td>
							<select style="width: 80px;" disabled ng-model="sub.daoz_id"  ng-options="option.value as option.name for option in chezList">
							</select>
						</td>
						<td>
							<select style="width: 140px;" disabled ng-model="sub.yunsfsb_id"  ng-options="option.value as option.name for option in yunsfsList">
							</select>
						</td>
						<td><input type="text" disabled style="width:50px;height:15px" ng-model="sub.shul" value={{sub.shul}}></td>
						<td><input type="text" disabled style="width:50px;height:15px" ng-model="sub.hansyfdj" value={{sub.hansyfdj}}></td>
						<td><input type="text" disabled style="width:50px;height:15px" ng-model="sub.buhsyfdj" value={{sub.buhsyfdj}}></td>
						<td><input type="text" disabled style="width:50px;height:15px" ng-model="sub.hanszfdj" value={{sub.hanszfdj}}></td>
						<td><input type="text" disabled style="width:50px;height:15px" ng-model="sub.bhszfdj" value={{sub.bhszfdj}}></td>
						<td><input type="text" disabled style="width:50px;height:15px" ng-model="sub.shuljsfs" value={{sub.shuljsfs}}></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div style="margin-left:70px;margin-bottom: 50px;">
		<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
	</div>
</div>
