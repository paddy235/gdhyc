<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style type="text/css">
    DIV {
        BORDER-WIDTH: 0px;
        PADDING: 0px;
    }

    #n-body {
        MARGIN: 0px auto;
        OVERFLOW: hidden;
        WIDTH: 650px;
        LINE-HEIGHT: 20px;
        HEIGHT: 20px;
    }

    #m-body {
        MARGIN: 0px auto;
        OVERFLOW: hidden;
        WIDTH: 650px;
        LINE-HEIGHT: 20px;
        HEIGHT: 20px;
    }

    body {
        margin-left: 0px;
        margin-top: 0px;
        margin-right: 0px;
        margin-bottom: 0px;
    }

    .tb_line01 {
        border: 1px solid #E8E8E8;
    }

    .tb_line02 {
        border: 1px solid #DADADA;
    }

    .tb_line03 {
        border: 1px solid #FFFFFF;
    }

    .bg03 {
        background-image: url(images/startpage/start_page_laihaocun_bg.gif);
        background-color: #CAD8E6;
        border: 1px solid #A6B9CD;
        background-repeat: repeat-x;
        background-position: top;

    }

    .bg02 {
        background-image: url(images/startpage/start_page_content_bg.gif);
        background-repeat: repeat-x;
        background-position: top;
        background-color: #A9BCD1;
    }

    .bg01 {
        background-color: #A9BCD1;
        background-image: url(images/startpage/start_page_link_bg.gif);
        background-repeat: repeat-x;
        background-position: top;
    }

    .quicklinks a:link {
        color: #194671;
        font-size: 14px;
        text-decoration: none;
    }

    .quicklinks a:visited {
        color: #194671;
        font-size: 14px;
        text-decoration: none;
    }

    .quicklinks a:hover {
        font-size: 14px;
        color: #FF6600;
        text-decoration: none;
    }

    .quicklinks a:active {
        color: #194671;
        font-size: 14px;
        text-decoration: none;
    }

    .laihaocun {
        font-family: "黑体", simhei;
        font-size: 16px;
    }

    .laihaocun a:link {
        color: #FF6600;
        text-decoration: none;
    }

    .laihaocun a:visited {
        color: #FF6600;
        text-decoration: none;
    }

    .laihaocun a:hover {
        color: #FF6600;
        text-decoration: underline;
    }

    .laihaocun a:active {
        color: #FF6600;
        text-decoration: none;
    }

    .tubiao {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
    }

    .tubiao a:link {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
    }

    .tubiao a:visited {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
    }

    .tubiao a:hover {
        font-size: 12px;
        color: #FF6600;
        text-decoration: none;
    }

    .tubiao a:active {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
    }

    .news {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
        border-bottom-width: 1px;
        border-bottom-style: solid;
        border-bottom-color: #8DB6DF;
    }

    .news a:link {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
    }

    .news a:visited {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
    }

    .news a:hover {
        font-size: 12px;
        color: #FF6600;
        text-decoration: none;
    }

    .news a:active {
        color: #194671;
        font-size: 12px;
        text-decoration: none;
    }

</style>
<div class="panel panel-default" ng-controller="wenjtzCtrl">
    <div class="panel-heading" style="padding:0 5px;font-size:12px;line-height:30px;height:30px;">
        <b>通知</b>&nbsp;&nbsp;|年月:
        <input id="datepicker" ng-model="search.riq" type="text" ng-change="searchData()"
               style="margin-top:4px;height:22px;line-height:22px;width:70px;">
        <span style="float:right;margin-right:10px;"></span>
    </div>
    <div class="panel-body" style="padding:0;height:268px;">
        <%-- <div id="mzpie" >
         </div>--%>
        <div style="overflow: auto">
            <table bgcolor='#95ABC3' height='99%'>
                <tr>
                    <td width="230"></td>
                    <td width="58%">
                        <table cellpadding="0" cellspacing="0" style="font-size: 10pt" border="0" height="100%"
                               width="102%">
                            <tr>
                                <td height="10" width="584"></td>
                            </tr>
                            <tr>
                                <td align="center" style="font-size: 15pt" height="33" width="584"><b>文件通知</b></td>
                            </tr>
                            <tr>
                                <td align="center" valign="top" style="line-height: 150%" width="584" height="35">
                                    <hr>
                                    　
                                </td>
                            </tr>
                            <tr>
                                <td align="center" valign="top" style="line-height: 150%" width="584">
                                    <table style="padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"
                                           width="513">
                                        <tr>
                                            <td class="news" align="center"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px">
                                                <b><font size="2">序号</font></b></td>
                                            <td class="news" align="center"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"
                                                width="78"><b><font size="2">发布人员</font></b></td>
                                            <td class="news" align="center"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"
                                                width="240"><b><font size="2">标题</font></b></td>
                                            <td class="news" align="center"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px">
                                                <b><font size="2">发布时间</font></b></td>
                                        </tr>
                                        <tr ng-repeat="wenj in wenjList">
                                            <td class="news" align="center"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px">
                                                <b><font size="2">{{wenj.ROWNUM}}</font></b></td>
                                            <td class="news" align="left"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"
                                                width="78"><font size="2">{{wenj.RENY}}</font></td>
                                            <td class="news" align="left"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"
                                                width="240">
                                                <font size="2"><a href='javascript:void(0)' ng-click="wenjtz(wenj.ID)">{{wenj.BIAOT}}</a>
                                                </font>
                                            </td>
                                            <td class="news" align="left"
                                                style=" border-bottom-style: solid; border-bottom-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px">
                                                <font size="2">{{wenj.SHIJ}}</font></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td width="584">　</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td width="584">　</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td width="584">　</td>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                    <td width="135"></td>
                </tr>
            </table>
        </div>
        <style>
            .diss {
                display: block;
            }
        </style>
        <div ng-class="{in: myTost, diss: myTost}" data-backdrop="static" class="modal fade" id="myModal"
             tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-show="myTost" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <table>
                            <tr>
                                <td width=50></td>
                                <td width=90%>
                                    <table cellpadding="0" cellspacing="0" style="font-size: 10pt" bgcolor="#FFFFFF"
                                           border="0" height="100%" width="100%">
                                        <tr>
                                            <td height=10></td>
                                        </tr>
                                        <tr>
                                            <td align="center" style="font-size: 15pt" height="70">
                                                <b>{{wenjmx.BIAOT}}</b></td>
                                        </tr>
                                        <tr>
                                            <td height=30 align=right valign=top>发布人：{{wenjmx.RENY}}&nbsp;&nbsp;&nbsp;&nbsp;发布时间：{{wenjmx.SHIJ}}
                                                <hr>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left" valign=top style="line-height: 150%">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{wenjmx.NEIR}}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;
                                            <td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;
                                            <td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;
                                            <td>
                                        </tr>
                                        <tr ng-repeat="fuj in wenjmx.fujList">
                                            <td align="left">
                                                <hr>附件1:<a  href='javascript:void(0)' ng-click="loadfuj(fuj.URL)" href="#">{{fuj.YUANMC}}</a>
                                                <br>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width=50></td>
                            </tr>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <button class="btn" aria-hidden="true" ng-click="close()"><i class="icon-remove-circle"></i> 关闭
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
        <div ng-show="myTost" class="modal-backdrop fade in"></div>
    </div>
</div>




