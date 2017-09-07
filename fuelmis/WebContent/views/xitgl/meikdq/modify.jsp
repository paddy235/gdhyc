<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="meikdqModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyMeikdqTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="meikdqAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="meikdq.id">
				<div class="control-group">
					<label class="control-label">名称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="meikdq.mingc" id="mingc" name="mingc" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">全称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="meikdq.quanc" id="quanc" name="quanc" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">状态</label>
					<div class="controls">
						<select  ng-model="meikdq.zhuangt">
							<option value="1">使用</option>
							<option value="2">停用</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">编码</label>
					<div class="controls">
						<input type="text" ng-model="meikdq.bianm" readonly="readonly" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">省份<font color="red">*</font></label>
					<div class="controls">
						<select ng-model="meikdq.shengfb_id"  id="shengf" name ="shengf" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in shengfList">
								</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">序号</label>
					<div class="controls">
						<input type="text" ng-model="meikdq.xuh" readonly="readonly" />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveMeikdq()">
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
		$("#meikdqAdd_form").validate({
			rules: {
				mingc:{
					required:true
				},
				quanc:{
					required:true
				},
				shengf:{
					required:true
				}
			}
		});
	});
</script>