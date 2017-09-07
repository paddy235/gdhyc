package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import org.springframework.stereotype.Component;

/*
 * 计价公式：(验收指标-合同基准)/增扣基数*实际增扣价+基准增扣价
 */
@Component("HycLHSCalculatorService")
public class HycLHSCalculatorService implements Icalculator{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean create(String po_sub_id) {
		return false;
	}

	@SuppressWarnings("rawtypes")
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		PriceBean priceBean = new PriceBean();
		priceBean.setType(mapParas.get("HETKHZB").toString());//类型
		priceBean.setZhib(mapParas.get("HETKHZB").toString());//类型
		
		//结算标准（指标）
		double zhibvalue = Double.parseDouble(mapParas.get(mapParas.get("HETKHZB").toString()).toString());
		double shijjg = 0;
		//考核指标
		List<Map<String, Object>> rangeList =new ArrayList<Map<String, Object>>();
		rangeList = jdbcTemplate
				.queryForList("select nvl(JIZJG,0) JIZJG, nvl(JIZRZ ,0) JIZRZ,nvl(JIANGKJS ,0) JIANGKJS,nvl(JIZKJ ,0) JIZKJ,\n"
						+ " nvl(SHIJZKJ ,0) SHIJZKJ,nvl(SHANGX ,0) SHANGX,nvl( XIAX ,0) XIAX "
						+ " from RL_HT_KAOHZBB where  scheme_id =" + po_price_scheme_id
						+ " and "
						+ zhibvalue + " >= nvl(xiax,0)   and " + zhibvalue + " < nvl(shangx,999999) ");
		double jizjg = 0;//基准价格
		double hetjz =0;//基准热值
		double jiangkjs = 0;//增扣基数
		double jizkj = 0;//基准增扣价
		double shijzkj = 0;//实际增扣价
		double shangx = 0;//上限
		double xiax = 0;
		if (rangeList!=null&&rangeList.size()>0) {
			 jizjg = Double.parseDouble(rangeList.get(0).get("JIZJG").toString());//基准价格
			 hetjz = Double.parseDouble(rangeList.get(0).get("JIZRZ").toString());//基准热值
			 jiangkjs = Double.parseDouble(rangeList.get(0).get("JIANGKJS").toString());//增扣基数
			 jizkj = Double.parseDouble(rangeList.get(0).get("JIZKJ").toString());//基准增扣价
			 shijzkj = Double.parseDouble(rangeList.get(0).get("SHIJZKJ").toString());//实际增扣价
			 shangx = Double.parseDouble(rangeList.get(0).get("SHANGX").toString());//上限
			 xiax = Double.parseDouble(rangeList.get(0).get("XIAX").toString());//下限
			//(验收指标-合同基准)/增扣基数*实际增扣价+基准增扣价
			if(zhibvalue>=hetjz){
				//基准价格+(结算热值-上限)/增扣基数*实际增扣价+基准增扣价
				shijjg=(zhibvalue-xiax)/jiangkjs*shijzkj+jizkj;
                priceBean.setFormula("("+zhibvalue+"-"+xiax+")/"+jiangkjs+"*"+shijzkj+"+"+jizkj);
			}else{
				//基准价格+(结算热值-下限)/增扣基数*实际增扣价+基准增扣价
				shijjg=(zhibvalue-shangx)/jiangkjs*shijzkj+jizkj;
                priceBean.setFormula("("+zhibvalue+"-"+shangx+")/"+jiangkjs+"*"+shijzkj+"+"+jizkj);
			}
		}else{
			List<Map<String, Object>> rangeList1 =new ArrayList<Map<String, Object>>();
			rangeList1 = jdbcTemplate
					.queryForList("select   nvl(max(JIZRZ), 0) JIZRZ from RL_HT_KAOHZBB where  scheme_id =" + po_price_scheme_id);
			 jizjg = Double.parseDouble("0");//基准价格
			 hetjz = Double.parseDouble(rangeList1.get(0).get("JIZRZ").toString());//基准热值 
			 jizjg = 0;
             priceBean.setFormula(" ");
		}
		priceBean.setHetjg(jizjg);
		priceBean.setHetjz(hetjz);//合同标准
		priceBean.setChaz(zhibvalue-hetjz);//相差数量
		priceBean.setPrice(shijjg);//折价标准
		priceBean.setZhibValue(zhibvalue);//结算标准、厂方验收
		return priceBean;
	}

	@Override
	public boolean delete(String po_sub_id, String price_component_id) {
		return false;
	}
}
