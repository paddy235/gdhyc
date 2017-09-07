gddlapp.controller('jiesdcxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.jiesdcxTitle='结算单查询';

	// var date = new Date();
	// var year = date.getFullYear();
	// var month = date.getMonth()<10?'0'+(date.getMonth()+1):(date.getMonth()+1);
	// var day = date.getDate()<10?'0'+date.getDate():date.getDate();


	//--------------------------------------日期定义--------------------------------------------------------------------------


	var begin=new Date();
	var sDate= begin.format("yyyy-MM"+"-01");
	var end=new Date();
	var eDate=end.format("yyyy-MM-dd");


	
	$scope.search = {
		sDate : sDate,
		eDate : eDate,
		chaxlx : 1,
		gongys:-1,
		jiesbh:'-1'
	};
	


	$scope.chaxlxList = [{"name":"结算单","value":1},{"name":"验收明细","value":2},{"name":"过衡单","value":3},{"name":"单批次明细","value":4}];
	
	$scope.selJiesbh = function(){
		$http.post('jiesgl/jiesdcx/getAllJiesbh',{search:angular.toJson($scope.search)}).success(function(data) {
			$scope.jiesbhList = data;
			
				$scope.search.jiesbh='-1'
			
		});
	}
	$scope.selJiesbh();
	$scope.refresh = function() {
		$http.post('jiesgl/jiesdcx/getJiesd',{search:angular.toJson($scope.search)}).success(function(data) {
			document.getElementById("report").innerHTML = data[0].html;
			$("#reportpage1 table:eq(0)").css(
					"margin-top","10px"
				);
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
	$scope.searchData = function() {
		$scope.selJiesbh();		
		if($scope.search.jiesbh=='-1'){
			//alert("请选择结算编号！");
			return false;
		}
		
		$scope.refresh();
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});