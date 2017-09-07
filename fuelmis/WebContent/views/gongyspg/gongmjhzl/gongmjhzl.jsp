<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="gongmjhzlCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{gongmjhzlTitle}}</div>
	</div>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
			<form class="form-horizontal ng-pristine ng-valid">
		 	<fieldset>
		 	<legend>查询信息</legend>
			    <div class="control-group ">
			    	<label class="control-label span2">供应商:</label>
					<div class="controls span4" >
						<select id="selectType" ng-model="search.gongysb_id"  ng-options="option.value as option.name for option in gongysList">
						</select>
					</div>
					
	        		<label class="control-label span2">开始时间:</label>
					<div class="controls span4" >
					 	<input id="datepicker1" ng-model="search.date" type="text" style="float: left">
					</div> 	
			    </div>
			    		 	
			    <div class="table-toolbar">
			   		<center>
		              <div class="btn-group">
		                 <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success"><i class="icon-search"></i>刷新</button>
		              </div>
		              
		              <div class="btn-group">
		                 <button style="margin-left: 10px;" ng-click="editGongmjhzl()" class="btn">指标</button>
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
					<th style="text-align: center; ">名称</th>
					<th style="text-align: center; ">日期</th>
					<th style="text-align: center; ">计划量</th>
				</tr>
			</thead>
		</table>
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
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'gongyspg/gongmjhzl/getRigmjhList',
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
</script>