<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="tab-pane" ng-controller="shujshCtrl">
    <div class="block-content collapse in ">
        <div class="span12" style="margin-bottom:20px;">
            <div class="table-toolbar">
                <form class="form-horizontal ng-pristine ng-valid">
                    <label style="width:40px;margin-right:5px;" class="control-label">状态:</label>
                    <select ng-model="search.zhuangt" style="float: left;width: 120px;"
                            ng-options="option.value as option.name for option in zhuangtList"
                            ng-change="searchData()"></select>
                    <label style="width: 80px;margin-right:5px;" class="control-label">采样日期:</label>
                    <input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate"
                           ng-change="searchData()">
                    <label style="width: 15px;margin:0 5px;" class="control-label">至</label>
                    <input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate"
                           ng-change="searchData()">
                </form>
            </div>
        </div>
        <div class="row-fluid">
            <div style="text-align:right;">
                <input type="text" style="float:right;width:150px;" ng-model="search.sSearch" ng-change="searchData()">
                <label style="float:right;width:60px;margin-right:5px;height:30px;line-height:30px;"
                       class="control-label">搜索:</label>
            </div>
            <table class="table table-bordered" id="example" data-sortable="true">
                <thead>
                <tr style="background-color:#f8f8f8;">
                    <th style="text-align: center;">序号</th>
                    <th colspan="3" style="text-align: center;">审核内容</th>
                    <th style="text-align: center;">预警提示</th>
                    <th style="text-align: center;">操作</th>
                </tr>
                </thead>
                <tbody ng-repeat="data in dataList  | filter : search.sSearch">
                <tr>
                    <td rowspan="7" style="text-align: center;vertical-align: middle;">{{$index+1}}</td>
                    <td colspan="3">单位名称：{{data.DIANCXXB_ID}}</td>
                    <td rowspan="7" style="text-align: center;vertical-align: middle;width: 150px;">{{data.yujts}}</td>
                    <td rowspan="7" style="text-align: center;vertical-align: middle;">
                        <button ng-if="data.ZHUANGT!=3" ng-click="shenh(data.SAMCODE)" class="btn btn-success"
                                style="width: 100px;">
                            <i class="icon-plus icon-white"></i> 审核
                        </button>
                        <button ng-if="data.ZHUANGT==3" ng-click="huit(data.SAMCODE)" class="btn"
                                style="width: 100px;">
                            <i class="icon-remove-circle"></i> 取消
                        </button>
                        <br><br><br>
                        <button ng-click="chakmx(data.SAMCODE)" class="btn btn-primary" style="width: 100px;">
                            <i class="icon-list-alt icon-white"></i> 查看明细
                        </button>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="border-right: 0px;border-bottom: 0px;border-top: 0px;">
                        供货单位：{{data.GONGYSB_ID}}
                    </td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;">煤矿单位：{{data.MEIKXXB_ID}}</td>
                </tr>
                <tr>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;">品种：{{data.PINZB_ID}}</td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        计划口径：{{data.JIHKJB_ID}}
                    </td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        运输方式：{{data.YUNSFSB_ID}}
                    </td>
                </tr>
                <tr>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;">车次：{{data.SAMCODE}}</td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        发货日期：{{data.FAHRQ}}
                    </td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        到货日期：{{data.DAOHRQ}}
                    </td>
                </tr>
                <tr>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;">发站：{{data.FAZ_ID}}</td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        到站：{{data.DAOZ_ID}}
                    </td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        车数：{{data.CHES}}
                    </td>
                </tr>
                <tr>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;">净重：{{data.JINGZ}}</td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        票重：{{data.PIAOZ}}
                    </td>
                    <td style="border-right: 0px;border-bottom: 0px;border-top: 0px;border-left: 0px;">
                        总扣吨：{{data.ZONGKD}}
                    </td>
                </tr>
                <tr>
                    <td style="border-right: 0px;border-top: 0px;">审核人：{{data.SHENHR}}</td>
                    <td style="border-right: 0px;border-top: 0px;border-left: 0px;" colspan="2">审核时间：{{data.SHENHSJ}}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <my-page2 data="dataList"></my-page2>
    </div>
    <!-- 提示框 -->
    <div my-progress="display"></div>

</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#datepicker1").datepicker({
            format: 'yyyy-mm-dd',
            language: "zh-CN",
            autoclose: true
        });

        $("#datepicker2").datepicker({
            format: 'yyyy-mm-dd',
            language: "zh-CN",
            autoclose: true
        });
    });
</script>