<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="block" ng-controller="priceComponetRelationCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{priceComponetTitle}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
				<input type="hidden" ng-model="priceComponet.id">
				<div class="row-fluid">
					<div class="span6" style="margin-bottom: 15px;">
						<label class="control-label">计价方案</label>
						<div class="controls">
							<input type="text" ng-model="priceComponet.name" readonly="readonly" style="float: left" required />
						</div>
					</div>

					<div class="span6" style="margin-bottom: 15px;">
						<label class="control-label">指标名称</label>
						<div class="controls">
							<input type="text" ng-model="priceItem.name" style="float: left" readonly="readonly" required />
						</div>
					</div>
				</div>
			</form>
			<legend></legend>		 
			<div class="pull-left" style="padding: 20px 100px 19px 50px;">
				  <button type="button" class="btn btn-primary" ng-click="addQalityRange()">
				  	<i class="icon-plus icon-white"></i> 添加
				  </button>				  
			</div>
              <table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th style="text-align:center;">序号</th>
						<th style="text-align:center;">上限(>=)</th>
						<th style="text-align:center;">下限(<)</th>
						<th style="text-align:center;">基准价格</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody >
					 <tr ng-repeat="qality in priceQalityRangeList">
						 <td>{{$index+1}}</td>
						 <td><input type="text" style="width:50px;height:15px" ng-model="qality.range_max" value={{qality.range_max}}></td>
						 <td><input type="text" style="width:50px;height:15px" ng-model="qality.range_min" value={{qality.range_min}}></td>
						 <td><input type="text" style="width:50px;height:15px" ng-model="qality.unitprice" value={{qality.unitprice}}></td>
						                          
 					     <td style="text-align:center;">
                        	<button class="btn btn-danger btn-mini" ng-click="deleteQalityRange(qality);">
                        		<i class="icon-remove icon-white" title="删除"></i>
                        	</button>
                         </td>
                           
					 </tr>		 
				</tbody>
			 </table>
			 
		</div>
	</div>
	<div style="text-align: center;">
		<button type="button" class="btn btn-primary"
			ng-click="saveQalityRange()">
			<i class="icon-check icon-white"></i> 保存
		</button>
		<button type="button" style="margin-left: 10px;" class="btn"
			ng-click="cancel()">
			<i class="icon-repeat"></i> 返回
		</button>
	</div>
</div>

