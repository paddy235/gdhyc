<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>2016年工程煤供需合同</title>
		<style>
			*{margin:0;padding:0;}
			#main-container{width:1000px;padding:20px 40px;margin:0 auto;font-size:18px;font-family:"SimSun";color:#000;background-color:#fff;}
			.data-table th,.data-table td{border:1px solid #444;padding:3px 8px;}
			.contbl tr td:nth-child(1){text-align:right;}
			#main-container input{box-shadow:none;border-radius:0;}
		</style>
	</head>
	<body>
		<div id="main-container" ng-controller="hetbeanAdd1Ctrl">
		<div id="picture" style="background-color: white;">
			<div style="text-align:center;font-size:26px;font-weight:bold;margin:20px 0;">
				<img src="/fuelmis/images/demo2.png">
			</div>
			<div id="first-layer" style="text-align:center;">
				<div style="text-align:center;font-size:26px;margin:20px 0;">国电新疆红雁池发电有限公司</div>
				<div style="text-align:center;font-size:22px;margin:20px 0;">工程煤供需合同</div>
				<table style="width:100%;line-height:40px;margin-top:50px;margin-bottom:50px;">
					<tr>
						<td width="15%">甲方（买方）：</td>
						<td width="25%">
							<select id="selectType" ng_model="hetbean.xuf" ng-change="changeDiancxx()" style="float: left">
								<option value="{{dianc.value}}"  ng-repeat="dianc in diancList">{{dianc.name}}</option>
				  			</select>
						</td>
						<td width="25%"></td>
						<td width="10%">合同编号：</td>
						<td width="25%">
							<input type="text" ng-model="hetbean.hetbh" style="font-size:18px;width:100%;border:none;border-bottom:1px solid #444;">
						</td>
					</tr>
					<tr>
						<td>乙方（卖方）：</td>
						<td>
							<select id="selectType" ng-model="hetbean.gongf" ng-change="changeGys()" style="float: left">
								<option value="{{gys.value}}"  ng-repeat="gys in gongysList2">{{gys.name}}</option>
							</select>
						</td>
						<td></td>
						<td>签订地点：</td>
						<td>
							<input type="text" ng-model="hetbean.qianddd" style="font-size:18px;width:100%;border:none;border-bottom:1px solid #444;">
						</td>
					</tr>
					<tr>
						<td>签订时间：</td>
						<td>
							<input id="datepicker1" ng-model="hetbean.qiandrq" type="text" style="float: left">
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			
			<div style="text-align:center;font-size:26px;font-weight:bold;margin:20px 0;">
				2016年煤炭供需合同
			</div>
			<div style="text-align:left;font-size:18px;font-weight:normal;margin:10px 0;">
				经甲、乙双方共同协商，就2016年煤炭供需事宜达成一致意见，并签订本合同。
			</div>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				1．煤炭品种：<select style="font-size:18px;width:100px;border:none;" ng-model="hetbean.pinz_id"  ng-options="option.value as option.name for option in kucwlList">
						 </select>
			</div>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				2．数量：
			</div>
			<p style="text-indent:2em;margin-top:10px;">
			2．1 2016年煤炭供需数量:按月完成甲方给乙方的计划供应数量，如果由于乙方原因不能完成计划量的百分之七十按不合格供应商处理，按实际供应数量结算。</p>
			<p style="text-indent:2em;margin-top:10px;">
			2．2 月度供需数量由甲方根据机组负荷情况上报乙方，尽量做到均衡拉运。如遇特殊情况调整月度供应量时应由甲、乙双方共同协商确定。</p>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				3．质量标准与煤质调价：
			</div>
			<div id="second-layer">
				<table class="data-table" style="width:100%;line-height:32px;border-collapse:collapse;">
					<tr>
						<th>煤质指标</th>
						<th>质量范围</th>
						<th>煤质调价</th>
					</tr>
					<tr>
						<td>全硫(St,ar)</td>
						<td>≤1.20％</td>
						<td>收到基全硫(St,ar)在1.2％－1.7％之间，以1.2％为基数，每超出0.01％，价格降低0.2元/吨；收到基全硫(St,ar)大于1.7％，以1.7％为基数，每超出0.01％，价格降低0.4元/吨。</td>
					</tr>
					<tr>
						<td>发热量（Qnet,ar）</td>
						<td>3500kcal/kg</td>
						<td>热值大于3500kcal/kg，每增加1kcal/kg，价格增加0.014元/吨，热值小于3500kcal/kg，每降低1kcal/kg，价格降低0.014元/吨。</td>
					</tr>
					<tr>
						<td>全水分</td>
						<td><28%</td>
						<td></td>
					</tr>
					<tr>
						<td>粒度</td>
						<td>0～100mm</td>
						<td></td>
					</tr>
					<tr>
						<td>挥发份</td>
						<td>﹥19%</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">备注：除表中已明确的煤质调价指标外，其它指标均不作为调价及考核合格与否的依据。如确实发生较大偏差，双方共同协商解决。</td>
					</tr>
				</table>
			</div>
			<div style="text-align:left;font-size:18px;margin:10px 0;">
				<span style="font-weight:bold;">4．交货地点及方式:</span>汽车运输，到厂交货。
			</div>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				5．价格
			</div>
			<p style="text-indent:2em;margin-top:10px;">
			5．1 合同价格：煤价（一票到厂含税价）：
			<input type="text" style="font-size:18px;width:40px;border:none;text-align:right;border-bottom:1px solid #444;" value="100" ng-change="getJiagDx()" ng-model="hetbean.jiag">元/吨,
			大写：<input type="text" style="font-size:18px;width:150px;text-align:right;border:none;border-bottom:1px solid #444;" ng-model="hetbean.jiag_dx" >每吨.</p>
			<p style="text-indent:2em;margin-top:10px;">
			5．2 市场行情发生变化或国家进行重大政策性调整时，甲、乙双方均有权提出调整合同价格，并经双方共同协商后重新签订合同，同时终止本合同。</p>
			<p style="text-indent:2em;margin-top:10px;">
			5．3 结算价格在合同价格的基础上，根据煤炭交接质量检验结果以质计价。</p>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				6．数量、质量的确认
			</div>
			<p style="text-indent:2em;margin-top:10px;">
			6．1 数量确认：煤炭数量以甲方入厂检斤为准。</p>
			<p style="text-indent:2em;margin-top:10px;">
			6．2 质量检验：甲、乙双方以甲方入厂检验结果为结算依据。</p>
			<p style="text-indent:2em;margin-top:10px;">
			6．3 甲、乙双方均有到现场监督的权利，但不能影响和干预质检单位采、制、化工作在“公开、公平、公正”的原则下独立进行。来煤夹杂沙、石、土、矸石及其它杂物，甲方有权扣除一定数量的吨位、拒付当日（车）煤款或解除合同；同一车煤上下煤质不一致，有明显的弄虚作假现象，甲方有权根据情况扣除当车煤量或解除合同，扣除情况需经乙方在三个工作日内签字确认并履行相关手续否则视为同意。</p>
			<p style="text-indent:2em;margin-top:10px;">
			6．4 甲方应在煤炭入厂三个工作日内将煤质化验报告单传至乙方，乙方对化验结果如有异议，应在接到化验报告单三个工作日内提出，并在条件允许的情况下重新取样复检，复检结果作为双方最终结算依据，复检费用由提议方承担。</p>
			<p style="text-indent:2em;margin-top:10px;">
			6．5 考虑到单批次化验偏差的偶然性，甲、乙双方以结算周期平均质量进行比对，如热值偏差在±70kcal/kg以内，则按合同确定的化验结果正常结算，如热值偏差超出±70kcal/kg，双方共同协商解决或后期双方共同委托第三方进行采、制、化工作并作为结算依据。</p>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				7．结算及付款
			</div>
			<p style="text-indent:2em;margin-top:10px;">
			7．1 甲、乙双方按有效的过衡单、质量检验化验单（以下简称“结算单据”）进行煤款结算。</p>
			<p style="text-indent:2em;margin-top:10px;">
			7．2 甲、乙双方根据甲方化验单结果计价按化验批次结算，月度累计汇总，每月15日统计上月15日到本月14日煤款（节假日顺延），乙方开具发票办理付款事宜。</p>
			<p style="text-indent:2em;margin-top:10px;">
			7．3 乙方应在收到“结算单据”第二个工作日内进行确认，并向甲方开具符合当地国税部门要求的税率为17%的增值税专用发票。</p>
			<p style="text-indent:2em;margin-top:10px;">
			7．4 甲方应在收到发票后60个工作日内以银行转帐支票或承兑汇票方式向乙方支付煤款。</p>
			<p style="text-indent:2em;margin-top:10px;">
			7．5 结算数量以吨为单位，保留至个位数。以质计价时，发热量以kcal/kg计价，保留至个位数。</p>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				8．履约权利与责任：
			</div>
			<p style="text-indent:2em;margin-top:10px;">
			8．1 在合同执行过程中，甲、乙双方均有权对本合同提出书面修改意见，对此甲、乙双方应该本着相互理解合作的精神进行协商，在双方未就修改意见达成一致及制作书面文件作为本合同有效组成之前，  提出的修改意见不视为成立。</p>
			<p style="text-indent:2em;margin-top:10px;">
			8．2一旦双方就修改意见达成一致，应以重新签订合同的形式签订合同同时上一个合同作废。</p>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				9．不可抵抗力
			</div>
			<p style="text-indent:2em;margin-top:10px;">
			9．1 如在双方履行合同期间及区域内因发生不可抵抗力（如战争、封锁、骚乱、沉船、铁路及航道阻塞以及火灾、水灾等自然灾害）使本合同无法正常履行时，甲、乙双方毋须对不能正常履行合同负责。</p>
			<p style="text-indent:2em;margin-top:10px;">
			9．2 发生不可抵抗力后，不能正常履行合同一方应第一时间将详情通知对方，并有义务尽量将损失降到最低。不能履行合同一方需提供事故所在地政府机关证明。</p>
			<p style="text-indent:2em;margin-top:10px;">
			9．3 不可抵抗力解除后，双方是否延期履行、部分履行或取消履行本合同，双方应本着相互谅解、互惠互利的原则共同协商解决。</p>
			<div style="text-align:left;font-size:18px;font-weight:bold;margin:10px 0;">
				10．争议解决
			</div>
			<p style="text-indent:2em;margin-top:10px;">
			甲、乙双方对执行合同发生的一切争执，本着友好合作的精神共同协商解决。若协商不能达成一致的，可提交给甲方所在地人民法院裁决。</p>
			<div style="text-align:left;font-size:18px;margin:10px 0;">
				<span style="font-weight:bold;">11．合同有效期：</span>本合同签订之日T+1日起至2016年12月31日止。
			</div>
			<div style="text-align:left;font-size:18px;margin:10px 0;">
				<span style="font-weight:bold;">12．合同签订地点：</span>鄂尔多斯市东胜区
			</div>
			<div style="text-align:left;font-size:18px;margin:10px 0;">
				<span style="font-weight:bold;">13．其他：</span>本合同一式六份，双方各执三份。
			</div>
			
			<!--底部table-->
			<div style="margin-top:50px;">
				<table style="width:100%;line-height:45px;border-collapse:collapse;font-size:18px;border:1px solid #444;">
					<tr>
						<td width="50%">
							<table class="contbl" style="width:100%;line-height:45px;padding:20px 10px 40px 10px;border-right:1px solid #444;">
								<tr>
									<td width="25%">甲方：</td>
									<td width="74%"><label style="text-align:left;margin-left:10px; font-size: 18px;">{{dianc.mingc}}</label></td>
								</tr>
								<tr>
									<td>法定代表人：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.faddbr}}</label>
									</td>
								</tr>
								<tr>
									<td>授权代表:</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.weitdlr}}</label>
									</td>
								</tr>
								<tr>
									<td>地址：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.diz}}</label>
									</td>
								</tr>
								<tr>
									<td>邮编：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.youb}}</label>
									</td>
								</tr>
								<tr>
									<td>电话：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.dianh}}</label>
									</td>
								</tr>
								<tr>
									<td>开户银行：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.kaihyh}}</label>
									</td>
								</tr>
								<tr>
									<td>帐号：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.zhangh}}</label>
									</td>
								</tr>
								<tr>
									<td>税号：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.shuih}}</label>
									</td>
								</tr>
								<tr>
									<td>传真：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.chuanz}}</label>
									</td>
								</tr>
							</table>
						</td>
						<td width="50%">
							<table class="contbl" style="width:100%;line-height:45px;padding:20px 10px 40px 10px;">
								<tr>
									<td width="25%">乙方：</td>
									<td width="74%"><label style="text-align:left;margin-left:10px; font-size: 18px;">{{gongys.mingc}}</label></td>
								</tr>
								<tr>
									<td>法定代表人：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.faddbr}}</label>
									</td>
								</tr>
								<tr>
									<td>授权代表:</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.weitdlr}}</label>
									</td>
								</tr>
								<tr>
									<td>地址：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.diz}}</label>
									</td>
								</tr>
								<tr>
									<td>邮编：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.youb}}</label>
									</td>
								</tr>
								<tr>
									<td>电话：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.dianh}}</label>
									</td>
								</tr>
								<tr>
									<td>开户银行：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.kaihyh}}</label>
									</td>
								</tr>
								<tr>
									<td>帐号：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.zhangh}}</label>
									</td>
								</tr>
								<tr>
									<td>税号：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.shuih}}</label>
									</td>
								</tr>
								<tr>
									<td>传真：</td>
									<td>
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.chuanz}}</label>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			</div>
				<div style="text-align: left;margin-top:25px;margin-bottom: 20px;">
					  <button type="button" class="btn btn-primary" ng-click="showCaigdd()">
					  	<i class="icon-plus icon-white"></i>选择合同发货订单
					  </button>
				</div>
				
			<!-- 采购订单table begin -->
			<div id="third-layer">
				<table class="data-table" style="width:100%;line-height:32px;border-collapse:collapse;font-size:18px;">
					<tr>
						<td colspan="9" align="center" style="font-size:20px;font-weight:bold;">采购订单</td>
					</tr>
					<tr>
						<td style="text-align: center;">编号</td>
						<td style="text-align: center;">煤种简称</td>
						<td style="text-align: center;">供应商</td>
						<td style="text-align: center;">数量</td>
						<td style="text-align: center;">质量</td>
						<td style="text-align: center;">单价</td>
						<td style="text-align: center;">计划口径</td>
						<td style="text-align: center;">开始时间</td>
						<td style="text-align: center;">结束时间</td>
					</tr>
					<tr>
						<td class="center">{{addcaigdd.bianh}}</td>
						<td class="center">{{addcaigdd.huowmc}}</td>
						<td class="center">{{addcaigdd.gongys_id}}</td>
						<td class="center">{{addcaigdd.shul}}</td>
						<td class="center">{{addcaigdd.zhil}}</td>
						<td class="center">{{addcaigdd.daocj}}</td>
						<td class="center">{{addcaigdd.jihkj}}</td>
						<td class="center">{{addcaigdd.kaissj}}</td>
						<td class="center">{{addcaigdd.jiessj}}</td>
					</tr>
				</table>
			</div>
			<!-- 采购订单table end -->
				
				
				<div style="text-align: center;margin-top:25px ">
					<button type="button" class="btn btn-primary" ng-click="saveHetbean()"><i class="icon-check icon-white"></i> 保存</button>
					<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
				</div>
			

			<!--弹出窗口 -->
			<div class="modal hide fade" id="myModal" style="width: auto;left: 35%">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">×</a>
					<h4>合同发货订单</h4>
				</div>
				<div class="modal-body">
						<div class="row-fluid" style="margin-bottom:15px;">
							<div class="span6" style="height:25px;line-height:25px;">
				        		开始时间:<input id="datepicker1" ng-model="search.strdate" type="text" style="width:60%;margin-left:10px;" ng-change="refresh()">
							</div>
							<div class="span6" style="height:25px;line-height:25px;vertical-align:middle;">
						    	结束时间:<input id="datepicker2" ng-model="search.enddate" type="text" style="width:60%;margin-left:10px;" ng-change="refresh()">
							</div>
					    </div>
				
					<table cellpadding="0" cellspacing="0" border="0"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th style="text-align: center; width: 100px;">编号</th>
								<th style="text-align: center; width: 100px;">煤种简称</th>
								<th style="text-align: center; width: 80px;">供应商</th>
								<th style="text-align: center; width: 60px;">数量</th>
								<th style="text-align: center; width: 80px;">质量</th>
								<th style="text-align: center; width: 80px;">单价</th>
								<th style="text-align: center; width: 80px;">计划口径</th>
								<th style="text-align: center; width: 60px;">开始时间</th>
								<th style="text-align: center; width: 60px;">结束时间</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
		
						<tbody>
							<tr ng-repeat="f in caigddbList">
								<td class="center">{{f.bianh}}</td>
								<td class="center">{{f.huowmc}}</td>
								<td class="center">{{f.gongys_id}}</td>
								<td class="center">{{f.shul}}</td>
								<td class="center">{{f.zhil}}</td>
								<td class="center">{{f.daocj}}</td>
								<td class="center">{{f.jihkj}}</td>
								<td class="center">{{f.kaissj}}</td>
								<td class="center">{{f.jiessj}}</td>
								<td style="text-align: center;">
									<button class="btn btn-success btn-mini" ng-click="addinfo(f);" ng-if="f.id != addcaigdd.id">
										<i class="icon-ok icon-white" title="选择" ng-if="f.id != addcaigdd.id"></i>
									</button>					
								</td>		
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button ng-click="getKaohzb()" class="btn" data-dismiss="modal"><i class="icon-remove"></i> 确认</button>
				</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	/* $(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	}); */
</script>