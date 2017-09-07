<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="gddlMain">
<head>
    <% response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.flushBuffer();
    %> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <%--<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">--%>
    <%@include file="../common/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/styles.css">

    <script type="text/javascript" src="${ctx}/js/views/main.js"></script>
    <script type="text/javascript"
            src="${ctx}/js/views/routeConfig.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${ctx}/js/libs/html5shiv.min.js"></script>
    <script src="${ctx}/js/libs/respond.min.js"></script>
    <![endif]-->
    <script src="${ctx}/js/views/myTable.js"></script>
    <script src="${ctx}/js/views/myUpload.js"></script>


    <script type="text/javascript" src="/fuelmis/js/ligerUI/js/core/base.js"></script>
    <script type="text/javascript" src="/fuelmis/js/ligerUI/js/plugins/ligerDrag.js"></script>
    <script type="text/javascript" src="/fuelmis/js/ligerUI/js/plugins/ligerDialog.js"></script>
    <script type="text/javascript" src="/fuelmis/js/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="/fuelmis/js/swfupload/plugins/swfupload.queue.js"></script>
    <script type="text/javascript" src="/fuelmis/jsp/swfupload.js"></script>

    <title>欢迎</title>
</head>
<body ng-controller="bodyCtrl" style="padding-top: 133px;">
<div class="navbar navbar-fixed-top">
    <div id="logo-div">
        <img id="blogo" src="${ctx}/images/logo.png"> <img id="slogo"
                                                           src="${ctx}/images/slogo.png">
        <ul class="nav pull-right">
            <li class="dropdown"><a href="javascript:void(0);"
                                    role="button" class="dropdown-toggle" data-toggle="dropdown"
                                    onfocus="this.blur();" style="color: #00f; font-weight: bold;">
                <i class="icon-user"></i> {{dangqyh[0].mingc}} <i class="caret"></i>
            </a>
                <ul class="dropdown-menu">
                    <li><a tabindex="-1" href="javascript:void(0);"
                           ng-click="changePassword(${sessionScope.user.id})">修改个人信息</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="/fuelmis/logout">注销</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="navbar-inner">
        <div class="container-fluid" style="padding: 0">
            <a class="btn btn-navbar" data-toggle="collapse"
               data-target=".nav-collapse"> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span>
            </a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="dropdown" ng-repeat="menu in topMenuxx"
                        ng-class="{'active': menu.id == activeItem.id}"><a
                            style="font-size: 12px;" href="javascript:void(0);" role="button"
                            class="dropdown-toggle" data-toggle="dropdown"
                            ng-click="changePage(menu)" onfocus="this.blur();">{{menu.name}}
                        <i ng-if="menu.children.length>0" class="caret"></i>
                    </a>
                        <ul class="dropdown-menu multi-level" ng-if="menu.name!='首页'">
                            <li class="dropdown-submenu"
                                ng-repeat="childMenu in menu.children"
                                ng-class="{'active': childMenu.id == activeItem.id}">
                                <a tabindex="-1" href="javascript:void(0);"
                                   ng-click="changeLeftPage(childMenu)"> {{childMenu.name}}
                                </a>
                                <ul class="dropdown-menu">
                                    <li ng-repeat="child in childMenu.children track by $index"
                                        ng-class="{'active': child.id == activeItem.id}"><a
                                            href="javascript:void(0);"
                                            ng-click="changeRightPage(childMenu.children,child,$event.target,$index)"
                                            onfocus="this.blur();"> {{child.name}} </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <div class="toggler">
        <span class="glyphicon glyphicon-chevron-right" style="display: block;">&nbsp;</span> <span
            class="glyphicon glyphicon-chevron-left" style="display: none;">&nbsp;</span>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <!-- <div class="span3" id="sidebar" ng-controller="leftCtrl" style="margin-right:0">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li ng-repeat="child in leftMenuxx" ng-class="{'active': child.id == activeItem.id}">
                    <a href="javascript:void(0);" ng-click="changeRightPage(child)" onfocus="this.blur();">
                    {{child.name}} </a>
                </li>
            </ul>
        </div> -->
        <ul class="nav nav-tabs" style="margin-bottom: 0px;" id="my-nav">
            <li ng-repeat="child in leftMenuxx track by $index"
                id="{{child.wenjwz}}"
                ng-class="{true: 'active', false: 'inactive'}[child.status]"><a
                    href="javascript:void(0);"
                    ng-click="changeRightPage(leftMenuxx,child,$event.target,$index)"
                    onfocus="this.blur();"> {{child.name}} </a></li>
        </ul>
        <div class="span12 tab-content " style="margin-top: 0px;"
             id="content">
            <div class="row-fluid block" ng-view style="width: 99.8%"></div>
        </div>
    </div>
</div>
</body>
<!-- ---------------------------------------------------------引入js------------------------------------------------- -->
<!-- --------------------------------------------修改密碼------------------------------------------------------------- -->
<script type="text/javascript" src="${ctx}/js/views/main/changePassword.js"></script>
<!-----------------------------------------------计划管理---------------------------------------------------------------->
<!-- 计划管理 Start -->
<!-- 月计划管理 Start -->
<script type="text/javascript" src="${ctx}/js/views/jih/yuedjhcx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/yuedrljh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/yuedcaigjh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/yuedjhtj.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/yuedcaigjhadd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/yuedranlzfjhadd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/yuedjhzhibycadd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/yuedjh/yuedjhzbyc.js"></script>
<!-- 月计划管理 End -->
<!-- 年计划管理 Start -->
<script type="text/javascript" src="${ctx}/js/views/jih/niandrljh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/niandcaigjh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/niandjhtj.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/niandcaigjhadd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/niandranlzfjhadd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/niandjhzhibycadd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jih/niandjh/niandjhzbyc.js"></script>
<!-- 年计划管理 End -->
<!-- 计划管理 End -->
<!------------------------------------------------合同管理--------------------------------------------------------------->
<!-- 合同管理-查询打印 Begin -->

<script type="text/javascript" src="${ctx}/js/ajaxexcelexport.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/chaxdy/hetcx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/chaxdy/pingsyjb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/caigddb/caigddpp.js"></script>
<!-- 合同管理-查询打印 End -->
<script type="text/javascript" src="${ctx}/js/views/hetgl/caigddb/caigddb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/hetbean/hetbean.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/hychetbean/hychetbean.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/hetmb/hetmb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/rlhetmb/rlhetmb.js"></script>
<script type="text/javascript"
        src="${ctx}/js/views/hetgl/pricecomponet/pricecomponet.js"></script>
<script type="text/javascript" src="${ctx}/js/views/hetgl/priceitem/priceitem.js"></script>
<script type="text/javascript"
        src="${ctx}/js/views/hetgl/priceqalityrange/priceqalityrange.js"></script>
<!------------------------------------------------采样管理--------------------------------------------------------------->
<!-- 采样操作 -->
<script type="text/javascript" src="${ctx}/js/views/caiygl/caiyzmd/caiyzmd.js"></script>
<!-- 编码查询 -->
<script type="text/javascript" src="${ctx}/js/views/caiygl/bianmcx/bianmcx.js"></script>
<!-- 采制化编码维护 -->
<script type="text/javascript" src="${ctx}/js/views/caiygl/caizhbmwh/caizhbmwh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/caiygl/caizhbmwh/heby.js"></script>
<!-- 化验编码生成条形码 -->
<script type="text/javascript" src="${ctx}/js/views/ruchy/huaybm/huaybm.js"></script>
<!------------------------------------------------验收管理--------------------------------------------------------------->
<!-- 入厂数量-入厂数量 Begin -->
<script type="text/javascript" src="${ctx}/js/views/rucsl/rucsl/shujsh.js?version=20160422115343"></script>
<!-- 验收管理-化验报告(新增) -->
<script type="text/javascript" src="${ctx}/js/views/rucsl/ruchybg/ruchybg.js"></script>
<!-- 入厂化验-化验审核 Begin -->
<script type="text/javascript" src="${ctx}/js/views/ruchy/huaysh/ruchyyjsh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/ruchy/huaysh/ruchyejsh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/ruchy/rucmyjcjg/rucmyjcjg.js"></script>
<script type="text/javascript" src="${ctx}/js/views/ruchy/shenh/shenh.js"></script>
<!-- 入厂化验-化验审核 End -->
<!-- 入厂数量 -->
<script type="text/javascript" src="${ctx}/js/views/rucsl/rucsl/sanfsllr.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rucsl/rucsl/shujplsh.js"></script>
<!-- 过衡日报 -->
<script type="text/javascript" src="${ctx}/js/views/rib/guohrb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rucsl/shultz/shultz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rucsl/huayrb/huayrb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rucsl/huaytz/huaytz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rucsl/zonghtz/zonghtz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rucslys/chepbtmp.js"></script>
<!-- 验收管理-外来样维护 -->
<script type="text/javascript" src="${ctx}/js/views/yansgl/wailyrl.js"></script>
<!-- 验收管理-数量痕迹保留 -->
<script type="text/javascript" src="${ctx}/js/views/yansgl/shulhjbl.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yansgl/baobcx/guohmx.js"></script>
<!-- 验收管理-痕迹保留-质量痕迹保留-->
<script type="text/javascript" src="${ctx}/js/views/yansgl/ruchjbl/zhilhjbl.js"></script>
<!-- 验收管理-痕迹保留-质量痕迹保留-->
<script type="text/javascript" src="${ctx}/js/views/yansgl/ruchjbl/zhilhjbl.js"></script>


<!------------------------------------------------库存管理--------------------------------------------------------------->
<!-- 库存管理 Begin -->
<script type="text/javascript" src="${ctx}/js/views/kucgl/shujlr/shujlr.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/pandcx/pandcx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/caigrk/ranlcgrk.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/caigrk/yunzfcgrk.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/caigrk/qitrk.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/chukgl/fadgrhyck.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/shiscbhs/shiscbhs.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/shangccw/shangccw.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/kucgl/kucgl.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/caigrk/churkdlr/churkdlr.js"></script>
<!-- 库存报表查询 -->
<script type="text/javascript" src="${ctx}/js/views/kucgl/sscbhs/kucsscbhs.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/kucbbcx/fadgrhyckbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/kucbbcx/chukdbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/kucbbcx/riclhcbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/kucgl/kucbbcx/ranmzgmx.js"></script>	<!-- 燃煤暂估 -->
<script type="text/javascript" src="${ctx}/js/views/kucgl/kucbbcx/chukcxbb.js"></script>
<!-- 出库管理 -->
<script type="text/javascript" src="${ctx}/js/views/kucgl/chukgl/churkdb.js"></script>
<!-- 入炉管理 -->
<script type="text/javascript" src="${ctx}/js/views/rulgl/baseSet/baseSet.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rulgl/xianggwh/xianggwh.js"></script>
<!------------------------------------------------结算管理--------------------------------------------------------------->
<!-- 结算管理-煤款结算 Begin -->
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/jiestz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/jiesdcx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/hycjiesdcx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/meikrjs.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/meikrjsdj.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/meikyjs.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/hycmeikjs.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/hycjiesdxg.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/jiesdxg.js"></script>
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/rijstz.js"></script>
<!-- 结算管理-煤款结算 End -->
<script type="text/javascript" src="${ctx}/js/views/jiesgl/rijssc/rijssc.js"></script>
<!-- 结算管理-杂费结算 -->
<script type="text/javascript" src="${ctx}/js/views/jiesgl/zafjs/zafjs.js"></script>
<!-- 结算-->
<script type="text/javascript" src="${ctx}/js/views/jies/jies.js"></script>
<!-- 月结算统计台账 -->
<script type="text/javascript" src="${ctx}/js/views/jiesgl/baobcx/yuejstjtz.js"></script>
<!-- 结算管理 - 结算台帐 -->
<!-- <script type="text/javascript" src="${ctx}/js/views/jiesgl/jiestz/jietz.js"></script>  -->
<script type="text/javascript" src="${ctx}/js/views/jiesgl/meikjs/jiestz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/main/wenjtz.js"></script>
<!------------------------------------------------场内费用--------------------------------------------------------------->
<!-- 厂内费用 Start-->
<script type="text/javascript" src="${ctx}/js/views/cnfy/caiwys/yuedyssq.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/caiwys/niandyssq.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/caiwys/yustzsq.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/caiwys/feiyxmsq.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/caiwys/yuszhcx.js"></script>

<script type="text/javascript" src="${ctx}/js/views/cnfy/feiyxm/feiyxmfl.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/feiyxm/feiyxmwh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/cnfymx/feiymx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/cnfymx/feiybxd.js"></script>

<script type="text/javascript" src="${ctx}/js/views/cnfy/zafht/zafht.js"></script>
<script type="text/javascript" src="${ctx}/js/views/cnfy/zafjs/zafjswh.js"></script>
<!-- 厂内费用 End-->
<!------------------------------------------------入炉管理--------------------------------------------------------------->
<!-- 入炉管理-报表查询 Begin -->
<script type="text/javascript" src="${ctx}/js/views/rulgl/baobcx/rulhyd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rulgl/baobcx/ruljzbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rulgl/baobcx/ruljztz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rulgl/baobcx/rucrlrzc.js"></script>
<script type="text/javascript" src="${ctx}/js/views/rulgl/baobcx/sis_shujcx.js"></script>
<!-- 入炉管理-报表查询 End -->
<!------------------------------------------------调运管理--------------------------------------------------------------->
<!-- 日报 -->
<script type="text/javascript" src="${ctx}/js/views/rib/ribcxh.js"></script>
<!-- 日报填报 -->
<script type="text/javascript" src="${ctx}/js/views/diaoygl/ribtb/ribtb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/diaoygl/ribgs/ribgs.js"></script>
<script type="text/javascript" src="${ctx}/js/views/diaodjh/diaodjh.js"></script>
<!-- 日调度审核 -->
<script type="text/javascript" src="${ctx}/js/views/diaoygl/diaodgl/riddsh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/diaoygl/diaodgl/diaodjhwh.js"></script>
<!-- 调运管理-报表查询 -->
<script type="text/javascript" src="${ctx}/js/views/diaoygl/baobcx/baobcx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/diaoygl/ribtb/ranmzhrbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/diaoygl/ribtb/dianmcgjgrb.js"></script>
<!------------------------------------------------供应商管理------------------------------------------------------------->
<!-- 供应商管理 -->
<!-- 基础信息/日特殊指标  -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/jicxx/ritszb.js"></script>
<!-- 日指标评分 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/gongyspggl/rizbpf.js"></script>
<!-- 日特殊评分 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/gongyspggl/ritspf.js"></script>
<!-- 月指标评分 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/gongyspggl/yuezbpf.js"></script>
<!-- 供煤计划调整 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/yuegmjh/gongmjhtz.js"></script>

<!-- 日供煤计划调整 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/rigmjh/rigmjh.js"></script>

<!-- 供煤计划查询 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/yuegmjh/gongmjhcx.js"></script>
<!-- 合同 -->
<!-- 考核指标 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/kaohzb/kaohzb.js"></script>
<!-- 评分方案 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/pingffa/pingffa.js"></script>
<!-- 评分方案 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/pingffaxzb/pingffaxzb.js"></script>
<!-- 合同 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/hetb/hetb.js"></script>

<script type="text/javascript" src="${ctx}/js/views/gongyspg/yuegmjh/yuegmjh.js"></script>
<!-- 发货表 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/fahb/fahb.js"></script>
<!-- 供煤计划质量 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/gongmjhzl/gongmjhzl.js"></script>
<!-- 供应商管理 - 合同执行管理 - 日计划质量填报 -->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/rijhzltb/rijhzltb.js"></script>
<!-- 供应商管理 -报表查询-供应商 月评分查询-->
<script type="text/javascript" src="${ctx}/js/views/gongyspg/baobcx/gongysbbcx.js"></script>

<!------------------------------------------------月报管理--------------------------------------------------------------->
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/yuebsjpz.js"></script>
<!-- 月报管理-数据维护Begin -->
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/rucsl.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/ruczl.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/haochj.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/haocmx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/rucbmdj.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/zhibqk.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/rulrz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/youshc.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/zafwh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/duibbmdj.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/rucrlkcbmdj.js"></script>
<!-- 月报管理-数据维护 End -->
<script type="text/javascript" src="${ctx}/js/views/yuebgl/shujwh/rucbddb.js"></script>
<!-- 月报管理-新增报表 End -->
<script type="text/javascript" src="${ctx}/js/views/xinzbb/fenkcgrcbmdj.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xinzbb/laihcl.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xinzbb/rucbddbbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xinzbb/kucbmdj.js"></script>


<!-- 月报管理-国电报表 Begin -->
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/meishcbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/shulysbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/zhilysbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/rucbmdjbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/ranyshc.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/rulmrz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/ranlcbbb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/changnfycx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/ranlzbqkb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/dianmcgjgyb.js"></script>
<script type="text/javascript" src="${ctx}/js/views/yuebgl/guodbb/rucmydb.js"></script>


<!-- 月报管理-国电报表 End -->
<!-- 月报管理-月报上传 Begin -->
<script type="text/javascript" src="${ctx}/js/views/yuebgl/yuebsc/yuebsc.js"></script>
<!-- 月报管理-月报上传 End -->
<!------------------------------------------------工作流---------------------------------------------------------------->
<!-- 三级审批-基础信息维护 -->
<script type="text/javascript" src="${ctx}/js/views/gongzl/jcxxwh/jcxxwh.js"></script>
<!------------------------------------------------系统管理-------------------------------------------------------------->
<!-- 系统管理 Begin -->
<script type="text/javascript" src="${ctx}/js/views/xitgl/ziyxx/ziyxx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/renyxx/renyxx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/diancxx/diancxx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/meikdq/meikdq.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/gongys/gongys.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/meikxx/meikxx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/chezxx/chezxx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/liclx/liclx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/lic/lic.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/meizxx/meizxx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/pinzxx/pinzxx.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/yunsdw/yunsdw.js"></script>
<script type="text/javascript" src="${ctx}/js/views/xitgl/jiz/jiz.js"></script>
<!-- 系统管理 End -->

<!-- 配煤 start-->
<%-- <script type="text/javascript" src="${ctx}/js/views/peim/peimei.js"></script>
<script type="text/javascript" src="${ctx}/js/views/peim/yijpeimei.js"></script>
<script type="text/javascript" src="${ctx}/js/views/peim/yijpeimeisearch.js"></script>

<script type="text/javascript" src="${ctx}/js/views/peim/xinxweih/yunscd.js"></script>
<script type="text/javascript" src="${ctx}/js/views/peim/xinxweih/meiyuan.js"></script>
<script type="text/javascript" src="${ctx}/js/views/peim/xinxweih/meishan.js"></script>

<script type="text/javascript" src="${ctx}/js/views/peim/diaoyjh.js"></script>
<script type="text/javascript" src="${ctx}/js/views/peim/chedtz.js"></script>
<script type="text/javascript" src="${ctx}/js/views/peim/meiccp.js"></script> --%>
<!-- 配煤end -->
<script type="text/javascript" src="${ctx}/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript">
    $(document).ajaxComplete(
            function (event, jqXHR, options) {
                if (jqXHR.responseText == "sessionTimeout") {
                    alert("请重新登录！")
                    window.location.href = "http://" + window.location.host
                            + "/fuelmis/";
                }
            });
    $(document).ready(
            function () {
                jQuery.validator.addMethod(
                                "isMobile",
                                function (value, element) {
                                    var length = value.length;
                                    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
                                    return this.optional(element)
                                            || (length == 11 && mobile
                                                    .test(value));
                                }, "请输入正确格式的手机号码");

                jQuery.validator.addMethod(
                                "isPhone",
                                function (value, element) {
                                    var length = value.length;
                                    var phone = /(^(\d{3,4}-)?\d{6,8}$)|(^(\d{3,4}-)?\d{6,8}(-\d{1,5})?$)|(\d{11})/;
                                    return this.optional(element)|| (phone.test(value));
                                }, "请输入正确格式的电话号码");

                jQuery.validator.addMethod("isZipCode", function (value,element) {
                    var zipCode = /^[0-9]{6}$/;
                    return this.optional(element)|| (zipCode.test(value));
                }, "请输入正确格式的邮政编码");
            });

</script>
</html>
