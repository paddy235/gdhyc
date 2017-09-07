<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="chepbtmpCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{chepbtmpTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
			<form class="form-horizontal ng-pristine ng-valid">
		 	<fieldset>
		 	<legend>查询信息</legend>
			    <div class="control-group ">
			    
	        		<label class="control-label span2">开始时间:</label>
					<div class="controls span4" >
					 	<input id="datepicker1" ng-model="search.strdate" type="text" style="float: left">
					</div> 	
			    	<label class="control-label span2">结束时间:</label>
					<div class="controls span4" >
					 	<input id="datepicker2" ng-model="search.enddate" type="text" style="float: left">
					</div>
			    </div>
			    		 	
			 	<div class="control-group ">
	        		<label class="control-label span2">电厂:</label>
					<div class="controls span4" >
					 	<select id="selectType" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select>
					</div> 	
			    </div>
			    <div class="table-toolbar">
			   		<center>
		              <div class="btn-group">
		                 <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success"><i class="icon-search"></i>刷新</button>
		              </div>
		              
		              <div class="btn-group">
		              	 <button style="margin-left: 10px;" ng-click="update()" class="btn">生成</button>	              
		              </div>	              		              	              
		            </center>
	            </div>
				</fieldset>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<table class="table table-striped table-bordered table-hover"
			id="example">
			<thead>
				<tr>
					<th style="text-align: center; width: 20px;"></th>
					<th style="text-align: center; width: 80px;">电厂信息表ID</th>
					<th style="text-align: center; width: 80px;">运单票据号</th>
					<th style="text-align: center; width: 60px;">供应商名称</th>
					<th style="text-align: center; width: 100px;">煤款单位名称</th>
					<th style="text-align: center; width: 120px;">燃料品种表</th>
					<th style="text-align: center; width: 120px;">录入时间</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">删除</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="deleteChepbtmp()">确认
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
			'ajax' : 'rucslys/chepbtmp/getChepbtmpList',
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
			'scrollX': 200,
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
	
	function deleteChepbtmp(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	$.post("hetgl/chepbtmp/delChepbtmp",{id:$(this).attr("id")},function(data){
					$('#myModal_Del').modal('hide');
					alert("删除成功！");
					oTable.fnReloadAjax('rucslys/chepbtmp/getChepbtmpList');
				},"json");
		    }
		});  
	}
</script>