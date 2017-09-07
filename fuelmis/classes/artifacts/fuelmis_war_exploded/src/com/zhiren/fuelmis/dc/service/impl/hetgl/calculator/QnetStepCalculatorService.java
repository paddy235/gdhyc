package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;

/** 
 * @author 陈宝露
 * 热值增扣款-阶梯算法
 */
@Component("qnetStepCalculatorService")
public class QnetStepCalculatorService implements Icalculator {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger logger = LogManager.getLogger(QnetStepCalculatorService.class);

	@Override
	public boolean create(String po_sub_id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * po_price_scheme_id
	 * 			采购订单表ID
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		// TODO Auto-generated method stub
		PriceBean priceBean = new PriceBean();
		long shijrz = Math.round(Double.parseDouble(mapParas.get("qnet_ar").toString())*1000/4.1816);
		double shijjg = 0;
		double jizjg = 0;
		int jizrz = 0;

		ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

		String jisgs = "";

		List<Map<String,Object>> rangeList = jdbcTemplate.queryForList("select * from RL_HT_KAOHZBB where caigddb_id = "+po_price_scheme_id+" and leix = '热值' and (xiax is not null and xiax <> 0) and (shangx is not null and shangx <> 0) and "+shijrz+" >= xiax and "+shijrz+" < shangx");
		if(rangeList.size()==0){
			List<Map<String,Object>> shangxNullList = jdbcTemplate.queryForList("select * from RL_HT_KAOHZBB where caigddb_id = "+po_price_scheme_id+" and leix = '热值' and (shangx is null or shangx = 0) and "+shijrz+" >= xiax");
			if(shangxNullList.size()==0){
				List<Map<String,Object>> xiaxNullList = jdbcTemplate.queryForList("select * from RL_HT_KAOHZBB where caigddb_id = "+po_price_scheme_id+" and leix = '热值' and (xiax is null or xiax = 0) and "+shijrz+" < shangx");
				if(xiaxNullList.size()==0){
					priceBean.setErrMsg("没有找到对应的增扣计价范围，无法计价！");
					return priceBean;
				}else{
					Map<String,Object> maps = jdbcTemplate.queryForMap("select * from RL_HT_KAOHZBB where caigddb_id = "+po_price_scheme_id+" and leix = '热值' and (xiax is null or xiax = 0)");
					jizjg = Double.parseDouble(maps.get("JIZJG").toString());
					jisgs += jizjg;
					jisgs += "-("+maps.get("SHANGX")+"-"+shijrz+")*"+maps.get("TIAOJ");
				}
			}else{
				Map<String,Object> maps = jdbcTemplate.queryForMap("select * from RL_HT_KAOHZBB where caigddb_id = "+po_price_scheme_id+" and leix = '热值' and (shangx is null or shangx = 0)");
				jizjg = Double.parseDouble(maps.get("JIZJG").toString());
				jisgs += jizjg;
				jisgs += "+("+shijrz+"-"+maps.get("XIAX")+")*"+maps.get("TIAOJ");
			}
		}else{
			jizjg = Double.parseDouble(rangeList.get(0).get("JIZJG").toString());
			jizrz = Integer.parseInt(rangeList.get(0).get("JIZRZ").toString());
			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from RL_HT_KAOHZBB where caigddb_id = "+po_price_scheme_id+" and leix = '热值' and (xiax is not null and xiax <> 0) and (shangx is not null and shangx <> 0) and jizjg = "+jizjg+" and jizrz = "+jizrz+" order by id asc");
			LinkedList<LinkedHashMap<String,Object>> lst = new LinkedList<LinkedHashMap<String,Object>>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					int shangx = Integer.parseInt(list.get(i).get("SHANGX").toString());
					int xiax = Integer.parseInt(list.get(i).get("XIAX").toString());
					double tiaoj = Double.parseDouble(list.get(i).get("TIAOJ").toString());
					LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<String, Object>();
					linkedHashMap.put("shangx", shangx);
					linkedHashMap.put("xiax", xiax);
					linkedHashMap.put("tiaoj",tiaoj);
					lst.add(linkedHashMap);
				}
			}
			int flag = -1;
			if(lst.size()>0){
				for(int i=0;i<lst.size();i++){
					LinkedHashMap<String,Object> linkedHashMap = lst.get(i);
					int shangx = Integer.parseInt(linkedHashMap.get("shangx").toString());
					int xiax = Integer.parseInt(linkedHashMap.get("xiax").toString());
					if(shijrz<shangx&&shijrz>=xiax){
						flag = i;
					}
				}
			}

			jisgs += jizjg;

			if(flag==0){
				LinkedHashMap<String,Object> linkedHashMap = lst.get(0);
				int shangx = Integer.parseInt(linkedHashMap.get("shangx").toString());
				double tiaoj = Double.parseDouble(linkedHashMap.get("tiaoj").toString());
				jisgs += "-("+shangx+"-"+shijrz+")*"+tiaoj;
			}else if(flag>0){
				for(int i=0;i<=flag;i++){
					if(i==flag){
						LinkedHashMap<String,Object> linkedHashMap = lst.get(i);
						int shangx = Integer.parseInt(linkedHashMap.get("shangx").toString());
						double tiaoj = Double.parseDouble(linkedHashMap.get("tiaoj").toString());
						jisgs += "-("+shangx+"-"+shijrz+")*"+tiaoj;
					}else if(i!=0){
						LinkedHashMap<String,Object> linkedHashMap = lst.get(i);
						int shangx = Integer.parseInt(linkedHashMap.get("shangx").toString());
						int xiax = Integer.parseInt(linkedHashMap.get("xiax").toString());
						double tiaoj = Double.parseDouble(linkedHashMap.get("tiaoj").toString());
						jisgs += "-("+shangx+"-"+xiax+")*"+tiaoj;
					}else{
						LinkedHashMap<String,Object> linkedHashMap = lst.get(i);
						int shangx = Integer.parseInt(linkedHashMap.get("shangx").toString());
						int xiax = Integer.parseInt(linkedHashMap.get("xiax").toString());
						double tiaoj = Double.parseDouble(linkedHashMap.get("tiaoj").toString());
						jisgs += "-("+shangx+"-"+xiax+")*"+tiaoj;
					}
				}
			}
		}

		logger.info("**********热值增扣计算公式："+jisgs+"**********");
		
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
