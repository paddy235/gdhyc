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
    <div class="tab-pane" ng-controller="niandcaigjhaddCtrl">
	   <%-- <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{niandcaigjhaddTitle}}</div>
	    </div>--%>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label">年份:</label>
					<input style="width: 60px;float: left;" id="datepicker" ng-model="search.nianf"  ng-change="selectnianf()" type="text">
					<label style="width: 35px;height: 30px;line-height: 30px;float:left;" class="control-label span1">单位:</label>
				 	<select style="width: 100px;float:left;" ng-model="search.diancid" ng-change="selectdianc()" ng-options="option.value as option.name for option in diancList"></select> 
		            <button style="margin-left: 5px;"  id="refresh" class="btn btn-success" ng-click="refresh()">
		            	<i class=" icon-refresh icon-white"></i> 刷新
		            </button>
		            <button   class="btn btn-primary" id="adddata" name={{search.state}} ng-click="addniandcaigjh()">
		            	<i class="icon-plus icon-white"></i> 新增
		            </button>
					<button  disabled id="update" ng-click="updateniandcgjh()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
					<button  disabled id="delete"  ng-click="delniandcaig()" class="btn btn-danger">
						<i class=" icon-trash icon-white"></i> 删除
					</button>
					<button  class="btn btn-primary" id="copyniandcaigjh" ng-click="copyniandcgjh()" >
						<i class="icon-file icon-white"></i> 复制上年计划
					</button>
				</div>
			</div>
			
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example"  >
	             <thead>
	                 <tr>
	                   	<th style="width:20px;"></th>
	                 	<th>序号</th>
	                 	<th>供货单位</th>
	                    <th>计划口径</th>
	                    <th>合同数量(吨)</th>
	                    <th>合同热值（MJ/Kg）</th>
	                    <th>合同煤价（元/吨）</th>
	                    <th>合同运价（元/吨）</th>
	                    <th>预计到货量（吨）</th>
	                    <th>预计到货热值（MJ/Kg）</th>
	                    <th>预计到货煤价(元/吨)</th>
	                    <th>预计到货运价(元/吨)</th>
	                    <th>预计其他费用(元/吨)</th>
	                    <th>预计其他费用(不含税)(元/吨)</th>
	                    <th>预计到厂价(元/吨）</th>
	                    <th>预计到厂标煤单价（元/吨）</th>
	                    <th >预计到厂不含税标煤单价(元/吨)</th>
	                 </tr>
	             </thead>
	         </table>
	       </div>
	       
		</div>
	<!-- END FORM-->
</div>

<script type="text/javascript">
  function check(args){
     	if($("#adddata").attr("name")=='0'){
    		if($(args).attr("checked")!=undefined){
    			$("#update").addClass("btn-info");
    			$("#update").attr('disabled', false);
    			$("#delete").addClass("btn-danger");
    			$("#delete").attr("disabled",false); //移除disabled属性	
    			$("input[type='checkbox']").attr("checked",false);
    			$(args).attr("checked",true);
    		}else{
    			$("#delete").removeClass("btn-danger");
    			$("#delete").attr('disabled',true);
    			$("#update").removeClass("btn-info");
    			$("#update").attr('disabled', true);
        	}
    	}
	} 
</script>