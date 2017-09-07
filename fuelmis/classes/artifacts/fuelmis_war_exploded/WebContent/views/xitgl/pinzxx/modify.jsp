<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="pinzxxModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyPinzxxTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="pinzAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="pinzxx.id">
				<div class="control-group">
					<label class="control-label">序号</label>
					<div class="controls">
						<input type="text" ng-model="pinzxx.xuh"  readonly="readonly"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">编码<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="pinzxx.bianm"  id="bianm" name="bianm" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">名称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="pinzxx.mingc"  id="mingc" name="mingc" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">拼音</label>
					<div class="controls">
						<input type="text" ng-model="pinzxx.piny"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">状态</label>
					<div class="controls">
						<select  ng-model="pinzxx.zhuangt">
							<option value="1">使用</option>
							<option value="2">停用</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">品种描述</label>
					<div class="controls">
						<input type="text" ng-model="pinzxx.pinzms"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类别</label>
					<div class="controls">
						<select  ng-model="pinzxx.leib">
							<option value="1">煤</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="savePinzxx()">
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
		$("#pinzAdd_form").validate({
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