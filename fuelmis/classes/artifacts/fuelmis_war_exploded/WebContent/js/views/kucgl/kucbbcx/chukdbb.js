gddlapp.controller('chukdbbcxCtrl', function($scope, $rootScope,$routeParams, $http/*,$location*/) {
	//--------------------------------------日期定义--------------------------------------------------------------------------

	var begin=new Date();
	var end=new Date();
	new Date(begin.setMonth((new Date().getMonth()-1)));
	var begintime= begin.format("yyyy-MM");
	var endtime=end.format("yyyy-MM");
	//-----------------------------------------------------------------------------------------------------------------------
	$scope.search = {
		Date:endtime
	};
	//查看详细
	$scope.show=function(){
		alert("hello!");
		var checkboxes=$("#example input:checked");
		if(checkboxes.length==0){
			alert("请选择要查看的行！") 
		}else{
			var id=checkboxes.attr("id");
			$http.post('diaoygl/diaodgl/getDiaodjh', {
				id : id
			}).success(function(data) {
				$scope.diaodjh=data;
				$('#myModal_diaodjh input').attr("disabled",true);
				$('#myModal_diaodjh').modal('show');
			});
		}
	};
	$scope.searchData = function() {
		$http.post('kucgl/kucbb/getChukdbb',{search:angular.toJson($scope.search)}).success(function(data) {
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

