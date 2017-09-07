<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="yunsdwModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyYunsdwTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="yunsdwAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="yunsdw.id">
				<div class="control-group">
					<label class="control-label">名称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.mingc"  id="mingc" name="mingc" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">全称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.quanc" id="quanc" name="quanc" style="float: left;margin-right: 10px;"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">编码</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.bianm" readonly="readonly" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">单位地址</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.danwdz"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮政编码</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.youzbm"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">税号</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.shuih"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">法定代表人</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.faddbr"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">委托代理人</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.weitdlr"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">开户银行</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.kaihyh"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">账号</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.zhangh"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电话</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.dianh"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">传真</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.chuanz"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注</label>
					<div class="controls">
						<input type="text" ng-model="yunsdw.beiz"  />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveYunsdw()">
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
		$("#yunsdwAdd_form").validate({
			rules: {
				mingc:{
					required:true
				},
				quanc:{
					required:true
				}
			}
		});
	});
</script>