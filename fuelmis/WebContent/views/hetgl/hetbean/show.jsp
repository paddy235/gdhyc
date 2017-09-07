<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>煤炭合同范本</title>
		<style>
			*{margin:0;padding:0;}
			#main-container{width:1000px;padding:20px 40px;font-size:18px;font-family:"SimSun";color:#000;background-color:#fff;}
			.data-table td{border:1px solid #444;padding:3px 8px;}
			#main-container input{box-shadow:none;border-radius:0;}
		</style>
	</head>
	<body>
		<div id="main-container" ng-controller="hetbeanShowCtrl">
			<div style="text-align:center;font-size:26px;font-weight:bold;margin:20px 0;">
				低硫煤采购合同
			</div>
			<div id="first-layer">
				<table style="width:100%;line-height:35px;">
					<tr>
						<td width="13%">甲方（买方）：</td>
						<td width="25%">
							<select id="selectType" ng_model="hetbean.xuf" ng-change="changeDiancxx()" style="float: left">
								<option value="{{dianc.value}}"  ng-repeat="dianc in diancList">{{dianc.name}}</option>
				  			</select>
						</td>
						<td width="27%"></td>
						<td width="10%">合同编号：</td>
						<td width="25%">
							<input type="text" ng-model="hetbean.hetbh" style="font-size:18px;width:100%;border:none;border-bottom:1px solid #444;">							
						</td>
					</tr>
					<tr>
						<td>乙方（卖方）：</td>
						<td>
							<select id="selectType" ng-model="hetbean.gongf" ng-change="changeGys()">
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
							<input id="datepicker4" ng-model="hetbean.qiandrq" type="text" style="float: left">
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<p style="text-indent:2em;margin-top:10px;">甲、乙双方依照《中华人民共和国合同法》及其它有关法律、法规规定，遵循平等、自愿、诚实信用的原则，经双方友好协商，签订本合同。</p>
			</div>
			<div style="text-align:left;font-size:20px;font-weight:bold;margin:10px 0;">
				一、煤种名称、品种、性能指标 
			</div>
			<div id="second-layer">
				<table class="data-table" style="width:100%;line-height:32px;border-collapse:collapse;font-size:18px;">
					<tr>
						<td rowspan="2">序号</td>
						<td rowspan="2">品种</td>
						<td colspan="4" align="center">质  量  标  准</td>
					</tr>
					<tr>
						<td>收到基低位发热量（Qnet，ar）</td>
						<td>收到基全硫（St，ar）</td>
						<td>收到基灰分（Aar）</td>
						<td>规格(mm)</td>
					</tr>
					<tr>
						<td>1</td>
						<td>原煤/低硫煤</td>
						<td>4200kcal/kg</td>
						<td>St≤0.5%</td>
						<td>≤13%</td>
						<td>0-100mm</td>
					</tr>
				</table>
			</div>
			<div style="text-align:left;font-size:20px;font-weight:bold;margin:10px 0;">
				二、考核指标：
			</div>
			<div id="third-layer">
				<table class="data-table" style="width:100%;line-height:32px;border-collapse:collapse;font-size:18px;">
					<tr>
						<td width="6%" align="center">序号</td>
						<td>低硫煤考核标准</td>
					</tr>
					<tr>
						<td align="center">1</td>
						<td>收到基全硫(St,ar) ≤0.5％，不考核；大于0.5%将按照同期中高硫煤合同结算。</td>
					</tr>
					<tr>
						<td rowspan="2" align="center">2</td>
						<td>热值大于4200kcal/kg，每增加1kcal/kg，价格增加0.0238元/吨；</td>
					</tr>
					<tr>
						<td>热值在4200－4000kcal/kg，每降低1kcal/kg，价格降低0.0279元/吨；热值在4000－3800kcal/kg，每降低1kcal/kg，价格降低0.0558元/吨；热值在3800－3600kcal/kg，每降低1kcal/kg，价格降低 0.1116元/吨；</td>
					</tr>
					<tr>
						<td align="center">3</td>
						<td>收到基全水分（Mt）≤30%；大于30%有可能拒收。</td>
					</tr>
					<tr>
						<td align="center">4</td>
						<td>收到基灰分（Aar）≤13%；大于17%将按同期工程煤合同价格结算。</td>
					</tr>
					<tr>
						<td align="center">5</td>
						<td>粒度在0-100mm，超过100mm 有可能拒收。</td>
					</tr>
					<tr>
						<td align="center">6</td>
						<td>收到基挥发份≥17%</td>
					</tr>
					<tr>
						<td align="center">7</td>
						<td>乙方运输车辆应及时清洗，保持车辆的清洁，不符合要求，一经发现考核乙方100元/车次。</td>
					</tr>
					<tr>
						<td align="center">8</td>
						<td>乙方车辆应密封良好，运输过程中应加盖篷布，防止漏煤、撒煤。未达到要求，一经发现考核乙方200元/车次。</td>
					</tr>
					<tr>
						<td align="center">9</td>
						<td>来煤掺杂杂物、矸石、沙土、明水或有冒烟、着火等情况，甲方有权拒收，已接受部分将扣除该批次2%吨位数量。</td>
					</tr>
				</table>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">三、合同价格：</span>一票到厂含税单价：XXX元/吨，大写：XXX元/吨；
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">四、交货地点及运输方式、费用：</span>国电新疆红雁池发电有限公司储煤场，汽车运输送货上门、费用由乙方负责。
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">五、供货数量及交货时间：</span>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>5.1、供货数量：乙方应按照甲方每日下达的计划数量供应。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>5.1、供货数量：乙方应按照甲方每日下达的计划数量供应。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">六、煤炭质量、数量验收标准及方法：</span>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>6.1、按照GB/T18666-2002《商品煤炭质量抽查和验收办法》执行。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>6.2、数量：以甲方过衡、入厂检斤后数量为准，以“吨”为单位，保留至个位数。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>6.3、质量：以甲方入厂煤化验数据为结算依据，低位发热量以“kcal/kg”计价，保留至个位数；收到基硫分保留小数点后两位。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>6.4、乙方如对甲方过衡数量、化验结果有异议，应在接到供煤数量及煤质化验报告单三个工作日内提出，并在条件允许的情况下重新取样复检，复检结果作为双方最终结算依据，复检费用由提议方承担。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>6.5、任一批次化验结果偏差较大时，甲、乙双方以近七日内平均结算质量进行比对，如热值偏差在±70kcal/kg以内，则按合同确定的化验结果正常结算，如热值偏差超出±70kcal/kg，双方共同协商解决或后期双方共同委托第三方进行采、制、化工作并作为结算依据。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">七、甲、乙双方权利：</span>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>7.1、甲方负责煤炭采购计划制定，甲方有权根据电厂机组运行情况调整日、月度采购数量。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>7.2、甲方负责煤炭入厂过衡、化验、及接卸工作，在煤炭入厂后三个工作日内将供煤数量及煤质化验报告单传至乙方，以备乙方核对、确认。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>7.3、 乙方负责煤炭装运工作，运输车辆的管理及运输过程中与交警、路政等协调、沟通工作，在煤炭运输过程中发生的一切不安全现象和安全事故,由乙方负责。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>7.4、甲、乙双方均有现场监督的权利，但不能影响和干扰甲方质检单位采、制、化在“公开、公平、公正”的原则下独立进行工作。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">八、结算及付款方式：</span>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>8.1、甲、乙双方按有效的过衡单、质量检验化验单（以下简称“结算单据”）进行煤款结算。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>8.2、甲、乙双方按“日”结算、按“月”累计方式统计结算结果。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p style="text-indent:2em;">“日结算”即以每日化验单加权后计算，单日内不够一个化验批次时，以累计日内数量、化验结果进行计算。每月15日统计汇总结算结果，通知乙方开票，节假日顺延。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>8.3、乙方在收到结算结果后三个工作日内进行确认，并向甲方提供17%的增值税专用发票，甲方办理付款事宜，付款以银行转帐支票或承兑汇票方式向乙方支付。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>8.4、因乙方的原因发生的涉税事宜，给甲方造成的经济损失和不良影响，由乙方承担全部责任并赔偿甲方全部经济损失。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">九、其他事项</span>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>9.1、乙方运输车辆进厂后应服从现场值班人员的指挥和调动，并服从《入厂煤车辆的管理规定》的考核。乙方运输车辆在甲方厂区内因乙方原因造成设施、设备损坏,由乙方进行全额赔偿（包括人工费、机械费、材料费）。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>9.2、 乙方负责车辆信息卡填报工作，乙方必须严格按照甲方要求使用车辆信息卡,如出现因乙方不及时填写或变更车辆信息卡,造成的一切后果由乙方自负。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>9.3、乙方销售人员私自与煤炭采制化人员进行接触，更改过衡数量、化验结果的，一经发现将终止并解除合同，且在三年内不再与乙方签订任何煤炭购销合同。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">十、合同解除和变更：</span>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>10.1、甲、乙方均有权终止和解除合同。合同期内遇市场价格变动，合同双方在协商一致的情况下签订新的煤炭购销合同，本合同在新合同签字生效后自行终止并解除。若协商不成，甲方有权终止合同执行。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>10.2、同一煤种签订新的煤炭购销合同后，本批次所签订该煤种所有合同同时终止并自动解除合同。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">十一、不可抗力：</span>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>11.1、由于战争、火灾、地震、雨雪天气等的自然因素，影响合同的正常履行，双方可免除违约责任。但由于一方延迟履行后发生不可抗力因素，不能免除责任。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<p>11.2、发生不可抗力的一方，应及时告知对方，采取措施，尽量减少、避免损失，并提供相关政府出具事故证明。</p>
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">十二、违约责任：</span>依照《中华人民共和国合同法》相关条款执行。
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">十三、解决合同纠纷的方式：</span>执行本合同发生争议，由当事人双方协商解决。协商不成，交由鄂尔多斯市人民法院管辖。
			</div>
			<div style="text-align:left;font-size:20px;margin:10px 0;">
				<span style="font-weight:bold;">十四、</span>本合同一式六份，双方各执三份。
			</div>
			<!--底部table-->
			<div>
				<table style="width:100%;line-height:32px;border-collapse:collapse;font-size:18px;border:1px solid #444;">
					<tr>
						<td width="48%" style="border-right:1px solid #444;">
							<table style="width:100%;line-height:35px;margin:20px 10px 40px 10px;">
								<tr>
									<td align="center" colspan="2">甲方</td>
								</tr>
								
								<tr>
									<td width="20%">单位名称：</td>
									<td width="79%">
										<label style="text-align:left;margin-left:10px; font-size: 18px;">{{dianc.mingc}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">地     址：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.diz}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">法人代表：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.faddbr}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">代 理 人：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;"></label>	
									</td>
								</tr>
								<tr>
									<td width="20%">联系电话：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.dianh}}</label>	
									</td>
								</tr>
								<tr>
									<td width="20%">传    真：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.chuanz}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">开户银行：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.kaihyh}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">帐    号：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.zhangh}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">税    号：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.shuih}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">邮    编：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.youb}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">签订日期：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;"></label>
									</td>
								</tr>
							</table>
						</td>
						
						<td width="48%" style="border-right:1px solid #444;">
							<table style="width:100%;line-height:35px;margin:20px 10px 40px 10px;">
								<tr>
									<td align="center" colspan="2">乙方</td>
								</tr>
								
								<tr>
									<td width="20%">单位名称：</td>
									<td width="79%">
										<label style="text-align:left;margin-left:10px; font-size: 18px;">{{gongys.mingc}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">地     址：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.danwdz}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">法人代表：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.faddbr}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">代 理 人：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;"></label>	
									</td>
								</tr>
								<tr>
									<td width="20%">联系电话：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.dianh}}</label>	
									</td>
								</tr>
								<tr>
									<td width="20%">传    真：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.chuanz}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">开户银行：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.kaihyh}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">帐    号：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.zhangh}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">税    号：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.shuih}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">邮    编：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.youb}}</label>
									</td>
								</tr>
								<tr>
									<td width="20%">签订日期：</td>
									<td width="79%">
										<label class="control-label" style="text-align:left;margin-left:10px;"></label>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
				</table>
				<div style="text-align: center;margin-top:25px ">
					<button type="button" class="btn btn-primary" ng-click="saveHetbean()"><i class="icon-check icon-white"></i> 保存</button>
					<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
				</div>
			</div>
		</div>
	<body>
</html>

<script type="text/javascript">
	/* $(document).ready(function() {
		$("#datepicker4").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
		$("#datepicker3").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
		$("#tabs").tabs();
	}); */
</script>