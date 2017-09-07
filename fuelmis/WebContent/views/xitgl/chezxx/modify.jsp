<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="chezxxModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyChezxxTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="chezAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="chezxx.id">
				<div class="control-group">
					<label class="control-label">序号</label>
					<div class="controls">
						<input type="text" ng-model="chezxx.xuh" readonly="readonly"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">编码</label>
					<div class="controls">
						<input type="text" ng-model="chezxx.bianm" readonly="readonly" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">名称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="chezxx.mingc" id="mingc" name="mingc" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">全称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" id="quanc" name="quanc" ng-model="chezxx.quanc"  style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">拼音</label>
					<div class="controls">
						<input type="text" ng-model="chezxx.piny"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">路局<font color="red">*</font></label>
					<div class="controls">
						<select ng-model="chezxx.lujxxb_id" id="luju" name="luju" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in lujList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类别</label>
					<div class="controls">
						<select  ng-model="chezxx.leib"
							ng-options="option.value as option.name for option in chezlbList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注</label>
					<div class="controls">
						<input type="text" ng-model="chezxx.beiz"  />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveChezxx()">
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
	$(document).ready(function() {
		$("#chezAdd_form").validate({
			rules: {
				mingc:{
					required:true
				},
				quanc:{
					required:true
				},
				luju:{
					required:true
				}
			}
		});
	});
</script>