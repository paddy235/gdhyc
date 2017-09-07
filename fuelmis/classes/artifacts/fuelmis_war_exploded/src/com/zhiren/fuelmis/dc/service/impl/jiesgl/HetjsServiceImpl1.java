package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.utils.Calculator;
import com.zhiren.fuelmis.dc.utils.Constant;

public class HetjsServiceImpl1 {

	//燃煤价格
	private double price;
	//硫分最小值
	private double minstar;
	
	//硫分第一取值范围0.6
	private double baseStar1;
	//硫分第二取值范围0.7
	private double baseStar2;
	//硫分第三取值范围0.8
	private double baseStar3;
	//硫分第四取值范围0.9
	private double baseStar4;
	//硫分第五取值范围1.0
	private double baseStar5;
	//默认第一范围扣价
	private double discount1;
	//默认第二范围扣价
//	private double discount2;
	//默认第三范围扣价
//	private double discount3;
	//默认第四范围扣价
//	private double discount4;
	//默认第五范围扣价
//	private double discount5;
	//默认第六范围扣价
//	private double discount6;
	
	private  Map<String,JSONObject> dataMaps;

	public void getType(List<Map<String,Object>> list){
		for (Map<String,Object> map : list) {
			String key = map.get("key").toString();
			if(!dataMaps.containsKey(key)){
				JSONObject json = new JSONObject();
				dataMaps.put(key, json);
			}
		}
	}
	
	/**
	 * 精确相加获取数量总和
	 * @param list
	 * @return
	 */
	public void getShulSum(List<Map<String,Object>> list){		
		for(Map<String,Object> map : list) {
			double result = 0; 
			String key = map.get("key").toString();
			JSONObject json = dataMaps.get(key);
			if(json.get("shulsum") != null){
				result = json.getDouble("shulsum");
			} 
			double qnetar = Double.parseDouble(map.get("qnetar").toString());
			double shul = Double.parseDouble(map.get("shul").toString());
			if(qnetar >= 3800){
				result = Calculator.add(result, shul);
			}
			json.put("shulsum", result);			
			dataMaps.put(key, json);
		}	
	}
	
	/**
	 * 精确相加获取硫份总和
	 * @param list
	 * @return
	 */
	public void getStarSum(List<Map<String,Object>> list){		
		for(Map<String,Object> map : list) {			
			double result = 0; 
			String key = map.get("key").toString();
			JSONObject json = dataMaps.get(key);
			if(json.get("starsum") != null){
				result = json.getDouble("starsum");
			}
			double star = Double.parseDouble(map.get("star").toString());
			double shul = Double.parseDouble(map.get("shul").toString());
			double qnetar = Double.parseDouble(map.get("qnetar").toString());
			double sumStar = Calculator.mul(star,shul);
			if(qnetar >= 3800){
				result = Calculator.add(result, sumStar);
			}
			json.put("starsum", result);
			dataMaps.put(key, json);
		}
	}
	
	/**
	 * 精确相加发热量总和
	 * @param list
	 * @return
	 */
	public void getQnetarSum(List<Map<String,Object>> list){			
		for(Map<String,Object> map : list) {
			double result = 0; 
			String key = map.get("key").toString();
			JSONObject json = dataMaps.get(key);
			if(json.get("qnetarsum") != null){
				result = json.getDouble("qnetarsum");
			} 
			double qnetar = Double.parseDouble(map.get("qnetar").toString());
			double shul = Double.parseDouble(map.get("shul").toString());
			double sumQnetar = Calculator.mul(qnetar,shul);
			result = Calculator.add(result, sumQnetar);
			json.put("qnetarsum", result);
			dataMaps.put(key, json);
		}
	}	
	
	
	@SuppressWarnings("unused")
	public static JSONArray Calculation(Map<String,Object> list){
//		JSONArray jsonArray = new JSONArray();
		HetjsServiceImpl1 h = new HetjsServiceImpl1(115,0.5,0.6,0.7,0.8,0.9,1.0);
		for (Map.Entry<String, JSONObject> entry : h.dataMaps.entrySet()) {
			JSONObject json = entry.getValue();
			double avgStar = Calculator.div(json.getDouble("starsum"),json.getDouble("shulsum")); 
			//全硫计算类型
		int starType = 0;
			//全硫价格
			double starPrice = 0;
			
			//收到基全硫(St,ar)大于0.5％，在0.5％－0.6％之间，以0.5%为基数，每超出0.01％，价格降低0.14元/吨；
			if(avgStar > h.minstar && avgStar <= h.baseStar1){
				double Ovalue = Calculator.sub(avgStar,Constant.MINSTAR);
				starPrice = Calculator.mul(Calculator.div(Ovalue,0.01),h.discount1);
				starType = 1;
			}
			
			if(avgStar > h.baseStar1 && avgStar <= h.baseStar2){
				//double Ovalue = Calculator.sub(json.getDouble("qnetar"));
			}
						
		}
			
		return null;
	}	
	
	public HetjsServiceImpl1(double price, double minstar, double baseStar1,
			double baseStar2, double baseStar3, double baseStar4,
			double baseStar5) {
		super();
		this.price = price;
		this.minstar = minstar;
		this.baseStar1 = baseStar1;
		this.baseStar2 = baseStar2;
		this.baseStar3 = baseStar3;
		this.baseStar4 = baseStar4;
		this.baseStar5 = baseStar5;
	}

	public HetjsServiceImpl1() {
		super();
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMinstar() {
		return minstar;
	}

	public void setMinstar(Double minstar) {
		this.minstar = minstar;
	}

	public Map<String, JSONObject> getDataMaps() {
		return dataMaps;
	}

	public void setDataMaps(Map<String, JSONObject> dataMaps) {
		this.dataMaps = dataMaps;
	}

	public double getBaseStar1() {
		return baseStar1;
	}

	public void setBaseStar1(double baseStar1) {
		this.baseStar1 = baseStar1;
	}

	public double getBaseStar2() {
		return baseStar2;
	}

	public void setBaseStar2(double baseStar2) {
		this.baseStar2 = baseStar2;
	}

	public double getBaseStar3() {
		return baseStar3;
	}

	public void setBaseStar3(double baseStar3) {
		this.baseStar3 = baseStar3;
	}

	public double getBaseStar4() {
		return baseStar4;
	}

	public void setBaseStar4(double baseStar4) {
		this.baseStar4 = baseStar4;
	}

	public double getBaseStar5() {
		return baseStar5;
	}

	public void setBaseStar5(double baseStar5) {
		this.baseStar5 = baseStar5;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setMinstar(double minstar) {
		this.minstar = minstar;
	}
	
	
	
}
