Date.prototype.format =function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
            (this.getFullYear()+"").substr(4- RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
                RegExp.$1.length==1? o[k] :
                        ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
/*
 * 获取当前时间的前一天
 *
 */
 function getYesterday(){
	 	var d= new Date();		
	 	var y=d.getFullYear();
		var m=d.getMonth()+1;
		var day=d.getDate()-1;
		if(day==0){
			day=new Date(d-1000*60*60*24).getDate();
			m=d.getMonth();
			if(m==0){
				y=d.getFullYear()-1;
				m=12;
			}
		}	
		var timestr=y+"-"+(m-1)+"-"+day;
		var timearr=timestr.split("-");
		var yesterday=new Date(timearr[0],timearr[1],timearr[2],"","","");
		return yesterday
}
/**
 * 获取当前月的第一天
 */
function getCurrentMonthFirst(){
	var d= new Date();		
 	var y=d.getFullYear();
	var m=d.getMonth()+1;
	var day=d.getDate()-1;
	if(day==0){
		day=new Date(d-1000*60*60*24).getDate();
		m=d.getMonth();
		if(m==0){
			y=d.getFullYear()-1;
			m=12;
		}
	}
	var timestr=y+"-"+(m-1)+"-01";
	var timearr=timestr.split("-");
	var month=new Date(timearr[0],timearr[1],timearr[2],"","","");
	return month
}
/**
 * 获取当前月的最后一天

function getCurrentMonthLast(){
    var date=new Date();
    var currentMonth=date.getMonth()-1;
    var nextMonth=++currentMonth;
    var currentday = date.getDay()+1;
    var nextMonthFirstDay=new Date(date.getFullYear(),nextMonth,currentday);
    var oneDay=1000*60*60*24;
    return new Date(nextMonthFirstDay-oneDay);
} */
//ready 开始
$(document).ready(function() {
//滾動信息
$.ajax({
        type : "get",
        url : 'zonghzs/ghcqk/getRanlxxgdts',
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success: function(data) {
        	var text=data.datalist[0].LAIMMX;
        	//alert(text+","+data.datalist[0]);
        	$('#scrollDiv marquee').text(text);
        	 
        }
});



//表格插件js
//表格一开始
$('#tableL01').bootstrapTable({
		height:183,
//列数据
		columns: [[{
			align:'center',
			valign:'middle',
			field: 'LAIHC',
			title: '来耗存',
			rowspan:2
		}, {
			align:'center',
			valign:'middle',
			field: 'dr',
			title: '当日',
			colspan:3
		}, {
			align:'center',
			valign:'middle',
			field: 'bz',
			title: '本周',
			colspan:3
		},{
			align:'center',
			valign:'middle',
			field: 'by',
			title: '本月',
			colspan:3}],
		[{
			align:'center',
			valign:'middle',
			field: 'DANGRBQ',
			title: '本期'
		}, {
			align:'center',
			valign:'middle',
			field: 'DANGRTB',
			title: '同比',
			formatter:trend
		},{
			align:'center',
			valign:'middle',
			field: 'DANGRHB',
			title: '环比',
			formatter:trend
			}, {
			align:'center',
			valign:'middle',
			field: 'BENZBQ',
			title: '本期'
		}, {
			align:'center',
			valign:'middle',
			field: 'BENZTB',
			title: '同比',
			formatter:trend
		},{
			align:'center',
			valign:'middle',
			field: 'BENZHB',
			title: '环比',
			formatter:trend
			}, {
			align:'center',
			valign:'middle',
			field: 'BENYBQ',
			title: '本期'
		}, {
			align:'center',
			valign:'middle',
			field: 'BENYTB',
			title: '同比',
			formatter:trend
		},{
			align:'center',
			valign:'middle',
			field: 'BENYHB',
			title: '环比',
			formatter:trend
		}]]});
		//模拟数据结束
	//上下浮动箭头样式添加
	function trend(value,row){
		var trendIcon=parseFloat(value)<=0?(parseFloat(value)==0?' ':'glyphicon glyphicon-arrow-down'):'glyphicon glyphicon-arrow-up';
		return "<div><i class='"+trendIcon+"'></i>"+value+"</div>"
	}
	//表格插件一结束

//表格插件(表二)开始
//声明用来统计合计（累计来煤，月计划）的变量
//var yjhArr=[];
//var ljlmArr=[];
$('#tableL02').bootstrapTable({
	height:535,
	//模拟数据
	columns: [{
		align:'center',
		valign:'middle',
		field: 'ID',
		title: '序号'
	}, {
		align:'center',
		valign:'middle',
		field: 'GONGYS',
		title: '供应商'
	}, {
		align:'center',
		valign:'middle',
		field: 'PINZ',
		title: '品种'
	},{
		align:'center',
		valign:'middle',
		field: 'REZ',
		title: '热值'
	},{
		align:'center',
		valign:'middle',
		field: 'MEIJ',
		title: '煤价'
	},{
		align:'center',
		valign:'middle',
		field: 'BIAOMDJ',
		title: '标煤单价'
	},{
		align:'center',
		valign:'middle',
		field: 'DANGRLM',
		title: '当日来煤'
	},{
		align:'center',
		valign:'middle',
		field: 'LEIJLM',
		title: '累计来煤'
	},{
		align:'center',
		valign:'middle',
		field: 'YUEJHL',
		title: '月计划量'
	},{
		align:'center',
		valign:'middle',
		field: 'JIND',
		title: '月计划完成进度',
		formatter:progress
	}]
});
//模拟数据结束
//显示进度条的自定义列
function progress (value,row){
	var width=parseInt(row.JIND);
	var red='#e63737';
	var blue='#b6ccf4';
//	yjhArr.push(row.YUEJHL);
//	ljlmArr.push(row.JIND);
if(width>100){
	width=100;
}
	return "<div style='height:20px;border:1px solid #b6ccf4;'><span style='display:block;float:left;width:"+width+"%;height:100%;background-color:"+(width>=100?red:blue)+";'>"+value+"%</span></div>"
}
/*
//计算合计数值的方法
function yjhTotal(){
	var subyjh=0;
	var subljlm=0;
	var row=[];
	for(var i=0;i<yjhArr.length;i++){
		if(yjhArr[i]==""){
			yjhArr[i]=0;
		}
		subyjh+=parseFloat(yjhArr[i]);
	}
	for(var j=0;j<ljlmArr.length;j++){
		if(ljlmArr[j]==""){
			ljlmArr[j]=0;
		}
		subljlm+=parseFloat(ljlmArr[j]);
	}
	row.push({
		ID:1,
		GONGYS:'合计',
		PINZ:'',
		REZ:'',
		MEIJ:'',
		BIAOMDJ:'',
		DANGRLM:'',
		LEIJLM:subljlm,
		YUEJHL:subyjh,
		JIND:'107%'
	});
	return row
}
*/
//表格插件二结束
//表格插件三开始
	$('#tableL03').bootstrapTable({
		height:183,
		//模拟数据
		columns: [[{
			align:'center',
			valign:'middle',
			field: 'ID',
			title: '序号',
			rowspan:2
		}, {
			align:'center',
			valign:'middle',
			field: 'LX',
			title: '',
			rowspan:2
		},
		{
			align:'center',
			valign:'middle',
			field: 'rc',
			title: '入厂',
			colspan:3
		}, {
			align:'center',
			valign:'middle',
			field: 'rl',
			title: '入炉',
			colspan:3
		},{
			align:'center',
			valign:'middle',
			field: 'cz',
			title: '差值',
			colspan:3}],
			[{
				align:'center',
				valign:'middle',
				field: 'RCDQ',
				title: '本期'
			}, {
				align:'center',
				valign:'middle',
				field: 'RCHB',
				title: '同比'
			},{
				align:'center',
				valign:'middle',
				field: 'RCTB',
				title: '环比'
			}, {
				align:'center',
				valign:'middle',
				field: 'RLDQ',
				title: '本期'
			}, {
				align:'center',
				valign:'middle',
				field: 'RLHB',
				title: '同比'

			},{
				align:'center',
				valign:'middle',
				field: 'RLTB',
				title: '环比'
			}, {
				align:'center',
				valign:'middle',
				field: 'CZDQ',
				title: '本期'
			}, {
				align:'center',
				valign:'middle',
				field: 'CZTB',
				title: '同比'
			},{
				align:'center',
				valign:'middle',
				field: 'CZHB',
				title: '环比',
			}]]
	});
//表格插件三结束


	$("#rcrlqs").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		rcrlzb();
	});
	$("#rcrljz").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		rcrlzb();
	});
	$("#rcbmqs").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showRucbmdjLine();
	});
	$("#rcbmjz").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showRucbmdjLine();
	});
	$("#rctrqs").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showRuctrmjLine();
	});
	$("#rctrjz").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showRuctrmjLine();
	});
	$("#fkrcdjqs").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showFenkrcbmdjLine();
	});
	$("#fkrcdjjz").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showFenkrcbmdjLine();
	});
/*	
 * 库存曲线模块隐藏了，因此隐藏此日期框架
 */
 /*$("#kcmqx").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	});
*/
	$("#lmxx").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		lmxxsj();
	});
	$("#lhcrq").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		lhcsj();
	});
	$("#kcqxqs").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showKucunqkLine();
	});
	$("#kcqxjz").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showKucunqkLine();
	});
	$("#lhmqxqs").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showLaihaomeiqkLine();
	});
	$("#lhmqxjz").datepicker({
		format : 'yyyy-mm-dd',
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showLaihaomeiqkLine();
	});
	$("#gysny").datepicker({
		format : 'yyyy-mm',
		minViewMode : 1,
		maxViewMode : 1,
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showGyspie();
	});
	$("#mzny").datepicker({
		format : 'yyyy-mm',
		minViewMode : 1,
		maxViewMode : 1,
		language : "zh-CN",
		autoclose : true,
	}).on('changeDate',function(ev){
		showMzpie();
	});

//路径配置
require.config({
    paths: {
        echarts: '${ctx}/js/echarts'
    }
});
//右下角弹窗开始----------------------------------------------------------------------------------------------------------
/*function aleOpen(){
	$("#pengding").css({
		"bottom":"-20px"
	});
}
function aleClose(){
	$("#pengding").css({
		"bottom":"-240px"
	});
}
aleOpen();
var StClose=setTimeout(aleClose,5000);
$("#pengding").mouseenter(function(){
	clearTimeout(StClose);
	aleOpen()
});
$("#pengding").mouseleave(function(){
	aleClose()
});
var aletime=new Date().format("yyyy-MM-dd");
$("#dataShow").html(aletime);*/
//右下角弹窗结束----------------------------------------------------------------------------------------------------------
//使用
//firstlayer供应商饼状图
function showGyspie(){
	var yearmonth="";
	var from = $("#gysny").val();
    if(from==""){
    	yearmonth = getCurrentMonthFirst().format("yyyy-MM");
    	console.log(yearmonth);
        $("#gysny").val(yearmonth);
    }else{
    	yearmonth=from;
    }
    var pourl = "zonghzs/ghcqk/bingtgys?yearmonth="+yearmonth;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data) {
        	var gongysList=[];
        	var shulList=[];
        	var arr=[];
        	var dataList=[];
        	arr=data.datalist;
        	for(var i=0;i<arr.length;i++){
        		gongysList.push(arr[i].GONGYSMC);
        		shulList.push(parseFloat(arr[i].SHUL));
        		//var list="{value:"+parseFloat(arr[i].SHUL)+",name:"+arr[i].GONGYSMC+"}";
        		var list={};
        		list.value=parseFloat(arr[i].SHUL);
        		list.name=arr[i].GONGYSMC;
        		dataList.push(list);
        	};
        	require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
			require(
		    [
		        'echarts',
		        'echarts/chart/pie' // 使用饼状图就加载pie模块，按需加载
		    ],
		    function (ec) {
		        // 基于准备好的dom，初始化echarts图表
		        var myChart = ec.init(document.getElementById('gyspie')); 
		        
		        var option = {
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        x : 'left',
		        		y : 'top',
				        orient: 'horizontal',
				        data: gongysList
				    },
				    series : [
				        {
				            name: '供应商',
				            type: 'pie',
				            radius : '45%',
				            center: ['50%', '70%'],
				            data:dataList,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
	
	        // 为echarts对象加载数据 
	       	 	myChart.setOption(option); 
	    		}
			);
		}
    });
}
showGyspie();

//firstlayer煤种饼状图
function showMzpie(yearmonth){
	var yearmonth="";
	var from = $("#mzny").val();
    if(from==""){
    	yearmonth = getCurrentMonthFirst().format("yyyy-MM");
        $("#mzny").val(yearmonth);
    }else{
    	yearmonth=from;
    }
    var pourl = "zonghzs/ghcqk/bingtmz?yearmonth="+yearmonth;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data) {
        	var meikList=[];
        	var shulList=[];
        	var arr=[];
        	var dataList=[];
        	arr=data.datalist;
        	for(var i=0;i<arr.length;i++){
        		meikList.push(arr[i].PINZMC);
        		shulList.push(parseFloat(arr[i].SHUL));
        		//var list="{value:"+parseFloat(arr[i].SHUL)+",name:"+arr[i].GONGYSMC+"}";
        		var list={};
        		list.value=parseFloat(arr[i].SHUL);
        		list.name=arr[i].PINZMC;
        		dataList.push(list);
        	};
        	require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
	require(
	    [
	        'echarts',
	        'echarts/chart/pie' // 使用饼状图就加载pie模块，按需加载
	    ],
	    function (ec) {
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = ec.init(document.getElementById('mzpie')); 
	        
	        var option = {
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        x : 'left',
	        		y : 'top',
			        orient: 'horizontal',
			        data: meikList
			    },
			    series : [
			        {
			            name: '煤种',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:dataList,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
	
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
		}
	);
}
});
}
showMzpie();

//库存煤曲线
/*
function showKucunmeiLine(){
	require(
	    [
	        'echarts',
	        'echarts/chart/line' // 使用折线图就加载line模块，按需加载
	    ],
	    function (ec) {
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = ec.init(document.getElementById('kucunmeiline')); 
	        
	        var option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['来煤','耗煤','存煤','库存热值','库存标煤单价']
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    grid: {
			        left: '1',
			        right: '1%',
			        bottom: '1%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : ['2:00','5:00','8:00','15:00','18:00','20:00','23:00']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'来煤',
			            type:'line',
			            stack: '总量',
			            areaStyle: {normal: {}},
			            data:[120, 132, 101, 134, 90, 230, 210]
			        },
			        {
			            name:'耗煤',
			            type:'line',
			            stack: '总量',
			            areaStyle: {normal: {}},
			            data:[220, 182, 191, 234, 290, 330, 310]
			        },
			        {
			            name:'存煤',
			            type:'line',
			            stack: '总量',
			            areaStyle: {normal: {}},
			            data:[220, 182, 191, 234, 290, 330, 310]
			        },
			        {
			            name:'库存热值',
			            type:'line',
			            stack: '总量',
			            areaStyle: {normal: {}},
			            data:[220, 182, 191, 234, 290, 330, 310]
			        },
			        {
			            name:'库存标煤单价',
			            type:'line',
			            stack: '总量',
			            label: {
			                normal: {
			                    show: true,
			                    position: 'top'
			                }
			            },
			            areaStyle: {normal: {}},
			            data:[150, 232, 201, 154, 190, 330, 410]
			        }
			    ]
			};
	
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
	    }
	);
}
showKucunmeiLine();
*/

//入厂,入炉热值折线图
function showRucrulLine(qsrq,jzrq){
    var kucDate0=new Array();
    var kucDate1=[];
    var kucDate2=new Array();
    var pourl = "zonghzs/ghcqk/chartLoad?qsriq="+qsrq+"&jzriq="+jzrq;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data) {
        	kucDate0=data[0];
        	//kucDate1=data[1];
        	kucDate2=data[2];
        	//kucDate3=data[3];
        	kucDate4=data[4];
        	for(var i=0;i<kucDate2.length;i++){
        		//kucDate1[i] =parseFloat(kucDate1[i])
        		kucDate2[i] =parseFloat(kucDate2[i])
        		//kucDate3[i] =parseFloat(kucDate3[i])
        		kucDate4[i] =parseFloat(kucDate4[i])
        	};
        	require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
	require(
	    [
	        'echarts',
	        'echarts/chart/line' // 使用折线图就加载line模块，按需加载
	    ],
	    function (ec) {
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = ec.init(document.getElementById('fol-right')); 
	        
	        var option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['入炉热值','入厂热值']
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    grid: {
			        left: '1',
			        right: '1%',
			        bottom: '1%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : kucDate0
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'入炉热值',
			            type:'line',
			            areaStyle: {normal: {}},
			            data:kucDate2
			        },
			        {
			            name:'入厂热值',
			            type:'line',
			            areaStyle: {normal: {}},
			            data:kucDate4
			        }
			    ]
			};
	
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
		    }
	);
}
})

}
//添加表格二的合计
//$('#tableL02').bootstrapTable('prepend',yjhTotal());	

//来耗存数据表开始
function lhcsj(){
	var jzrq="";
	var from = $("#lhcrq").val()
	if(from==""){
		jzrq=getYesterday().format("yyyy-MM-dd");
      	$("#lhcrq").val(jzrq);
  }else{
  	jzrq=from;
  }
	var pourl ="zonghzs/ghcqk/getLaihcrbGridData?riq="+jzrq;
	$.ajax({
	    type : "get",
	    url : pourl,
	    contentType : "application/json;charset=utf-8",
	    dataType : "json",
	    success: function(data) {
	    	$('#tableL01').bootstrapTable('load',data.datalist);
	    }
	});
}
lhcsj();

//来耗存库存表结束
//来煤信息表
function lmxxsj(){
	var jzrq="";
	var from = $("#lmxx").val()
	if(from==""){
		jzrq=getYesterday().format("yyyy-MM-dd");  
      	$("#lmxx").val(jzrq);
  	}else{
    	jzrq=from;
    }
	var pourl ="zonghzs/ghcqk/getRucbddb?riq="+jzrq;
	$.ajax({
	    type : "get",
	    url : pourl,
	    contentType : "application/json;charset=utf-8",
	    dataType : "json",
	    success: function(data) {
	    //	$tableL01.bootstrapTable({'load',data});
	    	$('#tableL02').bootstrapTable('load',data.datalist);
	    }
	});
}
lmxxsj();

//入厂入炉主要指标
function rcrlzb(){
	var qsrq="";
	var jzrq="";
	var from = $("#rcrlqs").val();
  	var to = $("#rcrljz").val();
  	if(from==""){
  	  if(to==""){
  		  qsrq = getCurrentMonthFirst().format("yyyy-MM-dd");
  		  $("#rcrlqs").val(qsrq);
  	  }else{
  		  alert("请选择开始日期")
  		  return
  	  }	  
    }else{
    	qsrq=from;
    }
    if(to==""){
  	  if(from==""){
  		  jzrq = getYesterday().format("yyyy-MM-dd");
  		  $("#rcrljz").val(jzrq);
  	  }else{
  		  alert("请选择结束日期")
  		  return
  	  }	  
    }else{
    	jzrq=to;
    }
	var pourl = "zonghzs/ghcqk/gridLoad?qsriq="+qsrq+"&jzriq="+jzrq;
	$.ajax({
	    type : "get",
	    url : pourl,
	    contentType : "application/json;charset=utf-8",
	    dataType : "json",
	    success: function(data) {
	    //	$tableL01.bootstrapTable({'load',data});
	    	$('#tableL03').bootstrapTable('load',data.datalist);
	    }
	});
	showRucrulLine(qsrq,jzrq);
}
rcrlzb();

//$ready結束
});
		
		
//库存情况
function showKucunqkLine(){
   
    //----------------------------------------------------------------------------------------------------------------------- */
    var qsrq="";
    var jzrq="";
    var from = $("#kcqxqs").val();
    var to = $("#kcqxjz").val();
    if(from==""){
    	  if(to==""){
    		  qsrq = getCurrentMonthFirst().format("yyyy-MM-dd");
    		  $("#kcqxqs").val(qsrq);
    	  }else{
    		  alert("请选择开始日期")
    		  return
    	  }	  
      }else{
      	qsrq=from;
      }
      if(to==""){
    	  if(from==""){
    		  jzrq = getYesterday().format("yyyy-MM-dd");
    		  $("#kcqxjz").val(jzrq);
    	  }else{
    		  alert("请选择结束日期")
    		  return
    	  }	  
      }else{
      	jzrq=to;
      }
    var kucDate0=new Array();
    var kucDate1=[];
    var kucDate2=new Array();
    var kucDate3=[];
    var pourl ="zonghzs/ghcqk/getKucqkqxData?qsriq="+qsrq+"&jzriq="+jzrq;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data){
        	if(data.length==0){
        		kucDate0=[];
        		kucDate1=[];
            	kucDate2=[];
            	kucDate3=[];
        	}else{        		
            	kucDate0=data[0];
            	kucDate1=data[1];
            	kucDate2=data[2];
            	kucDate3=data[3];
            	for(var i=0;i<kucDate2.length;i++){
            		kucDate1[i] =parseFloat(kucDate1[i]);
            		kucDate2[i] =parseFloat(kucDate2[i]);
            		kucDate3[i] =parseFloat(kucDate3[i]);
            	}
        	};
        	require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
        	
        	require(
        		    [
        		        'echarts',
        		        'echarts/chart/line' // 使用折线图就加载line模块，按需加载
        		    ],
        		    function (ec) {
        		        // 基于准备好的dom，初始化echarts图表
        		        var myChart = ec.init(document.getElementById('kucunqkline'));


        	            var option = {
        				    tooltip : {
        				        trigger: 'axis'
        				    },
        				    legend: {
        				        data:['库存情况','正常储备','警戒库存']
        				    },
        				    toolbox: {
        				        feature: {
        				            saveAsImage: {}
        				        }
        				    },
        				    grid: {
        				        left: '1',
        				        right: '1%',
        				        bottom: '1%',
        				        containLabel: true
        				    },
        				    xAxis : [
        				        {
        				            type : 'category',
        				            boundaryGap : false,
        				            data : kucDate0 
        				        }
        				    ],
        				    yAxis : [
        				        {
        				            type : 'value'
        				        }
        				    ],
        				    series : [
        				        {
        				            name:'警戒库存',
        				            type:'line',
        				            smooth:true,
        	            			itemStyle: {normal: {areaStyle: {type: 'default'}}},
        				            data:kucDate1
        				        },
        				        {
        				            name:'库存情况',
        				            type:'line',
        				            smooth:true,
        	            			itemStyle: {normal: {areaStyle: {type: 'default'}}},
        				            data:kucDate2
        				        },
        				        {
        				            name:'正常储备',
        				            type:'line',
        				            smooth:true,
        	            			itemStyle: {normal: {areaStyle: {type: 'default'}}},
        				            label: {
        				                normal: {
        				                    show: true,
        				                    position: 'top'
        				                }
        				            },
        				            areaStyle: {normal: {}},
        				            data:kucDate3
        				        }
        				    ]
        				};
        		        // 为echarts对象加载数据 
        		        myChart.setOption(option); 
        		    }
        		);
        }
    })
	
}
showKucunqkLine();
//来耗煤情况
function showLaihaomeiqkLine(){
	var qsrq="";
	var jzrq="";
	var from = $("#lhmqxqs").val();
    var to = $("#lhmqxjz").val();
  if(from==""){
	  if(to==""){
		  qsrq = getCurrentMonthFirst().format("yyyy-MM-dd");
		  $("#lhmqxqs").val(qsrq);
	  }else{
		  alert("请选择开始日期")
		  return
	  }	  
  }else{
  	qsrq=from;
  }
  if(to==""){
	  if(from==""){
		  jzrq = getYesterday().format("yyyy-MM-dd");
		  $("#lhmqxjz").val(jzrq);
	  }else{
		  alert("请选择结束日期")
		  return
	  }	  
  }else{
  	jzrq=to;
  }
    var kucDate0=new Array();
    var kucDate1=[];
    var kucDate2=new Array();
    var pourl ="zonghzs/ghcqk/getLaihmqkqxData?qsriq="+qsrq+"&jzriq="+jzrq;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data) {
        	if(data.length==0){
        		kucDate0=[];
            	kucDate1=[];
            	kucDate2=[];
        	}else{
        		kucDate0=data[0];
            	kucDate1=data[1];
            	kucDate2=data[2];
            	for(var i=0;i<kucDate2.length;i++){
            		kucDate1[i] =parseFloat(kucDate1[i]);
            		kucDate2[i] =parseFloat(kucDate2[i]);
            	}
            };
            require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
        	
	require(
	    [
	        'echarts',
	        'echarts/chart/line' // 使用折线图就加载line模块，按需加载
	    ],
	    function (ec) {
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = ec.init(document.getElementById('laihaomeiqkline')); 
	        
	        var option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['耗煤','来煤']
			    },
			    toolbox: {
			    	show: true,
			        feature: {
		            mark : {show: true},
		/*		            dataView : {show: true, readOnly: false},
			           
			            restore : {show: true},*/
			            /*saveAsImage : {show: true}*/
			        }
			    },
			/*    calculable : true,*/
			    grid: {
			        left: '1',
			        right: '1%',
			        bottom: '1%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : kucDate0
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'耗煤',
			            type:'line',
			            
			            areaStyle: {normal: {}},
			            data:kucDate2
			        },
			        {
			            name:'来煤',
			            type:'line',
			           
			            areaStyle: {normal: {}},
			            data:kucDate1
			        }
			    ]
			};
	
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
		    }
	);
}
})

}
showLaihaomeiqkLine();

//分矿入厂标煤单价
function showFenkrcbmdjLine(){
	var qsrq="";
	var jzrq="";
	var from = $("#fkrcdjqs").val();
    var to = $("#fkrcdjjz").val();	
    if(from==""){
  	  if(to==""){
  		  qsrq = getCurrentMonthFirst().format("yyyy-MM-dd");
  		  $("#fkrcdjqs").val(qsrq);
  	  }else{
  		  alert("请选择开始日期")
  		  return
  	  }	  
    }else{
    	qsrq=from;
    }
    if(to==""){
  	  if(from==""){
  		  jzrq = getYesterday().format("yyyy-MM-dd");
  		  $("#fkrcdjjz").val(jzrq);
  	  }else{
  		  alert("请选择结束日期")
  		  return
  	  }	  
    }else{
    	jzrq=to;
    }
    var kucDate0=new Array();
    var kucDate1=[];
    var kucDate2=new Array();
    var pourl = "zonghzs/ghcqk/getDaysofRucbmdj?riq="+qsrq+"&jzriq="+jzrq;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data) {
        	if(data.length==0){
        		kucDate0=[];
            	kucDate1=[];
            	kucDate2=[];
        	}else{
        		for(var k=0;k<5;k++){
            		kucDate0.push(data[0][k]);
            		kucDate1.push(data[1][k]);
            		kucDate2.push(data[2][k])
            	}
            	for(var i=0;i<kucDate2.length;i++){
            		kucDate1[i] =parseFloat(kucDate1[i]);
            		kucDate2[i] =parseFloat(kucDate2[i])
            	}
            };
            require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
	require(
	    [
	        'echarts',
	    //    'echarts/chart/line', // 使用折线图就加载line模块，按需加载
	        'echarts/chart/bar'
	    ],
	    function (ec) {
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = ec.init(document.getElementById('fenkrcbmdjline')); 
	        
	        var option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    calculable : true,
			    legend: {
			        data:['原煤单价','标煤单价','来煤数量-累计']
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : kucDate0
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '元/吨',
			            axisLabel : {
			                formatter: '{value}'
			            }
			        },
			        {
			            type : 'value',
			            name : '吨',
			            axisLabel : {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series : [
			
			        {
			            name:'原煤单价',
			            type:'bar',
			            data:kucDate1
			        },
			        {
			            name:'标煤单价',
			            type:'bar',
			            data:kucDate2
			        },
			 /*       {
			            name:'来煤数量-累计',
			            type:'line',
			            yAxisIndex: 1,
			            data:[50000, 52000, 23000, 24000, 24500, 24000, 8000]
			        } */
			    ]
			};
	
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
	    }
	);
}
})

}
showFenkrcbmdjLine();

//入厂天然煤价
function showRuctrmjLine(){
	var qsrq="";
	var jzrq="";
	var from = $("#rctrqs").val();
    var to = $("#rctrjz").val();
    if(from==""){
    	  if(to==""){
    		  qsrq = getCurrentMonthFirst().format("yyyy-MM-dd");
    		  $("#rctrqs").val(qsrq);
    	  }else{
    		  alert("请选择开始日期")
    		  return
    	  }	  
      }else{
      	qsrq=from;
      }
      if(to==""){
    	  if(from==""){
    		  jzrq = getYesterday().format("yyyy-MM-dd");
    		  $("#rctrjz").val(jzrq);
    	  }else{
    		  alert("请选择结束日期")
    		  return
    	  }	  
      }else{
      	jzrq=to;
      }
    var kucDate0=new Array();
    var kucDate1=[];
    var kucDate2=new Array();
    var pourl ="zonghzs/ghcqk/getRuctrmj?riq="+qsrq+"&jzriq="+jzrq;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data) {
        	if(data.length==0){
        		kucDate0=[];
            	kucDate1=[];
            	kucDate2=[];
        	}else{
        		kucDate0=data[0];
            	kucDate1=data[1];
            	kucDate2=data[2];
            	for(var i=0;i<kucDate2.length;i++){
            		kucDate1[i] =parseFloat(kucDate1[i])
            		kucDate2[i] =parseFloat(kucDate2[i])
            	}
            };
            require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
	require(
	    [
	        'echarts',
	        'echarts/chart/line' // 使用折线图就加载line模块，按需加载
	    ],
	    function (ec) {
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = ec.init(document.getElementById('ructrmjline')); 
	        
	        var option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['当日','累计']
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    grid: {
			        left: '1',
			        right: '1%',
			        bottom: '1%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : kucDate0
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'当日',
			            type:'line',
			           
			            areaStyle: {normal: {}},
			            data:kucDate1
			        },
			        {
			            name:'累计',
			            type:'line',
			           
			            areaStyle: {normal: {}},
			            data:kucDate2
			        }
			    ]
			};
	
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
	    }
	);
}
})
}

//入厂标煤单价
function showRucbmdjLine(){
	var qsrq="";
	var jzrq="";
	var from = $("#rcbmqs").val();
    var to = $("#rcbmjz").val();
    if(from==""){
  	  if(to==""){
  		  qsrq = getCurrentMonthFirst().format("yyyy-MM-dd");
  		  $("#rcbmqs").val(qsrq);
  	  }else{
  		  alert("请选择开始日期")
  		  return
  	  }	  
    }else{
    	qsrq=from;
    }
    if(to==""){
  	  if(from==""){
  		  jzrq = getYesterday().format("yyyy-MM-dd");
  		  $("#rcbmjz").val(jzrq);
  	  }else{
  		  alert("请选择结束日期")
  		  return
  	  }	  
    }else{
    	jzrq=to;
    }
    var kucDate0=new Array();
    var kucDate1=[];
    var kucDate2=new Array();
    var pourl ="zonghzs/ghcqk/getRucbmdj?riq="+qsrq+"&jzriq="+jzrq;
    $.ajax({
        type : "get",
        url : pourl,
        contentType : "application/json;charset=utf-8",
        //data : {qsriq:'2015-01-01',jzriq:'2015-01-11'},
        dataType : "json",
        success: function(data) {
        	if(data.length==0){
        		kucDate0=[];
            	kucDate1=[];
            	kucDate2=[];
        	}else{
        		kucDate0=data[0];
            	kucDate1=data[1];
            	kucDate2=data[2];
            	for(var i=0;i<kucDate2.length;i++){
            		kucDate1[i] =parseFloat(kucDate1[i])
            		kucDate2[i] =parseFloat(kucDate2[i])
            	}
            };
            require.config({
                paths:{ 
                    'echarts' : 'js/echarts'
                }
            });
	require(
	    [
	        'echarts',
	        'echarts/chart/line' // 使用折线图就加载line模块，按需加载
	    ],
	    function (ec) {
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = ec.init(document.getElementById('rucbmdjline')); 
	        
	        var option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['当日','累计']
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    grid: {
			        left: '1',
			        right: '1%',
			        bottom: '1%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : kucDate0
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'当日',
			            type:'line',
			            areaStyle: {normal: {}},
			            data:kucDate1
			        },
			        {
			            name:'累计',
			            type:'line',
			            areaStyle: {normal: {}},
			            data:kucDate2
			        }
			    ]
			};
	
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
	    }
	);
}
})

}

//第二层左侧tab切换
function showSecLeft(num,org){
	//alert($(org).attr('class'));
	$(org).addClass("sliactive").siblings().removeClass("sliactive");
	if(num==1){
		$("#laihaomeidiv").hide();
		$("#kucundiv").show();
		showKucunqkLine();
	}else{
		$("#kucundiv").hide();
		$("#laihaomeidiv").show();
		showLaihaomeiqkLine();
	}
}

//第三层右侧tab切换
function showTlright(num,org){
	//alert($(org).attr('class'));
	$(org).addClass("sliactive").siblings().removeClass("sliactive");
	if(num==1){
		$("#rucbmdjdiv").hide();
		$("#ructrmjdiv").hide();
		$("#fenkrcbmdjdiv").show();
		showFenkrcbmdjLine();
	}else if(num==2){
		$("#rucbmdjdiv").hide();
		$("#fenkrcbmdjdiv").hide();
		$("#ructrmjdiv").show();
		showRuctrmjLine();
	}else if(num==3){
		$("#fenkrcbmdjdiv").hide();
		$("#ructrmjdiv").hide();
		$("#rucbmdjdiv").show();
		showRucbmdjLine();
	}
}
