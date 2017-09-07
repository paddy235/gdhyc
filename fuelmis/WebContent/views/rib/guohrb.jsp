<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="tab-pane" ng-controller="guohrbCtrl">
	<div class="block-content collapse in">
        <div class="span12">
        <form id="ruczlAdd_form" class="form-horizontal">
        	<fieldset>
				<label class="control-label" style="width:70px;margin-right:5px;">过衡日期:</label>
			 	<input id="datepicker1" ng-change="refresh()" ng-model="search.date" type="text" style="float:left;width:150px;">
				<label class="control-label" style="width:35px;margin-left:10px;margin-right:5px;">单位:</label>
			 	<select id="selectType" style="float:left;width:150px;" ng-model="search.diancid"  ng-options="option.value as option.name for option in diancList"></select>
                 <button style="margin-left: 20px;" ng-click="refresh()" class="btn btn-success">
                 	<i class="icon-search icon-white"></i> 刷新
                 </button>
                 <button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
                 	<i class="icon-print icon-white"></i> 打印
                 </button>
                 <button style="margin-left: 10px;" my-export="过衡日报.xls" class="btn btn-primary">
                 	<i class="icon-download-alt icon-white"></i> 导出
                 </button>
         </fieldset>  
        </form>
        </div>
		<div class="row-fluid" id="report"></div>
		<div id="pagination_box" class="pagination pagination-right" style="width:100%;margin-left: auto;margin-right: auto;">
			<ul id="pagination_zc" ></ul>
		</div>
    </div>
        
</div>

<script type="text/javascript">
$(document).ready(function(){    

});
</script>