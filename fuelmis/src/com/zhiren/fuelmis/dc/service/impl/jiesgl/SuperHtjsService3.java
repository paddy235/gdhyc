package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.utils.Calculator;

/**
 * 050号合同结算
 * @author ZY
 *
 */
public class SuperHtjsService3 {
	/**
	 * 计算方法
	 * @param rangeList
	 * @param qnetar
	 * @return
	 */
	public JSONObject Qnetculation(JSONObject json){
		double qnetar = json.getDouble("qnetar");
		double star = json.getDouble("star");
				
		//发热量扣价信息
		Map<String,Object> qnetarMap =  getQnetar(qnetar);
		
		//硫分扣价信息
		Map<String,Object> starMap = getStar(star);
	
		JSONObject result= new JSONObject();
		
		result.put("qnetarPirce", qnetarMap.get("qnetarPirce"));
		result.put("qnetar", qnetarMap.get("shuom"));
		result.put("starPirce", starMap.get("starPirce"));
		result.put("starShuom", starMap.get("shuom"));
		
		return result;
	}
	
	
	/**
	 * 硫分总扣价
	 * @param list
	 * @param star
	 * @return
	 */
	public Map<String,Object> getStar(double star){
		Map<String,Object> map = new HashMap<String,Object>();	
		StringBuffer stb = new StringBuffer("加权硫分为:"+star);
		double starPirce =0;
		
		if(1.2<star && star<=1.7){	
			starPirce = Calculator.mul(Calculator.div(Calculator.sub(star, 1.2),0.01), 0.14); 
			stb.append(",在范围1.2-1.7之间扣价").append(starPirce+"元");
		}if(star>1.7){
			double price = Calculator.mul(Calculator.div(Calculator.sub(star, 1.7),0.01), 0.28);
			starPirce = Calculator.mul(Calculator.div(Calculator.sub(star, 1.2),0.01), 0.14); 
			starPirce = Calculator.add(starPirce,price);
			stb.append(",在范围1.2-1.7之间扣价").append(price+"元,在大于1.7范围内扣款").append(price).append("元");
		}
		
		map.put("starPirce", starPirce);
		map.put("shuom", stb.toString());
		return map;
	}
	
	/**
	 * 获取热值扣价
	 * @param qnetar
	 * @return
	 */
	public Map<String,Object> getQnetar(double qnetar){
		Map<String,Object> map = new HashMap<String, Object>();
		double qnetarPirce = 0;
		StringBuffer stb = new StringBuffer("加权发热量为"+qnetar+"kcal/kg");		
		//热值在3600kcal/kg以下，按工程煤60.00元/吨结算，以3500kcal/kg为基准，每降低1kcal/kg，价格降低0.0171元/吨，每增加1kcal/kg，价格增加0.0171元/吨。
		if(qnetar>3500){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 3500),0.0171);
			stb.append("热值在3500kcal/kg以上增加").append(qnetarPirce).append("元");
		}else{
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 3500),0.0171);
			stb.append("热值在3500kcal/kg以下降低").append(qnetarPirce).append("元");			
		}
		map.put("qnetarPirce", qnetarPirce);
		map.put("shuom", stb.toString());
		return map;
	}
}
