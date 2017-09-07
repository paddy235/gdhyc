<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="kaohzbAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{title}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="kaohzb.id">
				<div class="control-group">
					<label class="control-label">指标名称</label>
					<div class="controls">
						<input id="zhibmc" ng-model="kaohzb.zhibmc" type="text" style="float: left">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">指标代码</label>
					<div class="controls">
						<input id="zhibdm" ng-model="kaohzb.zhibdm" type="text" style="float: left">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">指标单位</label>
					<div class="controls">
						<input type="text" ng-model="kaohzb.zhibdw" type="text" style="float: left" required/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">状态</label>
					<div class="controls">
						<select  ng-model="kaohzb.zhuangt">
							<option value="1">启用</option>
							<option value="0">禁用</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类别</label>
					<div class="controls">
						<select ng-model="kaohzb.leib">
							<option value="1">数量指标</option>
							<option value="2">质量指标</option>
							<option value="3">其他指标</option>
						</select>					
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电厂</label>
					<div class="controls">
						<select ng-model="kaohzb.diancxxb_id" ng-options="option.value as option.name for option in diancList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注</label>
					<div class="controls">
						<input id="zhibmc" ng-model="kaohzb.beiz" type="text" style="float: left">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div style="margin-left: 350px;margin-bottom: 50px;">
		<button type="button" class="btn btn-primary" ng-click="saveKaohzb()">保存</button>
		<button type="button" class="btn" ng-click="cancel()">返回</button>
	</div>
</div>
