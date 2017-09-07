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

<div class="tab-pane" ng-controller="licCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{licTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width: 40px;margin-right:5px;" class="control-label">电厂:</label>
					<select id="dianc_id" ng-model="search.diancid" style="float: left;width: 120px;" 
						ng-options="option.value as option.name for option in diancList">
					</select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="addLic()" class="btn btn-primary">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="editLic()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
					<!-- <button style="margin-left: 10px;" ng-click="delLic()"
						class="btn">删除</button> -->
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 20px;"></th>
						<th style="text-align: center;">发站</th>
						<th style="text-align: center;">到站</th>
						<th style="text-align: center;">类型</th>
						<th style="text-align: center;">值(km)</th>
						<th style="text-align: center;">煤矿</th>
						<th style="text-align: center;">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel"  style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">删除里程信息</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除该里程信息吗？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deleteLic()">确认
            </button>
            <button type="button" class="btn" 
               data-dismiss="modal">取消
            </button>
         </div>
      </div>
   </div>
</div>

<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'lic/getAll',
			'bAutoWidth' : true,
			//"sPaginationType" : "full_numbers",
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
            },{"aTargets": [1,2,3,5,6],"sClass": "center"},{"aTargets": [4],"sClass": "right"}]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
	
	function deleteLic(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("lic/delLic",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					alert("删除里程信息成功！");
					oTable.fnReloadAjax('lic/getAll');
				},"json");
		    }
		});  
	}
</script>