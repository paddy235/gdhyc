<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="caiyzmdCtrl">
	<div class="block-content collapse in">
        <div class="span12">
	        <form id="ruczlAdd_form" class="form-horizontal">			
			 	<fieldset>
		        	<div class="control-group">
						<label class="control-label" style="margin-right:5px;width:140px;float:left;">到货日期或入炉日期:</label>
					 	<input id="datepicker1" ng-change="refresh()" ng-model="search.date" type="text" style="width:150px;float: left">
		                 <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success">
		                 	<i class="icon-search icon-white"></i> 刷新
		                 </button>
		                 <button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
		                 	<i class="icon-print icon-white"></i> 打印
		                 </button>
		                 <button style="margin-left: 10px;" my-export="采样转码单.xls" class="btn btn-primary">
		                 	<i class="icon-download-alt icon-white"></i> 导出
		                 </button>
				    </div>	    
			 	</fieldset>
	        </form>
        </div>
		<div class="row-fluid" id="report"></div>
		<div class="pagination pagination-right" id="pagination" style="display:none;"></div>
   </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
    $("#pagination").twbsPagination({
        first: '首页',
        prev: '前一页',
        next: '下一页',
        last: '尾页',
        totalPages: 10,
        visiblePages: 7,
        onPageClick: function (event, page) {  
            //callback
        }  
	});
    

});
</script>