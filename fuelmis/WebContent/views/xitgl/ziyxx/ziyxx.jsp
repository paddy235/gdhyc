<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="tab-pane" ng-controller="ziyxxCtrl">
<%--	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{ziyxxTitle}}</div>
	</div>--%>
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<input type="hidden" id="treeId" name="treeId" value=""/>
					<button style="margin-left: 10px;" ng-click="addZiyxx()" class="btn btn-primary">
						<i class="icon-plus icon-white"></i> 添加
					</button>
					<button style="margin-left: 10px;" ng-click="delZiyxx()" class="btn btn-danger">
						<i class="icon-trash icon-white"></i> 删除
					</button>
					<button style="margin-left: 10px;" ng-click="modifyZiyxx()" class="btn btn-info">
						<i class="icon-edit icon-white"></i> 修改
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div style="width:90%;height:400px;overflow: auto;margin-left: auto;margin-right: auto;margin-bottom: 20px;border: 1px solid #000;">
			<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal_Add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel">新增资源信息</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					<form id="fujAdd_form" class="form-horizontal">
						<div class="control-group">
							<input type="hidden" id="pandxx_id" value=""/>
							<label class="control-label">资源名称</label>
							<div class="controls">
								<input id="ziymc_a" name="ziymc_a" type="text" class="span7 m-wrap"/>
							</div>
							<br/>
							<label class="control-label">访问地址</label>
							<div class="controls">
								<input id="wenjwz_a" name="wenjwz_a" type="text" class="span7 m-wrap"/>
							</div>
						</div>
					</form>
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-primary" onclick="oprationZiyxx('A')"><i class="icon-check icon-white"></i> 保存</button>
            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
         </div>
      </div>
   </div>
</div>

<div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel">删除资源信息</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					确认删除该资源吗？
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-danger" onclick="oprationZiyxx('D')"><i class="icon-ok-sign icon-white"></i> 确认</button>
            <button type="button" class="btn"data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
         </div>
      </div>
   </div>
</div>

<div class="modal fade" id="myModal_Modify" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel">修改资源信息</h4>
         </div>
         <div class="modal-body">
            <div class="block-content collapse in">
				<div class="span12">
					<form id="fujAdd_form" class="form-horizontal">
						<div class="control-group">
							<input type="hidden" id="pandxx_id" value=""/>
							<label class="control-label">资源名称</label>
							<div class="controls">
								<input id="ziymc_u" name="ziymc_u" type="text" class="span7 m-wrap"/>
							</div>
							<br/>
							<label class="control-label">访问地址</label>
							<div class="controls">
								<input id="wenjwz_u" name="wenjwz_u" type="text" class="span7 m-wrap"/>
							</div>
						</div>
					</form>
				</div>
		 	</div>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-primary" onclick="oprationZiyxx('U')"><i class="icon-check icon-white"></i> 保存</button>
            <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消</button>
         </div>
      </div>
   </div>
</div>

<script type="text/javascript">
	var setting = {
		view : {
			dblClickExpand : true,
			showLine : true,
			selectedMulti : false
		},
		async:{
			enable : true,
			dataType : 'json',
			type : 'post',
			url : 'ziyxx/getZiyxx'
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "fuid",
				rootPId : "-1"
			}
		},
		callback : {
			beforeClick : function(treeid, treeNode){
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if(treeNode.isParent) {
					zTree.expandNode(treeNode);
					return true;
				} else {
					return true;
				}
			},
			onClick : zTreeOnClick
		}
	};

	function zTreeOnClick(event, treeId, treeNode) {
		$('#treeId').val(treeNode.id);
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#tree"), setting);
	});
	
	function oprationZiyxx(flag){
		if(flag=='A'){
			var id = $('#treeId').val();
			var mingc = $('#ziymc_a').val();
			var wenjwz = $('#wenjwz_a').val();
			
			$.post("ziyxx/addZiyxx",{id:id,mingc:mingc,wenjwz:wenjwz},function(data){
				$('#myModal_Add').modal('hide');
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				treeObj.reAsyncChildNodes(null, "refresh");
			},"json");
		}else if(flag=='D'){
			var id = $('#treeId').val();
			$.post("ziyxx/delZiyxx",{id:id},function(data){
				$('#myModal_Del').modal('hide');
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				treeObj.reAsyncChildNodes(null, "refresh");
			},"json");
		}else if(flag=='U'){
			var id = $('#treeId').val();
			var mingc = $('#ziymc_u').val();
			var wenjwz = $('#wenjwz_u').val();
			$.post("ziyxx/modifyZiyxx",{id:id,mingc:mingc,wenjwz:wenjwz},function(data){
				$('#myModal_Modify').modal('hide');
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				treeObj.reAsyncChildNodes(null, "refresh");
			},"json");
		}else{
			return;
		}
	}
</script>