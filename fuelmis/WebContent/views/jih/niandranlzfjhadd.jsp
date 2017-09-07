<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
 #example th,td{white-space: nowrap;}
  tbody .center {
text-align: center !important;
}
tbody .right {
text-align: right !important;
}
</style>
    <div class="tab-pane" ng-controller="niandranlzfjhCtrl">
<%--	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{naindranlzfTitle}}</div>
	    </div>--%>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label">年份:</label>
					<input style="width: 45px;float: left;" id="datepicker" ng-model="search.nianf" ng-change="selectnianf()" type="text">
					<label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label span1">单位:</label>
					<select style="width: 100px;float: left;" ng-model="search.diancid" ng-change="selectdianc()" ng-options="option.value as option.name for option in diancList"></select>
		            <button style="margin-left: 5px;"  id="refresh" ng-click="refresh()" class="btn btn-success"><i class="icon-refresh icon-white"></i> 刷新</button>
		            <button  class="btn btn-primary" id="adddata" name={{search.state}}  ng-click="addyuedranlzfjh()"><i class="icon-plus icon-white"></i> 新增</button>
					<button  disabled id="updateniandcaig" ng-click="updateyuedcgjh()" class="btn btn-info"><i class="icon-edit icon-white"></i> 修改</button>
					<button  disabled id="delniandcaig"  ng_click="delniandcaig()" class="btn btn-danger"><i class="icon-trash icon-white"></i> 删除</button>
					<button  class="btn btn-primary" id="copyniandcaig" ng-click="copyniandcaigjh()" ><i class="icon-file icon-white"></i> 复制上年计划</button>
				</div>
			</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
	             <thead>
	                 <tr>
	                 	<th style="text-align: center; width: 20px;"></th>
	                 	<th style="text-align: center; width: 30px;">序号</th>
	                 	<th style="text-align: center;">费用名称</th>
	                    <th style="text-align: center;">{{search.nianf}}年预测金额(元)</th>
	                    <th style="text-align: center;">预算年度费用说明</th>
	                    <th style="text-align: center;">上一年预计完成金额（元）</th>
	                    <th style="text-align: center;">上一年已完成金额（元）</th>
	                    <th style="text-align: center;">上一年未完成金额（元）</th>
	                    <th style="text-align: center;">上一年费用情况说明</th>
	                 </tr>
	             </thead>
	         </table>
	       </div>
		</div>
	<!-- END FORM-->
</div>

<script type="text/javascript">
	var oTable = $('#example').dataTable({
		"processing" : true,
		//'ajax' : 'diancxx/getAll',
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
		"sScrollX": "110%",
		"aoColumnDefs": [{
            "sClass": "center",
            "mRender": function(oObj, sVal) {
                return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },
        {
        	"aTargets": [2],
        	"sClass":"center",
        },
        {
        	"aTargets": [1,3,5,6,7],
        	"sClass":"right",
        }
		
		
		]
	});
	
    function check(args){
    	if($("#adddata").attr("name")=='0'){
			if($(args).attr("checked")!=undefined){
				$("#delniandcaig").removeAttr("disabled"); //移除disabled属性 
				$("#updateniandcaig").removeAttr("disabled"); //移除disabled属性 
				$("input[type='checkbox']").attr("checked",false);
				$(args).attr("checked",true);
				//obj.attr("id")
			}else{
				$("#delniandcaig").attr('disabled',true);
				$("#updateniandcaig").attr('disabled',true);
	    	}
    	}
	}
    
</script>