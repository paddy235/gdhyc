<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<style>
    #mtab td {
        padding-top: 2px;
        padding-bottom: 0px;
    }

    #mtab .textmspan {
        color: #53868B;
        display: inline-block;
    }

    #ci {
        width: 100% !important;
    }

/*    .mymodal {
        position: fixed;
        top: 10%;
        left: 63%;
        z-index: 1050;
        width: 109px;
        margin-left: -280px;
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
    }*/

    .ui-datepicker-calendar {
        display: none;
    }

    th {
        text-align: center !important;
    }

    td {
        text-align: center !important;
        margin: 0px !important;
        padding: 0px !important;
        vertical-align: inherit !important;
    }

    td input, td select {
        width: 100% !important;
        height: 37px !important;
        margin: 0px !important;
        padding: 0px !important;
        border: 0px !important;
        text-align: center !important;
        font-size: 16px !important;
        background-color: transparent !important;
        line-height: 35px !important;
    }

    td select {
        padding: 8px 0 !important;
    }

    table span {
        padding-top: 5px !important;
    }
</style>
<div class="tab-pane" ng-controller="jiesdxgCtrl">
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <form class="form-horizontal ng-pristine ng-valid">
                    <label style="width: 40px;margin-right:5px;" class="control-label">??????:</label>
                    <select ng-model="search.diancid" style="float: left;width: 120px;"
                            ng-options="option.value as option.name for option in diancList"
                            ng-change="getJiesdbh()"></select>
                    <label style="width: 100px;margin-right:5px;" class="control-label">???????????????:</label>
                    <select ng-model="search.jiesdid" style="float: left;width: 200px;" id="selJiesbh" name="selJiesbh"
                            ng-options="option.value as option.name for option in jiesdbhList"></select>
                    <button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
                        <i class="icon-search icon-white"></i> ??????
                    </button>
                    <button style="margin-left: 10px;" id="refresh" ng-click="save()" class="btn btn-primary">
                        <i class=" icon-check icon-white"></i> ??????
                    </button>
                    <button style="margin-left: 10px;" ng-click="shanchu()" class="btn btn-danger" id="delete">
                        <i class="icon-edit icon-white"></i> ??????
                    </button>
                    <button style="margin-left: 10px;" id="reback" ng-click="tijiao()" class="btn">
                        <i class=" icon-repeat"></i> ??????
                    </button>
                </form>
            </div>
        </div>
        <div class="row-fluid">
            <div style="text-align:center;"><h4>?????????????????????</h4></div>
            <div style="margin-bottom:20px;">
                <div style="float:left;margin-left:5px;">????????????: <span>{{data.DIANCMC}}</span></div>
                <div style="float:right;margin-right:5px;">??????: <input type="text" ng-model="data.JIESBH"/></div>
                <div style="clear:both;"></div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="table table-bordered" id="mtab">
                <tr>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.GONGYSMC}}</span></td>
                    <td>??????:</td>
                    <td colspan="2"><span class="textmspan">{{data.CHEZMC}}</span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.GONGYSMC}}</span></td>
                </tr>
                <tr>
                    <td>????????????:</td>
                    <td colspan="2"><span style="color:#53868B;">{{data.ZHONGCSJ}}</span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span style="color:#53868B;">{{data.QINGCSJ}}</span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.KAIHYH}}</span></td>
                </tr>
                <tr>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.PINZMC}}</span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.DIANCMC}}</span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.ZHANGH}}</span></td>
                </tr>
                <tr>
                    <td>????????????:</td>
                    <td colspan="2"><span style="color:#53868B;">{{data.PIAOZ}}</span>???&nbsp;&nbsp;&nbsp;<span
                            style="color:#53868B;">{{data.CHES}}</span>???
                    </td>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.DIANCMC}}</span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span></span></td>
                </tr>
                <tr>
                    <td>????????????:</td>
                    <td colspan="2"><span class="textmspan">{{data.DAIBCH}}</span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span></span></td>
                    <td>????????????:</td>
                    <td colspan="2"><span></span></td>
                </tr>
                <tr>
                    <td style="text-align: left;">????????????:<span class="textmspan">{{data.JIESJG}}</span></td>
                    <td style="text-align: center;">????????????</td>
                    <td style="text-align: center;">????????????</td>
                    <td style="text-align: center;">????????????</td>
                    <td style="text-align: center;">????????????</td>
                    <td style="text-align: center;">????????????</td>
                    <td style="text-align: center;">????????????(???/???)</td>
                    <td colspan="2" style="text-align: center;">????????????(???)</td>
                </tr>
                <tr>
                    <td>????????????(???)</td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">{{data.PIAOZ}}</span></td>
                    <td><span class="textmspan">{{data.JINGZ}}</span></td>
                    <td><span class="textmspan">{{data.JIESSL}}</span></td>
                    <td><span class="textmspan">{{data.JIESSLCY}}</span></td>
                    <td><span class="textmspan">{{data.JIESJG}}</span></td>
                    <td colspan="2"><span class="textmspan">0.0</span></td>
                </tr>
                <tr>
                    <td>Qnetar(Kcal/kg)</td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">{{data.REZ_CF}}</span></td>
                    <td><span class="textmspan">{{data.JIESRZ}}</span></td>
                    <td><span class="textmspan">{{data.REZC}}</span></td>
                    <td><span class="textmspan">{{data.REZZK}}</span></td>
                    <td colspan="2"><span class="textmspan">{{data.REZZJJE}}</span></td>
                </tr>
                <tr>
                    <td>Star(%)</td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">{{data.LIUF_CF}}</span></td>
                    <td><span class="textmspan">{{data.JIESLF}}</span></td>
                    <td><span class="textmspan">{{data.LIUFC}}</span></td>
                    <td><span class="textmspan">{{data.LIUFZK}}</span></td>
                    <td colspan="2"><span class="textmspan">{{data.LIUFZJJE}}</span></td>
                </tr>
                <tr>
                    <td>????????????</td>
                    <td>???????????????</td>
                    <td>??????</td>
                    <td>?????????</td>
                    <td>????????????</td>
                    <td>??????</td>
                    <td>??????</td>
                    <td colspan="2">??????</td>
                </tr>
                <tr>
                    <!-- <td><input type="text" ng-model="data.JIESSL" style="color:blue" ng-change="calculateMEIKJE()" /></td>-->
                    <td><span class="textmspan">{{data.JIESSL}}</span></td>
                    <td><span class="textmspan">{{data.BUHSDJ}}</span></td>
                    <td><input type="text" ng-model="data.MEIKJE" style="color:blue" ng-change="calculateMEIKHJ()"/>
                    </td>
                    <td><input type="text" ng-model="data.BUKK" style="color:blue" ng-change="calculateMEIKHJ()"/></td>
                    <td><input type="text" ng-model="data.MEIKJE" style="color:blue" ng-change="calculateMEIKHJ()"/>
                    </td>
                    <td><span class="textmspan">0.17</span></td>
                    <td><input type="text" ng-model="data.SHUIK" style="color:blue" ng-change="calculateMEIKHJ()"/></td>
                    <td colspan="2"><input type="text" ng-model="data.MEIKHJ" onfocus=this.blur()></td>
                    <!--  <td colspan="2"><input type="text" ng-model="data.MEIKJE+data.SHUIK" style="color:blue" onfocus=this.blur() /></td> -->
                </tr>
                <tr>
                    <td>??????????????????</td>
                    <td colspan="8"><span class="textmspan">???{{data.MEIKDJDX}}</span></td>
                </tr>
                <tr>
                    <td>??????</td>
                    <td>?????????</td>
                    <td>????????????</td>
                    <td>????????????</td>
                    <td>??????????????????</td>
                    <td>???????????????</td>
                    <td>??????</td>
                    <td>??????</td>
                    <td>???????????????</td>
                </tr>
                <tr>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                    <td><span class="textmspan">0.0</span></td>
                </tr>
                <tr>
                    <td>?????????????????????</td>
                    <td colspan="8"><span class="textmspan">????????????</span></td>
                </tr>
                <tr>
                    <td>????????????</td>
                    <td colspan="5"><span class="textmspan">???{{data.MEIKDJDX}}</span></td>
                    <td>????????????</td>
                    <td colspan="2"><span class="textmspan">{{data.MEIKHJ}}</span></td>
                </tr>
                <tr>
                    <td>??????</td>
                    <td colspan="4"><input type="text" ng-model="data.BEIZ" style="color:blue" /><!-- <span class="textmspan">{{data.BEIZ}}</span> --></td>
                    <td>????????????</td>
                    <td><span class="textmspan">0.0</span></td>
                    <td>????????????</td>
                    <td><span class="textmspan">0.0</span></td>
                </tr>
                <tr>
                    <td>??????????????????:</td>
                    <td><span class="textmspan">{{data.CHANGRLJBR}}</span></td>
                    <td colspan="2">?????????????????????: <span>{{data.CHANGRLJSRQ}}</span></td>
                    <td>????????????:</td>
                    <td><span class="textmspan">{{data.JINGZ}}</span></td>
                    <td>??????????????????:</td>
                    <td><span class="textmspan">{{data.JIESSLCY}}</span></td>
                    <td></td>
                </tr>
            </table>

        </div>

    </div>
    <!-- ??????????????? -->
    <div my-progress="showProgress"></div>
   <%-- <div class="mymodal fade" id="myModal_Edit" tabindex="-1" role="dialog" data-backdrop="static" style="display:none;"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <img id="ci" class="img operation" src="/fuelmis/images/circle-bar.gif"/>
    </div>--%>
</div>