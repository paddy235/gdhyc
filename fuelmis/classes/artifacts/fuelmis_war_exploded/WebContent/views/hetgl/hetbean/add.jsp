<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="block" ng-controller="hetbeanAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{hetbeanTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="hetbean.id">
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">合同编号</label>
						<div class="controls">
							<input type="text" ng-model="hetbean.hetbh" style="float: left" required/>
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">合同价格</label>
						<div class="controls">
							<input type="number" ng-model="hetbean.hetjg" style="float: left" required/>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">口径类型</label>
						<div class="controls">
							<select id="selectType" ng_model="hetbean.jihkj_id" style="float: left">
								<option value="{{jihkj.value}}"  ng-repeat="jihkj in jihkjList2">{{jihkj.name}}</option>
				  			</select>
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">合同模板</label>
						<div class="controls">
							<select id="selectType" ng-model="hetbean.fujxxId" style="float: left">
								<option value="{{hetmb.value}}"  ng-repeat="hetmb in hetmbList">{{hetmb.name}}</option>
							</select>
						</div>
					</div>
				</div>								
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">需方</label>
						<div class="controls">
							<select id="selectType" ng_model="hetbean.xuf" ng-change="changeDiancxx()" style="float: left">
								<option value="{{dianc.value}}"  ng-repeat="dianc in diancList">{{dianc.name}}</option>
				  			</select>					
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">供方</label>
						<div class="controls">
							<select id="selectType" ng-model="hetbean.gongf" ng-change="changeGys()">
								<option value="{{gys.value}}"  ng-repeat="gys in gongysList2">{{gys.name}}</option>
							</select>
						</div>
					</div>
				</div>				
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">有效期开始</label>
						<div class="controls">
							<input id="datepicker2" ng-model="hetbean.qisrq" type="text" style="float: left">
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">有效期结束</label>
						<div class="controls">
							<input id="datepicker3" ng-model="hetbean.guoqrq" type="text" style="float: left">
						</div>
					</div>
				</div>				
				<div class="row-fluid">
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label">签订地点</label>
						<div class="controls">
							<input id="datepicker1" ng-model="hetbean.qianddd" type="text" style="float: left">
						</div>
					</div>
					<div class="span6" style="margin-bottom:15px;">
						<label class="control-label" style="margin-right:20px;">签订日期</label>
						<div class="controls">
							<input id="datepicker4" ng-model="hetbean.qiandrq" type="text" style="float: left">
						</div>
					</div>
				</div>				
			</form>
		</div>
	</div>
	
	<legend>核对信息</legend>
	<div id="tabs">
		<form id="diancxxAdd_form" class="form-horizontal">
			<input type="hidden" ng-model="diancxx.id">
			<ul>
				<li><a href="#tabs-a">合同发货订单</a></li>
				<li><a href="#tabs-b">供方信息</a></li>
				<li><a href="#tabs-c">需方信息</a></li>
				<li><a href="#tabs-d">其他信息</a></li>
			</ul>
			<div id="tabs-a">
				<div class="block-content collapse in">
					<div class="pull-left" style="padding: 20px 100px 19px 30px;">
					  <button type="button" class="btn btn-primary" ng-click="addHetbeansub()">
					  	<i class="icon-plus icon-white"></i> 添加
					  </button>
					  <button type="button" class="btn btn-primary" ng-click="showCaigdd()">
					  	<i class="icon-plus icon-white"></i> 测试一下行不行
					  </button>
					</div>
		            <table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th style="text-align:center;">序号</th>
								<th style="text-align:center;">采购订单</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							 <tr ng-repeat="sub in hetbeansubList">
								 <td>{{$index+1}}</td>
								 <td class="center">
								 	<select id="selectType" ng_model="sub.id" ng-change="changeCaigdd()">
										<option value="{{caigdd.value}}"  ng-repeat="caigdd in caigddbList">{{caigdd.name}}</option>
			  						</select>	
								 </td>
							     <td class="center">
		                        	<button class="btn btn-danger btn-mini" ng-click="deleteHetbeansub(sub);">
		                        		<i class="icon-remove icon-white" title="删除"></i>
		                        	</button>
		                         </td>								 
							 </tr>		 
						</tbody>
												
				 </table>
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="text-align: center; width: 80px;">数量</th>
							<th style="text-align: center; width: 60px;">质量</th>
							<th style="text-align: center; width: 100px;">平仓价</th>
							<th style="text-align: center; width: 100px;">到场价</th>
							<th style="text-align: center; width: 100px;">数量计算方式</th>
						</tr>
					</thead>
					<tr ng-repeat="caigddb in caigddbsubs">
						<td style="text-align: center; width: 20px;">{{caigddb.shul}}</td>
						<td style="text-align: center; width: 20px;">{{caigddb.zhil}}</td>
						<td style="text-align: center; width: 20px;">{{caigddb.pingcj}}</td>
						<td style="text-align: center; width: 20px;">{{caigddb.daocj}}</td>
						<td style="text-align: center; width: 20px;">{{caigddb.shuljsfs}}</td>
					</tr>								
				</table>
				</div>
			</div>
			<div id="tabs-b">
				<div class="block-content collapse in">
					  <div class="control-group"> 
					    <label class="control-label" style="font-weight:bold;">法定代表人:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.faddbr}}</label>
					    <label class="control-label" style="font-weight:bold;">授权代表:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.mingc}}</label>
					  </div>
					  
					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">地址:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.mingc}}</label>
					    <label class="control-label" style="font-weight:bold;">邮编:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.youzbm}}</label>
					  </div>						  						

					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">电话:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.dianh}}</label>
					    <label class="control-label" style="font-weight:bold;">开户银行:</label>
					    <label class="control-label" style="text-align:left;width:400px;margin-left:10px;">{{gongys.kaihyh}}</label>
					  </div>
					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">账号:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.zhangh}}</label>
					    <label class="control-label" style="font-weight:bold;">税号:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.shuih}}</label>
					  </div>
					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">传真:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{gongys.chuanz}}</label>
					  </div>			  						 
				</div>
			</div>
			<div id="tabs-c">
				<div class="block-content collapse in">
					  <div class="control-group"> 
					    <label class="control-label" style="font-weight:bold;">法定代表人:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.faddbr}}</label>
					    <label class="control-label" style="font-weight:bold;">授权代表:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.mingc}}</label>
					  </div>
					  
					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">地址:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.mingc}}</label>
					    <label class="control-label" style="font-weight:bold;">邮编:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.youzbm}}</label>
					  </div>						  						

					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">电话:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.dianh}}</label>
					    <label class="control-label" style="font-weight:bold;">开户银行:</label>
					    <label class="control-label" style="text-align:left;width:400px;margin-left:10px;">{{dianc.kaihyh}}</label>
					  </div>
					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">账号:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.zhangh}}</label>
					    <label class="control-label" style="font-weight:bold;">税号:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.shuih}}</label>
					  </div>
					  <div class="control-group">
					    <label class="control-label" style="font-weight:bold;">传真:</label>
					    <label class="control-label" style="text-align:left;margin-left:10px;">{{dianc.chuanz}}</label>
					  </div>			  						 
				</div>
			</div>
			<div id="tabs-d">
				<div class="block-content collapse in">
					其他信息
				</div>	
			</div>
		</form>
	</div>
	
	<div style="margin-left:60px;margin-bottom:40px;">
		<button type="button" class="btn btn-primary" ng-click="saveHetbean()"><i class="icon-check icon-white"></i> 保存</button>
		<button type="button" style="margin-left: 10px;" class="btn" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
	</div>
	
	<!--弹出窗口 -->

	<div class="modal hide fade" id="myModal" style="width: auto;">
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
			<button href="javascript:void(0)" class="btn" data-dismiss="modal"><i class="icon-remove"></i> 关闭</button>
		</div>
	</div>



</div>

		




<script type="text/javascript">
	$(document).ready(function() {
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
	});
</script>
