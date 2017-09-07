
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="jiesupdateCtrl">

	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">修改发票信息</div>
	</div>

	<div class="block-content collapse in">
		<div class="span11">
			<!-- BEGIN FORM-->
			<form action="#" id="form_sample_1" class="form-horizontal">
				<fieldset>
					<table>
						<tr>
							<td>
								<div class="control-group">
									<label class="control-label ">煤矿简称</label>
									<div class="controls ">
										<select id="meik" ng-model="fapbean.MEIKXXB_ID"
											ng-change="chaxjsdh()"
											ng-options="option.value as option.name for option in meikxxList">
										</select>
									</div>
								</div>
							</td>
							<td>
								<div class="control-group">
									<label class="control-label ">性质</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.XINGZ" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="control-group">
									<label class="control-label ">结算单号</label>
									<div class="controls ">
										<select id="jies" float: left;" ng-model="fapbean.JIESB_ID"
											ng-change="selectdianc()"
											ng-options="option.value as option.name for option in jiesdhList">
										</select>
									</div>
								</div>
							</td>
							<td>
								<div class="control-group">
									<label class="control-label ">发票起始编号</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.QISBH" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="control-group">
									<label class="control-label ">发票终止编号</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.ZHONGZBH" />
									</div>
								</div>
							</td>
							<td>
								<div class="control-group">
									<label class="control-label ">发票总金额</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.ZONGJE" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="control-group">
									<label class="control-label ">结算煤款</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.JIESMK" />
									</div>
								</div>
							</td>
							<td>
								<div class="control-group">
									<label class="control-label ">发票日期</label>
									<div class="controls ">
										<input type="text" id='datepicker' ng-model="fapbean.FAPRQ" />

									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="control-group">
									<label class="control-label ">支付条款</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.ZHIFTK" />
									</div>
								</div>
							</td>
							<td>
								<div class="control-group">
									<label class="control-label ">发票数</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.FAPS" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="control-group">
									<label class="control-label ">录入人</label>
									<div class="controls ">
										<input type="text" ng-model="fapbean.LURR" />
									</div>
								</div>
							</td>
							<td></td>
						</tr>
					</table>

					<div class="control-group">
						<label class="control-label ">结算描述</label>
						<div class="controls ">
							<TEXTAREA rows="10" cols="30" style="width: 90%;" ng-model="fapbean.JIESMS">

							</TEXTAREA>
						</div>
					</div>




					<div class="control-group">
						<div class="span2"></div>
						<div class="span4">
							<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveFap();">
								<i class="icon-check icon-white"></i> 保存
							</button>
							<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
						</div>
						<div class="span6"></div>
					</div>
				</fieldset>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>