gddlapp.controller('ritspfCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.rizbpfTitle='日特殊评分';
	//日期插件数据
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		gongysid: -1
	}


	$scope.fun=function(){
        var e=window.event||arguments[0];
        var src=e.srcElement||e.target;
        if(src.nodeName=="TD"){
            var par=src.parentNode;
            var sd=par.getElementsByTagName("td")[0];
            if(sd.firstElementChild.checked==true){
                sd.firstElementChild.checked=false;
            }else{
                sd.firstElementChild.checked=true;
            }
        }
    }
	$scope.refresh=function(){
		$http.post('gongyspg/pingggl/ritspf/getRitspf',{condition : angular.toJson($scope.search)}).success(function(data) {
			$scope.datalist=data;
		});
		table_page();
	}
	$scope.save=function(){
		$http.post('gongyspg/pingggl/ritspf/saveRitspf',{data : angular.toJson($scope.datalist)}).success(function(data) {
			alert("保存成功！");
			$scope.refresh();
		});
	}
	
});