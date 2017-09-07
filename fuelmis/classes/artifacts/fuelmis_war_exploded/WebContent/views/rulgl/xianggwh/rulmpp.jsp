<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.ui-datepicker-calendar {
	display: none;
}

th {
	text-align: center !important;
}

td {
	text-align: center !important;
	margin: 0px !important;
	padding: 0px !important;
	height: 30px !important;
}

td input,td select {
	height: 100% !important;
	width: 100% !important;
	/* 	height: 37px !important; */
	margin: 0px !important;
	padding: 0px !important;
	border: 0px !important;
	text-align: center !important;
	/* font-size: 16px !important; */
	background-color: transparent !important;
}

td input {
	line-height: 28px !important;
}
.img{
	width : 30px !important;
	margin-top:5px;
}
</style>
<div class="tab-pane" ng-controller="rulmppCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<!-- 日期 -->
				<label style="width: 35px; height: 30px; line-height: 30px; float: left;" class="control-label span1">日期:</label> 
				<input style="width: 80px; float: left;" id="datepicker" ng-model="riq" ng-change="selectriq()" type="text"> 
				<label style="width:60px;margin-right:5px;margin-left:20px;height: 30px; line-height: 30px; float: left;"
					class="control-label span1">单位名称:</label>
				<select style="width: 120px; float: left;" id="meik" ng-model="DIANCXXB_ID" ng-change="refresh()"
					ng-options="option.value as option.name for option in diancList">
					<!-- <option value="{{dianc.value}}"  ng-repeat="dianc in diancList">{{dianc.name}}</option> -->
				</select>

				<button style="margin-left: 10px;" class="btn btn-success" id="save" ng-click="save()">
					<i class="icon-check icon-white"></i> 保存
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover"
					id="example">
					<thead>
						<tr>
							<th style="width: 15%;">耗用日期</th>
							<th style="width: 10%;">是否匹配</th>
							<th style="width: 10%;">入炉班组</th>
							<th style="width: 10%;">入炉机组</th>
							<th style="width: 10%;">发电耗用(吨)</th>
							<th style="width: 10%;">供热耗用(吨)</th>
							<th style="width: 10%;">其他用(吨)</th>
							<th style="width: 10%;">非生产用</th>
							<th style="width: 15%;">化验编码</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in list.haoylist track by $index" class="option">
	
							<!-- 耗用日期 -->
							<td><input class=" haoyrq" type="text" onfocus=this.blur()
								ng-model="row.HAOYRQ" ng-blur="checkKuaijrq($event.target)" /></td>
							<!-- 是否匹配 -->
							<td>
								<input type="text" ng-if="row.HUAYD_ID==0" onfocus=this.blur() value="未匹配" />
								<input type="text" ng-if="row.HUAYD_ID!=0" onfocus=this.blur() value="已匹配" />
							</td>
							<!--  入炉班组 -->
							<td><select class=""
								style="line-height: 28px; padding: 2px 0" ng-model="row.RULBZB_ID" disabled="disabled"
								ng-options="option.ID as option.MINGC for option in list.banzlist">
							</select></td>
							<!-- 入炉机组 -->
							<td><select class="" ng-model="row.JIZFZB_ID"  disabled="disabled"
								ng-options="option.ID as option.MINGC for option in list.jizlist">
							</select></td>
							<!-- 发电耗用(吨) -->
							<td><input type="text" ng-model="row.FADHY" onfocus=this.blur()
								ng-blur="checkKuaijrq($event.target)" /></td>
							<!-- 供热耗用(吨) -->
							<td><input type="text" ng-model="row.GONGRHY" onfocus=this.blur()
								ng-blur="checkKuaijrq($event.target)" /></td>
							<!-- 其他用(吨) -->
							<td><input type="text" ng-model="row.QITY" onfocus=this.blur()
								ng-blur="checkKuaijrq($event.target)" /></td>
							<!-- 非生产用 -->
							<td><input type="text" ng-model="row.FEISCY" onfocus=this.blur()
								ng-blur="checkKuaijrq($event.target)" /></td>
							<!-- 化验编码 -->						
							<td><select class="" 
								style="line-height: 28px; padding: 2px 0" ng-model="row.HUAYD_ID"
								ng-options="option.HUAYD_ID as option.HUAYBM for option in list.huaybmlist">
							</select></td>
						</tr>
						<tr >
							<!-- 耗用日期 -->
							<td colspan="4">合计</td>
							<!-- 发电耗用合计(吨) -->
							<td><input type="text" ng-model="FADHYHJ" onfocus=this.blur() /></td>
							<!-- 供热耗用合计(吨) -->
							<td><input type="text" ng-model="GONGRHYHJ" onfocus=this.blur() /></td>
							<!-- 其他用合计(吨) -->
							<td><input type="text" ng-model="QITYHJ" onfocus=this.blur() /></td>
							<!-- 非生产用合计 -->
							<td><input type="text" ng-model="FEISCYHJ" onfocus=this.blur() /></td>
							<!-- 化验编码合计-->						
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
	
</script>
