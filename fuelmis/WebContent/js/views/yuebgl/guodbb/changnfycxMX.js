gddlapp.controller('changnfycxMXCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.changnfycxMXTitle='厂内费用明细查询';
	
	$http.post('../../../yuebgl/guodbb/getChangnfycxMX').success(function(data) {
		document.getElementById("report").innerHTML = data[0].html;
		
		var totalPage = data[0].pageCount;
		
		if(totalPage>1){
			for(var i = 2;i <= totalPage;i++){
				$('#reportpage'+i).css('display','none');
			}
		}
		
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
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});