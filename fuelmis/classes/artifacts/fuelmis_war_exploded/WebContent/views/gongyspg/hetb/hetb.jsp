<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="hetbCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{hetbTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
		 			<fieldset>
		 				<label class="control-label" style="width:70px;margin-right:5px;">日期:</label>
					 	<input id="datepicker1" style="width:120px;float:left" ng-model="search.date" type="text" >
				    	<%--<label class="control-label" style="width:70px;margin-right:5px;">结束时间:</label>
					 	<input id="datepicker2" style="width:120px;float:left" ng-model="search.edate" type="text" >--%>
		        		<label class="control-label" style="width:50px;margin-right:5px;">供应商:</label>
						<select id="selectType" style="width:120px;" ng-model="search.gongysb_id"  ng-options="option.value as option.name for option in gongysList">
						</select>
		                <button style="margin-left:20px;" ng-click="refresh()" class="btn btn-success"><i class="icon-search icon-white"></i> 刷新</button>
		              	<button style="margin-left:10px;" ng-click="addHetb()" class="btn btn-primary"><i class="icon-plus icon-white"></i> 添加</button>	              
		                <button style="margin-left:10px;" ng-click="editHetb()" class="btn btn-info"><i class="icon-edit icon-white"></i> 修改</button>
		                <button style="margin-left:10px;" ng-click="delHetb()" class="btn btn-danger"><i class="icon-trash icon-white"></i> 删除</button>
		 			</fieldset>
				</form>
			</div>
		</div>
<%--		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" id="example">
				<thead>
					<tr>
						<th style="text-align:center;"></th>
						<th style="text-align:center;">供应商简称</th>
						<th style="text-align:center;">开始时间</th>
						<th style="text-align:center;">结束时间</th>
						<th style="text-align:center;">收到基硫(STAR)</th>
						<th style="text-align:center;">低位热(QNET_AR)</th>
						<th style="text-align:center;">合同量</th>
						<th style="text-align:center;">评分方案</th>
						<th style="text-align:center;">备注</th>
						<th style="text-align:center;">录入人员</th>
						<th style="text-align:center;">录入时间</th>
					</tr>
				</thead>
			</table>
		</div>--%>
        <div class="row-fluid">
            <table class="table table-striped table-bordered table-hover"
                   id="example"
                   my-table="overrideOptions"
                   aa-data="hetArray"
                   ao-column-defs="columnDefs"
                   my-selection=single
            >
                <thead>
                <tr>
                    <th style="text-align: center;width: 2% "></th>
                    <th style="text-align: center;width:10%">供应商简称</th>
                    <th style="text-align: center;width: 10%">开始时间</th>
                    <th style="text-align: center;width:10%">结束时间</th>
                    <th style="text-align: center;width:10%">合同量</th>
                    <th style="text-align:center;width:10%">低位热(QNET_AR)</th>
                    <th style="text-align:center;width:10%">收到基硫(STAR)</th>
                    <th style="text-align:center;width:10%">挥发分(VDAF)</th>
                    <th style="text-align:center;width:10%">全水(MT)</th>
                    <th style="text-align: center;width:10%">评分方案</th>
                    <th style="text-align: center;width:10%">备注</th>
                    <th style="text-align:center;width:10%">录入人员</th>
                    <th style="text-align:center;width:10%">录入时间</th>
                </tr>
                </thead>
            </table>
        </div>
	</div>
    <div class="modal fade" id="myModal_Del" style="display:none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">删除合同</h4>
                </div>
                <div class="modal-body">
                    <div class="block-content collapse in">
                        <div class="span12">
                            确认删除合同？
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" ng-click="deleteHetb()"><i class="icon-ok-sign icon-white"></i> 确认
                    </button>
                    <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>





<script type="text/javascript">
	$(document).ready(function() {
        $("#datepicker1").datepicker({
            format: 'yyyy-mm',
            minViewMode: 1,
            language: "zh-CN",
            autoclose: true
        });
    })
</script>