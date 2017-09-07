gddlapp.controller('kucsscbhsCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.Title = '库存实时成本核算';
	$scope.search = new Object();
	// ------------------------------------------日期定义-------------------------------------------
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	$scope.search = {
			Date : year+'-'+month,
		rukdbh:''
	};
 
	//暂估台帐
	$scope.getKucsscbAll=function(){
		 $http.post('kucgl/getKucsscbAll',{Date:$scope.search.Date}).success(function(data) {
			 var pagesize = 10;
			 var nowpage = 1;
		     var totalCount = 0;
			 $scope.list = data
				if($scope.list.length>0){
					totalCount = $scope.list.length;//记录条数
				}
				var totalPage = totalCount%pagesize>0?parseInt(totalCount/pagesize)+1:totalCount/pagesize;//记录页数
				$("#totalCount").html(totalCount);
				$("#totalPage").html(totalPage);
				$("#page").html(nowpage);
				//上一页
				$("#pre").bind("click",function(){
					if(nowpage<1){
						return false;
					}else if(nowpage == 1){
						return false;
					}else{
						nowpage = nowpage-1==0?nowpage:nowpage-1;
						$("#page").html(nowpage);
						fillTable($scope.list,nowpage,pagesize,totalCount);
					}
				})
				//下一页
				$("#next").bind("click",function(){
					if(nowpage==totalPage){
						return false;
					}else if(totalPage==1){
						return false;
					}else if(totalPage==0){
						return false;
					}else{
						nowpage = nowpage+1>totalPage?totalPage:nowpage+1;
						$("#page").html(nowpage);
						fillTable($scope.list,nowpage,pagesize,totalCount);
					}
				});
				//首页
				$("#pres").bind("click",function(){
						$("#page").html(1);
						fillTable($scope.list,1,pagesize,totalCount);
				})
				//尾页
				$("#nexts").bind("click",function(){
					$("#page").html(totalPage);
					fillTable($scope.list,totalPage,pagesize,totalCount);
			});
				 fillTable($scope.list,nowpage,pagesize,totalCount);
	 })
 }
	//暂估分页
	function fillTable(data,now,page,total){
		//填充表格数据
		$("#dataTbody").html("");
		//alert(totalPage);
		var beginNum = (now-1)*page;
		var toNum = page*now>total-1?total-1:page*now-1;
		//alert(beginNum+"==="+toNum);
		$.each($scope.list,function(i, item){
			if(i>=beginNum && i<=toNum){
				if(i == toNum && item.KUCZZ==undefined){
					item.KUCZZ = '';
					item.KUCWZ = '';
					item.KUCWL = '';
					item.HUOZ = '';
					item.YEWLX ='';
					item.CHURKLX ='';
					item.YEWRQ ='';
					item.KUAIJRQ ='';
					item.JIZRQ ='';
					var trdata = "<tr>"
					      +"<td>"+item.RUKDBH+"</td>"
					      +"<td>"+item.KUCZZ+"</td>"
					      +"<td>"+item.KUCWZ+"</td>"
					      +"<td>"+item.KUCWL+"</td>"
					      +"<td>"+item.HUOZ+"</td>"
					      +"<td>"+item.SHUL+"</td>"
					      +"<td>"+item.JINE+"</td>"
					      +"<td>"+item.YEWLX+"</td>"
					      +"<td>"+item.CHURKLX+"</td>"
					      +"<td>"+item.YEWRQ+"</td>"
					      +"<td>"+item.KUAIJRQ+"</td>"
					      +"<td>"+item.JIZRQ+"</td>"
					    +"</tr>";
					$("#dataTbody").append(trdata);
				}else{
					var trdata = "<tr>"
					      +"<td>"+item.RUKDBH+"</td>"
					      +"<td>"+item.KUCZZ+"</td>"
					      +"<td>"+item.KUCWZ+"</td>"
					      +"<td>"+item.KUCWL+"</td>"
					      +"<td>"+item.HUOZ+"</td>"
					      +"<td>"+item.SHUL+"</td>"
					      +"<td>"+item.JINE+"</td>"
					      +"<td>"+item.YEWLX+"</td>"
					      +"<td>"+item.CHURKLX+"</td>"
					      +"<td>"+item.YEWRQ+"</td>"
					      +"<td>"+item.KUAIJRQ+"</td>"
					      +"<td>"+item.JIZRQ+"</td>"
					    +"</tr>";
					$("#dataTbody").append(trdata);
				}
				
			}
		})
	   	window.parent.dyniframesizesub('subcontentiframe');
	}
	
	$scope.getKucsscbAll();
	
	$scope.getKucsscbAlls=function (){
		$scope.getKucsscbAll();
	}
});

