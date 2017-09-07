gddlapp.controller('gongysypfcxCtrl', function($scope, $rootScope,$routeParams, $http/*,$location*/) {
	//--------------------------------------日期定义--------------------------------------------------------------------------

	var begin=new Date();
	var end=new Date();
	new Date(begin.setMonth((new Date().getMonth())));
	var begintime= begin.format("yyyy-MM");
	var endtime=end.format("yyyy-MM-dd");
	//-----------------------------------------------------------------------------------------------------------------------
	$scope.search = {
		riqBegin:begintime+'-01',
		riqEnd:endtime,
		diancxxb_id:515,
		gongys_id:-1
	};
	$scope.searchData = function() {
		$http.post('gongys/baobcx/getGongysypfbb',{search:angular.toJson($scope.search)}).success(function(data) {
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
				totalPages :totalPage,
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
	};
	$scope.searchData();
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
});

