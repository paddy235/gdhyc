<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
   <div class="block" ng-controller="wailyrlCtrl">
   	<div id="tabs" style="margin-top:20px;">
     <div class="navbar navbar-inner block-header">
          <div class="muted pull-left">{{title}}</div> 
    </div>
   	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<label style="width: 35px;float:left;height:30px;line-height:30px;" class="control-label">日期:</label>
				<input style="width: 150px;float: left;" id="datepicker" ng-model="search.sDate"  type="text">
				<button style="margin-left: 5px;float: left;"  id="refresh" ng-click="" class="btn btn-info">查询 <i class="icon-search icon-white"></i></button>
				<button style="margin-left: 5px;float: left;"  id="save" ng-click=" " class="btn btn-info">保存 <i class="icon-check icon-white"></i></button>
				<button style="margin-left: 5px;float: left;"  id="add" ng-click=" "  class="btn btn-info">添加 <i class="icon-plus icon-white"></i></button>
				<button style="margin-left: 5px;float: left;"  id="delete" ng-click=" "  class="btn btn-info">删除 <i class="icon-plus icon-white"></i></button>
			</div>
		</div>
		<div class="row-fluid" style="padding-top: 5px;">
			<div width="100%" height="100px" align="center"><h4>外来样维护</h4></div>
			<div width ="100%" align="center">
				<div width="90%">
					<table class="table table-bordered table-condensed table-hover">
						<tr>
							<th width="10%"></th>
							<th width="10%">序号</th>
							<th width="10%">外来样编号</th>
							<th width="10%">取样时间</th>
							<th width="10%">煤矿单位</th>
							<th width="10%">煤种</th>
							<th width="10%">全水分Mt(%)</th>
							<th width="10%">水分Mad(%)</th>
							<th width="10%">灰分Aad(%)</th>
							<th width="10%">挥发分Vad(%)</th>
							<th width="10%">硫分St,ad(%)</th>
							<th width="10%">氢Had(%)</th>
							<th width="10%">弹桶热值Qb,ad(MJ/Kg)</th>
							<th width="10%">收到基低位热值Qnet,ar(Kcal/g)</th>
							<th width="10%">收到基灰Aar(%)</th>
							<th width="10%">收到基挥发分Var(%)</th>
							<th width="10%">收到基硫St,ar(%)</th>
							<th width="10%">取样人</th>
							<th width="10%">备注</th>
							<th width="10%">附件</th>
						</tr>
						
						<!-- 查询所有 -->
						<tr ng-repeat="data in list track by $index">
						 <td width="5%" align="center"><input class="checkedall" id={{data.ID}}  type="checkbox"></td>
					        <td width="10%"><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data。ID"></td>
					          <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data。WAILYBH"></td>
					            <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QUYSJ"></td>
					               <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIKDW"></td>
					                 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIZ"></td>
					                   <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MT"></td>
					                     <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MAD"></td>
					                       <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.AAD"></td>
					         				 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.VAD"></td>
					                           <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.STAD"></td>
					         					 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.HAD"></td>
					                               <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QBAD"></td>
					                                 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QENTAR"></td>
					                                   <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.AAR"></td>
					                                     <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.VAR"></td>
					                                       <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.STAR"></td>
					                                         <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QUYR"></td>
					                                           <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.BEIZ"></td>
					                                             <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.FUJMC"></td>
		    													</tr>
		    			<!-- 新增 -->
		    			<tr ng-repeat="data in array track by $index">
						   <td width="5%" align="center"><input class="checkedall" id={{data.ID}}  type="checkbox"></td>
					        <td width="10%"><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data。ID"></td>
					          <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data。WAILYBH"></td>
					            <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QUYSJ"></td>
					               <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIKDW"></td>
					                 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIZ"></td>
					                   <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MT"></td>
					                     <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MAD"></td>
					                       <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.AAD"></td>
					         				 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.VAD"></td>
					                           <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.STAD"></td>
					         					 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.HAD"></td>
					                               <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QBAD"></td>
					                                 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QENTAR"></td>
					                                   <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.AAR"></td>
					                                     <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.VAR"></td>
					                                       <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.STAR"></td>
					                                         <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QUYR"></td>
					                                           <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.BEIZ"></td>
					                                             <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.FUJMC"></td>
		    													</tr>
		    			
		    			<tr ng-repeat="data in lists track by $index">
						   <td width="5%" align="center"><input class="checkedall" id={{data.ID}}  type="checkbox"></td>
					        <td width="10%"><input disabled style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data。ID"></td>
					          <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data。WAILYBH"></td>
					            <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QUYSJ"></td>
					               <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIKDW"></td>
					                 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MEIZ"></td>
					                   <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MT"></td>
					                     <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.MAD"></td>
					                       <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.AAD"></td>
					         				 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.VAD"></td>
					                           <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.STAD"></td>
					         					 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.HAD"></td>
					                               <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QBAD"></td>
					                                 <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QENTAR"></td>
					                                   <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.AAR"></td>
					                                     <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.VAR"></td>
					                                       <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.STAR"></td>
					                                         <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.QUYR"></td>
					                                           <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.BEIZ"></td>
					                                             <td width="10%"><input  style="width: 70%;background-color:transparent;border-style:none;" type="text" ng-model="data.FUJMC"></td>
		    													</tr>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

		<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" style="display:none;" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		            <h4 class="modal-title" id="myModalLabel">删除年度计划</h4>
		         </div>
		         <div class="modal-body">
		            <div class="block-content collapse in">
						<div class="span12">
							确认删除？
						</div>
				 	</div>
		         </div>
		         <div class="modal-footer">
		          	<button type="button" class="btn btn-danger" onclick="deleteJihb()"><i class="icon-ok-sign icon-white"></i> 确认
		            </button>
		            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
		            </button>
		         </div>
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

  function deleteJihb(){
	$("#xz input[type=checkbox]").each(function() {
		if(this.checked){
			$.post("jihnz/deleteJihb",{id:$(this).attr("id")},function (data){
				$("#myModal_Del").modal('hide');
				alert("删除成功！");
			})
		}
	})
} 

</script>
