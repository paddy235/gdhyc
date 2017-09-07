package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.utils.Calculator;

/**
 * 094号合同
 * @author ZY
 *
 */
public class SuperHtjsService4 {
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
		//热值大于4300kcal/kg，每增加1kcal/kg，价格增加0.0193元/吨
		StringBuffer stb = new StringBuffer("加权发热量为"+qnetar+"kcal/kg");		
		if(qnetar > 4300){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 4300),0.0193);
			stb.append("发热量大于4300价格增加").append(qnetarPirce).append("元");
		}
		//热值在4300－4100kcal/kg，每降低1kcal/kg，价格降低0.0193元/吨
		if(4100<qnetar && qnetar<=4300){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 4300),0.0193);
			stb.append("热值在4300－4100kcal/kg 共扣").append(qnetarPirce).append("元");
		}
		//热值在4100－3900kcal/kg，每降低1kcal/kg，价格降低0.0386元/吨
		if(3900<qnetar && qnetar<=4100){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 4100),0.0386);
			stb.append("热值在4100－3900kcal/kg 共扣").append(qnetarPirce).append("元");
		}
		//热值在3900－3700kcal/kg，每降低1kcal/kg，价格降低 0.0772元/吨；
		if(3700<qnetar && qnetar<=3900){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 3900),0.0772);
			stb.append("热值低于3800kcal/kg 共扣").append(qnetarPirce).append("元");
		}
		//热值在3600kcal/kg以下，按工程煤60.00元/吨结算，以3500kcal/kg为基准，每降低1kcal/kg，价格降低0.0171元/吨，每增加1kcal/kg，价格增加0.0171元/吨。
		if(qnetar<3700){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 3500),0.016);
			stb.append("热值在3700kcal/kg以下").append(qnetarPirce).append("元");
		}
		map.put("qnetarPirce", qnetarPirce);
		map.put("shuom", stb.toString());
		return map;
	}
}
