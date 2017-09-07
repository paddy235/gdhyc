<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="diancxxModifyCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyDiancxxTitle}}</div>
	</div>
	<div style="margin-left: 10px;margin-top: 10px;">
		<button type="button" class="btn btn-primary" ng-click="saveDiancxx()"><i class="icon-check icon-white"></i> 保存</button>
		<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
	</div>
	<div id="tabs">
		<form id="diancxxAdd_form" class="form-horizontal">
			<input type="hidden" ng-model="diancxx.id">
			<ul>
				<li><a href="#tabs-a">基本信息</a></li>
				<li><a href="#tabs-b">资源信息</a></li>
				<li><a href="#tabs-c">储备信息</a></li>
				<li><a href="#tabs-d">合同信息</a></li>
			</ul>
			<div id="tabs-a">
				<div class="block-content collapse in">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">电厂编码<font color="red">*</font></label>
							<div class="controls">
								<input type="text" ng-model="diancxx.bianm" id="bianm" name="bianm" style="float: left;margin-right: 10px;"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">简称<font color="red">*</font></label>
							<div class="controls">
								<input type="text" ng-model="diancxx.mingc"
									id="mingc" name="mingc" style="float: left;margin-right: 10px;"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">全称<font color="red">*</font></label>
							<div class="controls">
								<input type="text" ng-model="diancxx.quanc"  id="quanc" name="quanc" style="float: left;margin-right: 10px;"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">拼音</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.piny"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">省份<font color="red">*</font></label>
							<div class="controls">
								<select ng-model="diancxx.shengfb_id"  id="shengf" name ="shengf" style="float: left;margin-right: 10px;"
									ng-options="option.value as option.name for option in shengfList">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上级单位</label>
							<div class="controls">
								<select ng-model="diancxx.fuid" 
									ng-options="option.value as option.name for option in diancList">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">到站</label>
							<div class="controls">
								<select ng-model="diancxx.daoz" 
									ng-options="option.value as option.name for option in chezList">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">总机</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.zongj"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">邮编</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.youzbm"
									 />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">地址</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.diz"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">级别</label>
							<div class="controls">
								<select  ng-model="diancxx.jib"
									ng-options="option.value as option.name for option in diancjbList">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">燃料负责人</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.ranlfzr"
									 />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系地址</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.lianxdz"
									 />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电厂类别<font color="red">*</font></label>
							<div class="controls">
								<select ng-model="diancxx.dianclbb_id"  id="leib" name="leib" style="float: left;margin-right: 10px;"
									ng-options="option.value as option.name for option in dianclbList">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">排序号</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.xuh"  readonly="readonly"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabs-b">
				<div class="block-content collapse in">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">接卸方式</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.jiexfs"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">接卸线(条)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.jiexx"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">接卸能力(车)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.jiexnl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">采样方式</label>
							<div class="controls">
								<select  ng-model="diancxx.caiyfs"
									ng-options="option.value as option.name for option in caiyfsList">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">计量方式</label>
							<div class="controls">
								<select  ng-model="diancxx.jilfs"
									ng-options="option.value as option.name for option in jilfsList">
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabs-c">
				<div class="block-content collapse in">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">装机容量(MW)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.zhuangjrl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">最大库存(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.zuidkc"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">正常储备(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.zhengccb"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">限负荷库存(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.xianfhkc"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">日均耗煤(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.rijhm"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">冬储煤指标(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.dongcmzb"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">经济储煤上限(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.jingjcmsx"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">经济储煤下限(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.jingjcmxx"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">警戒存煤(吨)</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.jingjcml"  />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabs-d">
				<div class="block-content collapse in">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">法定代表人</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.faddbr"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">委托代理人</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.weitdlr"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电话</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.dianh"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开户银行</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.kaihyh"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">账号</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.zhangh"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">税号</label>
							<div class="controls">
								<input type="text" ng-model="diancxx.shuih"  />
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$('#tabs').tabs();
		
		$("#diancxxAdd_form").validate({
			rules: {
				bianm:{
					required:true
				},
				mingc:{
					required:true
				},
				quanc:{
					required:true
				},
				shengf:{
					required:true
				},
				leib:{
					required:true
				}
			}
		});
	});
</script>