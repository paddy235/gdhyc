
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="/css/table.css">
<div class="block" ng-controller="diaodjhwhCtrl" >
	<div class="block-content collapse in" id="diaodjhList">
	<!-- 查询条件栏 -->
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
			 		<fieldset>
					    <div class="row-fluid" style="margin-bottom:20px;">
			        		<label class="control-label" style="float:left;width:70px;margin-right:5px;">开始时间:</label>
						 	<input class="datepicker" ng-model="search.sDate" style="width:80px;float:left;" type="text" style="float:left" ng-change="load()">
					    	<label class="control-label" style="float:left;width:70px;margin-right:5px;margin-left:10px;">结束时间:</label>
						 	<input class="datepicker" ng-model="search.eDate" style="width:80px;float:left;" type="text" style="float:left" ng-change="load()">
			        		<label class="control-label" style="float:left;width:35px;margin-right:5px;margin-left:20px;">电厂:</label>
						 	<select id="selectType" ng-model="search.diancid" style="width:120px;float:left;" ng-options="option.value as option.name for option in diancList" ng-change="load()"></select>
			        		<label class="control-label" style="float:left;width:50px;margin-right:5px;margin-left:20px;">供应商:</label>
						 	<select id="selectType" ng-model="search.gongysid" style="width:120px;float:left;" ng-options="option.value as option.name for option in gongysList" ng-change="load()"></select>
			                 <button style="margin-left: 10px;" ng-click="show()" class="btn btn-success">
			                 	<i class="icon-search icon-white"></i> 查看
			                 </button>
			              	 <button style="margin-left: 10px;" ng-click="add()" class="btn btn-primary">
			              	 	<i class="icon-plus icon-white"></i> 添加
			              	 </button>	              
			                 <button style="margin-left: 10px;" ng-click="edit()" class="btn btn-info">
			                 	<i class="icon-edit icon-white"></i> 修改
			                 </button>
			                 <button style="margin-left: 10px;" ng-click="del()" class="btn btn-danger">
			                 	<i class="icon-trash icon-white"></i> 删除
			                 </button>			                 
 							<button style="margin-left: 10px;" ng-click="copy()" class="btn btn-primary">
			                 	<i class="icon-file icon-white"></i> 复制上个月计划
			                 </button>				        
					    </div>
					</fieldset>
				</form>
			</div>
		</div>
	<!-- 表格	 -->
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" 
			my-table="overrideOptions"
            aa-data="diaodjhList"
        ao-column-defs="columnDefs"
        fn-row-callback="myCallback"
				   my-selection="single"
				id="example">
				<thead>
					<tr>
						<th style=" width: 20px;"></th>
						<th >计划单号</th>
						<th >日期</th>
						<th >供方</th>
						<th >煤种</th>
						<th >数量</th>
						<th >审批状态</th>
						<th >录入人</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- 提示框 -->
	<div class="modal fade" id="myModal_Edit" tabindex="-1" role="dialog" style="display:none;" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" 
	               aria-hidden="true">×
	            </button>
	            <h4 class="modal-title" id="myModalLabel">删除订单</h4>
	         </div>
	         <div class="modal-body">
	            <div class="block-content collapse in">
					<div class="span12">
						确认删除？
					</div>
			 	</div>
	         </div>
	         <div class="modal-footer">
	          	<button type="button" class="btn btn-danger" onclick="deleteCaigddb()"><i class="icon-ok-sign icon-white"></i> 确认</button>
	            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
	         </div>
	      </div>
	   </div>
	</div>
	<!-- 查看详细 -->
	<div   class="modal-me fade" id="myModal_diaodjh" tabindex="-1" role="dialog" style="display:none;" aria-labelledby="myModalLabel" aria-hidden="true">
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
		$(".datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	});
	function deleteCaigddb(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("hetgl/caigddb/beforedelCaigddb",{id:$(this).attr("id")},function(data){
		    		if(data.relust=="true"){
				    	$.post("hetgl/caigddb/delCaigddb",{id:$(this).attr("id")},function(data){
							$('#myModal_Del').modal('hide');
							alert("删除成功！");
							oTable.fnReloadAjax('hetgl/caigddb/getCaigddbList');
						},"json");		
		    		}else{
						$('#myModal_Del').modal('hide');
						alert("删除失败当前订单已有关联数据！");		    			
		    		}
				},"json")
		    	

		    }
		});  
	}
</script>