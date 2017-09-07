gddlapp.controller('ribtbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.ribtbTitle='日报填报';
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-'+day,
		diancid : 515
	}
	
	$scope.sZhuangtList = [{"name":"否","value":0},{"name":"是","value":1}];
	
	$http.post('diaoygl/ribgl/getAll',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
	    success(function(data, status, headers, config) {
	    	$scope.ribList = data;
	    	for(var i=0;i<data.length;i++){
	    		if(data[i].ZHUANGT==1){
	    			$(".table td input").prop("disabled",true);
    				$(".table-toolbar button[id$='Btn']").prop("disabled",true);
    			}else{
    				$(".table-toolbar button[id$='Btn']").prop("disabled",false);
    				$(".table input").prop("disabled",false);
    			}
	    	}
		});

	$scope.searchData = function() {
		$http.post('diaoygl/ribgl/getAll',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
		    success(function(data, status, headers, config) {
		    	$scope.ribList = data;
		    	for(var i=0;i<data.length;i++){
		    		if(data[i].ZHUANGT==1){
		    			$(".table td input").prop("disabled",true);
	    				$(".table-toolbar button[id$='Btn']").prop("disabled",true);
	    			}else{
	    				$(".table-toolbar button[id$='Btn']").prop("disabled",false);
	    				$(".table input").prop("disabled",false);
	    			}
		    	}
			});
	}
	
	$scope.createData = function() {
		document.getElementById('tt').innerHTML = $scope.search.sDate;
		$('#myModal_Del').modal('show');
	}
	
	$scope.create = function() {
		$('#myModal_Del').modal('hide');
		$http.post('diaoygl/ribgl/createData',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
		    success(function(data, status, headers, config) {
		    	if(data[0]>0){
		    		$http.post('diaoygl/ribgl/getAll',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
					    success(function(data, status, headers, config) {
					    	$scope.ribList = data;
						});
		    		alert("生成成功");
		    	}else{
		    		alert($scope.search.sDate+"来煤数据未审核，请到数据审核处进行审核！");
		    	}
			});
	}
	
	$scope.save = function() {
		$http.post('diaoygl/ribgl/save',{strList:angular.toJson($scope.ribList)}).
		    success(function(data, status, headers, config) {
		    	$http.post('diaoygl/ribgl/getAll',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
				    success(function(data, status, headers, config) {
				    	$scope.ribList = data;
				    	alert("保存成功！");
					});
		    	
			});
	}
	
	//上传
	 $scope.RibtbShangc = function (){
		 $http.post("diaoygl/ribgl/getRibtbShangc",{riq:$scope.search.sDate}).success(function (data){
			 alert("上传成功！");
			 $scope.searchData();
		 });
	 }
//	 scope.$watch(attrs.aaData, function(value) {
//         var val = value || null;
//         if (val!=undefined&&val.length!=0) {
//             dataTable.fnClearTable();
//             dataTable.fnAddData(scope.$eval(attrs.aaData)); 
//             
//         }else{
//         	dataTable.fnClearTable();
//         }
//     }) 
})