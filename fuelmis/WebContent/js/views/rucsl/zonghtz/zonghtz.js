gddlapp.controller('zonghtzCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.zonghtzTitle='综合台账';
	$scope.search = new Object();
	$scope.search.pinzid = -1;
	$scope.search.meik = -1;
	$scope.search.diancid = 515;
	$scope.search.type = "1";
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
	
	$http.post('zonghtz/getTableInfo').success(function(data) {
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
		if(!angular.isDefined($scope.search.gongysid)){			
			$http.post('zonghtz/getTableInfo',{strdate:$scope.search.strdate,enddate:$scope.search.enddate,diancid:$scope.search.diancid,meik:$scope.search.meik,pinzid:$scope.search.pinzid,type:$scope.search.type,xiax:$scope.search.xiax,shangx:$scope.search.shangx}).success(function(data){
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
	}
	$scope.refresh();
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

	
	
	$('#gongysid').typeahead({ // 供应商   jianc->jianc 
		source:function(query, process){
			$http.post("common/getGongysLikeJianc",{code:query}).
			success(function(data) {
				var arr = new Array();
				for (i in data){
					arr.push(data[i].name);
				}
				process(arr);
			});
		},
		items: 10
	});
	
});