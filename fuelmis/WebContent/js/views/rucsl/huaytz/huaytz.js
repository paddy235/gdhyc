gddlapp.controller('huaytzCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.huaytzTitle='化验台账';
	$scope.search = new Object();
	$scope.search.diancid=515;
	$scope.search.pinzid = -1;
	$scope.search.yunsfs = -1;

	$scope.search.strdate = timeStamp2String(2);
	$scope.search.enddate = timeStamp2String(1);


	function timeStamp2String(type){
		var datetime = new Date();
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		if(type == 1){
			return year + "-" + month + "-" + date;
		}else{
			return year + "-" + month + "-"+"01"
		}

	}


//	$http.post('shultz/getTableInfo').success(function(data) {
//		document.getElementById("report").innerHTML = data;
//	});

	$scope.refresh = function(){
		$http.post('huaytz/getTableInfo',{strdate:$scope.search.strdate,enddate:$scope.search.enddate,diancid:$scope.search.diancid,yunsfs:$scope.search.yunsfs,pinzid:$scope.search.pinzid}).success(function(data){
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
	$scope.refresh();
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}



}).controller('huaytzCtrlByRiq', function($scope,$rootScope,$http,$log,$location) {
	$scope.huaytzTitle='化验台账';
	$scope.search = new Object();
	$scope.search.diancid=515;
	$scope.search.strdate = timeStamp2String(2);
	$scope.search.enddate = timeStamp2String(1);
	function timeStamp2String(type){
		var datetime = new Date();
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		if(type == 1){
			return year + "-" + month + "-" + date;
		}else{
			return year + "-" + month + "-"+"01"
		}

	}

	$scope.refresh = function(){
		$http.post('huaytz/getTableInfoByRiq',{search:angular.toJson($scope.search)}).success(function(data){
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
	$scope.refresh();

	$scope.printPage = function() {
		$("#report").jqprint();
	}



});