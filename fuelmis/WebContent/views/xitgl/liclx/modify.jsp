<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="liclxModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyLiclxTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="liclxAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="liclx.id">
				<div class="control-group">
					<label class="control-label">序号</label>
					<div class="controls">
						<input type="text" ng-model="liclx.xuh" readonly="readonly" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">名称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="liclx.mingc"  id="mingc" name="mingc" style="float: left;margin-right: 10px;"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">拼音</label>
					<div class="controls">
						<input type="text" ng-model="liclx.piny"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注</label>
					<div class="controls">
						<input type="text" ng-model="liclx.beiz"  />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveLiclx()">
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
		$("#liclxAdd_form").validate({
			rules: {
				mingc:{
					required:true
				}
			}
		});
	});
</script>