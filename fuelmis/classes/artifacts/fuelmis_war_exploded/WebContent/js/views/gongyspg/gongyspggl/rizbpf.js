gddlapp.controller('rizbpfCtrl', function($scope,$rootScope,$http,$log,$location) {
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
		rukdbh:''
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
	
	$scope.refresh=function(){
		$http.post('gongyspg/pingggl/rizbpf/getRizbpf',{condition : angular.toJson($scope.search)}).success(function(data) {
			$scope.datalist=data;
		});
    }
	
	$scope.computeScore=function(){
		$scope.cmptArray=[]
		$("input[type=checkbox]:checked").not("#selectAll").each(function(){
			$scope.cmptArray.push($scope.datalist[$(this).attr("id")]) 
		});
		$http.post('gongyspg/pingggl/rizbpf/computeScore',{data:angular.toJson($scope.cmptArray)}).success(function(data) {
			alert(data);
		});
	}
	$scope.fab=function(){
		$scope.fabArray=[]
		$("input[type=checkbox]:checked").not("#selectAll").each(function(){
			if($scope.datalist[$(this).attr("id")].JIFB_ID!=null){
				$scope.fabArray.push($scope.datalist[$(this).attr("id")]) 
			}
		});
		$http.post('gongyspg/pingggl/rizbpf/fab',{data:angular.toJson($scope.fabArray)}).success(function(data) {
			alert("发布成功！");
		});	
	}
	$scope.fabCancel=function(){
		$scope.fabCancelArray=[]
		$("input[type=checkbox]:checked").not("#selectAll").each(function(){
			if($scope.datalist[$(this).attr("id")].JIFB_ID!=null){
				$scope.fabCancelArray.push($scope.datalist[$(this).attr("id")].JIFB_ID) 
			}
		});
		$http.post('gongyspg/pingggl/rizbpf/fabCancel',{data:angular.toJson($scope.fabCancelArray)}).success(function(data) {
			alert("发布取消成功！");
		});	
	}
	
	
})
