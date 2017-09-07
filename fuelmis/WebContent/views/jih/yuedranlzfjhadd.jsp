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
    <div class="tab-pane" ng-controller="yuedranlzfjhCtrl">
<%--	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{yuedranlzfTitle}}</div>
	    </div>--%>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label">日期:</label>
					<input style="width: 69px;float: left;" id="datepicker" ng-model="search.riq" ng-change="selectriq()" type="text">
					<label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label span1">单位:</label>
					<select style="width: 120px;float: left;" ng-model="search.diancid" ng-change="selectdianc()" ng-options="option.value as option.name for option in diancList"></select>
		            <button style="margin-left: 5px;"  id="refresh" ng-click="refresh()" class="btn btn-success">
		            	<i class=" icon-refresh icon-white"></i> 刷新
		            </button>
		            <button  class="btn btn-primary" name={{search.state}} id="adddata" ng-click="addyuedranlzfjh()">
		            	<i class="icon-plus icon-white"></i> 新增
		            </button>
					<button  disabled id="updateranlzf" ng-click="updateyuedcgjh()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
					<button  disabled id="delranlzf" ng-click="delranlzf()"  class="btn btn-danger">
						<i class=" icon-trash icon-white"></i> 删除
					</button>
					<button  class="btn btn-primary" id="copyranlzf" ng-click="copyranlzfjh()">
						<i class="icon-file icon-white"></i> 复制上月计划
					</button>
				</div>
			</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
	             <thead>
	                 <tr>
	                 	<th style="width:5%;"></th>
	                 	<th style="text-align:center;width:5%;">序号</th>
	                 	<th style="text-align:center;width:30%;">费用名称</th>
	                    <th style="text-align:center;width:20%;">{{search.titleriq}}月预付金额（元）</th>
	                    <th style="text-align:center;width:40%;">预算费用说明</th>
	                 </tr>
	             </thead>
	         </table>
	       </div>
		</div>
			<!-- END FORM-->
</div>

<script type="text/javascript">
	var oTable;
    $(document).ready(function() {
    	oTable = $('#example').dataTable({
			"processing" : true,
			//'ajax' : 'yuedranlzfjh/getRanlzfList',
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
			"resizable":true,
			//'scrollX': 200,
			"scrollCollapse": true,
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
                "targets": [2]      

            },
            {  
            	"targets": [1,3] ,
           	 "sClass": "right"      

           }
			
			
			
			]
		});
    });
    
    
   //设置选中表格数据，选中将删除和更新按钮置亮，取消按钮置灰
    function check(args){
    	if($("#adddata").attr("name")=='0'){
			if($(args).attr("checked")!=undefined){
				$("#delranlzf").removeAttr("disabled"); //移除disabled属性 
				$("#updateranlzf").removeAttr("disabled"); //移除disabled属性 
				$("input[type='checkbox']").attr("checked",false);
				$(args).attr("checked",true);
			}else{
				$("#delranlzf").attr('disabled',true);
				$("#updateranlzf").attr('disabled',true);
	    	}
    	}
	}
    
</script>