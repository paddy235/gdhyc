package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.utils.Calculator;
import com.zhiren.fuelmis.dc.utils.Constant;

public class HetjsServiceImpl {

	public static JSONObject Calculation(List<Map<String,Object>> list,double price){		
		//全硫平均值
		double avgStar = Calculator.div(getStarSum(list),getShulSum(list));
		
		//全硫价格
		double starPrice = 0;
		//全硫计算类型
		int starType = 0;
		//如果收到基全硫(St,ar)在1.2％－1.7％之间，以1.2％为基数，每超出0.01％，价格降低0.14元/吨
		if(avgStar >= Constant.MINSTAR && avgStar <= Constant.MAXSTAR){
			double Ovalue = Calculator.sub(avgStar,Constant.MINSTAR);
			starPrice = Calculator.mul(Calculator.div(Ovalue,0.01),Constant.DEF_MIN_PRICE);
			starType = 1;	
		}
		//收到基全硫(St,ar)大于1.7％，以1.7％为基数，每超出0.01％，价格降低0.28元/吨。
		else if(avgStar > Constant.MAXSTAR){
			//获取差值
			double Ovalue = Calculator.sub(avgStar,Constant.DEF_STAR_BASE);
			//数量等于1.7以内的数据*0.14 + 超出1.7部分*0.28
			starPrice = Calculator.mul(Calculator.div(Constant.DEF_STAR_BASE,0.01),Constant.DEF_MIN_PRICE) + Calculator.mul(Calculator.div(Ovalue,0.01),Constant.DEF_MAX_PRICE);
			starType = 2;
		}
		
		//平均发热量
		double avgQnetar = Calculator.div(getQnetarSum(list),getShulSum(list));
		double qnetarPrice;
		//热值大于3500kcal/kg，每增加1kcal/kg，价格增加0.0171元/吨，热值小于3500kcal/kg，每降低1kcal/kg，价格降低0.0171元/吨。
		double Ovalue = Calculator.sub(avgQnetar,Constant.DEF_QUNETAR_BASE);
		qnetarPrice = Calculator.mul(Ovalue,Constant.DEF_QUNETAR_PRICE);
		int qnetarType = 0 ;
		if(qnetarPrice>0){
			qnetarType = 1;
		}else{
			qnetarType = 2;
		}
		//计算煤价
		price = Calculator.add(Calculator.sub(price, starPrice),qnetarPrice);
			
		JSONObject json = new JSONObject();
		//硫分扣减公式
		String liufkjgs = getStarFormula(starType);
		//热值扣减公式
		String rezkjgs = getQunetFormula(qnetarType);
		//煤价
		json.put("meij", price);
		//热值扣减价
		json.put("rezkjj", qnetarPrice);
		//硫分扣减价
		json.put("liufkjj", starPrice);	
		json.put("liufkjgs", liufkjgs);
		json.put("rezkjgs", rezkjgs);		
		return json;
	}
	
	
	
	/**
	 * 精确相加获取数量总和
	 * @param list
	 * @return
	 */
	public static double getShulSum(List<Map<String,Object>> list){
		double result = 0; 
		for(Map<String,Object> map : list) {
			double shul = Double.parseDouble(map.get("shul").toString());
			result = Calculator.add(result, shul);
		}
		return result;		
	}
	
	/**
	 * 精确相加获取硫份总和
	 * @param list
	 * @return
	 */
	public static double getStarSum(List<Map<String,Object>> list){
		double result = 0; 
		for(Map<String,Object> map : list) {
			double star = Double.parseDouble(map.get("star").toString());
			double shul = Double.parseDouble(map.get("shul").toString());
			double sumStar = Calculator.mul(star,shul);
			result = Calculator.add(result, sumStar);
		}
		return result;
	}
	
	/**
	 * 精确相加发热量总和
	 * @param list
	 * @return
	 */
	public static double getQnetarSum(List<Map<String,Object>> list){		
		double result = 0; 		
		for(Map<String,Object> map : list) {
			double qnetar = Double.parseDouble(map.get("qnetar").toString());
			double shul = Double.parseDouble(map.get("shul").toString());
			double sumQnetar = Calculator.mul(qnetar,shul);
			result = Calculator.add(result, sumQnetar);
		}
		
		
		return result;		
	}

	 /**
	  * 获取全硫计算方式
	  * @param type
	  * @return
	  */
	 public static String getStarFormula(int type){
		 String result = null;
		 switch(type) {
		 case 1: 
			 result="收到基全硫(St,ar)在1.2％－1.7％之间，以1.2％为基数，每超出0.01％，价格降低0.14元/吨";	
		 break;
		 case 2: 
			 result="收到基全硫(St,ar)在1.2％－1.7％之间，以1.2％为基数，每超出0.01％，价格降低0.14元/吨";	
		 break;
		}
		return result;
	 }
	 
	 /**
	  * 获取热量计算方式
	  * @param type
	  * @return
	  */
	 public static String getQunetFormula(int type){
		 String result = null;
		 switch(type) {
		 case 1: 
			 result="热值大于3500kcal/kg，每增加1kcal/kg，价格增加0.0171元/吨";	
		 break;
		 case 2: 
			 result="热值小于3500kcal/kg，每降低1kcal/kg，价格降低0.0171元/吨";	
		 break;
		}
		return result;
	 }  
}
