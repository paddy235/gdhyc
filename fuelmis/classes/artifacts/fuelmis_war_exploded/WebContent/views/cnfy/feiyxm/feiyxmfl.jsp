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
    <div class="tab-pane" ng-controller="feiyxmflpageCtrl">
<%--	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{feiyxmflTitle}}</div>
	    </div>--%>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
		            <button style="margin-left: 10px;" id="adddata" class="btn btn-primary" ng-click="addfeiyxmfl()">
		            	<i class="icon-plus icon-white"></i> 新增
		            </button>
					<button style="margin-left: 10px;" disabled id="updatefeiyxmfl" ng-click="updatefeiyxmfl()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
					<button style="margin-left: 20px;" disabled id="changestate" class="btn btn-danger" ng-click="changestate()" >
						<i class=" icon-refresh icon-white"></i> 修改状态
					</button>
				</div>
			</div>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover" id="example">
		             <thead>
		                 <tr>
		                 	<th style="width: 20px;"></th>
		                 	<th style="text-align: center; width: 50px;">序号</th>
		                 	<th style="text-align: center; width: 150px;">编码</th>
		                    <th style="text-align: center; width: 150px;">名称</th>
		                    <th style="text-align: center; width: 150px;">说明</th>
		                    <th style="text-align: center; width: 150px;">分类口径</th>
		                    <th style="text-align: center; width: 150px;">状态</th>
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
		$("#changestate").addClass("btn-danger");
		$("#changestate").removeAttr("disabled"); //移除disabled属性 
		$("#updatefeiyxmfl").addClass("btn-info");
		$("#updatefeiyxmfl").removeAttr("disabled"); //移除disabled属性 
		$("input[type='checkbox']").attr("checked",false);
		$(args).attr("checked",true);
	}else{
		$("#changestate").removeClass("btn-danger");
		$("#changestate").attr('disabled',true);
		$("#updatefeiyxmfl").removeClass("btn-info");
		$("#updatefeiyxmfl").attr('disabled',true);
	}
}
</script>