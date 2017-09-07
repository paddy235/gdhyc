<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="meikxxModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyMeikxxTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="meikxxAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="meikxx.id">
				<div class="control-group">
					<label class="control-label">序号</label>
					<div class="controls">
						<input type="text" ng-model="meikxx.xuh" readonly="readonly"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">煤矿地区</label>
					<div class="controls">
						<select ng-model="meikxx.meikdq_id">
							<option value="{{meikdq.value}}" ng-repeat="meikdq in meikdqList"
								class="ng-binding ng-scope">{{meikdq.name}}</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">编码<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="meikxx.bianm"  id="bianm" name="bianm" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">名称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="meikxx.mingc"  id="mingc" name="mingc" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">全称<font color="red">*</font></label>
					<div class="controls">
						<input type="text" ng-model="meikxx.quanc"  id="quanc" name="quanc" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">拼音</label>
					<div class="controls">
						<input type="text" ng-model="meikxx.piny"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">省份</label>
					<div class="controls">
						<select ng-model="meikxx.shengfb_id" >
							<option value="{{shengf.value}}" ng-repeat="shengf in shengfList"
								class="ng-binding ng-scope">{{shengf.name}}</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类别<font color="red">*</font></label>
					<div class="controls">
						<select  ng-model="meikxx.leib" id="leib" name="leib" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in leibList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类型<font color="red">*</font></label>
					<div class="controls">
						<select  ng-model="meikxx.leix" id="leix" name="leix" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in leixList">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计划口径<font color="red">*</font></label>
					<div class="controls">
						<select ng-model="meikxx.jihkjb_id" id="jihkj" name="jihkj" style="float: left;margin-right: 10px;"
							ng-options="option.value as option.name for option in jihkjList2">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联系人</label>
					<div class="controls">
						<input type="text" ng-model="meikxx.lianxr"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电话</label>
					<div class="controls">
						<input type="text" ng-model="meikxx.guddh"  id="guddh" name="guddh" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">手机</label>
					<div class="controls">
						<input type="text" ng-model="meikxx.yiddh" id="yiddh" name="yiddh" style="float: left;margin-right: 10px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">传真</label>
					<div class="controls">
						<input type="text" ng-model="meikxx.chuanz"  />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电子邮件</label>
					<div class="controls">
						<input type="text" ng-model="meikxx.email"  id="email" name="email" style="float: left;margin-right: 10px;"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">使用状态<font color="red">*</font></label>
					<div class="controls">
						<select  ng-model="meikxx.shiyzt" id="shiyzt" name="shiyzt" style="float: left;margin-right: 10px;"> 
							<option value="1">使用</option>
							<option value="2">停用</option>
						</select>
					</div>
				</div>
				<!--  多选下拉框存在兼容性问题  暂时屏蔽
				<div class="control-group">
					<label class="control-label">设置车站</label>
					<div class="controls" >
						<select ng-model="meikxx.gongysList" id="selChez" data-size="5"
							class="span6 selectpicker show-tick form-control" multiple
							data-live-search="false">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">关联供应商</label>
					<div class="controls">
						<select ng-model="meikxx.gongysList" id="selGongys" data-size="5"
							class="span6 selectpicker show-tick form-control" multiple
							data-live-search="false">
						</select>
					</div>
				</div>
				-->
			<div class="control-group">
				<div class="span2"></div>
				<div class="span4">
					<button type="button" class="btn btn-primary" ng-click="saveMeikxx()">
						<i class="icon-check icon-white"></i> 保存
					</button>
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
	/*		$('#selChez').selectpicker({
			noneSelectedText : ''
		});
		
		$('#selGongys').selectpicker({
			noneSelectedText : ''
		});
		
		$.post("common/getComboChez",function(data){
			 var arry = eval(data);
			 for(var i=0;i<arry.length;i++){
				 $('#selChez').append('<option value="'+arry[i].value+'">'+arry[i].name+'</optoin>');
				 //每加载一个option需刷新，否则无法显示
				 $('#selChez').selectpicker('refresh'); 
			 }
		});
		
		$.post("common/getComboGongys",function(data){
			 var arry = eval(data);
			 for(var i=0;i<arry.length;i++){
				 if(arry[i].value==-1){
					 continue;
				 }
				 $('#selGongys').append('<option value="'+arry[i].value+'">'+arry[i].name+'</optoin>');
				 //每加载一个option需刷新，否则无法显示
				 $('#selGongys').selectpicker('refresh');
			 }
		});*/
		
		$("#meikxxAdd_form").validate({
			rules: {
				mingc:{
					required:true
				},
				quanc:{
					required:true
				},
				bianm:{
					required:true
				},
				leib:{
					required:true
				},
				leix:{
					required:true
				},
				jihkj:{
					required:true
				},
				guddh:{
					isPhone:true
				},
				yiddh:{
					isMobile:true
				},
				email:{
					email:true
				}
			}
		});
	});
</script>