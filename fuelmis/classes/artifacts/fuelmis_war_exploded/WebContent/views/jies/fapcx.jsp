<style>
tbody .hide{
display: none;
}

</style>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="fapcxCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">发票查询</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">

				<label style="width: 60px; height: 30px; line-height: 30px; margin-right: 5px; float: left;" class="control-label span1">煤矿单位:</label> 
				<select style="width: 150px; float: left;" id="meik"
					ng-model="search.meikxxbid" ng-change="load()" ng-options="option.value as option.name for option in meikxxList">
				</select> 
				<label style="width: 60px; height: 30px; line-height: 30px; margin-right: 5px; float: left;" class="control-label span1">结算单号:</label> 
				<select id="jies" style="width: 180px; float: left;"
					ng-model="search.jiesbid" ng-change="refresh()" ng-options="option.value as option.name for option in jiesdhList">
				</select>
				 <label style="width: 60px; height: 30px; line-height: 30px; margin: 0 5px 0 10px; float: left;" class="control-label">发票日期:</label>
				<!-- 起始日期 -->
				<input style="width:120px; float: left;" id="datepicker" ng-model="search.qisrq" ng-change="refresh()" type="text"> 
				<label style="width: 15px; height: 30px; line-height: 30px; margin: 0 5px; float: left;" class="control-label">至</label>
				<!-- 终止日期 -->
				<input style="width:120px; float: left;" id="datepicker1" ng-model="search.zhongzrq" ng-change="refresh()" type="text">


				<button style="margin-left:20px;" id="refresh" ng-click="refresh()" class="btn btn-success">
					<i class=" icon-refresh icon-white"></i> 刷新
				</button>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-hover" id="example">
				<thead>
					<tr>
						<th style="display:none;"></th>
						<th style="text-align: center;">编码</th>
						<th style="text-align: center;">性质</th>
						<th style="text-align: center;">煤矿简称</th>
						<th style="text-align: center;">结算单号</th>
						<th style="text-align: center;">发票日期</th>
						<th style="text-align: center;">条款</th>
						<th style="text-align: center;">总成本</th>
						<th style="text-align: center;">录入人</th>
						<th style="text-align: center;">状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- END FORM-->
</div>



<script type="text/javascript">
	var oTable;
	$(document).ready(

	function() {
		oTable = $('#example').dataTable({
			"processing" : true,
			//'ajax' : 'yuedranlzfjh/getRanlzfList',
			"language" : {
				"sLengthMenu" : "每页显示 _MENU_条",
				"sZeroRecords" : "没有找到符合条件的数据",
				"sProcessing" : "数据加载中...",
				"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
				"sInfoEmpty" : "没有记录",
				"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
				"sSearch" : "搜索：",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "前一页",
					"sNext" : "后一页",
					"sLast" : "尾页"
				}
			},

			//"resizable":true,
			//'scrollX': 200,
			//"scrollCollapse": true,
			"aoColumnDefs": [{
        	"aTargets": [0],
        	"sClass":"hide"
       	 }]
		});
		//console.log($("#jies"));
		//console.log($("#jies option :eq(0)"));
		//$("#jies option :eq(0)").attr("slected","slected");

	});

	//设置选中表格数据，选中将删除和更新按钮置亮，取消按钮置灰
	function check(args) {
		if ($(args).attr("checked") != undefined) {
			$("#delfap").addClass("btn-primary");
			$("#delfap").removeAttr("disabled"); //移除disabled属性 
			$("#updatefap").addClass("btn-primary");
			$("#updatefap").removeAttr("disabled"); //移除disabled属性 
			$("input[type='checkbox']").attr("checked", false);
			$(args).attr("checked", true);
		} else {
			$("#delfap").removeClass("btn-primary");
			$("#delfap").attr('disabled', true);
			$("#updatefap").removeClass("btn-primary");
			$("#updatefap").attr('disabled', true);
		}
	}

	function fjUpload() {
		$.ajaxFileUpload({
			url : 'kucgl/shujlr/uploadFile',
			secureuri : false,
			fileElementId : 'upFile',
			dataType : 'json',
			data : {
				id : $('#pandxx_id').val()
			},
			success : function(data, status) {
				alert(data[0].msg);
				$('#myModal').modal('hide');
				var riq = $('#datepicker').val();
				var diancid = $('#dianc_id').val();
				oTable.fnReloadAjax('kucgl/shujlr/getPandxx?riq=' + riq + '&dianc=' + diancid);
				$("#createBtn").hide();
				$("#fujBtn").show();
				$("#submitBtn").show();
			},
			error : function(data, status, e) {
				alert(e);
			}
		})
		return false;
	}
</script>