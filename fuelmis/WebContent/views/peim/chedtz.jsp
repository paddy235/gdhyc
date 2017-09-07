<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
   <div class="block" ng-controller="chedtzCtrl">
    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{chedtzTitle}}</div>
    </div>
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label  style="width: 80px;float: left;" class="control-label span1">运输车队:</label>
				<select style="width: 120px;float: left;" ng-model="search.peimeidanwid"  ng-options="option.value as option.name for option in YunscdComboList"></select>
				<label style="width: 45px;float:left;" class="control-label">日期:</label>
				<input style="width: 150px;float: left;" id="datepicker" ng-model="search.riq"  type="text">
				<button style="margin-left: 5px;float: left;"  id="refresh" ng-click="refresh()" class="btn btn-success"><i class=" icon-refresh"></i>查询</button>
				<button style="margin-left: 10px;float: left;"  id="save" ng-click="save()" class="btn btn-primary"><i class="icon-plus"></i>保存</button>
				
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div width="100%" height="100px" align="center"><h2>{{search.shij}}车队调运通知</h2></div>
		<div width ="100%" align="center">
			<div width="90%">
				<table class="table">
					<tr>
						<th width="5%">序号</th>
						<th width="15%">运输单位</th>
						<th width="15%">煤源</th>
						<th width="10%">车数</th>
						<th width="15%">配煤单位</th>
						<th width="40%">备注</th>
					</tr>
					<tr ng-repeat="data in items.data track by $index">
				        <td width="5%" rowspan="data.rowDetails.length">{{$index+1}}</td>
				        <td width="15%" rowspan="data.rowDetails.length"><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.rowData.CHEDMC"></td>
				        <td colspan="4">
				        	<table>
					        	<tr ng-repeat="hval in data.rowDetails track by $index">
					        		<td width="15%" ><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="hval.MEIYMC"></td>
							        <td width="10%" > <input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="hval.CHES"></td>
							        <td width="15%" ><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="hval.MINGC"></td>
							        <td width="40%" ><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="hval.BEIZ"></td>
					        	</tr>
				        	</table>	
				        </td>
				      </tr>
				</table>
			</div>
		</div>
		<table></table>
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
