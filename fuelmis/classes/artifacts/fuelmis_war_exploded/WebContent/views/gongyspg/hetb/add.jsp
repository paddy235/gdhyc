<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
td {
text-align: center !important;
margin: 0px !important;
padding: 0px !important;
}

td input,td select {
width: 100% !important;
height: 37px !important;
margin: 0px !important;
padding: 0px !important;
border: 0px !important;
text-align: center !important;
font-size: 16px !important;
background-color: transparent !important;
line-height: 35px !important;
}
td select{
padding:8px 0 !important;
}
</style>
<div class="block" ng-controller="hetbAddCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{title}}</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form id="renyAdd_form" class="form-horizontal">
                <table  class="table table-striped table-bordered">
                    <tbody>
                    <tr>
                        <td><label class="control-label">供应商:</label></td>
                        <td><select id="" ng-model="hetb.GONGYSB_ID"  ng-options="option.value as option.name for option in gongysList2"></select></td>
                        <td><label class="control-label">开始时间:</label></td>
                        <td><input id="datepicker1" ng-model="hetb.KAISRQ" type="text" style="float: left"></td>
                        <td><label class="control-label">结束日期:</label></td>
                        <td><input id="datepicker2" ng-model="hetb.JIESRQ" type="text" style="float: left"></td>
                    </tr>
                    <tr>
                        <td><label class="control-label">合同量:</label></td>
                        <td><input type="number" ng-model="hetb.HETL" type="text" style="float: left" required/></td>
                        <td><label class="control-label">评分方案:</label></td>
                        <td><select id="selectType" ng-model="hetb.PINGFFAB_ID"  ng-options="option.value as option.name for option in pingffaList"></select></td>
                        <td><label class="control-label">状态:</label></td>
                        <td><select  ng-model="hetb.ZHUANGT"><option value="1">启用</option><option value="0">禁用</option></select></td>
                    </tr>
                    <tr><td><label class="control-label">备注:</label></td><td colspan="5"><input id="zhibmc" ng-model="hetb.BEIZ" type="text" style="float: left"></td></tr>
                    </tbody>
                </table>
                <div class="control-group">
                <div class="pull-left" style="width:100%;padding: 20px 100px 19px 50px;">
                    <button type="button" class="btn btn-primary" ng-click="addZhib()">
                        <i class="icon-plus icon-white"></i> 添加
                    </button>
                </div>
                <div id="table1" style="width:100%;overflow: auto; overflow-y:hidden">
                    <table id="asynTr" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th style="text-align:center;">指标</th>
                            <th style="text-align:center;">值</th>
                            <th style="text-align:center;">单位</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody >
                        <tr ng-repeat="sub in hetb.zhilList track by $index">
                            <td>
                                <select style="width: 100px;" ng-model="sub.ZHIBDM"  ng-options="option.ZHIBDM as option.ZHIBMC for option in zhibList">
                                </select>
                            </td>

                            <td><input type="text" style="width:45px;height:15px" ng-model="sub.ZHIBZ" ></td>
                           <td>
                               <select style="width: 100px;" ng-model="sub.ZHIBDM"  ng-options="option.ZHIBDM as option.ZHIBDW for option in zhibList">
                               </select>
                           </td>
                            <td style="text-align:center;">
                                <button class="btn btn-danger btn-mini" ng-click="deleteZhib($index);">
                                    <i class="icon-remove icon-white" title="删除"></i>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
		</div>
	</div>
	<div style="margin-left: 350px;margin-bottom: 50px;">
		<button type="button" class="btn btn-primary" ng-click="saveHetb()">保存</button>
		<button type="button" class="btn" ng-click="cancel()">返回</button>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){    
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});
</script>