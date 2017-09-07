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
<div class="tab-pane" ng-controller="diaodjhInfoCtrl">
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">			
		 	<fieldset>
		    	<label class="control-label" style="width:100px;margin-right:5px;" >到货开始时间:</label>
			 	<input id="datepicker1" ng-model="search.strdate" type="text" style="width:150px;float: left" ng-change="searchData()">
				<label class="control-label" style="width:100px;margin-right:5px;" >到货结束时间:</label>
			 	<input id="datepicker2" ng-model="search.enddate" type="text" style="width:150px;float: left" ng-change="searchData()">
        		<label class="control-label" style="width:35px;margin-right:5px;" >电厂:</label>
			 	<select id="selectType" ng-model="search.diancid" style="width:150px;" ng-change="searchData()" ng-options="option.value as option.name for option in diancList"></select>
<!--                  <button style="margin-left: 10px;" ng-click="searchData()" class="btn btn-success"> -->
<!--                  	<i class="icon-search icon-white"></i> 刷新 -->
<!--                  </button> -->
		 	</fieldset>
        </form>
        </div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th ></th>
						<th >时间</th>
						<th >第几周</th>
						<th >计划数量</th>
						<th >录入人</th>
						<th >状态</th>
					</tr>
				</thead>
			</table>
		</div>
      </div>
</div>

<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		oTable = $('#example').dataTable({
			"processing" : true,
			'ajax' : 'diaodjh/getDiaodjhinfoList1',
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
			'scrollX': "100%",
			'scrollCollapse': true,
			"aoColumnDefs": [{
                "sClass": "center",
                "mRender": function(oObj, sVal) {
                    return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
                },
                "bSortable": false,
                "aTargets": [0]
            },
	        {  
	           "sClass": "center",
	           "targets": [1,2,4,5]      

	       },
	       {  
	           "targets": [3] ,
	           "sClass": "right"      

	      }]
		});
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
		
	});
		
</script>