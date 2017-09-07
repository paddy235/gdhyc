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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mjy (����ָ��-��ͬ��׼)/���۷���*���ۼ�(Ԫ/�֣�+ ��׼���ۼ�
 */
@Component("ThreeCalculatorService")
public class ThreeCalculatorService implements Icalculator {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger logger = LogManager.getLogger(QnetStepCalculatorService.class);

	@Override
	public boolean create(String po_sub_id) {
		return false;
	}

	/**
	 * po_price_scheme_id �ɹ������Ƽ۷�ʽ��ID
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		PriceBean priceBean = new PriceBean();
		priceBean.setType("STAR");
		List<Map<String, Object>> pricelist =new ArrayList<Map<String, Object>>();
		pricelist = jdbcTemplate.queryForList("select * from RL_HT_PRICE_SCHEME where id = " + po_price_scheme_id + " ");
		double shangxmax = Double.parseDouble(pricelist.get(0).get("SHANGXMAX")==null?"0":pricelist.get(0).get("SHANGXMAX").toString().toString());
		double xiaxmin = Double.parseDouble(pricelist.get(0).get("XIAXMIN")==null?"0":pricelist.get(0).get("XIAXMIN").toString());
//		long zhibvalue = Math.round(Double.parseDouble(mapParas.get("star").toString()));
		double zhibvalue = Double.parseDouble(mapParas.get("STAR").toString());
		//�����ֵ������
//		long maxrezhi=5500;
		if(zhibvalue>shangxmax){
			zhibvalue=shangxmax;
		}else if(zhibvalue<xiaxmin){
			zhibvalue=xiaxmin;
		}
		double shijjg = 0;

		ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

		List<Map<String, Object>> rangeList =new ArrayList<Map<String, Object>>();

		rangeList = jdbcTemplate
				.queryForList("select * from RL_HT_KAOHZBB where scheme_id = " + po_price_scheme_id
						+ " and (xiax is not null and xiax <> 0) and (shangx is not null and shangx <> 0) and "
						+ zhibvalue + " >= decode(xiax ,0,0,xiax ) and " + zhibvalue + " < decode(shangx ,0,7000,shangx )");

		if (rangeList!=null&&rangeList.size()>0) {
			//��׼�۸�	����
			double jizjg = Double.parseDouble(rangeList.get(0).get("JIZJG").toString());
			//��׼��ֵ	��ͬ��׼
			int jizrz = Integer.parseInt(rangeList.get(0).get("JIZRZ").toString());
			//���ۻ���	���۷���
			double jiangkjs = Double.parseDouble(rangeList.get(0).get("JIANGKJS").toString());
			//��׼���ۼ�	��׼���ۼ�
			double jizkj = Double.parseDouble(rangeList.get(0).get("JIZKJ").toString());
			//���ۼ�
			double shijzkj = Double.parseDouble(rangeList.get(0).get("SHIJZKJ").toString());
			//����<		<
			double shangx = Double.parseDouble(rangeList.get(0).get("SHANGX").toString());
			//����>=		>=
			double xiax = Double.parseDouble(rangeList.get(0).get("XIAX").toString());
			//(����ָ��-��ͬ��׼)/���۷���*���ۼ�(Ԫ/�֣�+ ��׼���ۼ�
			double hej=jiangkjs*shijzkj;
			if(hej>0){
				shijjg=(zhibvalue-jizrz)/jiangkjs*shijzkj+jizkj;
			}else{
				shijjg=jizkj;
			}
			BigDecimal bigjine = new BigDecimal(shijjg);
			shijjg = bigjine.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            String formula="("+zhibvalue+"-"+jizrz+")/"+jiangkjs+"*"+shijzkj+"+"+jizkj;
            priceBean.setFormula(formula);
		}

		logger.info("**********��ֵ���ۼ��㹫ʽ��" + shijjg + "**********");

		try {
//			shijjg = Double.parseDouble(jse.eval(jisgs).toString());
			priceBean.setPrice(shijjg);
			priceBean.setPriceChange(shijjg);
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
