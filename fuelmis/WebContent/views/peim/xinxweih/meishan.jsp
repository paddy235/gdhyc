   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="meishanCtrl">
	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{meishanTitle}}</div>
	    </div>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
		            <button style="margin-left: 10px;" id="adddata" class="btn btn-primary" ng-click="addfeiyxmfl()"><i class="icon-plus"></i>新增</button>
					<button style="margin-left: 10px;" disabled id="updatefeiyxmfl" ng-click="updatefeiyxmfl()" class="btn"><i class="icon-pencil"></i>修改</button>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" id="example">
             <thead>
                 <tr>
                 	<th style="width: 20px;"></th>
                 	<th style="text-align: center; width: 50px;">序号</th>
                 	<th style="text-align: center; width: 150px;">煤山名称</th>
                    <th style="text-align: center; width: 150px;">所属单位</th>
                    <th style="text-align: center; width: 150px;">煤场</th>
                    <th style="text-align: center; width: 150px;">热值</th>
                    <th style="text-align: center; width: 150px;">硫分</th>
                    <th style="text-align: center; width: 150px;">挥发分</th>
                 </tr>
             </thead>
         </table>
       </div>
			<!-- END FORM-->
</div>
<script type="text/javascript">
function check(args){
	if($(args).attr("checked")!=undefined){
		$("#changestate").addClass("btn-primary");
		$("#changestate").removeAttr("disabled"); //移除disabled属性 
		$("#updatefeiyxmfl").addClass("btn-primary");
		$("#updatefeiyxmfl").removeAttr("disabled"); //移除disabled属性 
		$("input[type='checkbox']").attr("checked",false);
		$(args).attr("checked",true);
	}else{
		$("#changestate").removeClass("btn-primary");
		$("#changestate").attr('disabled',true);
		$("#updatefeiyxmfl").removeClass("btn-primary");
		$("#updatefeiyxmfl").attr('disabled',true);
	}
}
</script>