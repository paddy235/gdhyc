<%@ page language="java" pageEncoding="UTF-8" %>

<style>
    th {
        text-align: center !important;
    }

    td {
        text-align: center !important;
        margin: 0px !important;
        padding: 0px !important;
        font-family: Microsoft YaHei;
    }

    td input, td select {
        width: 100% !important;
        height: 37px !important;
        margin: 0px !important;
        padding: 0px !important;
        border: 0px !important;
        text-align: center !important;
        font-family: Microsoft YaHei;
        background-color: transparent !important;
        line-height: 35px !important;
    }

    td select {
        padding: 8px 0 !important;
    }

    .operation {
        margin-top: 5px;
        width: 30px !important;
    }
</style>
<div class="tab-pane" ng-controller="kucftszCtrl">
    <div class="block-content collapse in ">
        <div class="span12" style="margin-bottom:20px;">
            <div class="table-toolbar">
                <%--				<button class="btn btn-primary" id="adddata" ng-click="add()">
                                    <i class="icon-plus icon-white"></i> 添加
                                </button>--%>
                <button class="btn btn-success" id="fujsc" ng-click="save()">
                    <i class="icon-check icon-white"></i> 保存
                </button>
            </div>
        </div>
        <form id="form1" runat="server">
            <div class="row-fluid">
                <table class="table table-striped table-bordered table-hover"
                       id="example">
                    <thead>
                    <tr>
                        <th style="width: 15%">序号</th>
                        <th style="width: 20%">库存物料</th>
                        <th style="width: 10%">百分比</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="row in kucftList track by $index" class="option">
                        <!-- 序号 -->
                        <td><input type="text" ng-model="row.XUH" value="{{$index+1}}"/></td>
                        <!-- 库存物料 -->
                        <td>{{row.KUCWL}}</td>
                        <!-- 百分比 -->
                        <td><input ng-focus="row.BAIFB=row.BAIFB.split('%')[0]" ng-blur="row.BAIFB=row.BAIFB+'%'" type="text" ng-model=row.BAIFB /></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
    <!-- END FORM-->
</div>