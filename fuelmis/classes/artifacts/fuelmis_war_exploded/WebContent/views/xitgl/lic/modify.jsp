<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="licModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyLicTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="licAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="lic.id">
				<div class="control-group">
					<label class="control-label">发站<font color="red">*</font></label>
					<div class="controls">
						<select ng-model="lic.faz_id" id="faz" name="faz" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in chezList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">到站<font color="red">*</font></label>
					<div class="controls">
						<select ng-model="lic.daoz_id" id="daoz" name="daoz" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in chezList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类型<font color="red">*</font></label>
					<div class="controls">
						<select ng-model="lic.liclxb_id" id="leix" name="leix" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in liclxList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">值(km)<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="lic.zhi" id="zhi" name="zhi" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">煤矿单位</label>
					<div class="controls">
						<select ng-model="lic.meikxxb_id" >
							<option value="{{meikxx.value}}" ng-repeat="meikxx in meikxxList"
								class="ng-binding ng-scope">{{meikxx.name}}</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注</label>
					<div class="controls">
						<input type="text" ng-model="lic.beiz"  />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveLic()">
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
		$("#licAdd_form").validate({
			rules: {
				faz:{
					required:true
				},
				daoz:{
					required:true
				},
				leix:{
					required:true
				},
				zhi:{
					required:true,
					number:true
				}
			}
		});
	});
</script>