package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;

import org.springframework.stereotype.Component;




//基准价格+(结算热值-下限)/增扣基数*实际增扣价+基准增扣价
@Component("HycQnetCalculatorService")
public class HycQnetCalculatorService implements Icalculator{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean create(String po_sub_id) {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		PriceBean priceBean = new PriceBean();
		priceBean.setType(mapParas.get("HETKHZB").toString());//类型
		priceBean.setZhib(mapParas.get("HETKHZB").toString());//考核指标
		//结算热值
		double jiesvalue = Double.parseDouble(mapParas.get(mapParas.get("HETKHZB").toString()).toString());
		double shijjg = 0;
		//考核指标
		List<Map<String, Object>> rangeList =new ArrayList<Map<String, Object>>();
		rangeList = jdbcTemplate
				.queryForList("select nvl(JIZJG,0) JIZJG, nvl(JIZRZ ,0) JIZRZ,nvl(JIANGKJS ,0) JIANGKJS,nvl(JIZKJ ,0) JIZKJ,\n"
						+ " nvl(SHIJZKJ ,0) SHIJZKJ,nvl(SHANGX ,0) SHANGX,nvl( XIAX ,0) XIAX "
						+ "	 from RL_HT_KAOHZBB where scheme_id = " + po_price_scheme_id
						+ " and " + jiesvalue + " >= nvl(xiax,0)   and " + jiesvalue + " < nvl(shangx,999999) ");

		if (rangeList!=null&&rangeList.size()>0) {
			double jizjg = Double.parseDouble(rangeList.get(0).get("JIZJG").toString());//基准价格
			double jizrz = Double.parseDouble(rangeList.get(0).get("JIZRZ").toString());//基准热值
			double jiangkjs = Double.parseDouble(rangeList.get(0).get("JIANGKJS").toString());//将扣基数
			double jizkj = Double.parseDouble(rangeList.get(0).get("JIZKJ").toString());//基准增扣价
			double shijzkj = Double.parseDouble(rangeList.get(0).get("SHIJZKJ").toString());//实际增扣价
			double shangx = Double.parseDouble(rangeList.get(0).get("SHANGX").toString());//上限
			double xiax = Double.parseDouble(rangeList.get(0).get("XIAX").toString());//下限
			if(jiesvalue>=jizrz){
				//基准价格+(结算热值-上限)/增扣基数*实际增扣价+基准增扣价
				shijjg=jizjg+(jiesvalue-xiax)/jiangkjs*shijzkj+jizkj;
                priceBean.setFormula(jizjg+"+("+jiesvalue+"-"+xiax+")/"+jiangkjs+"*"+shijzkj+"+"+jizkj);
			}else{
				//基准价格+(结算热值-下限)/增扣基数*实际增扣价+基准增扣价
				shijjg=jizjg+(jiesvalue-shangx)/jiangkjs*shijzkj+jizkj;
                priceBean.setFormula(jizjg+"+("+jiesvalue+"-"+shangx+")/"+jiangkjs+"*"+shijzkj+"+"+jizkj);
			}
			priceBean.setHetjz(jizrz);//合同标准
			priceBean.setChaz(jiesvalue-jizrz);//相差数量
			priceBean.setPrice(shijjg);//折价标准
			priceBean.setZhibValue(jiesvalue);//结算标准、厂方验收
			
		}
		return priceBean;
	}

	@Override
	public boolean delete(String po_sub_id, String price_component_id) {
		return false;
	}


}
