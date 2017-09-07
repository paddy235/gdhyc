gddlapp.controller('changnfycxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.changnfycxTitle='厂内费用查询';
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
		diancid : 515
	};
	
	$http.post('yuebgl/guodbb/getChangnfycx').success(function(data) {
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
		$http.post('yuebgl/guodbb/getChangnfycx',{riq:riq,dianc:diancid}).success(function(data) {
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
	$scope.searchData();
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});

function openLinkZaf(){
	var width = screen.availWidth;
	var height = screen.availHeight;
	window.open ('views/yuebgl/guodbb/changnfycxMX.jsp','newwindow','height='+(height/2+100)
			+',width='+width/2+',top='+(height/4-100)+',left='+width/4
			+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}