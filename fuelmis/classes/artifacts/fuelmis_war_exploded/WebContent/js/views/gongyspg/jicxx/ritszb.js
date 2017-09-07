gddlapp.controller('ritszbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.rizbpfTitle='日指标评分';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	$scope.isshow=false;
	var saveData="";
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		gongysid:-1
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
            	$("input[type=checkbox]").removeAttr("checked");//2016.1.19移除checked属性
                sd.firstElementChild.checked=true;
            }
        }
    }
	
	$scope.load=function(){
		$http.post('gongyspg/jicxx/getRitszb',{condition : angular.toJson($scope.search)}).success(function(data) {
			$scope.datalist=data;
		});
    }
	$scope.save=function(){
		$http.post('gongyspg/jicxx/saveRitszb',{data : angular.toJson($scope.datalist)}).success(function(data) {
			alert("保存成功！");
			$scope.load();
		});
	}
})
