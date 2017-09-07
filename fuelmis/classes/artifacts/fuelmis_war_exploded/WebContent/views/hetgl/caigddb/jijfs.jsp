<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<style>
    tbody .center {
        text-align: center !important;
    }

    tbody .right {
        text-align: right !important;
    }
    table th,td{white-space: nowrap;padding:5px 15px;}
</style>
<div class="block" ng-controller="jijfsCtrl">
    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{jijfsTitle}}</div>
    </div>
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <button style="margin-left: 10px;" id="adddata" class="btn btn-primary" ng-click="addPricSecheme()">
                    <i class="icon-plus icon-white"></i> 添加
                </button>
                <button style="margin-left: 10px;" id="savedata" class="btn btn-primary" ng-click="savePricSecheme()">
                    <i class="icon-check icon-white"></i> 保存
                </button>
                <button style="margin-left: 10px;" id="reback" class="btn" ng-click="reback()">
                    <i class="icon-repeat"></i> 返回
                </button>
            </div>
        </div>
        <div class="row-fluid">
            <table class="table table-striped table-bordered table-hover" id="example">
                <thead>
                <tr>
                    <th style="text-align: center; width: 70px;">指标</th>
                    <th style="text-align: center; width: 150px;">计价方式</th>
                    <th style="text-align: center; width: 150px;dispaly:none;">最高上限</th>
                    <th style="text-align: center; width: 150px;dispaly:none;">最低下限</th>
                    <th style="text-align: center; width: 150px;">指标范围维护</th>
                    <th style="text-align: center; width: 130px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="priceSecheme in  priceSechemeList track by $index">
                    <td class="center">
                        <select style="width: 250px;" ng-model="priceSecheme.PRICE_ITEM_ID"
                                ng-options="option.value as option.name for option in priceItemList">
                        </select>
                    </td>
                    <td class="center">
                        <select style="width: 250px;" ng-model="priceSecheme.PRICE_COMPONENT_ID"
                                ng-options="option.value as option.name for option in priceComponetList">
                        </select>
                    </td>
                    <td class="center" style="text-align: center; width: 150px;dispaly:none;">
                        <input type="text" ng-model="priceSecheme.SHANGXMAX" style="width: 50px">
                    </td>
                    <td class="center" style="text-align: center; width: 150px;dispaly:none;">
                        <input type="text" ng-model="priceSecheme.XIAXMIN" style="width: 50px">
                    </td>
                    <td style="text-align:center;">
                        <button ng-if="priceSecheme.ID!=undefined" class="btn btn-danger btn-mini"
                                ng-click="showzbwhfw($index);">
                            <i class="icon-list icon-white"></i>
                        </button>
                        <button disabled ng-if="priceSecheme.ID==undefined" class="btn  btn-mini"
                                ng-click="showzbwhfw($index);">
                            <i class="icon-list icon-white"></i>
                        </button>
                        <!-- <button  class="btn btn-danger btn-mini" id="zhibfwbutton{{index}}" ng-click="showzbwhfw(priceSecheme);">
                            <i class="icon-list icon-white"></i>
                        </button> -->
                    </td>
                    <td style="text-align: center;">
                        <button class="btn btn-danger btn-mini" ng-click="delPricSecheme(priceSecheme,$index);">
                            <i class="icon-remove icon-white" title="删除"></i>
                        </button>
                    </td>

                </tr>
                </tbody>
            </table>
            <div id="zbdetail">
                <table class="table table-bordered table-hover" ng-repeat="secheme in priceSechemeList" >
                    <thead>
                    <caption align="top">{{secheme.COMPONENT.NAME}}按{{secheme.COMPONENT.DANW}}计价范围({{secheme.ITEM.CODE}})<br>
                        <p style="text-align:right;">{{secheme.COMPONENT.SHUOM}}</p></caption>
                    <tr>
                        <th style="text-align: center; width: 80px;">合同基准</th>
						<th ng-if="secheme.ITEM.CODE=='Qnet_ar' || secheme.ITEM.CODE=='QNET_AR_DK'" style="text-align: center; width: 80px;">&lt;</th>
                        <th ng-if="secheme.ITEM.CODE=='Qnet_ar' || secheme.ITEM.CODE=='QNET_AR_DK'" style="text-align: center; width: 80px;">&gt;=</th>
                        <th ng-if="secheme.ITEM.CODE!='Qnet_ar' && secheme.ITEM.CODE!='QNET_AR_DK'" style="text-align: center; width: 80px;">指标上限</th>
                        <th ng-if="secheme.ITEM.CODE!='Qnet_ar' && secheme.ITEM.CODE!='QNET_AR_DK'" style="text-align: center; width: 80px;">指标下限</th>

                        <th ng-if="secheme.ITEM.CODE=='Qnet_ar' || secheme.ITEM.CODE=='QNET_AR_DK'" style="text-align: center; width: 80px;">基价( {{secheme.COMPONENT.DANW}})</th>
                        <th style="text-align: center; width: 80px;">增扣幅度</th>
                        <th style="text-align: center; width: 80px;">增扣价( {{secheme.COMPONENT.DANW}})</th>
                        <th style="text-align: center;  width: 80px;">基准增扣价</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="kaohzb in secheme.KAOHZB">
                        <td class="center"> {{kaohzb.JIZRZ}}</td>
                        <td class="center"> {{kaohzb.SHANGX}}</td>
                        <td class="center"> {{kaohzb.XIAX}}</td>
                        <td ng-if="secheme.ITEM.CODE=='Qnet_ar' || secheme.ITEM.CODE=='QNET_AR_DK'" class="center"> {{kaohzb.JIZJG}}</td>
                        <td class="center"> {{kaohzb.JIANGKJS}}</td>
                        <td class="center"> {{kaohzb.SHIJZKJ}}</td>
                        <td class="center" > {{kaohzb.JIZKJ}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- END FORM-->

    <!--弹出窗口 -->
    <div class="modal hide fade" id="myModal" style="width: auto;">
        <div class="modal-header">
            <a class="close" data-dismiss="modal">×</a>
            <h4>合同发货订单计价方案</h4>
        </div>
        <div class="modal-body">
            <button type="button" class="btn btn-primary" ng-click="addzhib()">
                <i class="icon-plus icon-white"></i> 添加
            </button>
            <button type="button" class="btn btn-primary" ng-click="savezhib()">
                <i class="icon-check icon-white"></i> 保存
            </button>
            <table id="liufen-table" cellpadding="0" cellspacing="0" border="0"
                   class="table table-striped table-bordered">
                <thead>
                <caption align="top">{{priceSechemeList[index].COMPONENT.NAME}}按质计价范围({{priceSechemeList[index].ITEM.CODE}})<br>{{priceSechemeList[index].COMPONENT.SHUOM}}</caption>
                <tr>
                    <th style="text-align: center; width: 50px;">合同基准</th>
                    <th ng-if="priceSechemeList[index].ITEM.CODE=='Qnet_ar' || priceSechemeList[index].ITEM.CODE=='QNET_AR_DK'" style="text-align: center; width: 80px;">指标上限(&lt;)</th>
                    <th ng-if="priceSechemeList[index].ITEM.CODE=='Qnet_ar' || priceSechemeList[index].ITEM.CODE=='QNET_AR_DK'" style="text-align: center; width: 80px;">指标下限(&gt;=)</th>
                    <th ng-if="priceSechemeList[index].ITEM.CODE!='Qnet_ar' && priceSechemeList[index].ITEM.CODE!='QNET_AR_DK'" style="text-align: center; width: 80px;">指标上限(&lt;)</th>
                    <th ng-if="priceSechemeList[index].ITEM.CODE!='Qnet_ar' && priceSechemeList[index].ITEM.CODE!='QNET_AR_DK'" style="text-align: center; width: 80px;">指标下限(&gt;=)</th>
                    <th ng-if="priceSechemeList[index].ITEM.CODE=='Qnet_ar' || priceSechemeList[index].ITEM.CODE=='QNET_AR_DK'" style="text-align: center; width: 60px;">基价({{priceSechemeList[index].COMPONENT.DANW}})</th>
                    <th style="text-align: center; width: 60px;">增扣幅度<br/></th>
                    <th style="text-align: center; width: 60px;">增扣价<br/>({{priceSechemeList[index].COMPONENT.DANW}})</th>
                    <th style="text-align: center; width: 60px;">基准增扣价</th>
                    <th style="text-align: center; width: 60px;">操作</th>
                </tr>
                </thead>

                <tbody>
                <tr ng-repeat="kaohzb in priceSechemeList[index].KAOHZB track by $index">
                    <td class="center"><input type="text" ng-model="kaohzb.JIZRZ" style="width: 50px"></td>
					<td class="center"><input type="text" ng-model="kaohzb.SHANGX" style="width: 50px"></td>
                    <td class="center"><input type="text" ng-model="kaohzb.XIAX" style="width: 50px"></td>
                    <td ng-if="priceSechemeList[index].ITEM.CODE=='Qnet_ar' || priceSechemeList[index].ITEM.CODE=='QNET_AR_DK'" class="center"><input type="text" ng-model="kaohzb.JIZJG" style="width: 50px"></td>
                    <td class="center"><input type="text" ng-model="kaohzb.JIANGKJS" style="width: 50px"></td>
                    <td class="center"><input type="text" ng-model="kaohzb.SHIJZKJ" style="width: 50px"></td>
                    <td class="center">
                        <input type="text" ng-model="kaohzb.JIZKJ" style="width: 50px">
                    </td>
                    <td style="text-align: center;">
                        <button class="btn btn-danger btn-mini" ng-click="delzhib($index);">
                            <i class="icon-remove icon-white" title="删除"></i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    
     <!--弹出窗口 -->
    <div class="modal hide fade" id="myModal_hetl" style="width: auto;">
        <div class="modal-header">
            <a class="close" data-dismiss="modal">×</a>
            <h4>合同量考核计价</h4>
        </div>
        <div class="modal-body">
            <button type="button" class="btn btn-primary" ng-click="addzhib()">
                <i class="icon-plus icon-white"></i> 添加
            </button>
            <button type="button" class="btn btn-primary" ng-click="savezhib()">
                <i class="icon-check icon-white"></i> 保存
            </button>
            <table id="liufen-table" cellpadding="0" cellspacing="0" border="0"
                   class="table table-striped table-bordered">
                <thead>
                <caption align="top">计价公式：</caption>
                <tr>
                    <th style="text-align: center; width: 80px;">约定上限(&lt;)</th>
                    <th style="text-align: center; width: 80px;">指标下限(&gt;=)</th>
                    <th style="text-align: center; width: 60px;">增扣价<br/>(元/吨)</th>
                    <th style="text-align: center; width: 60px;">操作</th>
                </tr>
                </thead>

                <tbody>
                <tr ng-repeat="kaohzb in priceSechemeList[index].KAOHZB track by $index">
					<td class="center"><input type="text" ng-model="kaohzb.SHANGX" style="width: 50px"></td>
                    <td class="center"><input type="text" ng-model="kaohzb.XIAX" style="width: 50px"></td>
                    <td class="center">
                        <input type="text" ng-model="kaohzb.JIZKJ" style="width: 50px">
                    </td>
                    <td style="text-align: center;">
                        <button class="btn btn-danger btn-mini" ng-click="delzhib($index);">
                            <i class="icon-remove icon-white" title="删除"></i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>


</div>
<script type="text/javascript">
    function check(args) {
        if ($(args).attr("checked") != undefined) {
            $("#changestate").addClass("btn-danger");
            $("#changestate").removeAttr("disabled"); //移除disabled属性
            $("#updatefeiyxmfl").addClass("btn-info");
            $("#updatefeiyxmfl").removeAttr("disabled"); //移除disabled属性
            $("input[type='checkbox']").attr("checked", false);
            $(args).attr("checked", true);
        } else {
            $("#changestate").removeClass("btn-danger");
            $("#changestate").attr('disabled', true);
            $("#updatefeiyxmfl").removeClass("btn-info");
            $("#updatefeiyxmfl").attr('disabled', true);
        }
    }
</script>