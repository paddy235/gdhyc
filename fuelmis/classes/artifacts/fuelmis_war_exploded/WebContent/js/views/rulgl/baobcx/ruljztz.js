gddlapp.controller('ruljztzCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.ruljztzTitle='检质台账';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		diancid : 515
	};
	
	$http.post('rulgl/baobcx/getRuljztz').success(function(data) {
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
	
	$scope.searchData = function() {
		var sDate = $scope.search.sDate;
		var eDate = $scope.search.eDate;
		var diancid = $scope.search.diancid;
		$http.post('rulgl/baobcx/getRuljztz',{sDate:sDate,eDate:eDate,dianc:diancid})
		.success(function(data) {
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
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	
});