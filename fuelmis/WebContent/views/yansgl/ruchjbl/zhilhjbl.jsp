
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="/fuelmis/css/table.css">
<style type="text/css">
 #example th,td{white-space: nowrap !important;padding:5px 15px!important;}
</style>
<div class="block" ng-controller="zhilhjblCtrl" >
	<div class="block-content collapse in" id="diaodjhList">
	<!-- 查询条件栏 -->
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
			 		<fieldset>
					    <div class="row-fluid" style="margin-bottom:20px;">
			        		<label class="control-label" style="float:left;width:70px;margin-right:5px;">开始时间:</label>
						 	<input class="datepicker" ng-model="search.sDate" style="width:80px;float:left;" type="text" style="float:left" ng-change="load()">
					    	<label class="control-label" style="float:left;width:70px;margin-right:5px;margin-left:10px;">结束时间:</label>
						 	<input class="datepicker" ng-model="search.eDate" style="width:80px;float:left;" type="text" style="float:left" ng-change="load()">
			        		<label class="control-label" style="float:left;width:35px;margin-right:5px;margin-left:20px;">电厂:</label>
						 	<select id="selectType" ng-model="search.diancid" style="width:120px;float:left;" ng-options="option.value as option.name for option in diancList" ng-change="load()"></select>
			        		<label class="control-label" style="float:left;width:50px;margin-right:5px;margin-left:20px;">供应商:</label>
						 	<select id="selectType" ng-model="search.gongysid" style="width:120px;float:left;" ng-options="option.value as option.name for option in gongysList" ng-change="load()"></select>
							<button style="margin-left: 20px;" ng-click="load()" class="btn btn-success"><i class="icon-search icon-white"></i> 刷新</button>				        
					    </div>
					</fieldset>
				</form>
			</div>
		</div>
	<!-- 表格	 -->
		<div class="row-fluid" style="width: 100%;height: 100%;overflow: auto;">
			<table  class="table table-bordered table-condensed table-hover" 
			my-table="overrideOptions"
            aa-data="dataList"
        ao-column-defs="columnDefs"
        fn-row-callback="myCallback"
				id="example">
				<thead>
					<tr>
						<th style=" width: 2.6% !important;"></th>
						<th style=" width: 2.6% !important;">化验时间</th>
						<th style=" width: 2.6% !important;">化验员</th>
						<th style=" width: 2.6% !important;">化验录入员</th>
						<th style=" width: 2.6% !important;">化验备注</th>
						<th style=" width: 2.6% !important;">操作时间</th>
						<th style=" width: 2.6% !important;">操作人员</th>
						<th style=" width: 2.6% !important;">收到基灰分Aar(%)</th>
						<th style=" width: 2.6% !important;">干燥基灰分Ad(%)</th>
						<th style=" width: 2.6% !important;">干燥无灰基挥发分Vdaf(%)</th>
						<th style=" width: 2.6% !important;">全水分Mt(%)</th>
						<th style=" width: 2.6% !important;">空气干燥基全硫St,ad(%)</th>
						<th style=" width: 2.6% !important;">空气干燥基灰分Aad(%)</th>
						<th style=" width: 2.6% !important;">空气干燥基水分Mad(%)</th>
						<th style=" width: 2.6% !important;">空气干燥基弹筒热值Qb,Ad(Mj/kg)</th>
						<th style=" width: 2.6% !important;">空气干燥基氢Had(%)</th>
						<th style=" width: 2.6% !important;">空气干燥基挥发分Vad(%)</th>
						<th style=" width: 2.6% !important;">固定碳FCad(%)</th>
						<th style=" width: 2.6% !important;">干燥基全硫St,d(%)</th>
						<th style=" width: 2.6% !important;">空气干燥基高位热值Qgr,ad(Mj/kg)</th>
						<th style=" width: 2.6% !important;">干燥无灰基氢Hdaf(%)</th>
						<th style=" width: 2.6% !important;">干燥无灰基高位热值Qgr,daf(Mj/kg)</th>
						<th style=" width: 2.6% !important;">干燥无灰基全硫Sdaf(%)</th>
						<th style=" width: 2.6% !important;">var</th>
						<th style=" width: 2.6% !important;">T1</th>
						<th style=" width: 2.6% !important;">T2</th>
						<th style=" width: 2.6% !important;">T3</th>
						<th style=" width: 2.6% !important;">T4</th>
						<th style=" width: 2.6% !important;">har</th>
						<th style=" width: 2.6% !important;">干燥基高位热值Qgr,d(Mj/kg)</th>
						<th style=" width: 2.6% !important;">收到基硫(%)</th>
						<th style=" width: 2.6% !important;">收到基低位热量Qnet,Ar(Mj/kg)</th>
						<th style=" width: 2.6% !important;">审核状态</th>
						<th style=" width: 2.6% !important;">电厂名称</th>
						<th style=" width: 2.6% !important;">化验编码</th>
						<th style=" width: 2.6% !important;">单据类型</th>
						<th style=" width: 2.6% !important;">业务环节</th>
						<th style=" width: 2.6% !important;">操作类型</th>
						<th style=" width: 2.6% !important;">操作人ip</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$(".datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	});
	
	function check(args){
		if($(args).attr("checked")!=undefined){
			$("input[type='checkbox']").attr("checked",false);
			$(args).attr("checked",true);
		}
	}
	
	function deleteCaigddb(){
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	
		    	$.post("hetgl/caigddb/beforedelCaigddb",{id:$(this).attr("id")},function(data){
		    		if(data.relust=="true"){
				    	$.post("hetgl/caigddb/delCaigddb",{id:$(this).attr("id")},function(data){
							$('#myModal_Del').modal('hide');
							alert("删除成功！");
							oTable.fnReloadAjax('hetgl/caigddb/getCaigddbList');
						},"json");		
		    		}else{
						$('#myModal_Del').modal('hide');
						alert("删除失败当前订单已有关联数据！");		    			
		    		}
				},"json")
		    	

		    }
		});  
	}
</script>