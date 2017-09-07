gddlapp.controller('ranlrcgjhdCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.search = new Object();
	$scope.search.diancid=515;
	$scope.search.pinzid = -1;
	$scope.search.yunsfs = -1;
	
	//-------------------------------------------日期处理--------------------------------------------------
	var date = new Date();
	var year = date.getFullYear(); 
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	$scope.riq =  year+'-'+month+'-'+day
	//----------------------------------------------------------------------------------------------------
	$scope.refresh = function(){								
		$http.post('diaoygl/baobcx/getRanlrcgjhd',{riq:$scope.riq}).success(function(data){
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
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

	
}).controller('duizdCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.search = new Object();
	$scope.search.diancid=515;
	$scope.search.pinzid = -1;
	$scope.search.yunsfs = -1;
		
	//-------------------------------------------日期处理--------------------------------------------------
	var date = new Date();
	var year = date.getFullYear(); 
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1; 
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	$scope.riq =  year+'-'+month+'-'+day
	//----------------------------------------------------------------------------------------------------
	
	$scope.refresh = function(){								
		$http.post('diaoygl/baobcx/getDuizd',{riq:$scope.riq}).success(function(data){
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
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

	
});