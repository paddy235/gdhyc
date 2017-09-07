<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="/fuelmis/css/table.css">
<div class="tab-pane" ng-controller="haocmxCtrl">
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
                    <button style="margin-left: 10px;" ng-click="createData_Win()" class="btn" id="createBtn" ng-disabled="zhuangt==1" >生成</button>
                    <button style="margin-left: 10px;" ng-click="delData_Win()" class="btn" id="delBtn" ng-disabled="zhuangt==1">删除</button>
                    <button style="margin-left: 10px;" ng-click="saveData()" class="btn" id="saveBtn" ng-disabled="zhuangt==1" >保存</button>
                    <button style="margin-left: 10px;" ng-click="printTable()" class="btn" id="printBtn">打印</button>
                </form>
            </div>
        </div>
        <div class="row-fluid">
            <table class="table table-striped table-bordered table-hover" id="example">
                <thead>
                <tr>
                    <th style="text-align: center; width: 100px;">供货单位</th>
                    <th style="text-align: center; width: 100px;">计划口径</th>
                    <th style="text-align: center; width: 100px;">品种</th>
                    <th style="text-align: center; width: 100px;">运输方式</th>
                    <th style="text-align: center; width: 100px;">分项</th>
                    <th style="text-align: center; width: 100px;">期初库存</th>
                    <th style="text-align: center; width: 100px;">收煤量</th>
                    <th style="text-align: center; width: 100px;">发电用</th>
                    <th style="text-align: center; width: 100px;">供热用</th>
                    <th style="text-align: center; width: 100px;">其他用</th>
                    <th style="text-align: center; width: 100px;">实际储损</th>
                    <th style="text-align: center; width: 100px;">调出量</th>
                    <th style="text-align: center; width: 100px;">盘盈亏</th>
                    <th style="text-align: center; width: 150px;">水分差调整</th>
                    <th style="text-align: center; width: 100px;">库存</th>
                </tr>
                </thead>
                <tbody>
                <tr  ng-if="zhuangt==0" ng-repeat="sub in haocmxList |subArray : 0 : 2 ">
                    <td style="text-align: center;">{{sub.GONGYSB_ID}}</td>
                    <td style="text-align: center;">{{sub.JIHKJB_ID}}</td>
                    <td style="text-align: center;">{{sub.PINZB_ID}}</td>
                    <td style="text-align: center;">{{sub.YUNSFSB_ID}}</td>
                    <td style="text-align: center;">{{sub.FENX}}</td>
                    <td style="text-align: right;">{{sub.QICKC}}</td>
                    <td style="text-align: right;">{{sub.SHOUML}}</td>
                    <td  style="text-align: right;" class="table-div-line" >{{sub.FADY}}</td>
                    <td style="text-align: right;">{{sub.GONGRY}}</td>
                    <td style="text-align: right;">{{sub.QITH}}</td>
                    <td style="text-align: right;">{{sub.SUNH}}</td>
                    <td style="text-align: right;">{{sub.DIAOCL}}</td>
                    <td style="text-align: right;">{{sub.PANYK}}</td>
                    <td style="text-align: right;">{{sub.SHUIFCTZ}}</td>
                    <td style="text-align: right;">{{sub.KUC}}</td>
                </tr>
                <tr ng-if="zhuangt==0" ng-repeat="sub in haocmxList |subArray : 2 ">
                    <td style="text-align: center;">{{sub.GONGYSB_ID}}</td>
                    <td style="text-align: center;">{{sub.JIHKJB_ID}}</td>
                    <td style="text-align: center;">{{sub.PINZB_ID}}</td>
                    <td style="text-align: center;">{{sub.YUNSFSB_ID}}</td>
                    <td style="text-align: center;">{{sub.FENX}}</td>

                    <td style="text-align: right;">
                        <input type="text" ng-if="sub.FENX=='本月'" ng-model="sub.QICKC" >
                        <span ng-if="sub.FENX=='累计'">{{sub.QICKC}}</span>
                    </td>
                    <td style="text-align: right;">{{sub.SHOUML}}</td>
                    <td  style="text-align: right;" class="table-div-line" >
                        <span ng-if="sub.FENX=='累计'">{{sub.FADY}}</span>
                        <input  ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.FADY"  >
                    </td>
                    <td style="text-align: right;">
                        <span ng-if="sub.FENX=='累计'">{{sub.GONGRY}}</span>
                        <input  ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.GONGRY" >
                    </td>
                    <td style="text-align: right;">
                        <span ng-if="sub.FENX=='累计'">{{sub.QITH}}</span>
                        <input   ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.QITH" >
                    </td>
                    <td style="text-align: right;">
                        <span ng-if="sub.FENX=='累计'">{{sub.SUNH}}</span>
                        <input  ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.SUNH" >
                    </td>
                    <td style="text-align: right;">
                        <span ng-if="sub.FENX=='累计'">{{sub.DIAOCL}}</span>
                        <input  ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.DIAOCL" >
                    </td>
                    <td style="text-align: right;">
                        <span ng-if="sub.FENX=='累计'">{{sub.PANYK}}</span>
                        <input  ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.PANYK" >
                    </td>
                    <td style="text-align: right;">
                        <span ng-if="sub.FENX=='累计'">{{sub.SHUIFCTZ}}</span>
                        <input  ng-if="sub.FENX=='本月'" type="text"  ng-model="sub.SHUIFCTZ" >
                    </td>
                    <td style="text-align: right;">
                        {{sub.KUC=sub.QICKC+sub.SHOUML-sub.FADY-sub.GONGRY-sub.QITH-sub.SUNH-sub.DIAOCL-sub.SHUIFCTZ|number : 2}}
                        <%-- <span ng-if="sub.FENX=='累计'">{{sub.KUC| number : 0}}</span>
                         <span ng-if="sub.FENX=='本月'">{{sub.KUC=$eval(sub.kucgs)| number : 0}}</span>--%>
                    </td>
                </tr>
                <tr ng-if="zhuangt==1" ng-repeat="sub in haocmxList" >
                    <td style="text-align: center;">{{sub.GONGYSB_ID}}</td>
                    <td style="text-align: center;">{{sub.JIHKJB_ID}}</td>
                    <td style="text-align: center;">{{sub.PINZB_ID}}</td>
                    <td style="text-align: center;">{{sub.YUNSFSB_ID}}</td>
                    <td style="text-align: center;">{{sub.FENX}}</td>
                    <td style="text-align: right;">{{sub.QICKC}}</td>
                    <td style="text-align: right;">{{sub.SHOUML}}</td>
                    <td  style="text-align: right;" class="table-div-line" >{{sub.FADY}}</td>
                    <td style="text-align: right;">{{sub.GONGRY}}</td>
                    <td style="text-align: right;">{{sub.QITH}}</td>
                    <td style="text-align: right;">{{sub.SUNH}}</td>
                    <td style="text-align: right;">{{sub.DIAOCL}}</td>
                    <td style="text-align: right;">{{sub.PANYK}}</td>
                    <td style="text-align: right;">{{sub.SHUIFCTZ}}</td>
                    <td style="text-align: right;">{{sub.KUC=sub.QICKC+sub.SHOUML-sub.FADY-sub.GONGRY-sub.QITH-sub.SUNH-sub.DIAOCL-sub.SHUIFCTZ|number : 2}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="modal fade" id="myModal_Create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
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
                    <button type="button" class="btn btn-danger" ng-click="createData()"><i class="icon-ok-sign icon-white"></i> 确认</button>
                    <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
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
                    <button type="button" class="btn btn-danger" ng-click="delData()"><i class="icon-ok-sign icon-white"></i> 确认</button>
                    <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("#datepicker1").datepicker({
            format : 'yyyy-mm',
            minViewMode : 1,
            language : "zh-CN",
            autoclose : true
        });
    });
</script>