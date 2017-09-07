gddlapp.controller('pandcxCtrl', function($scope,$rootScope,$http,$log,$location) { 
	$scope.pandcxTitle='盘点查询'; 
	var date = new Date();
	var year = date.getFullYear();
	
	$scope.search = {
		riq : year,
		diancid : 515
	};
	
//	$http.post('kucgl/pandcx/getReport').success(function(data) {
//		document.getElementById("report").innerHTML = data[0].html;
//		
//		var totalPage = data[0].pageCount;
//		
//		if(totalPage>1){
//			for(var i = 2;i <= totalPage;i++){
//				$('#reportpage'+i).css('display','none');
//			}
//		}
//		
//		$("#pagination_zc").twbsPagination({
//			first : '首页',
//			prev : '前一页',
//			next : '下一页',
//			last : '尾页',
//			totalPages :data[0].pageCount,
//			visiblePages :5,
//			onPageClick : function(event, page) {
//				for(var i = 1;i <= totalPage;i++){
//					if(i==page){
//						$('#reportpage'+i).css('display','block');
//					}else{
//						$('#reportpage'+i).css('display','none');
//					}
//				}
//			}
//		});
//	});
	$scope.searchData = function() {
		var riq = $scope.search.riq;
		var diancid = $scope.search.diancid;
		$http.post('kucgl/pandcx/getReport',{riq:riq,dianc:diancid}).success(function(data) {
			document.getElementById("report").innerHTML = data[0].html;
			
			var totalPage = data[0].pageCount;
			
			if(totalPage>1){
				for(var i = 2;i <= totalPage;i++){
					$('#reportpage'+i).css('display','none');
				}
			}
			
			$('#pagination_zc').remove();
			$("#pagination_box").append('<ul id="pagination_zc"></ul>');
			$("#pagination_zc").twbsPagination({
				first : '首页',
				prev : '前一页',
				next : '下一页',
				last : '尾页',
				totalPages :data[0].pageCount,
				visiblePages :5,
				onPageClick : function(event, page) {
					for(var i = 1;i <= totalPage;i++){
						if(i==page){
							$('#reportpage'+i).css('display','block');
						}else{
							$('#reportpage'+i).css('display','none');
						}
					}
				}
			});
		});
	}
	$scope.searchData();
	$scope.shangb=function () {
		$http.post('kucgl/pandcx/shangb',{search:angular.toJson($scope.search)}).success(function () {
			
		})

		
	}
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});