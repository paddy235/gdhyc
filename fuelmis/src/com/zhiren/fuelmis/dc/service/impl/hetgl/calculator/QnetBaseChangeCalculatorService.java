package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import com.zhiren.fuelmis.dc.dao.hetgl.QnetBaseChangeCalculatorDao;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceScheme;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import com.zhiren.fuelmis.dc.service.hetgl.PriceSchemeService;
import com.zhiren.fuelmis.dc.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class QnetBaseChangeCalculatorService implements Icalculator {

	@Autowired
	private QnetBaseChangeCalculatorDao qnetBaseChangeCalculatorDao;
	
	@Autowired
	private PriceSchemeService priceSchemeService;
	
	@Override
	public boolean create(String po_sub_id) {
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		String range_min="";//热值下限字符
		String range_max="";//热值上限限字符
		String rang_standard="";//增扣标准字符
		String base_price="";//基准价格字符
		String unitPrice="0";//价格字符

		String quality_item_id="0";
		double dblRange_min=0;//热值下限值
		double dblRange_max=0;//热值上限值
		double dblRang_standard=0;//增扣标准值
		double dblBase_price=0;//基准价格值
		double dblUnitPrice=0;//价格值

		boolean rangeIsFound=false;//是否找到热值对应的范围
		PriceBean priceBean=new PriceBean();

		//得到指标ID
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("po_price_scheme_id", po_price_scheme_id);
		quality_item_id = qnetBaseChangeCalculatorDao.getItemID(map);

		map.clear();
		map.put("id", po_price_scheme_id);
		PriceScheme priceScheme = priceSchemeService.getPriceSchemeByCaigddId(map);

		String componetId = priceScheme.getPrice_component_id().toString();

		//根据指标的id作为参数的名称,得到指标的值
		double quality_item_vlaue = getParameterValue(mapParas,quality_item_id);

		try {
			//按质计价的表数据
			//List list = qnetBaseChangeCalculatorDao.getTemplateData(po_price_scheme_id);
			Map pMap = new HashMap<String, Object>();
			pMap.put("componentid", componetId);
			List<PriceQalityRange> list = qnetBaseChangeCalculatorDao.getTemplateData(map);
			if (list.size()<=0){
				priceBean.errMsg="没有找到指标的模板数据，计算失败！";
				return priceBean;
			}
			/****************************************表结构弄混了 我晕 暂时搁置********************************/

			//循环遍历模板数据每一行数据，找到热值对应的范围及价格
			for (PriceQalityRange priceQalityRange : list) {
				range_min = String.valueOf(priceQalityRange.getRange_min());
				range_max = String.valueOf(priceQalityRange.getRange_max());
				rang_standard = String.valueOf(priceQalityRange.getUnitprice());
			}
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				HashMap record = (HashMap) iter.next();
				range_min=(String)record.get("RANGE_MIN");
				range_max=(String)record.get("RANGE_MAX");
				rang_standard=(String)record.get("RANGE_STANDARD");
				base_price=(String)record.get("BASE_PRICE");
				unitPrice=(String)record.get("UNITPRICE");

				//如果，上限，下限都为空，忽略
				if (!("NULL".equalsIgnoreCase(range_min)  && "NULL".equalsIgnoreCase(range_max))){
					if ("NULL".equalsIgnoreCase(range_min)){
						//下限为空，逻辑为 数据《=上限
						range_min="";
						dblRange_max=Util.toDouble(range_max,0);
						if (quality_item_vlaue<=dblRange_max){
							rangeIsFound=true;
							break;
						}
					}else if ("NULL".equalsIgnoreCase(range_max)){
						//上限为空  逻辑为 数据>=下限
						range_max="";
						dblRange_min=Util.toDouble(range_min,0);
						if (quality_item_vlaue>=dblRange_min){
							rangeIsFound=true;
							break;
						}
					}else {
						//上限，下限都有数据  数据>=下限 且  数据《=上限
						dblRange_min=Util.toDouble(range_min,0);
						dblRange_max=Util.toDouble(range_max,0);
						if  (quality_item_vlaue>=dblRange_min &&  quality_item_vlaue<=dblRange_max){
							rangeIsFound=true;
							break;
						}
					}
				}
			}
			//找到符合的范围，计算价格
			if (rangeIsFound){
				dblRang_standard=Util.toDouble(rang_standard,0);
				dblBase_price=Util.toDouble(base_price,0);
				dblUnitPrice=Util.toDouble(unitPrice,0);

				//(发热量-增扣质量标准)*增扣价格+基准价格
				priceBean.price=dblBase_price+dblUnitPrice*(quality_item_vlaue-dblRang_standard);
				priceBean.priceChange=dblUnitPrice*(quality_item_vlaue-dblRang_standard);
				priceBean.isSuccess=true;
				priceBean.rangeMin=range_min;
				priceBean.rangeMax=range_max;
			}else{
				priceBean.errMsg="没有找到符合范围的价格，计算失败！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priceBean;
	}

	private double getParameterValue(Map<String,Double> aMap,String name){
		int mapsize = aMap.size();
		Double value = null;
		for (int i = 0; i < mapsize; i++){
			value=aMap.get(name);
		}
		return value.doubleValue();
	}
	
	public boolean delete(String transactionID, String priceItem) {
		return false;
	}
	
	public String getShowHtml(String po_price_scheme_id) {
		return null;
	}

}
