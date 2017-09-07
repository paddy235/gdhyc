<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.ui-datepicker-calendar {
	display: none;
}
</style>
<link rel="stylesheet" type="text/css" href="/fuelmis/css/table.css">
<div class="tab-pane" ng-controller="sanfsllrCtrl">
	<div class="block-content collapse in ">
		<form id="form1" runat="server">
			<div class="row-fluid">
				<div class="table-toolbar" >
					<!-- 日期 -->
					<label style="width: 80px; height: 30px; line-height: 30px; float: left;" class="control-label span1">过衡日期:</label> 
					<input style="width: 80px; float: left;" id="datepicker" ng-model="search.riq" ng-change="load()" type="text"> 
					<label
						style="width: 80px; height: 30px; line-height: 30px; float: left;"
						class="control-label span1">供货单位:</label> 
					<select
						style="width: 120px; float: left;" id="meik" ng-model="search.GONGYSB_ID"
						ng-change="load()"
						ng-options="option.ID as option.MINGC for option in gongysList">
					</select>
					<button style="margin-left: 20px;" class="btn btn-primary"
						id="adddata" ng-click="load()">
						<i class="icon-plus icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" class="btn btn-success" id="save"
						ng-click="save()">
						<i class="icon-check icon-white"></i> 保存
					</button>
				</div>
				<table class="table table-striped table-bordered table-hover my-table-header">
					<thead>
						<tr >
							<th style="width: 5%" >序号</th>
							<th style="width: 8%" ng-click="sort($event.target)" id="CHEPH">车号 <span class="glyphicon glyphicon-star"></span></th>
							<th style="width: 5%" ng-click="sort($event.target)" id="MAOZ">毛重</th>
							<th style="width: 5%" ng-click="sort($event.target)" id="PIZ">皮重</th>
							<th style="width: 5%" ng-click="sort($event.target)" id="JINGZ">净重</th>
							<th style="width: 5%" ng-click="sort($event.target)" id="SANFL">三方量</th>
							<th style="width: 8%" ng-click="sort($event.target)" id="CHAE">差额</th>
							<th style="width: 10%" ng-click="sort($event.target)" id="MEIKDW">煤矿单位</th>
							<th style="width: 10%" ng-click="sort($event.target)" id="PINZ">品种</th>
							<th style="width: 8%" ng-click="sort($event.target)"  id="YUNSDW">运输单位</th>
							<th style="width: 15%" ng-click="sort($event.target)" id="ZHONGCSJ">重车时间</th>
							<th style="width: 15%" ng-click="sort($event.target)" id="QINGCSJ">轻车时间</th>
							<th style="width: 3px"></th>
						</tr>
					</thead>
				</table>
				<div class="table-body" style="height: 630px;" >
					<table  class="table table-striped table-bordered table-hover" id="example">
						<tbody>
							<tr ng-repeat="row in list track by $index" class="option">		
								<!-- 序号 -->
								<td style="width:5%"><input type="text" ng-model="$index" onfocus=this.blur() /></td>			
								<!-- 车号 -->
								<td style="width:8%"><input type="text" ng-model="row.CHEPH" onfocus=this.blur() /></td>
								<!-- 毛重 -->
								<td style="width:5%"><input type="text" ng-model="row.MAOZ" onfocus=this.blur() /></td>
								<!-- 皮重-->
								<td style="width:5%"><input type="text" ng-model="row.PIZ" onfocus=this.blur() /></td>
								<!-- 净重 -->
								<td style="width:5%"><input type="text" ng-model="row.JINGZ" onfocus=this.blur() /></td>
								<!-- 三方量 -->
								<td style="width:5%"><input id={{$index}} class="editorable" type="text" onkeyup="keyup()" onkeydown="keydown()" ng-model="row.SANFL" ng-focus="focusValue($event.target)" ng-blur="blurValue($event.target,row)"/></td>
								<!-- 差额 -->
								<td style="width:8%"><input type="text" ng-model="row.CHAE" onfocus=this.blur() /></td>
								<!-- 煤矿单位 -->
								<td style="width:10%"><input type="text" ng-model="row.MEIKDW" onfocus=this.blur() /></td>
								<!-- 品种 -->
								<td style="width:10%"><input type="text" ng-model="row.PINZ" onfocus=this.blur() /></td>
								<!-- 运输单位 -->
								<td style="width:8%"><input type="text" ng-model="row.YUNSDW" onfocus=this.blur() /></td>
								<!-- 重车时间-->
								<td style="width:15%"><input type="text" ng-model="row.ZHONGCSJ" onfocus=this.blur() /></td>
								<!-- 轻车时间 -->
								<td style="width:15%"><input type="text" ng-model="row.QINGCSJ" onfocus=this.blur() /></td>
							</tr>
						</tbody>
					</table>	
				</div>
			</div>
		</form>
	</div>
	<!-- END FORM-->
</div>
<script type="text/javascript">
//获取键盘按下事件，移动输入框
var isenter=[];
function keydown(){	
        var keycode = event.keyCode;
        isenter[keycode]=true;
       // var realkey = String.fromCharCode(event.keyCode);
       if(isenter[keycode]==true){
    	   	var id=event.target.id;
          	
           	if(keycode==38){
           	var	prevId=id-1;
           		if(prevId==-1){
           			prevId=0;
           		}
           		$("#"+prevId).focus();
           		isenter[keycode]=false;
           }else if(keycode==40){
        	 var  nextId=parseInt(id)+1;
        	   $("#"+nextId).focus();
        	   isenter[keycode]=false;
           }
       }        
}
function keyup(){	
    var keycode = event.keyCode;
    isenter[keycode]=true;
    isenter=[];
}
document.onkeydown = keydown;
document.onkeyup = keyup;

</script>
