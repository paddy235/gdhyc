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

<div class="tab-pane" ng-controller="jizCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{jizTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:80px;margin-right:5px;" class="control-label">单位名称:</label>
					<select id="dianc_id" style="float: left;width: 120px;" ng-model="search.diancid"
						ng-options="option.value as option.name for option in diancList">
					</select>
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="addJiz()" class="btn btn-primary">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="editJiz()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
					<!-- <button style="margin-left: 10px;" ng-click="delJiz()"
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
						<th style="text-align: center; ">序号</th>
						<th style="text-align: center; ">机组编号</th>
						<th style="text-align: center; ">机组容量(MW)</th>
						<th style="text-align: center; ">投产日期</th>
						<th style="text-align: center; ">日均消耗(t)</th>
						<th style="text-align: center; ">煤耗率(g/kwh)</th>
						<th style="text-align: center; ">计划电量(亿度)</th>
						<th style="text-align: center; ">设计煤种</th>
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
            <h4 class="modal-title" id="myModalLabel">删除机组信息</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除该机组信息吗？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deleteJiz()">确认
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
			'ajax' : 'jiz/getAll',
			'bAutoWidth' : true,
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
            },{"aTargets": [2,4,8],"sClass": "center"},{"aTargets": [1,3,5,6,7],"sClass": "right"}]
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
	
	function deleteJiz(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("jiz/delJiz",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					alert("删除机组信息成功！");
					oTable.fnReloadAjax('jiz/getAll');
				},"json");
		    }
		});  
	}
</script>