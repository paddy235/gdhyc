package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;

//合同量考核，在。。。区间内扣几元钱
@Component("HycHetlCalculatorService")
public class HycHetlCalculatorService implements Icalculator{

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
		//结算热值
		double zhibvalue = Double.parseDouble(mapParas.get(mapParas.get("HETKHZB").toString()).toString());
		double shijjg = 0;
		//合同量
		String sql ="select shul\n" +
				"  from rl_ht_caigddb_sub\n" + 
				" where id in\n" + 
				"       (select po_sub_id from rl_ht_price_scheme where id = "+po_price_scheme_id+")";
		long hetl = jdbcTemplate.queryForLong(sql);
		priceBean.setHetjz(hetl);//合同标准
		priceBean.setZhibValue(zhibvalue);
		priceBean.setChaz(zhibvalue-hetl);
		//考核指标
		List<Map<String, Object>> rangeList =new ArrayList<Map<String, Object>>();
		rangeList = jdbcTemplate
				.queryForList("select nvl(JIZKJ,0) JIZKJ from RL_HT_KAOHZBB where scheme_id = " + po_price_scheme_id
						+ " and "
						+ zhibvalue + " >= nvl(xiax,0)   and " + zhibvalue + " < nvl(shangx,999999) ");

		if (rangeList!=null&&rangeList.size()>0) {
			double jizkj = Double.parseDouble(rangeList.get(0).get("JIZKJ").toString());//基准增扣价
			shijjg=jizkj;
			priceBean.setPrice(shijjg);//折价标准
		}
		return priceBean;
	}

	@Override
	public boolean delete(String po_sub_id, String price_component_id) {
		return false;
	}
}
