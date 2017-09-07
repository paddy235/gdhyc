<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="pingffaAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{title}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="pingffa.id">
				<div class="control-group">
					<label class="control-label">方案名称</label>
					<div class="controls">
						<input id="zhibmc" ng-model="pingffa.mingc" type="text" style="float: left">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">供应商分类</label>
					<div class="controls">
						<select ng-model="pingffa.jihkj_id" ng-options="option.value as option.name for option in jihkjList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">起始时间</label>
					<div class="controls">
						<input id="datepicker1" ng-model="pingffa.qisrq" type="text" style="float: left">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">截至时间</label>
					<div class="controls">
						<input id="datepicker2" ng-model="pingffa.jiezrq" type="text" style="float: left">
					</div>
				</div>				
				<div class="control-group">
					<label class="control-label">状态</label>
					<div class="controls">
						<select  ng-model="pingffa.zhuangt">
							<option value="1">启用</option>
							<option value="0">禁用</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">上传状态</label>
					<div class="controls">
						<select ng-model="pingffa.shangczt">
							<option value="1">已上传</option>
							<option value="0">未上传</option>
						</select>					
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电厂</label>
					<div class="controls">
						<select ng-model="pingffa.diancxxb_id" ng-options="option.value as option.name for option in diancList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">附件</label>
					<div class="controls">
						<input id="zhibmc" ng-model="pingffa.fujmc" type="text" style="float: left">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div style="margin-left: 350px;margin-bottom: 50px;">
		<button type="button" class="btn btn-primary" ng-click="savePingffa()">保存</button>
		<button type="button" class="btn" ng-click="cancel()">返回</button>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){    
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});	
});
</script>