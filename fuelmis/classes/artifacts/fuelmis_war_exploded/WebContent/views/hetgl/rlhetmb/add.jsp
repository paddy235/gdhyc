<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="rlhetmbAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{rlhetmbTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="hetmb.id">
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">合同模板编号</label>
						<div class="controls">
								<input type="text" ng-model="hetmb.mubbh" style="float: left" required/>
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">合同模板名称</label>
						<div class="controls">
								<input type="text" ng-model="hetmb.mingc" style="float: left" required/>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">有效期开始</label>
						<div class="controls">
							<input id="datepicker2" ng-model="hetmb.youxkssj" type="text" style="float: left">
						</div>
					</div>				
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">有效期结束</label>
						<div class="controls">
							<input id="datepicker3" ng-model="hetmb.youxjssj" type="text" style="float: left">
						</div>
					</div>				
				</div>

				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">模板路径</label>
						<div class="controls">
							<input id="luj" ng-model="hetmb.luj" type="text" style="float: left">
						</div>
					</div>
									
					<div class="span6" style="margin-bottom:15px;">
					<label class="control-label" style="margin-right:20px;">模板状态</label>
						<select  ng-model="hetmb.zhuangt" style="float: left">
							<option value="1">启用</option>
							<option value="0">未启用</option>
						</select>
					</div>			
				</div>
				
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">模板文件路径</label>
						<div class="controls">
							<input id="luj" ng-model="hetmb.mublj" type="text" style="float: left">
						</div>
					</div>									
				</div>
						
			</form>
		</div>
	</div>	
		<div style="text-align: center;">
			<button type="button" class="btn btn-primary" ng-click="saveRlhetmb()"><i class="icon-check icon-white"></i> 保存</button>
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
