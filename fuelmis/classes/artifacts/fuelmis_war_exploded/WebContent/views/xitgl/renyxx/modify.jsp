<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="renyxxModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyRenyxxTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="renyxx.id">
				<div class="control-group">
					<label class="control-label">用户名<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="renyxx.mingc"  id="mingc" name="mingc" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码<font color="red">*</font></label>
					<div class="controls">
						<input type="password" ng-model="renyxx.mim" id="mim" name="mim" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">姓名</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.quanc"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">性别</label>
					<div class="controls">
						<select  ng-model="renyxx.xingb">
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">部门</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.bum"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">职位</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.zhiw"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否可登录</label>
					<div class="controls">
						<select  ng-model="renyxx.zhuangt">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">移动电话</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.yiddh"  id="yiddh" name="yiddh" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">固定电话</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.guddh"  id="guddh" name="guddh" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">传真</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.chuanz"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮政编码</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.youzbm" id="youzbm" name="youzbm" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.email"  id="email" name="email" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联系地址</label>
					<div class="controls">
						<input type="text" ng-model="renyxx.lianxdz"  />
					</div>
				</div>
				<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" class="btn btn-primary" ng-click="saveRenyxx()"><i class="icon-check icon-white"></i> 保存</button>
						<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
					</div>
					<div class="span6"></div>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#renyAdd_form").validate({
			rules: {
				mingc:{
					required:true
				},
				mim:{
					required:true,
					minlength:6
				},
				yiddh:{
					isMobile:true
				},
				guddh:{
					isPhone:true
				},
				youzbm:{
					isZipCode:true
				},
				email:{
					email:true
				}
			}
		});
	});
</script>