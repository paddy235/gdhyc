<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<div class="tab-pane" ng-controller="rigmjhCtrl">
    <%--	<div class="navbar navbar-inner block-header">
            <div class="muted pull-left">{{rigmjhTitle}}</div>
        </div>--%>
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">

                <label
                        style="width: 70px; margin-right: 5px; margin-left: 20px; height: 30px; line-height: 30px; float: left;"
                        class="control-label">发布状态:</label> <select
                    style="width: 100px; float: left;" ng-model="zhuangt">
                <option value=0>未发布</option>
                <option value=1>发布</option>
            </select> <label
                    style="width: 60px; margin-right: 5px; margin-left: 20px; height: 30px; line-height: 30px; float: left;"
                    class="control-label ">供应商:</label> <select
                    style="width: 100px; float: left;" ng-model="gongysb_id"
                    ng-options="option.value as option.name for option in gongysList">
            </select>
             <select style="margin-left: 12px;width: 100px; float: left;" ng-model="fabrq"
                              ng-options="option.fabrq as option.fabrq for option in fabrqList">
            </select> 
            <label
                    style="width: 70px; height: 30px; line-height: 30px; float: left;"
                    class="control-label span1">开始时间:</label> 
            <input
                    style="width: 120px; float: left;" id="datepicker1"
                    ng-model="strdate" type="text"> 
            <label
                    style="width: 70px; height: 30px; line-height: 30px; float: left;"
                    class="control-label span1">结束时间:</label> 
            <input
                    style="width: 120px; float: left;" id="datepicker2"
                    ng-model="enddate" type="text">
                </div>
        	</div>
                    
            <div class="span12" style="margin-bottom:10px">
                <button style="float: left;" class="btn btn-success"
                        id="refresh" ng-click="refresh()">
                    <i class="icon-plus icon-white"></i> 刷新
                </button>
                <button style="margin-left: 10px;float: left;" class="btn btn-primary" id="add"
                        ng-click="addRigmjh()">
                    <i class="icon-plus icon-white"></i> 添加
                </button>
                <button style="margin-left: 10px;float: left;" class="btn btn-danger"
                        id="delete" ng-click="delRigmjh()">
                    <i class="icon-plus icon-white"></i> 删除
                </button>
                <button style="margin-left: 10px;float: left;" class="btn btn-info" id="update"
                        ng-click="saveRigmjh()">
                    <i class="icon-plus icon-white"></i> 保存
                </button>
                <button style="margin-left: 10px;float: left;" class="btn btn-primary"
                        id="publish" ng-click="publishRigmjh()">
                    <i class="icon-plus icon-white"></i> 发布
                </button>
        </div>
        <form id="form1" runat="server">
            <div class="row-fluid" style="overflow-x: auto;">
                <table class="table table-striped table-bordered table- hover"
                       id="example">
                    <thead>
                    <tr>
                        <th style="width: 40px;" rowspan="2">全选 <br> <input
                                type="checkbox" ng-model="selectAll" id="selectAll">
                        </th>
                        <th width="10%">供应商</th>
                        <th width="10%">计划日期</th>
                        <th width="10%">计划煤量</th>
                        <th width="10%">调整量</th>
                        <th width="10%">方案名称</th>
                        <th width="10%">发布日期</th>
                        <th width="10%">发布人员</th>
                        <th width="10%">操作</th>

                    </tr>
                    </thead>

                    <tbody>
                    <tr ng-repeat="row in list.listArray track by $index">
                        <td style="width: 20px;"><input type="checkbox"
                                                        ng-checked="selectAll" id="{{$index}}"></td>
                        <!--供应商 -->
                        <td width="10%"><input type="text" ng-model="row.GONGYSBMC"
                                               readonly="readonly"/></td>
                        <td width="10%"><input type="text" ng-model="row.RIQ"
                                               readonly="readonly"/></td>
                        <td width="10%"><input type="text" ng-model="row.JIHML"/></td>
                        <td width="10%"><input type="text" ng-model="row.TIAOZL"/></td>

                        <td width="10%"><select class="kuczz"
                                                ng-model="row.PINGFFAB_ID"
                                                ng-options="option.value as option.name for option in pingffaList">
                        </select></td>
                        <td width="10%"><input type="text" ng-model="row.FABRQ"
                                               readonly="readonly"/></td>
                        <td width="10%"><input type="text" ng-model="row.FABRY"
                                               readonly="readonly"/></td>

                    </tr>
                    <tr ng-repeat="row in array track by $index">
                        <td style="width: 20px;"><input type="checkbox"
                                                        ng-checked="selectAll" id="{{$index}}"></td>
                        <td width="10%"><select class="kuczz"
                                                ng-model="row.GONGYSB_ID"
                                                ng-options="option.value as option.name for option in gongysList">
                        </select></td>

                        <td width="10%"><input type="text" ng-model="row.RIQ"/></td>
                        <td width="10%"><input type="text" ng-model="row.JIHML"/></td>
                        <td width="10%"><input type="text" ng-model="row.TIAOZL"/></td>

                        <td width="10%"><select class="kuczz"
                                                ng-model="row.PINGFFAB_ID"
                                                ng-options="option.ivalue as option.name for option in pingffaList">
                        </select></td>
                        <td width="10%"><input type="text" ng-model="row.FABRQ"/></td>
                        <td width="10%"><input type="text" ng-model="row.FABRY"/></td>

                        <td width="10%" id="{{$index}}" class="insert"><img
                                ng-click="del($event.target)" class="img"
                                src="/fuelmis/images/kucgl/delete.png"/></td>

                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>


    <div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog"
         style="display: none;" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">删除日供煤计划</h4>
                </div>
                <div class="modal-body">
                    <div class="block-content collapse in">
                        <div class="span12">确认删除？</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger"
                            ng-click="DelRigmjhs()">
                        <i class="icon-ok-sign icon-white"></i> 确认
                    </button>
                    <button type="button" class="btn" data-dismiss="modal">
                        <i class="icon-remove-circle"></i> 取消
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $("#datepicker1").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });
        $("#datepicker2").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });

    });
</script>