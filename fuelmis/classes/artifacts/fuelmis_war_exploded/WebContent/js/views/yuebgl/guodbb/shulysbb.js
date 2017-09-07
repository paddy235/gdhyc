gddlapp.controller('shulysbbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.shulysbbTitle='数量验收报表';
	//var date = new Date();
	//var year = date.getFullYear();
	//var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	//--------------------------------------日期定义--------------------------------------------------------------------------
	var begin=new Date();
	var end=new Date();
	begin.setMonth(begin.getMonth()-1);
	var begintime= begin.format("yyyy-MM");
	var endtime=end.format("yyyy-MM-dd");
	//-----------------------------------------------------------------------------------------------------------------------
	$scope.search = {
		riq : begintime,
		diancid : 515,
		leix:0
	};
	$http.post('yuebgl/guodbb/getShulysbb').success(function(data) {
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
		var riq = $scope.search.riq;
		var diancid = $scope.search.diancid;
		var leix = $scope.search.leix;
		$http.post('yuebgl/guodbb/getShulysbb',{riq:riq,dianc:diancid,leix:leix}).success(function(data) {
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
	$scope.searchData();
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});