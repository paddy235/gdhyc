gddlapp.controller('huayrbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.huayrbTitle='化验日报';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	$scope.search.date = timeStamp2String();
	
		
	function timeStamp2String(){
	    var datetime = new Date();
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    return year + "-" + month + "-" + date;
	}	
		
	$http.post('huayrb/getTableInfo').success(function(data) {
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
	
	$scope.refresh = function(){								
		$http.post('huayrb/getTableInfo',{date:$scope.search.date,diancid:$scope.search.diancid}).success(function(data){
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