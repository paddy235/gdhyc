gddlapp.controller('shujplshCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.zhuangtList = [{"name":"未审核","value":"0"},{"name":"已审核","value":"1"}];//
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();

	$scope.search = {
		zhuangt : $routeParams.zhuangt==undefined?"0":$routeParams.zhuangt,
		sDate : $routeParams.sDate==undefined?year+'-'+month+'-'+day:$routeParams.sDate,
		eDate : $routeParams.eDate==undefined?year+'-'+month+'-'+day:$routeParams.eDate,
		sSearch : ''
	}

	var zhuangt = $routeParams.zhuangt==undefined?0:$routeParams.zhuangt;
	var sDate = $routeParams.sDate==undefined?null:$routeParams.sDate;
	var eDate = $routeParams.eDate==undefined?null:$routeParams.eDate;
	$scope.searchData = function() {
		var zhuangt = $scope.search.zhuangt;
		var sDate = $scope.search.sDate;
		var eDate = $scope.search.eDate;
		var sSearch = encodeURI(encodeURI($scope.search.sSearch));
		$http.post('rucsl/shulsh/getTableData?zhuangt='+zhuangt+'&sDate='+sDate+'&eDate='+eDate+'&sSearch='+sSearch).
			success(function(data, status, headers, config) {
				$scope.dataList=data.data
		});
	}
	
	$scope.shenh=function(){
		var checkboxes=$('input:checkbox[name=checkId]:checked')
		var l=checkboxes.length
		var jidkd=parseInt(100/l)
		var jindz=0
		var count=0
		var isSuccess=true
		$('#shenhbar').css('width','0%')
		$('#shenhbar span').html('0%完成')	
		if(checkboxes.length==0){ 
			alert("请选择要审核的行") 
		}else{
			$('#myModal_Edit').modal('show')
			var i=0
			for(;i<checkboxes.length;i++){ 
				var samcode=$(checkboxes[i]).attr("id")
				$http.post('rucsl/shulsh/shenh?id='+samcode).success(function(data, status, headers, config) {
						count++
// 						$('#myModal_Edit').modal('show')
						jindz+=jidkd
						$('#shenhbar').css('width',jindz+'%')
						$('#shenhbar span').html(jindz+'%完成')
						if(count==l){
							$('#myModal_Edit').modal('hide')
							alert("审核完成！");
						}
					if(data[0]>0){
// 						$('#myModal_Edit').modal('hide') 
// 						alert("审核成功！");
						var zhuangt = $scope.search.zhuangt;
						var sDate = $scope.search.sDate;
						var eDate = $scope.search.eDate;
						$http.post('rucsl/shulsh/getTableData?zhuangt='+zhuangt+'&sDate='+sDate+'&eDate='+eDate).
						success(function(data, status, headers, config) {
							$scope.dataList=data.data
						});
					}else{
// 						alert("审核失败！");
					}
				});
			}

		}
	}
	
	$scope.huit=function(){ 
		var checkboxes=$('input:checkbox[name=checkId]:checked') 
		if(checkboxes.length==0){
			alert("请选择要回退的行!") 
		}else{
			for(var i=0;i<checkboxes.length;i++){
				var samcode=$(checkboxes[i]).attr("id")
				//查出月结单编号
				var jiesxxList=[]
				$http.post('rucsl/shulsh/getJiesxxList',{samcode:samcode}).success(function(data) {
					jiesxxList=data
					var jiesdbhs=""
					for(var i in jiesxxList){
						jiesdbhs=jiesdbhs+','+jiesxxList[i].JIESBH
					}
					if(jiesdbhs==""){
						$http.post('rucsl/shulsh/deleteRijsd', {//删除日结算单
							list : angular.toJson(jiesxxList)
						}).success(function(data) {
							$http.post('rucsl/shulsh/huit?id='+samcode).
							success(function(data, status, headers, config) {
								if(data[0]>0){
									alert("取消成功！");
									var zhuangt = $scope.search.zhuangt;
									var sDate = $scope.search.sDate;
									var eDate = $scope.search.eDate;
									$http.post('rucsl/shulsh/getTableData?zhuangt='+zhuangt+'&sDate='+sDate+'&eDate='+eDate).
									success(function(data, status, headers, config) {
										$scope.dataList=data.data
									});
								}else{
									alert("取消失败！");
								}
							});
						});
					}else {
						alert("已生成月结算单，不允许取消，结算单:" + jiesdbhs.substr(1, jiesdbhs.length))
					}
				})
			}
		}
	}
})
