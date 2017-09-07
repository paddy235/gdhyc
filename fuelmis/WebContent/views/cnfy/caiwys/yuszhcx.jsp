   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="tab-pane" ng-controller="yuszhcxCtrl">
	   <%-- <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{yuszhcxTitle}}</div>
	    </div>--%>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
				<label style="width: 45px;float:left;" class="control-label">年份:</label>
					<input style="width: 60px;float: left;" id="datepicker" ng-model="search.nianf"  ng-change="refresh()" type="text">
					<label style="width:35px;height:30px;line-height:30px;float:left;" class="control-label span1">单位:</label>
				 	<select style="width: 150px;float:left;" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select> 
				 	<label style="width:65px;height:30px;line-height:30px;float:left;"class="control-label span1">费用名称:</label>
				 	<select style="width: 150px;float:left;" ng-model="search.zafid" 
				 		ng-options="option.value as option.name for option in zafList">
				 	</select> 
		            <button style="margin-left: 20px;" class="btn btn-success" ng-click="refresh()" >
		            	<i class=" icon-refresh icon-white"></i> 刷新
		            </button>
				</div>
			</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
	             <thead>
	                 <tr>
	                 	<th style="text-align: center; width: 150px;">单位</th>
	                 	<th style="text-align: center; width: 150px;">厂费名称</th>
	                    <th style="text-align: center; width: 150px;"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></th>
	                    <th style="text-align: center; width: 150px;">年度预算金额</th>
	                    <th style="text-align: center; width: 50px;">1月</th>
	                    <th style="text-align: center; width: 50px;">2月</th>
	                    <th style="text-align: center; width: 50px;">3月</th>
	                    <th style="text-align: center; width: 50px;">4月</th>
	                    <th style="text-align: center; width: 50px;">5月</th>
	                    <th style="text-align: center; width: 50px;">6月</th>
	                    <th style="text-align: center; width: 50px;">7月</th>
	                    <th style="text-align: center; width: 50px;">8月</th>
	                    <th style="text-align: center; width: 50px;">9月</th>
	                    <th style="text-align: center; width: 70px;">10月</th>
	                    <th style="text-align: center; width: 70px;">11月</th>
	                    <th style="text-align: center; width: 70px;">12月</th>
	                    <th style="text-align: center; width: 70px;">合计</th>
	                 </tr>
	             </thead>
	         </table>
	       </div>
		</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
function check(args){
	if($(args).attr("checked")!=undefined){
		$("input[type='checkbox']").attr("checked",false);
		$(args).attr("checked",true);
	}
}
</script>