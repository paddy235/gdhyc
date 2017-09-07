<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="pingffaxzbCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{pingffaxzbTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<button style="margin-left: 10px;" ng-click="addPingffaxzb()" class="btn btn-primary"><i class="icon-plus icon-white"></i> 添加</button>
					<button style="margin-left: 10px;" ng-click="editPingffaxzb()" class="btn btn-info"><i class="icon-edit icon-white"></i> 修改</button>
					<button style="margin-left: 10px;" ng-click="delPingffaxzb()" class="btn btn-danger"><i class="icon-trash icon-white"></i> 删除</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" id="example">
				<thead>
					<tr>
						<th style="text-align:center;"></th>
						<th style="text-align:center;">评分方案</th>
						<th style="text-align:center;">指标代码</th>
						<th style="text-align:center;">指标公式</th>
						<th style="text-align:center;">分值</th>
						<th style="text-align:center;">类型</th>
						<th style="text-align:center;">文字说明</th>
						<th style="text-align:center;">公式说明</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>


<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display:none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" style="display:none;">×</button>
            <h4 class="modal-title" id="myModalLabel">删除评分标准</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">确认删除评分标准?</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deletePingffaxzb()">确认</button>
            <button type="button" class="btn" data-dismiss="modal">取消</button>
         </div>
      </div>
   </div>
</div>


<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'gongyspg/pingffaxzb/getPingffaxzbList',
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
			'bAutoWidth' : true,
			'scrollX': "100%",
			'scrollCollapse': true,
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
	
	function deletePingffaxzb(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("gongyspg/pingffaxzb/delPingffaxzb",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					alert("删除成功！");
					oTable.fnReloadAjax('gongyspg/pingffaxzb/getPingffaxzbList');
				},"json");
		    }
		});  
	}
</script>