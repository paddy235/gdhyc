<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<style type="text/css">
    #example th, td {
        white-space: nowrap !important;
        padding: 5px 15px !important;

    }
    #example td{
        height: 25px !important;
    }
</style>
<link rel="stylesheet" type="text/css" href="css/table.css">
<div class="tab-pane" ng-controller="jiesglZafjswhCtrl">
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
                <label style="white-space: nowrap;height: 30px;line-height: 30px;float: left;margin-left:5px;"
                       class="control-label">业务类型:</label>
                <select style="width:120px; float: left;" ng-model="search.YEWLX" ng-change="refresh()"
                        ng-options="option.VALUE as option.NAME for option in yewlxList">
                </select>

            </div>

        </div>
        <div class="span12" style=" margin-bottom: 12px;">
            <button style="margin-left:20px;float: left;" ng-click="refresh()" class="btn btn-success">
                <i class=" icon-refresh icon-white"></i>刷新
            </button>
            <button style="margin-left: 10px;float: left;" ng-click="add()" class="btn btn-primary">
                <i class="icon-plus icon-white"></i> 添加
            </button>
            <button style="margin-left: 10px;float: left;" class="btn btn-danger"
                    id="delete" ng-click="delete()">
                <i class="icon-trash icon-white"></i> 删除
            </button>
            <button style="margin-left: 10px;float: left;" class="btn btn-primary" ng-click="save()">
                <i class="icon-check icon-white"></i> 保存
            </button>
            <button type="button" style="margin-left: 10px;" class="btn"  ng-click="back()">
                <i class="icon-repeat"></i> 返回
            </button>
        </div>
        <div class="row-fluid" class="span12">
            <table class="table table-striped table-bordered table-hover" id="example">
                <thead>
                <tr>
                    <th style="text-align: center; padding-right: 0px;padding-left: 0px;">
                        <span>全选</span>
                        <input id="selectAll" type="checkbox" name="check" ng-click="selectAll()"/>
                    </th>
                    <th style="text-align: center; ">序号</th>
                    <th style="text-align: center; ">项目</th>
                    <th style="text-align: center; ">计费总量</th>
                    <th style="text-align: center; ">单价</th>
                    <th style="text-align: center; ">应结金额</th>
                    <th style="text-align: center; ">实结金额</th>
                    <th style="text-align: center; ">备注</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="row in zafjswhList">
                    <td><input type="checkbox" ng-checked="hasSelect" id="row.ID"/></td>
                    <td><input type="text" ng-model="row.XUH"/></td>
                    <td><input type="text" ng-model="row.XIANGM"/></td>
                    <td><input type="text" ng-model="row.JIFZL"/></td>
                    <td><input type="text" ng-model="row.DANJ"/></td>
                    <td><input type="text" ng-model="row.YINGJJE"/></td>
                    <td><input type="text" ng-model="row.SHIJJE"/></td>
                    <td><input type="text" ng-model="row.BEIZ"/></td>
                </tr>
                <tr>
                    <td colspan="3">合计</td>
                    <td>{{}}</td>
                    <td>{{}}</td>
                    <td>{{}}</td>
                    <td>{{}}</td>
                    <td>{{}}</td>
                </tr>
                </tbody>
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