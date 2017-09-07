gddlapp.controller('YuejstjtzCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.YuejstjtzTitle='月结算统计台帐';

	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();

	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		gongys : -1,
		pinz : -1,
		hetbh : '-1'
	};
	
	$scope.getHetbh=function(){
		$http.post('jiesgl/jiesdcx/getHetbh',{search:angular.toJson($scope.search)}).success(function(data) {
			$scope.yuejshetbhList = data;
		});
	}
	$scope.getHetbh();
	$scope.getYuejstjtz= function(){
		$http.post('jiesgl/jiesdcx/getYuejstjtz',{search:angular.toJson($scope.search)}).success(function(data) {

			document.getElementById("report").innerHTML = data[0].html;

			var totalPage = data[0].pageCount;

			if (totalPage > 1) {
				for (var i = 2; i <= totalPage; i++) {
					$('#reportpage' + i).css('display', 'none');
				}
			}

			$('#pagination_zc').remove();
			$("#pagination_box").append('<ul id="pagination_zc"></ul>');
			$("#pagination_zc").twbsPagination({
				first: '首页',
				prev: '前一页',
				next: '下一页',
				last: '尾页',
				totalPages: data[0].pageCount,
				visiblePages: 5,
				onPageClick: function (event, page) {
					for (var i = 1; i <= totalPage; i++) {
						if (i == page) {
							$('#reportpage' + i).css('display', 'block');
						} else {
							$('#reportpage' + i).css('display', 'none');
						}
					}
				}
			});
		});
		
	}
	 
	//$scope.getYuejstjtz();
});