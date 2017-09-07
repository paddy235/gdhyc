<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="gongmjhcxCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{gongmjhcxTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
				 	<fieldset>
		        		<label class="control-label" style="width:35px;margin-right:5px;">时间:</label>
					 	<input id="datepicker1" ng-model="search.date" type="text" style="width:150px;float:left">
		        		<label class="control-label" style="width:50px;margin-right:5px;margin-left:10px;">供应商:</label>
					 	<select id="selectType" ng-model="search.gongysb_id" style="width:150px;" ng-options="option.value as option.name for option in gongysList">
						</select>
		                <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success"><i class="icon-search icon-white"></i> 刷新</button>
		              	<!-- 
		              	<button id="generate" style="margin-left: 10px;" ng-click="addGongmjh()" class="btn btn-primary fbtn" ><i class="icon-ok-circle icon-white"></i> 生成</button>	              
		                <button id="delete" style="margin-left: 10px;" ng-click="delGongmjh()" class="btn btn-danger fbtn"><i class="icon-trash icon-white"></i> 删除</button>
		                <button id="submit" style="margin-left: 10px;" ng-click="updateGongmjh()" class="btn btn-primary fbtn"><i class="icon-file icon-white"></i> 提交</button>
		                -->
					</fieldset>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example" my-table="yuegmjh" aa-data="yuegmjh"
				ao-column-defs="columnDefs" fn-row-callback="myCallback">
				<thead>
					<tr>
						<th style="text-align: center; width: 5px;"></th>
						<th style="text-align: center;">时间</th>
						<th style="text-align: center;">计划数量</th>
						<th style="text-align: center;">供应商</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>


<div class="modal fade" id="myModal_Del" style="display:none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
          	<button type="button" class="btn btn-danger" onclick="deleteGongmjh()"><i class="icon-ok-sign icon-white"></i> 确认
            </button>
            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
            </button>
         </div>
      </div>
   </div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
/* 		function check(args){
			if($(args).attr("checked")!=undefined){
				$("input[type='checkbox']").attr("checked",false);
				$(args).attr("checked",true);
			}
		} */
	});
</script>