<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="block" ng-controller="shezqxCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">{{shezqxTitle}}</div>
	</div>
	<div class="row-fluid">
		<input type="hidden" id="renyxxb_id" name="renyxxb_id" value=""/>
		<div style="width:90%;height:400px;overflow: auto;margin-left: auto;margin-right: auto;margin-bottom: 20px;margin-top: 20px;border: 1px solid #000;">
			<ul id="tree" class="ztree" style="width:360px; overflow:auto;"></ul>
		</div>
		<div style="width:90%;margin-bottom: 20px;">
			<center>
				<button type="button" class="btn btn-primary" ng-click="saveQuanx()"><i class="icon-check icon-white"></i> 保存</button>
				<button type="button" class="btn" style="margin-left: 10px;" ng-click="cancel()"><i class="icon-repeat"></i> 返回</button>
			</center>
		</div>
	</div>
</div>

<script type="text/javascript">
	var setting = {
		check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType : { "Y" : "ps", "N" : "ps" },
			autoCheckTrigger: true
		},
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
			onClick : zTreeOnClick,
			onNodeCreated: zTreeOnNodeCreated,
			onAsyncSuccess:showTree
			
		}
	};

	function zTreeOnClick(event, treeId, treeNode) {
		$('#treeId').val(treeNode.id);
	}
	
	function showTree(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var tree=zTree.getNodeByTId("tree_1");
		zTree.expandNode(tree,true);
	}

	function zTreeOnNodeCreated(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		$.post("renyxx/getQuanx",{id:$("#renyxxb_id").val()},function(data){
			for(var i = 0;i < data[0].length;i++){
				if(data[0][i]==treeNode.id){
					treeNode.checked = true;
					//zTree.expandAll(false);
					zTree.updateNode(treeNode);
				}
			}
		},"json");
		zTree.expandAll(false);
    }

	$(document).ready(function(){
		$.fn.zTree.init($("#tree"), setting);
	});
</script>