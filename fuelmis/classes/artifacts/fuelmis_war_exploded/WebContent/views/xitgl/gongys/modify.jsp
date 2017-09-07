<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="tab-pane" ng-controller="gongysModifyCtrl">
<!-- 	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{modifyGongysTitle}}</div>
	</div> -->
	<div style="margin-left: 10px;margin-top: 10px;">
		<button type="button" class="btn btn-primary" ng-click="saveGongys()">
			<i class="icon-check icon-white"></i> 保存
		</button>
		<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()">
			<i class="icon-repeat"></i> 返回
		</button>
	</div>
	<div id="tabs">
		<form id="gongysAdd_form1" class="form-horizontal">
			<input type="hidden" ng-model="gongys.id">
			<ul>
				<li><a href="#tabs-a">基本信息</a></li>
				<li><a href="#tabs-b">其他信息</a></li>
				<!-- <li><a href="#tabs-c">其他信息</a></li>  -->
			</ul>
			<div id="tabs-a">
				<div class="block-content collapse in">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">序号</label>
							<div class="controls">
								<input type="text" ng-model="gongys.xuh" readonly="readonly"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">公司全称<font color="red">*</font></label>
							<div class="controls">
								<input type="text" ng-model="gongys.quanc"   id="quanc" name="quanc" style="float: left;margin-right: 10px;"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">公司简称<font color="red">*</font></label>
							<div class="controls">
								<input type="text" ng-model="gongys.mingc"  id="mingc" name="mingc" style="float: left;margin-right: 10px;" 
									ng-change="getBianm()"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">公司编码<font color="red">*</font></label>
							<div class="controls">
								<input type="text" ng-model="gongys.bianm"  id="bianm" name="bianm" style="float: left;margin-right: 10px;"/>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">公司地址</label>
							<div class="controls">
								<input type="text" ng-model="gongys.danwdz"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">省份<font color="red">*</font></label>
							<div class="controls">
								<select ng-model="gongys.shengfb_id"  id="shengf" name="shengf" style="float: left;margin-right: 10px;"
									ng-options="option.value as option.name for option in shengfList">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">邮编</label>
							<div class="controls">
								<input type="text" ng-model="gongys.youzbm"  id="youzbm" name="youzbm" style="float: left;margin-right: 10px;"
									 />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">法定代表人</label>
							<div class="controls">
								<input type="text" ng-model="gongys.faddbr"  />
							</div>
						</div>
					
						 <div class="control-group">
							<label class="control-label">注册资本金</label>
							<div class="controls">
								<input type="text" ng-model="gongys.zhuczbj"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开户行号</label>
							<div class="controls">
								<input type="text" ng-model="gongys.zhangh"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开户行</label>
							<div class="controls">
								<input type="text" ng-model="gongys.kaihyh"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">公司组织机构代码</label>
							<div class="controls">
								<input type="text" ng-model="gongys.zuzjgdm"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">公司税务登记证</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shuiwdjz"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系人</label>
							<div class="controls">
								<input type="text" ng-model="gongys.lianxr"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电话</label>
							<div class="controls">
								<input type="text" ng-model="gongys.dianh"   id="dianh" name="dianh" style="float: left;margin-right: 10px;"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">手机</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shouj"   id="shouj" name="shouj" style="float: left;margin-right: 10px;"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">传真</label>
							<div class="controls">
								<input type="text" ng-model="gongys.chuanz"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电子邮件</label>
							<div class="controls">
								<input type="text" ng-model="gongys.email"   id="email" name="email" style="float: left;margin-right: 10px;"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabs-b">
				<div class="block-content collapse in">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">公司主页</label>
							<div class="controls">
								<input type="text" ng-model="gongys.gongszy"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">公司性质</label>
							<div class="controls">
								<input type="text" ng-model="gongys.gongsxz"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">付款方式</label>
							<div class="controls">
								<input type="text" ng-model="gongys.fukfs"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">税率</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shuil"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">运输方式</label>
							<div class="controls">
								<input type="text" ng-model="gongys.yunsfs"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">运输税率</label>
							<div class="controls">
								<input type="text" ng-model="gongys.yunssl"  />
							</div>
						</div>
						<!-- 
						<div class="control-group">
							<label class="control-label">储备能力(万吨)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.chubnl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">供应能力(万吨)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.gongynl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">重点合同(万吨)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.zhongdht"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开采能力(万吨)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.kaicnl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">流向</label>
							<div class="controls">
								<input type="text" ng-model="gongys.liux"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">运输能力(万吨)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.yunsnl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开采年限(年)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.kaicnx"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">合作年限(年)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.heznx"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">生成能力(万吨)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shengcnl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">市场采购量(万吨)</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shiccgl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">融洽关系</label>
							<div class="controls">
								<select  ng-model="gongys.rongqgx">
									<option value="1">优</option>
									<option value="0">良</option>
									<option value="-1">差</option>
								</select>
							</div>
						</div> -->
					</div>
				</div>
			</div>
			<!-- div id="tabs-c">
				<div class="block-content collapse in">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">信誉</label>
							<div class="controls">
								<select  ng-model="gongys.xiny">
									<option value="1">优</option>
									<option value="0">良</option>
									<option value="-1">差</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">是否上市</label>
							<div class="controls">
								<select  ng-model="gongys.shifss">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">收煤比例</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shoumbl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上市地址</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shangsdz"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">其他比例</label>
							<div class="controls">
								<input type="text" ng-model="gongys.qitbl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">可供应煤种</label>
							<div class="controls">
								<input type="text" ng-model="gongys.kegymz"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">可供应煤种指标</label>
							<div class="controls">
								<input type="text" ng-model="gongys.kegymzzb"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">自产比例</label>
							<div class="controls">
								<input type="text" ng-model="gongys.zicbl"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">税号</label>
							<div class="controls">
								<input type="text" ng-model="gongys.shuih"  />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备注</label>
							<div class="controls">
								<input type="text" ng-model="gongys.beiz"  />
							</div>
						</div>
					</div>
				</div>
			</div> -->
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$('#tabs').tabs();
		
		$("#gongysAdd_form1").validate({
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
				shengf:{
					required:true
				},
				meikxx:{
					required:true
				},
				dianh:{
					isPhone:true
				},
				shouj:{
					isMobile:true
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