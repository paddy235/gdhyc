<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<div class="tab-pane" ng-controller="fadgrhyckCtrl">
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <form class="form-horizontal ng-pristine ng-valid">
                    <label style="width: 80px;margin-right:5px;" class="control-label">创建日期:</label>
                    <input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
                    <label style="width: 15px;margin:0 5px 0 5px;" class="control-label">至</label>
                    <input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
                    <label style="width: 80px;margin-right:5px;" class="control-label">搜索条件:</label>
                    <input type="text" style="float: left;width: 120px;" ng-model="search.rukdbh">
                    <button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
                        <i class="icon-search icon-white"></i> 查询
                    </button>
                    <button style="margin-left: 10px;" ng-click="addRanlcgrk()" class="btn btn-primary">
                        <i class="icon-plus icon-white"></i> 添加
                    </button>
                </form>
            </div>
        </div>
        <div class="row-fluid">
            <table class="table table-striped table-bordered"
                   id="example">
                <thead>
                <tr>
                    <th style="text-align: center; width: 100px;">入库单号</th>
                    <th style="text-align: center; width: 100px;">业务日期</th>
                    <th style="text-align: center; width: 100px;">库存组织</th>
                    <th style="text-align: center; width: 100px;">业务类型</th>
                    <th style="text-align: center; width: 100px;">状态</th>
                    <th style="text-align: center; width: 100px;">货主</th>
                    <th style="text-align: center; width: 100px;">数量(吨)</th>
                    <th style="text-align: center; width: 100px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="sub in chukdList | filter : search.rukdbh">
                    <td style="text-align: right;">{{sub.RUKDBH}}</td>
                    <td style="text-align: right;">{{sub.YEWRQ}}</td>
                    <td style="text-align: center;">{{sub.KUCZZ}}</td>
                    <td style="text-align: center;">{{sub.YEWLX}}</td>
                    <td style="text-align: center;">{{sub.ZHUANGT}}</td>
                    <td style="text-align: center;">{{sub.HUOZ}}</td>
                    <td style="text-align: right;">{{sub.CHUKSL}}</td>
                    <td style="text-align: center;">
                        <button ng-click="edit(sub.RUKDBH)" ng-if="sub.ZHUANGT=='已出库'"
                                class="btn btn-info">查看
                        </button>
                        <button ng-click="edit(sub.RUKDBH)" ng-if="sub.ZHUANGT=='未出库'"
                                class="btn btn-info">编辑
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
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