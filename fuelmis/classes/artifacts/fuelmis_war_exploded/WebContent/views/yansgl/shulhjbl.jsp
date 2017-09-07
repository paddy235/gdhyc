<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
 #example th,td{white-space: nowrap;padding:5px 15px;}
</style>
   <div class="tab-pane" ng-controller="shulhjblCtrl">
   	<div id="tabs" style="margin-top:20px;">
<!--      <div class="navbar navbar-inner block-header">
          <div class="muted pull-left">{{title}}</div> 
    </div> -->
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				 <label class="control-label" style="width:50px;margin-right:5px;margin-left:10px;">批次号:</label>
				 	<input type="text"  style="float: left;"  placeholder="请输入批次号"  ng-model = "search.pich"/>
				 <label class="control-label" style="width:50px;margin-right:5px;margin-left:10px;">车号:</label>
				 	<input type="text" style="float: left;" placeholder="请输入车号"  ng-model="search.cheh"/>
				<button style="margin-left: 5px;float: left;"  id="refresh" ng-click="getShulhjblAll();" class="btn btn-info">查询 <i class="icon-search icon-white"></i></button>
			</div>
		</div>
		<div class="row-fluid" style="width: 100%;height: 100%;overflow: auto;">
					<table class="table table-bordered table-condensed table-hover" id="example">
						<tr>
							 <th style="width:5%; text-align: center">ID</th>
							 <th style="width:5%; text-align: center">车号</th>
							 <th style="width:5%; text-align: center">票据号</th>
							 <th style="width:5%; text-align: center">毛重</th>
							 <th style="width:5%; text-align: center">皮重</th>
							 <th style="width:5%; text-align: center">票重</th>
							 <th style="width:8%; text-align: center">煤矿名称</th>
							 <th style="width:8%; text-align: center">供应商名称</th>
							 <th style="width:5%; text-align: center">煤矿信息表ID</th>
							 <th style="width:5%; text-align: center">供应商表ID</th>
							 <th style="width:5%; text-align: center">合同表ID</th>
							 <th style="width:5%; text-align: center">发站</th>
							 <th style="width:5%; text-align: center">发站ID</th>
							 <th style="width:4%; text-align: center">到站</th>
							 <th style="width:5%; text-align: center">到站ID</th>
							 <th style="width:5%; text-align: center">计划口径</th>
							 <th style="width:5%; text-align: center">计划口径ID</th>
							 <th style="width:4%; text-align: center">品种</th>
							 <th style="width:5%; text-align: center">品种表ID</th>
							 <th style="width:5%; text-align: center">运输方式</th>
							 <th style="width:5%; text-align: center">运输方式表ID</th>
							 <th style="width:10%; text-align: center">电厂信息表ID</th>
							 <th style="width:10%; text-align: center">操作类型</th>
							 <th style="width:10%; text-align: center">操作人员</th>
							 <th style="width:10%; text-align: center">操作时间</th>
							 <th style="width:10%; text-align: center">卸货时间</th>
							 <th style="width:10%; text-align: center">状态</th>
							 <th style="width:10%; text-align: center">编码</th>
							 <th style="width:10%; text-align: center">盈吨</th>
							 <th style="width:10%; text-align: center">盈亏</th>
							 <th style="width:10%; text-align: center">运损</th>
							 <th style="width:10%; text-align: center">扣吨</th>
							 <th style="width:10%; text-align: center">扣水</th>
							 <th style="width:10%; text-align: center">扣杂</th>
							 <th style="width:10%; text-align: center">扣毛重</th>
							 <th style="width:10%; text-align: center">总扣吨</th>
							 <th style="width:10%; text-align: center">亏吨</th>
							 <th style="width:10%; text-align: center">煤厂</th>
							 <th style="width:10%; text-align: center">三方数量</th>
							 <th style="width:10%; text-align: center">车次</th>
							 <th style="width:10%; text-align: center">合并状态</th>
							 <th style="width:10%; text-align: center">ID</th>
							 <th style="width:10%; text-align: center">车皮表ID</th>
							 <th style="width:10%; text-align: center">轻车时间</th>
							 <th style="width:10%; text-align: center">轻车衡号</th>
							 <th style="width:10%; text-align: center">轻车检斤员</th>
							 <th style="width:10%; text-align: center">重车时间</th>
							 <th style="width:10%; text-align: center">重车衡号</th>
							 <th style="width:10%; text-align: center">重车检斤员</th>
							 <th style="width:10%; text-align: center">状态</th>
							 <th style="width:10%; text-align: center">业务环节</th>
							 <th style="width:10%; text-align: center">当前操作人ip</th>
							 <th style="width:10%; text-align: center">当前操作时间</th>
							 <th style="width:10%; text-align: center">当前操作类型</th>
							 <th style="width:10%; text-align: center">当前操作人员</th>
						</tr>
						
						<!-- 查询所有 -->
						<tr ng-repeat="data in list track by $index">
						<td>{{data.ID}}</td>
						<td>{{data.CHEPH}}</td>
						<td>{{data.PIAOJH}}</td>
						<td>{{data.MAOZ}}</td>
						<td>{{data.PIZ}}</td>
						<td>{{data.PIAOZ}}</td>
						<td>{{data.MEIKMC}}</td>
						<td>{{data.MEIKXXB_ID}}</td>
						<td>{{data.GONGYSMC}}</td>
						<td>{{data.GONGYSB_ID}}</td>
						<td>{{data.CAIGDDB_ID}}</td>
						<td>{{data.FAZ}}</td>
						<td>{{data.FAZ_ID}}</td>
						<td>{{data.DAOZ}}</td>
						<td>{{data.DAOZ_ID}}</td>
						<td>{{data.JIHKJ}}</td>
						<td>{{data.JIHKJB_ID}}</td>
						<td>{{data.PINZ}}</td>
						<td>{{data.PINZB_ID}}</td>
						<td>{{data.YUNSFS}}</td>
						<td>{{data.YUNSFSB_ID}}</td>
						<td>{{data.DIANCXXB_ID}}</td>
						<td>{{data.CAOZLX}}</td>
						<td>{{data.CAOZRY}}</td>
						<td>{{data.CAOZSJ}}</td>
						<td>{{data.XIEHSJ}}</td>
						<td>{{data.ZHUANGT}}</td>
						<td>{{data.SAMCODE}}</td>
						<td>{{data.YINGD}}</td>
						<td>{{data.YINGK}}</td>
						<td>{{data.YUNS}}</td>
						<td>{{data.KOUD}}</td>
						<td>{{data.KOUS}}</td>
						<td>{{data.KOUZ}}</td>
						<td>{{data.KOUM}}</td>
						<td>{{data.ZONGKD}}</td>
						<td>{{data.KUID}}</td>
						<td>{{data.MEIC}}</td>
						<td>{{data.SANFSL}}</td>
						<td>{{data.CHEC}}</td>
						<td>{{data.ISCONVERGED}}</td>
						<td>{{data.ID}}</td>
						<td>{{data.CHEPB_ID}}</td>
						<td>{{data.QINGCSJ}}</td>
						<td>{{data.QINGCHH}}</td>
						<td>{{data.QINGCJJY}}</td>
						<td>{{data.ZHONGCSJ}}</td>
						<td>{{data.ZHONGCHH}}</td>
						<td>{{data.ZHONGCJJY}}</td>
						<td>{{data.ZHUANGT}}</td>
						<td>{{data.YEWHJ}}</td>
						<td>{{data.DANQCAOZRIP}}</td>
						<td>{{data.DANQCAOZSJ}}</td>
						<td>{{data.DANQCAOZLX}}</td>
						<td>{{data.DANQCAOZRY}}</td>
					 </tr>
			 	</table>
			</div>
		</div>
	</div>
</div>