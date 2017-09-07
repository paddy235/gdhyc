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

<div class="tab-pane" ng-controller="meikxxCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{meikxxTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<button style="margin-left: 10px;" ng-click="addMeikxx()" class="btn btn-primary">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="editMeikxx()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
					<button style="margin-left: 10px;" ng-click="shezChez()" class="btn btn-success">
						<i class="icon-shopping-cart icon-white"></i> 设置车站
					</button>
					<button style="margin-left: 10px;" ng-click="guanlGongys()" class="btn btn-success">
						<i class="icon-list-alt icon-white"></i> 关联供应商
					</button>
					<button style="margin-left: 10px;" ng-click="uploadFuj()" class="btn btn-inverse" id="fujBtn">
						<i class="icon-file icon-white"></i> 添加附件
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 20px;"></th>
						<th style="text-align: center; width: 50px;">序号</th>
						<th style="text-align: center; width: 150px;">煤矿地区</th>
						<th style="text-align: center; width: 100px;">编码</th>
						<th style="text-align: center; width: 150px;">名称</th>
						<th style="text-align: center; width: 200px;">全称</th>
						<th style="text-align: center; width: 100px;">拼音</th>
						<th style="text-align: center; width: 100px;">省份</th>
						<th style="text-align: center; width: 100px;">类别</th>
						<th style="text-align: center; width: 100px;">类型</th>
						<th style="text-align: center; width: 100px;">计划口径</th>
						<th style="text-align: center; width: 100px;">联系人</th>
						<th style="text-align: center; width: 100px;">电话</th>
						<th style="text-align: center; width: 100px;">手机</th>
						<th style="text-align: center; width: 100px;">传真</th>
						<th style="text-align: center; width: 100px;">电子邮件</th>
						<th style="text-align: center; width: 100px;">使用状态</th>
						<th style="text-align: center; width: 200px;">附件内容</th>
						<th style="text-align: center; width: 100px;">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">附件上传 </h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					<form id="fujAdd_form" class="form-horizontal">
						<div class="control-group">
							<input type="hidden" id="meikxx_id" value=""/>
							<label class="control-label">附件上传</label>
							<div class="controls">
								<input id="upFile" name="upFile" type="file" size="45" class="span6 m-wrap"/>
								<button type="button" class="btn btn-primary" onclick="fjUpload()">
									<i class="icon-upload icon-white"></i> 上传
								</button>
							</div>
						</div>
					</form>
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
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
			'ajax' : 'meikxx/getAll',
			//"sPaginationType": "full_numbers",
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
			"sScrollXInner": "2870px",
			"bScrollCollapse": true,
			"aoColumnDefs": [{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
                },
                "bSortable": false,
                "aTargets": [0]
            },{"aTargets": [2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18],"sClass": "center"},{"aTargets": [1],"sClass": "right"}]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
	
	function fjUpload(){
		$.ajaxFileUpload({
			url: 'meikxx/uploadFile',
			secureuri: false,
			fileElementId: 'upFile',
			dataType: 'json',
			data:{
				id : $('#meikxx_id').val()
			},
			success: function (data, status){
				alert(data[0].msg);
				$('#myModal').modal('hide');
				oTable.fnReloadAjax('meikxx/getAll');
            },
            error: function (data, status, e){
                alert(e);
            }
        })
        return false;
	}
</script>