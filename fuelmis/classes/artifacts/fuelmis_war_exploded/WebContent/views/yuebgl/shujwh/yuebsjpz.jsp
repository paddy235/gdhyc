<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<style>
    #example th {
        text-align: center !important;
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
    #example td select{
        padding:8px 0 !important;
    }
</style>
<div class="tab-pane" ng-controller="yuebsjpzCtrl">
    <div class="block-content collapse in ">
        <div class="span12" style="height: 42px;">
            <div class="table-toolbar">
                <button style="margin-left: 20px;" ng-click="search()" class="btn btn-success">
                    <i class="icon-search icon-white"></i>刷新
                </button>
                <button style="margin-left: 10px;" ng-click="add()" class="btn btn-primary" id="saveBtn">
                    <i class="icon-plus icon-white"></i>添加
                </button>
                <button style="margin-left: 10px;" ng-click="save()" class="btn btn-primary" id="saveBtn">
                    <i class="icon-check icon-white"></i>保存
                </button>

            </div>
        </div>
        <div class="row-fluid">
            <table class="table table-striped table-bordered table-hover" id="example">
                <thead>
                    <tr>
                        <th>月报期</th>
                        <th>月报开始日期</th>
                        <th>月报结束日期</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <tr ng-repeat="sub in yuebsjpzList track by $index">
                    <td><input type="text" ng-model="sub.YUEBJQ" class="datepicker1"></td>
                    <td><input type="text" ng-model="sub.YUEBKSRQ" class="datepicker0"></td>
                    <td><input type="text" ng-model="sub.YUEBJZRQ" class="datepicker0"></td>
                    <td>
                        <button style="margin-left: 10px;" ng-click="delete($index)" class="btn btn-danger" id="deleteBtn">
                        <i class="icon-trash icon-white"></i>删除
                    </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>