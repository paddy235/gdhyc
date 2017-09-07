   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="tab-pane" ng-controller="niandyusspCtrl">
<%--	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{niandyusspTitle}}</div>
	    </div>--%>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<label style="width:35px;height:30px;line-height:30px;float:left;" class="control-label">年份:</label>
					<input style="width: 100px;float: left;" id="datepicker" ng-model="search.nianf" ng-change="selectriq()" type="text">
					<label class="control-label span1" style="width:35px;height:30px;line-height:30px;float:left;">单位:</label>
				 	<select style="float:left;width:150px;" ng-model="search.diancid" ng-change="selectdianc()" 
				 		ng-options="option.value as option.name for option in diancList">
				 	</select> 
		            <button style="margin-left: 20px;" class="btn btn-success" ng-click="refresh()" >
		            	<i class=" icon-refresh icon-white"></i> 刷新
		            </button>
		            <button style="margin-left: 10px;" id="adddata" class="btn btn-primary" ng-click="addniandcgjh()">
		            	<i class="icon-plus icon-white"></i> 新增
		            </button>
					<button style="margin-left: 10px;" disabled id="updateniandys" ng-click="updateyuedcgjh()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
					<button style="margin-left: 10px;" disabled id="delniandys" ng-click="delniandys()" class="btn btn-danger">
						<i class=" icon-trash icon-white"></i> 删除
					</button>
					<button style="margin-left: 10px;"  class="btn btn-primary" id="submit" ng-click="sumbmitniandyussq()" >
						<i class="icon-file icon-white"></i> 提交
					</button>
				</div>
			</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
	             <thead>
	                 <tr>
	                 	<th style="width: 20px;"></th>
	                 	<th style="text-align: center; width: 50px;">序号</th>
	                 	<th style="text-align: center; width: 150px;">厂内费用名称</th>
	                    <th style="text-align: center; width: 150px;">预算额度（元）</th>
	                    <th style="text-align: center; width: 150px;">说明</th>
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
		$("#delniandys").addClass("btn-primary");
		$("#delniandys").removeAttr("disabled"); //移除disabled属性 
		$("#updateniandys").addClass("btn-primary");
		$("#updateniandys").removeAttr("disabled"); //移除disabled属性 
		$("input[type='checkbox']").attr("checked",false);
		$(args).attr("checked",true);
	}else{
		$("#delniandys").removeClass("btn-primary");
		$("#delniandys").attr('disabled',true);
		$("#updateniandys").removeClass("btn-primary");
		$("#updateniandys").attr('disabled',true);
	}
}
</script>