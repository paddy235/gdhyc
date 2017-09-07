<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="hetmbCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{hetmbTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
			<form class="form-horizontal ng-pristine ng-valid">
		 	<fieldset>
		 	<!-- <legend>查询信息</legend> -->
			<!-- <div class="control-group "> -->
			    
<!-- 	        		<label class="control-label span2" style="margin-right:-20px;">开始时间:</label> -->
<!-- 					<div class="controls span4" > -->
<!-- 					 	<input id="datepicker1" ng-model="search.strdate" type="text" style="float: left"> -->
<!-- 					</div> 	 -->
<!-- 			    	<label class="control-label span2" style="margin-right:-20px;">结束时间:</label> -->
<!-- 					<div class="controls span4" > -->
<!-- 					 	<input id="datepicker2" ng-model="search.enddate" type="text" style="float: left"> -->
<!-- 					</div> -->
<!-- 			    </div> -->
			    		 	
<!-- 			 	<div class="control-group "> -->
<!-- 	        		<label class="control-label span2" style="margin-right:-20px;">电厂:</label> -->
<!-- 					<div class="controls span4" > -->
<!-- 					 	<select id="selectType" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select> -->
<!-- 					</div> 	 -->
<!-- 			    </div> -->
			    <div class="table-toolbar">
<!-- 		           <div class="btn-group"> -->
<!-- 		              <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success"> -->
<!-- 		                <i class="icon-search icon-white"></i> 刷新 -->
<!-- 		              </button> -->
<!-- 		           </div> -->
		              
		              <div class="btn-group">
		              	 <button style="margin-left: 10px;" ng-click="addHetmb()" class="btn btn-primary">
		              	 	<i class="icon-plus icon-white"></i> 添加
		              	 </button>	              
		              </div>	              
	
		              <div class="btn-group">
		                 <button style="margin-left: 10px;"  ng-click="editHetmb()" class="btn btn-info">
		                 	<i class="icon-edit icon-white"></i> 修改
		                 </button>
		              </div>
		              
		              <div class="btn-group">
		                 <button style="margin-left: 10px;" ng-click="delHetmb()" class="btn btn-danger">
		                 	<i class="icon-trash icon-white"></i> 删除
		                 </button>
		              </div>
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
						<th style="text-align: center; ">模板编号</th>
						<th style="text-align: center; ">名称</th>
						<th style="text-align: center; ">有效开始时间</th>
						<th style="text-align: center; ">有效结束时间</th>
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
            <h4 class="modal-title" id="myModalLabel">删除合同模板</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deleteHetmb()"><i class="icon-ok-sign icon-white"></i> 确认
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
			"ajax" : "hetgl/hetmb/getHetmbList",
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
	
	function deleteHetmb(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("hetgl/hetmb/delHetmb",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					if(data.result==1){
						alert("删除成功！");	
					}else{
						alert("删除失败！当前合同模板已关联合同");
					}
					
					oTable.fnReloadAjax('hetgl/hetmb/getHetmbList');
				},"json");
		    }
		});  
	}
</script>