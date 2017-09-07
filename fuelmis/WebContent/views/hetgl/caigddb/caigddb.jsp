<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<div class="block" ng-controller="caigddbCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{caigddbTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<fieldset>
						<div class="row-fluid" style="margin-bottom:20px;">
							<label class="control-label" style="float:left;width:70px;margin-right:5px;">开始时间:</label>
							<input id="datepicker1" ng-model="search.strdate" style="width:120px;float:left;" type="text" style="float:left" ng-change="refresh()">
							<label class="control-label" style="float:left;width:70px;margin-right:5px;margin-left:10px;">结束时间:</label>
							<input id="datepicker2" ng-model="search.enddate" style="width:120px;float:left;" type="text" style="float:left" ng-change="refresh()">
							<label class="control-label" style="float:left;width:35px;margin-right:5px;margin-left:20px;">电厂:</label>
							<select id="selectType" ng-model="search.diancid" style="width:120px;float:left;" ng-options="option.value as option.name for option in diancList" ng-change="refresh()"></select>
							<label class="control-label" style="float:left;width:50px;margin-right:5px;margin-left:20px;">供应商:</label>
							<select id="selectType" ng-model="search.gongys" style="width:120px;float:left;" ng-options="option.value as option.name for option in gongysList" ng-change="refresh()"></select>
							<button style="margin-left: 10px;" ng-click="showCaigddb()" class="btn btn-success">
								<i class="icon-search icon-white"></i> 查看
							</button>
							<button style="margin-left: 10px;" ng-click="addCaigddb()" class="btn btn-primary">
								<i class="icon-plus icon-white"></i> 添加
							</button>
							<button style="margin-left: 10px;" ng-click="editCaigddb()" class="btn btn-info">
								<i class="icon-edit icon-white"></i> 修改
							</button>
							<button style="margin-left: 10px;" ng-click="delCaigddb()" class="btn btn-danger">
								<i class="icon-trash icon-white"></i> 删除
							</button>
							<!-- 			                 <button style="margin-left: 10px;" ng-click="getJiag()" class="btn btn-danger"> -->
							<!-- 			                 	<i class="icon-trash icon-white"></i> 测试计算价格 -->
							<!-- 			                 </button>			             -->
						</div>
					</fieldset>
				</form>
			</div>
		</div>

		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				   id="example">
				<thead>
				<tr>
					<th style="text-align: center; width: 20px;"></th>
					<th style="text-align: center;">订单编号</th>
					<th style="text-align: center;">订单日期</th>
					<th style="text-align: center;">订单类型</th>
					<th style="text-align: center;">需方</th>
					<th style="text-align: center;">供方</th>
					<th style="text-align: center;">操作人员</th>
				</tr>
				</thead>
			</table>
		</div>

	</div>
</div>


<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" style="display:none;" aria-labelledby="myModalLabel" aria-hidden="true">
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


<script type="text/javascript">
	var oTable;
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
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'hetgl/caigddb/getCaigddbList',
			"language" : {
				"sLengthMenu" : "每页显示 _MENU_条",
				"sZeroRecords" : "没有找到符合条件的数据",
				"sProcessing" : "数据加载中...",
				"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
				"sInfoEmpty" : "没有记录",
				"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
				"sSearch" : "搜索：",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "前一页",
					"sNext" : "后一页",
					"sLast" : "尾页"
				}
			},
			"aoColumnDefs": [{
				"sClass": "center",
				"mRender": function(oObj, sVal) {
					return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
				},
				"bSortable": false,
				"aTargets": [0]
			}]
		});
	});

	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}

	function deleteCaigddb(){
		$("#example input[type=checkbox]").each(function(){
			if(this.checked){
				//hetgl/caigddb/delCaigddbsub
				//$.post("hetgl/caigddb/beforedelCaigddb",{id:$(this).attr("id")},function(data){
				//if(data.relust=="true"){
				$.post("hetgl/caigddb/delCaigddb",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					alert("删除成功！");
					oTable.fnReloadAjax('hetgl/caigddb/getCaigddbList');
				},"json");
				//}else{
				//	$('#myModal_Del').modal('hide');
				//	alert("删除失败当前订单已有关联数据！");
				//}
				//},"json")


			}
		});
	}
</script>