package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.utils.Calculator;

public class DataCollation {

	
	public void doCollation(){
		//数据传输接口
		List<Map<String,Object>> baseDataList = new ArrayList<Map<String,Object>>();
		
		//模拟数据一
		Map<String,Object> baseDataMap = new HashMap<String,Object>();
		//入库单号
		baseDataMap.put("huaydid", "danh1");
		//入库单行号
		baseDataMap.put("rukdhh", 1);
		//车皮表id
		baseDataMap.put("chepbid", 45);
		//化验单id
		baseDataMap.put("huaydid", 4);
		//车号
		baseDataMap.put("cheh", "gd433");
		//数量
		baseDataMap.put("shul", 46.5);
		//热值
		baseDataMap.put("qnetar", 4100);
		//硫分
		baseDataMap.put("star", 0.9);
		baseDataMap.put("key", "2015-09-23");
		
		
		//模拟数据二
		Map<String,Object> baseDataMap1 = new HashMap<String,Object>();
		//入库单号
		baseDataMap1.put("huaydid", "danh2");
		//入库单行号
		baseDataMap1.put("rukdhh", 12);
		//车皮表id
		baseDataMap1.put("chepbid", 46);
		//化验单id
		baseDataMap1.put("huaydid", 4);
		//车号
		baseDataMap1.put("cheh", "gd555");
		//数量
		baseDataMap1.put("shul", 44.3);
		//热值
		baseDataMap1.put("qnetar", 4100);
		//硫分
		baseDataMap1.put("star", 0.9);
		baseDataMap1.put("key", "2015-09-23");
		

		//模拟数据二
		Map<String,Object> baseDataMap4 = new HashMap<String,Object>();
		//入库单号
		baseDataMap4.put("huaydid", "danh1");
		//入库单行号
		baseDataMap4.put("rukdhh", 1);
		//车皮表id
		baseDataMap4.put("chepbid", 45);
		//化验单id
		baseDataMap4.put("huaydid", 4);
		//车号
		baseDataMap4.put("cheh", "gd433");
		//数量
		baseDataMap4.put("shul", 50);
		//热值
		baseDataMap4.put("qnetar", 4300);
		//硫分
		baseDataMap4.put("star", 0.28);
		baseDataMap4.put("key", "2015-09-23");		
		
		
		//模拟数据三
		Map<String,Object> baseDataMap2 = new HashMap<String,Object>();
		//入库单号
		baseDataMap2.put("huaydid", "danh3");
		//入库单行号
		baseDataMap2.put("rukdhh", 12);
		//车皮表id
		baseDataMap2.put("chepbid", 46);
		//化验单id
		baseDataMap2.put("huaydid", 5);
		//车号
		baseDataMap2.put("cheh", "gd555");
		//数量
		baseDataMap2.put("shul", 44.3);
		//热值
		baseDataMap2.put("qnetar", 4500);
		//硫分
		baseDataMap2.put("star", 1.63);
		baseDataMap2.put("key", "2015-09-24");

		
		baseDataList.add(baseDataMap1);
		baseDataList.add(baseDataMap2);
		baseDataList.add(baseDataMap);
		baseDataList.add(baseDataMap4);
		
		//根据日期获取数据
		Map<String,JSONObject> data = doOrder(baseDataList);
		
		List<JSONObject> dataList = orderData(data);
		
		SuperHtjsService su = new SuperHtjsService();
		
		for (JSONObject json : dataList) {
			
			@SuppressWarnings("unused")
			JSONObject jj = su.Qnetculation(json);
		}
		
	}
	
	/**
	 * 计算加权平均值
	 * 修约数量
	 * @param map
	 * @return
	 */
	public List<JSONObject> orderData(Map<String,JSONObject> map){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<JSONObject> result = new ArrayList();
		for (Map.Entry<String, JSONObject> entry : map.entrySet()) {
			JSONObject json = entry.getValue();
			String key = entry.getKey();
			double sumStar = json.getDouble("star");
			double sumQnetar = json.getDouble("qnetar");
			double shul = json.getDouble("shul");
			//加权平均硫分
			double avgStar = Calculator.div(sumStar, shul);
			//加权平均发热量
			double avgQnetar = Calculator.div(sumQnetar, shul);
			//修正数量
			int vShul = getCorrection(shul); 						
			JSONObject reJson = new JSONObject();
			reJson.put("key", key);
			reJson.put("shul", vShul);
			reJson.put("star", avgStar);
			reJson.put("qnetar",avgQnetar);			
			result.add(reJson);			
		}
		return result;
	}
	
	
	/**
	 * 修正算法
	 * @param count
	 * @return
	 */
	
	public int getCorrection(double count){
		NumberFormat numberFormat = new DecimalFormat("#0");
		return Integer.parseInt(numberFormat.format(count));
	}
	
	
	/**
	 * 整理数据
	 * 根据日期加权发热量、硫分并且获得数量和
	 * @param list
	 * @return
	 */
	public Map<String,JSONObject> doOrder(List<Map<String,Object>> list){
		Map<String,JSONObject> dataMaps = new HashMap<String, JSONObject>();
		for (Map<String,Object> map : list) {
			String key = map.get("key").toString();
			double shul = Double.parseDouble(map.get("shul").toString());
			double star = Double.parseDouble(map.get("star").toString());
			double qnetar = Double.parseDouble(map.get("qnetar").toString());			
			if(!dataMaps.containsKey(key)){
				JSONObject json = new JSONObject();
				json.put("shul", shul);
				//加权硫分
				json.put("star", Calculator.mul(shul, star));
				//加权热值
				json.put("qnetar", Calculator.mul(shul, qnetar));
				dataMaps.put(key, json);
				
			}else{				
				JSONObject json = dataMaps.get(key);
				//加权数量
				json.put("shul", Calculator.add(shul, json.getDouble("shul")));
				//加权硫分
				json.put("star", Calculator.add(json.getDouble("star"),Calculator.mul(shul, star)));
				//加权热值
				json.put("qnetar", Calculator.add(json.getDouble("qnetar"), Calculator.mul(shul, qnetar)));
				dataMaps.put(key, json);
			}
		}
		return dataMaps;
	}
	
	public static void main(String[] args) {
		DataCollation dd = new  DataCollation();
		dd.doCollation();
	}
	
	
}
