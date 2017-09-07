<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="priceComponetAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{priceComponetTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="priceComponet.id">
				
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">名称</label>
						<div class="controls">
							<input type="text" ng-model="priceComponet.name" style="float: left" required/>
						</div>
					</div>
					
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">类名</label>
						<div class="controls">
							<input type="text" ng-model="priceComponet.classname" style="float: left" required/>
						</div>
					</div>
				</div>
				
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">URL</label>
						<div class="controls">
							<input type="text" ng-model="priceComponet.url" style="float: left" required/>
						</div>
					</div>
					
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">说明</label>
						<div class="controls">
							<input type="text" ng-model="priceComponet.remarks" style="float: left" required/>
						</div>
					</div>
				</div>
				
				
				<div class="row-fluid">									
					<div class="span6" style="margin-bottom:15px;">
					<label class="control-label" style="margin-right:20px;">模板状态</label>
						<select  ng-model="priceComponet.status" style="float: left">
							<option value="1">启用</option>
							<option value="0">未启用</option>
						</select>
					</div>			
				</div>
				
			</form>
		</div>
	</div>
	
	
		<div style="text-align: center;">
			<button type="button" class="btn btn-primary" ng-click="savePriceComponet()"><i class="icon-check icon-white"></i> 保存</button>
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
