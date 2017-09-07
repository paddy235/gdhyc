<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.ui-datepicker-calendar {
	display: none;
}

th {
	text-align: center !important;
}

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
.img{
margin-top:5px;
width : 30px !important;
}
</style>
<div class="tab-pane" ng-controller="meihysisCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<!-- 日期 -->
				<label style="width:35px;height:30px;line-height:30px;float:left;" class="control-label span1">日期:</label> 
				<input style="width:120px; float: left;" id="datepicker" ng-model="riq" ng-change="load()" type="text">
				<label style="width:60px; margin-right:5px;margin-left:20px;height: 30px; line-height: 30px; float: left;"
				class="control-label">单位名称:</label> 
				<select style="width: 120px; float: left;" id="meik" ng-model="DIANCXXB_ID" ng-change="load()"
				ng-options="option.value as option.name for option in diancList">
				<!-- <option value="{{dianc.value}}"  ng-repeat="dianc in diancList">{{dianc.name}}</option> -->
				</select>
				<button style="margin-left: 20px;" class="btn btn-primary" id="adddata" ng-click="getMeihyAll()">
					<i class="icon-plus icon-white"></i> 获取
				</button>
				<button style="margin-left: 5px;" id="refresh" ng-click="load()" class="btn btn-success">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
				<button style="margin-left: 10px;" class="btn btn-danger" id="Del" ng-click="DelMeihysis()">
					<i class="icon-trash icon-white"></i> 删除
				</button>
				<button style="margin-left: 10px;" class="btn btn-success" id="save" ng-click="MeihyAdd()">
					<i class="icon-check icon-white"></i> 保存
				</button>
			</div>
		</div>
		<form id="form1" runat="server">
			<div class="row-fluid">
				<table id="meihy" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 5%;"></th>
							<th style="width: 10%;">入炉班组</th>
							<th style="width: 10%;">入炉机组</th>
							<th style="width: 10%;">开始点</th>
							<th style="width: 10%;">结束点</th>
							<th style="width: 10%;">获取煤量</th>
							<th style="width: 10%;">入炉煤量</th>
							<th style="width: 10%;">差值</th>
							<th style="width: 10%;">发电耗用</th>
							<th style="width: 10%;">供热耗用</th>
							<th style="width: 10%;">其他</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="data in list track by $index" class="option">
							<td width="5%" align="center"><input class="checkedall" id={{data.ID}}  type="checkbox"></td>
							<!-- 入炉班组 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.BANZ"></td>
							<!-- 入炉机组 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.JIZ"></td>
							<!-- 开始点 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ></td>
							<!-- 结束点 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ></td>
							<!-- 获取煤量 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ></td>
							<!-- 入炉煤量 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ></td>
							<!-- 差值-->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ></td>
							<!-- 发电耗用 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIL"></td>
							<!-- 供热耗用 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.GONGRHY"></td>
							<!-- 其他 -->
							<td><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QITY"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<!-- END FORM-->
	
	<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" style="display:none;" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		            <h4 class="modal-title" id="myModalLabel">删除煤耗用sis</h4>
		         </div>
		         <div class="modal-body">
		            <div class="block-content collapse in">
						<div class="span12">
							确认删除？
						</div>
				 	</div>
		         </div>
		         <div class="modal-footer">
		          	<button type="button" class="btn btn-danger" ng-click="DelMeihys()"><i class="icon-ok-sign icon-white"></i> 确认
		            </button>
		            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
		            </button>
		         </div>
		      </div>
		   </div>
		</div>
	
</div>

<script type="text/javascript">
</script>
