<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="hetmbEditCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{hetmbTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="hetmb.id">
				<div class="control-group">
					<label class="control-label">合同模板编号</label>
					<div class="controls">
							<input type="text" ng-model="fujxx.daim" style="float: left" required/>
					</div>
				</div>				
				<div class="control-group">
					<label class="control-label">有效期开始</label>
					<div class="controls">
						<input id="datepicker2" ng-model="fujxx.youxksrq" type="text" style="float: left">
					</div>
				</div>				
				<div class="control-group">
					<label class="control-label">有效期结束</label>
					<div class="controls">
						<input id="datepicker3" ng-model="fujxx.youxjsrq" type="text" style="float: left">
					</div>
				</div>						
			</form>
		</div>
	</div>
	
	
		<div style="margin-left: 350px;margin-bottom: 50px;">
			<button type="button" class="btn btn-primary" ng-click="saveHetmb()"><i class="icon-check icon-white"></i> 保存</button>
			<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function(){    
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	$("#datepicker3").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});	
	
	$(function() {
		$('#tabs').tabs();
	});	
	
});
</script>
