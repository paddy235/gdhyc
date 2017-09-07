<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<style>
    td {
        padding: 0px !important;
        height: 32px !important;
        text-align: center !important;
    }

    td input, td select {
        margin: 0px !important;
        text-align: center !important;
    }
</style>
<div class="tab-pane" ng-controller="niandjhzbycCtrl">
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <label
                        style="width: 35px; height: 30px; line-height: 30px; float: left;"
                        class="control-label">日期:</label> <input
                    style="width: 68px; float: left;" id="datepicker"
                    ng-model="search.riq" ng-change="refresh()" type="text">
                <label
                        style="width: 35px; height: 30px; line-height: 30px; float: left;"
                        class="control-label span1">单位:</label> <select
                    style="width: 100px; float: left;" id="selectdanw"
                    ng-model="search.diancid" ng-change="refresh()"
                    ng-options="option.value as option.name for option in diancList"></select>
                <button style="margin-left: 5px;" id="refresh" ng-click="refresh()"
                        class="btn btn-success">
                    <i class=" icon-refresh icon-white"></i> 刷新
                </button>
                <button id="delranlzf" ng-click="del()" class="btn btn-danger" ng-disabled="jihList[0].SANJ_ZT==1">
                    <i class="icon-trash icon-white"></i> 删除
                </button>
                <button class="btn btn-primary" id="copyranlzf" ng-disabled="jihList[0].SANJ_ZT==1"
                        ng-click="copyranlzfjh()">
                    <i class="icon-file icon-white"></i> 复制上月计划
                </button>
                <button class="btn btn-primary" ng-disabled="jihList[0].SANJ_ZT==1"
                        ng-click="save()">
                    <i class="icon-check icon-white"></i> 保存
                </button>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th style="display: none;"></th>
                        <th style="text-align: center; width: 10%;">序号</th>
                        <th style="text-align: center; display: none;">字段名</th>
                        <th style="text-align: center; width: 30%;">项目1</th>
                        <th style="text-align: center; width: 30%;">值1</th>
                        <th style="text-align: center; width: 30%;">单位1</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="row in jihList |subArray : 0 : jihList.length/2">
                        <td ng-show="false"><input type="text" ng-model="row.ID"/></td>
                        <td>{{row.XUH}}</td>
                        <td>{{row.MINGC}}</td>
                        <td ng-if="row.GONGS!=null">{{row.ZHI=$eval(row.GONGS)}}</td>
                        <td ng-if="row.GONGS==null"><input ng-change="calculate(row)" type="text" ng-model="row.ZHI"/>
                        </td>
                        <td>{{row.DANW}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="span6">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th style="display: none;"></th>
                        <th style="text-align: center; width: 10%;">序号</th>
                        <th style="text-align: center; display: none;">字段名</th>
                        <th style="text-align: center; width: 30%;">项目2</th>
                        <th style="text-align: center; width: 30%;">值2</th>
                        <th style="text-align: center; width: 30%;">单位2</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="row in jihList |subArray : (jihList.length/2) : jihList.length">
                        <td ng-show="false"><input type="text" ng-model="row.ID"/></td>
                        <td>{{row.XUH}}</td>
                        <td>{{row.MINGC}}</td>
                        <td ng-if="row.GONGS!=null">{{row.ZHI=$eval(row.GONGS)}}</td>
                        <td ng-if="row.GONGS==null"><input ng-change="calculate(row)" type="text" ng-model="row.ZHI"/>
                        </td>
                        <td>{{row.DANW}}</td>
                    </tr>
                    </tbody>

                </table>
            </div>

        </div>
    </div>
    <!-- END FORM-->
</div>

