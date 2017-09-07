<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>
<div class="tab-pane" ng-controller="riddshCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">	 
	        		<label class="control-label" style="width:70px;margin-right:5px;">开始时间:</label>
				 	<input id="datepicker1" ng-model="search.strdate" type="text" style="width:80px;float: left">
			    	<label class="control-label" style="width:70px;margin-right:5px;">结束时间:</label>
				 	<input id="datepicker2" ng-model="search.enddate" type="text" style="width:80px;float: left">
	        		<label class="control-label" style="width:35px;margin-right:5px;float: left">电厂:</label>
				 	<select id="selectType" ng-model="search.diancid" style="width:150px;float: left" ng-options="option.value as option.name for option in diancList"></select>
	                <label class="control-label" style="width:35px;margin-right:5px;">状态:</label>
				 	<select style="width:180px;" ng-model="search.zhuangt" ng-change="load()">
						<option value=0  >未审核</option>
						<option value=1  >已审核</option>	
					</select>
	                 <button style="margin-left: 10px;" ng-click="load()"  class="btn btn-success">
	                 	<i class="icon-search icon-white"></i> 刷新
	                 </button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example" my-table="overrideOptions" aa-data="diaodjh"
				ao-column-defs="columnDefs" fn-row-callback="myCallback">
				<thead>
					<tr>
						<th style="text-align: center;"></th>
						<th style="text-align: center;">编码</th>
						<th style="text-align: center;">时间</th>
						<th style="text-align: center;">煤种</th>
						<th style="text-align: center;">计划数量</th>
						<th style="text-align: center;">煤矿</th>
						<th style="text-align: center;">录入人</th>
						<th style="text-align: center;">批准人</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>


<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display:none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">删除调度计划</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除调度计划？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deleteDiaodjh()">确认
            </button>
            <button type="button" class="btn" 
               data-dismiss="modal">取消
            </button>
         </div>
      </div>
   </div>
</div>


<script type="text/javascript">

	$(document).ready(function() {
		$("#datepicker1").datepicker({
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

	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
	

	
</script>