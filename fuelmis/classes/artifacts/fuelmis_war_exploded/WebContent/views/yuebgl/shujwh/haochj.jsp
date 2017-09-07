<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<style>
	.ui-datepicker-calendar {
		display: none;
	}

	/*#example th,td{white-space: nowrap !important;padding:5px 15px!important;}*/
	#example tbody td input{
		color: #00aa0c;
	}
	#example tbody td{
		text-align: left;
	}
	#example td {
		text-align: center !important;
		margin: 0px !important;
		padding: 0px !important;
	}

	#example td input,td select {
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
	#example thead th{
		text-align: center !important;
	}
	#example td select{
		padding:8px 0 !important;
	}

</style>
<div class="tab-pane" ng-controller="haochjCtrl">
	<div class="block-content collapse in ">
		<div class="span12" style="height: 42px;">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:50px;margin-right:5px;" class="control-label">时间:</label> <input
						id="datepicker1" type="text" style="float: left; width: 120px;margin-right:5px;margin-left:5px;"
						ng-model="search.riq"><label style="width:40px;margin-right:5px;"
													 class="control-label">单位:</label> <select
						ng-model="search.diancid" style="float: left; width: 120px;"
						ng-options="option.value as option.name for option in diancList"></select>
					<button style="margin-left: 20px;" ng-click="searchData()"
							class="btn btn-success"><i class="icon-search icon-white"></i>刷新
					</button>
					<button style="margin-left: 10px;" ng-click="createData_Win()" class="btn" id="createBtn">生成
					</button>
					<button style="margin-left: 10px;" ng-click="delData_Win()" class="btn" id="delBtn">删除</button>
					<button style="margin-left: 10px;" ng-click="saveData()" class="btn" id="saveBtn">保存</button>
					<button style="margin-left: 10px;" ng-click="printTable()" class="btn" id="printBtn">打印</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" id="example">
				<thead>
				<tr>
					<th >分项</th><th >期初库存</th><th >净重</th><th >发电用</th><th >供热用</th><th >其他用</th><th >实际储损</th><th >调出量</th><th >水分差调整</th><th >库存</th><th >允许储损</th>
				</tr>
				</thead>
				<tbody>
				<tr ng-repeat="sub in haochjList track by $index">
					<td style="text-align: center;">{{sub.fenx}}</td>
					<td >{{sub.qickc}}</td>
					<td >{{sub.shouml}}</td>
					<td ><input type="text"  ng-model="sub.fady"></td>
					<td ><input type="text"  ng-model="sub.gongry"></td>
					<td ><input type="text"  ng-model="sub.qith"></td>
					<td ><input type="text"  ng-model="sub.sunh"></td>
					<td ><input type="text"  ng-model="sub.diaocl"></td>
					<td ><input type="text"  ng-model="sub.shuifctz"></td>
					<td >{{sub.kuc=sub.qickc+sub.shouml-sub.fady-sub.gongry-sub.qith-sub.sunh-sub.diaocl-sub.shuifctz| number : 0}}</td>
					<td ><input type="text"  ng-model="sub.runxcs"></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="myModal_Create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		 style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">生成数据</h4>
				</div>
				<div class="modal-body">
					<div class="block-content collapse in">
						<div class="span12">
							生成数据将覆盖原有数据，是否生成？
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" ng-click="createData()"><i
							class="icon-ok-sign icon-white"></i> 确认
					</button>
					<button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		 style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">删除数据</h4>
				</div>
				<div class="modal-body">
					<div class="block-content collapse in">
						<div class="span12">
							数据删除后无法还原，确认删除？
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" ng-click="delData()"><i
							class="icon-ok-sign icon-white"></i> 确认
					</button>
					<button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $(document).ready(function () {
            $("#datepicker1").datepicker({
                format: 'yyyy-mm',
                minViewMode: 1,
                language: "zh-CN",
                autoclose: true
            });
        });
    });
</script>