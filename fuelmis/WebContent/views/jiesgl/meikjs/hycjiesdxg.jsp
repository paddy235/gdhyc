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
        background-color: #F0F0F0 !important;
        line-height: 35px !important;
    }

    td select {
        padding: 8px 0 !important;
    }

    table span {
        padding-top: 5px !important;
    }
</style>
<div class="tab-pane" ng-controller="hycmeikjsxg_new1">
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <form class="form-horizontal ng-pristine ng-valid">
                    <!-- <label style="width: 40px;margin-right:5px;" class="control-label">单位:</label>
                    <select ng-model="search.diancid" style="float: left;width: 120px;"
                            ng-options="option.value as option.name for option in diancList"
                            ng-change="getJiesdbh()"></select> -->
                     <label style="width: 100px;margin-right:5px;" class="control-label">结算单编号:</label>
                    <select ng-model="jiesdid" style="float: left;width: 200px;" id="selJiesbh" name="selJiesbh"
                            ng-options="option.value as option.name for option in jiesdbhList"></select> 
                    <button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
                        <i class="icon-search icon-white"></i> 刷新
                    </button> 
                    <button style="margin-left: 10px;" id="refresh" ng-click="save()" class="btn btn-primary">
                        <i class=" icon-check icon-white"></i> 保存
                    </button>
                    <button style="margin-left: 10px;" ng-click="deleteByjiesdbh()" class="btn btn-danger" id="delete">
                        <i class="icon-edit icon-white"></i> 删除
                    </button>  
                    <button style="margin-left: 10px;" id="reback" ng-click="tijiao()" class="btn">
                        <i class=" icon-repeat"></i> 提交
                    </button>
                </form>
            </div>
        </div>
        <div class="row-fluid">
            <div style="text-align:center;"><h4>燃料统一结算单</h4></div>
            <div style="margin-bottom:20px;">
                <div style="float:left;margin-left:5px;">填制单位: <span><input type="text" disabled ng-model="jiesd.DIANCMC"/></span></div>
                <div style="float:right;margin-right:5px;">编号: <input type="text" disabled ng-model="jiesd.JIESBH"/></div>
                <div style="clear:both;"></div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="table table-bordered" id="mtab">
                <tr>
                    <td>供货单位:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.GONGYSMC"  type="text"></span></td>
                    <td>发站:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.FAZMC"  type="text"></span></td>
                    <td>收款单位:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.SHOUKDW"  type="text"></span></td>
                </tr>
                <tr>
                    <td>发货日期:</td>
                    <td colspan="2"><span style="color:#53868B;"><input style="width:70px;float:left;" ng-model="jiesd.FAHKSRQ"  type="text">
                    											至<input style="width:70px;float:left;" ng-model="jiesd.FAHJZRQ"  type="text"></span></td>
                    <td>验收日期:</td>
                    <td colspan="2"><span style="color:#53868B;"><input style="width:70px;float:left;" ng-model="jiesd.YANSKSRQ"  type="text">
                    									至<input style="width:70px;float:left;" ng-model="jiesd.YANSJZRQ"  type="text"></span></td>
                    <td>开户银行:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.KAIHYH"  type="text"></span></td>
                </tr>
                <tr>
                    <td>货物名称:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.PINZ"  type="text"></span></td>
                    <td>原收货人:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" disabled ng-model="jiesd.DIANCMC"  type="text"></span></td>
                    <td>银行账号:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;"  ng-model="jiesd.ZHANGH"  type="text"></span></td>
                </tr>
                <tr>
                    <td>发运数量:</td>
                    <td colspan="1">
                    	<span style="color:#53868B;"><input style="width:110px;float:left;" ng-model="jiesd.PIAOZ"  type="text"></span>
                    	<span style="color:#53868B;"><input style="width:110px;float:left;" ng-model="jiesd.CHES"  type="text"></span>
                    </td>
                    <td>
	                    <span style="color:#53868B;float:left;">吨</span>
	                    <br/>
	                    <br/>
	                    <span style="color:#53868B;float:left;">车</span>
                    </td>
                    <td>现收货人:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" disabled ng-model="jiesd.DIANCMC"  type="text"></span></td>
                    <td>发票编号:</td>
                    <td colspan="2"><span><input style="width:150px;float:left;" ng-model="jiesd.FAPBH"  type="text"></span></td>
                </tr>
                <tr>
                    <td>代表车号:</td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.DAIBCH"  type="text"></span></td>
                    <td>验收编号:</td>
                    <td colspan="2"><span><input style="width:150px;float:left;" ng-model="jiesd.YANSBH"  type="text"></span></td>
                    <td>付款方式:</td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td style="text-align: left;">含税单价:<span class="textmspan">{{jiesd.JIESJG}}</span></td>
                    <td style="text-align: center;">合同标准</td>
                    <td style="text-align: center;">供方标准</td>
                    <td style="text-align: center;">厂方验收</td>
                    <td style="text-align: center;">结算标准</td>
                    <td style="text-align: center;">相差数量</td>
                    <td style="text-align: center;">折价标准(元/吨)</td>
                    <td colspan="2" style="text-align: center;">折合金额(元)</td>
                </tr>
               <!--  <tr>
                    <td>结算数量(吨)</td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.JIESSL"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.GONGFBZ_JIESSL"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.CHANGFYS_JIESSL"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.JIESBZ_JIESSL"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.XIANGCSL_JIESSL"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.ZHEJBZ_JIESSL"  type="text"></span></td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.ZHEHJE_JIESSL"  type="text"></span></td>
                </tr> -->
                <tr ng-repeat="zhib in jiesd.zhibList">
                    <td>{{zhib.LEIB|zhibShow}}<%--(zhib.DANW)--%></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="zhib.HETBZ"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="zhib.GONGF"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="zhib.CHANGF"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="zhib.JIES"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="zhib.YINGK"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="zhib.ZHEJBZ"  type="text"></span></td>
                    <td colspan="2"><span class="textmspan"><input style="width:150px;float:left;" ng-model="zhib.ZHEJJE"  type="text"></span></td>
                </tr>
                <tr>
                    <td>结算数量</td>
                    <td>合同考核价</td>
                    <td>不含税单价</td>
                    <td>金额</td>
                    <td>补扣价款</td>
                    <td>应付不含税</td>
                    <td>税率</td>
                    <td>应付税款</td>
                    <td>应付合计</td>
                </tr>
                <tr>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.JIESSL"  ng-change=changefirst() type="text"></span></td>
                    <td><span class="textmspan">{{jiesd.HETJG}}</span></td>
                    <td>{{jiesd.BUHSDJ}}</td>
                    <td><span class="textmspan">{{jiesd.MEIKHJ}}</span>
                    </td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.BUKK" ng-change=changesecond() type="text"></span></td>
                    <td><input style="width:150px;float:left;" ng-model="jiesd.MEIKJE" ng-change=changethird() type="text"></td>
                    <td><span class="textmspan">{{jiesd.SHUIL}}</span></td>
                    <td><span class="textmspan">{{jiesd.SHUIK}}</span></td>
                    <td>{{jiesd.JIAKHJ}}</td>
                    </tr>
                <tr>
                    <td>煤款合计大写</td>
                    <td colspan="8"><span class="textmspan">￥{{jiesd.MEIKDJDX}}</span></td>
                </tr>
                <tr>
                    <td>运费</td>
                    <td>运杂费</td>
                    <td>矿区运费</td>
                    <td>矿区杂费</td>
                    <td>补扣以前运费</td>
                    <td>不含税运费</td>
                    <td>税率</td>
                    <td>税款</td>
                    <td>运杂费合计</td>
                </tr>
                <tr>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.YUNF"   type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.YUNZF"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.KUANGQYF"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.KUANGQZF"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.BUKQYF"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.YUNFBHS"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.SHUIL1"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.SHUIK1"  type="text"></span></td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.YUNZFHJ"  type="text"></span></td>
                </tr>
                <tr>
                    <td>运杂费合计大写</td>
                    <td colspan="8"><span class="textmspan">￥{{jiesd.YUNZFHJDX}}</span></td>
                </tr>
                <tr>
                    <td>合计大写</td>
                    <td colspan="5"><span class="textmspan">￥{{jiesd.ZONGHJDX}}</span></td>
                    <td>合计小写</td>
                    <td colspan="2"><span class="textmspan">{{jiesd.ZONGHJ}}</span></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="4"><input type="text" ng-model="jiesd.BEIZ" style="color:blue" /><!-- <span class="textmspan">{{jiesd.BEIZ}}</span> --></td>
                    <td>拒付运费</td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.JUFYF"  type="text"></span></td>
                    <td>拒付杂费</td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.JUFZF"  type="text"></span></td>
                </tr>
                <tr>
                    <td>厂燃料经办人:</td>
                    <td><span class="textmspan"><span class="textmspan"><input disabled style="width:150px;float:left;" ng-model="jiesd.CHANGRLJBR"  type="text"></span></span></td>
                    <td colspan="2">厂燃料经办日期: <span><span class="textmspan"><input disabled style="width:150px;float:left;" ng-model="jiesd.CHANGRLJSRQ"  type="text"></span></span></td>
                    <td>过衡数量:</td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.GUOHSL"  type="text"></span></td>
                    <td>结算数量差异:</td>
                    <td><span class="textmspan"><input style="width:150px;float:left;" ng-model="jiesd.JIESSLCY"  type="text"></span></td>
                    <td></td>
                </tr>
            </table>

        </div>

    </div>
    <!-- 进度滚动条 -->
    <div my-progress="showProgress"></div>
</div>