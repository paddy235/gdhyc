<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<div class="tab-pane" ng-controller="jiesglZafjsCtrl">
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <label style="width: 65px;height: 30px;line-height: 30px;float: left;" class="control-label">电厂:</label>
                <select style="width:120px; float: left;" ng-model="search.DIANCXXB_ID" ng-change="refresh()"
                        ng-options="option.value as option.name for option in diancList">
                </select>
                <label style="width: 65px;height: 30px;line-height: 30px;float: left;"
                       class="control-label">入厂日期:</label>
                <input style="width: 120px;float: left;" id="datepicker" ng-model="search.sDate" ng-change="refresh()"
                       type="text">
                <label style="width:15px;height: 30px;line-height: 30px;margin-left:5px;margin-right:5px;float: left;"
                       class="control-label">至</label>
                <input style="width: 120px;float: left;" id="datepicker1" ng-model="search.eDate" ng-change="refresh()"
                       type="text">
                <label style="width: 55px;height: 30px;line-height: 30px;float: left;margin-left:5px;"
                       class="control-label">供应商:</label>
                <select style="width:120px; float: left;" ng-model="search.GONGYSB_ID" ng-change="getPinzList()"
                        ng-options="option.value as option.name for option in gongysList">
                </select>
                <label style="width: 50px;height: 30px;line-height: 30px;float: left;margin-left:5px;"
                                                class="control-label">品种:</label>
                <select style="width:120px; float: left;" ng-model="search.PINZB_ID" ng-change="refresh()"
                        ng-options="option.value as option.name for option in pinzList">
                </select>
                <label style="width: 50px;height: 30px;line-height: 30px;float: left;margin-left:5px;"
                       class="control-label">业务类型:</label>
                <select style="width:120px; float: left;" ng-model="search.YEWLX" ng-change="refresh()"
                        ng-options="option.VALUE as option.NAME for option in yewlxList">
                </select>
                <button style="margin-left:20px;" ng-click="refresh()" class="btn btn-success">
                    <i class=" icon-refresh icon-white"></i>刷新
                </button>
                <button style="margin-left:20px;" ng-click="jies()" class="btn btn-success">
                    <i class=" icon-refresh icon-white"></i>结算
                </button>
            </div>
        </div>
        <div class="row-fluid">
            <table class="table table-striped table-bordered table-hover"
                   my-table="overrideOptions"
                   aa-data="zafjsList"
                   ao-column-defs="columnDefs"
                   my-selection=true
                   id="example">
                <thead>
                    <tr>
                        <th style="text-align: center; padding-right: 0px;padding-left: 0px;">
                            <a href='javascript:void(0)' onclick="selectAll.click()">全选</a>
                            <input id="selectAll" type="checkbox" name="check" />
                        </th>
                        <th style="text-align: center; ">入库单号</th>
                        <th style="text-align: center; ">煤矿单位</th>
                        <th style="text-align: center; ">品种</th>
                        <th style="text-align: center; ">到货日期</th>
                        <th style="text-align: center; ">车数</th>
                        <th style="text-align: center; ">三方数量</th>
                        <th style="text-align: center; ">毛重</th>
                        <th style="text-align: center; ">皮重</th>
                        <th style="text-align: center; ">净重</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#datepicker").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });
        $("#datepicker1").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });
    });
</script>