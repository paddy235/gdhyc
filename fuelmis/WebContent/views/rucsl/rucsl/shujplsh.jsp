<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<style>
    .mymodal {
        position: fixed;
        top: 22%;
        left: 25%;
        z-index: 1050;
        width: 50%;
        /*	margin-left: -280px;
            background-color: #FFD;
            border: 1px solid #999;
            border: 1px solid rgba(0, 0, 0, 0.3);
            border: 1px solid #999;
            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;
            outline: 0;
            -webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            -moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            -webkit-background-clip: padding-box;
            -moz-background-clip: padding-box;
            background-clip: padding-box
        */
    }
    .progress-bar {
        float: left;
        width: 0;
        height: 100%;
        font-size: 12px;
        line-height: 20px;
        color: #fff;
        text-align: center;
        background-color: #337ab7;
        -webkit-box-shadow: inset 0 -1px 0 rgba(0, 0, 0, .15);
        box-shadow: inset 0 -1px 0 rgba(0, 0, 0, .15);
        -webkit-transition: width .6s ease;
        -o-transition: width .6s ease;
        transition: width .6s ease;
    }
    .progress-bar-success {
        background-color: #5cb85c;
    }
</style>
<div class="tab-pane" ng-controller="shujplshCtrl">
    <div class="block-content collapse in ">
        <div class="span12" style="margin-bottom:20px;">
            <div class="table-toolbar">
                <form class="form-horizontal ng-pristine ng-valid">
                    <label style="width:40px;margin-right:5px;" class="control-label">状态:</label>
                    <select ng-model="search.zhuangt"  style="float: left;width: 120px;"
                            ng-options="option.value as option.name for option in zhuangtList" ng-change="searchData()"></select>
                    <label style="width: 80px;margin-right:5px;" class="control-label">发货日期:</label>
                    <input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate" ng-change="searchData()">
                    <label style="width: 15px;margin:0 5px;" class="control-label">至</label>
                    <input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate" ng-change="searchData()">
                    <button   ng-click="shenh()" class="btn btn-success" style="width: 100px;margin-left: 5px;">
                        <i class="icon-plus icon-white"></i> 审核
                    </button>
                    <button   ng-click="huit()" class="btn" style="width: 100px;">
                        <i class="icon-remove-circle"></i> 取消
                    </button>
                </form>
            </div>
        </div>
        <div class="row-fluid">
            <div style="text-align:right;">
                <input type="text" style="float:right;width:150px;" ng-model="search.sSearch" ng-change="searchData()">
                <label style="float:right;width:60px;margin-right:5px;height:30px;line-height:30px;" class="control-label">搜索:</label>
            </div>
            <table class="table table-bordered"
                   id="example">
                <thead>
                <tr style="background-color:#f8f8f8;">
                    <th style=" width: 20px;"></th>
                    <th>供应商</th>
                    <th>煤矿</th>
                    <th>车号</th>
                    <th>毛重</th>
                    <th>皮重</th>
                    <th>票种</th>
                    <th>过恒时间</th>
                    <th>车数</th>
                </tr>
                </thead>
                <tbody ng-repeat="data in dataList">
                <tr>
                    <td ><input type="checkbox" id="{{data.SAMCODE}}" name="checkId"  /></td>
                    <%--供应商--%>
                    <td >{{data.GONGYSB_ID}}</td>
                    <%--煤矿--%>
                    <td >{{data.MEIKXXB_ID}}</td>
                    <%--车号--%>
                    <td>{{data.CHEH}}</td>
                    <%--毛重--%>
                    <td>{{data.MAOZ}}</td>
                    <%--皮重--%>
                    <td>{{data.PIZ}}</td>
                    <%--票种--%>
                    <td>{{data.PIAOZ}}</td>
                    <%--过恒时间--%>
                    <td>{{data.DAOHRQ}}</td>
                    <%--车数--%>
                    <td>{{data.CHES}}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div id="pagination_box" class="pagination pagination-right" style="width:100%;margin-left: auto;margin-right: auto;">
            <ul id="pagination_zc" ></ul>
        </div>
        <!-- 提示框 -->
        <div style="background-color: transparent;height: 20px;" class="mymodal fade" id="myModal_Edit" tabindex="-1" role="dialog" data-backdrop="static"  style="display:none;"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="progress progress-striped active">
                <div id="shenhbar" class="progress-bar progress-bar-success" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 10%;">
                    <span class="sr-only">40% 完成</span>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("#datepicker1").datepicker({
            format : 'yyyy-mm-dd',
            language : "zh-CN",
            autoclose : true
        });

        $("#datepicker2").datepicker({
            format : 'yyyy-mm-dd',
            language : "zh-CN",
            autoclose : true
        });
    });
</script>