<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<style>

</style>
<div class="tab-pane" ng-controller="RijsscCtrl">
    <div class="block-content collapse in ">
        <div class="span12">
            <form class="form-horizontal ng-pristine ng-valid">
                <div class="table-toolbar">
                    <label style="width:70px;margin-right:5px;" class="control-label">发货日期:</label>
                    <input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
                    <label style="width:15px;margin:0 5px;" class="control-label">至</label>
                    <input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
                    <label style="width:70px;margin-right:5px;" class="control-label">煤矿单位:</label>
                    <select ng-model="search.gongys" style="float: left;width: 120px;margin-right:30px;"
                            ng-options="option.value as option.name for option in gongysList">
                    </select>
                    <button style="margin-left: 20px;" ng-click="getRijscx();" class="btn btn-success">
                        <i class="icon-search icon-white"></i> 刷新
                    </button>
                   <%-- <button style="margin-left: 20px;" ng-click="delRijssc();" class="btn btn-danger">
                        <i class="icon-trash icon-white"></i> 日结算删除
                    </button>
                    <button style="margin-left: 20px;" ng-click="delRuksc();" class="btn btn-danger">
                        <i class="icon-trash icon-white"></i> 入库单删除
                    </button>--%>
                    <button style="margin-left: 20px;" ng-click="del();" class="btn btn-danger">
                        <i class="icon-trash icon-white"></i> 删除日结算入库单
                    </button>
                    <button style="margin-left: 20px;" ng-click="ruk();" class="btn btn-primary">
                        <i class="icon-download-alt icon-white"></i> 重新入库
                    </button>
                    <button style="margin-left: 20px;" ng-click="rijs();" class="btn btn-primary">
                        <i class="icon-download-alt icon-white"></i> 重新日结算
                    </button>
                </div>
                <div style="margin-top:20px;text-align:center;">
                </div>
            </form>
        </div>
    </div>
    <%--<div my-table-plus header="myHead" data="myArray"></div>--%>
    <div class="row-fluid" style="width: 100%;height: 100%;overflow: auto;">
        <table class="table table-striped table-bordered table-hover"
               id="example"
               my-table="overrideOptions"
               aa-data="myArray"
               ao-column-defs="columnDefs"
               my-selection="true"
        >
            <thead>
            <tr>
                <th style="text-align: center;width: 2% "><input type="checkbox" id="selectAll"></th>
                <th>供应商</th>
                <th>品种</th>
                <th>车数</th>
                <th>到货日期</th>
                <th>三方数量</th>
                <th>净重</th>
                <th>采样编码</th>
                <th>化验编码</th>
                <th>化验状态</th>
                <th>QNET_AR</th>
                <th>入库单编号</th>
                <th>入库金额</th>
                <th>编号</th>
                <th>开始时间</th>
                <th>结算时间</th>
                <th>日结算编号</th>
                <th>日结算数量</th>
                <th>日结算热值</th>
                <th>日结算硫分</th>
                <th>日结算含税金额</th>
                <th>日结算不含税金额</th>
                <th>日结算含税单价</th>
                <th>日结算热值增扣</th>
                <th>日结算硫分增扣</th>
                <th>日结算公式</th>
                <th>日结算合同编号</th>
                <th>月结算编号</th>
                <th>结算单价</th>
            </tr>
            </thead>
        </table>
    </div>
    <!-- 进度环 -->
    <div my-progress="isDisplay"></div>
</div>
<style type="text/css">
    #example th, td {
        white-space: nowrap;
    }
</style>
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