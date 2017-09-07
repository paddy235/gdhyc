package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.utils.Calculator;

public class HetjgCalculator {
	
	private List<Range> rangeList;
	
//	private double price;
	
	//热值大于3800价格计算
	public void calculat1(Map<String,JSONObject> dataMaps){		
		double starSum = 0;
		double qnetarSum = 0;
		double shul = 0;
		double avgStar = 0;
		double avgQnetar = 0;
		for (Map.Entry<String, JSONObject> entry : dataMaps.entrySet()) {
			JSONObject json = entry.getValue();
			starSum = Calculator.add(starSum, json.getDouble("star"));
			qnetarSum = Calculator.add(qnetarSum, json.getDouble("qnetar"));
			shul = Calculator.add(shul, json.getDouble("shul"));
		}
		//修正
		shul = getCorrection(shul);
		avgStar = Calculator.div(starSum, shul);
		avgQnetar = Calculator.div(qnetarSum, shul);
		
		Map<String,Double> map = new HashMap<String, Double>();
		map.put("shul", shul);
		map.put("star", avgStar);
		map.put("qnetar", avgQnetar);		
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
	 * 硫分计算方法
	 * 
	 * 
	 */
	public JSONObject Calculation(Map<String,Double> map){
		rangeList = new ArrayList<Range>();
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
				
		JSONObject json = new JSONObject();
//		double star = map.get("star");
		
//		double jiag = this.starCal(rangeList, star);
		return json;		
	}
	
	
	/**
	 * 发热量计算方法
	 * 
	 * 
	 */
	public JSONObject Qnetculation(Map<String,Double> map){
		rangeList = new ArrayList<Range>();
		
		Range range3 = new Range();
		range3.setMin(4000);
		range3.setMax(4200);
		range3.setJiag(0.0274);
		range3.setBase_price(115);
		rangeList.add(range3);

		Range range2 = new Range();
		range2.setMin(3800);
		range2.setMax(4000);
		range2.setJiag(0.0548);
		range2.setBase_price(115);
		rangeList.add(range2);	
		
		Range range1 = new Range();
		range1.setMin(3600);
		range1.setMax(3800);
		range1.setJiag(0.1096);
		range1.setBase_price(115);
		rangeList.add(range1);
		
		Range range = new Range();
		range.setMin(3500);
		range.setMax(3600);
		range.setJiag(0.0171);
		range.setBase_price(60);
		rangeList.add(range);
		
		JSONObject json = new JSONObject();
	//	double qnetar = map.get("qnetar");
		
//		double jiag = this.qnetarCal(rangeList, qnetar);
		return json;		
	}
	
	/**
	 * 发热量计算
	 * @param rangeList
	 * @param qnetar
	 * @return
	 */
	public double qnetarCal(List<Range> rangeList,Double qnetar){
		double result = 0;
		if(qnetar>4200){
			result =  Calculator.mul((qnetar - 4200),0.0274);
		}else{
			for (Range range : rangeList) {
				double max = range.getMax();
				double jiag = range.getJiag();
				double min = range.getMin();
//				double base_price = range.getBase_price();
				if(qnetar - min < 0){
					result += Calculator.mul(Calculator.sub(max, min),jiag);							
				}else{
					result += Calculator.mul(Calculator.sub(max,qnetar), jiag);				
					break;					
				}
			}			
		}
		return result;
	}
	
	public double starCal(List<Range> rangeList,Double star){
		double result = 0;
		for (Range range : rangeList) {
			double max = range.getMax();
			double jiag = range.getJiag();
			double min = range.getMin();
			if(star - max > 0){
///				double a = Calculator.mul(Calculator.div(Calculator.sub(max, min),0.01), jiag);				
				result += Calculator.mul(Calculator.div(Calculator.sub(max, min),0.01), jiag);
			}else{
				result += Calculator.mul(Calculator.div(Math.abs(Calculator.sub(star,min)),0.01), jiag);				
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("star", 0.72);
		map.put("qnetar", (double) 3850);
		HetjgCalculator h = new HetjgCalculator();
		//h.Calculation(map);
		h.Qnetculation(map);
	}
	
	
	
}
