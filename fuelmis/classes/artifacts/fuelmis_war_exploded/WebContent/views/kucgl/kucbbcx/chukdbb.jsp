<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
</style>
   <div class="tab-pane" ng-controller="chukdbbcxCtrl">
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 50px;margin-right:5px;" class="control-label">日期:</label>
					<input id="datepicker" type="text" style="float: left;width: 120px;" ng-model="search.Date">
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="出库单报表.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid" id="report" style="width: 100%;margin-left: auto;margin-right: auto;overflow: auto;"></div>
	<div id="pagination_box" class="pagination pagination-right" style="width: 100%;margin-left: auto;margin-right: auto;">
		<ul id="pagination_zc" ></ul>
	</div>
		<!-- 查看详细 -->
	<div   class="modal-me fade" id="myModal_chukdbb" tabindex="-1" role="dialog" style="display:none;" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	   <form id="renyAdd_form" class="form-horizontal" ng-submit="save(diaodjh)" name="diaodjh">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	            <h4 class="modal-title" id="myModalLabel">{{title}}</h4>
	         </div>
	         <div class="modal-body">
	            <div class="block-content collapse in">
					<div class="span12">
						<input type="hidden" ng-model="diaodjh.ID">
						<div class="row-fluid">
							<div class="span6" style="margin-bottom:15px;">
								<label class="control-label">计划单号</label>
								<div class="controls">
									<input type="text" ng-model="diaodjh.JIHDH" style="float: left" required readonly="readonly"/>
								</div>
							</div>
						</div>				
						<div class="row-fluid">
							<div class="span6" style="margin-bottom:15px;">
								<label class="control-label" style="margin-right:20px;">供方</label>
								<div class="controls">
									<select required id="selectType" ng-model="diaodjh.GONGYSB_ID" style="float: left"  ng-options="option.value as option.name for option in gongysList"></select>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span6" style="margin-bottom:15px;">
								<label class="control-label">日期</label>
								<div class="controls" style="display: block;">
									<input required class="datepicker" ng-change="generateJihdh(diaodjh)" ng-model="diaodjh.RIQ" type="text" style="float: left">
								</div>
							</div>
						</div>				
						 <div class="pull-left" style="padding: 20px 100px 19px 50px;">
							  <button type="button" class="btn btn-primary" ng-click="addDiaodjhSub()">
							  	<i class="icon-plus icon-white"></i> 添加
							  </button>				  
						</div>
						<table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th style="width: 80px;" >煤种</th>
									<th >数量(吨)</th>
									<th >低位发热量(Kcal/g,Qnet,ar)</th>
									<th >硫分(%)St,ar</th>
									<th >挥发分(%)Var</th>
									<th >灰分(%)Aar</th>
									<th >总车数</th>
									<th >班组车数</th>
									<th style="width: 40px;">操作</th>
								</tr>
							</thead>
							<tbody >
								<tr ng-repeat="sub in 	diaodjh.diaodjhSubList">
											 <!-- <td>{{$index+1}}</td> -->
									<!-- 煤种 -->
									<td>
										<select style="width: 80px;" ng-model="sub.MEIZ_ID"  ng-options="option.value as option.name for option in pinzList">
									   </select>
									</td>
									<!-- 数量 -->
									<td><input required type="text"  ng-model="sub.SHUL" ></td>
									<!-- 低位发热量 -->
									<td><input required type="text"  ng-model="sub.QNET_AR" ></td>
									<!-- 硫分 -->
									<td><input required type="text"  ng-model="sub.ST_AR"></td>
									<!-- 挥发分 -->
									<td><input required type="text"  ng-model="sub.V_AR" ></td>						 					  
									<!-- 灰分-->
									<td><input required type="text"  ng-model="sub.A_AR" ></td>
									 <!-- 总车数 -->
									<td><input required type="text"  ng-model="sub.ZONGCS" ></td>						 					  
									<!-- 班组车数-->
									<td><input required type="text"  ng-model="sub.BANZCS" ></td>	
									<td >
				                    	<button class="btn btn-danger btn-mini" ng-click="deleteDiaodjhSub($index);">
				                    		<i class="icon-remove icon-white" title="删除"></i>
				                    	</button>
									</td>  
								</tr>		 
							</tbody>
						</table>
					</div>
			 	</div>
	         </div>
			<div class="modal-footer">
	         <input type="submit" class="btn btn-primary" value="保存" ng-disabled="diaodjh.$invalid" >
			<button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
	        </div>
		</div>
	</form>
</div>
</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true,
		});
	});
</script>