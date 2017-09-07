<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="priceSchemeCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{priceSchemeTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
			<form class="form-horizontal ng-pristine ng-valid">
			 	<fieldset>
		              <div class="btn-group">
		              	 <button style="margin-left: 10px;" ng-click="addPriceScheme()" class="btn btn-primary">
		              	 	<i class="icon-plus icon-white"></i> 添加
		              	 </button>	              
		              </div>	              
	
		              <div class="btn-group">
		                 <button style="margin-left: 10px;" ng-click="editPriceScheme()" class="btn btn-info">
		                 	<i class="icon-edit icon-white"></i> 修改
		                 </button>
		              </div>
		              
		              <div class="btn-group">
		                 <button style="margin-left: 10px;" ng-click="delPriceScheme()" class="btn btn-danger">
		                 	<i class="icon-trash icon-white"></i> 删除
		                 </button>
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
						<th style="text-align: center; "></th>
						<th style="text-align: center; ">名称</th>
						<th style="text-align: center; ">代码</th>
						<th style="text-align: center; ">状态</th>
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
            <h4 class="modal-title" id="myModalLabel">删除计价指标</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deletepriceScheme()"><i class="icon-ok-sign icon-white"></i> 确认
            </button>
            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
            </button>
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
			"ajax" : "hetgl/priceitem/getPriceSchemeList",
			"bAutoWidth" : true,
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
	
	function deletepriceScheme(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("hetgl/priceitem/delPriceScheme",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					if(data.result==1){
						alert("删除成功！");	
					}else{
						alert("删除失败!");
					}
					oTable.fnReloadAjax('hetgl/priceitem/getPriceSchemeList');
				},"json");
		    }
		});  
	}
</script>