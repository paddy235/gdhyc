<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style>
    * {
        margin: 0;
        padding: 0;
    }

    body {
        font-size: 12px;
    }

    a {
        font-weight: bold;
    }

    li {
        list-style: none;
    }

    #top {
        height: 40px;
        line-height: 40px;
        position: relative;
        color: #ddd;
        padding-left: 10px;
        margin-top: 10px;
        font-weight: bold;
        background-color: #333;
        background-image: linear-gradient(#4d4d4d, #2a2a2a);
        border-radius: 5px;
    }

    #top li {
        float: left;
        height: 40px;
        line-height: 40px;
        font-size: 15px;
        margin-right: 20px;
        width: 100px;
        text-align: center;
        cursor: pointer;
    }

    #top .liactived {
        background: #000;
        border-radius: 3px;
        color: #fff;
    }

    #nowLocation {
        padding-left: 10px;
        height: 30px;
        line-height: 30px;
        margin-top: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    #scrollDiv {
        margin-top: 10px;
        background: #d6f6fd;
        height: 30px;
        line-height: 30px;
        border: 1px solid #ddd;
        font-size: 15px;
        font-weight: bold;
    }

    .firstlayer {
        width: 100%;
        margin: 10px 0;
        height: 300px;
    }

    #fl-left {
        width: 49%;
        height: 300px;
        margin-right: 10px;
        float: left;
    }

    @media all and (max-width: 1300px) {
        #fl-left {
            width: 100%;
            height: 300px;
            margin-right: 10px;
            float: left;
        }
    }

    #fl-middle {
        width: 49%;
        height: 300px;
        margin-right: 10px;
        float: left;
    }

    #fl-right {
        width: 49%;
        height: 300px;
        float: left;
    }

    #fl-rightf {
        width: 50%;
        height: 300px;
        float: left;
        border: 1px solid #ddd;
        border-radius: 3px;
    }

    @media all and (max-width: 1300px) {
        #fl-rightf {
            width: 100%;
            height: 300px;
            float: left;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
    }

    .secondlayer {
        margin: 10px 5px 10px 0;
        height: 610px;
        display: inline-block;
        width: 49%
    }

    .thirdlayer {
        margin: 10px 5px 10px 0;
        height: 610px;
        display: inline-block;
        width: 49.9%
    }

    .fourthlayer {
        margin: 10px 0;
        height: 300px;
        margin-bottom: 40px;
    }

    .glyphicon-arrow-up {
        color: green;
    }

    .glyphicon-arrow-down {
        color: red;
    }

    #gyspie {
        height: 267px;
    }

    #mzpie {
        height: 267px;
    }

    #sl-left {
        width: 100%;
        height: 300px;
        margin-right: 10px;
        float: left;
    }

    .sll-navs {
        height: 30px;
        border-bottom: 1px solid #ddd;
        color: #337ab7;
    }

    .sll-navs li {
        float: left;
        height: 29px;
        line-height: 29px;
        font-size: 12px;
        font-weight: bold;
        padding: 0 10px;
        cursor: pointer;
        border: 1px solid #f5f5f5;
        border-bottom-color: #ddd;
    }

    .sliactive {
        background: #fff;
        height: 29px;
        line-height: 29px;
        color: #555;
        border: 1px solid #ddd;
        border-top-left-radius: 3px;
        border-top-right-radius: 3px;
        border-bottom-color: #fff;
    }

    #sl-right {
        width: 100%;
        height: 300px;
        float: left;
    }

    @media all and (min-width: 1300px) {
        #sl-right {
            margin-top: 10px;
        }
    }

    .tab-border {
        border: 1px solid #ddd;
        border-bottom-left-radius: 3px;
        border-bottom-right-radius: 3px;
        border-top: none;
    }

    #kucundiv {
        height: 268px;
    }

    #laihaomeidiv {
        height: 268px;
    }

    #kucunqkline {
        height: 237px;
    }

    #laihaomeiqkline {
        height: 237px;
    }

    @media all and (max-width: 1300px) {
        .secondlayer {
            margin: 10px 5px 10px 0;
            height: 310px;
            display: inline-block;
            width: 100%
        }

        #sl-left {
            width: 49%;
            height: 300px;
            margin-right: 10px;
            float: left;
        }

        #sl-right {
            width: 49%;
            height: 300px;
            float: left;
        }
    }

    /*#tl-left{width:100%;height:300px;margin-right:10px;float:left;}*/
    #tl-right {
        width: 100%;
        height: 610px;
        float: left;
    }

    #kucunmeiline {
        height: 267px;
    }

    #fenkrcbmdjdiv {
        height: 268px;
    }

    #ructrmjdiv {
        height: 268px;
    }

    #rucbmdjdiv {
        height: 268px;
    }

    #fenkrcbmdjline {
        height: 237px;
    }

    #ructrmjline {
        height: 237px;
    }

    #rucbmdjline {
        height: 237px;
    }

    @media all and (max-width: 1300px) {
        .thirdlayer {
            margin: 10px 5px 10px 0;
            height: 610px;
            display: inline-block;
            width: 100%
        }

        #tl-right {
            width: 100%;
            height: 610px;
            float: left;
        }

    }

    /* tab panel 不显示Echarts  图表问题   */
    /* bootstrap hack: fix content width inside hidden tabs */
    #sl-left .tab-content > .tab-pane,
    .pill-content > .pill-pane {
        display: block; /* undo display:none          */
        height: 0; /* height:0 is also invisible */
        overflow-y: hidden; /* no-overflow                */
    }

    #sl-left .tab-content > .active,
    .pill-content > .active {
        height: 258px; /* let the content decide it  */
    }

    /* bootstrap hack end */

    #tl-right .tab-content > .tab-pane,
    .pill-content > .pill-pane {
        display: block; /* undo display:none          */
        height: 0; /* height:0 is also invisible */
        overflow-y: hidden; /* no-overflow                */
    }

    #tl-right .tab-content > .active,
    .pill-content > .active {
        height: 258px; /* let the content decide it  */
    }

    /* bootstrap hack end */

    #fol-left {
        width: 49%;
        height: 300px;
        margin-right: 10px;
        float: left;
    }

    #fol-right {
        width: 50%;
        height: 298px;
        float: left;
        border: 1px solid #ddd;
        border-radius: 3px;
    }

    @media all and (max-width: 1300px) {
        #fol-left {
            width: 100%;
            height: 300px;
            margin-right: 10px;
            float: left;
        }

        #fol-right {
            width: 100%;
            height: 298px;
            float: left;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
    }

    .nav {
        margin-bottom: 0;
    }

    ul, ol {
        padding: 0;
        margin: 0;
    }

    #pengding {
        width: 25%;
        height: 38%;
        position: fixed;
        bottom: -300px;
        right: 30px;
        transition: bottom 2s;
        -moz-transition: bottom 2s; /* Firefox 4 */
        -webkit-transition: bottom 2s; /* Safari 和 Chrome */
        -o-transition: bottom 2s; /* Opera */
        border: 1px solid #337ab7;
    }

    #pengdingT {
        padding: 5px 15px;
    }

    #pengdingB {
        height: 200px;
        overflow-y: auto;
    }
    .title {
        font-size: 14px;
        color: #fff;
        background-color: #337ab7;
    }
    .close {
        width: 30px;
        text-align: center;
        color: white;
        border: 1px solid white;
    }
    #dataShow {
        display: inline-block;
        width: 33%;
        margin-right: 20px;
    }
</style>
<div class="container-fluid" style="padding:10px;" ng-controller="rucbddb">
    <!-- 主内容区第二块 -->
    <div class="secondlayer">
        <!-- 主内容区第三块 -->
        <div id="tl-right">
            <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
                起始日期
                <input id="datepicker" class="fydatepicker" type="text" ng-model="search.sDate"
                       style="margin-top:4px;height:22px;line-height:22px;width:100px;">
                截止日期
                <input id="datepicker1" class="fydatepicker" type="text" ng-model="search.eDate"
                       style="margin-top:4px;height:22px;line-height:22px;width:100px;">
                <button style="margin-left: 20px;float: left;" ng-click="searchData()" class="btn btn-success">
                    <i class="icon-search icon-white"></i> 刷新
                </button>
                <button style="margin-left: 5px;float: left;" ng-click="printPage()" class="btn btn-primary">
                    <i class="icon-print icon-white"></i> 打印
                </button>
                <button style="margin-left: 5px;float: left;" my-export="国电电力发展股份有限公司发电供热耗用出库报表.xls" class="btn btn-primary">
                    <i class="icon-download-alt icon-white"></i> 导出
                </button>
            </div>
            <%--<div class="sll-navs"
                 style="padding:0 5px;font-size:12px;line-height:30px;height:30px;background:#f5f5f5;border-radius:3px;">
                <b>来煤信息</b>|日期:
                <input id="lmxx" class="fydatepicker" type="text"
                       style="margin-top:4px;height:22px;line-height:22px;width:100px;">
                <span style="float:right;margin-right:10px;">单位:MJ/KG,元/吨,%</span>
            </div>--%>
            <div class="panel-body" style="padding:0;height:268px;">
                <div class="row-fluid" id="report" style="width: 100%;margin-left: auto;margin-right: auto;overflow: auto;"></div>
                <div id="pagination_box" class="pagination pagination-right" style="width: 100%;margin-left: auto;margin-right: auto;">
                    <ul id="pagination_zc" ></ul>
                </div>
            </div>
        </div>
    </div>
    <div class="secondlayer">
        <div id="sl-left">
           <%-- <ul class="sll-navs" style="background:#f5f5f5;border-radius:3px;">
                <li class="sliactive" onclick="showSecLeft(1,this)">库存情况曲线图</li>
            </ul>--%>
            <div class="span12 tab-border" style="margin-left:0;">
                <div id="kucundiv">
                   <%-- <div class="panel panel-default" style="border: none;">
                            <span style="float:right;margin-right:10px;">单位：元/吨</span>
                        </div>--%>
                        <div class="panel-body" style="padding:0;">
                            <div id="rucbmdjline"></div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("#datepicker").datepicker({
            format : 'yyyy-mm',
            minViewMode : 1,
            language : "zh-CN",
            autoclose : true
        });
        $("#datepicker1").datepicker({
            format : 'yyyy-mm',
            minViewMode : 1,
            language : "zh-CN",
            autoclose : true
        });

    });
</script>