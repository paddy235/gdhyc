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

<div class="tab-pane" ng-controller="renyxxCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{renyxxTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<button style="margin-left: 10px;" ng-click="addRenyxx()" class="btn btn-primary"><i class="icon-plus icon-white"></i> 添加</button>
					<button style="margin-left: 10px;" ng-click="editRenyxx()" class="btn btn-info"><i class="icon-edit icon-white"></i> 修改</button>
					<!-- <button style="margin-left: 10px;" ng-click="delRenyxx()"class="btn">删除</button> -->
					<button style="margin-left: 10px;" ng-click="resetPassword()" class="btn btn-success"><i class="icon-refresh icon-white"></i> 重置密码</button>
					<button style="margin-left: 10px;" ng-click="shezqx()" class="btn btn-danger"><i class="icon-list-alt icon-white"></i> 设置权限</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th></th>
						<th style="text-align: center; width: 100px;">用户名</th>
						<th style="text-align: center; width: 100px;">姓名</th>
						<th style="text-align: center; width: 100px;">性别</th>
						<th style="text-align: center; width: 100px;">部门</th>
						<th style="text-align: center; width: 200px;">职位</th>
						<th style="text-align: center; width: 120px;">是否可登录</th>
						<th style="text-align: center; width: 100px;">移动电话</th>
						<th style="text-align: center; width: 100px;">固定电话</th>
						<th style="text-align: center; width: 100px;">传真</th>
						<th style="text-align: center; width: 100px;">邮政编码</th>
						<th style="text-align: center; width: 150px;">Email</th>
						<th style="text-align: center; width: 100px;">联系地址</th>
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
            <h4 class="modal-title" id="myModalLabel">删除人员信息</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除该人员吗？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deleteRenyxx()">确认
            </button>
            <button type="button" class="btn" 
               data-dismiss="modal">取消
            </button>
         </div>
      </div>
   </div>
</div>

<div class="modal fade" id="myModal_Reset" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel"  style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel">重置密码</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认要重置密码吗？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="reset()"><i class="icon-ok-sign icon-white"></i> 确认</button>
            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
         </div>
      </div>
   </div>
</div>

<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'renyxx/getAll',
			//"sPaginationType": "full_numbers",  //加上这行可以显示首页/尾页，但是无法加载样式
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
			"sScrollX": "100%",
			//"sScrollXInner": "1370px",
			"bScrollCollapse": true,
			"aoColumnDefs": [{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
                },
                "bSortable": false,
                "aTargets": [0]
            },{"aTargets": [1,2,3,4,5,6,7,8,9,10,11,12],"sClass": "center"}]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
	
	function deleteRenyxx(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("renyxx/delRenyxx",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					alert("删除人员信息成功！");
					oTable.fnReloadAjax('renyxx/getAll');
				},"json");
		    }
		});  
	}
	
	function reset(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("renyxx/resetPassword",{id:$(this).attr("id")},function(data){
					$('#myModal_Reset').modal('hide');
					alert("重置密码成功！");
					oTable.fnReloadAjax('renyxx/getAll');
				},"json");
		    }
		});  
	}
</script>