package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component("SecondCalculatorService")
public class SecondCalculatorService implements Icalculator {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean create(String po_sub_id) {
		return false;
	}

	/**
	 *
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		PriceBean priceBean = new PriceBean();
		priceBean.setType("QNET_AR");
		List<Map<String, Object>> pricelist = jdbcTemplate
				.queryForList("select * from RL_HT_PRICE_SCHEME where id = " + po_price_scheme_id );
		double shangxmax = Double.parseDouble(pricelist.get(0).get("SHANGXMAX")==null?"0":pricelist.get(0).get("SHANGXMAX").toString().toString());
		double xiaxmin = Double.parseDouble(pricelist.get(0).get("XIAXMIN")==null?"0":pricelist.get(0).get("XIAXMIN").toString());
		double jiesvalue = Double.parseDouble(mapParas.get("QNET_AR").toString());

		if(jiesvalue>shangxmax){
			jiesvalue=shangxmax;
		}else if(jiesvalue<xiaxmin){
			jiesvalue=xiaxmin;
		}
		double shijjg = 0;
		List<Map<String, Object>> rangeList= jdbcTemplate
				.queryForList("select * from RL_HT_KAOHZBB where scheme_id = " + po_price_scheme_id
						+ "    and xiax is not null and shangx is not null  and "
						+ jiesvalue + " >=  decode(xiax ,0,0,xiax ) and " + jiesvalue + " <  decode(shangx ,0,7000,shangx ) ");
		if (rangeList!=null&&rangeList.size()>0) {

			double jizjg = Double.parseDouble(rangeList.get(0).get("JIZJG").toString());

			int jizrz = Integer.parseInt(rangeList.get(0).get("JIZRZ").toString());

			double jiangkjs = Double.parseDouble(rangeList.get(0).get("JIANGKJS").toString());

			double jizkj = Double.parseDouble(rangeList.get(0).get("JIZKJ").toString());

			double shijzkj = Double.parseDouble(rangeList.get(0).get("SHIJZKJ").toString());

			double shangx = Double.parseDouble(rangeList.get(0).get("SHANGX").toString());

			double xiax = Double.parseDouble(rangeList.get(0).get("XIAX").toString());


			double hej=jiangkjs*shijzkj;
			if(hej>0){
			shijjg=jiesvalue*jizjg+(jiesvalue-jizrz)/jiangkjs*shijzkj;
			}else{
				shijjg=jiesvalue*jizjg;
			}
			BigDecimal bigjine = new BigDecimal(shijjg);
			shijjg = bigjine.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            String formular=jiesvalue+"*"+jizjg+"+"+"("+jiesvalue+"-"+jizrz+")/"+jiangkjs+"*"+shijzkj;
            priceBean.setFormula(formular);
		}
		try {
			priceBean.setPrice(shijjg);
			priceBean.setPriceChange(shijjg);
		} catch (Exception t) {
			t.printStackTrace();
		}
		return priceBean;
	}

	@Override
	public boolean delete(String po_sub_id, String price_component_id) {
		return false;
	}
}
