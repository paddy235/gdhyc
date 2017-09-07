<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style>
tbody .center {
	text-align: center !important;
}

tbody .right {
	text-align: right !important;
}
</style>

<div class="tab-pane" ng-controller="shujlrCtrl">
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:40px;margin-right:5px;" class="control-label">日期:</label>
					<input id="datepicker" type="text" style="float: left;width: 120px;" ng-model="search.riq">
					<label style="width:50px;margin-right:5px;" class="control-label">单位:</label>
					<select id="dianc_id" style="float: left;width: 120px;">
						<option ng-repeat="dianc in diancList" value="{{dianc.value}}">{{dianc.name}}</option>
					</select>
					<button style="margin-left:20px;" class="btn btn-success" ng-click="searchData()">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="createData()" class="btn btn-primary" id="createBtn">
						<i class="icon-plus icon-white"></i> 生成
					</button>
					<button style="margin-left: 10px;" ng-click="uploadFuj()" class="btn btn-inverse" id="fujBtn">
						<i class="icon-file icon-white"></i> 添加附件
					</button>
					<button style="margin-left: 10px;" ng-click="saveData()" class="btn btn-primary" id="saveBtn">
						<i class="icon-upload icon-white"></i> 保存
					</button>
					<button style="margin-left: 10px;" ng-click="submitData()" class="btn btn-primary" id="submitBtn">
						<i class="icon-upload icon-white"></i> 提交
					</button>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="example">
				<thead>
					<tr>
						<th style="text-align: center;width: 50px;"></th>

						<th style="text-align: center; width: 100px;">电厂名称</th>
						<th type="text"> 账面库存</th>
						<th type="text">实盘库存</th>
						<th style="text-align: center; width: 100px;">场损量</th>
						<th style="text-align: center; width: 100px;">水分差调</th>
						<th style="text-align: center; width: 100px;">盈亏吨</th>
						<th type="text">附件名称</th>
					</tr>
				<tr ng-repeat="r in pandxxList" >
					<td><input type="checkbox"></td>
					<td><select type="text" ng-model="r.DIANCXXB_ID" ng-options="option.value as option.name for option in diancList" style="width:100px;margin-right:5px"></select></td>
					<td><input type="text" ng-model="r.ZHANGMKC" style="width:150px;margin-right:5px"></td>
					<td><input type="text" ng-model="r.SHIPKC" style="width:150px;margin-right:5px"></td>
					<td><input type="text" ng-model="r.CHANGSL" style="width:150px;margin-right:5px"></td>
					<td><input type="text" ng-model="r.SHUIFCTZL" style="width:150px;margin-right:5px"></td>
					<td><input type="text" ng-model="r.YINGKD" style="width:150px;margin-right:5px"></td>
					<td><a href="common/downloadFile?fileId={{r.FUJMC}}">{{r.FUJMC}}</a></td>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×
					</button>
					<h4 class="modal-title" id="myModalLabel">附件上传 </h4>
				</div>
				<div class="modal-body">
					<div class="block-content collapse in">
						<div class="span12">
							<form id="fujAdd_form" class="form-horizontal">
								<div class="control-group">
									<input type="hidden" id="pandxx_id" value=""/>
									<label class="control-label">附件上传</label>
									<div class="controls">
										<input id="upFile" name="upFile" type="file" size="45" class="span6 m-wrap"/>
										<button type="button" class="btn btn-primary" ng-click="fjUpload()">
											<i class="icon-upload icon-white"></i> 上传
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">
						<i class="icon-remove-circle"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</div>


<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		$("#datepicker").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
		
//		oTable = $('#example').dataTable({
//			"processing" : true,
//			'ajax' : 'kucgl/shujlr/getPandxx',
//			'bPaginate' : false,
//			'bAutoWidth' : true,
//			'bFilter' : false,
//			'bInfo' : false,
//			"oLanguage" : {
//				"sLengthMenu" : "每页显示 _MENU_条",
//				"sZeroRecords" : "没有找到符合条件的数据",
//				"sProcessing" : "数据加载中...",
//				"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
//				"sInfoEmpty" : "没有记录",
//				"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
//				"sSearch" : "搜索：",
//				"oPaginate" : {
//					"sFirst" : "首页",
//					"sPrevious" : "前一页",
//					"sNext" : "后一页",
//					"sLast" : "尾页"
//				}
//			},
//			/*"columns": [
//				{"data":"id"},
//				{"data":"id"},
//			    {"data":"diancxxb_id"},
//                {
//                    "data": "zhangmkc",
//                    "render": function (sData, type, row) {
//                        row.zhangmkc = Number(sData).toFixed(2);
//                        return row.zhangmkc;
//                    }
//                },
//                {
//                    "data": "shipkc",
//                    "render": function (sData, type,row) {
//                        row.shipkc=Number(sData).toFixed(2);
//                        return row.shipkc;
//                    }
//                },
//                {
//                    "data": "changsl",
//                    "render": function (sData,type,row) {
//                        row.changsl=Number(sData).toFixed(2);
//                        return row.changsl;
//                    }
//                },
//				/!*{
//					"data": "zhangmkc",
//					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//						$(nTd).addClass('inputTd').attr('id', 'zhangmkc_' + oData.id);
//					}
//				},*!/
//				/!*{
//					"data": "shipkc",
//					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//						$(nTd).addClass('inputTd').attr('id', 'shipkc_' + oData.id);
//					}
//				},*!/
//				/!*{
//					"data": "changsl",
//					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//						$(nTd).addClass('inputTd').attr('id', 'changsl_' + oData.id);
//					}
//				},*!/
//				{
//					"data": "shuifctzl",
//					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//						$(nTd).addClass('inputTd').attr('id', 'shuifctzl_' + oData.id).toFixed(2);
//					}
//				},
//				{
//					"data": "yingkd",
//					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//						$(nTd).addClass('inputTd').attr('id', 'yingkd_' + oData.id);
//					}
//				},
//				{"data": "fujmc"}
//			],*/
//			"fnDrawCallback": function (data, x) {
//				$('#example tbody td.inputTd').editable('kucgl/shujlr/savePandxx',{
//					type : 'text',
//					height : '20px',
//					onblur : 'submit',
//					callback : function(value, settings) {
//				        oTable.fnReloadAjax('kucgl/shujlr/getPandxx?riq='+$('#datepicker').val()+'&dianc='+$('#dianc_id').val());
//				        $.post("kucgl/shujlr/checkCount",function(data){
//							$("#createBtn").hide();
//							$("#fujBtn").show();
//							$("#submitBtn").show();
//						});
//				    }
//				});
//			},
//			"aoColumnDefs": [{
//	             "mRender": function(oObj, sVal) {
//	                 return '<input type="checkbox" id="' + oObj + '" name="checkId" />';
//	             },
//	             "bSortable": false,
//	             "aTargets": [0],
//	             "sClass": "center",
//            },{ "bVisible": false, "aTargets": [1]},{"sClass": "center","aTargets": [2]},
//            {"sClass": "right","aTargets": [3,4,5,6,7]},{"sClass": "center","aTargets": [8]}
//            ]
//		});
		
		var riq = $('#datepicker').val();
		var diancid = isNaN($('#dianc_id').val())?"":$('#dianc_id').val();
		$.post('kucgl/shujlr/checkCount?riq='+riq+'&dianc='+diancid,function(data){
			if(data[1]==0){
				$("#createBtn").show();
				$("#fujBtn").hide();
				$("#submitBtn").hide();
				//$("#saveBtn").hide();
			}else{
				$("#createBtn").hide();
				$("#fujBtn").show();
				$("#submitBtn").show();
               // $("#saveBtn").show();
			}
		});
	});
	

</script>