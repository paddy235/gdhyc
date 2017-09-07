package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.utils.Calculator;

import net.sf.json.JSONObject;

/**
 * 046合同结算
 * @author ZY
 *
 */
public class SuperHtjsService {
	
	/**
	 * 计算方法
	 * @param rangeList
	 * @param qnetar
	 * @return
	 */
	public JSONObject Qnetculation(JSONObject json){
		double qnetar = json.getDouble("qnetar");
		double star = json.getDouble("star");
		String key = json.getString("key");
		List<Range> rangeList = new ArrayList<Range>();
		Range range = new Range();
		range.setMin(0.5);
		range.setMax(0.6);
		range.setJiag(0.14);
		rangeList.add(range);
		
		Range range1 = new Range();
		range1.setMin(0.6);
		range1.setMax(0.7);
		range1.setJiag(0.28);
		rangeList.add(range1);
		
		Range range2 = new Range();
		range2.setMin(0.7);
		range2.setMax(0.8);
		range2.setJiag(0.56);
		rangeList.add(range2);		
		
		Range range3 = new Range();
		range3.setMin(0.8);
		range3.setMax(0.9);
		range3.setJiag(1.12);
		rangeList.add(range3);

		Range range4 = new Range();
		range4.setMin(0.9);
		range4.setMax(1.0);
		range4.setJiag(2.24);
		rangeList.add(range4);
		
		
		//发热量扣价信息
		Map<String,Object> qnetarMap =  getQnetar(qnetar);
		
		//硫分扣价信息
		Map<String,Object> starMap = getStar(rangeList,star);
	
		JSONObject result= new JSONObject();
		
		
		result.put("key", key);
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
	public Map<String,Object> getStar(List<Range> list ,double star){
		Map<String,Object> map = new HashMap<String,Object>();	
		StringBuffer stb = new StringBuffer("加权硫分为:"+star);
		double starPirce =0;
		for (Range range : list) {
			double max = range.getMax();
			double jiag = range.getJiag();
			double min = range.getMin();
			if(star - max > 0){	
				double price = Calculator.mul(Calculator.div(Calculator.sub(max, min),0.01), jiag); 
				starPirce = Calculator.add(starPirce, price); 
				stb.append("在范围").append(min).append("至").append(max).append("扣价").append(price+"元");
			}else{
				double price = Calculator.mul(Calculator.div(Math.abs(Calculator.sub(star,min)),0.01), jiag);
				starPirce = Calculator.add(starPirce, price); 
				stb.append("在范围").append(min).append("至").append(max).append("扣价").append(price+"元");			
				break;
			}
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
		//热值大于4200kcal/kg，每增加1kcal/kg，价格增加0.0274元/吨
		StringBuffer stb = new StringBuffer("加权发热量为"+qnetar+"kcal/kg");		
		if(qnetar > 4200){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 4200),0.0274);
			stb.append("发热量大于4200价格增加").append(qnetarPirce).append("元");
		}
		//热值在4200－4000kcal/kg，每降低1kcal/kg，价格降低0.0274元/吨；
		if(4000<qnetar && qnetar<4200){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 4200),0.0274);
			stb.append("热值在4200－4000kcal/kg 共扣").append(qnetarPirce).append("元");
		}
		//热值在4000－3800kcal/kg，每降低1kcal/kg，价格降低0.0548元/吨。
		if(3800<qnetar && qnetar<4000){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 4000),0.0548);
			stb.append("热值在4000－3800kcal/kg 共扣").append(qnetarPirce).append("元");
		}
		//热值低于3800kcal/kg，本化验结果代表的当批煤样不参与全月加权，热值在3800－3600kcal/kg，每降低1kcal/kg，价格降低 0.1096元/吨；
		if(3600<qnetar && qnetar<3800){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 3800),0.1096);
			stb.append("热值低于3800kcal/kg 共扣").append(qnetarPirce).append("元");
		}
		//热值在3600kcal/kg以下，按工程煤60.00元/吨结算，以3500kcal/kg为基准，每降低1kcal/kg，价格降低0.0171元/吨，每增加1kcal/kg，价格增加0.0171元/吨。
		if(qnetar<3600){
			qnetarPirce = Calculator.mul(Calculator.sub(qnetar, 3500),0.0171);
			stb.append("热值在3600kcal/kg以下").append(qnetarPirce).append("元");
		}
		map.put("qnetarPirce", qnetarPirce);
		map.put("shuom", stb.toString());
		return map;
	}
	
}
