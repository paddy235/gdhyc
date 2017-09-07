<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="jizModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyJizTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="jizAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="jiz.id">
				<div class="control-group">
					<label class="control-label">序号</label>
					<div class="controls">
						<input type="text" ng-model="jiz.xuh"  readonly="readonly"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">机组编号<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="jiz.jizbh" id="jizbh" name="jizbh" style="float: left;margin-right: 10px;"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">机组容量(MW)<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="jiz.jizurl" id="jizrl" name="jizrl" style="float: left;margin-right: 10px;"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">投产日期<font color="red">*</font></label>
					<div class="controls">
						<input type="text" id="datepicker" ng-model="jiz.toucrq" id="toucrq" name="toucrq" style="float: left;margin-right: 10px;"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">日均消耗(t)<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="jiz.rijhm"  id="rijhm" name="rijhm" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">煤耗率(g/kwh)<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="jiz.meihl"  id="meihl" name="meihl" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计划电量(亿度)<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="jiz.jihdl"  id="jihdl" name="jihdl" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设计煤种</label>
					<div class="controls">
						<input type="text" ng-model="jiz.shejmz"  />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveJiz()">
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
		$("#datepicker").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true,
		});
		
		$("#jizAdd_form").validate({
			rules: {
				jizbh:{
					required:true
				},
				jizrl:{
					required:true,
					number:true
				},
				toucrq:{
					required:true
				},
				rijhm:{
					required:true,
					number:true
				},
				meihl:{
					required:true,
					number:true
				},
				jihdl:{
					required:true,
					number:true
				}
			}
		});
	});
</script>