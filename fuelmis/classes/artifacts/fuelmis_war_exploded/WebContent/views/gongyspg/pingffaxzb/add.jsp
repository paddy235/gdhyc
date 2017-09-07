<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="pingffaxzbAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{pingffaxzbTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="pingffaxzb.id">
				
				<div class="control-group">
					<label class="control-label">评分标准</label>
					<div class="controls">
						<select ng-model="pingffaxzb.pingffab_id" ng-options="option.value as option.name for option in pingffaList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">指标代码</label>
					<div class="controls">
						<select ng-model="pingffaxzb.zhibdm" ng-options="option.value as option.name for option in kaohzbList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="zhibgs">指标公式</label>
					<div class="controls">
                        <textarea wrap="soft" rows="3" cols="40" id ="zhibgs" ng-model="pingffaxzb.zhibgs" type="text" style="float: left"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">指标分值</label>
					<div class="controls">
						<input id="zhibfz" ng-model="pingffaxzb.zhibfz" type="number" style="float: left">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类型</label>
					<div class="controls">
						<select  ng-model="pingffaxzb.leix">
							<option value="1">日指标</option>
							<option value="2">月指标</option>
							<option value="3">临时指标</option>
						</select>
					</div>
				</div>				
				<div class="control-group">
					<label class="control-label" for="wenzsm">文字说明</label>
					<div class="controls">
                        <textarea rows="3" cols="40" id="wenzsm" ng-model="pingffaxzb.wenzsm" type="text" style="float: left"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gongssm">公式说明</label>
					<div class="controls">
                        <textarea id="gongssm" ng-model="pingffaxzb.gongssm" type="text" style="float: left"></textarea>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">电厂</label>
					<div class="controls">
						<select ng-model="pingffaxzb.diancxxb_id" ng-options="option.value as option.name for option in diancList">
						</select>
					</div>
				</div>
				
				
			</form>
		</div>
	</div>
	<div style="margin-left: 350px;margin-bottom: 50px;">
		<button type="button" class="btn btn-primary" ng-click="savePingffaxzb()">保存</button>
		<button type="button" class="btn" ng-click="cancel()">返回</button>
	</div>
</div>
