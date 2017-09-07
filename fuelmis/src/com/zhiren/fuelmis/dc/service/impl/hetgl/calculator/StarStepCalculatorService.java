package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;

/** 
 * @author 陈宝露
 * 硫分增扣款-阶梯算法
 */
@Component("starStepCalculatorService")
public class StarStepCalculatorService implements Icalculator {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger = LogManager.getLogger(StarStepCalculatorService.class);

	@Override
	public boolean create(String po_sub_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		// TODO Auto-generated method stub
		PriceBean priceBean = new PriceBean();
		double shijlf = Double.parseDouble(mapParas.get("star").toString());
		double shijjg = 0;
		double jizjg = 0;
		double jizlf = 0;
		
		double base_tiaoj = 0;
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from RL_HT_KAOHZBB where caigddb_id = "+po_price_scheme_id+" and leix = '硫分' order by xiax asc");
		LinkedList<LinkedHashMap<String,Double>> lst = new LinkedList<LinkedHashMap<String,Double>>();
		if(list!=null&&list.size()>0){
			jizjg = Double.parseDouble(list.get(0).get("SHIJZKJ").toString());
			jizlf = Double.parseDouble(list.get(0).get("JIZZB").toString());
			base_tiaoj = Double.parseDouble(list.get(0).get("TIAOJ").toString());
			for(int i=0;i<list.size();i++){
				double shangx = Double.parseDouble(list.get(i).get("SHANGX").toString());
				double xiax = Double.parseDouble(list.get(i).get("XIAX").toString());
				double tiaoj = Double.parseDouble(list.get(i).get("TIAOJ").toString());
				LinkedHashMap<String,Double> linkedHashMap = new LinkedHashMap<String, Double>();
				linkedHashMap.put("shangx", shangx==0?100:shangx);
				linkedHashMap.put("xiax", xiax);
				linkedHashMap.put("tiaoj", tiaoj);
				lst.add(linkedHashMap);
			}
		}
		int flag = -1;
		if(lst.size()>0){
			for(int i=0;i<lst.size();i++){
				LinkedHashMap<String,Double> linkedHashMap = lst.get(i);
				double shangx = linkedHashMap.get("shangx");
				double xiax = linkedHashMap.get("xiax");
				if(shijlf<shangx&&shijlf>=xiax){
					flag = i;
				}
			}
		}
		String jisgs = jizjg+"";
		if(flag==0){
			jisgs += "-("+shijlf+"-"+jizlf+")*"+(base_tiaoj*100);
		}else if(flag>0){
			for(int i=0;i<=flag;i++){
				if(i==flag){
					LinkedHashMap<String,Double> linkedHashMap = lst.get(i);
					double xiax = linkedHashMap.get("xiax");
					double tiaoj = linkedHashMap.get("tiaoj");
					jisgs += "-("+shijlf+"-"+xiax+")*"+(tiaoj*100);
				}else if(i!=0){
					LinkedHashMap<String,Double> linkedHashMap = lst.get(i);
					double shangx = linkedHashMap.get("shangx");
					double xiax = linkedHashMap.get("xiax");
					double tiaoj = linkedHashMap.get("tiaoj");
					jisgs += "-("+shangx+"-"+xiax+")*"+(tiaoj*100);
				}else{
					LinkedHashMap<String,Double> linkedHashMap = lst.get(i);
					double shangx = linkedHashMap.get("shangx");
					double tiaoj = linkedHashMap.get("tiaoj");
					jisgs += "-("+shangx+"-"+jizlf+")*"+(tiaoj*100);
				}
			}
		}
		
		logger.info("**********硫分增扣计算公式："+jisgs+"**********");

		ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
		try {
			shijjg = Double.parseDouble(jse.eval(jisgs).toString());
			priceBean.setPrice(shijjg);
			priceBean.setPriceChange(shijjg-jizjg);
			priceBean.setErrMsg(jisgs);
		} catch (Exception t) {
			t.printStackTrace();
		}
		return priceBean;
	}

	@Override
	public boolean delete(String po_sub_id, String price_component_id) {
		// TODO Auto-generated method stub
		return false;
	}
}
