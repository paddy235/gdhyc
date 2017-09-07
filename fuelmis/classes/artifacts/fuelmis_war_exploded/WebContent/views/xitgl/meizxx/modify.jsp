<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="meizxxModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyMeizxxTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="meizAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="meizxx.id">
				<div class="control-group">
					<label class="control-label">序号</label>
					<div class="controls">
						<input type="text" ng-model="meizxx.xuh" readonly="readonly" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">名称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="meizxx.mingc"  id="mingc" name="mingc" style="float: left;margin-right: 10px;" 
							ng-change="getBianm()"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">编码<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="meizxx.bianm"  id="bianm" name="bianm" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveMeizxx()">
							<i class="icon-check icon-white"></i> 保存
						</button>
						<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()">
							<i class="icon-repeat"></i> 返回
						</button>
					</div>
					<div class="span6"></div>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function() {		
		$("#meizAdd_form").validate({
			rules: {
				mingc:{
					required:true
				},
				bianm:{
					required:true
				}
			}
		});
	});
</script>