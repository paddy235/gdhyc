<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
   <div class="tab-pane" ng-controller="kucsscbhsCtrl">
   <!-- 	<div id="tabs" style="margin-top:20px;"> -->
<!--      <div class="navbar navbar-inner block-header">
          <div class="muted pull-left">{{Title}}</div> 
    </div> -->
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label style="width: 60px;float:left;height:30px;line-height:30px;" class="control-label">会计时间:</label>
				<input style="width: 150px;float: left;" id="datepicker" ng-model="search.Date"  type="text">
				<button style="margin-left: 5px;float: left;"  id="refresh" ng-click="getKucsscbAlls();" class="btn btn-info">刷新<i class="icon-search icon-white"></i></button>
		   </div>
		</div>
		
		<div class="row-fluid" style="padding-top: 5px;">
			<div width="100%" height="100px" align="center"><!-- <h4>库存</h4></div> -->
			<div width ="100%" align="center">
				<div width="90%">
					<table id="xz" class="table table-striped table-bordered table-hover"> <!-- 滚动条style="width: 1440px;" -->
					<thead>
						<tr>
							<th style="width:10%; text-align: center">库存单编号</th>
							<th style="width:5%; text-align: center">库存组织</th>
							<th style="width:5%; text-align: center">库存位置</th>
							<th style="width:5%; text-align: center">库存物料</th>
							<th style="width:5%; text-align: center">货主</th>
							<th style="width:5%; text-align: center">数量</th>
							<th style="width:5%; text-align: center">金额</th>
							<th style="width:10%; text-align: center">业务类型</th>
							<th style="width:8%; text-align: center">出入库类型</th>
							<th style="width:6%; text-align: center">业务日期</th>
							<th style="width:5%; text-align: center">会计日期</th>
							<th style="width:6%; text-align: center">记账日期</th>
						</tr>
		    			<thead>
		    			 <tbody id="dataTbody"></tbody>
					</table>
				</div>
	<div id="pageDiv">
    	数据条数共<span class="pageNum" id="totalCount"></span>条,第<span class="pageNum" id="page"></span>页,共<span class="pageNum" id="totalPage"></span>页
    	<button style="height: 21px; padding-top: 1px; padding-bottom: 2px;" type="button" id="pres" class="btn btn-default btn-xs pageTip">
      		首页
   	    </button>
    	<button style="height: 21px; padding-top: 1px; padding-bottom: 2px;" type="button" id="pre" class="btn btn-default btn-xs pageTip">
      		上一页
   	    </button>
   		<button style="height: 21px; padding-top: 1px; padding-bottom: 2px;" type="button" id="next" class="btn btn-default btn-xs pageTip">
      		下一页
   		</button>
   		<button style="height: 21px; padding-top: 1px; padding-bottom: 2px;" type="button" id="nexts" class="btn btn-default btn-xs pageTip">
      		尾页
   	    </button>
    	</div>
	</div>
</div>
	</div>
	</div>
</div> 
<script type="text/javascript">
$(document).ready(function(){
    $("#datepicker").datepicker({
		format : 'yyyy-mm',
		minViewMode : 1,
		language : "zh-CN",
		autoclose : true
	});
});
</script>
<style>
*{margin:0;padding:0;}
#pageDiv{text-align:right;padding-right:20px;margin-top:20px;border-top:1px solid #ddd;}
.pageNum{color:#00f;margin:0 5px;}
.pageTip{color:#00f;cursor:pointer;margin-left:25px;}
td {
	text-align: center !important;
}
</style>
