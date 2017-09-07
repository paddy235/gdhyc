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

<div class="tab-pane" ng-controller="ritspfCtrl">
	<%--<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{rizbpfTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label class="control-label" style="float:left;width:50px;margin-right:5px;margin-left:20px;">供应商:</label>
					<select id="selectType" ng-model="search.gongysid" style="width:120px;float:left;" ng-options="option.value as option.name for option in gongysList" ng-change="load()"></select>
			       
					<label style="width: 80px;margin-right:5px;" class="control-label">开始日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width: 15px;margin:0 5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
					<button style="margin-left: 5px;" id="refresh" ng_click="refresh()" class="btn btn-success">
						<i class=" icon-refresh icon-white"></i> 刷新
					</button>
					<button style="margin-left: 5px;" id="delete" ng_click="delete()" class="btn btn-danger">
						<i class=" icon-refresh icon-white"></i> 删除
					</button>
					<button style="margin-left: 5px;" id="save" ng_click="save()" class="btn btn-info">
						<i class=" icon-refresh icon-white"></i> 保存
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="width: 20px;text-align:center;">
							全选 <br>
							<input type="checkbox" ng-model="selectAll">
						</th>
						<th style="text-align: center; width: 50px;vertical-align: middle">序号</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">供应商名称</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">来煤日期</th>
						<th style="text-align: center; width: 100px;vertical-align: middle">特殊加分</th>
						<th style="text-align: center; width: 150px;vertical-align: middle">加分说明</th>
					</tr>
				</thead>
				<tbody ng-click="fun()" id="page" >
                        <tr ng-repeat="data in datalist ">
                            <td style="width: 20px;text-align:center;"><input type="checkbox" ng-checked="selectAll"></td>
                            <td style="text-align:center;">{{$index+1}}</td>
                            <td style="text-align:center;">{{data.MINGC}}</td><!-- 供应商名称 -->
                            <td style="text-align:center;">{{data.RIQ}}</td><!-- 来煤日期 -->
                            <td style="text-align:center;"><input type="number" ng-model="data.JIAF"></td><!-- 特殊加分 -->
                            <td style="text-align:center;"><input type="text" ng-model="data.BEIZ"></td><!-- 加分说明 -->
                        </tr>
                    </tbody>
                </table>
            </div>
        <my-page data="datalist"></my-page>
        <!-- END FORM-->
    </div>
</div>
<script type="text/javascript">
	//日期插件
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
		
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
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
</script>