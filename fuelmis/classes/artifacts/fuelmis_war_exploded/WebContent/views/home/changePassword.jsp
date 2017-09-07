<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
#renyxx tbody tr>td:nth-child(odd) {
	width: 100px !important;
	text-align: right;
}
</style>
<div class="block" ng-controller="changePasswordCtrl">
	<div class="navbar navbar-inner block-header" >
		<div class="muted pull-left">修改个人信息</div>
	</div>
	<div class="block-content collapse in">
		<form id="renyAdd_form" class="form-horizontal" name="myForm" ng-submit="saveRenyxx(myForm.$valid)" novalidate>
			<div class="row-fluid">
				<div class="span1"></div>
				<div class="span9" style="text-align: center;">	
				<div class="span12"
					style="text-align: center; padding-bottom: 10px;">
					<button type="submit" class="btn btn-primary" ng-disabled="myForm.$invalid"
						>
						<i class="icon-check icon-white"></i> 保存
					</button>
				</div>
				
					<table id="renyxx"
						class="table table-striped table-bordered table-hover"
						style="width: 100%; vertical-align: middle;">
						<tbody>
							<tr>
								<td>用户名<font color="red">*</font></td>
								<td><input type="text" required  ng-model="renyxx.mingc" id="mingc"
									name="mingc" /></td>
								<td>密码<font color="red">*</font></td>
								<td><input type="password" required  ng-model="renyxx.mim" id="mim" ng-minlength="6" 
									name="mim" />
									<p ng-show="myForm.mim.$error.minlength" class="help-block text-danger">密码必须大于6位.</p>
									</td>
								<td>姓名</td>
								<td><input type="text" ng-model="renyxx.quanc" /></td>
							<tr>
								<td>性别</td>
								<td><select ng-model="renyxx.xingb">
										<option value="1">男</option>
										<option value="2">女</option>
								</select></td>
								<td>部门</td>
								<td><input type="text" ng-model="renyxx.bum" /></td>
								<td>职位</td>
								<td><input type="text" ng-model="renyxx.zhiw" /></td>
							</tr>
							<tr>
								<td>是否可登录</td>
								<td><select ng-model="renyxx.zhuangt">
										<option value="1">是</option>
										<option value="0">否</option>
								</select></td>
								<td>移动电话</td>
								<td><input type="text" ng-model="renyxx.yiddh" id="yiddh"
									name="yiddh" /></td>
								<td>固定电话</td>
								<td><input type="text" ng-model="renyxx.guddh" id="guddh"
									name="guddh" /></td>
							</tr>
							<tr>
								<td>传真</td>
								<td><input type="text" ng-model="renyxx.chuanz" /></td>
								<td>邮政编码</td>
								<td><input type="text" ng-model="renyxx.youzbm" id="youzbm"
									name="youzbm" /></td>
								<td>Email</td>
								<td><input type="text" ng-model="renyxx.email" id="email"
									name="email" /></td>
							</tr>
							<tr>
								<td>联系地址</td>
								<td><input type="text" ng-model="renyxx.lianxdz" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="span2"></div>
			</div>
		</form>
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