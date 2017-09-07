gddlapp.controller('guohmxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.GuohmxTitle='过衡明细';
	var date=new Date();
	$scope.search = new Object();
	$scope.search.diancid = 515;
	$scope.search.pinzid = -1;
	$scope.search.meikxxb_id = -1;
	$scope.search.gongys_id = -1;
	$scope.search.sDate = date.format('yyyy-MM'+'-01');
	$scope.search.eDate = date.format('yyyy-MM-dd');
		

	$scope.refresh = function(){
		var gongys_id = $scope.search.gongys_id;
		$http.post('yansgl/getGuohmxAll',{
			sDate:$scope.search.sDate,
			eDate:$scope.search.eDate,
			diancid:$scope.search.diancid,
			gongys_id:$scope.search.gongys_id,
			meikxxb_id:$scope.search.meikxxb_id,
			pinzid:$scope.search.pinzid,
			jihkj:$scope.search.jihkj}).success(function(data){
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
		$("#reportpage1 table:eq(1)").css(
				"zoom","0.7"
		);
		$("#reportpage1 table:eq(0)").css(
				"zoom","0.7"
		);
		$("#reportpage1 table:eq(2)").css(
				"margin-top","-10px"
		);
	});		
	}
	$scope.refresh();
	
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	$(".datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	
});