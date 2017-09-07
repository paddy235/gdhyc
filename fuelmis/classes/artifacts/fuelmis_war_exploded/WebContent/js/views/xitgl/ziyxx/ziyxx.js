gddlapp.controller('ziyxxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.ziyxxTitle='资源管理';
	
	$scope.addZiyxx = function() {
		var treeId = $('#treeId').val();
		if(treeId==""){
			alert("请先选择一个资源，然后点击“添加”按钮！")
		}else{
			$('#myModal_Add').modal('show');
		}
	}
	
	$scope.delZiyxx = function() {
		var treeId = $('#treeId').val();
		if(treeId==""){
			alert("请先选择一个资源，然后点击“删除”按钮！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
	
	$scope.modifyZiyxx = function() {
		var treeId = $('#treeId').val();
		if(treeId==""){
			alert("请先选择一个资源，然后点击“修改”按钮！")
		}else{
			$.post("ziyxx/getOne",{id:$('#treeId').val()},function(data){
				$('#ziymc_u').val(data[0].name);
				$('#wenjwz_u').val(data[0].wenjwz);
			},"json");
			$('#myModal_Modify').modal('show');
		}
	}
});