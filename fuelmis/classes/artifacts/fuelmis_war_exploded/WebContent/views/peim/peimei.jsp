<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
 <!--
 .backgroundTbDiv { position: relative; width: 205px;}
 .backgroundTbDiv div{width: 100%;position: absolute;z-index: 9;}

 -->
 </style>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
   <div class="block" ng-controller="peimeiCtrl">
    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{peimeiTitle}}</div>
    </div>
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label style="width: 45px;float:left;" class="control-label">日期:</label>
				<input style="width: 150px;float: left;" id="datepicker" ng-model="search.riq" ng-change="refresh()" type="text">
	            <button style="margin-right: 50px;float: right;"  id="submit" ng-click="submit()" class="btn btn-success"><i class="icon-upload"></i>提交</button>
				<button style="margin-right: 10px;float: right;" disabled id="calculate" ng-click="calculate()"  class="btn btn-primary"><i class="icon-tasks"></i>计算</button>
				<button style="margin-right: 10px;float: right;" disabled id="save" ng-click="save()" class="btn btn-primary"><i class="icon-plus"></i>保存</button>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div style="margin-left: 5px;" ><input id="checkedall" ng-click="checkall()" type="checkbox"></div>
		<div style="width:100%;margin-top:10px;overflow:auto;" ng-repeat="item in items.data track by $index" >
			<table width="100%" border="1">
				<tr>
					<td width="3%">
						<div align="center">
							<input name="subBox" class="checkbox_check" id="{{item.rowData.ID}}" value={{item.rowData.ID}} style="height:200px; line-height:200px;overflow:hidden;" ng-click="check_each($event.target,$index)" type="checkbox"  >
						</div>
					</td>
					<td  width="20%">
						<table id="initHtable{{$index}}">
							<tr width="70%"><td colspan="5">{{item.rowData.MEISMC}}</td></tr>
							<tr width="70%"><td align="center" width="23%">目标值</td><td width="25%" align="right">热    值</td><td  width="10%" align="center">&gt;=</td><td  width="15%" align="right"><input style="width: 40px;border-style:none;border-bottom-style:none;border-top-style:none;border-left-style:none;border-right-style:none;text-align:right;position:relative;top:5px;" type="text" ng-model="item.rowData.Q_MB"></td><td  width="27%" algin="left"><span>kcal/kg</span></td></tr>
							<tr width="70%"><td width="23%"></td><td width="25%" align="right">硫    分</td><td  width="10%" align="center">&lt;=</td><td  width="15%" align="right"><input style="width: 40px;border-style:none;text-align:right;position:relative;top:5px;" type="text" ng-model="item.rowData.S_MB"></td><td  width="27%" algin="left"><span>%</span></td></tr>
							<tr width="70%"><td width="23%"></td><td width="25%" align="right">挥发份</td><td  width="10%" align="center">&lt;=</td><td  width="15%" align="right"><input style="width: 40px;border-style:none;text-align:right;position:relative;top:5px;" type="text" ng-model="item.rowData.V_MB"></td><td  width="27%" algin="left"><span>%</span></td></tr>
							<tr width="70%"><td width="23%"></td><td width="25%" align="right">数    量</td><td  width="10%" align="center">=</td><td  width="15%" align="right"><input style="width: 40px;border-style:none;text-align:right;position:relative;top:5px;" type="text" ng-model="item.rowData.SHUL_MB"></td><td  width="27%" algin="left"><span>t</span></td></tr>
						</table>
					</td>
					<td width="57%">
						<div id=check{{item.rowData.ID}} class="all_td" ng-if="item.rowData.CHECKED==1" >
							<div>&nbsp;&nbsp;&nbsp;<span>{{item.rowData.MEISMC}}调用计划</span><br /></div>
							<div style="width:2%;float:left; "> &nbsp;&nbsp;</div>
							<div style="display:inline; width:95%;  float:left; ">
								<table  border="1"  class="table table-bordered table-condensed table-hover">
									<tr>
										<th width="20%">煤源</th>
										<th width="15%">标单</th>
										<th width="11%">热值</th>
										<th width="11%">硫分</th>
										<th width="11%">最大量</th>
										<th width="11%">最小量</th>
										<th width="11%">调运量</th>
										<th width="10%">车数</th>
									</tr>
									<tr ng-repeat="data in item.rowDetails">
								        <td width="20%"><input disabled style="width: 90%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIYMC"></td>
								        <td width="15%"><input disabled style="width: 90%;background-color:transparent;border-style:none;" type="text" ng-model="data.BIAOMDJ"></td>
								        <td width="11%"><input disabled style="width: 90%;background-color:transparent;border-style:none;" type="text" ng-model="data.QNET_AR"></td>
								        <td width="11%"><input disabled style="width: 90%;background-color:transparent;border-style:none;" type="text" ng-model="data.S"></td>
								        <td width="11%"><input style="width: 85%;border-style:none;" type="text" ng-model="data.SHUL_MAX"></td>
								        <td width="11%"><input style="width: 85%;border-style:none;" type="text" ng-model="data.SHUL_MIN"></td>
								        <td width="11%"><input style="width: 85%;border-style:none;"  type="text"  ng-model="data.SHUL_DY" ng-change="Diaoylchange({{data.ID}})"></td>
								        <td width="10%"><input style="width: 85%;border-style:none;" type="text" ng-model="data.CHES" ></td>
					    			</tr>
								</table>
							</div>
							<div style="width:2%; float:left;">&nbsp;&nbsp;</div>
							<br/><br/>
							<div style="float:left; ">&nbsp;&nbsp;&nbsp; 掺配值&nbsp;&nbsp;&nbsp;  热值=<span ng-model="item.rowData.QNET_AR_JG"></span><input disabled style="width: 40px;background-color:transparent;border-style:none;text-align:right;position:relative;top:4px;" type="text" ng-model="item.rowData.QNET_AR_JG"><span>kcal/kg</span>
								&nbsp;&nbsp;&nbsp;硫分=<input disabled style="width: 40px;background-color:transparent;background-color:transparent;border-style:none;text-align:right;position:relative;top:4px;" type="text" ng-model="item.rowData.S_JG"><span>%</span>
								&nbsp;&nbsp;&nbsp;数量=<input disabled style="width: 40px;background-color:transparent;border-style:none;text-align:right;position:relative;top:4px;" type="text" ng-model="item.rowData.SHUL_JG"><span>t</span>
								&nbsp;&nbsp;&nbsp;标单=<input disabled style="width: 50px;background-color:transparent;border-style:none;text-align:right;position:relative;top:4px;" type="text" ng-model="item.rowData.BIAOMDJ"><span>元/吨</span>
							</div>
						</div>
					</td>
					<td width="20%" id="httd{{$index}}">
						<div class="backgroundTbDiv" id="bg" >
                  			<img src="/fuelmis/images/peimei/meiduo.png" id="img{{$index}}"  width="100%" style="height:220px" />
                 			<div id="bgin" style="top: 0px;" align = "center">
                         		<table>
                             		<tr>
                                 		<td>
                                     		<font color="red">热值:{{item.rowData.QNET_AR_JG}}</font>
                                 		</td>
                             		</tr>
                             		<tr>
                                 		<td>
                                     		<font color="red">标单:{{item.rowData.BIAOMDJ}}</font>
                                 		</td>
                             		</tr>
                             		<tr>
                                 		<td>
                                     		<font color="red">硫分:{{item.rowData.S_JG}}</font>
                                 		</td>
                             		</tr>
                         		</table>
                     		</div>
                     		<div style="bottom: 0px;" align = "center">
                     			<table>
                             		<tr>
                                 		<td>
                                     		<font color="yellow">热值:{{item.rowData.Q_MB}}</font>
                                 		</td>
                                 		</tr>
                                 		<tr>
                                 		<td>
                                     		<font color="yellow">标单:</font>
                                 		</td>
                                 		</tr>
                                 		<tr>
                                 		<td>
                                     		<font color="yellow">硫分:{{item.rowData.S_MB}}</font>
                                 		</td>
                             		</tr>
                         		</table>
                     		</div>
             			</div>			
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
    
});
$(function() {
   $("#checkedall").click(function() {
        $('input[name="subBox"]').attr("checked",this.checked); 
    });
    var $subBox = $("input[name='subBox']");
    $subBox.click(function(){
        $("#checkedall").attr("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
    });
});

</script>
