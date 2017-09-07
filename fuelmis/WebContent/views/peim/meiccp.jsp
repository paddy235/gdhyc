<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
   <div class="block" ng-controller="meiccpCtrl">
    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{meiccpTitle}}</div>
    </div>
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label  style="width: 80px;float: left;" class="control-label span1">配煤单位:</label>
				<select style="width: 120px;float: left;" ng-model="search.peimeidanwid" ng-options="option.value as option.name for option in peimdwlist"></select>
				<label style="width: 45px;float:left;" class="control-label">日期:</label>
				<input style="width: 150px;float: left;" id="datepicker" ng-model="search.riq" type="text">
				<button style="margin-left: 5px;float: left;"  id="refresh" ng-click="refresh()" class="btn btn-success"><i class="icon-refresh"></i>查询</button>
				<button style="margin-left: 5px;float: left;"  id="save" ng-click="save()" class="btn btn-primary"><i class="icon-plus"></i>保存</button>
				<button style="margin-left: 5px;float: left;"  id="add" ng-click="add()"  class="btn btn-primary"><i class="icon-tasks"></i>添加</button>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div width="100%" height="100px" align="center"><h2>煤场掺配调用单</h2></div>
		<div width ="100%" align="center">
			<div width="90%">
				<table class="table">
					<tr>
						<th width="5%"></th>
						<th width="10%">配煤单位</th>
						<th width="10%">煤源</th>
						<th width="10%">车数</th>
						<th width="10%">到厂量预估</th>
						<th width="10%">实际到厂量</th>
						<th width="10%">卸煤煤山</th>
						<th width="10%">进煤日期</th>
						<th width="10%">删除</th>
					</tr>
					<tr ng-repeat="data in items.data track by $index">
						<td width="5%" align="center"><input class="checkedall" id={{$index}} ng-click="checkit($event.target)" type="checkbox"></td>
				        <td width="10%" rowspan="data.rowDetails.length"><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.rowData.MINGC"></td>
				        <td colspan="7" width="70%">
				        	<table>
				        		<tr ng-repeat="hval in data.rowDetails track by $index">
					        		<td width="14%">
					        		 <select style="width: 100%;float: left;" ng-model="hval.MEIYB_ID"  ng-options="option.value as option.name for option in meiyuanlist"></select>
					        		</td>
							        <td width="14%"><input style="width: 70%;border-style:none;" type="text" ng-model="hval.CHES"></td>
							        <td width="14%"><input style="width: 70%;border-style:none;"  type="text"  ng-model="hval.DAOCLYG" ng-change=""></td>
							        <td width="14%"><input style="width: 70%;border-style:none;"  type="text"  ng-model="hval.SHIJDCL" ng-change=""></td>
							        <td width="14%">
							        <select style="width: 100%;float: left;" ng-model="hval.MEISB_ID"  ng-options="option.value as option.name for option in xiemmslist"></select>
							        </td>
							        <td width="14%"><input style="width: 70%;border-style:none;"  type="text"  ng-model="hval.RIQ" ng-change=""></td>
							        <td width="14%"><button  id={{hval.ID}} ng-click="delete($event.target,data.rowData.ID,$index)" class="btn btn-primary"><i class="icon-plus"></i>删除</button></td>
				        		</tr>
				        	</table>
				        </td>
				        
	    			</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});

</script>
